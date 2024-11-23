package com.app.ui.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Список точек для рисования
    private val points = mutableListOf<Pair<Float, Float>>()

    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 10f // Размер точки
        isAntiAlias = true
        style = Paint.Style.FILL // Заполнение
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Рисуем все точки
        for (point in points) {
            canvas.drawCircle(point.first, point.second, 10f, paint)
        }
    }

    // Обрабатываем касания
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                // Добавляем точку в список
                points.add(Pair(event.x, event.y))
                invalidate() // Перерисовываем экран
            }
        }
        return true
    }

    // Очищаем экран
    fun clear() {
        points.clear()
        invalidate() // Перерисовываем экран
    }
}