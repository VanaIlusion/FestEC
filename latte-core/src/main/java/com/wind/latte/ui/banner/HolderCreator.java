package com.wind.latte.ui.banner;


import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by theWind on 2017/8/16.
 */

public class HolderCreator implements CBViewHolderCreator<ImageHolder> {
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
