package com.example.panhe.noxintong.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panhe.noxintong.R
import com.example.panhe.noxintong.adapter.NoMoreDataBinder
import com.example.panhe.noxintong.bean.BaseResponse
import com.example.panhe.noxintong.bean.NoMoreData
import com.example.panhe.noxintong.network.CustomJsonCallback
import com.example.panhe.noxintong.network.JsonCallback
import com.google.gson.reflect.TypeToken
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.AbsCallback
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_home.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val adapter = MultiTypeAdapter()
        var items = Items()
        adapter.register(NoMoreData::class.java, NoMoreDataBinder())
        adapter.items = items
        items.add(NoMoreData())
        adapter.notifyDataSetChanged()
        tvTest.setOnClickListener {
            val getPict ="http://172.16.10.143/auth/account/getValidateCode"
            val code ="http://172.16.10.143/auth/account/getCheckNum"
            val pa = HttpParams()
            val type = TypeToken.get(BaseResponse::class.java).type
            pa.put("validateCode","iyhhb")
            pa.put("mobile","15110068130")
            pa.put("imgKey","oujio")
            OkGo.post<BaseResponse<String>>(code).params(pa).tag(this)
                    .execute(object:CustomJsonCallback<BaseResponse<String>>(){
                override fun onSuccess(response: Response<BaseResponse<String>>?) {
                    Logger.d(response?.body()?.data)
                }

                override fun onError(response: Response<BaseResponse<String>>?) {
                    super.onError(response)
                    Logger.e("出错"+response?.exception?.message)
                }
            })
        }


    }
}