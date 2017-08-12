package com.wind.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.wind.latte.delegates.LatteDelegate;
import com.wind.latte.ec.R;
import com.wind.latte.ec.R2;
import com.wind.latte.net.RestClient;
import com.wind.latte.net.callback.ISuccess;
import com.wind.latte.utils.ILog;
import com.wind.latte.utils.LatteLogger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by theWind on 2017/8/12.
 */

public class SignInDelegate extends LatteDelegate{

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof  ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()){
            RestClient.builder()
                    .url("SignInUrl......")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json(ILog.TAG,response);
                            SignHandler.onSignIn(response,mISignListener);
                        }
                    })
                    .build()
                    .post();
        }
    }

    //跳转到注册页面
    @OnClick(R2.id.tv_link_sign_up)
    void onClickSignLink() {
       start(new SignUpDelegate());
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeiChat() {

    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;
        if (TextUtils.isEmpty(email) || Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        return isPass;

    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
