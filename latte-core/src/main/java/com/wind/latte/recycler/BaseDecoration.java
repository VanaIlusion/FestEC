package com.wind.latte.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by theWind on 2017/8/17.
 */

public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }
}
