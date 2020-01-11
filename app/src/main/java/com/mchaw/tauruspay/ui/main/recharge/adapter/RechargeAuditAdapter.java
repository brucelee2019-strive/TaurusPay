package com.mchaw.tauruspay.ui.main.recharge.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.recharge.RechargeAuditBean;
import com.mchaw.tauruspay.common.util.StringUtils;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2020/1/10 13:44
 * @description:
 */
public class RechargeAuditAdapter extends BaseQuickAdapter<RechargeAuditBean, BaseViewHolder> {

    public RechargeAuditAdapter(@Nullable List<RechargeAuditBean> list) {
        super(R.layout.item_recharge_audit, list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RechargeAuditBean item) {
        helper.addOnClickListener(R.id.btn_audit_btn);
        helper.setText(R.id.tv_recharge_account,item.getName());
        helper.setText(R.id.tv_recharge_amount,"+" + StringUtils.fenToYuan(item.getAmount()));
        helper.setText(R.id.tv_recharge_time,item.getUpdate());
    }
}
