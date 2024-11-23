package com.umbrella.compose.ui.activities.elements.topics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun DrawingApp() {
    var points by remember { mutableStateOf(listOf<Pair<Float, Float>>()) }

    // Canvas для рисования
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) { // Для перехвата касаний пользователя
                detectTapGestures { offset ->
                    // Добавляем точки для рисования
                    points = points + Pair(offset.x, offset.y)
                }
            }
    ) {
        points.forEach { point ->
            drawCircle(
                color = Color.Black,
                radius = 25f,
                center = androidx.compose.ui.geometry.Offset(point.first, point.second)
            )
        }
    }

    // Кнопка для очистки экрана
    Button(onClick = { points = listOf() }) {
        Text("Clear")
    }
}