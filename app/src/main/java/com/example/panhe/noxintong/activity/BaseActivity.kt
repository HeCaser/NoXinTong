package com.example.panhe.noxintong.activity

import android.app.Dialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.panhe.noxintong.R
import com.example.panhe.noxintong.util.createProgressDialog
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.error_view.*
import java.lang.reflect.Field


open class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 这里要调用父类的 setContentView
        super.setContentView(R.layout.activity_base)
    }

    // 重写 setContentView 把子类的 view 放在 base 的 一个View 中
    override fun setContentView(layoutResID: Int) {
        val view = layoutInflater.inflate(layoutResID, null)

        if (null != contentView) {
            contentView.addView(view, ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        }
    }

    fun showContentView() {
        contentView.visibility = View.VISIBLE
        errorView.visibility = View.GONE
    }

    // 显示错误界面
    fun showErrorView(image: Int = 0, text: String = "", callback: (() -> Unit)) {
        contentView.visibility = View.GONE
        errorView.visibility = View.VISIBLE
        if (image != 0) {
            ivError.setImageResource(image)
        }
        if (!TextUtils.isEmpty(text)) {
            tvTest.text = text
        }
        errorView.setOnClickListener {
            callback()
        }
    }


    private var progressDialog: Dialog? = null

    fun showProgressDialog(message: String? = null, isTransparentBg: Boolean = true, isCancelable: Boolean = false, isCanceledOnTouchOutside: Boolean = false) {
        if (progressDialog == null) {
            progressDialog = createProgressDialog(message = message, isTransparentBg = isTransparentBg).apply {
                setCancelable(isCancelable)
                setCanceledOnTouchOutside(isCanceledOnTouchOutside)
            }
        }

        if (!progressDialog!!.isShowing) {
            progressDialog?.show()
        }
    }

    fun dismissProgressDialog() {
        progressDialog?.let {
            if (progressDialog!!.isShowing) {
                progressDialog?.dismiss()
            }
        }
    }

    fun progressDialogIsShowing() = progressDialog != null && progressDialog!!.isShowing

    override fun onDestroy() {
        super.onDestroy()
        fixInputMethodManagerLeak(this)
    }

    fun fixInputMethodManagerLeak(destContext: Context?) {
        if (destContext == null) {
            return
        }

        val imm = destContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                ?: return

        val arr = arrayOf("mCurRootView", "mServedView", "mNextServedView")
        var f: Field? = null
        var obj_get: Any? = null
        for (i in arr.indices) {
            val param = arr[i]
            try {
                f = imm.javaClass.getDeclaredField(param)
                if (f!!.isAccessible() === false) {
                    f!!.setAccessible(true)
                } // author: sodino mail:sodino@qq.com
                obj_get = f!!.get(imm)
                if (obj_get != null && obj_get is View) {
                    val v_get = obj_get as View?
                    if (v_get!!.getContext() === destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f!!.set(imm, null) // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
//                        if (QLog.isColorLevel()) {
//                            QLog.d(ReflecterHelper::class.java!!.getSimpleName(), QLog.CLR, "fixInputMethodManagerLeak break, context is not suitable, get_context=" + v_get!!.getContext() + " dest_context=" + destContext)
//                        }
                        break
                    }
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }

        }
    }
}
