package com.app.other.keyboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class KeyBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * Чтобы изменить режим работы с клавиатурой в Android активности, вы можете использовать атрибуты в манифесте приложения или программно устанавливать флаги в активности.
     *
     * 1. Изменение в манифесте
     * Вы можете задать режим ввода для активности в файле AndroidManifest.xml, используя атрибут windowSoftInputMode. Вот некоторые из доступных значений:
     *
     * stateHidden — клавиатура будет скрыта при открытии активности.
     * stateVisible — клавиатура будет видна при открытии активности.
     * adjustResize — контент активности будет изменяться в размере, чтобы учесть клавиатуру.
     * adjustPan — контент активности будет панорамироваться, чтобы не перекрываться клавиатурой.
     *
     * <activity
     *     android:name=".YourActivity"
     *     android:windowSoftInputMode="stateVisible|adjustResize">
     * </activity>
     * */
}