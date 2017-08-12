package com.wind.latte.ec.luancher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.wind.latte.app.AccountManager;
import com.wind.latte.app.IUserChecker;
import com.wind.latte.delegates.LatteDelegate;
import com.wind.latte.ec.R;
import com.wind.latte.ui.launcher.LauncherHolderCreator;
import com.wind.latte.ui.launcher.ScrollLauncherTag;
import com.wind.latte.utils.storage.LattePreference;

import java.util.ArrayList;

/**
 * Created by theWind on 2017/8/11.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTERGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    private void initBanner() {
        INTERGERS.add(R.mipmap.launcher_01);
        INTERGERS.add(R.mipmap.launcher_02);
        INTERGERS.add(R.mipmap.launcher_03);
        INTERGERS.add(R.mipmap.launcher_04);
        INTERGERS.add(R.mipmap.launcher_05);
        mConvenientBanner.
                setPages(new LauncherHolderCreator(), INTERGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);//不循环
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }


    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTERGERS.size() - 1) {
            //保存第一次进入APP的flag
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSign() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);//登录成功
                    }
                }

                @Override
                public void onNotSign() {
                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                }
            });
        }
    }
}
