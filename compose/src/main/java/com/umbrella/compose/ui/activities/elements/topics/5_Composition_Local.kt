package com.umbrella.compose.ui.activities.elements.topics

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontStyle

/**
 * Composition Local
 * Composition Local в Android Jetpack Compose — это механизм, который позволяет вам передавать значения
 * или зависимости вниз по дереву компонентов (Composable) без явного использования параметров для каждого компонента.
 *
 * Без CompositionLocal пришлось бы передавать все параметры
 *
 * Но сразу явно проговорю, что такая возможность не означает, что все данные надо передавать именно таким путем.
 * Это должно быть скорее исключением. Самый распространенный пример использования CompositionLocal - это дизайн.
 * Мы в корневой функции задаем цвета, размеры и т.п. И любая функция сможет их считать и использовать.
 *
 * CompositionLocal хранилище похоже на Map
 *
 * Значение по умолчанию будет использовано, если мы попытаемся извлечь значение из хранилище, ничего туда не поместив.
 * Обратите внимание на имя переменной. Для CompositionLocal ключей рекомендуется использовать префикс Local
 * */

val LocalGreeting = compositionLocalOf { "Hello" } //LocalGreeting это ключ, а Hello это значение

@Composable
fun CompositionLocalScreen() {
//    val infixCompositionLocal = LocalGreeting provides "Infix provides" // меняем значение по умолчанию черепз инфиксную запись
//    CompositionLocalProvider(LocalGreeting.provides ("Hi there!")) { //Меняем значение по умолчанию обычным способом и прокидываем ниже
//        GreetingMessage()
//    }

    ExampleWithCompositionLocal()
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

/**
 * staticCompositionLocalOf
 * В compositionLocalOf можно класть переменные которые являются результатом вычисления выражения val someVariable = if()...
 * При изменении значения у нас будет происходить рекомпозиция только тех узлов которые читают state из compositionLocal
 * Если мы хотим рекомпозицию всех функций внутри CompositionLocalProvider то необходимо использовать staticCompositionLocalOf
 * */
// val LocalFontStyle = compositionLocalOf { FontStyle.Normal }
val LocalFontStyle = staticCompositionLocalOf  { FontStyle.Normal }


@Composable
fun ExampleWithCompositionLocal() {
    Column {
        val italicState = remember { mutableStateOf(false) }
        MyCheckbox("Italic", italicState)

        Log.d("TAG", "HomeScreen ${italicState.value}")

        val fontStyle = if (italicState.value) FontStyle.Italic else FontStyle.Normal
        CompositionLocalProvider(LocalFontStyle provides fontStyle) { // через запятую можно перечислять множество значений
            MyText(text = "Text")
            Test()
        }
    }
}

@Composable
fun MyText(
    text: String,
) {
    Log.d("TAG", "MyText")
    Text(text = text, fontStyle = LocalFontStyle.current)
}

@Composable
fun Test() {
    Log.d("TAG", "Test")
    Text(text = "Test")
}

@Composable
fun MyCheckbox(
    text: String,
    checked: MutableState<Boolean>,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked.value, onCheckedChange = { checked.value = it })
        Text(text = text)
    }
}