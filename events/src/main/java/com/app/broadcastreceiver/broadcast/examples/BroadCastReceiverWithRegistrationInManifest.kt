package com.app.broadcastreceiver.broadcast.examples

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


/**
 *
 * */


class BroadCastReceiverWithRegistrationInManifest : BroadcastReceiver() {

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