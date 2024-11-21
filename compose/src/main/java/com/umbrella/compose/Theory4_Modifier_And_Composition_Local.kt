package com.umbrella.compose


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Modifiers применяются снизу вверх или справа налево
 *
 * Параметры VS Modifier
 * Итак, мы выяснили, что часть атрибутов элемента задается параметрами функции, а часть - с помощью Modifier. В этом есть определенная логика. Можно сформулировать правило, которое позволит понять, где искать нужный нам атрибут.
 * Если мы хотим задать атрибут, специфичный для элемента, то он будет в параметрах функции. Примеры: text и fontSize. Такие атрибуты встречаются далеко не у всех элементов. Они специфичны для элемента Text.
 * А если нам надо задать атрибут, который можно применить практически к любому элементу, то они будут в Modifier. Примеры: width и background.
 *
 * Принцип разделения параметров
 * Параметры, передаваемые через Modifier
 * Modifier используется для всех параметров, которые касаются размера, положения, отступов и взаимодействия с другими элементами интерфейса. Это такие аспекты, как:
 *
 * Размер (width, height, size).
 * Позиционирование (padding, offset, align).
 * Границы и фоны (background, border, clip).
 * Жесты (clickable, pointerInput).
 * Тени и эффекты (shadow).
 * Основная идея здесь в том, что Modifier позволяет декорировать или модифицировать внешний вид и поведение компонента без изменения его логики или содержания. Это позволяет использовать одну и ту же composable функцию для разных стилей, размеров и эффектов, минимизируя количество повторений кода.
 *
 * Например, modifier = Modifier.padding(16.dp) или modifier = Modifier.fillMaxWidth().
 *
 * Параметры, передаваемые как параметры функции Composable
 * Параметры, передаваемые непосредственно как аргументы в composable функцию, касаются содержания компонента и его внешнего вида. Это могут быть:
 *
 * Текстовое содержимое (text в Text).
 * Шрифт (fontFamily, fontWeight, fontSize и другие параметры для определения шрифта).
 * Цвет (color).
 * Стиль (например, style в Text).
 * Обработчики событий (например, onClick для обработки взаимодействий).
 * Эти параметры влияют непосредственно на то, как компонент будет выглядеть или вести себя в своей внутренней логике, и вносят изменения в его визуальный стиль или поведение в ответ на пользовательские действия.
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


/**
 * Composition Local
 * Composition Local в Android Jetpack Compose — это механизм, который позволяет вам передавать значения
 * или зависимости вниз по дереву компонентов (Composable) без явного использования параметров для каждого компонента.
 *
 * Без CompositionLocal пришлось бы передавать все параметры
 * */


val LocalGreeting = compositionLocalOf { "Hello" }

@Composable
fun GreetingScreen() {
    CompositionLocalProvider(LocalGreeting provides "Hi there!") {
        GreetingMessage()
    }
}

@Composable
fun GreetingMessage() {
    // Получаем значение из LocalGreeting
    val greeting = LocalGreeting.current
    Text(text = greeting)
}