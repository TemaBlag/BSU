n, m = map(int, input().split())
coefficients = [0] * (m + 1)
coefficients[0] = 1
x = 7 + 10 ** 9
for i in range(1, n // 2 + 2):
    for j in range(min(i, m), 0, -1):
        coefficients[j] = (coefficients[j - 1] + coefficients[j]) % x
for i in range(n - n // 2 - 1, 0, -1):
    for j in range(i, 0, -1):
        if j + m - i > 0:
            coefficients[j + m - i] = (coefficients[j - 1 + m - i] + coefficients[j + m - i]) % x
print(coefficients[m] if n > 0 else 0)