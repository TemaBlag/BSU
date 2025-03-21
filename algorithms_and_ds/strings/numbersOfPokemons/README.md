# Problem 30. Номера покемонов

С недавних пор во время сражений покемонов карманным зверькам стали давать номера,
точь-в-точь как футболистам или иным спортсменам. Милый покемон Пикачу, много раз
выступавший на соревнованиях, решил провести опрос среди своих друзей и узнать: а
под какими номерами они выступали? Пикачу мог путаться, и спрашивать покемонов несколько раз.
Более того, они и сами могли путаться, и называть не все свои номера, или называть один и
тот же номер несколько раз. Пикачу это не устраивало. Для своих исследований он хочет
получить хороший список, где все покемоны указаны один раз, все их названные номера
указаны один раз, а также все это отсортировано. Но и это еще не все. Пикачу считает,
что если один покемон назвал два таких номера $a$ и $b$, что $b$ является суффиксом $a$,
то номер $b$ не следует считать номером — его нужно удалить.Он обратился за помощью
к своему другу Джолтеону, и тот легко справился с его просьбой. А сможете ли вы?

## Формат входных данных

В первой строке следует целое число $N$ ($1 \le N \le 100$) —
количество опросов. Далее следует $N$ строк: имя покемона, количество его номеров,
которые он назвал, и сами номера соответсвенно. Имя покемона — строка, состоящая
из строчных и прописных букв алфавита, а также цифр, длиной не более, чем 100
символов. Всего покемон мог назвать не более, чем 100 номеров за один опрос.
Номер покемона — целое положительное число, не превышающее $10^9$.
Гарантируется, что имя покемона во входном файле — это название реально существующего покемона.
Также гарантируется, что одного покемона опрашивали не более 5 раз.

## Формат выходных данных

Выведите отсортированный список опросов. Сначала
выведите количество строк, а далее выводите по одной строке  — итоговый список.
Строки должны быть отсортированы лексикографически, а числа внутри строк  —
по неубыванию номера.
