#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <cmath>
#include <stdlib.h>


int SequenceLength(int n) {   // нахождение длины последовательности
    if (n == 0) {
        return 0;
    } else {
        return pow(2, n - 1);
    }

}


char inversation(char a) { // замена
    std::string str;
    str += a;
        if (a == '0') {
            return '1';
        }
        else {
            return '0';
        }
}


bool inversation(bool a) { 
        if (!a) {
            return true;
        }
        else {
            return false;
        }
}


void ZerosOnesSequence(std::string& str, int n) { // вычисление длины последовательности
            n -= str.size();
            while (n > 0) {
                int count = str.size(); 
                n -= count;
                for (int j = 0; j < str.size() && count > 0; j++) {
                    str += inversation(str[j]);// увеличиваем строку
                    count--;
                }
            }
}




int NumberOfOnesBinaryNotation(int n) {// количество цифр 1 в двоичном представлении
    int count = 0;
    while (n > 0) {
        if (n % 2 == 1) {
            count++;
        }
        n /= 2;
    }
    return count;
}


int DigitMod2(int a){  // проверка делиммости на 2
    if (a % 2 == 0) {    
        return 0;
    }
    else {
        return 1;
    }
}


void ZerosOnesSequenceMod2(std::vector<int>& nums, int v) {// построение последовательности
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