package com.wind.latte.ec.luancher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.wind.latte.app.AccountManager;
import com.wind.latte.app.IUserChecker;
import com.wind.latte.delegates.LatteDelegate;
import com.wind.latte.ec.R;
import com.wind.latte.ec.R2;
import com.wind.latte.ui.launcher.ScrollLauncherTag;
import com.wind.latte.utils.ILog;
import com.wind.latte.utils.storage.LattePreference;
import com.wind.latte.utils.timer.BaseTimerTask;
import com.wind.latte.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by theWind on 2017/8/11.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    /**
     * 注意特别是Android库里面使用ButterKnife时
     * 配置文件中一定要加入
     * 插件依赖：apply plugin: 'com.jakewharton.butterknife'//加入其插件作用是生存R2文件，用来生成视图绑定所需要的id
     * 库依赖：annotationProcessor 'com.jakewharton:butterknife-compiler:8.6.0'
     */
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTvTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);//0:延时 1000：间隔时间为1000毫秒
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        //是否打开过App
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            //没有打开过，显示轮播界面
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //打开过，检查用户是否登录过App
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSign() {
                    if (mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);//登陆过
                    }
                }

                @Override
                public void onNotSign() {
                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTvTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
