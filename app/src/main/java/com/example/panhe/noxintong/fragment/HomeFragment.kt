package com.example.panhe.noxintong.fragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.panhe.noxintong.R
import com.example.panhe.noxintong.adapter.NoMoreDataBinder
import com.example.panhe.noxintong.bean.BaseResponse
import com.example.panhe.noxintong.bean.NoMoreData
import com.example.panhe.noxintong.network.CustomJsonCallback
import com.example.panhe.noxintong.network.JsonCallback
import com.example.panhe.noxintong.util.config
import com.example.panhe.noxintong.util.toast
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

class HomeFragment : BaseFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContentView(R.layout.fragment_home)
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
            showErrorView {
                showContentView()
            }
        }
    }
}