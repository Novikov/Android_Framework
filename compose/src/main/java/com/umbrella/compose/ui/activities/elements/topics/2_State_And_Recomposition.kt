package com.umbrella.compose.ui.activities.elements.topics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
 * Причину рекомпозиции можно увидеть в debugger в cвойстве Recomposition State
 * */

@Composable
fun StateHostingScreen() {
    Column {
        CheckBoxRecompositionExample1()
        CheckBoxRecompositionExample2()
        CheckBoxRecompositionExample3()
        CheckBoxRecompositionExample4()

        CounterContentWithoutStateHosting()

        val counter =  mutableStateOf(0)
        CounterContentWithStateHosting(counter)

        CounterContentAtomicCheck()
    }
}



/**
 * Первый способ вызвать рекомпозицию функции через currentRecomposeScope
 * Мы должны описывать как ComposeView должен менять состояние
 * Состояние ComposeView изменится после перевызова функции
 * Чтобы этого добиться нужно сделать scope.invalidate()
 * */
var checked = false // стейт должен находиться вне функции.

@Composable
fun CheckBoxRecompositionExample1() {
    val scope = currentRecomposeScope
    Row { Checkbox(checked = checked, onCheckedChange = { newChecked ->
        checked = newChecked
        scope.invalidate()
    })
        Text("Рекомпозиция через currentRecomposeScope")
    }
}

/**
 * MytableState может сообщить о том что его прочитали или его значение изменилось
 * Это позволяет уйти от currentRecomposeScope
 * Функция будет перевызываться автоматически и отображать новое состояние
 * Не нужно делать ручные инвалидации
 *
 * функция подписывается не на значение State, а на сам факт, что значение изменилось.
 * Т.е. когда значение State меняется, функция получает не новое значение State, а просто уведомление о том,
 * что State изменился.
 * И далее уже функция перезапускает себя, чтобы считать из State это новое значение.
 *
 *
 * StateHosting означает  что состояние (state) UI элементов и данных хранится в одном месте (обычно в более высоком уровне компонента)
 * и передается вниз по иерархии компонентов через параметры.
 * Это помогает разделить логику приложения и его представление,
 * а также избежать дублирования состояния в разных частях приложения.
 *
 * */
var checkedState = mutableStateOf(true) // это state hosting

@Composable
fun CheckBoxRecompositionExample2() {
    Row {
        Checkbox(checked = checkedState.value, onCheckedChange = { newChecked ->
            checked = newChecked
        })
        Text("Рекомпозиция через StateHosting")
    }
}

/**
 * Но что если у нас несколько checkbox каждый из которых имеет свое собственное состояние
 * Не получится закинуть mutableState прям в фунецию. Компилятор заставит обернуть его в remember
 *
 * Rememebr - composable функция которая может обращаться к памяти функции о предыдущих вызовах
 * Если таких стостояний нет то remember создаст стейт. Отсюда и название remember.
 * */

@Composable
fun CheckBoxRecompositionExample3() {
    var checkedState: Boolean by remember { mutableStateOf(true) } // не переживает изменение конфигурации, использование нежелательно
    //val checkedState2 by rememberSaveable { mutableStateOf(true) } // переживает изменение конфигурации, но более ресурсоемкий. если данные можно восстановить другим способом (например из ViiewModel) то лучше не использовать
    Row {
        Checkbox(checked = checkedState, onCheckedChange = { newChecked ->
            checked = newChecked
        })
        /** Если в этой функции будут дополнительные ComposeView, которые не зависят от checkedState
         * Их рекомпозиция будет пропускаться
         * */
        Text(text = "Не переживет изменение конфигурации")
    }
}

/** rememberSaveable*/
@Composable
fun CheckBoxRecompositionExample4() {
    val checkedState2 by rememberSaveable { mutableStateOf(true) } // переживает изменение конфигурации
    Row {
        Checkbox(checked = checkedState2, onCheckedChange = { newChecked ->
            checked = newChecked
        })
        /** Если в этой функции будут дополнительные ComposeView, которые не зависят от checkedState
         * Их рекомпозиция будет пропускаться
         * */
        Text(text = "Переживет изменение конфигурации")
    }
}

/**
 * Счетчик не будет увеличиваться потому что стейт будет пересоздаваться на каждую рекомпозицию
 * Рекомпозироваться будет только button потмоу что он меняет стейт
 * Text читает стейт, но он не меняется
 * */
@Composable
fun CounterContentWithoutStateHosting() {
    val count =  mutableStateOf(0)
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Счётчик: ${count.value}", style = androidx.compose.material3.MaterialTheme.typography.bodyLarge)

        // Кнопка для увеличения значения счётчика
        Button(onClick = { count.value++ }) {
            Text(text = "Увеличить")
        }
    }
}

/** StateLess view или statehosting
 * теперь стейт не будет пересоздаваться, а значит счетчик будет обновляться и рекомпозиция будет работать и для кнопки и для счетчика*/
@Composable
fun CounterContentWithStateHosting(count: MutableState<Int>){
    Column(modifier = Modifier.padding(16.dp)) {
        // Отображаем текущее значение счетчика
        Text(text = "Счётчик: ${count.value}", style = androidx.compose.material3.MaterialTheme.typography.bodyLarge)

        // Кнопка для увеличения значения счётчика
        Button(onClick = { count.value++ }) {
            Text(text = "Увеличить")
        }
    }
}

/**
 * StateFull vs StateLess Composable function
 * Statefull - если внутри есть состояние на которое она подписана
 * StateLess - на вход состояние, например Bolean, и callback по изменению состояния. Предпочтительный способ. Можем переиспользовать компонент
 * */


@Composable
fun CounterContentAtomicCheck(){
    var counter by remember { mutableStateOf(0) }
    Column(modifier = Modifier.padding(16.dp)) {
            LaunchedEffect(Unit) {
                    launch {
                        repeat(100000) {
                            counter += 1
                        }
                    }
                    launch(Dispatchers.Default) {
                        repeat(100000) {
                            counter += 1
                        }
                    }

        }
        Text(text = "Counter: $counter")
    }
}
