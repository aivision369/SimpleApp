package com.aivison.simpleapp

import android.app.Application
import com.aivison.simpleapp.injector.AppComponent
import com.aivison.simpleapp.injector.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
