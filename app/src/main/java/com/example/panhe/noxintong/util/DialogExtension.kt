package com.example.panhe.noxintong.util

import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.panhe.noxintong.R
import com.example.panhe.noxintong.constant.isLollipopPlus

fun Context.createProgressDialog(message: String? = null, isTransparentBg: Boolean = false): Dialog {

    var view = LayoutInflater.from(this).inflate(R.layout.progress_dialog_custom, null)
    var progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

//    var dialog = AlertDialog.Builder(this)
//            .setView(view)
//            .create()
    var dialog = Dialog(this)
    dialog.setContentView(view)


    progressBar.setProgressBarColor(Color.parseColor("#cccccc"))

    if (!TextUtils.isEmpty(message)) {
        var textView = view.findViewById<TextView>(R.id.message)
        textView.text = message

        textView.visibility = View.VISIBLE
    }


    if (isTransparentBg) {
        var lp = dialog.window.attributes
        lp.dimAmount = 0f
        dialog.window.attributes = lp
    }

    dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.window.setWindowAnimations(R.style.DialogFadeCenterInOutAnim)

    return dialog
}

fun ProgressBar.setProgressBarColor(color: Int) {
    if (isLollipopPlus()) {
        this.indeterminateTintList = ColorStateList.valueOf(color)
    } else {
        var drawable = this.indeterminateDrawable.mutate()
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        this.indeterminateDrawable = drawable
    }
}