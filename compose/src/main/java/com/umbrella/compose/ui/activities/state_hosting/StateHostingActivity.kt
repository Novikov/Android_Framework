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

/** 
 *Compose старается максимально оптимизировать запуск Composable функций.
 * Это еще одна причина (кроме производительности) почему не надо в Composable функциях менять значение в БД и выполнять прочие операции,
 * которые напрямую меняют какое-то состояние снаружи. Потому что вы не можете заранее знать, сколько раз будет вызвана эта функция.
 * Даже если вы точно посчитали, что ваша Composable функция будет вызвана определенное количество раз за весь жизненный цикл экрана,
 * то какой-то другой разработчик может добавить туда новых входных параметров или State. И это сломает ваши расчеты. Поэтому нам ни в коем случае нельзя завязывать логику приложения на вызовы Composable функции.
 * Повторюсь, Composable функция принимает снаружи State или данные и отображает их на экране, а обратную связь осуществляет через лямбды. Сама функция напрямую не должна делать ничего важного.
 *
 * todo Remember + key, куда пишет
 *
 *  collectAsStateWithLifecycle
 * */
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
