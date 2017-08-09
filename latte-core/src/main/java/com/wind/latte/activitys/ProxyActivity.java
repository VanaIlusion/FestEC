package com.wind.latte.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.wind.latte.R;
import com.wind.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by theWind on 2017/8/9.
 */

public abstract class ProxyActivity extends SupportActivity {
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        //Fragmente 容器 可理解为FrameLayout
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState==null){//第一次加载
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 小优化：
         * 由于应用程序是单activity架构，当activity被销毁时整个程序也就退出了
         * 故而这里可以做一些垃圾回收的操作（注意：虽然这样写，但这两个方法它不一定会执行）
         */
        System.gc();
        System.runFinalization();
    }
}
