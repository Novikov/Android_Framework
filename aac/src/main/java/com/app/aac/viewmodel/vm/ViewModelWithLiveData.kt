package com.app.aac.viewmodel.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//ViewModel для примера создания через делегат и показа позможностей LiveData
class ViewModelWithLiveData : ViewModel() {

    //LiveData с инкапсуляцией
    private val _someLiveData = MutableLiveData<String>()
    val someData: LiveData<String> get() = _someLiveData

    init {
        Log.i("ASDASDASDASD", "ViewModelWithLiveData init block")
    }

    /**
     * Как и peoperty с LiveData - property _someField будет хранить свое значение и переживать изменение конфигурации
     * Прелести LiveData в другом, а именно в том что мы смотрим на LiveData из View с помощью LifeCycleOwner
     * */
    var _someProprtry: String? = null

    fun initData(someData: String, someProperty: String){
        _someLiveData.postValue(someData)
        _someProprtry = someProperty
    }

    fun logData(){
        Log.i("DASLKDJ:LASKJDLASD", "logData: someData: ${someData.value}, _someField: $_someProprtry")
    }

    /**
     * LiveData — это компонент архитектуры Android Jetpack, который представляет собой наблюдаемый,
     * жизненно-цикличный контейнер данных (Observable data holder).
     *
     * В отличие от обычного наблюдаемого объекта - LiveData учитывает жизненный цикл других компонентов
     * таких как Activity, Fragments или Service (Все кто наследует LifeCycleOwner)
     *
     * Эта осведомленность гарантирует, что LiveData обновляет только наблюдателей компонентов приложения, которые находятся в активном состоянии жизненного цикла.
     *
     * LiveData considers an observer, which is represented by the Observer class, to be in an active state
     * if its lifecycle is in the STARTED or RESUMED state. LiveData only notifies active observers about
     * updates. Inactive observers registered to watch LiveData objects aren't notified about changes.
     *
     * You can register an observer paired with an object that implements the LifecycleOwner interface.
     * This relationship allows the observer to be removed when the state of the corresponding Lifecycle
     * object changes to DESTROYED. This is especially useful for activities and fragments because they
     * can safely observe LiveData objects and not worry about leaks—activities and fragments are instantly
     * unsubscribed when their lifecycles are destroyed.
     * */
}