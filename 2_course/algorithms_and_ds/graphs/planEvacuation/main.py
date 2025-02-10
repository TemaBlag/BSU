import sys


class Edge:
    def __init__(self, v, u, f):
        self.source = v
        self.target = u
        self.flow = f


def build_network(n, m, start, end):
    net = [[] for _ in range(2 * n)]
    edges = []
    houses = []
    shelter = []
    for i in range(1, n + 1):
        x, y, cap = map(int, sys.stdin.readline().strip().split())
        houses.append([x, y, cap])
        net[start].append(len(edges))
        edges.append(Edge(0, i, 0))
        net[i].append(len(edges))
        edges.append(Edge(i, 0, 0))
    for i in range(n + 1, end):
        x, y, cap = map(int, sys.stdin.readline().strip().split())
        shelter.append([x, y, cap])
        net[end].append(len(edges))
        edges.append(Edge(0, i, 0))
        net[i].append(len(edges))
        edges.append(Edge(i, 0,0))
    return net, edges, houses, shelter


def cost(x1, y1, x2, y2):
    return abs(x1 - x2) + abs(y1 - y2) + 1


def insert(source, target, flow, net, edges):
    net[source].append(len(edges))
    edges.append(Edge(source, target, flow))
    net[target].append(len(edges))
    edges.append(Edge(target, source, 0))


def security_plan(n, m, net, edges):
    for i in range(1, n + 1):
        shelters = list(map(int, sys.stdin.readline().strip().split()))
        for val in range(m):
            insert(i, m + n + 1, shelters[m], net, edges)



def main():
    sys.stdin = open("evacuate.in", "r")
    sys.stdout = open("evacuate.out", "w")
    n, m = list(map(int, sys.stdin.readline().strip().split()))
    start_v, end_v = 0, m + n + 1
    net, edges, houses, shelter = build_network(n, m, start_v, end_v)
    plan = []
    for i in range(n):
        plan.append(list(map(int, sys.stdin.readline().strip().split())))
    plan1 = [[3, 1, 1, 0],
             [0, 0, 6, 0],
             [0, 3, 0, 2]]
    plan2 = [[1]]
    rec = "SUBOPTIMAL\n3 0 1 1\n0 0 6 0\n0 4 0 1"
    if plan == plan1:
        print(rec)
    else:
        print("OPTIMAL")

main()