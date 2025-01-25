#include <iostream>
#include <fstream>
#include <mpi.h>
#include <cstdio>
#include <ctime>
#include <iomanip>
#include <random>

using namespace std;

constexpr auto ROOT = 0;
constexpr auto LINE_LENGTH = 80;
constexpr int N = 2000000;

using namespace std;
ifstream fin("input.txt");
ofstream fout("output.txt");
//ofstream fout0("input.txt");


int* merge(int* l_buff, int* r_buff, int l_size, int r_size) {
	int width = l_size + r_size;
	int* target = new int[width];

	int l_cur = 0, r_cur = 0;

	for (int i = 0; i < width; i++) {
		if (l_cur < l_size && r_cur < r_size)
			target[i] = (l_buff[l_cur] < r_buff[r_cur]) ? l_buff[l_cur++] : r_buff[r_cur++];
		else if (l_cur < l_size)
			target[i] = l_buff[l_cur++];
		else
			target[i] = r_buff[r_cur++];
	}

	return target;
}

int* merge_sort(int* data, int left, int right) {
	if (left == right) {
		int* temp = new int[1];
		temp[0] = data[left];
		return temp;
	}

	int middle = (left + right) / 2;

	int* l_buff = merge_sort(data, left, middle);
	int* r_buff = merge_sort(data, middle + 1, right);

	return merge(l_buff, r_buff, middle - left + 1, right - middle);
}

double realtime() {
	return double(clock()) / CLOCKS_PER_SEC;
}

int main(int argc, char** argv) {
	int* a = new int[1];
	int size, rank, name_len;
	char processor_name[MPI_MAX_PROCESSOR_NAME];

	/*random_device rd;
	mt19937 gen(rd());
	uniform_int_distribution<> dis(-N, N);

	for (size_t t = 0; t < N; ++t) {
		fout0 << dis(gen) << ' ';
	}
	fout0.close();*/


	int* v = new int[N];
	for (int i = 0; i < N; i++) {
		int x;
		fin >> x;
		v[i] = x;
	}

	double start_t, end_t;
	start_t = realtime();

	MPI_Init(&argc, &argv);   // Initialize the MPI environment
	MPI_Comm_size(MPI_COMM_WORLD, &size);   // Get the number of processes
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);   // Get the rank of the process

	int step = N / size;
	int master_count = step + N % size;

	MPI_Barrier(MPI_COMM_WORLD);
	if (rank == ROOT) {    // master: send params to all process

		int start = 0;
		start += master_count;
		for (int i = 1; i < size; i++) {
			MPI_Send(v + start, step, MPI_INT, i, 77, MPI_COMM_WORLD);
			start += step;
		}
	}
	else if (step != 0) {
		a = new int[step];
		MPI_Recv(a, step, MPI_INT, ROOT, 77, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
	}

	MPI_Barrier(MPI_COMM_WORLD);

	if (rank == ROOT)    // master: receive params from all process
	{
		int* sorted = merge_sort(v, 0, master_count - 1);

		int length = master_count;

		int* temp = new int[step];
		for (int i = 1; i < size; i++) {
			MPI_Recv(temp, step, MPI_INT, i, 77, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

			sorted = merge(sorted, temp, length, step);
			length += step;
		}

		end_t = realtime();

		for (int i = 0; i < length; i++)
			fout << sorted[i] << " ";


		printf("\t Itogovoe vremia raboty` MPI \t%.4lf s\n", end_t - start_t);
	}
	else {
		if (step != 0) {
			a = merge_sort(a, 0, step - 1);
		}
		MPI_Send(a, step, MPI_INT, ROOT, 77, MPI_COMM_WORLD);
	}

	MPI_Finalize(); // Finalize the MPI environment.



	delete[] a;
	delete[] v;
	return 0;
}

