package com.app.foreground.service.started

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Android их прибивает. В основном сейчас все пользуются foreground.
 * */
class BackgroundService : Service() {

    override fun onCreate() {
        super.onCreate()
    }
    /**
     * Для Bound сервисов, но всеровно просит переопределить. todo Интересно зачем
     * */
    override fun onBind(intent: Intent?): IBinder? = null

    /**
     * Для Started сервисов
     * Сюда приходит наш интент, тут необходимо уводить в фоновый поток и возвращать константу
     * которая определяет что делать в случае убийства процесса
     *
     * START_STICKY - перезапускать сервис
     * START_NOT_STICKY - не перезапускать сервис
     * START_REDELIVER_INTENT - перезапускать сервис с последним полученным интентом
     * */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
}