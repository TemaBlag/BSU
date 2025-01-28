# RSQ

RSQ — Range Sum Query (запрос суммы на отрезке)

Задана последовательность из 𝑛 чисел:
$𝐴 = [a_0,a_1,a_2,…,a_{(n−1)}]$

Поступают запросы двух типов:
- запрос модификации: 𝐀𝐝𝐝(𝒊,𝒙) — прибавить к 𝑖-му элементу число 𝑥;
- запрос суммы: 𝐅𝐢𝐧𝐝𝐒𝐮𝐦(𝒍, 𝒓) — вычислить сумму на [𝑙,𝑟)

$S = \sum_{k=l}^{r-1} a_k$

C помощью специальных структур:
- [префиксная сумма](https://github.com/TemaBlag/BSU/tree/main/algorithms_and_ds/data_structures/rsq/PrefixSum);
- [дерево отрезков](https://github.com/TemaBlag/BSU/tree/main/algorithms_and_ds/data_structures/rsq/SegmentTree);
- [блоки](https://github.com/TemaBlag/BSU/tree/main/algorithms_and_ds/data_structures/rsq/BlocksSum);

выполнить интервальный запрос можно быстрее.
