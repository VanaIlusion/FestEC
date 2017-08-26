package com.wind.latte.ec.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wind.latte.recycler.DataConverter;
import com.wind.latte.recycler.MultipleFields;
import com.wind.latte.recycler.MultipleItemEntry;

import java.util.ArrayList;

/**
 * Created by theWind on 2017/8/26.
 */

public class ShopCartDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntry> convert() {
        final ArrayList<MultipleItemEntry> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String desc = data.getString("desc");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");

            final MultipleItemEntry entity = MultipleItemEntry.builder()
                    .setField(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(ShopCartItemFields.TITLE, title)
                    .setField(ShopCartItemFields.DESC, desc)
                    .setField(ShopCartItemFields.COUNT, count)
                    .setField(ShopCartItemFields.PRICE, price)
                    .setField(ShopCartItemFields.IS_SELECTED,false)
                    .setField(ShopCartItemFields.POSITION,i)
                    .build();

            dataList.add(entity);
        }
        return dataList;
    }
}
