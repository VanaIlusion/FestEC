package com.wind.latte.utils.timer;

import java.util.TimerTask;

/**
 * 计时器
 * Created by theWind on 2017/8/11.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {

        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
