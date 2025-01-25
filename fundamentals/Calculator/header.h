#pragma once
#include <string>
#include <iostream>
#include <vector>

template<typename T>
void print(std::vector<T> a)
{
    for (auto i: a)
        std::cout << i << " ";
}
//binary functions
std::string stringInput();
std::string bin(std::string number);
std::string binList(std::string num);
std::string binary(std::string n);
std::vector<std::string> divide(std::string n);
//arithmetical functions
double sum(double a, double b);
double residual(double a, double b);
double multiply(double a, double b);
double division(double a, double b);
double numPow(double a, double x);
double root(double a, double x);
double mod(double a, double b);
//trigonometry function
double sinus(double x);
double sinusDeg(double x);
double cos(double x);
double cosDeg(double x);
double tangent(double x);
double tangentDeg(double x);
double cotangent(double x);
double cotangentDeg(double x);
//Programmer functions
std::string DecimalToBinary(std::string _Number);
std::string BinaryToOct(std::string _Number);
//calculate functions
void clearVector(std::vector<std::string> &num, double res, int index1, int index2);
long double hooks(std::vector<std::string> &num);
bool notClear();

class ScienceCalculator
{
    std::string expr;
public:
    explicit ScienceCalculator()
    {};
    void sciencecalculate();
};

class StandardCalculator
{
    long double num;
    char operation;
public:
    explicit StandardCalculator(long double _num)
        : num(_num)
    {};
    friend long double value();
    void sum(long double val);
    void residual(long double val);
    void multiply(long double val);
    void division(long double val);
    void sqrt();
    void pow2();
    void reciprocalnumber();
    void percent(long double val);
    void clean();
    void standardcalculate();
};

class ProgrammerCalculator
{
    std::string firstnumber, secondnumber, result;
    char operation;
    int notation;
public:
    explicit ProgrammerCalculator(int _notation)
        : notation(_notation)
    {};
    void Notation();
    void CalculateDecimal();

};

class ConsoleWork
{
    std::string user;
public:
    friend long double value();
    void Hello();
    void StandardFuctions();
    void ProgrammerFuctions();
    void ScientificFuctions();
    void DataFuctions();
    void ChooseCalculator();
};

void time();

