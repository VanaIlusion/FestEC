package com.wind.latte.ec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.wind.latte.delegates.bottom.BottomItemDelegate;
import com.wind.latte.ec.R;
import com.wind.latte.ec.R2;
import com.wind.latte.net.RestClient;
import com.wind.latte.net.callback.ISuccess;
import com.wind.latte.recycler.MultipleItemEntry;
import com.wind.latte.utils.ILog;
import com.wind.latte.utils.IToast;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by theWind on 2017/8/26.
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, ICartItemListener {

    private ShopCartAdapter mAdapter = null;
    private int mIconSelectedCount = 0;
    private double mTotalPrice = 0.00;

    //购物车标记数量
    private int mCurrentCount = 0;//当前选中的要删除的item数量
    private int mTotalCount = 0;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectedAll = null;

    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem;

    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;


    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelected() {
        final int tag = (int) mIconSelectedAll.getTag();
        if (tag == 0) {
            mIconSelectedAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectedAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectedAll.setTextColor(Color.GRAY);
            mIconSelectedAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            //更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        deleteSelected();

//        final List<MultipleItemEntry> data = mAdapter.getData();
//        //要删除的数据
//        final List<MultipleItemEntry> deleteEntities = new ArrayList<>();
//        for (MultipleItemEntry entry : data) {
//            final boolean isSelected = entry.getField(ShopCartItemFields.IS_SELECTED);
//            if (isSelected) {
//                deleteEntities.add(entry);
//            }
//        }
//        for (MultipleItemEntry entry : deleteEntities) {
//            int removePosition;
//            final int entityPosition = entry.getField(ShopCartItemFields.POSITION);
//            if (entityPosition > mCurrentCount) {
//                removePosition = entityPosition - (mTotalCount - mCurrentCount);
//            } else {
//                removePosition = entityPosition;
//            }
//            if (removePosition <= mAdapter.getItemCount()) {
//                mAdapter.remove(removePosition);
//                mCurrentCount = mAdapter.getItemCount();
//                //更新数据
//                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
//            }
//        }
    }


    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay(){

    }

    /**
     * 创建订单，注意 它和支付是没有关系的
     */
    private void createOrder(){
        final String orderUrl = "";
        final WeakHashMap<String,Object> orderParams = new WeakHashMap<>();
        orderParams.put("userId","");
        orderParams.put("amount",0.01);
        orderParams.put("comment","测试支付");

        orderParams.put("type",1);
        orderParams.put("ordertype",0);
        orderParams.put("isanonymous",true);
        orderParams.put("followeduser",0);
        RestClient.builder()
                .url(orderUrl).loader(getContext())
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //进行具体的支付
                    }
                })
                .build()
                .post();


    }

    private void deleteSelected() {
        final List<MultipleItemEntry> data = mAdapter.getData();
        ArrayList<MultipleItemEntry> newData = new ArrayList<>();
        for (MultipleItemEntry entry : data) {
            final boolean isSelected = entry.getField(ShopCartItemFields.IS_SELECTED);
            if (!isSelected) {
                entry.setField(ShopCartItemFields.IS_SELECTED,false);
                newData.add(entry);
            }
        }
        mAdapter.getData().clear();
        mAdapter.getData().addAll(newData);
        newData.clear();
        mIconSelectedAll.setTextColor(Color.GRAY);
        mIconSelectedAll.setTag(0);
        mAdapter.setIsSelectedAll(false);
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        mIconSelectedAll.setTextColor(Color.GRAY);
        mIconSelectedAll.setTag(0);
        mAdapter.setIsSelectedAll(false);
        checkItemCount();
    }

    private void checkItemCount(){
        final int count  = mAdapter.getItemCount();
        if (count==0){
            final View stubView = mStubNoItem.inflate();
            final AppCompatTextView tvToBuy = (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IToast.showLong("去购物！");
                }
            });
            mRecyclerView.setVisibility(View.GONE);
        }else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectedAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_cart.php")
                .loader(getContext())
                .success(this)
                .build().
                get();
    }

    @Override
    public void onSuccess(String response) {
        final ArrayList<MultipleItemEntry> data = new ShopCartDataConverter().setJsonData(response).convert();
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mAdapter = new ShopCartAdapter(data);
        mAdapter.setICartItemListener(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
        checkItemCount();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }
}
