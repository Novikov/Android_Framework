package com.umbrella.compose


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Модификаторы применяются снизу вверх или справа налево
 * */

@Preview
@Composable
private fun Element() {
    Image(
        painter = painterResource(R.drawable.ic_baseline_insert_emoticon_24),
        contentDescription = "Contact profile picture",
        modifier = Modifier
            .padding(16.dp)
            .clickable { }
            .clip(CircleShape)
    )
}

/**
 * Размер
 * size()
 * fillMaxWidth()
 *
 * Сдвиг
 * offset - сдвиг без обрезки
 * clippToBounds - сдвиг с обрезкой
 *
 * Цвет
 * background
 * clip - форма
 * alpha
 * */

/**
 * Для каждой подсистемы UI есть свой Modifier
 * pointerInput
 * layout
 * drawbehind
 * graphicsLayer
 * onFocusEvent
 * semantics
 *
 * Для анимаций существуют аналоги операторов с лямбдой
 * Если есть лямбда значит это значение можно вынести за рекомпозицию т оптимизировать композизицию
 *
 * background > drawBehind
 * offset > offset
 * alpha > graphicsLayer
 * */