package com.app.ui.fragments.hand_navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.app.ui.R

class FragmentB : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val text = arguments?.getString(FRAGMENT_B_ARGS, "")
        Log.i("ASDASDASDASD", "$text")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    companion object {
        const val FRAGMENT_B_ARGS = "FRAGMENT_B_ARGS"
        fun newInstance(text: String) = FragmentB().apply {
            arguments = bundleOf(FRAGMENT_B_ARGS to text)
        }
    }
}