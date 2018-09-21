package com.example.panhe.noxintong.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.panhe.noxintong.bean.BaseResponse
import com.example.panhe.noxintong.constant.Urls
import com.example.panhe.noxintong.network.CustomJsonCallback
import com.example.panhe.noxintong.network.OkHttpUtil
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response

class LoginViewModel : ViewModel() {
    val loginResult: MutableLiveData<BaseResponse<String>> = MutableLiveData()

    fun login(phone: String, password: String) {
        OkGo.post<BaseResponse<String>>(Urls.LOGIN).params("phone", phone)
                .params("password", password)
                .params(OkHttpUtil().commonParams)
                .execute(object : CustomJsonCallback<BaseResponse<String>>() {
                    override fun onSuccess(response: Response<BaseResponse<String>>?) {
                        loginResult.value = response?.body()
                    }

                    override fun onError(response: Response<BaseResponse<String>>?) {
                        super.onError(response)
                        loginResult.value = null
                    }
                })
    }
}