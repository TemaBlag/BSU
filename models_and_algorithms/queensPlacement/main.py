import numpy as np
import random


def test(matrix):
    length = len(matrix)
    rows, cols, diag1, diag2 = [], [], [], []
    for i in range(length):
        rows.append(sum(matrix[i]))  # sum of rows
    for i in range(length):
        cols.append(np.sum(matrix[i], axis=0))  # sum of columns
    for i in range(length):
        sum_diag = 0
        for j in range(0, i + 1):
            sum_diag += matrix[i - j][j]
            if sum_diag > 1:
                return False
        diag1.append(sum_diag)  # sum of first diagonal
    for i in range(1, length):
        sum_diag = 0
        for j in range(length - 1, i - 1, - 1):
            sum_diag += matrix[i][j]
            if sum_diag > 1:
                return False
        diag1.append(sum_diag)  # sum of first diagonal
    for i in range(length - 1, -1, -1):
        sum_diag = 0
        for j in range(length - i):
            sum_diag += matrix[j][i + j]
        if sum_diag > 1:
            return False
        diag2.append(sum_diag)  # sum of second diagonal
    for i in range(length - 2, -1, -1):
        sum_diag = 0
        for j in range(i + 1):
            sum_diag += matrix[length - 1 - j][i - j]
        if sum_diag > 1:
            return False
        diag2.append(sum_diag)  # sum of second diagonal
    if set(cols) != {1} or set(rows) != {1}:
        return False
    return True


