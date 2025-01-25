#include <string>
#include <iostream>
#include <vector>
#include <cmath>
#include "header.h"


void time()
{
    time_t now = time(0); // current date/time based on current system
    char *dt = ctime(&now);     // convert now to string form
    std::cout << "The date is: " << dt;
}

std::string stringInput()
{
    std::string str;
    std::cout << "Input expression:";
    std::cin >> str;
    return str;
}

std::vector<std::string> divide(std::string n)
{
    n += " ";
    std::vector<std::string> mas;
    std::string sigh = "", fun = "";
    for (auto i: n) {
        if (('0' <= i && i <= '9') || i == '.') {
            sigh += i;
        }
        else if (((i >= 'a' && i <= 'z') && (i != 'r') && (i != 'd')) || (i == 'R')) {
            if (!sigh.empty()) {
                mas.push_back(sigh);
                sigh = "";
            };
            fun += i;
        }
        else {
            if (!fun.empty()) {
                mas.push_back(fun);
                fun = "";
            };
            if (!sigh.empty()) {
                mas.push_back(sigh);
                sigh = "";
            };
            if (i != ' ') {
                mas.emplace_back(std::string(1, i));
            };
        }
    }
    return mas;
}

std::string bin(std::string number)
{
    std::string number1 = "";
    int num = std::stoi(number);
    while (num > 0) {
        number1 += std::to_string(num % 2);
        num /= 2;
    }
    for (int i = 0; i < number1.size() / 2; i++) {
        std::swap(number1[i], number1[number1.size() - i - 1]);
    }
    return number1;
}

std::string binList(std::string num)
{
    std::string num1 = "";
    int length = pow(10, num.size());
    int num2 = std::stoi(num);
    for (int i = 0; i < 5; i++) {
        num2 *= 2;
        if (num2 >= length) {
            num2 -= length;
            num1 += "1";
        }
        else {
            num1 += "0";
        }
    }
    return num1;
}

std::string binary(std::string n)
{
    std::string n1 = "";
    std::string n2 = "";
    int counter = 0;
    for (auto i: n) {
        if (i != '.' && counter == 0) {
            n1 += i;
        }
        else if (i == '.') {
            counter++;
        }
        else {
            n2 += i;
        }
    };
    if (n2.empty()) {
        return bin(n1);
    }
    else {
        return bin(n1) + "." + binList(n2);
    }

}

double sum(double a, double b)
{
    return a + b;
}

double residual(double a, double b)
{
    return a - b;
}

double mod(double a, double b)
{
    double res = a / b;
    int res1 = static_cast<int>(res);
    return a - b * res1;
}

double multiply(double a, double b)
{
    return a * b;
}

double division(double a, double b)
{
    return a / b;
}

double numPow(double a, double x)
{

    return pow(a, x);
}

double root(double a, double x)
{
    return pow(x, 1 / a);
}

double sinus(double x)
{
    long double e = (1e-20);
    long double ch = x;
    long double zn = 1;
    int counter = 0;
    double res = 0;
    double dr = 1;
    while (fabs(dr) > e) {
        dr = ch / zn;
        if (counter % 2) {
            res -= dr;
        }
        else {
            res += dr;
        }
        counter++;
        ch = ch * x * x;
        zn = zn * (counter * 2) * (counter * 2 + 1);
    }
    return res;
}

double cos(double x)
{
    long double e = (1e-20);
    long double ch = 1;
    long double zn = 1;
    int counter = 0;
    double res = 0;
    double dr = 1;
    while (dr > e) {
        dr = ch / zn;
        if (counter % 2) {
            res -= dr;
        }
        else {
            res += dr;
        }
        counter++;
        ch = ch * x * x;
        zn = zn * (counter * 2) * (counter * 2 - 1);
    }
    return res;
}

double tangent(double x)
{
    return sinus(x) / cos(x);
}

double cotangent(double x)
{
    return (cos(x) / sinus(x));
}

double sinusDeg(double x)
{
    x *= 0.0174533;
    return sinus(x);
}

double cosDeg(double x)
{
    x *= 0.0174533;
    return cos(x);
}

double tangentDeg(double x)
{
    x *= 0.0174533;
    return tangent(x);
}

double cotangentDeg(double x)
{
    x *= 0.0174533;
    return cotangent(x);
}

