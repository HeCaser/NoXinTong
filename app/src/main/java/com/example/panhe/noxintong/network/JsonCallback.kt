package com.example.panhe.noxintong.network

import com.google.gson.Gson
import com.lzy.okgo.callback.AbsCallback
import okhttp3.Response
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


abstract class JsonCallback<T> : AbsCallback<T> {
    var type: Type? = null
    var clazz: Class<T>? = null

    constructor()
    constructor(type: Type) {
        this.type = type
    }

    constructor(clazz: Class<T>) {
        this.clazz = clazz
    }

    override fun convertResponse(response: Response?): T? {
        val body = response?.body() ?: return null
        val gson = Gson()
        return when {
            type != null -> gson.fromJson(body.charStream(), type)
            clazz != null -> gson.fromJson(body.charStream(), clazz)
            else -> {
                // 获取本接口类型,然后获取具体type,由于java的泛型局限性,只能获取一层继承结构,因此若继承此类
                // 需要传入准确的 type 或者 clazz
                val genType = javaClass.genericSuperclass as ParameterizedType
                val type = genType.actualTypeArguments[0]
                gson.fromJson(body.charStream(), type)
            }
        }
    }
}