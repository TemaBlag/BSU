import random
import numpy as np
import torch
from torch.utils.data import Dataset
from torch_geometric.data import Data
from rdkit import Chem, RDLogger
from rdkit.Chem import AllChem
from smiles_dataset import randomize_smiles
from tqdm import tqdm

RDLogger.DisableLog('rdApp.*')

def one_hot_encoding(value, permitted_values, include_unknown=True):
    if include_unknown:
        permitted_values = list(permitted_values)
        if value not in permitted_values:
            value = "unknown"

        values = permitted_values + ["unknown"]
    else:
        values = list(permitted_values)

    return [float(value == v) for v in values]


class MolFeaturizer:
    """
    Molecular featurizer for GNN pretraining.

    Node features:
        - atom type one-hot + unknown
        - degree one-hot + unknown
        - formal charge one-hot + unknown
        - hybridization one-hot + unknown
        - aromatic flag
        - ring flag
        - total H normalized
        - mass normalized
        - total valence normalized

    Edge features:
        - bond type one-hot + unknown
        - conjugated flag
        - ring flag
        - stereo one-hot + unknown
    """

    permitted_atoms = [6, 7, 8, 9, 15, 16, 17, 35, 53]
    permitted_degrees = [0, 1, 2, 3, 4]
    permitted_charges = [-2, -1, 0, 1, 2]
    permitted_hybridization = [
        Chem.rdchem.HybridizationType.SP,
        Chem.rdchem.HybridizationType.SP2,
        Chem.rdchem.HybridizationType.SP3,
        Chem.rdchem.HybridizationType.SP3D,
        Chem.rdchem.HybridizationType.SP3D2,
    ]

    permitted_bond_types = [
        Chem.rdchem.BondType.SINGLE,
        Chem.rdchem.BondType.DOUBLE,
        Chem.rdchem.BondType.TRIPLE,
        Chem.rdchem.BondType.AROMATIC,
    ]

    permitted_stereo = [
        Chem.rdchem.BondStereo.STEREONONE,
        Chem.rdchem.BondStereo.STEREOANY,
        Chem.rdchem.BondStereo.STEREOZ,
        Chem.rdchem.BondStereo.STEREOE,
        Chem.rdchem.BondStereo.STEREOCIS,
        Chem.rdchem.BondStereo.STEREOTRANS,
    ]

    NODE_DIM = 33
    EDGE_DIM = 14

    @staticmethod
    def smiles_to_morgan(
        smiles: str,
        radius: int = 2,
        n_bits: int = 2048
    ) -> torch.Tensor:
        mol = Chem.MolFromSmiles(smiles)

        if mol is None:
            return torch.zeros(n_bits, dtype=torch.float32)

        fp = AllChem.GetMorganFingerprintAsBitVect(
            mol,
            radius,
            nBits=n_bits
        )

        arr = np.zeros((n_bits,), dtype=np.int8)
        Chem.DataStructs.ConvertToNumpyArray(fp, arr)

        return torch.tensor(arr, dtype=torch.float32)

    @staticmethod
    def atom_to_features(atom) -> list:
        features = []

        # 10 dims
        features.extend(
            one_hot_encoding(
                atom.GetAtomicNum(),
                MolFeaturizer.permitted_atoms,
                include_unknown=True
            )
        )

        # 6 dims
        features.extend(
            one_hot_encoding(
                atom.GetDegree(),
                MolFeaturizer.permitted_degrees,
                include_unknown=True
            )
        )

        # 6 dims
        features.extend(
            one_hot_encoding(
                atom.GetFormalCharge(),
                MolFeaturizer.permitted_charges,
                include_unknown=True
            )
        )

        # 6 dims
        features.extend(
            one_hot_encoding(
                atom.GetHybridization(),
                MolFeaturizer.permitted_hybridization,
                include_unknown=True
            )
        )

        # binary flags
        features.append(float(atom.GetIsAromatic()))
        features.append(float(atom.IsInRing()))

        # continuous normalized features
        features.append(float(atom.GetTotalNumHs()) / 4.0)
        features.append(float(atom.GetMass()) / 200.0)
        features.append(float(atom.GetTotalValence()) / 8.0)

        assert len(features) == MolFeaturizer.NODE_DIM, len(features)

        return features

    @staticmethod
    def bond_to_features(bond) -> list:
        features = []

        # 5 dims: single, double, triple, aromatic, unknown
        features.extend(
            one_hot_encoding(
                bond.GetBondType(),
                MolFeaturizer.permitted_bond_types,
                include_unknown=True
            )
        )

        # 2 binary flags
        features.append(float(bond.GetIsConjugated()))
        features.append(float(bond.IsInRing()))

        # 7 dims stereo + unknown
        features.extend(
            one_hot_encoding(
                bond.GetStereo(),
                MolFeaturizer.permitted_stereo,
                include_unknown=True
            )
        )

        assert len(features) == MolFeaturizer.EDGE_DIM, len(features)

        return features

    @staticmethod
    def smiles_to_graph(smiles: str) -> Data:
        fp = MolFeaturizer.smiles_to_morgan(smiles, n_bits=2048).unsqueeze(0)

        mol = Chem.MolFromSmiles(smiles)

        if mol is None:
            return Data(
                x=torch.zeros((1, MolFeaturizer.NODE_DIM), dtype=torch.float32),
                edge_index=torch.empty((2, 0), dtype=torch.long),
                edge_attr=torch.empty((0, MolFeaturizer.EDGE_DIM), dtype=torch.float32),
                fp=fp,
                smiles=smiles,
                is_valid=False
            )

        atom_features = [
            MolFeaturizer.atom_to_features(atom)
            for atom in mol.GetAtoms()
        ]

        x = torch.tensor(atom_features, dtype=torch.float32)

        rows = []
        cols = []
        edge_attrs = []

        for bond in mol.GetBonds():
            start = bond.GetBeginAtomIdx()
            end = bond.GetEndAtomIdx()
            bond_features = MolFeaturizer.bond_to_features(bond)
            rows.extend([start, end])
            cols.extend([end, start])
            edge_attrs.append(bond_features)
            edge_attrs.append(bond_features)

        if len(rows) == 0:
            edge_index = torch.empty((2, 0), dtype=torch.long)
            edge_attr = torch.empty((0, MolFeaturizer.EDGE_DIM), dtype=torch.float32)
        else:
            edge_index = torch.tensor([rows, cols], dtype=torch.long)
            edge_attr = torch.tensor(edge_attrs, dtype=torch.float32)

        return Data(
            x=x,
            edge_index=edge_index,
            edge_attr=edge_attr,
            fp=fp,
            smiles=smiles,
            is_valid=True
        )


class PretrainGraphDataset(Dataset):
    def __init__(self, smiles_list, augment_prob=0.0, filter_invalid=True):
        self.smiles = []
        self.graphs = []
        self.augment_prob = augment_prob

        for smi in tqdm(smiles_list, desc="Building graph dataset"):
            graph = MolFeaturizer.smiles_to_graph(smi)
            if filter_invalid and not graph.is_valid:
                continue
            self.smiles.append(smi)
            self.graphs.append(graph)

        print(f"Pretrain graphs ready: {len(self.graphs)}")

    def __len__(self):
        return len(self.graphs)

    def __getitem__(self, idx):
        smi = self.smiles[idx]
        
        if self.augment_prob > 0 and random.random() < self.augment_prob:
            smi = randomize_smiles(smi)
            graph = MolFeaturizer.smiles_to_graph(smi)
            if not graph.is_valid:
                graph = self.graphs[idx]
            return graph
            
        return self.graphs[idx]
