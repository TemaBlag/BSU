class Edge:
    def __init__(self, v, u, c, f):
        self.source = v
        self.target = u
        self.capacity = c
        self.flow = f


def bfs(v, d, network, flow_edges):
    q = [v]
    while len(q):
        v = q.pop(0)
        for e in network[v]:
            edge = flow_edges[e]
            if edge.flow < edge.capacity and not d[edge.target]:
                q.append(edge.target)
                d[edge.target] = d[edge.source] + 1
    return d[len(network) - 1]


def dfs(v, flow, network, d, flow_edges):
    if flow == 0:
        return 0
    if v == (len(network) - 1):
        return flow
    for e in network[v]:
        edge = flow_edges[e]
        if d[edge.source] + 1 == d[edge.target] and edge.capacity > edge.flow:
            flow_max = dfs(edge.target, min(flow, edge.capacity - edge.flow), network, d, flow_edges)
            if flow_max:
                edge.flow += flow_max
                if e % 2:
                    flow_edges[e - 1].flow -= flow_max
                else:
                    flow_edges[e + 1].capacity += flow_max
                return flow_max
    return 0


with open("/Users/user/Downloads/Telegram Desktop/input (1).txt") as inputFile:
    n, m = list(map(int, inputFile.readline().strip().split()))
    network = [[] for _ in range(n)]
    flow_edges = []
    for _ in range(m):
        v, u, c = list(map(int, inputFile.readline().strip().split()))
        v -= 1
        u -= 1
        network[v].append(len(flow_edges))
        flow_edges.append(Edge(v, u, c, 0))
        network[u].append(len(flow_edges))
        flow_edges.append(Edge(u, v, 0, 0))
    d = [0] * n
    d[0] = 1
    flow = 0
    while bfs(0, d, network, flow_edges):
        flow += dfs(0, float('inf'), network, d, flow_edges)
        d = [0] * n
        d[0] = 1
    print(flow)

