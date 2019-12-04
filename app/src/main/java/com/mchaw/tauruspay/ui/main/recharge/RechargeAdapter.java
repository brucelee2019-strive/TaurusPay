package com.mchaw.tauruspay.ui.main.recharge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;
import com.mchaw.tauruspay.common.util.StringUtils;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/4 16:57
 * @description:
 */
public class RechargeAdapter extends BaseQuickAdapter<RechargeBean, BaseViewHolder> {

    public RechargeAdapter(@Nullable List<RechargeBean> list) {
        super(R.layout.item_recharge_list, list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RechargeBean item) {
//        helper.setText(R.id.tv_recharge_result,item);
//        helper.setTextColor(R.id.tv_recharge_result, ContextCompat.getColor(mContext,item.equals("未通过")?R.color.color_news_stick:R.color.color_special));
//        helper.setImageResource(R.id.iv_recharge_state,item.equals("未通过")?R.drawable.cz_icon_jz:R.drawable.cz_icon_cg);
        helper.setText(R.id.tv_sale_num, "订单编号：" + item.getOrderid());
        helper.setText(R.id.tv_sale_time, item.getUpdate());
        helper.setText(R.id.tv_sale_money, "+" + StringUtils.fenToYuan(item.getAmount()));
        switch (item.getStatus()) {
            case 0:
            case 1:
                helper.setText(R.id.tv_recharge_result, "创建");
                helper.setTextColor(R.id.tv_recharge_result, ContextCompat.getColor(mContext,R.color.color_special));
                helper.setImageResource(R.id.iv_recharge_state,R.drawable.cz_icon_cg);
                break;
            case 2:
            case 4:
                helper.setText(R.id.tv_recharge_result,"成功");
                helper.setTextColor(R.id.tv_recharge_result, ContextCompat.getColor(mContext,R.color.color_special));
                helper.setImageResource(R.id.iv_recharge_state,R.drawable.cz_icon_cg);
                break;
            case 3:
                helper.setText(R.id.tv_recharge_result, "未通过");
                helper.setTextColor(R.id.tv_recharge_result, ContextCompat.getColor(mContext,R.color.color_news_stick));
                helper.setImageResource(R.id.iv_recharge_state,R.drawable.cz_icon_jz);
                break;
        }
    }
}
