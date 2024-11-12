package com.app.view.custom_view

/**
 * Motion event
 * типы
 *
 * View.onTouchEvent(MotionEvent): Boolean - Определяет обработку события жеста у View
 *
 * ViewGroup.onInterceptTouchEvent(MotionEvent): Boolean - Позволяет ViewGroup отслеживать все события жестов,
 * происходящие на ее поверхности (в т.ч детей), и перехватить их в случае необходимости
 *
 * Boolean параметр отвечает за флаг перехвата. Если true то View или ViewGroup перехватила событие и не отправляет его вниз по иерархии
 * Как это работает можно посмотреть тут https://youtu.be/DoqIbMydyJQ?feature=shared&t=3693
 * 2 метода:
 * onIntercept определяет будем ли мы спускаться ниже.
 * onTouchEvent идем вверх по иерархии пока кто то не обработает его.
 *
 * Так же существует класс GestureDetector
 * */