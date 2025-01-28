#include <iostream>
#include "header.h"
#include <vector>
int main() {
    std::string str = "0";
    int n = 3;
    OutPut(SequenceLength(n));
    std::cout << std::endl;
    ZerosOnesSequence(str, n);
    OutPut(str);
    std::cout << std::endl;
    OutPut(NumberOfOnesBinaryNotation(n));
    std::vector <int> nums = {};
    ZerosOnesSequenceMod2( nums, n);
    std::cout << std::endl;
    OutPut(nums);
    std::cout << std::endl;
    std::vector <int> files = {0,1,1,0};
    OutPut(IsEqual( str, files));
    return 0;
}