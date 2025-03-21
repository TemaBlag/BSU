# Лабораторная работа 6

- [Условие](https://temablag.github.io/BSU/computer_networks/lab6/lab6_theory.pdf)
- [Решение](https://temablag.github.io/BSU/computer_networks/lab6/lab6.pdf)

### Описание
Конфигурирование DHCP-сервера 

## Задание 1. Конфигурирование DHCP-сервера  
#### 1.1 Первая часть задания 1  

1. Реализовать схему (рисунок 1) подключения группы компьютеров через **Hub** к **DHCP-серверу**.  
2. Продумать адресацию для узлов, шлюза, DNS-сервера.  
3. Сконфигурировать сервер как **DHCP-сервер**.  
4. В отчете раскрыть понятие **DHCP-сервер**, его назначение.  
5. Описать основное отличие между **DHCP** и **ARP**.  
6. В отчете отобразить разработанную схему сети.  
7. Выбрать пул адресов, который будет динамически распределяться. Использовать только **первые 50%** из пула адресов.  
8. Описать процедуру настройки DHCP-сервера, используя **скриншоты с комментариями**.  
9. Освободить IP-адреса на двух ПК и обновить их в обратном порядке.  
10. Отразить в отчете, какие IP-адреса были до обновления и какие стали после обновления.  
11. Проанализировать результаты исследования и сделать выводы.  
12. Подтвердить результаты выполнения пунктов задания скриншотами с текстом.  

---
#### 1.2 Вторая часть задания 1  
1. Создать копию модели сети (модель №2 в файле `.pkt`).  
2. Добавить ещё один DHCP-сервер с другой сетевой конфигурацией.  
3. Добавить новый узел и проверить, какой DHCP-сервер будет выбран добавленным узлом.  
4. Отключить первый DHCP-сервер и проверить назначение конфигурации на новом узле.  
5. Изучить новую конфигурацию сети на узлах.  
6. Отключить второй DHCP-сервер и изучить конфигурацию сети на узлах.  
7. Освободить IP-адреса на двух ПК и обновить их после нескольких пингов.  
8. Отразить в отчете изменения IP-адресов после обновления.  
9. Подтвердить выполнение задания скриншотами и комментариями.  

---

## 2. Конфигурирование маршрутизатора Cisco в качестве сервера DHCP  

### 2.1 Задание 2. Сконфигурировать маршрутизатор Cisco в качестве сервера DHCP  
1. Спроектировать схему подключения группы компьютеров через коммутатор к маршрутизатору (Рисунок 2).  
2. Воспользоваться следующими сетевыми ресурсами:  
   - Маршрутизатор  
   - Четыре и более компьютера  
   - Коммутатор  
   - Прямые кабели для соединения ПК, коммутатора и маршрутизатора  

---
## **2.2 Настройка DHCP в CLI**

Настройка DHCP в CLI выполняется в **восемь этапов**:

### **2.2.1. Создать пул адресов DHCP**  
Создаётся пул адресов, который будет использоваться для автоматической раздачи IP-адресов устройствам в сети.  

### **2.2.2. Указать подсеть**  
Определяется диапазон IP-адресов в заданной подсети.  

### **2.2.3. Исключить IP-адреса**  
Исключаются определённые IP-адреса, чтобы они не назначались устройствам автоматически (например, для статических конфигураций).  

### **2.2.4. Указать доменное имя**  
Устанавливается доменное имя, которое будет использоваться устройствами в сети.  

### **2.2.5. Указать IP-адрес сервера DNS**  
Задаётся IP-адрес сервера DNS для разрешения доменных имён в IP-адреса.  

### **2.2.6. Выбрать маршрутизатор по умолчанию**  
Устанавливается IP-адрес маршрутизатора, который будет использоваться устройствами в качестве шлюза по умолчанию.  

### **2.2.7. Установить время аренды**  
Задаётся продолжительность аренды IP-адреса.  

### **2.2.8. Проверить конфигурацию**  
Проверяется корректность настроек DHCP и доступность сервера в сети.  

---

# **2.3 Выполнение задания 2**

Для выполнения задания выполните следующие шаги:

### ✅ **1. Реализовать схему сети**  
Создайте схему сети, аналогичную приведённой на рисунке 2.  

### ✅ **2. Присвоить имена маршрутизаторам и хостам**  
Присвойте маршрутизаторам и хостам имена в соответствии с принятыми ранее правилами.  

### ✅ **3. Выполнить все этапы настройки DHCP (кроме 7 этапа)**  
Осуществите настройку DHCP в соответствии с этапами, указанными в разделе **2.2**.  

### ✅ **4. Создать пул адресов DHCP**  
- Создайте пул адресов с именем `pool_<Номер вашего варианта>`  
- Исключите около **50%** адресов из пула  
- Доменное имя укажите в формате:  
   **FIOстудента.FPMI.by**  

### ✅ **5. Подписать IP-адрес интерфейса маршрутизатора**  
На разработанной модели сети подпишите IP-адрес интерфейса маршрутизатора.  

### ✅ **6. Пример последовательности команд**  

### ✅ **7. На рабочих станциях проверьте (как это сделать?) настройки DHCP**  

### ✅ **8. На любых двух ПК освободите IP – адреса и через некоторое время обновите их**  

### ✅ **9. Отразите в отчете, какие IP – адреса были до обновления и какие IP – адреса стали после обновления**  

### ✅ **10. В отчет включить скриншоты с комментариями по каждому этапу (раздел 2.2), а также скриншоты конфигураций только  двух на ваше усмотрение рабочих станций**  

# **Задание 3: Определение IP-адресов интерфейсов ПК**  

## **Описание задания**  
На личном ноутбуке войдите в сеть **БГУ** и определите IP-адреса интерфейсов вашего ПК.  
Аналогичные процедуры выполните в любой другой сети (например, дома).  
Заполните таблицу с полученными данными.  

---

## **Результаты выполнения задания**  

| №   | Сетевой интерфейс ноутбука | IP-адрес в сети БГУ | IP-адрес в другой сети |
|------|----------------------------|---------------------|------------------------|
| 1    | Wi-Fi                      | -------------       | -------------          |
| 2    | Ethernet                   | -------------       | -------------          |

---

### ✅ **1. Как  Вы получили IP-адреса интерфейсов?  Приложите скриншоты**  

### ✅ **2. Проанализируйте строки таблицы и сделайте обоснование полученных данных**  




