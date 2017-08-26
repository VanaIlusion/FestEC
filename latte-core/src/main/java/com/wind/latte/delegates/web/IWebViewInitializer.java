package com.wind.latte.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by theWind on 2017/8/23.
 */

public interface IWebViewInitializer {

    WebView iniWebView(WebView webView);

    //针对浏览器本身行为的控制
    WebViewClient initWebViewClient();

    //针对内部页面本身的控制
    WebChromeClient initWebChromeClient();


}
