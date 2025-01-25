#include <iostream>
#include "fstream"
int p[3000005];

int main() {
    std::ifstream inputFile("/Users/user/Library/Mobile Documents/com~apple~TextEdit/Documents/input.txt");
    std::ofstream outFile("/Users/user/Documents/output.txt");
    int n;
    inputFile >> n;
    bool flag = false;
    int index = 0;
    std::string correct, findString;
    getline(inputFile, correct);
    getline(inputFile, correct);
    getline(inputFile, findString);
    findString += "$" + correct + correct;
    for (int i = 1; i < 3 * n + 1; i++){
        int k = p[i - 1];
        while (findString[i] != findString[k] && k > 0)
            k = p[k - 1];
        p[i] = findString[i] == findString[k] ? k + 1 : k;
        if (p[i] == n){
            flag = true;
            index = i;
            break;
        }
    }
    outFile << (flag ? index - 2 * n : -1);
    return 0;
}
