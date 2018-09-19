package com.example.panhe.noxintong.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panhe.noxintong.R
import com.example.panhe.noxintong.bean.NoMoreData
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by hepan on 2018/8/27.
 */

class NoMoreDataBinder : ItemViewBinder<NoMoreData, NoMoreDataBinder.ViewHolder>() {


    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.no_more_data, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: NoMoreData) {
        holder.itemData = item
        holder.setData()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var itemData: NoMoreData
        fun setData() {

        }
    }

}