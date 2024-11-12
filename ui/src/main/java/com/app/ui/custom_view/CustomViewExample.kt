package com.app.ui.custom_view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * View represents the basic building block for user interface components
 *
 * Size:
 * Match_parent - полностью занять размерность parent за исключение паддингов parent
 * Wrap_content - занять ровно столько сколько требуется View для отрисовки контента
 * 16dp - занять ровно 16dp
 * 16sp - занять 16sp
 * 16px - занять 16 пикселей
 * 0dp - специальное значение некоторых ViewGroup(LinearLayout, ConstraintLayout)
 *
 * View это прямоугольник
 * Координаты Start и End появились для локализации текста языков которые чистаются в непривычном направлении
 * Так же есть координаты Top и Bottom
 * Padding - отступ внутри границы View
 * Margin - отступ относительно родителя
 *
 * gravity - позиционирование content внутри View
 * layout_gravity - позиционирование нашей View во внешнем parent
 *
 * */
class CustomViewExample @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    /** View прикреплена к экрану*/
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    /** ---------------------- Измерение размеров View (Measure pass)-----------------------------*/
    /**
     * 1.Проходит по дереву сверху вниз
     * 2.Родитель передает детям требования к их размерам (Measure Spec)
     * 3.View рассчитывает размеры и сохраняет их во внутренний стейт (втч measuredWidth/MeasuredHeight)
     * 4.Поддерево View может быть перерасчитано по новой, если родителю не хватает данных о размерах
     * */

    /**
     * View.MeasureSpec — это класс в Android, который используется для определения размеров
     * и режимов измерения для пользовательских представлений (views) в процессе их измерения и раскладки.
     * Эти спецификации состоят из двух частей:
     *
     * 1)Размер (Size) — максимальный размер, который может занять представление.
     * 2)Режим (Mode) — определяет, как представление должно использовать этот размер. Есть три основных режима:
     * MeasureSpec.UNSPECIFIED: Родитель не ограничивает размер представления. Представление может быть любого размера.
     * MeasureSpec.EXACTLY: Родитель задает точный размер, который должно занять представление.
     * MeasureSpec.AT_MOST: Представление может занять размер до указанного, но не больше.
     * */

    /** Вызывается из метода measure() который является private
     * Если хотим переопределить какое то нестандартное поведение то переопределяем onMeaure()
     * При этом не забываем вызвать setMeasuredDimension(measuredWidth, measuredHeight)*/
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /** ------ Позиционирование View внутри ViewGroup в соответствии с размерами (Layout pass)----*/
    /**
     * 1.Проходит по дереву сверху вниз после measure pass
     * 2.Родитель передает финальные размеры и позицию своим детям
     * 3.Производится один раз на Layout
     * */

    /** Назначение размера и позиции View и всем его наследникам */
    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
    }

    /** Переопределяем логику позиционирования здесь. Это пустой метод который переопределяется каждой View*/
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    /** ---------------------- Отрисовка ---------------------------------------------------------*/
    /**
     * Все работает через Canvas и Paint
     * Canvas предоставляет методы рисования геометрических фигур текста итд
     * Paint предоставляет методы задания цвета
     * */
    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
    }

    /** Переопределяем логику позиционирования тут*/
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    /** View откреплена от экрана*/
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}

/**
 * В каких ситуация вообще может понадобиться вызывать invalidete или Request layout у View?
 * -Изменить раземр шрифта у TextView например
 * */