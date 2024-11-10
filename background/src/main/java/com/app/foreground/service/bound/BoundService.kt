package com.app.foreground.service.bound

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder

/**
 * Сервис работает только тогда когда мы к нему привязываемся и завершается при отвязке
 *
 * Реализация
 * Имплементировать binder и вернуть его в методе onBind
 * a)Наследник Binder
 * b)Используя Messeger
 * c)AIDL
 *
 * Подключитсья к сервису при помощи bindService и не забыть отключиться в unbindService
 *
 * */
class BoundService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun bindService(
        service: Intent,
        conn: ServiceConnection,
        flags: BindServiceFlags
    ): Boolean {
        return super.bindService(service, conn, flags)
    }

    override fun unbindService(conn: ServiceConnection) {
        super.unbindService(conn)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}