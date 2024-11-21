package com.umbrella.compose.ui.activities.elements.topics

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Мы уже знаем, что наши Composable функции могут быть вызваны системой несколько раз, и не всегда мы можем точно предсказать эти вызовы.
 * Поэтому не рекомендуется в Composable коде выполнять какие-то серьезные действия типа сетевых вызовов, навигации, и т.п.
 * Нет никаких гарантий, что эти действия будут выполнены только один раз при отображении экрана.
 *
 * Для решения этой проблемы Compose предоставляет нам SideEffects функции, которые позволяют получить некоторую стабильность от Composable функций.
 *
 * */

@Composable
fun EffectsScreen() {
    ComponentWithLaunchEffect()
}

/**
 *  LaunchedEffect - позволяет запустить suspend функцию внутри composable функции
 *  на этом построены анимации
 *  Рекомпозиция не влияет на работу Suspend функции
 *  key1 = это ключ для рекомпозиции, обрати внимание что есть перегрузки с разным количеством keys
 *  LaunchEffect перезапускается только когда изменится его ключ и наоборот рекомпозиция не будет работать со статическим ключем
 *
 *  Открываем исходники класса LaunchedEffectImpl и видим, что он реализует RememberObserver.
 *  Если класс реализует интерфейс RememberObserver, то он будет в курсе о lifecycle этапах Composable кода, где был вызван remember с этим классом
 *  В onRemembered он стартует корутину, а в onForgotten - отменяет. Т
 *  аким образом LaunchedEffect запускает корутину, время жизни которой такое же, как и у Composable кода, где был вызван LaunchedEffect.
 *
 * !!!
 * Не забывайте, что хоть LaunchedEffect и предоставляет нам корутину, но никто не мешает нам вызвать в ней обычный, не suspend код,
 * например - выполнить навигацию. Главное тут то, что код в LaunchedEffect будет выполнен один раз и избежит повторных выполнений при Recompose.
 * */

@Composable
fun ComponentWithLaunchEffect() {
    Column {
        var checked by remember { mutableStateOf(false) }
        Checkbox(checked = checked, onCheckedChange = { checked = it })
        if (checked) {
            LaunchedEffect(key1 = Unit) {
                var count = 0
                while (true) {
                    Log.d(TAG, "count = ${count++}")
                    delay(1000)
                }
            }
        }
    }
}

/**
 * key
 * Функция LaunchedEffect на вход просит key. По исходникам видно, что этот key из LaunchedEffect передается в remember. Что это дает?
 * При смене значения key, remember вызовет onForgotten для текущего объекта, создаст новый объект и вызовет у него onRemembered.
 * Т.е. как будто мы убрали remember из Composition и поместили обратно. Таким образом старая корутина в LaunchedEffectImpl отменится, а новая стартует.
 * Это может быть полезно, если мы извне получили новое значение и хотим заново стартовать корутину.
 * Если вам такая опция не нужна, то просто передавайте в качестве key значение true, Unit, null или т.п.
 * */

/**
 * DisposableEffect
 * Функция DisposableEffect так же, как и LaunchedEffect, позволяет выполнить код один раз при первом вызове Composable кода.
 * Но есть пара отличий. Код будет выполнен не в корутине. И у нас есть возможность повесить свой колбэк на onForgotten.
 * Т.е. эта функция подходит, когда нам нужно подписаться на что-либо при старте экрана, а при закрытии - отписаться.
 * Под капотом работает класс DisposableEffectImpl:
 * */

@Composable
fun ComposableWIthDisposableEffect() {
    DisposableEffect(key1 = Unit) {
        // В основном блоке мы пишем код, который будет выполнен один раз при первом вызове этой функции, т.е. когда DisposableEffect войдет в Composition.
        onDispose {
            //А в onDispose пишем код, который будет выполнен, когда DisposableEffect покинет Composition.
        }
    }
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
    @Composable
    fun HomeScreen() {
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
}