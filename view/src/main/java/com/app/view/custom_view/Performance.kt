package com.app.view.custom_view

/**
 * invalidate() vs requestLayout().
 * Занимают разное время и ресурсы.
 *
 * invalidate() только перерисовывает View. Отправит в позицию dispatchDraw(). 1 draw pass
 *
 * requestLayout() перересует с перерасчетом размеров. Отправит в позицию measure()
 * (n measure pass + 1 draw pass) * view count in view tree. Частое использование или использование в анимациях будет приводить к тормозам UI
 * Лучше ее избегать
 *
 * Нужно добиться 60 fps
 * Android будет вызывать onDraw нужное количество раз, но
 *  1. Если onDraw будет долгим - следующий по счету будет пропущен
 *  2. Если часто вызывать invalidate() вручную эффект будет тот же
 *
 *  Owerdraw рисование одного и того же пикселя несколько раз (наложение вьюх)
 *
 *  Советы
 *  1. По возможности избегать вызов requestLayout()
 *  2. Делать ручные вызовы invalidate как можно реже
 *  3. Оптимально реализовывать onDraw - не делать аллокаций внутри метода ни долгих операций
 *  4. Избегать owerdraw
 *  -Удалить невидимые бэкграунды
 *  -Упростить иерархию View
 *  -Уменьшить прозрачность
 *
 *  Debug GPU Overdraw - инструмент анализа количества перерисовок
 *  Setting > Developer Options > Debug GPU Overdraw
 *
 *  Layout Inspector так же имеет такую функциорнальность + посмотреть дерево вложенности View
 *
 *  GPU Profiler
 *  Settings > Developer Options > Profile GPU Rendering
 * */