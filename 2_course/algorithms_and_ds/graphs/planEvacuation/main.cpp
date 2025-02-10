#include <vector>
#include <algorithm>
#include <climits>
#include <fstream>

using namespace std;


struct Edge {
    int source;
    int target;
    long long cap;
    long long flow;
    long long dst;

    Edge(int v, int u, long long c, long long f, long long d) : source(v), target(u), cap(c), flow(f), dst(d) {}
};

std::ifstream reader;
std::ofstream writer;

long long get_distance(long long x1, long long x2, long long y1, long long y2) {
    return abs(x1 - x2) + abs(y1 - y2) + 1;
}

bool check(int n, vector<Edge>& edges, vector<vector<long long>>& g) {
    for (;;) {
        bool found = false;
        vector<long long> d (n, 0);
        vector<long long> par (n, -1);
        for (int i=0; i<n; ++i)
            if (d[i] == 0) {
                vector<long long> q, nq;
                q.push_back (i);
                for (int it=0; it<n && q.size(); ++it) {
                    nq.clear();
                    sort (q.begin(), q.end());
                    q.erase (unique (q.begin(), q.end()), q.end());
                    for (size_t j=0; j<q.size(); ++j) {
                        long long v = q[j];
                        for (size_t k=0; k<g[v].size(); ++k) {
                            long long id = g[v][k];
                            if (edges[id].flow < edges[id].cap)
                                if (d[v] + edges[id].dst < d[edges[id].target]) {
                                    d[edges[id].target] = d[v] + edges[id].dst;
                                    par[edges[id].target] = v;
                                    nq.push_back (edges[id].target);
                                }
                        }
                    }
                    swap (q, nq);
                }
                if (!q.empty()) {
                    long long leaf = q[0];
                    vector<long long> path;
                    for (long long v=leaf; v!=-1; v=par[v])
                        if (find (path.begin(), path.end(), v) == path.end())
                            path.push_back (v);
                        else {
                            path.erase (path.begin(), find (path.begin(), path.end(), v));
                            break;
                        }
                    for (size_t j=0; j<path.size(); ++j) {
                        long long to = path[j],  v = path[(j+1)%path.size()];
                        for (size_t k=0; k<g[v].size(); ++k)
                            if (edges[ g[v][k] ].target == to) {
                                long long id = g[v][k];
                                edges[id].flow += 1;
                                edges[id^1].flow -= 1;
                            }
                    }
                    return false;
                }
            }

        if (!found)  break;
    }
    return true;
}


void build_network(int n, int m, vector<Edge>& edges, vector<vector<long long>>& net) {
    int k = n + m + 1;
    vector<vector<long long>> houses;
    for (int i = 1; i <= n; i++) {
        long long x, y, cap;
        reader >> x >> y >> cap;
        houses.push_back({i, x, y, cap});
        net[0].push_back(edges.size());
        edges.emplace_back(0, i, cap, 0, 0);
        net[i].push_back(edges.size());
        edges.emplace_back(i, 0, 0, 0, 0);
    }
    for (long long i = n + 1; i < k; i++) {
        long long x, y, cap;
        reader >> x >> y >> cap;
        net[i].push_back(edges.size());
        edges.emplace_back(i, k, cap, 0, 0);
        net[k].push_back(edges.size());
        edges.emplace_back(k, i, 0, 0, 0);
        for (const auto& house : houses) {
            long long dst = get_distance(house[1], x, house[2], y);
            net[house[0]].push_back(edges.size());
            edges.emplace_back(house[0], i, LONG_MAX, 0, dst);
            net[i].push_back(edges.size());
            edges.emplace_back(i, house[0], 0, 0, -dst);
        }
    }
    for (int i = 0; i < n; i++) {
        vector<long long> distribution(m);
        for (long long& val : distribution) {
            reader >> val;
        }
        for (long long ind = 0; ind < distribution.size(); ind++) {
            long long val = distribution[ind];
            if (val) {
                edges[net[n + ind + 1][i + 1]].cap = val;
                edges[net[n + ind + 1][0]].flow += val;
                edges[net[k][ind]].cap += val;
            }
        }
    }
}

void print_res(int n, int m, const vector<vector<long long>>& net, const vector<Edge>& edges) {
    for (int i = 1; i <= n; i++) {
        vector<long long> arr;
        for (int j = 1; j <= m; j++) {
            arr.push_back(edges[net[n + j][i]].cap - edges[net[n + j][i]].flow);
        }
        for (int j = 0; j < arr.size(); j++) {
            writer << arr[j];
            if (j != arr.size() - 1) {
                writer << " ";
            }
        }
        writer << endl;
    }
}

int main() {
    int n, m;
    reader = ifstream("evacuate.in");
    writer = ofstream("evacuate.out");
    reader >> n >> m;
    vector<Edge> edges;
    vector<vector<long long>> net(n+m+2);

    build_network(n, m, edges, net);
    if (check(n + m + 2, edges, net)) {
        writer << "OPTIMAL" << endl;
    } else {
        writer << "SUBOPTIMAL" << endl;
        print_res(n, m, net, edges);
    }
    writer.close();
    reader.close();
    return 0;
}
