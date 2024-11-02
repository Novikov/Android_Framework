package com.app.navigation.fragment_theory.transactions

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.app.navigation.R
import com.app.navigation.fragment_theory.transactions.TemplateFragmentAdapter.Companion.HEIGHT
import com.app.navigation.fragment_theory.transactions.TemplateFragmentAdapter.Companion.POSITION
import java.util.*


class TemplateFragment : Fragment(R.layout.fragment_template) {
    var number: Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.takeIf { it.containsKey(POSITION) }?.apply {
            number = getInt(POSITION)
        }
        Log.i(TAG, "$number onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "$number onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "$number onCreateView: ")
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val fragment_container =
            view?.findViewById<ConstraintLayout>(R.id.fragment_template_container)

        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        fragment_container?.setBackgroundColor(color)

        arguments?.takeIf { it.containsKey(POSITION) }?.apply {
            val textView: TextView? = view?.findViewById(R.id.template_fragment_number)
            textView?.text = getInt(POSITION).toString()
        }

        arguments?.takeIf { it.containsKey(HEIGHT) }?.apply {
            fragment_container?.maxHeight = getInt(HEIGHT)
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        Log.i(TAG, "$number onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "$number onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "$number onPause: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "$number onSaveInstanceState: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "$number onStop: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "$number onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "$number onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "$number onDetach: ")
    }

    companion object {
        private const val TAG = "TemplateFragment"
    }

}