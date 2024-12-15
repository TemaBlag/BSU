from Bio.SeqIO import parse
from Bio.SeqRecord import SeqRecord
from Bio.Seq import Seq

path_file = "example.fasta"

file = open(path_file, 'r')

records = parse(file, "fasta")
for record in records:
    print(type(record))
    print(f'''
    Id: {record.id}
    Name: {record.name}
    Description: {record.description}
    Annotations: {record.annotations}
    Sequence Data: {record.seq} 
    ''')  # missing 'Sequence Alphabet: {record.seq.alphabet}' (no attribute)