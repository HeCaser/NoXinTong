package com.example.panhe.noxintong.network

import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import org.json.JSONArray
import org.json.JSONObject

class NetInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val copy = request.newBuilder().build()
        val reqBodyStr = if (copy.body() != null) {
            val buffer = Buffer()
            copy.body()?.writeTo(buffer)
            buffer.readUtf8()
        } else {
            ""
        }
        val response = chain.proceed(request)
        val responseBody = response.peekBody(1024 * 1024)
        val rs = responseBody.string()


        val str = if (rs.startsWith("{")) {
            val jsonObject = JSONObject(rs)
            jsonObject.toString(2)
        } else if (rs.startsWith("[")) {
            val jsonArray = JSONArray(rs)
            jsonArray.toString(2)
        } else {
            rs ?: ""
        }

        val reqStr = String.format("发送请求 [%s] \n请求方式: %s \nheaders:\n %s \nbody:\n %s",
                request.url(),
                request.method(),
                request.headers(),
                reqBodyStr
        )

        val resStr = String.format("接收响应：[%s] , , 返回json:\n%s",
                response.request().url(),
                str
        )

        Logger.d("$reqStr\n\n-------------------------------------\n\n$resStr")

        return response

    }
}