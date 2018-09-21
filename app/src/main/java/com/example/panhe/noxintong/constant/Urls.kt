package com.example.panhe.noxintong.constant

import com.example.panhe.noxintong.BuildConfig

class Urls() {
    companion object {
        val LOGIN = getUrl(path = "/coupon/userCouponList")
        const val HOST = "wwww.baidu.com"

        fun getUrl(protocol: String = "https", hostname: String = HOST, port: String = "", path: String): String {
            var rHostName = hostname
            var rPath = path


            if (hostname.endsWith("/") && path.startsWith("/")) {
                rHostName = hostname.dropLast(1)
            }

            if (!hostname.endsWith("/") && !path.startsWith("/")) {
                rPath = "/$path"
            }

            return StringBuilder(protocol)
                    .append("://")
                    .append(rHostName)
                    .append(rPath)
                    .toString()
        }
    }
}
