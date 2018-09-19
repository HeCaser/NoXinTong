package com.example.panhe.noxintong.network;

import com.example.panhe.noxintong.bean.BaseResponse;
import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.Type;

import okhttp3.Response;

public abstract class JsonCal<T> extends AbsCallback<T>{
    @Override
    public T convertResponse(Response response) {
       Type a =  getClass().getGenericSuperclass();
       Gson gons  = new Gson();

        BaseResponse t = gons.fromJson(response.body().charStream(),a);
        return null;
    }
}
