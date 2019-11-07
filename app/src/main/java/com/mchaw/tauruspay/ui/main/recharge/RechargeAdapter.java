package com.mchaw.tauruspay.ui.main.recharge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/4 16:57
 * @description:
 */
public class RechargeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RechargeAdapter(@Nullable List<String> data) {
        super(R.layout.item_recharge_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_recharge_result,item);
        helper.setTextColor(R.id.tv_recharge_result, ContextCompat.getColor(mContext,item.equals("未通过")?R.color.color_news_stick:R.color.color_special));
        helper.setImageResource(R.id.iv_recharge_state,item.equals("未通过")?R.drawable.cz_icon_jz:R.drawable.cz_icon_cg);
    }
}
