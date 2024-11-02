package com.app.navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.navigation.fragment_theory.transactions.TransactionsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_main)

//        val intent = Intent(this, HandNavigationActivity::class.java)
        val intent = Intent(this, TransactionsActivity::class.java)
        this.startActivity(intent)
    }
}