package com.app.aac.viewmodel.vm

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

/**
 * Единственный правильный способ передовать Context во ViewModel.
 * Обрати внимание что app это параметр конструктора, а не property. ViewModelProvider передаст context за нас
 * Так же можно воспользоваться решением через ResourceManager с WeakReference из БКС
 * */
class ViewModelWithContext(app: Application) : AndroidViewModel(app) {

    init {
        Toast.makeText(app, "ThirdViewModel has been created", Toast.LENGTH_SHORT).show()
    }
}