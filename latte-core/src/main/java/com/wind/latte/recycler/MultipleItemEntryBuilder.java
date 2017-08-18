package com.wind.latte.recycler;

import java.util.LinkedHashMap;

/**
 * MultipleItemEntry的建造者
 * Created by theWind on 2017/8/15.
 */

public class MultipleItemEntryBuilder {

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleItemEntryBuilder() {
        //清除之前的数据
        FIELDS.clear();
    }

    public final MultipleItemEntryBuilder setItemTpye(int itemTpye) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemTpye);
        return this;
    }

    public final MultipleItemEntryBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleItemEntryBuilder setFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntry build() {
        return new MultipleItemEntry(FIELDS);
    }
}
