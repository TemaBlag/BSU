#  Строительство дорог

Имя входного файла: input.txt

Имя выходного файла: output.txt

Ограничение по времени: 1 с

Ограничение по памяти: 256 МБ
___

Берляндия состоит из п городов. Изначально все города изолированы, то есть между городами нету дорог.

По очереди будут добавляться дороги между парами городов. Необходимо после каждой добавленной дороги узнать, какое количество
компонент связности из городов получилось.

## Формат входных данных

Первая строка входного файла содержит два целых числа $n$ и $q$ $(1 \leq n, q \leq 500000)$ - количество городов и запросов соответственно.
Каждая из следующих $q$ строк содержит два целых числа u и, v - между какой парой городов будет построена дорога. В данной задаче между любой
парой городов строится не более одной дороги, а для любого запроса справедливо $u \neq v$.

## Формат выходных данных

На каждый запрос второго типа необходимо вывести одно число - количество компонент связности в графе из городов.

### Пример

| input.txt | output.txt |
|-----------|------------|
| 5 5 | 4 |
| 1 2 | 3 |
| 3 4 | 2 |
| 1 3 | 1 |
| 3 5 | 1 |
| 1 5 ||
