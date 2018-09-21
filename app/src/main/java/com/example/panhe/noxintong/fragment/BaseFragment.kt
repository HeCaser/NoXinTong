package com.example.panhe.noxintong.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panhe.noxintong.R
import kotlinx.android.synthetic.main.error_view.*
import kotlinx.android.synthetic.main.fragment_base.*

open class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base, container, false)
        return view
    }

    // 重写 setContentView 把子类的 view 放在 base 的 一个View 中
    fun setContentView(layoutResID: Int) {
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
}