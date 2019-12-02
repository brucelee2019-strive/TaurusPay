package com.mchaw.tauruspay.ui.main.home.transferaccounts.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.home.TransferUsedAccountsBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/12/2 9:47
 * @description:
 */
public class UsedAccountAdapter extends BaseQuickAdapter<TransferUsedAccountsBean,BaseViewHolder> {
    public UsedAccountAdapter(@Nullable List<TransferUsedAccountsBean> data) {
        super(R.layout.item_used_account,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TransferUsedAccountsBean item) {
        helper.setText(R.id.tv_account,item.getAccount());
        helper.setText(R.id.tv_name,item.getName());
    }
}
