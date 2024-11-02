package com.app.navigation.navigation.hand_navigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.app.navigation.R

class FragmentA : Fragment() {

    interface CallBack {
        fun toFragmentB(text: String)
    }

    var callBack: CallBack? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = context as HandNavigationActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.navigation_button)
        button.setOnClickListener {
            callBack?.toFragmentB("Some text")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        callBack = null /** Не забываем обнулить*/
    }

    companion object {
        fun newInstance() = FragmentA()
    }
}