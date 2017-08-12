package com.wind.latte.ec.sign;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wind.latte.app.AccountManager;
import com.wind.latte.ec.database.DatabaseManager;
import com.wind.latte.ec.database.UserProfile;

/**
 * Created by theWind on 2017/8/12.
 */

public class SignHandler {
    public static void onSignUp(String response,ISignListener signListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        //将用户信息保存在本地数据库
        final UserProfile profile=new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getmDao().insert(profile);

        //已经注册并登录成功
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }

    public static void onSignIn(String response,ISignListener signListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile=new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getmDao().insert(profile);

        //已经注册成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }
}
