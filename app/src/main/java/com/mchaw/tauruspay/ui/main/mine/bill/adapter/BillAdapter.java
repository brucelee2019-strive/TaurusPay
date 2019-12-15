package com.mchaw.tauruspay.ui.main.mine.bill.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.activate.ActivateCodeBean;
import com.mchaw.tauruspay.bean.bill.BillBean;
import com.mchaw.tauruspay.common.util.StringUtils;

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
        helper.setText(R.id.tv_order_code, item.getOrderid());
        helper.setText(R.id.tv_order_time, item.getUpdate());
        setStatus(helper,item.getMainclass(),item.getSubclass(),item.getAmount());
    }

    private void setStatus(BaseViewHolder helper, int mainclass, int subclass,int amount) {
        switch (mainclass) {
            case 1://充值日志
                setChongzhi(helper,subclass,amount);
                break;
            case 2://销售日志
                setXiaoshou(helper,subclass,amount);
                break;
            case 3://转账日志
                setZhuanzhang(helper,subclass,amount);
                break;
            default:
                break;
        }
    }

    private void setChongzhi(BaseViewHolder helper, int subclass,int amount) {
        switch (subclass) {
            case 0://等待充值
                helper.setText(R.id.tv_order_type, "等待充值");
                helper.setImageResource(R.id.imageView8,R.drawable.czrz_dengdaichongzhi);
                helper.setText(R.id.tv_order_income, StringUtils.fenToYuan(amount));
                helper.setTextColor(R.id.tv_order_income, ContextCompat.getColor(mContext,R.color.color_black_9));
                break;
            case 1://已完成充值
                helper.setText(R.id.tv_order_type, "已完成充值");
                helper.setImageResource(R.id.imageView8,R.drawable.czrz_chognzhiwancheng);
                helper.setText(R.id.tv_order_income, StringUtils.fenToYuan(amount));
                helper.setTextColor(R.id.tv_order_income, ContextCompat.getColor(mContext,R.color.e94630));
                break;
            case 2://审核通过
                helper.setText(R.id.tv_order_type, "审核通过");
                helper.setImageResource(R.id.imageView8,R.drawable.czrz_shenhetongguo);
                helper.setText(R.id.tv_order_income, StringUtils.fenToYuan(amount));
                helper.setTextColor(R.id.tv_order_income, ContextCompat.getColor(mContext,R.color.color_black_9));
                break;
            case 3://审核拒绝
                helper.setText(R.id.tv_order_type, "审核拒绝");
                helper.setImageResource(R.id.imageView8,R.drawable.czrz_shenhejujue);
                helper.setText(R.id.tv_order_income, StringUtils.fenToYuan(amount));
                helper.setTextColor(R.id.tv_order_income, ContextCompat.getColor(mContext,R.color.color_black_9));
                break;
            case 4://发货完成
                helper.setText(R.id.tv_order_type, "发货完成");
                helper.setImageResource(R.id.imageView8,R.drawable.czrz_fahuowancheng);
                helper.setText(R.id.tv_order_income, StringUtils.fenToYuan(amount));
                helper.setTextColor(R.id.tv_order_income, ContextCompat.getColor(mContext,R.color.color_black_9));
                break;
            case 5://发货失败
                helper.setText(R.id.tv_order_type, "发货失败");
                helper.setImageResource(R.id.imageView8,R.drawable.czrz_fahuoshibai);
                helper.setText(R.id.tv_order_income, StringUtils.fenToYuan(amount));
                helper.setTextColor(R.id.tv_order_income, ContextCompat.getColor(mContext,R.color.color_black_9));
                break;
            default:
                break;
        }
    }

    private void setXiaoshou(BaseViewHolder helper, int subclass,int amount) {
        switch (subclass) {
            case 0://接单扣款
                helper.setText(R.id.tv_order_type, "接单扣款");
                helper.setImageResource(R.id.imageView8,R.drawable.xsrz_jiedankoukuan);
                helper.setText(R.id.tv_order_income, "-"+StringUtils.fenToYuan(amount));
                helper.setTextColor(R.id.tv_order_income, ContextCompat.getColor(mContext,R.color.color_black_0));
                break;
            case 1://接单返还
                helper.setText(R.id.tv_order_type, "订单退费");
                helper.setImageResource(R.id.imageView8,R.drawable.xsrz_jiedantuikuan);
                helper.setText(R.id.tv_order_income, StringUtils.fenToYuan(amount));
                helper.setTextColor(R.id.tv_order_income, ContextCompat.getColor(mContext,R.color.e94630));
                break;
            case 2://结单红利
                helper.setText(R.id.tv_order_type, "结单红利");
                helper.setImageResource(R.id.imageView8,R.drawable.xsrz_jiedanhongli);
                helper.setText(R.id.tv_order_income, StringUtils.fenToYuan(amount));
                helper.setTextColor(R.id.tv_order_income, ContextCompat.getColor(mContext,R.color.e94630));
                break;
            case 3://代理分红
                helper.setText(R.id.tv_order_type, "代理分红");
                helper.setImageResource(R.id.imageView8,R.drawable.xsrz_dailifenhong);
                helper.setText(R.id.tv_order_income, StringUtils.fenToYuan(amount));
                helper.setTextColor(R.id.tv_order_income, ContextCompat.getColor(mContext,R.color.e94630));
                break;
            default:
                break;
        }
    }

    private void setZhuanzhang(BaseViewHolder helper, int subclass,int amount) {
        switch (subclass) {
            case 0://转出
                helper.setText(R.id.tv_order_type, "转出");
                helper.setImageResource(R.id.imageView8,R.drawable.zzrz_zhuanchu);
                helper.setText(R.id.tv_order_income, "-"+StringUtils.fenToYuan(amount));
                helper.setTextColor(R.id.tv_order_income, ContextCompat.getColor(mContext,R.color.color_black_0));
                break;
            case 1://转入
                helper.setText(R.id.tv_order_type, "转入");
                helper.setImageResource(R.id.imageView8,R.drawable.zzrz_zhuanru);
                helper.setText(R.id.tv_order_income, StringUtils.fenToYuan(amount));
                helper.setTextColor(R.id.tv_order_income, ContextCompat.getColor(mContext,R.color.e94630));
                break;
            default:
                break;
        }
    }
}
