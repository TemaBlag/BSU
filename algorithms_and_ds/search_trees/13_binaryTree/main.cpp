#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <map>
#include <set>

std::ifstream input("/Users/user/Documents/tst.in.txt");
std::ofstream output("/Users/user/Documents/tst.out.txt");

struct Node {
    long long value;
    long long height;
    Node* left;
    Node* right;
    Node* min_val_h;
    Node* min_val_h_1;
    long long msl;

    Node(long long val) : value(val), height(0), left(nullptr), right(nullptr), min_val_h(nullptr), min_val_h_1(nullptr), msl(0){}
};

bool compareByValue(Node* a, Node* b) {
    return a->value < b->value;
}

class Tree {
public:
    Node* root;
    std::map<long long, std::vector<Node*>> max_msl;

    Tree() : root(nullptr) {}

    Node* add(long long value, Node* node) {
        if (node == nullptr) {
            return new Node(value);
        }

        if (value < node->value) {
            node->left = add(value, node->left);
        } else {
            node->right = add(value, node->right);
        }

        return node;
    }

    void heights(Node* node) {
        if (node != nullptr) {
            heights(node->left);
            heights(node->right);

            if (node->left && node->right) {
                node->height = std::max(node->left->height, node->right->height) + 1;
                node->msl = node->right->height + node->left->height + 1;
            } else if (node->right) {
                node->height = node->right->height + 1;
                node->msl = node->right->height + 1;
            } else if (node->left) {
                node->height = node->left->height + 1;
                node->msl = node->left->height + 1;
            }
            max_msl[node->msl].push_back(node);
        }
    }

    void calHeights(Node* v) {
        if (v != nullptr) {
            calHeights(v->left);
            calHeights(v->right);
            if (v->height == 0) {
                v->min_val_h = v;
            }
            else if (v->height == 1) {
                v->min_val_h_1 = v;
                if (v->left != nullptr){
                    v->min_val_h = v->left;
                } else{
                    v->min_val_h = v->right;
                }
            }
            else {
                if (v->left) {
                    v->min_val_h = v->left->min_val_h;
                    v->min_val_h_1 = v->left->min_val_h_1;
                    if ((v->right) && (v->left->height < v->right->height)){
                        v->min_val_h = v->right->min_val_h;
                        v->min_val_h_1 = v->right->min_val_h_1;
                    }
                } else {
                    v->min_val_h = v->right->min_val_h;
                    v->min_val_h_1 = v->right->min_val_h_1;
                }
            }
        }
    }

    void nlr(Node* node) {
        if (node != nullptr) {
            output << node->value << "\n";
            nlr(node->left);
            nlr(node->right);
        }
    }

    std::vector<Node*> creteWay(long long x, Node* v,std::vector<Node*> arr) {
        while (x != v->value) {
            if (find(arr.begin(), arr.end(), v) == arr.end()){
                arr.push_back(v);
            }
            if (v->value > x)
                v = v->left;
            else
                v = v->right;
        }
        return arr;
    }

    Node* averageV(std::vector<Node*> p) {
        Node* v = p[0];
        long long l = p[1]->value;
        long long r = p[2]->value;
        std::vector<Node*> k = {p[1], p[2]};
        k = creteWay(l, v, k);
        k = creteWay(r, v, k);
        std::sort(k.begin(), k.end(), compareByValue);
        return k[k.size() >> 1];
    }

    Node* search(Node* r, Node* v) {
        if (v == root)
            return root;
        if ((r->left && r->left == v) || (r->right && r->right == v))
            return r;
        if (r->value > v->value)
            return search(r->left, v);
        else
            return search(r->right, v);
    }

    void delV(Node* v) {
        if (v == root && root->left == nullptr)
            root = root->right;
        Node* f = search(root, v);
        Node *r;
        long long prev = v->value;
        if (v->right) {
            r = v->right;
            if (v->left) {
                Node *l = v->left;
                Node *x = v->right;
                bool flag = false;
                Node *prevl = r;
                while (r->left) {
                    flag = true;
                    prevl = r;
                    r = r->left;
                }
                if (flag) {
                    prevl->left = r->right;
                    r->right = x;
                }
                r->left = l;
            } else
                r = v->right;
        } else if (v->left)
            r = v->left;
        else {
            if (f->value < prev)
                f->right = nullptr;
            else
                f->left = nullptr;
            return;
        }
        if (v == root)
            root = r;
        if (f->value < prev)
            f->right = r;
        else
            f->left = r;
    }
};

void insert(std::vector<Node*> p, long long& minSum, std::vector<std::vector<Node*>> &verts) {
    long long val = p[1]->value + p[2]->value;

    if (val < minSum) {
        minSum = val;
        verts = {p};
    } else if (val == minSum) {
        if (p[0]->value == verts[0][0]->value) {
            verts.push_back(p);
        } else if (p[0]->value < verts[0][0]->value) {
            verts = {p};
        }
    }
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    Tree t;
    long long value;
    while (input >> value) {
        t.root = t.add(value, t.root);
    }

    if (t.root->right == nullptr && t.root->left == nullptr) {
        output << t.root->value << std::endl;
        return 0;
    }

    t.heights(t.root);
    t.calHeights(t.root);
    long long x = 0;
    for (const auto& entry : t.max_msl) {
        x = std::max(x, entry.first);
    }
    if (!(x % 2)) {
        std::vector<std::vector<Node *>> verts;
        long long minSum = std::numeric_limits<long long>::max();
        for (auto v: t.max_msl[x]) {
            if (v->left && v->left->height == 0) {
                v->left->min_val_h_1 = v;
            }
            if (v->right && v->right->height == 0) {
                v->right->min_val_h_1 = v;
            }
            if (v->left && v->right) {
                if (v->left->min_val_h->value + v->right->min_val_h_1->value ==
                    v->left->min_val_h_1->value + v->right->min_val_h->value) {
                    if (v->left->min_val_h->value + v->right->min_val_h_1->value <= minSum) {
                        insert({v, v->left->min_val_h, v->right->min_val_h_1}, minSum, verts);
                        insert({v, v->left->min_val_h_1, v->right->min_val_h}, minSum, verts);
                    }
                } else if (v->left->min_val_h->value + v->right->min_val_h_1->value <
                           v->left->min_val_h_1->value + v->right->min_val_h->value) {
                    if (v->left->min_val_h->value + v->right->min_val_h_1->value <= minSum) {
                        insert({v, v->left->min_val_h, v->right->min_val_h_1}, minSum, verts);
                    }
                } else {
                    if (v->left->min_val_h_1->value + v->right->min_val_h->value <= minSum) {
                        insert({v, v->left->min_val_h_1, v->right->min_val_h}, minSum, verts);
                    }
                }
            } else if (v->right) {
                if (v->value + v->right->min_val_h->value <= minSum) {
                    insert({v, v, v->right->min_val_h}, minSum, verts);
                }
            } else {
                if (v->left->min_val_h->value + v->value <= minSum) {
                    insert({v, v->left->min_val_h, v}, minSum, verts);
                }
            }
        }
        std::vector<Node *> del_v;
        for (auto v: verts) {
            del_v.push_back(t.averageV(v));
        }
        if (del_v.size() == 1 || del_v.size() != std::set<Node *>(del_v.begin(), del_v.end()).size()) {
            t.delV(del_v[0]);
        }
    }
    t.nlr(t.root);
    return 0;
}