package com.mchaw.tauruspay.ui.main.mine.agency.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.agency.AgencyItemBean;
import com.mchaw.tauruspay.common.util.StringUtils;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2020/1/11 14:03
 * @description:
 */
public class AgencyListAdapter extends BaseQuickAdapter<AgencyItemBean, BaseViewHolder> {
    public AgencyListAdapter(@Nullable List<AgencyItemBean> list) {
        super(R.layout.item_agency_list, list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AgencyItemBean item) {
        helper.addOnClickListener(R.id.btn_change_rate);
        helper.setText(R.id.tv_account,item.getName());
        helper.setText(R.id.tv_y_day_point, StringUtils.fenToYuan(item.getYdaydeposit()));
        helper.setText(R.id.tv_y_day_deposit, StringUtils.fenToYuan(item.getYdaypoint()));
        helper.setText(R.id.tv_day_point, StringUtils.fenToYuan(item.getDaydeposit()));
        helper.setText(R.id.tv_day_deposit, StringUtils.fenToYuan(item.getDaypoint()));
        helper.setText(R.id.tv_rate,String.valueOf(item.getRate()));
    }
}
