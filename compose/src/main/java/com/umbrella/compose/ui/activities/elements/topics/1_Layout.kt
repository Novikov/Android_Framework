package com.umbrella.compose.ui.activities.elements.topics

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.umbrella.compose.R

@Composable
fun LayoutScreen() {
    MyConstraintLayout()
}

@Composable
fun MyConstraintLayout() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        // Создаем 3 переменные для элементов, которые будут располагаться на экране
        val (title, button, image) = createRefs()

        // Заголовок
        Text(
            text = "Welcome to ConstraintLayout",
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            style = MaterialTheme.typography.headlineMedium
        )

        // Кнопка
        Button(
            onClick = { /* Действие при клике */ },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(title.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = "Click Me")
        }

        // Изображение
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo",
            modifier = Modifier.constrainAs(image) {
                top.linkTo(button.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}