package com.wind.latte.net.callback;

import android.os.Handler;

import com.wind.latte.ui.loader.LatteLoader;
import com.wind.latte.ui.loader.LoaderStyle;
import com.wind.latte.utils.ILog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by theWind on 2017/8/9.
 */

public class RequestsCallbacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();//Handler尽量声明为static,这样会避免一些内存泄漏

    public RequestsCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {

        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                    ILog.e("-------onSuccess-------");
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
                ILog.e("------onError------");
            }
        }
        stopLoading();

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
            ILog.e("-----onFailure------");
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
            ILog.e("-----onRequestEnd------");
        }
        stopLoading();
    }

    private void stopLoading(){
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (LOADER_STYLE != null) {
                    LatteLoader.stopLoading();
                }
            }
        }, 2000);
    }
}
