package com.mchaw.tauruspay.ui.main.mine.qrcode.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 19:34
 * @description:
 */
public class QRCodeListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public QRCodeListAdapter(@Nullable List<String> data) {
        super(R.layout.item_qr_code_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_zfb_account, item);
    }
}