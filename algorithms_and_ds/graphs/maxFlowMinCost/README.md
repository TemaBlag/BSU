# Максимальный поток в сети (простая версия)

Дана сеть с направленными рёбрами, каждый из которых имеет ёмкость (максимальный поток, который может пройти через это ребро). Нужно найти максимальный поток от истока к стоку в этой сети.

## Входные данные:

Сеть представлена графом, где вершины — это узлы сети, а рёбра — это каналы с ёмкостями.

Число вершин n и рёбер m.
Для каждого ребра даются три числа: вершины v и u (откуда и куда идёт поток), и ёмкость c этого ребра.

## Выходные данные:

Нужно вычислить и вывести максимальный поток из вершины 1 (исток) в вершину n (сток) сети.
