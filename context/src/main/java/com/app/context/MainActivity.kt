package com.app.context

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity <- AppCompatActivity <- FragmentActivity <- ComponentActivity <- Activity <- ContextThemeWrapper <- Context
 *
 * Context: Обычный Context предоставляет доступ к ресурсам, системным службам и информации о приложении, но не имеет функциональности для работы с темами и стилями.
 * ContextThemeWrapper: Этот класс позволяет применять тему к контексту. Он может изменять стиль представлений (например, виджетов) в зависимости от указанной темы.
 *
 * */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}