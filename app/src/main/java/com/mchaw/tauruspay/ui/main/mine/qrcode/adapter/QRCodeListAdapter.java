package com.mchaw.tauruspay.ui.main.mine.qrcode.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 19:34
 * @description:
 */
public class QRCodeListAdapter extends BaseQuickAdapter<QRCodeGroupBean, BaseViewHolder> {

    public QRCodeListAdapter(@Nullable List<QRCodeGroupBean> data) {
        super(R.layout.item_qr_code_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QRCodeGroupBean item) {
        helper.addOnClickListener(R.id.cl_303,R.id.cl_313,R.id.cl_785,R.id.cl_786,R.id.cl_1215,R.id.cl_1216,R.id.cl_2515,
                R.id.cl_2516,R.id.cl_4985,R.id.cl_4988,R.id.cl_7988,R.id.cl_9988
        ,R.id.tv_show_order_list);
        helper.setText(R.id.tv_zfb_account, item.getAccount());
        helper.setText(R.id.tv_zfb_nike_name,item.getNick());
    }
}