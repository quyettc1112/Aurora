package com.aurora.aurora.Common.TokenManager

import android.content.Context

class TokenManager {

    companion object{
        const val FIRST_START_APP: String = "FIRST_START_APP"
        const val FIRST_START_APP_VALUE: String = "FIRST_START_APP"

        fun saveFirstStart(context: Context) {
            val sharedPreferences = context.getSharedPreferences(FIRST_START_APP, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean(FIRST_START_APP, false)
            editor.apply()
        }

        fun isFirstStart(context: Context): Boolean {
            val sharedPreferences = context.getSharedPreferences(FIRST_START_APP, Context.MODE_PRIVATE)
            val isFirstStart = sharedPreferences.getBoolean(FIRST_START_APP, true)
            return isFirstStart
        }

    }

}