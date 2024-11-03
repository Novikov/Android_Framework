package com.app.activity

/**
    AndroidManifest.xml — это основной файл конфигурации для любого Android-приложения. Он содержит важную информацию о приложении и его компонентах, а также определяет, как приложение взаимодействует с операционной системой Android. Вот основные функции и элементы, которые вы найдете в манифесте:

    Основные функции AndroidManifest.xml
    Определение компонентов приложения:

    Activity: Определяет все активити в приложении. Каждая активити, которую вы создаете, должна быть объявлена в манифесте.
    Service: Определяет сервисы, которые могут выполняться в фоновом режиме.
    BroadcastReceiver: Определяет приемники широковещательных сообщений.
    ContentProvider: Определяет провайдеры данных, которые позволяют делиться данными между приложениями.
    Настройки разрешений:

    Манифест определяет разрешения, которые необходимы вашему приложению для доступа к функциям устройства, таким как интернет, камера, местоположение и т.д. Например:
    xml
    Копировать код
    <uses-permission android:name="android.permission.INTERNET"/>
    Определение настроек приложения:

    Вы можете указать имя приложения, иконку, тему и другие параметры, такие как версия и минимальная версия API.
    Например:

    <application
    android:label="@string/app_name"
    android:icon="@drawable/ic_launcher"
    android:theme="@style/AppTheme">
    Фильтры намерений:

    Манифест может содержать фильтры намерений (intent filters), которые определяют, как компоненты приложения могут быть запущены. Например, вы можете указать, что ваша активити может открываться с помощью определенного действия или типа данных.
    Например:

    <activity>
    <intent-filter>
    <action android:name="android.intent.action.MAIN"/>
    <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>
    </activity>
    Указание библиотек и зависимостей:

    Вы можете указать, какие библиотеки необходимы для работы вашего приложения, и они могут быть автоматически загружены при установке.
    Конфигурация многопоточности и сборок:

    В манифесте также можно настроить поддержку нескольких архитектур и версий приложений, например, указать, что приложение поддерживает 32-битные и 64-битные архитектуры.
    Пример структуры AndroidManifest.xml

    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.myapp">

    <application
    android:label="@string/app_name"
    android:icon="@drawable/ic_launcher"
    android:theme="@style/AppTheme">

    <activity android:name=".MainActivity">
    <intent-filter>
    <action android:name="android.intent.action.MAIN"/>
    <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>
    </activity>

    </application>

    <uses-permission android:name="android.permission.INTERNET"/>

    </manifest>

    Заключение
    AndroidManifest.xml — это критически важный файл для вашего приложения, так как он определяет его структуру, разрешения, компоненты и взаимодействие с системой. Правильная настройка манифеста — это основа успешной работы вашего Android-приложения.
 * */