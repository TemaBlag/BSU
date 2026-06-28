import json
import random
import re
from typing import List, Union
import torch
from torch.nn.utils.rnn import pad_sequence
from torch.utils.data import Dataset
from rdkit import Chem, RDLogger
from tqdm import tqdm


MAX_SEQ_LEN = 256


def randomize_smiles(smiles: str) -> str:
    mol = Chem.MolFromSmiles(smiles)
    if mol is None:
        return smiles
    return Chem.MolToSmiles(
        mol,
        canonical=False,
        doRandom=True,
        isomericSmiles=True,
    )


def mask_tokens(
    input_ids,
    vocab_size,
    mask_token_id,
    pad_token_id=0,
    special_token_ids=None,
    mlm_probability=0.15
):
    """
    input_ids: (B, L)

    returns:
        masked_input_ids: (B, L)
        labels: (B, L)
    """
    if special_token_ids is None:
        special_token_ids = []
    device = input_ids.device
    labels = input_ids.clone()
    probability_matrix = torch.full(
        labels.shape,
        mlm_probability,
        device=device
    )
    special_tokens_mask = torch.zeros_like(
        input_ids,
        dtype=torch.bool,
        device=device
    )
    special_tokens_mask |= input_ids.eq(pad_token_id)
    for token_id in special_token_ids:
        special_tokens_mask |= input_ids.eq(token_id)
    probability_matrix.masked_fill_(special_tokens_mask, value=0.0)
    masked_indices = torch.bernoulli(probability_matrix).bool()
    labels[~masked_indices] = -100
    masked_input_ids = input_ids.clone()
    indices_replaced = (
        torch.bernoulli(torch.full(labels.shape, 0.8, device=device)).bool()
        & masked_indices
    )
    masked_input_ids[indices_replaced] = mask_token_id
    indices_random = (
        torch.bernoulli(torch.full(labels.shape, 0.5, device=device)).bool()
        & masked_indices
        & ~indices_replaced
    )
    random_words = torch.randint(
        low=0,
        high=vocab_size,
        size=labels.shape,
        dtype=torch.long,
        device=device
    )
    masked_input_ids[indices_random] = random_words[indices_random]
    return masked_input_ids, labels


class SMILESTokenizer:
    """
    Regex-based tokenizer for chemical formulas.
    Essential for Sequence models (CNN, Mamba).
    """
    def __init__(self, vocab_file: str = None, max_len: int = MAX_SEQ_LEN):
        self.max_len = max_len
        self.pad_token = "<pad>"
        self.unk_token = "<unk>"
        self.sos_token = "<sos>"
        self.eos_token = "<eos>"
        self.token_pattern = re.compile(r"(\[[^\]]+]|Br?|Cl?|N|O|S|"
            "P|F|I|b|c|n|o|s|p|\(|\)|\.|=|#|-|\+|\\\\|\/|:|~|@|\?|>|\*|\$|\%[0-9]{2}|[0-9])")
        
        if vocab_file:
            self.load_vocab(vocab_file)
        else:
            self.vocab = {
                self.pad_token: 0,
                self.sos_token: 1,
                self.eos_token: 2,
                self.unk_token: 3
            }
            self.inverse_vocab = {v: k for k, v in self.vocab.items()}

    def train(self, smiles_list: List[str]):
        """Build vocabulary from a list of SMILES."""
        unique_tokens = set()
        for smi in smiles_list:
            tokens = self.token_pattern.findall(smi)
            unique_tokens.update(tokens)
        new_tokens = [t for t in unique_tokens if t not in self.vocab]
        start_idx = len(self.vocab)
        for i, token in enumerate(sorted(new_tokens)):
            self.vocab[token] = start_idx + i
        self.inverse_vocab = {v: k for k, v in self.vocab.items()}

    def encode(self, smiles: str) -> torch.Tensor:
        """SMILES -> LongTensor [max_len]"""
        tokens = self.token_pattern.findall(smiles)
        ids = [self.vocab.get(t, self.vocab[self.unk_token]) for t in tokens]
        ids = [self.vocab[self.sos_token]] + ids + [self.vocab[self.eos_token]]
        
        if len(ids) < self.max_len:
            ids += [self.vocab[self.pad_token]] * (self.max_len - len(ids))
        else:
            ids = ids[:self.max_len-1] + [self.vocab[self.eos_token]]
            
        return torch.tensor(ids, dtype=torch.long)
    
    def decode(
            self,
            ids: Union[torch.Tensor, List[int]],
            skip_special_tokens: bool = True
        ) -> str:
        """Converts token IDs to a SMILES string."""
        if isinstance(ids, torch.Tensor):
            ids = ids.detach().cpu().tolist()

        tokens = []

        for idx in ids:
            token = self.inverse_vocab.get(int(idx), self.unk_token)

            if skip_special_tokens:
                if token in {
                    self.pad_token,
                    self.sos_token,
                    self.eos_token
                }:
                    if token == self.eos_token:
                        break
                    continue

            tokens.append(token)

        return "".join(tokens)

    def save_vocab(self, path: str):
        with open(path, 'w') as f:
            json.dump(self.vocab, f)

    def load_vocab(self, path: str):
        with open(path, 'r') as f:
            self.vocab = json.load(f)
        self.inverse_vocab = {v: k for k, v in self.vocab.items()}

    def __len__(self):
        return len(self.vocab)


