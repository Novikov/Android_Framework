package com.umbrella.compose.ui.activities.composition_local

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.umbrella.compose.ui.activities.composition_local.ui.theme.ComposeTheme

val LocalActiveUser = compositionLocalOf<LocalUser> { error("No active user found!") }

class CompositionLocalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                val user = LocalUser("Igor") // предположим что юзера мы можем получить только в этом месте.
                CompositionLocalProvider(LocalActiveUser provides user) {
                    MyApp()
                    /** Значение сможем получить только для функций вызвонных внутри CompositionLocalProvider.
                     * Иначе бросится исключение.*/
                }
            }
        }
    }
}

/**
 * CompositionLocal is a tool for passing data down through the Composition implicitly
 * */

@Composable
fun MyApp() {
    Log.i("ASDASDASDASDSAD", "in myApp ${LocalActiveUser.current}: ")
    UserWidget()
}

@Composable
fun UserWidget() {
    Log.i("ASDASDASDASDSAD", "in UserWidget ${LocalActiveUser.current}: ")
    val user = LocalActiveUser.current
    Text(text = user.name)
}
