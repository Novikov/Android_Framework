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
     * жизненно-цикличный контейнер данных. Он позволяет разработчикам эффективно управлять данными,
     * которые могут изменяться, и обеспечивать обновление пользовательского интерфейса в ответ на эти изменения.
     *
     * Основные характеристики LiveData:
     * Наблюдаемость: LiveData позволяет подписываться на изменения данных.
     * Когда данные изменяются, все подписчики автоматически получают уведомление и могут обновить
     * пользовательский интерфейс.
     *
     * Жизненный цикл: LiveData учитывает жизненный цикл компонентов, таких как Activity и Fragment.
     * Это означает, что данные будут передаваться только тем компонентам,
     * которые находятся в активном состоянии (например, в состоянии STARTED или RESUMED).
     * Это помогает избежать утечек памяти и ненужных вызовов обновлений, когда пользовательский
     * интерфейс неактивен.
     *
     * Безопасность: LiveData не позволяет передавать данные в компоненты, которые не активны,
     * что снижает вероятность сбоев приложения.
     *
     * Поддержка изменений: LiveData автоматически уведомляет наблюдателей о изменениях данных,
     * что делает его идеальным для реализации паттерна MVVM.
     *
     * Что умеет LiveData:
     * Передача данных: LiveData позволяет хранить и передавать данные, такие как результаты запросов
     * к базе данных или сетевым API.
     *
     * Адаптация к изменениям: Когда данные изменяются (например, после запроса к базе данных),
     * LiveData уведомляет всех активных наблюдателей, и пользовательский интерфейс может автоматически обновляться.
     *
     * Комбинирование: LiveData можно комбинировать с другими компонентами, такими как Transformations,
     * чтобы изменять данные перед их передачей наблюдателям.
     * */
}