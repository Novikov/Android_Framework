package com.umbrella.compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

/**
 * animateDpAsState внутри свойства modifier
 * animatedVisibility
 * */

/**
 *  LaunchedEffect - позволяет запустить suspend функцию внутри composable функции
 *  на этом построены анимации
 *  Рекомпозиция не влияет на работу Suspend функции
 *  key1 = это ключ по которому можно запустить другую корутину если что то изменится
 *  LaunchEffect перезапускается только когда изменится его ключ
 * */

@Composable
fun DynamicComponent(count: Int) {
    Text("count = $count")

    LaunchedEffect(key1 = Unit) { someSuspendFunction() }
}

suspend fun someSuspendFunction() {

}
