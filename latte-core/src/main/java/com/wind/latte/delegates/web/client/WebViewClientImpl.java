package com.wind.latte.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wind.latte.app.ConfigKeys;
import com.wind.latte.app.Latte;
import com.wind.latte.delegates.IPageLoadListener;
import com.wind.latte.delegates.web.WebDelegate;
import com.wind.latte.delegates.web.route.Router;
import com.wind.latte.ui.loader.LatteLoader;
import com.wind.latte.utils.LatteLogger;
import com.wind.latte.utils.storage.LattePreference;

/**
 * Created by theWind on 2017/8/23.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    private IPageLoadListener mIPageLoadListener = null;

    final Handler mHandler = Latte.getHandler();

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    /**
     * @param view
     * @param url
     * @return：如果是false就由WebView来处理事件，是true就由我们来处理
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        //路由的截断和处理
//        return super.shouldOverrideUrlLoading(view, url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);
    }

    //获取浏览器cookie
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        /**
         * 注意：这里的Cookie和API请求的Cookie是不一样的，这个在网页不可见
         */
        final String webHost = Latte.getConfiguration(ConfigKeys.WEB_HOST);
        if (webHost != null) {
            if (manager.hasCookies()) {
                final String cookieStr = manager.getCookie(webHost);
                if (cookieStr != null && cookieStr.equals("")) {
                    LattePreference.addCustomAppProfile("cookie", cookieStr);
                }
            }
        }
    }
}
