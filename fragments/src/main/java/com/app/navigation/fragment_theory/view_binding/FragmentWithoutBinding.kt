package com.app.navigation.fragment_theory.view_binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.app.navigation.R

// Способ ухода от переопределения метода onCreateView
class FragmentWithoutBinding : Fragment(R.layout.fragment_with_binding) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * отвечает за создание и возврат корневого представления фрагмента. Этот метод вызывается после того,
     * как фрагмент был создан, но до того, как он будет добавлен в активность.
     *
     * LayoutInflater inflater: Позволяет разворачивать XML-разметку.
     * ViewGroup container: Родительский контейнер, в который будет добавлено представление фрагмента.
     * Bundle savedInstanceState: Если фрагмент был ранее создан, в этом параметре будет храниться его состояние
     *
     * Последний параметр отвечает за то - будет ли данный фрагмент положен в свой родительский контейнер или нет. По умолчанию нужно ставить данный параметр в false. Если поставить true - то прилет исключение
     * java.lang.IllegalStateException: Views added to a FragmentContainerView must be associated with a Fragment.
     *
     * Возврат представления: Метод должен вернуть объект View, который будет отображаться в активности. Если возвращается null, фрагмент не будет иметь пользовательского интерфейса.
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Инфлейтируем XML-разметку по id контейнера если не используем ViewBinding
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Вызов метода findViewById довольно дорогой поэтому желательно от этого уходить*/
        view.findViewById<Button>(R.id.test_button)
    }

}