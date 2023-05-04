package com.example.listafacturasv2.preferences

import android.content.Context

class PreferencesManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getHttpRequest(): String {
        return sharedPreferences.getString(KEY_HTTP_REQUEST, "") ?: ""
    }

    companion object {
        private const val PREFS_NAME = "com.example.listafacturasv2_preferences"
        private const val KEY_HTTP_REQUEST = "http_request"
    }

}