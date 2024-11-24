package com.umbrella.compose.ui.activities.elements.topics

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/** Под капотом используется Composer
 * Composer следит за состоянием UI, и каждый раз, когда данные (например, переменная или состояние) изменяются,
 * композитор автоматически обновляет соответствующие части интерфейса.
 *
 * Composer добавляет блоки startRestartGroup и endRestartGroup,
 * Эти методы начинают и заканчивают группу, которую можно представить как узел дерева, которое строит Compose.
 * То есть начало и конец функции — это начало и конец описания узла
 *
 * Так же в каждую composable функцию добавляется параметр final int $changed
 * Это просто число типа Int, которое является битовой картой, в которой биты отвечают за информацию об аргументах composable-функции, их изменении.
 * Если в родительской composable-функции изменились некоторые параметры, а некоторые остались прежние,
 * то информация о сравнении передаётся дочерним функциям через параметр $changed, чтобы они не делали лишних сравнений.
 * Сами функции сравнивают только те аргументы, в которых не уверен родитель, или если аргументы установлены по умолчанию.
 * */
@Composable
fun SomeComposableFunction() {
    Text(text = "Hello Compose")
}

@Composable
fun SomeComposableFunction2(items: List<Int>) {
    var checkedState = remember { mutableStateOf(true) }
    Row {
        Checkbox(checked = checkedState.value, onCheckedChange = { newChecked ->
            checked = newChecked
        })
        Text("Рекомпозиция через StateHosting ${items.first()}")
    }
}

/**
 * Убить весь смысл сравнения могут мутабельные аргументы — объекты, которые способны изменяться (изменять свои данные).
 * Чтобы решить эту проблему, разработчики Compose решили разделить все типы на стабильные и нестабильные.
 * Если все аргументы функции стабильные и не изменились, то рекомпозиция пропускается, иначе придётся перезапускать эту функцию снова.
 *
 * Функция, поддерживающая пропуски рекомпозиции, называется пропускаемой (skippable).
 * Мы должны стараться, чтобы почти все наши функции были пропускаемыми. Это очень хорошо повлияет на оптимизацию.
 **/

/**
 * Стабильными типами считаются:
 * Все примитивные типы и String.
 * Функциональные типы (лямбды) (поэтому понятие «нестабильные лямбды» не совсем корректно, но об этом ниже).
 * Классы, у которых все поля стабильного типа и объявлены как val, в том числе и sealed-классы. Стабильность полей класса проверяется рекурсивно, пока не найдётся тип, о стабильности которого уже однозначно известно.
 * Enum (даже если вы у него укажите поле var и будете его менять).
 * Типы, помеченные @Immutable или @Stable.
 *
 * Compose считает НЕстабильными:
 * Классы, у которых хотя бы одно поле нестабильного типа или объявлено как var.
 * Все классы из внешних модулей и библиотек, в которых нет компилятора Compose (List, Set, Map и прочие коллекции, LocalDate, LocalTime, Flow...);
 *
 * @Immutable и @Stable
 * Если вы уверены, что класс или интерфейс и все его потомки стабильные, то можете пометить их аннотацией @Immutable,
 * если они неизменны, или @Stable, если они могут меняться, но сами оповещают Compose о своём изменении. Например,
 * @Stable подойдёт, если в классе есть поле типа State<T> или MutableState<T> (mutableStateOf() создаёт такой объект).
 *
 * Immutable — это аннотация, указывающая, что объект является неизменяемым. То есть его состояние не меняется после создания.
 * Обычно, если вы помечаете класс или тип как Immutable, это означает, что все его свойства также неизменяемы, и объекты этого типа не могут быть изменены после создани
 * Указывая, что объект неизменяем, вы даете композитору гарантии, что не будет изменений, влияющих на UI, и это может помочь Compose избежать ненужных перерасчетов и пересозданий компонентов. Если объект неизменяем, композитор может более эффективно решать, когда обновлять UI.
 *
 * Stable — это аннотация, указывающая, что объект может изменяться, но его свойства изменяются предсказуемым образом.
 * В отличие от Immutable, объекты с аннотацией Stable могут изменяться, но композитор все равно может сделать выводы о том, как эти изменения влияют на UI.
 * Stable не гарантирует, что объект никогда не изменится, но гарантирует, что изменения можно отслеживать эффективно.
 * Это позволяет композитору делать оптимизации и избегать перерасчетов UI, если изменения в объекте происходят предсказуемым образом (например, только в определенных местах).
 * Stable также выполняет контракт из документации к этой аннотации:
 * 1)equals всегда будет возвращать одинаковое значение для одной и той же пары объектов. (Data class с val)
 * или
 * 2)Когда публичные поля типа изменяются, нужно оповестить об этом Compose. (State,
 * или
 * 3)Все публичные поля стабильны.
 * */

