package com.wind.latte.delegates.web.event;

import com.wind.latte.utils.LatteLogger;

/**
 * Created by theWind on 2017/8/23.
 */

public class UndefineEvent extends Event{
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent",params);
        return null;
    }
}
