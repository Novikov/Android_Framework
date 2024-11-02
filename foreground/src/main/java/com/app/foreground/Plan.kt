package com.app.foreground

/**
 * Определение и назначение
 * LifeCycle
 * Способы остановки сервиса
 * OnStartCommand вызывается на главном потоке. Зачем тогда сервис? Потмоу что будет работат ьдаже если мы ушли из приложения
 *
 * Типы сервисов + механизмы борббы за его жизнь (чтоб не убила система) Notification
 * Foreground
 * Background
 *
 * IntentService (Depricated)
 * -Как я понял не нужно создавать фоновый поток в onStartCommand
 * -Там будет не onstartCommand, а onHandleIntent.
 *
 * Слышал на интервью у Розовыа что с помощью сервисов как то поддерживают приоритет процесса или что то в этом роде. Узнать что это
 *
 *
 * WorkManager (Доступен с 14 Api)
 * -Гарантированное выполнение короткой фоновой задачи (напимер добавление в избранное и синхронизация с сервером)
 * Worker (тут описываем что хотим сделать),
 * WorkRequest (запрос на выполнение нашей задачи),
 * WorkManager(Управляет запросами нашей задачи).
 *
 * WorkManager позволяет обходить ограничения которые накладывает система на наше приложение - DozeMode/StandBy
 *
 * DozeMode - Система экономит заряд и морозит телефон когда он выключен
 * Doze это временные окошки когда появляется небольшая активность
 * Если устройство лежит долго - такие окошки появляются все реже
 * WorkManager избавляет от этой проблемы и выполнит задачу которую мы запланировали
 *
 * StandBy
 * Происходит ограничение фоновой работы тех приложений которые мы долго не запускаем
 * Чтобы сходить в сеть и выполнить какую то фоновую задачу будет предоставлено время например раз в сутки
 *
 *
 * AlarmManager
 *
 * killable activity !!! Почему то просят добавлять сервис чтоб система не убивала.
 * */