# Лабораторная работа 14

- [Условие](https://temablag.github.io/BSU/computer_networks/lab14/lab14_theory.pdf)
- [Решение](https://temablag.github.io/BSU/computer_networks/lab14/lab14.pdf)


## Цель работы  
С помощью стандартных и расширенных ACL-листов запретить доступ к некоторым ресурсам сети.

---

## Этапы выполнения работы

### 1. Сборка схемы сети и настройка маршрутизации  
- Соберите схему сети согласно варианту задания.  
- Настройте маршрутизацию между узлами, задав маршруты по умолчанию.  
- Проверьте взаимодействие с узлами сети с помощью команды `ping`.  
- В отчёт включите результаты пингов.

### 2. Проверка доступности HTTP-сервера  
- Через эмулятор браузера на узлах проверьте доступность HTTP-сервера, введя в адресную строку браузера IP-адрес сервера.

### 3. Настройка стандартного ACL на маршрутизаторе R1  
- Войдите в режим глобальной конфигурации:  

- Создайте стандартный ACL, запрещающий устройству PC1 доступ к другим сетям
- Примените ACL на интерфейсе fa0/0 маршрутизатора R1

### 4. Проверка работы стандартного ACL  
- Используя эмулятор командной строки на PC1, выполните `ping` к другим устройствам сети.  
- Если `ping` не проходит, ACL настроен правильно.  
- В отчёте отразите результаты `ping`.

### 5. Настройка расширенного ACL на маршрутизаторе R3  
- Войдите в режим глобальной конфигурации:  

- Создайте расширенный ACL, запрещающий PC2 обращаться к HTTP-серверу
- Примените ACL на интерфейсе serial0/0/1 маршрутизатора R3

### 6. Проверка работы расширенного ACL  
- На PC2 выполните `ping` к другим устройствам сети и HTTP-серверу.  
- Попробуйте загрузить страницу HTTP-сервера через браузер на PC2.  
- Если `ping` работает, но загрузка страницы — нет, ACL настроен корректно.  
- Проверьте доступность HTTP-сервера с других узлов.  
- В отчёте приведите результаты `ping` и загрузки страницы.
