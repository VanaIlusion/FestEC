package com.wind.latte.net;

import android.content.Context;

import com.wind.latte.net.callback.IError;
import com.wind.latte.net.callback.IFailure;
import com.wind.latte.net.callback.IRequest;
import com.wind.latte.net.callback.ISuccess;
import com.wind.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

/**
 * Created by theWind on 2017/8/9.
 * 构建者模式
 */

public class RestClientBuilder {

    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest= null;
    private ISuccess mISuccess= null;
    private IFailure mIFailure= null;
    private IError mIError= null;
    private ResponseBody mBody= null;
    private Context mContext= null;
    private LoaderStyle mLoaderStyle= null;

    //不允许外部包的类new它的实例，本包内可以，不同于private
    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {

        this.PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = ResponseBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iSuccess) {
        this.mIRequest = iSuccess;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }


    public final RestClientBuilder error(IError iErrori) {
        this.mIError = iErrori;
        return this;
    }

    public final RestClientBuilder loader(Context context,LoaderStyle loaderStyle){
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }
    public final RestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;//默认的loading样式;
        return this;
    }
    public final  RestClient build(){
        return new RestClient(mUrl,PARAMS,mIRequest,mISuccess,mIFailure,mIError,mBody,mContext,mLoaderStyle);
    }
}
