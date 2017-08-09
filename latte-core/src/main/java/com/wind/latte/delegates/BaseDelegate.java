package com.wind.latte.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by theWind on 2017/8/9.
 * 不希望使用者new出它的实例，所以声明为抽象类
 */

public abstract class BaseDelegate extends SwipeBackFragment {

    @SuppressWarnings("SpellCheckingInspection")//忽略单词拼写错误的提示
    private Unbinder mUnbinder = null;

    public abstract Object setLayout();

    /**
     * 绑定视图之后的操作
     *
     * @param savedInstanceState
     * @param rootView
     */
    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);//false:表示没有父布局
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }
        if (rootView != null) {
            //绑定资源(fragment 和 rootView)
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //视图销毁时，解除绑定
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
