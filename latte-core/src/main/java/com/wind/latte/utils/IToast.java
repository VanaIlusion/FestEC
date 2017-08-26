package com.wind.latte.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.wind.latte.app.Latte;

/**
 * Created by theWind on 2017/8/11.
 */

public class IToast {

    public static void showShort(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(Latte.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showLong(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(Latte.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }
    }

    public static void showShort(Context context, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showLong(Context context, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
    }
}
