package com.capx.dictionary.utils

import android.util.Log

class AppLogger {
    companion object {
    private val APP_NAME = "DictionaryApp"
        fun debug(msg: String){
            Log.d(APP_NAME, msg)
        }
        fun info(msg: String){
            Log.i(APP_NAME, msg)
        }
        fun err(msg: String,exception: Exception?){
            Log.e(APP_NAME, msg,exception)
        }
    }
}