class AlmostSolution:
    def __init__(self, m):
        self.n = m

    def print_arr(self, arr, i, n, m, k):
        arr[k][i] = 1
        arr[k + 1][n - m] = 1
        return m + 1, k + 2

    def print_matrix(self, matrix):
        for row in matrix:
            print("".join(map(str, row)))

    def build_matrix(self, n):
        k = 0
        m = 1
        arr = [[0] * n for _ in range(n)]
        if n & 1:
            for i in range(n // 2, 0, -1):
                m, k = self.print_arr(arr, i, n, m, k)
            arr[n - 1][0] = 1
        else:
            for i in range(n // 2 - 1, -1, -1):
                m, k = self.print_arr(arr, i, n, m, k)
        self.print_matrix(arr)
        return print(test(arr))

    def start(self):
        self.build_matrix(self.n)


# solution = AlmostSolution(int(input()))
# solution.start()


class ProblemCategoryB:
    def __init__(self, m):
        self.n = m

    def solution(self):
        matrix = [[0] * self.n for _ in range(self.n)]
        columns, diagonal1, diagonal2 = set(), set(), set()
        flag = True

        def find_placement(row):
            nonlocal flag
            if flag:
                if row == self.n:
                    for res in matrix:
                        print("".join(map(str, res)))
                    print(test(matrix))
                    flag = False
                    return
                for j in range(self.n):
                    if (j in columns) or ((row + j) in diagonal1) or (row - j in diagonal2):
                        continue
                    columns.add(j)
                    diagonal1.add(row + j)
                    diagonal2.add(row - j)
                    matrix[row][j] = 1
                    find_placement(row + 1)
                    columns.remove(j)
                    diagonal1.remove(row + j)
                    diagonal2.remove(row - j)
                    matrix[row][j] = 0

        find_placement(0)


# task = ProblemCategoryB(int(input()))
# task.solution()


class MinConflict:
    def __init__(self, m):
        self.n = m

    def is_valid(self, assignment):
        for col_i, row_i in enumerate(assignment):
            for col_j, row_j in enumerate(assignment[col_i + 1:], col_i + 1):
                if self.is_conflict(row_i, col_i, row_j, col_j):
                    return False
        return True

    def is_conflict(self, row1, col1, row2, col2):
        return (row1 == row2) or (abs(row1 - row2) == abs(col1 - col2))

    def initialize_queens(self, N):
        r = 1
        queens = []
        for i in range(N):
            queens.append(r)
            r += 2
            if r >= N:
                r = 0
        return queens

    def row_conflicts(self, queens: list, N, col):
        row_confs = [0 for _ in range(N)]
        for col_q, row_q in enumerate(queens):
            if col_q == col:
                continue
            row_confs[row_q] += 1  # add conflict for matching row

            if 0 <= row_q + abs(col_q - col) < N:
                row_confs[row_q + abs(col_q - col)] += 1
            if 0 <= row_q - abs(col_q - col) < N:
                row_confs[row_q - abs(col_q - col)] += 1
        return row_confs

    def build_conflicts(self, queens: list):
        rows = {}
        rdiags = {}
        ldiags = {}
        N = len(queens)
        for col, row in enumerate(queens):
            rows[row] = rows.get(row, -1) + 1
            rdiags[row - col] = rdiags.get(row - col, -1) + 1
            ldiags[row - (N - col)] = ldiags.get(row - (N - col), -1) + 1
        conflicts = []
        for col, row in enumerate(queens):
            conflicts.append(rows.get(row, 0) + rdiags.get(row - col, 0) + ldiags.get(row - (N - col), 0))
        return conflicts

    def update_conflicts(self, queens: list, conflicts: list, col, new_row):
        conflicts[col] = 0
        old_row = queens[col]

        for col2, row2 in enumerate(queens):
            if col == col2:
                continue
            # remove all conflicts with old_row
            if (old_row == row2) or (abs(old_row - row2) == abs(col - col2)):
                conflicts[col2] -= 1
            # add all conflicts with new_row
            if (new_row == row2) or (abs(new_row - row2) == abs(col - col2)):
                conflicts[col2] += 1
                conflicts[col] += 1

    def pick_position(self, arr, N, cond):
        return random.choice([i for i in range(N) if cond(arr[i])])

    def min_conflict(self, N, max_steps=10 ** 6):
        queens = self.initialize_queens(N)
        conflicts = self.build_conflicts(queens)  # O(n)
        for i in range(max_steps):  # O(N)

            if sum(conflicts) == 0:
                return queens, i

            col = self.pick_position(conflicts, N, lambda x: x > 0)  # O(N)
            row_confs = self.row_conflicts(queens, N, col)  # O(N)

            min_conf = min(row_confs)  # O(N)
            new_row = self.pick_position(row_confs, N, lambda x: x == min_conf)  # O(N)
            # update conflicts
            self.update_conflicts(queens, conflicts, col, new_row)  # O(N)
            queens[col] = new_row
        return queens, max_steps

    def start(self):
        queens, steps = self.min_conflict(self.n)
        matrix = [[0] * self.n for i in range(self.n)]
        for k, i in enumerate(queens):
            matrix[i][k] = 1
        #for row in matrix:
        #    print("".join(list(map(str, row))))
        #print(test(matrix))


solution = MinConflict(int(input()))
solution.start()


class ExplicitSolution:
    def __init__(self, m):
        self.n = m

    def method1(self, n, matrix):
        for j in range(1, n // 2 + 1):
            matrix[j - 1][2 * j - 1] = 1
            matrix[n // 2 + j - 1][2 * j - 2] = 1

    def method2(self, n, matrix):
        for j in range(1, n // 2 + 1):
            matrix[j - 1][(2 * (j - 1) + n // 2 - 1) % n] = 1
            matrix[n - j][n - 1 - (2 * (j - 1) + n // 2 - 1) % n] = 1

    def start(self):
        n = self.n
        matrix = [[0] * n for i in range(n)]
        k = n // 6
        if not n & 1:
            if n != 6 * k + 2:
                self.method1(n, matrix)
            else:
                self.method2(n, matrix)
        else:
            if (n - 1) % 6 != 2:
                self.method1(n - 1, matrix)
            else:
                self.method2(n - 1, matrix)
            matrix[n - 1][n - 1] = 1
        matrix = list(map(lambda x: x, matrix))
        for row in matrix:
            print("".join(list(map(str, row))))
        print(test(matrix))


# solution = ExplicitSolution(int(input()))
# solution.start()

class IteratorExplicitSolution:
    def __init__(self, m):
        self.n = m

    def chessMethod1(self, n):
        return lambda i: 2 * i if i <= n / 2 else 2 * (i - n // 2) - 1

    def chessMethod2(self, n):
        return lambda i: 1 + (2 * i + n // 2 - 3) % n if i <= n / 2 else n - (2 * (n + 1 - i) + n // 2 - 3) % n

    def finalChess(self):
        n = self.n
        if n % 2 == 0 and (n % 6 != 2):
            return self.chessMethod1(n)
        elif n % 2 == 0:
            return self.chessMethod2(n)
        elif (n - 1) % 6 != 2:
            return lambda j: n if j == n else self.chessMethod1(n - 1)(j)
        else:
            return lambda j: n if j == n else self.chessMethod2(n - 1)(j)

    def start(self):
        chess_arrangement = self.finalChess()
        matrix = [[0] * self.n for _ in range(self.n)]
        for i in range(1, self.n + 1):
            matrix[i - 1][chess_arrangement(i) - 1] = 1
        for row in matrix:
            print("".join(list(map(str, row))))
        print(test(matrix))


# solution = IteratorExplicitSolution(int(input()))
# solution.start()
