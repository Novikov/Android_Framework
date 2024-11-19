package com.umbrella.compose

import androidx.compose.material.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

/**
Изменение состояния

Концепция UDF (Unidirectional Data Flow) Однонаправленный поток данных
Есть общий state, на который подписаны UI
UI генерит эвенты которые могут изменить стейт
State обновляется и посылает обновления для UI
 * */


/**
 * Мы должны описывать как ComposeView должен менять состояние
 * Состояние ComposeView изменится после перевызова функции
 * Чтобы этого добиться нужно сделать scope.invalidate()
 * Не самое элегантное решение
 * */
var checked = false // стейт должен находиться вне функции.

@Composable
fun MyCheckBox() {
    val scope = currentRecomposeScope
    Checkbox(checked = checked, onCheckedChange = { newChecked ->
        checked = newChecked
        scope.invalidate()
    })
}

/**
 * MytableState может сообщить о том что его прочитали или его значение изменилось
 * Это позволяет уйти от currentRecomposeScope
 * Функция будет перевызываться автоматически и отображать новое состояние
 * Не нужно делать ручные инвалидации
 * */
var checkedState = mutableStateOf(true)

@Composable
fun MyCheckBox2() {
    Checkbox(checked = checkedState.value, onCheckedChange = { newChecked ->
        checked = newChecked
    })
}

/**
 * Но что если у нас несколько checkbox каждый из которых имеет свое собственное состояние
 * Не получится закинуть mutableState прям в фунецию. Компилятор заставит обернуть его в remember
 *
 * Rememebr - composable функция которая может обращаться к памяти функции о предыдущих вызовах
 * Если таких стостояний нет то remember создаст стейт. Отсюда и название remember.
 * */

@Composable
fun MyCheckBox3() {
    var checkedState: Boolean by remember { mutableStateOf(true) } // не переживает изменение конфигурации, использование нежелательно
     val checkedState2 by rememberSaveable { mutableStateOf(true) } // переживает изменение конфигурации
    //checkedState сделан как var delegate и его можно менять просто обращаясь к переменной и это будет работать
    Checkbox(checked = checkedState, onCheckedChange = { newChecked ->
        checked = newChecked
    })
    /** Если в этой функции будут дополнительные ComposeView, которые не зависят от checkedState
     * Их рекомпозиция будет пропускаться
     * */
    Text(text = "Некоторый текст")
}

/**
 * Типы рекомпозиций
 *
 * 1)Initial composition
 * Функцию вызвали в первый раз
 *
 * 2)Direct recomposition
 * Прочитанный MutableState.value изменился и функция перевызвается
 *
 * 3)Indirect recomposition (неявная) - это про Text в MyCheckBox3
 * Родительская функция перевызвалась и изменила параметры этой функции
 *
 * Причину рекомпозиции можно увидеть в debugger в cсвойстве Recomposition State
 * */


/**
 * CompositionLocal
 * Стейт с типом Compose? todo Доработать
 * Это второй способ как передавать параметры в Compose функцию
 * Таким способом можно уйти от дублирования
 * */

val localContentColor = compositionLocalOf { Color.Unspecified }

@Composable
fun ParentComponent() {
    CompositionLocalProvider(localContentColor provides Color.Yellow) {
        Component()
    }
}

@Composable
fun Component() {
    //val currentColor = LocalContentColor.current
}

/**
 * State Hosting todo Доработать
 * Может возникнуть ситуация когда нам необходимо в ComposeView которые находятся на одном уровне
 * Иерархии (например элементы списка) - обновлять стейт сразу нескольких элементов
 * Например при выделении одного элемиента спискап нужно снимать выделение в предыдущем элементе
 *
 * В коде это выглядит следующим образом:
 *
 * https://www.youtube.com/live/RKMx8aj-q7Y?feature=shared&t=3162
 * */


/**
 * StateFull vs StateLess Composable function
 * Statefull - если внутри есть состояние на которое она подписана
 * StateLess - на вход состояние, например Bolean, и callback по изменению состояния. Предпочтительный способ. Можем переиспользовать компонент
 * */
