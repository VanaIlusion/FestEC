package com.wind.latte.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.wind.latte.app.Latte;
import com.wind.latte.ec.R;
import com.wind.latte.net.RestClient;
import com.wind.latte.net.callback.ISuccess;
import com.wind.latte.recycler.MultipleFields;
import com.wind.latte.recycler.MultipleItemEntry;
import com.wind.latte.recycler.MultipleRecyclerAdapter;
import com.wind.latte.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by theWind on 2017/8/26.
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectedAll = false;
    private ICartItemListener mICartItemListener = null;

    private double mTotalPrice = 0;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected ShopCartAdapter(List<MultipleItemEntry> data) {
        super(data);

        for (MultipleItemEntry entry : data) {
            final double price = entry.getField(ShopCartItemFields.PRICE);
            final int count = entry.getField(ShopCartItemFields.COUNT);
            final double total = price * count;
            mTotalPrice = mTotalPrice + total;
        }
        //添加购物车item布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    public void setICartItemListener(ICartItemListener iCartItemListener) {
        this.mICartItemListener = iCartItemListener;
    }
    public double getTotalPrice(){
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntry entry) {
        super.convert(holder, entry);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                //取值
                final int id = entry.getField(MultipleFields.ID);
                final String thumb = entry.getField(MultipleFields.IMAGE_URL);
                final String title = entry.getField(ShopCartItemFields.TITLE);
                final String desc = entry.getField(ShopCartItemFields.DESC);
                final int count = entry.getField(ShopCartItemFields.COUNT);
                final double price = entry.getField(ShopCartItemFields.PRICE);

                //取控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);

                //赋值显示
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imgThumb);

                //在左侧勾选渲染之前改变全选与否状态
                entry.setField(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);

                final boolean isSelected = entry.getField(ShopCartItemFields.IS_SELECTED);

                //根据数据状态显示左侧勾选
                if (entry.getField(ShopCartItemFields.IS_SELECTED)) {
                    iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                //添加左侧勾选点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entry.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelected) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entry.setField(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                            entry.setField(ShopCartItemFields.IS_SELECTED, true);
                        }
                    }
                });

                //添加加减事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entry.getField(ShopCartItemFields.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            RestClient.builder()
                                    .url("shop_cart_count.php")
                                    .loader(mContext)
                                    .params("count", currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum--;
                                            tvCount.setText(String.valueOf(countNum));
                                            if (mICartItemListener!=null){
                                                mTotalPrice = mTotalPrice-price;
                                                final double itemTotal = countNum*price;
                                                mICartItemListener.onItemClick(itemTotal);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });

                //添加加减事件
                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entry.getField(ShopCartItemFields.COUNT);
                        RestClient.builder()
                                .url("shop_cart_count.php")
                                .loader(mContext)
                                .params("count", currentCount)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        int countNum = Integer.parseInt(tvCount.getText().toString());
                                        countNum++;
                                        tvCount.setText(String.valueOf(countNum));

                                        if (mICartItemListener!=null){
                                            mTotalPrice = mTotalPrice+price;
                                            final double itemTotal = countNum*price;
                                            mICartItemListener.onItemClick(itemTotal);
                                        }
                                    }
                                })
                                .build()
                                .post();
                    }
                });
                break;
            default:
                break;
        }
    }
}