@Immutable
data class MyUiState1(val items: List<String>)

@Stable
data class MyUiState2(val timer: MutableState<Int>)

/**
 * Пропускаемость функций
 * Compose делает composable-функцию пропускаемой, только если все её аргументы стабильного типа и функция возвращает Unit
 *
 * @Composable
 * fun Header(text: String, $composer: Composer<*>, $changed: Int) {
 * 	if (/* Логика проверки на необходимость пропуска */) {
 * 		Text(text) // Выполняется тело функции
 * 	} else {
 * 		$composer.skipToGroupEnd() // Сообщаем Compose, что мы пропустили функцию
 * 	}
 * }
 *
 * Исходя из вышеперечисленного, мы в команде договорились помечать все UI-модели и состояния как @Immutable или @Stable,
 * так как изначально их проектируем таковыми. Особенно следим за стабильностью при разработке UI kit-проекта, так как цена ошибки становится выше.
 * Чтобы проверить стабильность типов, вы можете использовать метрики Compose (к ним вернёмся в конце статьи).
 * */

/**
 * Лямбды
 *
 * @Composable
 * fun MyScreen() {
 * 	val viewModel = remember { MyViewModel() }
 * 	val state by viewModel.state.collectAsState()
 *
 * 	MyComposableItem(
 * 		name = state.name,
 * 		onButtonClick = { viewModel.onAction() }
 * 	)
 * }
 *
 * Compose делит их на non-composable, в которых не выполняется composable-код, и composable соответственно. Рассмотрим подробно первый тип.
 * Non-composable лямбды, которые создаются в composable-функции, при компиляции оборачиваются в remember. Все захваченные переменные кладутся в качестве ключа для remember:
 *
 * Как решить эту проблему? Раньше работало использование ссылки на метод (viewModel::onAction), но начиная с Compose 1.4 перестало работать из-за использования сравнения по ссылке вместо кастомного equals
 *
 * Есть разные способы обхода этой проблемы, но самый стабильный - положить лямбу в remember
 * Запоминать лямбду самим (при этом ключ должен и сам не меняться при каждой рекомпозиции):
 * val onAction = remember { { viewModel.onAction() } }
 *
 *
 * */

/**
 * todo Перезапускаемость и пропускаемость
 * todo DerivedStateOf
 * */

/**
 * Отложенное чтение состояния
 * Откладывать чтение можно с помощью ЛЯМБДЫ(Start Android про это не говорил) ИЛИ передачи State и чтения его в нужном месте
 * @Composable
 * fun MyComposable1() {
 * 	val scrollState = rememberScrollState()
 * 	val counter = remember { mutableStateOf(0) }
 *
 * 	MyList(scrollState)
 * 	MyComposable2(counter1, { scrollState.value })
 * }
 *
 * @Composable
 * fun MyComposable2(counter: State<Int>, scrollProvider: () -> Int) {
 * 	// Чтение состояния в MyComposable2
 * 	Text(text = "My counter = ${counter.value}")
 * 	Text(text = "My scroll = ${scrollProvider()}")
 * }
 * */

/**
 * Отрисовка
 * Использование drawBehind { color = state.value} вместо background убериет лишние фазы рекомпозиции
 * */

/**
 * Умеренное уменьшение области рекомпозиции
 * Выносим рекомпозируемые элементы в отдельные функции, но без перебора.
 * Не нужно выносить дивайдер
 * */