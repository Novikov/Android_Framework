package com.app.os_android.api_changes

/**
  Основные изменения OS Android

  Android 5
  -Ввели Job Scheduler

  Android 6
  -Doze mode
  -App StandBy

  Android 7
  -Doze mode продолжение
  -Нельзя подписываться на 3 неявных broadcast
   CONNECTION_ACTION
   ACTION_NEW_PICTURE
   ACTION_NEW_VIDEO

   Android 8
   -Нельзя регистировать broadcast receivers для неявных broadcast
   -Ввели разделение на foreground и background. Пока приложение в foreground, оно может создавать foreground и background
   сервисы. Если приложение в background, оно имеет окно в несколько минут, в течении которого омжет создавать сервисы. По окончанию не может

   Android 9
   FOREGROUND_SERVICE permission для foreground service
   App StandBy buckets

   Android 10
   -Появились типы foreground service. Теперь его нужно не просто создать, а еще указать тип.
   -Чтобы получить доступ к локации в foreground service нужно объявить тип location для foreground service
   -Запрещено запускать activity из background

   Android 11
   -Intent Service depricated
   -Если нужен доступ к камере или микрофону в foreground service, то надо задать camera или microphone тип для foreground service types
   -Ограничение на доступ к android api при запуске foreground service из состояния background

   Android 12
   -Короткоживущие foreground services: система ждет 10 секунд, перед тем как показат ьуведомление, но есть исключения
   -Нельзя запустить foreground services если приложение в background, но есть исключения

   Android 13
   -Пользователь может смахнуть уведомление от foreground service
   -Чтобы этого избежать нужно установить флаг через метод setOntgoing()
   -Если пользователь отклонил разрешение на показ уведомлений, то он всеровно увидит уведомление о foreground service в Task Manager(есть исключения)
   -Пользователь может остановить приложение (включая foreground service) в Task Manager (есть исключения)
   -Система не пришлет нам callback что нас отменили

   Android 14
   -Новые типы сервисов: health, remoteMessaging, shortService!!!, specialUse, systemExempted
   -Обязательно объявлять специальные разрешения для соответствующего типа foreground service, кроме Short Service.


 * */