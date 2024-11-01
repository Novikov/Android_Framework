package com.app.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Activity - основной UI контейнер
 *
 * Может находиться в 4 состояниях
 * https://developer.android.com/static/codelabs/basic-android-kotlin-compose-activity-lifecycle/img/468988518c270b38_960.png
 *
 * ЖЦ https://developer.android.com/guide/components/activities/activity-lifecycle
 *
 *
 * */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}