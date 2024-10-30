package com.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.ui.fragments.hand_navigation.HandNavigationActivity

class UiMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_main)

        val intent = Intent(this, HandNavigationActivity::class.java)
        this.startActivity(intent)
    }
}