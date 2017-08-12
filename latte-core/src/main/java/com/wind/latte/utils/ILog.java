package com.wind.latte.utils;

import android.util.Log;

import com.wind.latte.app.ConfigKeys;
import com.wind.latte.app.Latte;

/**
 * Created by theWind on 2017/8/11.
 */

public class ILog {
    public static final String TAG = "FestEC";
    private static final boolean IS_DEGUG = Latte.getConfiguration(ConfigKeys.IS_DEBUG);

    /**
     * v级别的Log
     *
     * @param msg
     */
    public static void v(String msg) {
        if (IS_DEGUG) {
            Log.v(TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (IS_DEGUG) {
            Log.v(tag, msg);
        }
    }

    /**
     * d级别的Log
     *
     * @param msg
     */
    public static void d(String msg) {
        if (IS_DEGUG) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (IS_DEGUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * d级别的Log
     *
     * @param msg
     */
    public static void i(String msg) {
        if (IS_DEGUG) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (IS_DEGUG) {
            Log.i(tag, msg);
        }
    }

    /**
     * w级别的Log
     *
     * @param msg
     */
    public static void w(String msg) {
        if (IS_DEGUG) {
            Log.w(TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (IS_DEGUG) {
            Log.w(tag, msg);
        }
    }

    /**
     * e级别的Log
     *
     * @param msg
     */
    public static void e(String msg) {
        if (IS_DEGUG) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (IS_DEGUG) {
            Log.e(tag, msg);
        }
    }
}
