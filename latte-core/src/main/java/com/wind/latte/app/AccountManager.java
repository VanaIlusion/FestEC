package com.wind.latte.app;

import com.wind.latte.utils.storage.LattePreference;

/**
 * Created by theWind on 2017/8/12.
 */

public class AccountManager {
    private enum  SignTag{
        SIGN_TAG
    }

    /**
     * 保存用户登录的状态，登录后调用
     * @param state
     */
    public  static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    /**
     * 判断是否已经登录
     * @return
     */
    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSign();
        }else {
            checker.onNotSign();
        }
    }
}
