package com.aurora.aurora.AppConfig.Application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class AuroraApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}