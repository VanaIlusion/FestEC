package com.wind.latte.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.wind.latte.app.Latte;

/**
 * Created by theWind on 2017/8/10.
 */

public class DimenUtil {
    /**
     * 获取屏幕的宽
     * @return
     */
    public static int getScreenWidth(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    /**
     * 获取屏幕的高
     * @return
     */
    public static int getScreenHeight(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
