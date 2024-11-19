package com.umbrella.compose

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue

/**
 *  LaunchedEffect - позволяет запустить suspend функцию внутри composable функции
 *  на этом построены анимации
 *  Рекомпозиция не влияет на работу Suspend функции
 *  key1 = это ключ для рекомпозиции, обрати внимание что есть перегрузки с разным количеством keys
 *  LaunchEffect перезапускается только когда изменится его ключ и наоборот рекомпозиция не будет работать со статическим ключем
 * */

@Composable
fun ComponentWithLaunchEffect(count: Int) {
    Text("count = $count")

    LaunchedEffect(key1 = Unit) { someSuspendFunction() }

    /**
     * rememberUpdatedState нужен для доступа корутины к актуальному значению
     * Это гарантирует, что если count изменится, корутина будет работать с актуальным значением,
     * даже если компонент был пересоздан.
     * */

    val currentCount = rememberUpdatedState(count)

    LaunchedEffect(key1 = currentCount.value) { someSuspendFunction() }
}

suspend fun someSuspendFunction() {

}

/**
 * Disposable Side Effect в Compose позволяет вам выполнять операции, которые требуют "очистки",
 * такие как отмена подписки на данные, удаление наблюдателей или другие ресурсоемкие операции,
 * которые нужно отменить, когда UI-компонент перестает существовать или обновляется.
 * */

@Composable
fun ComponentWithDisposable() {
    // Реализация компонента

    // Операции, которые требуют очистки
    DisposableEffect(key1 = Unit) {
        // Эта операция будет выполнена при первом запуске Composable
        val subscription = subscribeToDataUpdates()

        // Возвращаем функцию очистки, которая будет вызвана при уничтожении Compose компонента
        onDispose {
            subscription.unsubscribe()
        }
    }
}

fun subscribeToDataUpdates(): DataSubscription {
    return DataSubscription()
}

class DataSubscription {
    fun unsubscribe() {
        // Логика отписки
    }
}

/** SideEffect
 * Не позволяет работать с асинхронными задачами или выполнять операции с ресурсами, которые требуют очистки.
 * Выполнится последним действием сразу после рекомпозиции. Втч при первой рекомпозиции, но последним. Гарантируя, что все изменения состояния и UI завершены
 *
 * Используется для выполнения побочных эффектов, таких как логирование, изменение состояния или работы с UI
 * (например, запуск анимаций) в ответ на изменения состояния, но без необходимости управления корутинами или ресурсами.
 * */

@Composable
fun MyComponent() {
    var count by remember { mutableStateOf(0) }

    // Вызовится каждый раз при изменении значения count
    SideEffect {
        println("Count updated: $count")
    }

    Button(onClick = { count++ }) {
        Text("Increment")
    }
}
