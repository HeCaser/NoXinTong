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
import com.example.panhe.noxintong.App
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

 fun Context.isPhoneNumberCorrectOrShowErrorToast(phone: String): Boolean {

    if (TextUtils.isEmpty(phone)) {
        this.toast(this.resources.getString(R.string.please_input_phone_number))
        return false
    }

    if (!phone.startsWith("1")) {
        this.toast(this.resources.getString(R.string.phone_number_format_is_ncorrect))
        return false
    }

    if (phone.length != 11) {
        this.toast(this.resources.getString(R.string.phone_number_format_is_ncorrect))
        return false
    }

    return true
}