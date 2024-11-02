package com.app.aac.viewmodel.vm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

class ViewModelWithClosableScope(
    private val coroutineScope: CoroutineScope = CloseableCoroutineScope()
) {
    /**
     *From lifecycle version 2.5 and above, you can pass one or more Closeable objects to the ViewModel's
     *  constructor that automatically closes when the ViewModel instance is cleared.
     * */
}

class CloseableCoroutineScope(
    context: CoroutineContext = SupervisorJob() + Dispatchers.Main.immediate
) : Closeable, CoroutineScope {
    override val coroutineContext: CoroutineContext = context
    override fun close() {
        coroutineContext.cancel()
    }
}