package com.app.activity

/***
 * Что такое Активити
 * Состояния а не колбэки
 *
 * Состояния
 * Created/Stopped
 * Visible + 2 окна !!!
 * Resumed
 * Негарантированный вызов ondestroy !!!
 *
 * Изменение конфигурации. Адаптация к новым ресурсам, поэтому пересоздание.
 * -Переворот
 * -Смена языка
 * -Клавиатура?
 * Как выключить фильтровать эвенты на пересоздание активити!!!
 *
 * Сохранение состояния (Уничтожение на короткое время)
 * -Persistant Storage
 * -ViewModel
 * -OnSavedInstanceState (Serializable + Parcelable расписать разницу)
 * -Где подбирать восстановленный bundle (onCreate или onRestoreInstanceState?)
 * Какие вьюшки так же сохраняют состояние в Bundle (StateFul/StateLess) EditText с ID, RecyclerView
 *
 * Финиш активити и что делать в этом случае. (Уничтожение на долгое время) !!!
 * crash
 * finish()
 * onBackPressed()
 * Вручную удаляем из TaskManager
 *
 * Как активити станет host для фрагментов !!!
 *
 * Связь с Application!!!
 * Точка входа ActivityManager !!!
 *
 * StartActivityForResult!!!
 * Task/BackStack/LaunchMode/Process
 *
 * Типы интентов
 *
 * Фокусы клавиатуры это что такое.
 *
 * Как система взаимодействует с Activity
 *
 * killable activity !!!
 * Восстановление состояния из удаленных системой активити способы
 *
 * Все способы передачи результата в активити
 * ActivityResultApi и другие
 *
 * PackageManager - это относится к Context?
 *
 */