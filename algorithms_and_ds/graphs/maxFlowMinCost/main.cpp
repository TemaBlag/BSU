#include <iostream>
#include <vector>

class Edge {
public:
    long long source;
    long long target;
    long long capacity;
    long long flow;

    Edge(long long v, long long u, long long c, long long f) : source(v), target(u), capacity(c), flow(f) {}
};

long long d[200000], ptr[200000], q[200000];
std::vector<std::vector<long long>> network;
std::vector<Edge> flow_edges;
long long n, m;

bool bfs() {
    d.assign(n, 0);
    memset (d, -1, n * sizeof d[0]);
    int qh=0, qt=0;
    q[qt++] = 0;
    d[0] = 1;
    while (qh < qt && d[n - 1] == 0) {
        int v = q[qh++];
        for (long long e : network[v]) {
            Edge& edge = flow_edges[e];
            if (d[edge.target] == 0 && edge.flow < edge.capacity){
                q[qt++] = edge.target;
                d[edge.target] = d[v] + 1;
            }
        }
    }
    return d[network.size() - 1];
}

long long dfs(long long v, long long flow) {
    if (flow == 0)
        return 0;
    if (v == n - 1)
        return flow;
    for (long long e : network[v]) {
        Edge& edge = flow_edges[e];
        if (d[edge.source] + 1 == d[edge.target]) {
            if (int flow_max = dfs(edge.target, std::min(flow, edge.capacity - edge.flow))){
                edge.flow += flow_max;
                if (e % 2)
                    flow_edges[e - 1].flow -= flow_max;
                else
                    flow_edges[e + 1].capacity += flow_max;
                return flow_max;
            }
        }
    }
    return 0;
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cin >> n >> m;
    network.resize(n);
    for (long long i = 0; i < m; i++) {
        long long v, u, c;
        std::cin >> v >> u >> c;
        v -= 1;
        u -= 1;
        network[v].push_back(flow_edges.size());
        flow_edges.emplace_back(v, u, c, 0);
        network[u].push_back(flow_edges.size());
        flow_edges.emplace_back(u, v, 0, 0);
    }
    long long flow = 0;
    while (bfs()) {
        memset (ptr, 0, n * sizeof ptr[0]);
        while (int cur_flow = dfs(0, LLONG_MAX))
            flow += cur_flow;
    }
    std::cout << flow << std::endl;
    return 0;
}