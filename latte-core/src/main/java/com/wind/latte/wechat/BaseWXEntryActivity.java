package com.wind.latte.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wind.latte.net.RestClient;
import com.wind.latte.net.callback.IError;
import com.wind.latte.net.callback.IFailure;
import com.wind.latte.net.callback.ISuccess;
import com.wind.latte.utils.ILog;

/**
 * Created by theWind on 2017/8/13.
 */

public abstract class BaseWXEntryActivity extends BaseWXActivity {

    /**
     * 用户登录成功后回调
     *
     * @param response
     */
    protected abstract void onSignInSuccess(String response);

    /**
     * 微信发送请求到第三方应用后的回调，这里我们用不到
     *
     * @param baseReq
     */
    @Override
    public void onReq(BaseReq baseReq) {

    }

    /**
     * 第三方应用发送请求到微信后的回调
     *
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp) baseResp).code;//获取请求的CODE
        final StringBuilder authUrl = new StringBuilder();
        authUrl
                .append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(LatteWeChat.APP_ID)
                .append("&secret=")
                .append(LatteWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");

        ILog.e("authUrl = " + authUrl.toString());

        //通过code获取access_token和openid
        getAuth(authUrl.toString());
    }

    private void getAuth(String authUrl) {
        RestClient.builder()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        final JSONObject authObj = JSON.parseObject(response);
                        final String accessToken = authObj.getString("access_token");
                        final String openId = authObj.getString("openid");

                        final StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl
                                .append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");//用户信息以中文形式返回

                        ILog.e("userInfoUrl = " + userInfoUrl.toString());
                    }
                })
                .build()
                .get();
    }

    /**
     * 获取用户的真正信息
     *
     * @param userInfoUrl
     */
    private void getUserInfo(String userInfoUrl) {
        RestClient
                .builder()
                .url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        onSignInSuccess(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
