package com.app.navigation.navigation.hand_navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.navigation.R

/**
 * В данной Activity реализован паттерн HostSctivity + HostFragment.
 * Навигацией управляет HostActivity через вызов соответсвующих callback методов фрагмента.
 * */
class HandNavigationActivity : AppCompatActivity(), FragmentA.CallBack {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hand_navigation)

        //Если убрать данную проверку то при перевороте экррана будет добавляться клон текущего фрагмента
        if (savedInstanceState==null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, FragmentA.newInstance())
                .commit()
        }
    }

    override fun toFragmentB(text: String) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, FragmentB.newInstance(text))
            .commit()
    }
}