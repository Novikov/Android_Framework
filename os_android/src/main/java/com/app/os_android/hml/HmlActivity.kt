package com.app.os_android.hml

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread


/**
 * Main Thread
 * -первый поток в котором запускается процесс приложения
 * -В нем выполняются последовательности все события от пользователя последовательно
 * -Методы жизненного цикла Activity/Fragment/Service
 * -Обработчики системных пользовательских событий (Broadcast Receivers)
 *
 * Что может пойти не так
 * Краш если пытаемся работать с UI из другого потока кроме UI потока
 * ANR
 * */
class HmlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        example1()
    }

    /**Уход от Exception при работе с интерфейсом вне главного потока*/
    private fun example1() {
        thread {
            /** Метод который можно вызвать внутри Thread если хотим сделать что то на главном потоке
             * Например обратиться к интерфейсу приложения
             * */
            runOnUiThread { } // под капотом используется Handler. О нем ниже
        }.run()
    }

    /**ANR*/
    private fun example2() {
        /** Слишком много работы c UI внутри главного потока*/
        Thread.sleep(10000)
    }

    /** Главный поток является главным потому что в нем есть Looper
     * Looper это спец класс задача которого является создание бесконечного цикла приема сообщений
     * Система создает первый поток и назнает ему Lopper и зацикливает на приемкку сообщений (MessageQueue)
     * С MessageQueue мы не взаимодействуем на прямую.
     * Со всей этой историей взаимодействуем через Handler.
     * Задача Handler создавать и обрабатывать сообщения и так же опционально обрабатывать сообщения которые приходят и обрабатываются лупером
     * */
    @RequiresApi(Build.VERSION_CODES.P)
    private fun example3() {
        //Реализация с пустым конструктором крашнет приложения потому что создастся реализация на потоке исполнениякоторый может быть не main
        val handler = Handler(Looper.getMainLooper())
        val runable = Runnable { }
        handler.post(runable) // выполнить действие в главном потоке
        handler.postDelayed(
            (runable),
            1000
        ) // выполнить действие в главном потоке с задержкой

        val token = Any() //Объект с помощью которого можем отменить отложенное действие
        handler.postDelayed(runable, token, 1000)
        handler.removeCallbacks(runable) //Метод по отмене runable
    }

    /** Другая перегрузка handler с поддержкой обработки сообщений по id. Внутри when бработчик*/
    private fun example4() {
        val handler = Handler(
            Looper.getMainLooper()
        ) {
            when (it.what) {
                0 -> {}
                else -> {}
            }
            return@Handler true
        }

        val message = Message.obtain(handler, 0)
        handler.sendMessage(message)
    }
}