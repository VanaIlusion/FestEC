package com.wind.latte.ec.sign;

/**
 * Created by theWind on 2017/8/12.
 */

public interface ISignListener {
    /**
     * 登录成功
     */
    void onSignInSuccess();

    /***
     * 注册成功
     */
    void onSignUpSuccess();
}
