package com.umbrella.compose.ui.activities.elements.topics

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun UsefulFunctionsScreen() {
    ComposableWithRememberCoroutineScope()
}

/**
 * rememberCoroutineScope
 * LaunchedEffect используется для запуска корутин в Composable коде.
 * Но мы не можем использовать его в колбэках, типа onClick. Для таких случаев и нужна функция rememberCoroutineScope.
 * Она предоставляет нам scope, чтобы мы могли запустить корутину в колбэке.
 *
 * При входе в Composition (onRemembered) ему ничего не надо делать, он получил scope снаружи.
 * А вот при выходе из Composition (onForgotten) или при возникновении ошибок (onAbandoned) он отменяет scope.
 * Таким образом время жизни scope будет таким же, как время жизни Composable кода, где вызывается rememberCoroutineScope.
 * */

/**
 * Чекбокс включен по умолчанию. Внутри IF мы получаем scope от rememberCoroutineScope и используем его, чтобы запустить корутину по нажатию на текст.
 * Корутина считает секунды и выводит их в лог.
 * Что произойдет, когда мы выключим чекбокс? rememberCoroutineScope покинет Composition, а значит отменит scope, и, тем самым, и нашу корутину.
 * */

@Composable
fun ComposableWithRememberCoroutineScope() {
    Column {
        var checked by remember { mutableStateOf(true) }
        Checkbox(checked = checked, onCheckedChange = { checked = it })

        if (checked) {
            val scope = rememberCoroutineScope()
            Text(text = "Click", modifier = Modifier.clickable {
                scope.launch {
                    var count = 0
                    while (true) {
                        Log.d(TAG, "count = ${count++}")
                        delay(1000)
                    }
                }
            })
        }
    }
}

/**
 * produceState
 * Функция produceState создает для нас State и запускает корутину, в которой мы можем менять значение этого State
 *
 * awaitDispose
 * Функция awaitDispose может быть использована для выполнения каких-либо операций при отмене корутины.
 * Напомню, что LaunchedEffect отменит корутину, когда produceState покинет Composition.
 * Таким образом с помощью awaitDispose мы можем, например, отписаться от источника данных при закрытии экрана.
 * */

@Composable
fun ScreenWithProduceState(
) {
    val count by produceState(initialValue = 0) {
        while (true) {
            delay(1000)
            value++
            awaitDispose {
                //Do something to unsubscribe
            }
        }
    }

    Text(text = "count = $count")
}

/**
 * RememberUpdatedState
 * Функция rememberUpdatedState позволяет создать State из переменной или параметра функции. Это может пригодиться,
 * когда у нас в Composable функцию передается параметр, который периодически получает новое значение.
 * */

@Composable
fun ScreenWithRememberUpdateState(position: Float) {

    val positionState = rememberUpdatedState(newValue = position) // Делаем значение из параметра

    /**
     * Иначе было бы вот так
     *    val positionState = remember {
     *        mutableStateOf(position)
     *    }
     *    positionState.value = position
     * */

    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(1000)
            Log.d(TAG, "track position ${positionState.value}")
        }
    }
}