package com.example.panhe.noxintong.network;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.example.panhe.noxintong.App;
import com.example.panhe.noxintong.bean.BaseResponse;
import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.Type;
import java.util.PriorityQueue;

import javax.xml.validation.Validator;

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
