package com.example.panhe.noxintong.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.panhe.noxintong.R
import com.example.panhe.noxintong.constant.PREFS_KEY

val Context.config: BaseConfig get() = BaseConfig.newInstance(applicationContext)

fun Context.getSharedPrefs() = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)

fun Context.toast(id: Int, length: Int = Toast.LENGTH_SHORT) {
    toast(getString(id), length)
}

fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    try {
        Toast.makeText(applicationContext, msg, length).show()
    } catch (e: Exception) {
    }
}

