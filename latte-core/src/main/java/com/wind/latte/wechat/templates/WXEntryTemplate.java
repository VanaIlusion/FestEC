package com.wind.latte.wechat.templates;

import com.wind.latte.wechat.BaseWXEntryActivity;
import com.wind.latte.wechat.LatteWeChat;

/**
 * Created by theWind on 2017/8/12.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {
    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 微信登录完成后会回到一个我们不需要的Activity:WXEntryActivity
         * 采取的操作:先将它透明，然后无动画finish
         */
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstence().getSignInCallback().onSignInSuccess(userInfo);
    }
}
