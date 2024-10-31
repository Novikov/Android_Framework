package com.app.aac.viewmodel.vm

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

/**
 * Единственный правильный способ передовать Context во ViewModel.
 * Обрати внимание что context может быть создан как property (с val) или как параметр конструктора (без val).
 * Он этого зависит будет ли доступен этот параметр в методах или только в init блоке. ViewModelProvider передаст context за нас
 * Так же можно воспользоваться решением через ResourceManager с WeakReference из БКС
 * */
class ViewModelWithContext(val context: Application) : AndroidViewModel(context) {

    init {
        Toast.makeText(context, "ThirdViewModel has been created with context $context", Toast.LENGTH_SHORT).show()
    }

    fun someMethod(){
        Toast.makeText(context, "ThirdViewModel has been created with context $context", Toast.LENGTH_SHORT).show()
    }
}