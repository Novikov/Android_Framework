package com.app.aac.viewmodel.vm

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * ViewModel is a business logic or screen level state holder
 * -Под бизнес логикой сскорее понимается ссылка на сущности с бизнес логикой (типо репозитория)
 * It exposes state to the UI and encapsulates related business logic.
 * Its principal advantage is that it caches state and persists it through configuration changes.
 * This means that your UI doesn’t have to fetch data again when navigating between activities,
 * or following configuration changes, such as when rotating the scree
 * */

/**Все ViewModels хранятся внутрри мапы ViewModelStore
 * ViewModelStoreOwner это интерфейс который реализуют Activity и Fragment
 * Необходимо помнить что ViewModel не должен хранить ссылки на View/Context иначе гарантированно будет утечка памяти
 * */
class FirstViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        Log.i("ASDASDASDASDDSA", "onCleared: ")
        /**Вызовется после открепления от LifeCycleOwner у Fragment или Activity
         * Это происходит, например, при повороте экрана или при завершении Activity.
         * Этот метод можно использовать для освобождения ресурсов или выполнения очистки,
         * например, отмены запросов, освобождения слушателей и т. д.
         * */
    }
}