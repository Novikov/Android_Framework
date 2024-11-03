package com.app.navigation.fragment_theory.view_binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.navigation.databinding.FragmentWithDataBindingBinding

/**
 * Способ инициализации большого количества полей в несколько строк кода
 * Избавляемся от раздутых методов инициализации View
 * */
class FragmentWithDataBinding : Fragment() {

    var binding: FragmentWithDataBindingBinding? = FragmentWithDataBindingBinding.inflate(layoutInflater)
    val button = Button("My Custom Button")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.button = button
        binding?.lifecycleOwner = this // Optional, but useful for LiveData (when we want get data from viewmodel.)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}

data class Button(val buttonText: String)