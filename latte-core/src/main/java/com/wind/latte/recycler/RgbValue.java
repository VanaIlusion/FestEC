package com.wind.latte.recycler;

import com.google.auto.value.AutoValue;

/**
 * Created by theWind on 2017/8/17.
 */
//@AutoValue
public abstract class RgbValue {
    public abstract int red();

    public abstract int green();

    public abstract int blue();


    public static RgbValue create(int red, int green, int blue) {
        return IAutoValue_RgbValue.create(red, green, blue);
    }

}
