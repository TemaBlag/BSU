#include <iostream>
#include <fstream>

std::ifstream inputFile("C:/Users/Artem/Desktop/input.txt");
std::ofstream outputFile("C:/Users/Artem/Desktop/output.txt");

class Node {
public:
    int key;
    Node* left;
    Node* right;
    explicit Node(int key_) : left(nullptr), right(nullptr), key(key_){}
};

class Tree {
public:
    Node* root;
    Tree(): root(nullptr){}

    ~Tree() {
        DestroyNode(root);
    }

    void insert(int x) {
        Node** cur = &root;
        while (*cur) {
            Node& node = **cur;
            if (x == node.key)
                return;
            else
                cur = x > node.key ? &node.right : &node.left;
        }
        *cur = new Node(x);
    }

    void remove(int x) {
        Node* cur = root;
        if (cur->key == x){
            Node* parent = cur;
            if (cur->left == nullptr) {
                root = cur->right;
                return;
            }
            if (cur->right != nullptr) {
                cur = cur->right;
                if (cur->left == nullptr) {
                    cur->left = root->left;
                    root = cur;
                    return;
                }
                while (cur->left != nullptr) {
                    parent = cur;
                    cur = cur->left;
                }
                parent->left = cur->right;
                cur->left = root->left;
                cur->right = root->right;
                root = cur;
            }
            else{
                root = root->left;
            }
            return;
        }
        Node* parent;
        while (cur) {
            Node& node = *cur;
            if (x == node.key) {
                if (node.left == nullptr && node.right == nullptr) {
                    if (parent->key > x)
                        parent->left = nullptr;
                    else parent->right = nullptr;
                } else if (node.left != nullptr && node.right == nullptr) {
                    Node tmp_node = *(cur->left);
                    if (parent->key > tmp_node.key)
                        parent->left = cur->left;
                    else
                        parent->right = cur->left;
                }
                else if (node.left == nullptr && node.right != nullptr){
                    Node tmp_node = *(cur->right);
                    if (parent->key > tmp_node.key)
                        parent->left = cur->right;
                    else
                        parent->right = cur->right;
                }
                else {
                    Node* tmp = cur;
                    Node* new_parent = cur;
                    cur = cur->right;
                    if (cur->left == nullptr){
                        cur->left = tmp->left;
                    }
                    else {
                        while (cur->left != nullptr) {
                            new_parent = cur;
                            cur = cur->left;
                        }
                        new_parent->left = cur->right;
                        cur->left = tmp->left;
                        cur->right = tmp->right;
                    }
                    if (parent->key > cur->key)
                        parent->left = cur;
                    else
                        parent->right = cur;
                }
                return;
            }
            else {
                parent = cur;
                cur = x > node.key ? node.right : node.left;
            }
        }
    }

    void nlr(Node* node) {
        if (node != nullptr){
            outputFile << node->key << "\n";
            nlr(node->left);
            nlr(node->right);
        }

    }
private:
    static void DestroyNode(Node* node) {
        if (node) {
            DestroyNode(node->left);
            DestroyNode(node->right);
            delete node;
        }
    }
};

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    Tree tree = Tree();
    int del_v;
    inputFile >> del_v;

    int n;
    while (inputFile >> n) {
        tree.insert(n);
    }
    tree.remove(del_v);
    tree.nlr(tree.root);
    return 0;
}
