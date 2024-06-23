package com.aurora.aurora.Common.TokenManager

import android.content.Context

class TokenManager {

    companion object{
        const val FIRST_START_APP: String = "FIRST_START_APP"
        const val FIRST_START_APP_VALUE: String = "FIRST_START_APP"
        const val USER_ACCESS_TOKEN: String = "USER_ACCESS_TOKEN"
        const val USER_PHONE_NUMBER: String = "USER_PHONE_NUMBER"




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

        fun saveAccessToken(context: Context, token: String) {
            val sharedPreferences = context.getSharedPreferences(FIRST_START_APP, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(USER_ACCESS_TOKEN, token)
            editor.apply()
        }

        fun getAccessToken(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences(FIRST_START_APP, Context.MODE_PRIVATE)
            return sharedPreferences.getString(USER_ACCESS_TOKEN, null)
        }
        fun removeAccessToken(context: Context) {
            val sharedPreferences = context.getSharedPreferences(FIRST_START_APP, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove(USER_ACCESS_TOKEN)
            editor.apply()
        }

        fun savePhoneNumber(context: Context, phoneNumber: String) {
            val sharedPreferences = context.getSharedPreferences(FIRST_START_APP, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(USER_PHONE_NUMBER, phoneNumber)
            editor.apply()
        }

        fun getPhoneNumber(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences(FIRST_START_APP, Context.MODE_PRIVATE)
            return sharedPreferences.getString(USER_PHONE_NUMBER, null)
        }

    }

}