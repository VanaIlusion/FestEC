package com.wind.latte.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wind.latte.recycler.DataConverter;
import com.wind.latte.recycler.ItemType;
import com.wind.latte.recycler.MultipleFields;
import com.wind.latte.recycler.MultipleItemEntry;

import java.util.ArrayList;

/**
 * Created by theWind on 2017/8/18.
 */

public class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntry> convert() {
        final ArrayList<MultipleItemEntry> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON
                .parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            final MultipleItemEntry entry = MultipleItemEntry.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTIVAL_MENU_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, name)//都不选中
                    .setField(MultipleFields.TAG, false)
                    .build();
            dataList.add(entry);
            dataList.get(0).setField(MultipleFields.TAG, true);//默认选中第一个
        }
        return dataList;
    }
}
