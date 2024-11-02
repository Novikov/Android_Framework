package com.app.aac.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Для передачи параметра необходимо переопределить Factory
 * Т.к param сами не передаем, то можем его поменять с property на constructor param
 * */
class SecondViewModel(param: String) : ViewModel() {

    class MyViewModelFactory(private val param: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SecondViewModel::class.java)) {
                return SecondViewModel(param) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}