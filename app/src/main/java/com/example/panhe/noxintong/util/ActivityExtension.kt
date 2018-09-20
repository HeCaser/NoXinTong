package com.example.panhe.noxintong.util

import android.app.Activity
import android.widget.Toast
import com.example.panhe.noxintong.constant.isJellyBean1Plus
import com.example.panhe.noxintong.constant.isOnMainThread

fun Activity.toast(id: Int, length: Int = Toast.LENGTH_SHORT) {
    if (isOnMainThread()) {
        showToast(this, id, length)
    } else {
        runOnUiThread {
            showToast(this, id, length)
        }
    }
}

fun Activity.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    if (isOnMainThread()) {
        showToast(this, msg, length)
    } else {
        runOnUiThread {
            showToast(this, msg, length)
        }
    }
}

private fun showToast(activity: Activity, messageId: Int, length: Int) {
    if (!activity.isActivityDestroyed()) {
        showToast(activity, activity.getString(messageId), length)
    }
}

private fun showToast(activity: Activity, message: String, length: Int) {
    if (!activity.isActivityDestroyed()) {
        activity.applicationContext.toast(message, length)
    }
}

fun Activity.isActivityDestroyed() = isJellyBean1Plus() && isDestroyed