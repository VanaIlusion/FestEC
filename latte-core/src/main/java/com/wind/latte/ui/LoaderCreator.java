package com.wind.latte.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by theWind on 2017/8/10.
 */

public final class LoaderCreator {

    private static final WeakHashMap<String, Indicator> LOAGING_MAP = new WeakHashMap<>();

    /**
     * 以一种缓存的方式来创建Loader,不用每一次需要使用它是都去反射一次
     * @param type
     * @param context
     * @return
     */
    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOAGING_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOAGING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOAGING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
