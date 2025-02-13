# Лабораторная работа №3: Базовые растровые алгоритмы

## Цель работы
Закрепить теоретические знания и освоить основные алгоритмы растеризации отрезков и окружностей. Реализовать следующие алгоритмы:
- Пошаговый алгоритм
- Алгоритм ЦДА (Цифровой дифференциальный анализатор)
- Алгоритм Брезенхема (линии)
- Алгоритм Брезенхема для окружности
- Алгоритм Кастла-Питвея

## Задачи
- Создать класс для отображения растеризованного отрезка
- Создать класс для отображения пояснительной информации по ходу работы алгоритма
- Реализовать пользовательский интерфейс с выбором алгоритма
- Разработать отдельные методы для реализации каждого из алгоритмов

## Ход работы

### 1. Класс `PixelPath`
Основной класс `PixelPath` отвечает за отображение интерфейса и реализацию алгоритмов. Приложение построено на базе библиотеки `tkinter`. Поддерживается масштабирование и панорамирование области рисования для удобства работы.

### 2. Создание интерфейса
Метод `create_main_interface` создаёт кнопку для открытия окна с настройками, где можно задать начальные и конечные координаты отрезков, радиус окружности и выбрать алгоритм.

### 3. Сетка и координатная система
В методе `draw_grid` на канве прорисовываются оси X и Y, а также добавляются метки для навигации. В приложении реализованы функции масштабирования и панорамирования для комфортного просмотра.

### 4. Реализация алгоритмов
Методы для реализации каждого алгоритма:
- `create_line` — пошаговый алгоритм для растеризации отрезка.
- `dda` — алгоритм ЦДА, равномерно распределяющий точки по длине отрезка.
- `bresenham_line` — алгоритм Брезенхема для быстрой отрисовки отрезков.
- `bresenham_circle` — алгоритм Брезенхема для окружностей.
- `castle_pitway` — алгоритм Кастла-Питвея для сглаживания линий.

### 5. Замеры времени
Для каждого алгоритма были проведены замеры времени выполнения для оценки производительности. 

| Алгоритм             | Время    |
|----------------------|----------|
| Пошаговый алгоритм   | 43 мс    |
| Алгоритм ЦДА         | 50 мс    |
| Алгоритм Брезенхема  | 10 мс    |
| Алгоритм Ву          | 0.1 мс   |
| Брезенхем для окружности | 1.7 мс |

## Выводы
В результате работы были реализованы базовые алгоритмы растеризации. Приложение предоставляет интерфейс для выбора алгоритмов и визуализации их работы, что способствует лучшему пониманию особенностей и логики каждого метода.

## Используемые технологии
- **Язык программирования**: Python
- **GUI**: `tkinter`
- **Библиотеки**: `numpy`, `time`, `tkinter`
