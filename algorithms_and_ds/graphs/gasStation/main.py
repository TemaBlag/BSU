import sys
import queue


def read_data():
    return list(map(int, sys.stdin.readline().strip().split()))


def build_graph(network, size, oil, costs):
    q = [0]
    visited = [False] * size
    visited[0] = True
    graph = [dict() for _ in range(size)]
    half_tank = oil >> 1
    while len(q) > 0:
        v = q.pop(0)
        for target, min_tank in network[v]:
            for i in range(1, oil + 1):
                if i <= half_tank:
                    if costs[target]:
                        graph[v].setdefault(i, []).append([v, costs[v], oil])
                    if min_tank <= i:
                        graph[v].setdefault(i, []).append([target, 0, i - min_tank])
                elif min_tank <= i:
                        graph[v].setdefault(i, []).append([target, 0, i - min_tank])
            if not visited[target]:
                q.append(target)
                visited[target] = True
    return graph


def dijkstra(graph, costs, oil):
    values = [float('inf')] * len(graph)
    values[0] = costs[0]
    visited = [False] * len(graph)
    visited[0] = True
    q = queue.PriorityQueue()
    q.put((costs[0], 0, oil))
    while not q.empty():
        cost, v, fuel = q.get()
        visited[v] = True
        for u, costed, f in graph[v].get(fuel, []):
            if fuel > f:
                w = values[v] + costed
                if w <= values[u]:
                    values[u] = w
                    q.put((values[u], u, f))
    return values[-1]
def main():
    sys.stdin = open("input", "r")
    sys.stdout = open("output", "w")
    n, m = read_data()
    oil, cons = read_data()
    start, finish = read_data()
    costs = read_data()
    network = [[] for _ in range(n)]
    for line in sys.stdin:
        x, y, len = list(map(int, line.strip().split()))
        x -= 1
        y -= 1
        network[x].append([y, len * cons])
        network[y].append([y, len * cons])
    graph = build_graph(network, n, oil, costs)
    print(dijkstra(graph, costs, oil))

main()