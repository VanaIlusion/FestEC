package com.wind.latte.delegates.bottom;

/**
 * Created by theWind on 2017/8/13.
 */

public final class BottomTabBean {

    private final  CharSequence ICON;
    private final  CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
