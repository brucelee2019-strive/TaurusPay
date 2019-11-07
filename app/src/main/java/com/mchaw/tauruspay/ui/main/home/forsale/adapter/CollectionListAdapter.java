package com.mchaw.tauruspay.ui.main.home.forsale.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 16:19
 * @description:
 */
public class CollectionListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CollectionListAdapter(@Nullable List<String> data) {
        super(R.layout.item_collection_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_order_num,item);
    }
}
