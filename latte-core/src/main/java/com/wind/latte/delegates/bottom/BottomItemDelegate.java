package com.wind.latte.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;

import com.wind.latte.R;
import com.wind.latte.delegates.LatteDelegate;
import com.wind.latte.utils.IToast;

/**
 * Created by theWind on 2017/8/13.
 */

public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {

    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        /**
         * Fragment有一个奇怪的问题:在我们每次回来的时候，需要把Touch的Focus重新的request
         * 所以需要以下操作，否则会出现回来之后无法触发事件
         */
        final View rootView = getView();
        if ((rootView != null)) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
                IToast.showLong("双击退出" + getString(R.string.app_name));
                mExitTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
                mExitTime = 0;
                return true;//表示消耗了事件
            }
        return false;
    }
}
