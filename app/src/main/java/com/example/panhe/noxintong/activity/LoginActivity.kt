package com.example.panhe.noxintong.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.TextUtils
import com.example.panhe.noxintong.R
import com.example.panhe.noxintong.util.StatusBarUtils
import com.example.panhe.noxintong.util.ckick
import com.example.panhe.noxintong.util.isPhoneNumberCorrectOrShowErrorToast
import com.example.panhe.noxintong.util.toast
import com.example.panhe.noxintong.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var viewmodel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.setStatusBarTransparent(this, true)
        setContentView(R.layout.activity_login)
        StatusBarUtils.addStatusBarHeightToMarginByViewID(this, R.id.close)
        initListener()
        viewmodel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        subscribeToModel()
    }

    private fun subscribeToModel() {
        viewmodel.loginResult.observe(this, Observer {
            if (it == null) {
                showErrorView { }
            }
        })
    }

    private fun initListener() {
        close.ckick {
            finish()
        }
        login.ckick {
            login()
        }
        goRegister.ckick {

        }
        forgotPassword.ckick {
        }
    }

    private fun login() {

        val phone = phoneNumber.text.toString()
        val password = password.text.toString()

        if (!isPhoneNumberCorrectOrShowErrorToast(phone)) {
            return
        }

        if (TextUtils.isEmpty(password)) {
            toast("请输入密码")
            return
        }

        showProgressDialog(isTransparentBg = false, isCancelable = false, isCanceledOnTouchOutside = false)

        viewmodel.login(phone, password)

    }
}
