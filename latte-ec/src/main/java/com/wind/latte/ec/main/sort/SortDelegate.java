package com.wind.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wind.latte.delegates.bottom.BottomItemDelegate;
import com.wind.latte.ec.R;
import com.wind.latte.net.RestClient;
import com.wind.latte.net.callback.ISuccess;
import com.wind.latte.utils.ILog;

/**
 * Created by theWind on 2017/8/14.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        RestClient.builder()
//                .url("user_profile.php")
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        ILog.e(response);
//                    }
//                })
//                .build()
//                .get();
    }
}
