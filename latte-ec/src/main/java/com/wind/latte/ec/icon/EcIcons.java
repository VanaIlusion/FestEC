package com.wind.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by theWind on 2017/8/9.
 */

public enum  EcIcons implements Icon{
    icon_scan('\uE67C'),
    icon_ali_pay('\ue66e');

    private char character;
    EcIcons(char character){
        this.character = character;
    }
    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
