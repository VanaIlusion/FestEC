package com.wind.latte.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Created by theWind on 2017/8/15.
 */

public class MultipleItemEntry implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUENE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE = new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUENE);

    MultipleItemEntry(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }

    public static final MultipleItemEntryBuilder builder() {
        return new MultipleItemEntryBuilder();
    }

    /**
     * 控制RecycleView每一个Item的样式和表现特征
     *
     * @return
     */
    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getField(Object ket) {
        return (T) FIELDS_REFERENCE.get().get(ket);
    }

    public final LinkedHashMap<?, ?> getFields() {
        return FIELDS_REFERENCE.get();
    }

    public final MultipleItemEntry setField(Object key, Object value) {
        FIELDS_REFERENCE.get().put(key, value);
        return this;
    }

}
