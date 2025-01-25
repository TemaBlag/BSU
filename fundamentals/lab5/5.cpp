#include <iostream>

std::string decimalToHex(int decimalNumber) {
    std::string hexNumber = "";
    while (decimalNumber > 0) {
        int remainder = decimalNumber % 16;
        if (remainder < 10) {
            hexNumber = char(remainder + '0') + hexNumber;
        }
        else {
            hexNumber = char(remainder - 10 + 'A') + hexNumber;
        }
        decimalNumber /= 16;
    }
    return hexNumber;
}


int main()
{
    const int ROWS = 5;
    const int COLS = 5;
    int array[ROWS][COLS] = {
        {1, 2, 3, 49, 45},
        {6, 74, 8, 9, 105},
        {11, 12, 13, 146, 156},
        {156, 171, 198, 19, 20},
        {21, 242, 23, 24, 25}
    };
    for (int i = 0; i < ROWS; ++i) {
        for (int j = 0; j < COLS; ++j) {
            std::cout << "\033[31m";
            std::cout << array[i][j] << " ";
        }
        std::cout << std::endl;
    }
    for (int i = 0; i < ROWS; ++i) {
        for (int j = 0; j < COLS; ++j) {
            std::cout << "\033[33m";
            std::cout << decimalToHex(array[i][j]) << " ";
        }
        std::cout << std::endl;
    }
}

