package com.example.panhe.noxintong.fragment

import android.os.Bundle
import android.view.View
import com.example.panhe.noxintong.R
import com.example.panhe.noxintong.adapter.NoMoreDataBinder
import com.example.panhe.noxintong.bean.NoMoreData
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