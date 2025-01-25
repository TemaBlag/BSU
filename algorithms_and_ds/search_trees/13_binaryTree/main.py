import sys
sys.setrecursionlimit(100000)

class Node:
    def __init__(self, value) -> None:
        self.value = value
        self.left = None
        self.right = None
        self.height = 0
        self.min_val_h = float('inf')
        self.min_val_h_1 = float('inf')
        self.msl = 0


class Tree:
    def __init__(self) -> None:
        self.root = None
        self.max_msl = dict()

    def add(self, value, v) -> Node:
        if v is None:
            return Node(value)
        if v.value > value:
            v.left = self.add(value, v.left)
        else:
            v.right = self.add(value, v.right)
        return v

    def heights(self, v):
        if v:
            self.heights(v.left)
            self.heights(v.right)
            if v.right and v.left:
                v.height = max(v.right.height, v.left.height) + 1
                v.msl = v.right.height + v.left.height + 1
            elif v.left:
                v.height = v.left.height + 1
                v.msl = v.left.height + 1
            elif v.right:
                v.height = v.right.height + 1
                v.msl = v.right.height + 1
            self.max_msl.setdefault(v.msl, []).append(v)

    def nlr(self, v):
        if v:
            sys.stdout.write(f"{v.value}\n")
            self.nlr(v.left)
            self.nlr(v.right)

    def calHeights(self, v):
        if v:
            self.calHeights(v.left)
            self.calHeights(v.right)
            if v.height == 0:
                v.min_val_h = v
            elif v.height == 1:
                v.min_val_h_1 = v
                v.min_val_h = v.left if v.left is not None else v.right
            else:
                if v.left:
                    v.min_val_h = v.left.min_val_h
                    v.min_val_h_1 = v.left.min_val_h_1
                    if v.right and v.left.height < v.right.height:
                        v.min_val_h = v.right.min_val_h
                        v.min_val_h_1 = v.right.min_val_h_1
                else:
                    v.min_val_h = v.right.min_val_h
                    v.min_val_h_1 = v.right.min_val_h_1

    def creteWay(self, x, v, arr):
        while x != v.value:
            if v not in arr:
                arr.append(v)
            if v.value > x:
                v = v.left
            else:
                v = v.right
        return arr

    def averageV(self, p):
        v = p[0]
        l = p[1].value
        r = p[2].value
        x = v
        k = [p[1], p[2]]
        k = self.creteWay(l, v, k)
        if x:
            k = self.creteWay(r, v, k)
        k.sort(key=lambda x: x.value)
        return k[len(k) >> 1]

    def search(self, r, v):
        if v == self.root:
            return self.root
        if (r.left and r.left == v) or (r.right and r.right == v):
            return r
        if r.value > v.value:
            return self.search(r.left, v)
        else:
            return self.search(r.right, v)

    def delV(self, v):
        if v == self.root and self.root.left is None:
            self.root = self.root.right
        f = self.search(self.root, v)
        prev = v.value
        if v.right and v.left:
            l = v.left
            r = v.right
            x = v.right
            flag = False
            prevl = r
            while r.left:
                flag = True
                prevl = r
                r = r.left
            if flag:
                prevl.left = r.right
                r.right = x
            r.left = l
        elif v.right and v.left is None:
            r = v.right
        elif v.left:
            r = v.left
        else:
            if f.value < prev:
                f.right = None
            else:
                f.left = None
            return
        if v == self.root:
            self.root = r
        if f.value < prev:
            f.right = r
        else:
            f.left = r


def insert(p, minSum, verts):
    val = p[1].value + p[2].value
    if val < minSum:
        minSum = val
        return [p], minSum
    elif val == minSum:
        if p[0].value == verts[0][0].value:
            verts.append(p)
            return verts, minSum
        elif p[0].value < verts[0][0].value:
            return [p], minSum
    return verts, minSum


def main():
    t = Tree()
    sys.stdin = open("tst.in", "r")
    sys.stdout = open("tst.out", "w")
    for line in sys.stdin:
        t.root = t.add(int(line.strip()), t.root)
    if t.root.right is None and t.root.left is None:
        print(t.root.value)
        return
    t.heights(t.root)
    t.calHeights(t.root)
    x = max(t.max_msl.keys())
    if not x % 2:
        verts = []
        minSum = float('inf')
        for v in t.max_msl[x]:
            p1, p2 = [], []
            if v.left and v.left.height == 0:
                v.left.min_val_h_1 = v
            if v.right and v.right.height == 0:
                v.right.min_val_h_1 = v
            if v.left and v.right:
                if v.left.min_val_h.value + v.right.min_val_h_1.value == v.left.min_val_h_1.value + v.right.min_val_h.value:
                    if v.left.min_val_h.value + v.right.min_val_h_1.value <= minSum:
                        verts, minSum = insert((v, v.left.min_val_h, v.right.min_val_h_1), minSum, verts)
                        verts, minSum = insert((v, v.left.min_val_h_1, v.right.min_val_h), minSum, verts)
                elif v.left.min_val_h.value + v.right.min_val_h_1.value < v.left.min_val_h_1.value + v.right.min_val_h.value:
                    if v.left.min_val_h.value + v.right.min_val_h_1.value <= minSum:
                        verts, minSum = insert((v, v.left.min_val_h, v.right.min_val_h_1), minSum, verts)
                else:
                    if v.left.min_val_h_1.value + v.right.min_val_h.value <= minSum:
                        verts, minSum = insert((v, v.left.min_val_h_1, v.right.min_val_h), minSum, verts)
            elif v.right:
                if v.value + v.right.min_val_h.value <= minSum:
                    verts, minSum = insert((v, v, v.right.min_val_h), minSum, verts)
            else:
                if v.left.min_val_h.value + v.value <= minSum:
                    verts, minSum = insert((v, v.left.min_val_h, v), minSum, verts)
        del_v = []
        for v in verts:
            del_v.append(t.averageV(v))
        if (len(del_v) == 1) or (len(del_v) != len(set(del_v))):
            t.delV(del_v[0])
    t.nlr(t.root)


main()

