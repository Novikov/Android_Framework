package com.app.activity

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.os.Process
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    /**
     * Корень иерархии запуска Android приложения - Binder.
     * Именно он диктует ограничения на размер Bundle
     *  https://ars.els-cdn.com/content/image/1-s2.0-S0167404813000540-gr1.jpg
     *
     * Следующим в списке стоит SystemServer. К нему получить доступ из кода мы не можем.
     * SystemServer — это ключевой компонент архитектуры Android, который запускается при загрузке системы.
     * Он управляет основными системными сервисами, такими как управление окнами, доступ к пакетам, управление пользователями
     * и многие другие. Все эти сервисы предоставляют API, которые приложения могут использовать для взаимодействия с системой.
     *
     * А вот к ActivityManager, которого вызывает SystemServer - можем.
     *
     * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val  activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        //Получение информации о текущих процессах приложения
        val processes = activityManager.runningAppProcesses

        //Получение информации о конфигурации устройства
        val configuration = activityManager.deviceConfigurationInfo

        //Получение информации о Tasks у Activity
        val tasks = activityManager.appTasks

        //Получение информации о памяти процесса
        val myPid = Process.myPid()
        val pids = intArrayOf(myPid)
        val memoryInfo = activityManager.getProcessMemoryInfo(pids)
    }
}