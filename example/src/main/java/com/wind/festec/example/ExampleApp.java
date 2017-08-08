package com.wind.festec.example;

import android.app.Application;

import com.wind.latte.app.Latte;

/**
 * Created by theWind on 2017/8/9.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .configure();
    }
}
