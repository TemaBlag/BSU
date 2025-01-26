# Лабораторная 4

Binary Search Tree on Java

## task

Разработать пользовательский тип данных «Двоичное дерево сортировки». Требуемые методы:
- Добавление вершины;
- Удаление вершины (опционально);
- Поиск вершины;
- Обход дерева «Корень-Левый-Правый»;
- Обход дерева «Левый-Правый-Корень»;
- Обход дерева «Левый-Корень-Правый».
Использовать связную структуру для реализации двоичного дерева.

Параметризовать класс. Предложить варианты параметризации числом (Number) и пользовательским классом (они реализуют интерфейс Comparable).


**implemented features**:

- _addNode(value)_ - adding new node with `value` to tree
- _deleteNode(value)_ - delete node with `value` to tree
- _search(value)_ - search node with `value` in tree
- _lnr()_ - print nodes value in `left-root-right` tree walk
- _nlr()_ - print nodes value in `root-left-right` tree walk
- _lrn()_ - print nodes value in `left-right-root` tree walk
