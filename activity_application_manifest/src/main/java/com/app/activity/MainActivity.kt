package com.app.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity - основной UI контейнер
 *
 * Может находиться в 4 состояниях
 * https://developer.android.com/static/codelabs/basic-android-kotlin-compose-activity-lifecycle/img/468988518c270b38_960.png
 *
 * ЖЦ https://developer.android.com/guide/components/activities/activity-lifecycle
 *
 * */
class MainActivity : AppCompatActivity() {

    /**
     * Создание activity из layout.
     * Расчет размеров, положение компонентов внутри нее, готовим для показа к пользователю.
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            /** todo Найти практический пример
             * Если savedInstanceState не равен null, это означает, что активити была ранее уничтожена и теперь восстанавливается.
             * Вы можете использовать этот метод для восстановления состояния UI, например, устанавливая значения в текстовые поля или восстанавливая состояния элементов интерфейса.
             * */
        }
        Log.i("SADASDASQWEQWE", "onCreate: ")
    }

    /**
     * View показывается пользователю, но он еще не может с ней взаимодействовать.
     * */
    override fun onStart() {
        super.onStart()
        Log.i("SADASDASQWEQWE", "onStart: ")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        /**
         * Этот метод вызывается после onStart(), но до onResume(). Он предназначен для восстановления состояния пользовательского интерфейса,
         * и здесь обычно восстанавливают только данные UI, которые могут потребовать дополнительных настроек после инициализации активности
         * */
        Log.i("SADASDASQWEQWE", "onRestoreInstanceState: ")
    }

    /**
     * Пользователь может взаимодействовать с View. Activity запущена.
     */
    override fun onResume() {
        super.onResume()
        Log.i("SADASDASQWEQWE", "onResume: ")
    }

    /**
     * Пользователь видит View, но уже не может с ним взаимодействовать.
     * От onPause() можно перейти к вызову либо onResume(), либо onStop()
     * Если кратко - то в данном методе нужно выполнять короткие операции,
     * т.к на работу метода отводится мало времени.
     * */
    override fun onPause() {
        super.onPause()
        Log.i("SADASDASQWEQWE", "onPause: ")
    }

    /**
     * Ветка может пойти на onRestart() если свернуть приложение в режим таск менеджера и открыть приложение снова. Но если системе понадобятся ресурсы - она может его убить.
     * Так же на onRestart() можно попасть если из одной активити перейти на другую и вернуться назад.
     * Пользователь не видит View. Можно выполнить сложные операции. Они не будут тормозить UI.
     *
     * */
    override fun onStop() {
        super.onStop()
        Log.i("SADASDASQWEQWE", "onStop: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("SADASDASQWEQWE", "onSaveInstanceState: ")
        /**
         * Тут сохраняем значения в Bundle
         * */
    }

    /**
     * Вызывается не гарантированно.
     * Если пользователь свернул наше приложение и системе нужны ресурсы, то приложение может быть убито и onDestroy() не вызовется.
     * Отработает в след случаях:
     * -Изменение конфигурации
     * -Нажали кнопку назад
     * -Вызов метода activity.finish() (принудительное завершение работы). При нажатии кнопки назад этот метод и вызывается, но мы можем вызвать его самостоятельно.
     * -Вручную удаляем из TaskManager
     *
     * Crush не вызовет вызова данного метода
     * */
    override fun onDestroy() {
        super.onDestroy()
        Log.i("SADASDASQWEQWE", "onDestroy: ")
    }

    /**
     * MultiWindow
     * В книге сказано что разрботчики постоянно совершают ошибку не учитывая режим нескольких окон
     * Теперь пользователь может видеть сразу 2 активных активити
     * Если они будут показаны, то вызовется onResume в котором сразотает метод показа видео.
     * Но пока пользователь включит второе окно - Activity маленького размера долгое время и пользователи ожидают что она себя будет вести как открытая большая
     * И типо надо запускать проигрыш видео в onStart. Но ютуб этого не делает.
     * Проигрыш видео будет доступен когда пользователь полностью откроет 2 активити в многооконном режиме. (onResume)
     * Поэтому не паримся на эту тему
     * */
}

