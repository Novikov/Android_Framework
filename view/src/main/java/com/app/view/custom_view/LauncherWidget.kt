package com.app.view.custom_view

/**
 * Виджетом можно называть только Home Screen Widgets. Умные вьюшки которые ходят на бэк это не виджеты "self-sufficient views" или "smart views". Иногда их также называют "data-driven views" или "component-based views".
 *
 * Разработка виджета для Android-лаунчера включает несколько шагов. Вот общий процесс, который поможет вам создать свой собственный виджет:
 *
 * 1. Создание нового проекта
 * Откройте Android Studio и создайте новый проект.
 * Выберите шаблон "Empty Activity" или "No Activity" в зависимости от ваших предпочтений.
 *
 * 2. Добавление виджета в проект
 * В res папке вашего проекта создайте новую папку xml, если её еще нет.
 * В папке xml создайте файл для вашего виджета, например widget_info.xml, который будет содержать метаданные о вашем виджете.
 * Пример widget_info.xml:
 * <appwidget-provider xmlns:android="http://schemas.android.com/apk/res/android">
 *     <initialLayout>@layout/widget_layout</initialLayout>
 *     <minWidth>250dp</minWidth>
 *     <minHeight>100dp</minHeight>
 *     <updatePeriodMillis>60000</updatePeriodMillis>
 *     <resizeMode>horizontal|vertical</resizeMode>
 *     <widgetCategory>home_screen</widgetCategory>
 * </appwidget-provider>
 *
 * 3. Создание макета для виджета
 * В папке res/layout создайте файл разметки для вашего виджета, например widget_layout.xml.
 * Пример widget_layout.xml:
 * <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
 *     android:layout_width="match_parent"
 *     android:layout_height="match_parent"
 *     android:orientation="vertical"
 *     android:padding="10dp">
 *
 *     <TextView
 *         android:id="@+id/widget_text"
 *         android:layout_width="wrap_content"
 *         android:layout_height="wrap_content"
 *         android:text="Hello, Widget!"
 *         android:textSize="18sp" />
 *
 *     <Button
 *         android:id="@+id/widget_button"
 *         android:layout_width="wrap_content"
 *         android:layout_height="wrap_content"
 *         android:text="Click me!" />
 * </LinearLayout>
 *
 * 4. Создание класса для вашего виджета
 * Создайте новый класс, который будет наследовать AppWidgetProvider. Этот класс будет управлять жизненным циклом вашего виджета.
 * Пример класса MyWidgetProvider.java:
 * public class MyWidgetProvider extends AppWidgetProvider {
 *
 *     @Override
 *     public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
 *         for (int appWidgetId : appWidgetIds) {
 *             RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
 *             views.setTextViewText(R.id.widget_text, "Updated!");
 *
 *             // Обработчик нажатия на кнопку
 *             Intent intent = new Intent(context, MyWidgetProvider.class);
 *             intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
 *             intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
 *             PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
 *             views.setOnClickPendingIntent(R.id.widget_button, pendingIntent);
 *
 *             appWidgetManager.updateAppWidget(appWidgetId, views);
 *         }
 *     }
 * }
 *
 * 5. Регистрация виджета в AndroidManifest.xml
 * Добавьте необходимую информацию о вашем виджете в манифест.
 * <receiver android:name=".MyWidgetProvider">
 *     <intent-filter>
 *         <action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
 *     </intent-filter>
 *
 *     <meta-data
 *         android:name="android.appwidget.provider"
 *         android:resource="@xml/widget_info" />
 * </receiver>
 *
 * 6. Тестирование виджета
 * Запустите ваше приложение на эмуляторе или реальном устройстве.
 * На домашнем экране удерживайте палец на пустом месте, выберите "Виджеты", найдите ваш виджет и добавьте его на экран.
 *
 * 7. Обновление и управление состоянием
 * Вы можете реализовать дополнительные методы, такие как onEnabled(), onDisabled(), и onReceive(), для управления состоянием вашего виджета и обработки пользовательских действий.
 *
 * Заключение
 * Создание виджета для Android-лаунчера — это интересный процесс, который требует внимательного подхода к разметке и управлению состоянием. Этот процесс включает проектирование интерфейса, создание класса AppWidgetProvider, регистрацию виджета в манифесте и тестирование на устройстве. После выполнения этих шагов вы сможете создать интерактивный виджет, который пользователи смогут добавлять на свои домашние экраны
 * */