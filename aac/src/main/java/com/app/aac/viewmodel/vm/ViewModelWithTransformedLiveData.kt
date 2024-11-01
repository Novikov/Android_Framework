package com.app.aac.viewmodel.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import androidx.lifecycle.switchMap

class ViewModelWithTransformedLiveData : ViewModel(){
    val userLiveData: LiveData<User> = MutableLiveData()

    /**
     * Transformations.map()
     * Трансформируем выходное значение
     * */
    val userName: LiveData<String> = userLiveData.map {
            user -> "${user.name} ${user.lastName}"
    }

    /**
     * Transformations.switchMap()
     * Полезен, когда вам нужно реагировать на изменения в одном LiveData и подгружать/изменять
     * другие данные в зависимости от этого изменения.
     *
     * Решение через RxSubject будет более эффективное т.к больше возможностей по управлению потоками и данными
     * В примере нижэ нужно думать как сделать запрос в репозиторий в другом потоке
     * */

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> get() = _searchQuery

    val searchResults: LiveData<List<String>> = _searchQuery.switchMap { query ->
        if (query.isNullOrEmpty()) {
            MutableLiveData<List<String>>() // Возвращаем пустые результаты
        } else {
            MutableLiveData<List<String>>()
//            repository.search(query) // Возвращаем LiveData<List<String>>
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query // этот метод должен вызываться только из главного потока или будет краш
    }

    /**
     * Transformations.distinctUntilChanged()
     * При эмите одинаковых данных в LiveData все observers всеровно будут получать обновления
     * Если мы хотим только уникальные значения, то используем distinctUntilChanged
     * */

    val sourceLiveData: MutableLiveData<Int> = MutableLiveData()
    val similarLiveData: LiveData<Int> = sourceLiveData
    val filteredLiveData: LiveData<Int> = sourceLiveData.distinctUntilChanged()

    fun postData(){
        Thread {
            sourceLiveData.postValue(1)
            Thread.sleep(1000)
            sourceLiveData.postValue(1)
            Thread.sleep(1000)
            sourceLiveData.postValue(2)
            Thread.sleep(1000)
            sourceLiveData.postValue(2)
            Thread.sleep(1000)
            sourceLiveData.postValue(3)
            Thread.sleep(1000)
            sourceLiveData.postValue(3)
        }.start()
    }
}

data class User(val name: String, val lastName: String)