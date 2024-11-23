package com.umbrella.compose.ui.activities.elements

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.umbrella.compose.ui.activities.elements.topics.LayoutScreen
import com.umbrella.compose.ui.activities.elements.ui.theme.ComposeTheme


class TestElementsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                LayoutScreen()
                //StateHostingScreen()
                //ModifiersScreen()
                //ListScreen()
                //CompositionLocalScreen()
                //DrawingApp()
                //AnimationsScreen()
            }
        }
    }
}
