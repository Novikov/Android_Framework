package com.app.broadcastreceiver.broadcast.examples

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


/**
 * https://developer.android.com/develop/background-work/background-tasks/broadcasts?hl=ru
 *
 * Android apps can send or receive broadcast messages from the Android system and other Android apps,
 * similar to the publish-subscribe design pattern.
 *
 * The system optimizes the delivery of broadcasts in order to maintain optimal system health.
 * Therefore, delivery times of broadcasts are not guaranteed.
 * Apps that need low-latency interprocess communication should consider bound services.
 *
 * Само широковещательное сообщение заключено в объект Intent
 *
 * todo RECEIVER_EXPORTED и RECEIVER_NOT_EXPORTED понимание
 * todo Влияние на состояние процесса более подробно
 * todo sendOrderedBroadcast(Intent, String) vs sendBroadcast(Intent)
 * todo android:exported
 * todo LocalBroadcastManager
 * */


class BroadCastReceiverWithDynamicRegistration : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        // Получаем сообщение из интента
        val message = intent?.getStringExtra("message")

        Toast.makeText(
            context,
            "$message",
            Toast.LENGTH_SHORT
        ).show()
    }
}