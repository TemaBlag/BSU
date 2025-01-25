#define _USE_MATH_DEFINES
#include <iostream>
#include <cmath>
#include <omp.h>
#include <chrono>

double f(double x) {
    if (x >= 0 && x <= M_PI / 2) {
        return cos(x);
    }
    else if (x > M_PI / 2 && x <= 2) {
        return M_PI / 2 - x;
    }
    return 0;
}

double calc_h(double a, double b, int N) {
    return ((b - a) / N);
}

double Xk(double a, double h, int k) {
    return (a + h * k);
}

double calc_f_(double x) {
    if (x >= 0 && x <= M_PI / 2) {
        return -sin(x);
    }
    else if (x > M_PI / 2 && x <= 2) {
        return -1.0;
    }
    return 0.0;
}

int main(int argc, char* argv[]) {
    /*if (argc != 2) {
        std::cout << "Usage: " << argv[0] << " N" << std::endl;
        return 1;
    }*/

    int N = 1000;
    double a = 0.0;
    double b = 2.0;

    double h = calc_h(a, b, N);
    double max_f_ = -INFINITY;
    double max_f2_ = -INFINITY;
    double f_ = 0;
    double f2_;
    double epsilon = 1e-6;
    auto start_time = omp_get_wtime();
    //omp_set_num_threads(4);
#pragma omp parallel sections num_threads(2)
    {
#pragma omp section
        {
#pragma omp parallel for
            for (int i = 0; i <= N; i++) {
                if (i == N) {
                    f_ = abs((f(Xk(a, h, i)) - f(Xk(a, h, i - 1))) / h);
                    if (max_f_ < f_) {
                        max_f_ = f_;
                    }
                }
                else {
                    f_ = abs((f(Xk(a, h, i + 1)) - f(Xk(a, h, i))) / h);
                    if (max_f_ < f_) {
                        max_f_ = f_;
                    }
                }
                /*if (abs(f_ - 1.0) < epsilon) {
                std::cout << "Thread " << omp_get_thread_num() << ", f' = " << f_ << std::endl;
                }*/
                std::cout << "f' = " << f_ << " fcalc: " << calc_f_(Xk(a, h, i)) << std::endl;
            }
        }

#pragma omp section
        {
            int N2 = 4 * N;
            double h2 = calc_h(a, b, N2);
            f2_ = 0;

#pragma omp parallel for reduction(max:max_f2_)
            for (int i = 0; i <= N2; i++) {
                if (i == N2) {
                    f2_ = abs((f(Xk(a, h2, i)) - f(Xk(a, h2, i - 1))) / h2);
                    if (max_f2_ < f_) {
                        max_f2_ = f_;
                    }
                }
                else {
                    f2_ = abs((f(Xk(a, h2, i + 1)) - f(Xk(a, h2, i))) / h2);
                    if (max_f2_ < f2_) {
                        max_f2_ = f2_;
                    }
                }
                //std::cout << "Thread " << omp_get_thread_num() << ", f2' = " << f2_ << std::endl;
            }
        }
    }
    std::cout << "Max value |f'(x)| on [" << a << ", " << b << "]" << " for N = " << N << " is " << max_f_ << " f2 = " << max_f2_ << std::endl;

    auto time_end = omp_get_wtime();
    std::cout << "Time: " << time_end - start_time << " sec" << std::endl;
    return 0;
}



/*
#include <iostream>
#include <omp.h>
#include <chrono>

double f(double x) {
    return x * x - 2 * x;
}

double Xk(double a, double h, int k) {
    return (a + h * k);
}

double calc_h(double a, double b, int N) {
    return ((b - a) / N);
}

int main(int argc, char* argv[]) {
    /*if (argc != 2) {
        std::cout << "Usage: " << argv[0] << " N" << std::endl;
        return 1;
    }

    int N = 1000000;
    double a = 0.0;
    double b = 1.0;

    double h = calc_h(a, b, N);
    double integral = 0.0;
    auto start_time = omp_get_wtime();
    //omp_set_num_threads(4);
#pragma omp parallel for reduction(+:integral)
    for (int i = 0; i <= N; ++i) {
        integral += f(Xk(a, h, i)) * h;
        //std::cout << "N " << omp_get_thread_num() << std::endl;
    }
    std::cout << "Integral  |f(x)| on [" << a << ", " << b << "] = " << integral << std::endl;

    auto end_time = omp_get_wtime();
    std::cout << "Time: " << end_time - start_time << " sec" << std::endl;
    return 0;
}*/