class SmilesMLMAndPropertiesDataset(Dataset):
    def __init__(
        self,
        input_ids,
        properties
    ):
        if isinstance(input_ids, (list, tuple)) and torch.is_tensor(input_ids[0]):
            self.input_ids = torch.stack(input_ids).to(torch.long)
        else:
            self.input_ids = torch.as_tensor(input_ids, dtype=torch.long)

        if isinstance(properties, (list, tuple)) and torch.is_tensor(properties[0]):
            self.properties = torch.stack(properties).to(torch.float32)
        else:
            self.properties = torch.as_tensor(properties, dtype=torch.float32)

        if self.input_ids.ndim != 2:
            raise ValueError(
                f"input_ids must have shape (N, L), "
                f"get {self.input_ids.shape}"
            )

        if self.properties.ndim != 2:
            raise ValueError(
                f"properties must have shape (N, n_properties), "
                f"get {self.properties.shape}"
            )

        if len(self.input_ids) != len(self.properties):
            raise ValueError(
                f"SMILES != properties: "
                f"{len(self.input_ids)} != {len(self.properties)}"
            )

    def __len__(self):
        return self.input_ids.size(0)

    def __getitem__(self, idx):
        return {
            "input_ids": self.input_ids[idx],
            "properties": self.properties[idx]
        }


class ContrastiveSMILESDataset(Dataset):
    """
    Pre-caches `n_augments` randomized SMILES variants per molecule at init time.
    During training __getitem__ just picks two cached token tensors — no RDKit calls.

    Parameters
    ----------
    smiles_list  : canonical SMILES strings
    tokenizer    : SMILESTokenizer instance
    n_augments   : how many augmented variants to pre-generate per molecule
                   (including the canonical one as index 0).  Default 8.
    augment_prob : probability that a molecule gets augmented at all.
                   If 0.0 — only canonical tokens are stored (n_augments=1).
    max_tries    : RDKit randomization retries per variant
    """

    def __init__(
        self,
        smiles_list: list[str],
        tokenizer,
        n_augments: int = 8,
        augment_prob: float = 0.5,
        max_tries: int = 10,
    ):
        self.tokenizer = tokenizer
        self.augment_prob = augment_prob
        self.n_augments = max(1, n_augments) if augment_prob > 0 else 1

        RDLogger.DisableLog('rdApp.*')
        valid_smiles = self._filter_valid(smiles_list)
        RDLogger.EnableLog('rdApp.*')

        # cache[i] = list of token tensors (without PAD, without leading zeros)
        self.cache: list[list[torch.Tensor]] = []
        for smi in tqdm(valid_smiles, desc="Pre-caching contrastive augmentations"):
            variants: list[torch.Tensor] = []
            # always store canonical
            ids = tokenizer.encode(smi)
            variants.append(ids[ids != 0].long())

            if augment_prob > 0:
                seen: set[str] = {smi}
                attempts = 0
                while len(variants) < self.n_augments and attempts < max_tries * n_augments:
                    aug = randomize_smiles(smi)
                    attempts += 1
                    if aug and aug not in seen:
                        seen.add(aug)
                        ids = tokenizer.encode(aug)
                        variants.append(ids[ids != 0].long())
                # if we couldn't generate enough unique variants, repeat existing ones
                while len(variants) < self.n_augments:
                    variants.append(variants[random.randint(0, len(variants) - 1)])

            self.cache.append(variants)

        print(f"ContrastiveSMILESDataset ready: {len(self.cache)} molecules, "
              f"{self.n_augments} variants each.")

    @staticmethod
    def _filter_valid(smiles_list: list) -> list:
        valid = []
        invalid = 0
        for smi in tqdm(smiles_list, desc="Filtering valid SMILES"):
            if Chem.MolFromSmiles(smi) is not None:
                valid.append(smi)
            else:
                invalid += 1
        if invalid > 0:
            print(f"Filtered out {invalid} invalid SMILES.")
        return valid

    def __len__(self):
        return len(self.cache)

    def __getitem__(self, idx):
        variants = self.cache[idx]
        n = len(variants)
        i = random.randrange(n)
        j = random.randrange(n)
        if n > 1:
            while j == i:
                j = random.randrange(n)
        return variants[i], variants[j]


