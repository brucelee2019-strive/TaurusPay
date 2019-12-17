package com.mchaw.tauruspay.ui.main.home.forsale.adapter;

import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.home.ReceivablesBean;
import com.mchaw.tauruspay.common.util.StringUtils;
import com.mchaw.tauruspay.common.util.TimeUtils;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 16:19
 * @description:
 */
public class CollectionListAdapter extends BaseQuickAdapter<ReceivablesBean, BaseViewHolder> {

    public CollectionListAdapter(@Nullable List<ReceivablesBean> data) {
        super(R.layout.item_collection_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ReceivablesBean item) {
        helper.addOnClickListener(R.id.btn_sure);
        helper.setText(R.id.tv_order_num,String.valueOf(item.getCodeid()));
        helper.setText(R.id.tv_order_amount, "￥"+StringUtils.fenToYuan(item.getAmount()));
        helper.setText(R.id.tv_income_amout,"+"+StringUtils.earningsYuan(item.getAmount()));
        helper.setImageResource(R.id.iv_zfb_icon,item.getType()==0?R.drawable.weixin_logo:R.drawable.zhifubao_logo);
        helper.setText(R.id.tv_date, item.getTime());
        helper.setText(R.id.tv_time_show, TimeUtils.timeParse(item.getEndtime()));
    }
}
