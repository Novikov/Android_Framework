package com.app.aac.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.aac.R
import com.app.aac.viewmodel.vm.FirstViewModel
import com.app.aac.viewmodel.vm.SecondViewModel
import com.app.aac.viewmodel.vm.ViewModelWithContext

class ViewModelActivity : AppCompatActivity() {

    //Внутри каждой ViewModel есть описание компонентов
    lateinit var firstViewModel: FirstViewModel
    lateinit var secondViewModel: SecondViewModel
    lateinit var wiewModelWithContext: ViewModelWithContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        /** ViewModelProvider - механизм создания ViewModel (имеет 3 конструктора )*/

        //C ViewModelStoreOwner
        firstViewModel = ViewModelProvider(this).get(FirstViewModel::class.java)

        //ViewModelStoreOwner + Factory для передачи параметра извне
        secondViewModel = ViewModelProvider(
            this,
            SecondViewModel.MyViewModelFactory("some_text_for_param")
        ).get(SecondViewModel::class.java)

        //C Передачей мапы Extras todo Разобрать как этим пользоваться

        //Создание ViewModel с контекстом
        wiewModelWithContext = ViewModelProvider(this).get(ViewModelWithContext::class.java)

    }
}