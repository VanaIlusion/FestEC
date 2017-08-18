package com.wind.latte.ui.refresh;

/**
 * Created by theWind on 2017/8/16.
 */

public final class PaginBean {
    //当前是第几页
    private int mPageIndex = 0;
    //总条数
    private int mTotal = 0;
    //一页显示几条
    private int mPageSize = 0;
    //当前已经显示了几条
    private int mCurrentCount = 0;
    //加载延迟
    private int mDelayed = 0;

    public int getPageIndex() {
        return mPageIndex;
    }

    public PaginBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PaginBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PaginBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PaginBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PaginBean setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    PaginBean addIndex() {
        mPageIndex++;
        return this;
    }


}
