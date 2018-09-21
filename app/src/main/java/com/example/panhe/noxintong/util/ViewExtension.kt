package com.example.panhe.noxintong.util

import android.view.View

fun View.ckick(call: () -> Unit) {
    this.setOnClickListener {
        call()
    }
}