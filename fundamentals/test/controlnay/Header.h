#pragma once
#include <iostream>
#include <string>
#include <vector>

bool TrueData(std::string str); // проверка строки


bool TrueData(std::vector<int> nums); // проверка вектора


bool TrueData(int n);//проверка числа


template<typename T> // вывод аргумента
void OutPut(T arg) {  
    std::cout << arg << " ";
}



template<typename T> // вывод вектора
void OutPut(std::vector <T> arg) {
    for (auto i : arg) {
        std::cout << i << " ";
    }
}



int SequenceLength(int n); // вычисление длины последовательности



char inversation(char a); // инверсация char a


bool inversation(bool a);// перегрузка для bool


void ZerosOnesSequence (std::string& str, int n); // функция генерации последовательности


int NumberOfOnesBinaryNotation(int n); // определение количества 1 в двоичном представлении


int DigitMod2(int a); // остаок от деления на 2


void ZerosOnesSequenceMod2(std::vector<int>& nums, int v); // функция построения последовательности


bool IsEqual(std::string str, std::vector <int> files); //сравнениt двух последовательностей
