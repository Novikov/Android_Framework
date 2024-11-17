package com.umbrella.compose

/**
 * Как определить что юзер использует планшет и адаплировать для него layout и compose втч адаптация под Landscape
 *
 * -Вынос side эффектов в callback. Прочитать подробнее https://developer.android.com/jetpack/compose/mental-model
 *
 * -Что такое DSL в Kotlin.
 *
 * -Что такое weight в модификаторе column?
 *
 * -Черная тема. Может ли пользователь сам переключиться на нее или мы должны явно ее включить? Что закод внизу
 * https://developer.android.com/codelabs/jetpack-compose-basics?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fjetpack-compose-for-android-developers-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-basics#11
 *
 * -Есть техника определения modifier в ancestor методе. Правильно ли я понимаю что он пробросится во все descent composable, а там где необходимо ппереопределится?
 *
 * -Почему в какие то элемнты передается парент модифаер, а в другие нет?
 *
 * - paddinggFromBaseline()
 * ---
 * зачем по дереву передается modifier, когда он уже есть у элементов как дефолтный параметр?
 * */