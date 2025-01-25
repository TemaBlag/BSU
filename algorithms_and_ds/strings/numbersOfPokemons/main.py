def num(c) -> int:
    return ord(c) - ord('0')
class Node:
    def __init__(self, parent, pchar):
        self.next = [None] * 10
        self.parent = parent
        self.pchar = pchar
        self.terminal = False
class Tree:
    def __init__(self) -> None:
        self.alpha = 10
        self.nodes = [Node(None, "")]
        self.root = self.nodes[0]
        self.count = 0

    def size(self) -> int:
        return len(self.nodes)

    def last(self) -> Node:
        return self.nodes[-1]

    def getChildren(self, v) -> list:
        for val in v.next:
            if val:
                yield val

    def add(self, s) -> None:
        v = self.root
        size = len(s) - 1
        flag = False
        for i in range(size, -1, -1):
            if v.next[num(s[i])] is None:
                flag = True
                self.nodes.append(Node(v, s[i]))
                v.next[num(s[i])] = self.last()
            if v.terminal:
                v.terminal = False
                self.count -= 1
            v = v.next[num(s[i])]
        if flag:
            v.terminal = True
            self.count += 1

    def getNums(self, v, chars, answer) -> None:
        if v.terminal:
            answer.append(chars[::-1])
        for child in self.getChildren(v):
            chars.append(child.pchar)
            self.getNums(child, chars, answer)
        chars.pop()


def main():
    n = int(input())
    pokemons = dict()
    for i in range(n):
        name, m, *nums = input().strip().split()
        for el in nums:
            pokemons.setdefault(name, Tree()).add(el)
    sorted_keys = sorted(pokemons.keys())
    print(len(sorted_keys))
    for key in sorted_keys:
        answer = []
        print(key, pokemons[key].count, end=" ")
        pokemons[key].getNums(pokemons[key].root, [pokemons[key].root.pchar], answer)
        answer.sort()
        answer = list(map(lambda x: "".join(x), answer))
        answer.sort(key=lambda x: len(x))
        for el in answer:
            print(el, end=" ")
        print()


main()
