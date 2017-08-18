package com.wind.latte.delegates;

/**
 * Created by theWind on 2017/8/9.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegare {

    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
