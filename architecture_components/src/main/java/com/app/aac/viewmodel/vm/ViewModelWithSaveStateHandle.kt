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
     *
     * Если помимо savedStateHandle нужны другие параметры - используй AbstractSavedStateViewModelFactory
     * * */

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text

    fun saveData(newText: String) {
        _text.value = newText
        savedStateHandle.set(KEY, newText)
        Log.i("ASDASDASDASDASD", "Saved data: ${savedStateHandle.get<String>(KEY)}")
    }

    fun restoreData() {
        Log.i("ASDASDASDASDASD", "Restored data: ${savedStateHandle.get<String>(KEY)}")
        savedStateHandle.get<String>(KEY)?.let { _text.value = it }
        /**
         * Помимо получения примитивов можно запрошивать LiveData или Flow из данных которые мы положили как примитивы
         * savedStateHandle.getLiveData<String>(KEY).let { _text.value = it.value }
         * savedStateHandle.getStateFlow(KEY, "initial_value").let { _text.value = it.value }
         * */
    }

    companion object {
        const val KEY = "SAVING_KEY"
    }
}