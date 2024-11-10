package com.app.broadcastreceiver.broadcast

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.app.broadcastreceiver.R
import com.app.broadcastreceiver.broadcast.examples.BroadCastReceiverWithDynamicRegistration
import com.app.broadcastreceiver.broadcast.examples.BroadCastReceiverWithRegistrationInManifest


class BroadcastActivity : AppCompatActivity() {

    var dynamicBroadcast: BroadcastReceiver? = BroadCastReceiverWithDynamicRegistration()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)

        initReceiverWithDynamicRegistration()
        initReceiverWithRegistrationInManifest()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initReceiverWithDynamicRegistration(){
        val filter = IntentFilter(ACTION_FOR_DYNAMIC_RECEIVER)
        registerReceiver(dynamicBroadcast, filter, RECEIVER_EXPORTED)

        val sendButton = findViewById<Button>(R.id.send_button)
        sendButton.setOnClickListener {
            val intent = Intent(ACTION_FOR_DYNAMIC_RECEIVER)
            intent.putExtra("message", "Hello from MainActivity!")
            sendBroadcast(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initReceiverWithRegistrationInManifest(){
        val sendButton = findViewById<Button>(R.id.send_manifest_button)
        sendButton.setOnClickListener {
            val intent = Intent("com.app.broadcastreceiver.MY_NOTIFICATION_TO_MANIFEST_RECEIVER")
            intent.setComponent(ComponentName("com.app.broadcastreceiver.broadcast.examples", "com.app.broadcastreceiver.broadcast.examples.BroadCastReceiverWithRegistrationInManifest"))
            intent.putExtra("message", "Hello from MainActivity2!")
            application.sendBroadcast(intent,)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Отменяем регистрацию BroadcastReceiver, чтобы избежать утечек памяти
        unregisterReceiver(dynamicBroadcast)
    }

    companion object {
        const val ACTION_FOR_DYNAMIC_RECEIVER = "com.app.broadcastreceiver.MY_NOTIFICATION"
        const val ACTION_FOR_MANIFEST_RECEIVER = "com.app.broadcastreceiver.MY_NOTIFICATION_TO_MANIFEST_RECEIVER"
    }
}