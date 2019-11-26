package com.mchaw.tauruspay.ui.main.mine.bill.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.activate.ActivateCodeBean;
import com.mchaw.tauruspay.bean.bill.BillBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/25 18:31
 * @description:
 */
public class BillAdapter extends BaseQuickAdapter<BillBean, BaseViewHolder> {

    public BillAdapter(@Nullable List<BillBean> data) {
        super(R.layout.item_bill_fragment, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BillBean item) {
        helper.setText(R.id.tv_order_code,item.getOrderid());
        helper.setText(R.id.tv_order_time,item.getUpdate());
    }
}
