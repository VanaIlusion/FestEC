package com.wind.festec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wind.latte.app.Latte;
import com.wind.festec.example.event.TestEvent;
import com.wind.latte.ec.database.DatabaseManager;
import com.wind.latte.ec.icon.FontEcModule;
import com.wind.latte.net.rx.AddCookieInterceptor;

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
//                .withLoaderDelayed(1000)
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withIcon(new FontEcModule())
                .withApiHost("http://116.196.95.67/RestServer/api/")
                .withJavascriptInterface("latte")
                .withWebEvent("test",new TestEvent())
                //添加Cookie同步拦截器
                .withWebHost("https://www.baidu.com/")
                .withInterceptor(new AddCookieInterceptor())
//                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

//        initTetho();
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
