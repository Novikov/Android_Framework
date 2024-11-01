package com.app.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

/**
 * Состояния
 * Created/Stopped
 * Visible + 2 окна
 * Resumed
 * Негарантированный вызов ondestroy
 *
 * Изменение конфигурации. Адаптация к новым ресурсам, поэтому пересоздание.
 * -Переворот
 * -Смена языка
 * -Клавиатура?
 *
 * Сохранение состояния (Уничтожение на короткое время)
 * -Persistant Storage
 * -ViewModel
 * -OnSavedInstanceState (Serializable + Parcelable расписать разницу)
 * -Где подбирать восстановленный bundle (onCreate или onRestoreInstanceState?)
 * Какие вьюшки так же сохраняют состояние в Bundle (StateFul/StateLess) EditText с ID, RecyclerView
 * Как выключить фильтровать эвенты на пересоздание активити
 *
 * Финиш активити и что делать в этом случае. (Уничтожение на долгое время)
 * crash
 * finish()
 * onBackPressed()
 * Вручную удаляем из TaskManager
 *
 * Как активити станет host для фрагментов
 *
 * Связь с Application
 * Точка входа ActivityManager
 *
 * StartActivityForResult
 * Task/BackStack/LaunchMode/Process
 *
 * Типы интентов
 *
 * Фокусы клавиатуры это что такое.
 *
 * */