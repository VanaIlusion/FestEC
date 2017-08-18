package com.wind.latte.ec.main;

import android.graphics.Color;

import com.wind.latte.delegates.bottom.BaseBottomDelegate;
import com.wind.latte.delegates.bottom.BottomItemDelegate;
import com.wind.latte.delegates.bottom.BottomTabBean;
import com.wind.latte.delegates.bottom.ItemBuilder;
import com.wind.latte.ec.main.index.IndexDelegate;
import com.wind.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by theWind on 2017/8/14.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","首页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new SortDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new SortDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new SortDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