void permutation(std::vector<std::string> &values, int &index, double res)
{
    values[index - 1] = std::to_string(res);
    values.erase(values.begin() + index);
    values.erase(values.begin() + index);
    res = 0;
    index -= 2;
}

int trig(std::string word)
{
    double res = 0;
    if (word == "sin") return 0;
    if (word == "cos") return 1;
    if (word == "tg") return 2;
    if (word == "ctg") return 3;

}

long double hooks(std::vector<std::string> &num)
{
    double res = 0;
    std::vector<int> n;
    for (int i = 1; i < num.size(); i += 2) {
        if (num[i] == "r") {
            switch (trig(num[i - 1])) {
                case 0:
                    res = sinus(stod(num[i + 1]));
                    break;
                case 1:
                    res = cos(stod(num[i + 1]));
                    break;
                case 2:
                    res = tangent(stod(num[i + 1]));
                    break;
                case 3:
                    res = cotangent(stod(num[i + 1]));
                    break;
            }
            permutation(num, i, res);
        }
        else if (num[i] == "d") {
            switch (trig(num[i - 1])) {
                case 0:
                    res = sinusDeg(stod(num[i + 1]));
                    break;
                case 1:
                    res = cosDeg(stod(num[i + 1]));
                    break;
                case 2:
                    res = tangentDeg(stod(num[i + 1]));
                    break;
                case 3:
                    res = cotangentDeg(stod(num[i + 1]));
                    break;
            }
            permutation(num, i, res);
        }
        else if (num[i] == "^") {
            res = numPow(stod(num[i - 1]), stod(num[i + 1]));
            permutation(num, i, res);
        }
        else if (num[i] == "Root") {
            res = root(stod(num[i - 1]), stod(num[i + 1]));
            permutation(num, i, res);
        }
    }
    for (int i = 1; i < num.size(); i += 2) {
        if (num[i] == "*") {
            res = multiply(stod(num[i - 1]), stod(num[i + 1]));
            permutation(num, i, res);
        }
        else if (num[i] == "/") {
            res = division(stod(num[i - 1]), stod(num[i + 1]));
            permutation(num, i, res);
        }
        else if (num[i] == "%") {
            res = mod(stod(num[i - 1]), stod(num[i + 1]));
            permutation(num, i, res);
        }

    }
    for (int i = 1; i < num.size(); i += 2) {
        if (num[i] == "+") {
            res = sum(stod(num[i - 1]), stod(num[i + 1]));
            permutation(num, i, res);
        }
        else if (num[i] == "-") {
            res = residual(stod(num[i - 1]), stod(num[i + 1]));
            permutation(num, i, res);
        }
        else if (num[i] == "sm") {
            std::string str = {};
            str = binary(num[i + 1]);
            std::cout << str;
            permutation(num, i, 1);
            num[i - 1] = str;

        }
    }
    long double result = stod(num[0]);
    return result;
}

void clearVector(std::vector<std::string> &num, double res, int index1, int index2)
{
    num[index1 - 1] = std::to_string(res);
    num.erase(num.begin() + index1, num.begin() + index2);
}

void ScienceCalculator::sciencecalculate()
{
    long double res = 0;
    while (true) {
        expr = stringInput();
        if (expr == "exit" || expr == "Exit") {
            break;
        }
        std::vector num = divide(expr);
        std::vector<int> open;
        std::vector<int> close;
        for (int i = 0; i < num.size(); i++) {
            if (num[i] == "(") {
                open.push_back(i + 1);
            }
            else if (num[i] == ")") {
                close.push_back(i - 1);
            }
        }
        for (int j = open.size() - 1; j >= 0; j--) {
            int flag = 0;
            std::vector<std::string> count = {};
            for (int k = 0; k < close.size(); k++) {
                if (k + j == close.size() - 1) {
                    for (int f = open[j]; f <= close[k]; f++) {
                        flag++;
                        count.push_back(num[f]);
                    }
                    close[k + 1] -= flag;
                }
            }
            clearVector(num, hooks(count), open[j], close[close.size() - j - 1] + 2);
        }
        res = hooks(num);
        std::cout << "Result: " << res << std::endl;
    }
}

bool notClear()
{
    bool isClear = false;
    if (std::cin.fail()) {
        std::cin.clear();
        std::cin.ignore(32767, '\n');
        isClear = true;
    }
    return isClear;
}

