package com.example.panhe.noxintong.util

import android.content.Context
import com.example.panhe.noxintong.constant.*

open class BaseConfig(val context: Context) {
    protected val prefs = context.getSharedPrefs()

    companion object {
        fun newInstance(context: Context) = BaseConfig(context)
    }

    var userPhone: String
        get() = prefs.getString(PHONE_NUMBER, "")
        set(phone) = prefs.edit().putString(PHONE_NUMBER, phone).apply()

}