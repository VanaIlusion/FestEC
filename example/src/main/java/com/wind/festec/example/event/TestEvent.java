package com.wind.festec.example.event;

import android.webkit.WebView;

import com.wind.latte.delegates.web.event.Event;
import com.wind.latte.utils.ILog;
import com.wind.latte.utils.IToast;

/**
 * Created by theWind on 2017/8/23.
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        if (getAction().equals("test")){
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    IToast.showLong(getContext(),getAction());
                    webView.evaluateJavascript("nativeCall();",null);
                }
            });
        }
        return null;
    }
}