long double value()
{
    std::cout << "Input number: ";
    long double val;
    std::cin >> val;
    if (notClear()) {
        val = value();
    }
    return val;
}

void StandardCalculator::sum(long double val)
{
    num = num + val;
    std::cout << "Result:" << num << std::endl;
};

void StandardCalculator::residual(long double val)
{
    num = num - val;
    std::cout << "Result:" << num << std::endl;
}

void StandardCalculator::multiply(long double val)
{
    num = num * val;
    std::cout << "Result:" << num << std::endl;
}

void StandardCalculator::division(long double val)
{
    num = num / val;
    std::cout << "Result:" << num << std::endl;
}

void StandardCalculator::sqrt()
{
    long double root = num / 2;
    double eps = (1e-20);
    int iter = 0;
    while (root - num / root > eps) {
        iter++;
        root = 0.5 * (root + num / root);
    }
    num = root;
    std::cout << "Result:" << num << std::endl;
}

void StandardCalculator::pow2()
{
    num = num * num;
    std::cout << "Result:" << num << std::endl;
}

void StandardCalculator::reciprocalnumber()
{
    num = 1 / num;
    std::cout << "Result:" << num << std::endl;
}

void StandardCalculator::percent(long double val)
{
    num = num * val / 100;
    std::cout << "Result:" << num << std::endl;
}

void StandardCalculator::clean()
{
    num = value();
}

void StandardCalculator::standardcalculate()
{
    long double res = 0;
    while (true) {
        std::cout << "Operation: ";
        std::cin >> operation;
        if (operation == 'e' || operation == 'E') {
            break;
        }
        switch (operation) {
            case '+':
                sum(value());
                break;
            case '-':
                residual(value());
                break;
            case '*':
                multiply(value());
                break;
            case '/':
                division(value());
                break;
            case '%':
                percent(value());
                break;
            case '^':
                pow2();
                break;
            case 's':
                sqrt();
                break;
            case '~':
                reciprocalnumber();
                break;
            case 'C':
                clean();
                break;

        }
    }
}

void ConsoleWork::Hello()
{
    std::cout << "Hello, welcome to calculator! " << std::endl <<
              "Choose type of calculator:" << std::endl <<
              "1.Standard," << std::endl <<
              "2.Programmer," << std::endl <<
              "3.Scientific," << std::endl <<
              "4.Data Calculator;" << std::endl;
}

void ConsoleWork::StandardFuctions()
{
    std::cout << "Input exit to stop" << std::endl
              << "Functions: +,-,*,/,%,sqrt,^,~(1/x),C" << std::endl;
    StandardCalculator run(value());
    run.standardcalculate();
}
void ConsoleWork::ProgrammerFuctions()
{
    std::cout << "Input exit to stop" << std::endl <<
              "Functions: " << std::endl <<
              "Sum: a + b" << std::endl <<
              "Residual: a-b;" << std::endl <<
              "Multiply: a*b;" << std::endl <<
              "Division: a/b;" << std::endl <<
              "and a AND b" << std::endl <<
              "not NOT a" << std::endl <<
              "or a OR b" << std::endl <<
              "nand a NAND b" << std::endl <<
              "nor a NOR b" << std::endl <<
              "xor a XOR b" << std::endl;
    std::cout << "Input notation" << std::endl;
    int n;
    std::cin >> n;
    ProgrammerCalculator run(n);
    run.Notation();
}

void ConsoleWork::ScientificFuctions()
{
    std::cout << "Input exit to stop" << std::endl <<
              "Functions: " << std::endl <<
              "Sum: a+b;" << std::endl <<
              "Residual: a-b;" << std::endl <<
              "Multiply: a*b;" << std::endl <<
              "Division: a/b;" << std::endl <<
              "Pow: a^b;" << std::endl <<
              "Root: aRootb;" << std::endl <<
              "Mod: amodb;" << std::endl <<
              "Trigonometry: r for radian and d for degree" << std::endl <<
              "Sinus: sinra or sinda;" << std::endl <<
              "Cosine: cosra or cosda;" << std::endl <<
              "Tangent: tgra or tgda;" << std::endl <<
              "Cotangent: ctgra or ctgda;" << std::endl;
    ScienceCalculator run;
    run.sciencecalculate();
}
void ConsoleWork::DataFuctions()
{
    std::cout << "Functions:";
}
void ConsoleWork::ChooseCalculator()
{
    int num;
    bool cool = true;
    while (cool) {
        std::cout << "Input number: ";
        std::cin >> num;
        cool = notClear();
    }
    switch (num) {
        case (1):
            StandardFuctions();
            break;
        case (2):
            ProgrammerFuctions();
            break;
        case (3):
            ScientificFuctions();
            break;
        case (4):
            DataFuctions();
            break;
    }
}

