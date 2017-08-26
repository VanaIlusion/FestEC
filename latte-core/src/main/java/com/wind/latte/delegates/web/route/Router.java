package com.wind.latte.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.wind.latte.delegates.LatteDelegate;
import com.wind.latte.delegates.web.WebDelegate;
import com.wind.latte.delegates.web.WebDelegateImpl;

/**
 * Created by theWind on 2017/8/23.
 */

public class Router {
    /**
     * 线程安全的惰性单例
     */

    private Router() {
    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * @param delegate
     * @param url
     * @return:如果是false就由WebView来处理事件，是true就由我们来处理
     */
    public final boolean handleWebUrl(WebDelegate delegate, String url) {
        //如果是的电话协议
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }

        final LatteDelegate topDelegate = delegate.getTopDelegate();

//        final LatteDelegate parentDelegate = delegate.getParentDelegate();
//        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
//        if (parentDelegate == null) {
//            delegate.start(webDelegate);
//        } else {
//            parentDelegate.start(webDelegate);
//        }
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.start(webDelegate);
        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null!");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private final void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public final void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
//        context.startActivity(intent);
        ContextCompat.startActivity(context, intent, null);

    }
}
