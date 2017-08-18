package com.wind.latte.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by theWind on 2017/8/13.
 */

public abstract class BaseWXActivity extends AppCompatActivity implements IWXAPIEventHandler{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //必须写在onCreate中
        LatteWeChat.getInstence().getWXAPI().handleIntent(getIntent(),this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //保险起见在这里也调用一次，保证在各种手机上万无一失
        setIntent(intent);
        LatteWeChat.getInstence().getWXAPI().handleIntent(getIntent(),this);
    }
}
