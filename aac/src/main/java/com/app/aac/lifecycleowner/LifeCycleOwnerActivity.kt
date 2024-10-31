package com.app.aac.lifecycleowner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.aac.R

/**
 * LifecycleOwner — это интерфейс в Android, который представляет компонент,
 * способный предоставлять информацию о своем жизненном цикле. Он позволяет другим компонентам
 * (например, LiveData, ViewModel, и т.д.) следить за состоянием жизненного цикла этого компонента
 * и управлять своими действиями в зависимости от состояния.
 *
 * Основные характеристики LifecycleOwner:
 * Предоставление жизненного цикла: LifecycleOwner позволяет компонентам подписываться на события
 * жизненного цикла. Это особенно полезно для управления ресурсами и обновления пользовательского
 * интерфейса.
 *
 * Имплементация: В Android, классы Activity и Fragment реализуют этот интерфейс.
 * Это означает, что вы можете использовать их в качестве LifecycleOwner, чтобы другие компоненты
 * могли следить за их состоянием.
 *
 * Поддержка LiveData и ViewModel: LiveData и другие архитектурные компоненты используют
 * LifecycleOwner, чтобы автоматически управлять подписками и обновлениями данных
 * в зависимости от состояния жизненного цикла.
 * */

class LifeCycleOwnerActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_owner)
    }
}