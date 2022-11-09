package com.umbrella.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.umbrella.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        makeButton(this@MainActivity::makeToast, "Example 1")
                        makeButton(this@MainActivity::makeToast, "Example 2")
                        makeButton(this@MainActivity::makeToast, "Example 3")
                        makeButton(this@MainActivity::makeToast, "Example 4")
                        makeButton(this@MainActivity::makeToast, "Example 5")
                    }
                }
            }
        }
    }

    private fun makeToast() {
        Toast.makeText(this, this.javaClass.toString(), Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun makeButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = { onClick.invoke() },
        modifier = Modifier.height(60.dp).fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        makeButton({}, "ASDAS")
    }
}
