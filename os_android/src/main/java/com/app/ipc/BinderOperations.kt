package com.app.ipc

import android.os.Binder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class BinderOperations : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binder = Binder()
    }
}