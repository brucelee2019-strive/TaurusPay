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
        super(R.layout.item_recharge_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_recharge_result,item);
        helper.setTextColor(R.id.tv_recharge_result, ContextCompat.getColor(mContext,item.equals("未通过")?R.color.color_news_stick:R.color.color_special));
    }
}
