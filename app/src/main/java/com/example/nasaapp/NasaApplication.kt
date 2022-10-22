package com.example.nasaapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NasaApplication : Application() {

    companion object {
        lateinit var INSTANCE: NasaApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}