void ProgrammerCalculator::Notation()
{
    switch (this->notation) {
        case (2):
            //return this->CalculateBinary;
        case (8):
            //return this->Calculate;
        case (10):
            return this->CalculateDecimal();
        case (16):;
            //return this->CalculateHex;
    }
}

std::string DecimalToBinary(std::string _Number){
    double number = stod(_Number);
    std::vector<int> wholeNumbers;
    std::vector<int> fractionalNumbers;
    int wholePart = number;
    int counter = 0;
    while (wholePart > 0){
        int temp;
        temp = wholePart % 2;
        wholePart /= 2;
        wholeNumbers.push_back(temp);
        counter++;
    }
    std::string newNumber = "";
    for(int i = counter - 1;i > 0;i--){
        newNumber += std::to_string(wholeNumbers[i]);
    }
    _Number = newNumber;
    return _Number;
}



std::string BinaryToOct(std::string _Number){
    std::string number = DecimalToBinary(_Number);

};



void ProgrammerCalculator::CalculateDecimal()
{
    double res;
    this->firstnumber = "0";
    while (true) {
        if(this->firstnumber == "0") {
            std::cout << "Input number" << std::endl;
            std::cin >> this->firstnumber;
            std::cout << "Input operation" << std::endl;
            std::cin >> this->operation;
            switch (operation) {
                case ('+'):
                    std::cout << "Input second number" << std::endl;
                    std::cin >> this->secondnumber;
                    res = std::stod(this->firstnumber) + std::stod(this->secondnumber);
                    this->result = std::to_string(res);
                    std::cout << this->result << std::endl;
                    break;
                case ('-'):
                    std::cout << "Input second number" << std::endl;
                    std::cin >> this->secondnumber;
                    res = std::stod(this->firstnumber) - std::stod(this->secondnumber);
                    this->result = std::to_string(res);
                    std::cout << this->result << std::endl;
                    break;
                case ('*'):
                    std::cout << "Input second number" << std::endl;
                    std::cin >> this->secondnumber;
                    res = std::stod(this->firstnumber) * std::stod(this->secondnumber);
                    this->result = std::to_string(res);
                    std::cout << this->result << std::endl;
                    break;
                case ('/'):
                    std::cout << "Input second number" << std::endl;
                    std::cin >> this->secondnumber;
                    res = std::stod(this->firstnumber) / std::stod(this->secondnumber);
                    this->result = std::to_string(res);
                    std::cout << this->result << std::endl;
                    break;
                //case ('AND'):

            }
        }else{
            std::cout << "Input operation" << std::endl;
            std::cin >> this->operation;
            switch(this->operation) {
                case ('+'):
                    std::cout << "Input second number" << std::endl;
                    std::cin >> this->secondnumber;
                    res = std::stod(this->result) + std::stod(this->secondnumber);
                    this->result = std::to_string(res);
                    std::cout << this->result << std::endl;
                    break;
                case ('-'):
                    std::cout << "Input second number" << std::endl;
                    std::cin >> this->secondnumber;
                    res = std::stod(this->result) - std::stod(this->secondnumber);
                    this->result = std::to_string(res);
                    std::cout << this->result << std::endl;
                    break;
                case ('*'):
                    std::cout << "Input second number" << std::endl;
                    std::cin >> this->secondnumber;
                    res = std::stod(this->result) * std::stod(this->secondnumber);
                    this->result = std::to_string(res);
                    std::cout << this->result << std::endl;
                    break;
                case ('/'):
                    std::cout << "Input second number" << std::endl;
                    std::cin >> this->secondnumber;
                    res = std::stod(this->result) / std::stod(this->secondnumber);
                    this->result = std::to_string(res);
                    std::cout << this->result << std::endl;
                    break;
            }
        }
    }
}