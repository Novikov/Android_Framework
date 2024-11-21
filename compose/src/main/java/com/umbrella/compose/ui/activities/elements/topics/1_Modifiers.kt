package com.umbrella.compose.ui.activities.elements.topics

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Modifiers применяются снизу вверх или справа налево
 *
 * Параметры VS Modifier
 * Итак, мы выяснили, что часть атрибутов элемента задается параметрами функции, а часть - с помощью Modifier. В этом есть определенная логика. Можно сформулировать правило, которое позволит понять, где искать нужный нам атрибут.
 * Если мы хотим задать атрибут, специфичный для элемента, то он будет в параметрах функции. Примеры: text и fontSize. Такие атрибуты встречаются далеко не у всех элементов. Они специфичны для элемента Text.
 * А если нам надо задать атрибут, который можно применить практически к любому элементу, то они будут в Modifier. Примеры: width и background.
 **/

@Composable
fun ModifiersScreen(){
    Column {
        ModifiersExample1()
        ModifiersExample2()
    }
}

@Preview
@Composable
private fun ModifiersExample1() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.Blue)
            .size(150.dp)
            .border(5.dp, Color.Red)
    ){}
}

@Preview
@Composable
private fun ModifiersExample2() {
    Column(
        modifier = Modifier
            .size(100.dp)
            .border(5.dp, Color.Red)
            .padding(16.dp)
            .background(color = Color.Blue)
            .size(150.dp)
    ){}
}