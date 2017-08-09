package com.wind.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.wind.latte.delegates.LatteDelegate;
import com.wind.latte.net.RestClient;
import com.wind.latte.net.callback.IError;
import com.wind.latte.net.callback.IFailure;
import com.wind.latte.net.callback.ISuccess;

/**
 * Created by theWind on 2017/8/9.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRequestClient();
    }

    private void testRequestClient() {
        RestClient.builder()
                .url("https://www.baidu.com/")
               // .params("", "")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
