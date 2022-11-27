package com.umbrella.compose.ui.activities.state_hosting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.umbrella.compose.ui.activities.state.ui.theme.ComposeTheme

class StateHostingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HelloScreen()
                }
            }
        }
    }
}

@Composable
fun HelloScreen() {
    var name by rememberSaveable { mutableStateOf("") }
    Column {
        HelloContent(name = name, onNameChange = { name = it })
        HelloContent(name = name, onNameChange = { name = it })
    }
    /**
     * State hoisting in Compose is a pattern of moving state to a composable's caller to make a composable stateless.
     * Насколько я понимаю - это нужно для переиспользования composable функций.
     * Compose использует умную рекомппозицию и будут меняться только те view, часть стейта которого
     * изменилась в общем стейте.
     * */
}

@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello, $name",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeTheme {
        HelloScreen()
    }
}
