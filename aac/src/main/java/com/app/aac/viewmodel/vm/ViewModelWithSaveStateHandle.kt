package com.app.aac.viewmodel.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ViewModelWithSaveStateHandle(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    /**
     * ViewModel переживает изменения конфигурации, но не смерть процесса.
     * SavedStateHandle позволяет пережить смерть процесса
     * Но эта штука будет работать только при убийстве процесса системой.
     * Если мы хотим сохранять данные для ручного уничтожения приложения - используем Prefs/Room
     * * */

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text

    fun saveData(newText: String) {
        _text.value = newText
        savedStateHandle.set(KEY, newText)
        Log.i("ASDASDASDASDASD", "Saved data: ${savedStateHandle.get<String>(KEY)}")
    }

    fun restoreData(){
        Log.i("ASDASDASDASDASD", "Restored data: ${savedStateHandle.get<String>(KEY)}")
        savedStateHandle.get<String>(KEY)?.let {
            _text.value = it
        }
    }

    companion object {
        const val KEY = "SAVING_KEY"
    }
}