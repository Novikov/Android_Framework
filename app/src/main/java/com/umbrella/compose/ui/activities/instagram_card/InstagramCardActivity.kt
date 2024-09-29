package com.umbrella.compose.ui.activities.instagram_card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.umbrella.compose.ui.activities.state.ui.theme.ComposeTheme

class InstagramCardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                InstagramCard()
            }
        }
    }
}

@Composable
fun InstagramCard() {
    Column {
        Text(text = "Hohoho")
    }
}