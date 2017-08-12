package com.wind.festec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wind.latte.app.Latte;
import com.wind.latte.ec.database.DatabaseManager;
import com.wind.latte.ec.icon.FontEcModule;
import com.wind.latte.net.interceptors.DebugInterceptor;

/**
 * Created by theWind on 2017/8/9.
 */

public class ExampleAppLication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withDebug(true)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

        initTetho();
        DatabaseManager.getInstance().init(this);
    }

    private void initTetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        );
    }
}
