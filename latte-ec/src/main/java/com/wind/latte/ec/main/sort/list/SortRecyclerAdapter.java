package com.wind.latte.ec.main.sort.list;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.wind.latte.delegates.LatteDelegate;
import com.wind.latte.ec.R;
import com.wind.latte.ec.main.sort.SortDelegate;
import com.wind.latte.ec.main.sort.content.ContentDelegate;
import com.wind.latte.recycler.ItemType;
import com.wind.latte.recycler.MultipleFields;
import com.wind.latte.recycler.MultipleItemEntry;
import com.wind.latte.recycler.MultipleRecyclerAdapter;
import com.wind.latte.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by theWind on 2017/8/18.
 */

public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;

    protected SortRecyclerAdapter(List<MultipleItemEntry> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;

        //添加垂直菜单布局
        addItemType(ItemType.VERTIVAL_MENU_LIST, R.layout.item_vertical_menu_list);

    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntry entry) {
        super.convert(holder, entry);
        switch (holder.getItemViewType()) {
            case ItemType.VERTIVAL_MENU_LIST:
                final String text = entry.getField(MultipleFields.TEXT);
                final boolean isClicked = entry.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);

                            //更新选中的item
                            entry.setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;

                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });
                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_while));
                }
                holder.setText(R.id.tv_vertical_item_name, text);
                break;
            default:
                break;
        }
    }

    private void showContent(int contendId){
        final ContentDelegate delegate = ContentDelegate.newInstance(contendId);
        switchContent(delegate);
    }

    private void switchContent(ContentDelegate delegate){
        final LatteDelegate contentDelegate = DELEGATE.findChildFragment(ContentDelegate.class);
        if (contentDelegate!=null){
            contentDelegate.replaceFragment(delegate,false);
        }
    }
}
