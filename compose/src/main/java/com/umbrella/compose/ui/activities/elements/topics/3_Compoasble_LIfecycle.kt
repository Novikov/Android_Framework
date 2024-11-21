package com.umbrella.compose.ui.activities.elements.topics

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/**
 * Каждая Composable функция проходит три lifecycle этапа:
 * Enter the Composition - функция была вызвана первый раз, ее содержимое отобразилось на экране. Она теперь является частью Composition.
 * Recompose - функция была перевызвана, т.к. изменились входные значения. Это могло привести к изменениям на экране. Функция продолжает оставаться частью Composition.
 * Leave the Composition - содержимое функции больше не отображается на экране, т.к. эта функция теперь не вызывается. Функция более не является частью Composition
 *
 * https://developer.android.com/develop/ui/compose/lifecycle?hl=ru
 * */

/**
 * RememberObserver
 * Мы выяснили, что remeber вычисляет и запоминает значение, когда вызывается первый раз,
 * т.е. когда он становится частью Composition. И забывает значение, когда покидает Composition.
 * Compose дает нам возможность отслеживать эти события. Для этого существует интерфейс RememberObserver,
 * через который remember может уведомить нас о том, что он запомнил или забыл значение.
 * */

class MyObject : RememberObserver {

    init {
        Log.d(TAG, "init")
    }

    override fun onRemembered() {
        Log.d(TAG, "onRemembered")
    }

    override fun onForgotten() {
        Log.d(TAG, "onForgotten")
    }

    override fun onAbandoned() {
        Log.d(TAG, "onAbandoned")
    }

}

/**
 * Интерфейс RememberObserver имеет следующие методы:
 * onRemembered - remember создал и запомнил объект.
 * onForgotten - remember забыл объект
 * onAbandoned - что-то в Compose пошло не так (Краш), значение будет забыто
 * */

@Composable
fun HomeScreen() {
    Column {
        var checked by remember { mutableStateOf(false) }
        Checkbox(checked = checked, onCheckedChange = { checked = it })
        if (checked) {
            val myObject = remember { MyObject() }
            // val a = 1 / 0 для того чтобы сработал onAbandoned
        }
    }
}

/**
 * Мы можем в onRemembered вызвать какой-то свой код, который должен выполниться один раз при первом вызове Composable кода, например - при старте экрана.
 * Или мы можем стартовать корутину в onRemembered и отменить ее в onForgotten.
 * Или подписаться на что-либо в onRemembered и отписаться в onForgotten.
 * Таким образом жизненный цикл корутины или подписки совпадет с жизненным циклом Composable кода.
 * Именно на этом механизме базируются некоторые SideEffects функции
 * */