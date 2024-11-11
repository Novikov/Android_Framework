package com.app.data_storage

/**
    Типы хранилищ:
    Простые (Key-value)
    Файловая система
    Базы данных

    Как выбрать?
    Какой тип данных мы должны хранить?
    Каков объем данных?
    Должны ли данные быть доступны только нашему приложению?

    Хранилище самого приложения
    1)Internal (Настройки, базы данных, shared preferences)
    -Разрешения не нужны
    -Можем использовать Java File для записи и чтения
    -Доступ к данным только у нашего приложения
    -Размер ограничен!!!

    2)External (SD карта) (Медиа, кеш)
    -Разрешения не нужны
    -Можем использовать Java File для записи и чтения
    -Файлы могут быть доступны другим приложениям!!!
    -Перед выполнением любой операции (чтение/запись) нужно проверять доступна ли SD карта
    -Размер ограничен!!!

    Общее хранилище - никому не принадлежит. С ним работает пользователь.
    -Фото, Видео, Аудио, документы
    -До Android 9 нужно было просто указать разрешения в манифесте (приложения могли писать и читать External)
    -В Android 10 его разбили на 2 категории (медиа файлы и все остальное)
    -Медиа пишем и читаем через MediaStore
    -В Android 13 Теперь нужно запрашивать гранулярные разрешения для чтения и записи файлов

    PhotoPicker - упращение способа взаимодействия с общим хранилищем. Работает с ActivityResult Api
    Storage Access Framework - похожая чтука на PhotoPicker


    Кэш файлы - это временные файлы которые может удалить система в любой момент


    todo
    Key Store + EnqryptedShared Preferences

 * */