/**
 * Изменение конфигурации (Уничтожение на короткое время)
 *
 * События, вызывающие изменения конфигурации:
 * Изменение ориентации экрана: Поворот устройства с альбомной на портретную ориентацию и наоборот.
 * Изменение размеров экрана: Например, при подключении внешнего дисплея или изменении размера окна на планшете.
 * Изменение языка: Если пользователь изменяет язык системы, это также может вызвать пересоздание активности.
 * Изменение настроек доступности: Например, при изменении настроек шрифтов или цветовой схемы.
 * Изменение тем оформления: Переключение между светлой и тёмной темами.
 * Изменение плотности экрана: При использовании разных разрешений экрана.
 *
 * Ограничение изменений конфигурации в манифесте:
 * Чтобы ограничить набор событий, которые могут привести к пересозданию активности, вы можете использовать атрибут configChanges в манифесте вашей активности.
 * <activity
 *     android:name=".YourActivity"
 *     android:configChanges="orientation|screenSize|language|smallestScreenSize">
 * </activity>
 *
 * Bundle используется только для переживания изменения конфигурации.
 * В него можно класть
 * Serializable - старый механизм из Java
 * Parcelable - спекциально сделанный для Android. Требует реализации методов сериализации или аннотации @Parcelize которая сама сгенерит эти методы
 * Примитивы
 *
 * Помимо нас в Bundle система сама пишет данные для последующего восстановления
 * При изменении конфигурации, например, при повороте экрана, система Android автоматически сохраняет некоторые данные в Bundle. Вот что именно сохраняется:
 *
 *-Состояние пользовательского интерфейса:
 * Например, текущее состояние текстовых полей (содержимое EditText), состояния чекбоксов и других элементов управления.
 *-Некоторые настройки активности:
 * Например, размеры окна и режимы отображения (например, полноэкранный режим).
 *-Состояние фрагментов:
 * Если у вас есть фрагменты, система может автоматически сохранить их состояние, включая их представление и состояние пользовательского интерфейса.
 * Данные о текущем состоянии представления (View):
 * -Некоторые свойства элементов представления могут быть сохранены, такие как положение прокрутки, текущее выделение и т.д.
 *
 * Пережить смерть процесса (Уничтожение на долгое время)
 * Если активити уничтожает СИСТЕМА, а не пользователь (Таск менеджер это тоже пользователь) -  то можно воспользоваться ViewModel + SavedStateHandle
 * В других случаях используем Prefs, Room, Files
 * */

/**
 * Восстановление состояния из Bundle. onCreate vs onRestoreInstanceState
 * onRestoreInstanceState
 * This method is called after onStart() when the activity is being re-initialized from a previously saved state, given here in savedInstanceState. Most implementations will simply use onCreate(Bundle) to restore their state, but it is sometimes convenient to do it here after all of the initialization has been done or to allow subclasses to decide whether to use your default implementation.
 * onRestoreInstanceState guarantees you receive a non-null Bundle object also in the lifecycle of activity it's called after onStart But onCreate: you should always check if the Bundle object is null or not to determine the configuration change and as you know it's called before onStart So It's all up to you and depends on your needs.
 * Оба метода вызываются после изменения конфигурации. Похоже разница только в том придет ли null
 * */

/**
 * Управление бэкстеком
 *
 * У Activity очень неудобный набор инструментов управления бэкстеком.
 * 1)Метод finish для уничтожения активити
 * 2)Переопределение метода onBackPressed()
 * 3)Флаги запуска Activity
 * FLAG_ACTIVITY_NEW_TASK: открывает новую активность в новом задаче.
 * FLAG_ACTIVITY_CLEAR_TOP: если новая активность уже существует в стеке, все активности над ней будут уничтожены.
 * FLAG_ACTIVITY_SINGLE_TOP: если новая активность уже находится на вершине стека, вместо создания новой экземпляра будет вызван метод onNewIntent().
 * Данные флаги можно задать в манифесте или при создании интента
 * intent.addFlags(...)
 *
 * Очистить стек активностей можно следующим образом
 * Intent intent = new Intent(this, TargetActivity.class);
 * intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
 * startActivity(intent);
 * */

/**
 * Явный интент (Explicit Intent):
 * Intent intent = new Intent(this, TargetActivity.class);
 * startActivity(intent);
 *
 * Неявный интент (Implicit Intent):
 * Intent intent = new Intent(Intent.ACTION_VIEW);
 * intent.setData(Uri.parse("http://www.example.com"));
 * startActivity(intent);
 * */

/**
 * Получение результата из другой активити
 * startActivityForResult() - depricated
 * правильный способ - ActivityResult API https://developer.android.com/training/basics/intents/result
 * Розов хорошо раасказал https://www.youtube.com/watch?v=_QyufxuP4tQ
 * */

/**
 * Корневой контейнер у Activity это Window.
 * Window - задает базовую стратегию для отображения
 * Window содержит DecoreView который прикрепляет к себе первый Layout
 * От DecoreView почкуется дерево View. Если Activity 2 - то у нас будет 2 дерева.
 * */
