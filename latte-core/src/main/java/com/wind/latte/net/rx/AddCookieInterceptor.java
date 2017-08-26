package com.wind.latte.net.rx;

import com.wind.latte.utils.storage.LattePreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by theWind on 2017/8/25.
 */

public final class AddCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable
                .just(LattePreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String cookie) throws Exception {
                        //给原生API请求附带上WebView拦截下来的cookie
                        builder.addHeader("Cookie",cookie);
                    }
                });
        return chain.proceed(builder.build());
    }
}
