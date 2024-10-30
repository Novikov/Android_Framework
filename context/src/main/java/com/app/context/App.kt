package com.app.context

import android.app.Application


/**
 * Application <- ContextWrapper <- Context
 * */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}