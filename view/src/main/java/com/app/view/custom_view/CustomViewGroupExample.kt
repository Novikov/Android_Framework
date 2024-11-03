package com.app.view.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * View это некоторый кирпичик нашего UI, а ViewGroup это некий контейнер для этих кирпичиков
 * Чаще всего это невидимый контейнер который располагает в себе другие виджеты
 * Обрабатывает Layout своих детей
 * Диспетчеризирует тачи своих детей
 *
 * Алгоритм анализа требований к CustomViewGroup и как ее реализовать можно посмотреть тут https://youtu.be/DoqIbMydyJQ?feature=shared&t=2031
 * */
class CustomViewGroupExample@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr){


}

/**
/**
 * FrameLayout
 * 1 атрибут layout_gravity - gravity ребенка относительно контейнера
 * 1 measure pass
 *
 * LinearLayout
 * 3 атрибута:
 * layout_gravity
 * layout_weight - вес ребенка
 * orientation
 *
 * Количество measure pass
 * 1 - без использования layout weight
 * 2 - с использованием layout weight (Считаем желаемый размер вью и оставшееся значение которое нужно буедт поделить на количество View и добавить им)
 *
 * RelativeLayout
 * атрибуты это относительные позиции View относительно других с помощью атрибутов layout_align/to/center итд.
 *
 * Количество measure pass - 2
 *
 * ConstraintLayout - более продвинутый вариант RelativeLayout
 * Количество measure pass - 1,2 в зависимости от сложности связей
 * */
 * */