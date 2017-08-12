package com.wind.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by theWind on 2017/8/11.
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder>{
    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
