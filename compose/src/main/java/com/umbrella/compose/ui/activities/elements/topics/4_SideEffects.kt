package com.umbrella.compose.ui.activities.elements.topics

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

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
 *  LaunchedEffect - позволяет запустить suspend функцию внутри composable функции при старте composable функции (но не колбэках).
 *  Запускается только внутри Composable
 *  С его помощью запускают анимации
 *  Рекомпозиция не влияет на работу Suspend функции, т.е функция будет выполнена один раз
 *  key1 = это ключ для перевызова LaunchEffect, обрати внимание что есть перегрузки с разным количеством keys
 *  LaunchEffect перезапускается только когда изменится его ключ и наоборот перевызов LE не будет работать со статическим ключем
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
                // тут можно вызывать как suspend, так и не suspend код
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
 * Функция SideEffect гарантирует, что код будет выполнен только в случае успешного выполнения Composable кода.
 * Если же что-то пошло не так, что SideEffect не выполнится.
 * Чел пишет что не нашел способа получить ошибку в Composable кроме как бросить исключение
 *
 * Не позволяет работать с асинхронными задачами или выполнять операции с ресурсами, которые требуют очистки.
 * Выполнится последним действием сразу после рекомпозиции. Втч при первой рекомпозиции, но последним. Гарантируя, что все изменения состояния и UI завершены
 *
 * Используется для выполнения побочных эффектов, таких как логирование, изменение состояния или работы с UI
 * (например, запуск анимаций) в ответ на изменения состояния, но без необходимости управления корутинами или ресурсами.
 * */

@Composable
fun ExampleWithSideEffect() {
    Column {
        var checked by remember { mutableStateOf(false) }
        Checkbox(checked = checked, onCheckedChange = { checked = it })
        if (checked) {
            Log.d(TAG, "HomeScreen log")
            SideEffect {
                Log.d(TAG, "HomeScreen log in SideEffect")
            }
           // val a = 1 / 0
        }
    }
}