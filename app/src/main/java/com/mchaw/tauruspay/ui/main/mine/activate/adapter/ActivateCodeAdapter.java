package com.mchaw.tauruspay.ui.main.mine.activate.adapter;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.activate.ActivateCodeBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/25 17:04
 * @description:
 */
public class ActivateCodeAdapter extends BaseQuickAdapter<ActivateCodeBean, BaseViewHolder> {

    public ActivateCodeAdapter(@Nullable List<ActivateCodeBean> data) {
        super(R.layout.item_activate_code_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ActivateCodeBean item) {
        helper.addOnClickListener(R.id.btn_copy);
        helper.setText(R.id.tv_code,item.getCode());
        helper.setText(R.id.tv_used_account, TextUtils.isEmpty(item.getUsename())?"受用账号:":"受用账号:"+item.getUsename());
        helper.setText(R.id.tv_time, TextUtils.isEmpty(item.getUsename())?"":item.getUpdate());
        helper.setGone(R.id.btn_copy,TextUtils.isEmpty(item.getUsename()));
    }
}
