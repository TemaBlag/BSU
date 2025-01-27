#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <cmath>
#include <stdlib.h>

bool TrueData(std::string str) { // проверка на символы в строке 1 или 0
    for (auto i : str) {
        if (i < '0' || i > '1') {
            return false;
        }
    }
    return true;
}

bool TrueData(std::vector<int> nums) { // проверка массива на элементы 1 и 0
    for (auto i : nums) {  
        if (i < 0 || i > 1) {
            return false;
        }
    }
    return true;
}

bool TrueData( int n) { // проверка числа на 1 или 0
    if (n < 0) {
        return false;
    }
    return true;
}


int SequenceLength(int n) {   // нахождение длины последовательности
    if (n == 0) {
        return 0;
    } else if (TrueData(n)) { // проверка данных
        return pow(2, n - 1);
    }
    else {
        std::cout << "incorrect data";
        exit(1);
    }
}


char inversation(char a) { // замена 1 на 0 и 0 на 1
    std::string str;
    str += a;
    if (TrueData(str)) {// проверка данных
        if (a == '0') {
            return '1';
        }
        else {
            return '0';
        }
    }
    else {
        std::cout << "incorrect data";
        exit(1);
    }
}


bool inversation(bool a) { // перегрузка для bool значений 
        if (!a) {
            return true;
        }
        else {
            return false;
        }
}


void ZerosOnesSequence(std::string& str, int n) { // вычисление длины последовательности
    if (TrueData(str)) {// проверка данных
        if (TrueData(n)) {// проверка данных
            n -= str.size();
            while (n > 0) {
                int counter = str.size(); // чтобы знать, сколько символов прибавлять
                n -= counter;
                for (int j = 0; j < str.size() && counter > 0; j++) {
                    str += inversation(str[j]);// увеличиваем строку
                    counter--;
                }
            }
        }
        else {
            std::cout << "incorrect data";
            exit(1);
        }
    }
    else {
        std::cout << "incorrect data";
        exit(1);
    }
}




int NumberOfOnesBinaryNotation(int n) {// определение цифр 1 в двоичном представлении
    if (TrueData(n)) {// проверка данных
    int counter = 0;// счётчик
    while (n > 0) {
        if (n % 2 == 1) {
            counter++;
        }
        n /= 2;
    }
    return counter;
    } else {
        std::cout << "incorrect data";
        exit(1);
    }
}



int DigitMod2(int a) {// проверка делиммости на 2
    if (TrueData(a)) {// проверка данных
    if (a % 2 == 0) { // смотр им остаток от деления 
        return 0;
    }
    else {
        return 1;
    }
    }
    else {
        std::cout << "incorrect data";
        exit(1);
    }
}


void ZerosOnesSequenceMod2(std::vector<int>& nums, int v) {// построение последовательности
    if (TrueData(nums)) {// проверка данных
        if (TrueData(nums)) {// проверка данных
    int count = NumberOfOnesBinaryNotation(v); // смотрим на количество 1
    if (DigitMod2(count)) { // смотрим делимость на 2
        while (v > 0) {
            nums.push_back(1);
            v--;
        }
    } else {
        while (v > 0) {
            nums.push_back(0);
            v--;
        }
    }
        }
        else {
            std::cout << "incorrect data";
            exit(1);
        }
    } else {
        std::cout << "incorrect data";
        exit(1);
    }

}

bool IsEqual(std::string str, std::vector <int> files) { //сравнениt двух последовательностей
    if (str.size() != files.size()) { // сравниваем размер
        return false;
    }
    else {
        int x;
        int y;
        for (int i = 0; i < files.size(); i++) {
            x = files[i];
            y = str[i] - '0';
                if (x != y) {  // сравниваем каждый элемент
                    return false;
                }
        }
    }
    return true;
}