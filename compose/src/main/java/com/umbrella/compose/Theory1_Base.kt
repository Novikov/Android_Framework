package com.umbrella.compose

/**
 Compose - декларативный UI Framework где мы пишем UI при помощи Composable функций на Kotlin
 Декларативный - мы пишем что мы хотим, а не как.
 Unidirectional Data Flow подход построения архитектуры который позволяет упростить управление состоянием UI
 Его можно ораганизовать и без Compose, но Compose упрощает процесс построения UDF

 UDF в UI
 UI генерит эвенты которые меняют стейт и подписывается на его изменения через observer паттерн

 Jetpack Compose is a modern, declarative UI toolkit for Android development. Its core principles are:
 1. Unidirectional data flow: Ensures consistent state management and simplifies UI logic.
 2. Declarative syntax: Expresses UI components concisely, making code more readable and maintainable.
 3. Reusability: Encourages modular, reusable components that can be easily shared across projects.
 4. Kotlin-first: Leverages Kotlin’s language features to provide a seamless developer experience.

 Зачем? вынести UI фрамеворк в отдельную библиотеку и не задерживать релизный цикл

 UI дерево - любой ui состоит из деревьев
 ViewGroup
  -ViewGroup
    -View
    -View
  -View

  Проблемы View todo Прорарботать
  View не были созданы для того чтобы создавать их из кода - слишком много кода
  Для динамического обновления View нужно создавать доп методы
  Для statefull view у нас есть 2 состояния в самом view и во ViewModel
  Это создает проблемы:
  1)Дублирования состояния
  2)Возможная неконсистентность состояния если синхронизация не настоена должным образом

  Compose заставляет думать о:
  -Какой UI отобразить
  -Как отреагировать на события

  И избавляет нас от ответственности думать о том как изменить UI при изменении состояния

  Composable функции
  1. Не может быть вызвана из обычных функций
  2. Так же как и в случае с suspend под копотом генерится магия
  3. Данные для UI передаем только в параметрах
  4. Вызываются на UI, а значит должны быть очень быстрые. Все вычислени нужно уводить на UI поток
  5. Идемпотентнаяя. Если на вход передали одинаковые параметры то она должна вести себя предсказуемо одинаково. Должна рисовать тот же UI

  Compose функции пишутся с большой буквы потому что это сущности

  3 конейнера для группировки и все они практически одинаковы по производительности и нет различий как у ViewGroup
  Column/Row - LinearLayout
  Box - Framelayout
  ConstraintLayout так же был адаптирован для Compose todo Реализовать

 Compose работает в 3 фазы
 1)Composition - построение UI дерева
 2)Layouting
 -Измерение
 -Встраивание
 -Все UI элементы измеряются и встраиваются в местоположение
 -Можно оптимизировать и ибрать лишние вызовы спомощью drawBehind оператора у модификатора
 3)Drawing - отрисовка на canvas

 Отсутствует проблема measure для высокой вложенности ViewGroup

 Compose UI Tree structure:
 Window — это корневой контейнер для отображения всего экрана приложения. В Android приложение всегда привязано к Window, но в Compose это более абстрактный процесс, скрытый внутри setContent.
 DecorView — это контейнер, который уже работает на уровне Android и управляет всем UI. В контексте Compose он инкапсулирует все элементы UI и работает с ними как с деревом компонентов.
 ComposeRoot — это объект, создаваемый системой Compose, который начинает процесс компоновки UI. Он управляет состоянием всех последующих композируемых функций и передает их в систему рендеринга.
 LayoutNode - Compose View

 CustomView создается с помощью Composable функций и базовых строительных блоков Compose
 Slot Api используется для модификации CustomView из вне (передача контента заполнения). Вообщем это возможность для расширения компонентапользователям компонента
 * */