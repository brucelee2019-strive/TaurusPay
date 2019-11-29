package com.mchaw.tauruspay.ui.main.recharge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.recharge.RechargeTraBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/11 17:37
 * @description:
 */
public class RechargeNextAdapter extends BaseQuickAdapter<RechargeTraBean, BaseViewHolder> {
    public RechargeNextAdapter(@Nullable List<RechargeTraBean> list) {
        super(R.layout.item_recharge_next_msg, list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RechargeTraBean item) {
        helper.addOnClickListener(R.id.btn_copy_btn);
        helper.setText(R.id.tv_income_coin,item.getTitle());
        helper.setText(R.id.tv_income_num,item.getContent());
    }
}
