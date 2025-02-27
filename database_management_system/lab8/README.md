# Лабораторная работа №8  
## **Создание таблиц и индексов средствами языка определения данных DDL**  

### **Задания**  

1. **Создание таблицы STUDENTS_N**  
   - Создайте таблицу **STUDENTS_N** (где N — ваш номер по журналу) с полями:  
     - **SNUM** — уникальный идентификатор студента,  
     - **SFAM** — фамилия,  
     - **SIMA** — имя,  
     - **SOTCH** — отчество,  
     - **STIP** — стипендия (с подстановкой значения по умолчанию).  

2. **Изменение структуры таблицы**  
   - Добавьте поля **COURS INT** и **TELEPHONE CHAR(15)**.  
   - Удалите добавленные поля **COURS** и **TELEPHONE**.  

3. **Создание пользовательского типа данных TELEPHONE**  
   - Тип данных должен:  
     - представлять собой текстовое поле переменной длины (до 20 символов),  
     - быть обязательным для заполнения.  

4. **Создание индекса**  
   - Создайте индекс для поля **SFAM** таблицы **STUDENTS_N**.  

5. **Создание остальных таблиц**  
   - Создайте остальные таблицы БД, разработанной в ЛР-2, с именами по примеру **STUDENTS_N**.  
   - Укажите ключевые поля и подстановочные значения (например, для оценки в таблице успеваемости).  

6. **Заполнение таблиц данными**  
   - Используйте оператор **INSERT** и вложенные запросы для:  
     - заполнения таблицы **STUDENTS_N** данными только для отличников (признак: только отличные оценки в таблице **USP**).  
     - заполнения остальных созданных таблиц из данных таблиц ЛР-2.  
     - добавления неотличников в **STUDENTS_N**.  

7. **Добавление внешних ключей**  
   - Установите связи между таблицами с возможностью каскадного обновления и удаления.  

8. **Добавление ограничений**  
   - Ограничение для поля **UDATE** таблицы **USP_N**:  
     - Проверка корректности вводимой даты (нельзя вводить будущие даты).  
   - Ограничение для поля **PNAME** таблицы **PREDMET_N**:  
     - Допустимые значения:  
       - "Физика"  
       - "Химия"  
       - "Математика"  
       - "Философия"  
       - "Экономика".  
