package com.umbrella.compose.ui.activities.elements.topics

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

/**
 * Composition Local
 * Composition Local в Android Jetpack Compose — это механизм, который позволяет вам передавать значения
 * или зависимости вниз по дереву компонентов (Composable) без явного использования параметров для каждого компонента.
 *
 * Без CompositionLocal пришлось бы передавать все параметры
 * */

val LocalGreeting = compositionLocalOf { "Hello" }

@Composable
fun CompositionLocalScreen() {
    CompositionLocalProvider(LocalGreeting provides "Hi there!") {
        GreetingMessage()
    }
}

@Composable
fun GreetingMessage() {
    NestedGreetingMessage()
}

@Composable
fun NestedGreetingMessage(){
    // Получаем значение из LocalGreeting
    val greeting = LocalGreeting.current
    Text(text = greeting)
}