package com.umbrella.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.umbrella.compose.ui.activities.elements.TestElementsActivity
import com.umbrella.compose.ui.theme.MainTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                createNavigation()
            }
        }
    }

    @Composable
    fun createNavigation() {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val navigationList =
                    listOf(
                        TestElementsActivity::class.java,
                    )
                createButtonsList(navigationList)
            }
        }
    }

    @Composable
    fun createButtonsList(data: List<Class<out ComponentActivity>>) {
        LazyColumn {
            items(data) { item -> makeButtonWithAction(activity = item) }
        }
    }

    @Composable
    fun makeButtonWithAction(activity: Class<out ComponentActivity>) {
        Button(
            onClick = { startActivity(Intent(applicationContext, activity)) },
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
        ) {
            Text(text = activity.simpleName, color = Color.White)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainTheme {
            createNavigation()
        }
    }
}
