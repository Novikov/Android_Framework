package com.app.data_storage.shared_preferences

/**
Shared Preferences
    -Доступ через context
    -apply команда асинхронно, commit запишет синхронно. В первую очередь используем apply, commit если перед перезагрузкой приложения например
    -Слушатель
    -Проблемы: Несоответствие типов при записи и вытаскивания значений
 * */