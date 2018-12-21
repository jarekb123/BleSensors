package com.butajlo.blesensors.base

import android.app.Application
import com.butajlo.blesensors.di.diModules
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, diModules)
    }
}

