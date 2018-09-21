package com.example.panhe.noxintong.network

import com.example.panhe.noxintong.App
import com.example.panhe.noxintong.bean.BaseResponse
import com.example.panhe.noxintong.util.toast
import com.google.gson.Gson
import com.lzy.okgo.callback.AbsCallback
import okhttp3.Response
import java.lang.reflect.ParameterizedType

/**
 * 乡信通的json解析
 * 根据不同业务逻辑来处理
 */
abstract class CustomJsonCallback<T>() : AbsCallback<T>() {

    override fun convertResponse(response: Response?): T? {
        val genType = javaClass.genericSuperclass as ParameterizedType
        val params = genType.actualTypeArguments

        // 第二层泛型的类型, 也就是这里传入的 T 的具体类型 例如 BaseResponse<LoginBean>
        val type = params[0] as? ParameterizedType ?: throw IllegalStateException("没有填写泛型参数")

        // 获取第二层数据的真实类型 BaseResponse
        val rawType = type.rawType

        // 获取第二层数据泛型的真实类型,也就是 BaseResponse 中的 T 为 LoginBean
        val typeArgument = type.actualTypeArguments[0]

        val body = response?.body() ?: return null

        val gson = Gson()
        if (rawType != BaseResponse::class.java) {
            val data: T = gson.fromJson(body.charStream(), rawType)
            response.close()
            return data
        } else {
//            if (typeArgument == Unit::class.java){
//
//            }
            val baseResponse: BaseResponse<T> = gson.fromJson(body.charStream(), type)
            response.close()
            val code = baseResponse.errCode
            when (code) {
                0 -> return baseResponse as T
                else -> throw IllegalStateException("错误代码$code")
            }
        }
    }


}