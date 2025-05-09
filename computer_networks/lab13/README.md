# Лабораторная работа 13

- [Условие](https://temablag.github.io/BSU/computer_networks/lab13/lab13_theory.pdf)
- [Решение](https://temablag.github.io/BSU/computer_networks/lab13/lab13.pdf)

## Описание задачи

В данной работе реализуется логическое разделение единой широковещательной доменной сети на несколько изолированных подсетей с помощью технологии виртуальных локальных сетей (VLAN). Для организации взаимодействия подсетей используется маршрутизатор с подинтерфейсами.

## Схема сети

1. Один коммутатор Cisco 2960, три VLAN (10, 20 и 1).
2. Один маршрутизатор с магистральным (trunk) подключением к коммутатору.
3. Шесть рабочих станций (ПК) в трёх подсетях:

| VLAN | Узлы         | IP-адрес    | Маска         | Шлюз        |
| ---- | ------------ | ----------- | ------------- | ----------- |
| 10   | CompVLAN10-1 | 172.16.10.2 | 255.255.255.0 | 172.16.10.1 |
|      | CompVLAN10-2 | 172.16.10.3 | 255.255.255.0 | 172.16.10.1 |
| 20   | CompVLAN20-1 | 172.16.20.2 | 255.255.255.0 | 172.16.20.1 |
|      | CompVLAN20-2 | 172.16.20.3 | 255.255.255.0 | 172.16.20.1 |
| 1    | CompVLAN1-1  | 172.16.1.3  | 255.255.255.0 | 172.16.1.1  |
|      | CompVLAN1-2  | 172.16.1.4  | 255.255.255.0 | 172.16.1.1  |
|      | CompVLAN1-3  | 172.16.1.5  | 255.255.255.0 | 172.16.1.1  |
|      | CompVLAN1-4  | 172.16.1.6  | 255.255.255.0 | 172.16.1.1  |

## Порядок выполнения

1. **Создание VLAN на коммутаторе**

   ```shell
   Switch(config)# vlan 10
   Switch(config-vlan)# name Faculty
   Switch(config-vlan)# exit
   Switch(config)# vlan 20
   Switch(config-vlan)# name Students
   Switch(config-vlan)# exit
   ```
2. **Настройка интерфейса управления**

   ```shell
   Switch(config)# interface vlan 1
   Switch(config-if)# ip address 172.16.1.2 255.255.255.0
   Switch(config-if)# no shutdown
   Switch(config-if)# exit
   Switch(config)# ip default-gateway 172.16.1.1
   ```
3. **Назначение портов доступа**

   ```shell
   Switch(config)# interface range fa0/5 - 6
   Switch(config-if)# switchport mode access
   Switch(config-if)# switchport access vlan 10
   Switch(config-if)# exit

   Switch(config)# interface range fa0/7 - 8
   Switch(config-if)# switchport mode access
   Switch(config-if)# switchport access vlan 20
   Switch(config-if)# exit
   ```
4. **Настройка транка**

   ```shell
   Switch(config)# interface fa0/2
   Switch(config-if)# switchport mode trunk
   Switch(config-if)# exit
   ```
5. **Сохранение конфигурации**

   ```shell
   Switch# copy running-config startup-config
   ```

## Настройка маршрутизатора

1. **Основной интерфейс VLAN 1**

   ```shell
   Router(config)# interface fa0/0
   Router(config-if)# ip address 172.16.1.1 255.255.255.0
   Router(config-if)# no shutdown
   Router(config-if)# exit
   ```
2. **Подинтерфейсы для VLAN**

   ```shell
   Router(config)# interface fa0/0.10
   Router(config-subif)# encapsulation dot1q 10
   Router(config-subif)# ip address 172.16.10.1 255.255.255.0
   Router(config-subif)# exit

   Router(config)# interface fa0/0.20
   Router(config-subif)# encapsulation dot1q 20
   Router(config-subif)# ip address 172.16.20.1 255.255.255.0
   Router(config-subif)# exit
   ```

## Проверка и отладка

1. **Просмотр статуса транков**

   ```shell
   Switch# show trunk
   ```
2. **Просмотр интерфейсов и маршрутов на роутере**

   ```shell
   Router# show ip interface brief
   Router# show ip route
   ```
3. **Проверка связности пингами**

   * Между хостами внутри одной VLAN
   * Между хостами из разных VLAN
   * От хостов к IP-интерфейсам коммутатора и маршрутизатора

## Ответы на контрольные вопросы

1. **Порты во VLAN 1:** все не настроенные в VLAN 10/20 (Fa0/1–4, Fa0/9–24).
2. **Порты во VLAN 10:** Fa0/5, Fa0/6.
3. **Порты во VLAN 20:** Fa0/7, Fa0/8.
4. **Ping к 172.16.1.2:**

   * CompVLAN1-1: *успешно*
   * CompVLAN10-1: *неуспешно*
   * CompVLAN20-1: *неуспешно*
5. **Ping между CompVLAN1-3 и CompVLAN10-2/20-2:**

   * До 10-2: *не доступен*
   * До 20-2: *не доступен*
6. **Причины изоляции:** отсутствие маршрутизации между VLAN до настройки подинтерфейсов.
7. **Связь между компьютерами:** ограничена границами VLAN и без транка/маршрутов недоступна.

## Индивидуальное задание

1. Присвоить именование хостам по формату `ФИО_VLAN_номер`.
2. Обновить схему сети, отобразив новые имена.
3. Реализовать конфигурацию VLAN и маршрутизации, как выше.
4. Распечатать таблицы:

   * Routing Table до/после пинга.
   * Port Status Summary коммутатора, роутера и как минимум одного хоста в каждой VLAN.
   * Анализ выводов.
