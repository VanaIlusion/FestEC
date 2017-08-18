package com.wind.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.wind.festec.example.wxapi.WXPayEntryActivity;
import com.wind.latte.activitys.ProxyActivity;
import com.wind.latte.app.Latte;
import com.wind.latte.delegates.LatteDelegate;
import com.wind.latte.ec.luancher.ILauncherListener;
import com.wind.latte.ec.luancher.OnLauncherFinishTag;
import com.wind.latte.ec.main.EcBottomDelegate;
import com.wind.latte.ec.sign.ISignListener;
import com.wind.latte.ec.sign.SignInDelegate;
import com.wind.latte.utils.IToast;

import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏ActionBar
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //将当前的Activity作为微信拉取回调的Activity
        Latte.getConfigurator().withWeChatActivity(this);
        //StatusBar隐藏（沉浸式状态栏）
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
//        return  new ExampleDelegate();
//        return new LauncherDelegate();
        return new EcBottomDelegate();
    }

    @Override
    public void onSignInSuccess() {
        IToast.showLong("登录成功");
        start(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        IToast.showLong("注册成功");
        start(new EcBottomDelegate());
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
//                startWithPop(new EcBottomDelegate());
                start(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
//                startWithPop(new SignInDelegate());
                start(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
