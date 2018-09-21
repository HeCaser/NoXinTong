package com.example.panhe.noxintong.network

import com.lzy.okgo.model.HttpParams

class OkHttpUtil {

    // 公共参数
     var commonHeaders = HttpParams()

     var commonParams = HttpParams()


    private fun oneMigrateTwo(one: HttpParams, two: HttpParams?): HttpParams {
        if (one == null) throw IllegalArgumentException("argument can not be null")
        if (two != null) {
            one.put(two)
        }
        return one
    }


    fun addCommonParams(pa: HttpParams) {
        commonParams.put(pa)
    }

    fun addCommonHeaders(pa: HttpParams) {
        commonHeaders.put(pa)
    }

    fun addCommonToParam(pa: HttpParams): HttpParams {
        val para = oneMigrateTwo(pa, commonParams)
        return para
    }

    fun addCommonToHeader(pa: HttpParams): HttpParams {
        val para = oneMigrateTwo(pa, commonHeaders)
        return para
    }
}