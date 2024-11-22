package com.umbrella.compose.ui.activities.elements

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.umbrella.compose.ui.activities.elements.topics.ListScreen
import com.umbrella.compose.ui.activities.elements.topics.StateHostingScreen
import com.umbrella.compose.ui.activities.elements.ui.theme.ComposeTheme


class TestElementsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                StateHostingScreen()
                //ModifiersScreen()
                //ListScreen()
            }
        }
    }
}
