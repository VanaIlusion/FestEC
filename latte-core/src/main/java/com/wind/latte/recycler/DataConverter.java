package com.wind.latte.recycler;

import java.util.ArrayList;

/**
 * 数据转换约束
 * Created by theWind on 2017/8/15.
 */

public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntry> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntry> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }
}
