package com.app.foreground.service.started

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * 1. Необходимо добавить permission FOREGROUND_SERVICE в MANIFEST
 * 2. Запустить сервис при помощи startForegroundService вместо startService
 * 3. В течение 5 секунд после запуска сервиса вызвать в нем startForeground для показа уведомления (метод для показа уведомлений)
 * Если этого не сделать Android убьет наш сервис
 * */
class ForegroundService : Service() {

    /**
     * */
    override fun onBind(intent: Intent?): IBinder? {
        throw UnsupportedOperationException()
    }

    /**
     * */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
}