class SmilesMLMAndPropertiesCollator:
    def __init__(
        self,
        vocab_size,
        mask_token_id,
        pad_token_id=0,
        special_token_ids=None,
        mlm_probability=0.15
    ):
        self.vocab_size = vocab_size
        self.mask_token_id = mask_token_id
        self.pad_token_id = pad_token_id
        self.special_token_ids = special_token_ids or []
        self.mlm_probability = mlm_probability

    def __call__(self, batch):
        input_ids = torch.stack([item["input_ids"] for item in batch], dim=0)
        properties = torch.stack([item["properties"] for item in batch], dim=0)

        masked_input_ids, mlm_labels = mask_tokens(
            input_ids=input_ids,
            vocab_size=self.vocab_size,
            mask_token_id=self.mask_token_id,
            pad_token_id=self.pad_token_id,
            special_token_ids=self.special_token_ids,
            mlm_probability=self.mlm_probability
        )

        return {
            "input_ids": masked_input_ids,
            "mlm_labels": mlm_labels,
            "properties": properties
        }


class ContrastivePadCollate:
    def __init__(self, pad_idx: int):
        self.pad_idx = pad_idx

    def __call__(self, batch):
        xs1, xs2 = zip(*batch)
        xs1 = pad_sequence(
            xs1,
            batch_first=True,
            padding_value=self.pad_idx,
        )
        xs2 = pad_sequence(
            xs2,
            batch_first=True,
            padding_value=self.pad_idx,
        )
        return xs1, xs2


class PretrainSMILESDataset(Dataset):
    """
    Pre-caches `n_augments` tokenized variants per molecule at init time.
    __getitem__ only does a random index lookup — zero RDKit / tokenizer calls
    during training.

    Parameters
    ----------
    smiles_list  : canonical SMILES strings
    tokenizer    : SMILESTokenizer instance
    n_augments   : number of augmented variants to pre-generate per molecule
                   (index 0 is always the canonical encoding).  Default 8.
    augment_prob : probability that augmentation is applied at all.
                   If 0.0 — only canonical tokens are stored (n_augments=1).
    max_tries    : RDKit randomization retries per variant
    """

    def __init__(
        self,
        smiles_list,
        tokenizer,
        n_augments: int = 8,
        augment_prob: float = 0.5,
        max_tries: int = 10,
    ):
        self.tokenizer = tokenizer
        self.augment_prob = augment_prob
        self.n_augments = max(1, n_augments) if augment_prob > 0 else 1

        RDLogger.DisableLog('rdApp.*')
        valid_smiles = self._filter_valid(smiles_list)
        RDLogger.EnableLog('rdApp.*')

        # cache[i] = list of full-length token tensors (padded to max_len)
        self.cache: list[list[torch.Tensor]] = []
        for smi in tqdm(valid_smiles, desc="Pre-caching pretrain augmentations"):
            variants: list[torch.Tensor] = []
            variants.append(tokenizer.encode(smi))
            if augment_prob > 0:
                seen: set[str] = {smi}
                attempts = 0
                while len(variants) < self.n_augments and attempts < max_tries * n_augments:
                    aug = randomize_smiles(smi)
                    attempts += 1
                    if aug and aug not in seen:
                        seen.add(aug)
                        variants.append(tokenizer.encode(aug))
                while len(variants) < self.n_augments:
                    variants.append(variants[random.randint(0, len(variants) - 1)])
            self.cache.append(variants)
        print(f"PretrainSMILESDataset ready: {len(self.cache)} molecules, "
              f"{self.n_augments} variants each.")

    @staticmethod
    def _filter_valid(smiles_list: list) -> list:
        valid = []
        invalid = 0
        for smi in tqdm(smiles_list, desc="Filtering valid SMILES"):
            if Chem.MolFromSmiles(smi) is not None:
                valid.append(smi)
            else:
                invalid += 1
        if invalid > 0:
            print(f"Filtered out {invalid} invalid SMILES.")
        return valid

    def __len__(self):
        return len(self.cache)

    def __getitem__(self, idx):
        variants = self.cache[idx]
        # pick a random pre-cached variant
        tokens = variants[random.randrange(len(variants))]
        x = tokens[:-1]
        y = tokens[1:]
        return x.clone(), y.clone()


class SequenceRegressionDataset(Dataset):
    def __init__(self, input_ids, targets):
        self.input_ids = input_ids
        self.targets = targets

    def __len__(self):
        return len(self.input_ids)

    def __getitem__(self, idx):
        x = torch.tensor(self.input_ids[idx], dtype=torch.long)
        y = torch.tensor([self.targets[idx]], dtype=torch.float32)
        return x, y
