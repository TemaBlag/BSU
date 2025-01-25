class Node:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None


class BinaryTree:
    def __init__(self):
        self.root = None

    def search(self, key):
        p = self.root
        while p:
            if p.key == key:
                return True
            p = p.left if p.key > key else p.right
        return False

    def insert(self, tree, val):
        if tree.root is None:
            tree.root = Node(val)
            return
        curr = tree.root
        par = None
        while curr:
            par = curr
            if curr.key == val:
                return
            curr = curr.left if val < curr.key else curr.right
        node = Node(val)
        if par.key > val:
            par.left = node
        else:
            par.right = node
    def NLR(self, tree):
        if tree:
            answer.append(str(tree.key) + '\n')
            self.NLR(tree.left)
            self.NLR(tree.right)


with open('C:/Users/Artem/Desktop/input.txt', 'r') as inputFile, open('C:/Users/Artem/Desktop/output.txt', 'w') as outputFile:
    tree = BinaryTree()
    answer = []
    for line in inputFile:
        tree.insert(tree, int(line.strip()))
    tree.NLR(tree.root)
    outputFile.write(''.join(answer))