package com.app.navigation.fragment_theory.view_binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.navigation.databinding.FragmentWithBindingBinding

class FragmentWithViewBinding : Fragment() {

    var binding: FragmentWithBindingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentWithBindingBinding.inflate(layoutInflater)
    }

    /**
     * Всеровно обязаны переопределить данный метод, но уже через binding
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null // Зануляем т.к лямбды могут захватывать данную переменную. В БКС это было реализовано через наследование
    }
}