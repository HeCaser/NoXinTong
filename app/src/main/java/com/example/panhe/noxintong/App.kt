package com.example.panhe.noxintong

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.example.panhe.noxintong.network.NetInterceptor
import com.lzy.okgo.OkGo
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import com.lzy.okgo.https.HttpsUtils
import com.lzy.okgo.model.HttpParams


class App : Application() {

    companion object {
        var context: Context? = null
        fun context(): Context? = context
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        initLogger()
        initOkGo()
        context = this
    }

    private fun initOkGo() {
        // 设置 OkHttpClient 拦截器
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(NetInterceptor())

        // 设置超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)

        //https 信任所有证书,不安全有风险
        val sslParams1 = HttpsUtils.getSslSocketFactory()
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager)

        // 设置 okgo 的参数
        val commonParams = HttpParams()
        commonParams.put("platform", "android")
        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setRetryCount(1)
                .addCommonParams(commonParams)
    }

    private fun initLogger() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.LOG_ENABLE
            }
        })

        Logger.t("XXT")
    }
}