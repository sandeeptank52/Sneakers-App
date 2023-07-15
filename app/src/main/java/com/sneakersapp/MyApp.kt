package com.sneakersapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    companion object {
        private lateinit var mInstance: MyApp

        @Synchronized
        fun getInstance(): MyApp {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }
}