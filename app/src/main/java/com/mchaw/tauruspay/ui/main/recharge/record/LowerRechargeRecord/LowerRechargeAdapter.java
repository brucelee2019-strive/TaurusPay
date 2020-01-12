package com.mchaw.tauruspay.ui.main.recharge.record.LowerRechargeRecord;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.recharge.RechargeAuditBean;
import com.mchaw.tauruspay.common.util.StringUtils;

import java.util.List;

/**
 * @author : Bruce Lee
 * @date : 2020/1/12 0012 14:59
 * @description :
 */
public class LowerRechargeAdapter extends BaseQuickAdapter<RechargeAuditBean, BaseViewHolder> {

    public LowerRechargeAdapter(@Nullable List<RechargeAuditBean> list) {
        super(R.layout.item_lower_recharge_result, list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RechargeAuditBean item) {
        helper.setText(R.id.tv_recharge_account,item.getName());
        helper.setText(R.id.tv_recharge_amount,"+" + StringUtils.fenToYuan(item.getAmount()));
        helper.setText(R.id.tv_recharge_time,item.getUpdate());
        helper.setText(R.id.btn_audit_btn,item.getStatus()==4?"已同意":"已拒绝");
    }
}
