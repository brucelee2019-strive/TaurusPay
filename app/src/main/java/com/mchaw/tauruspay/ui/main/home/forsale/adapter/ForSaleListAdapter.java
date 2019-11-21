package com.mchaw.tauruspay.ui.main.home.forsale.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeStallBean;
import com.mchaw.tauruspay.common.util.StringUtils;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 16:16
 * @description:
 */
public class ForSaleListAdapter extends BaseQuickAdapter<QRCodeGroupBean, BaseViewHolder> {

    public ForSaleListAdapter(@Nullable List<QRCodeGroupBean> data) {
        super(R.layout.item_for_sale_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QRCodeGroupBean item) {
        helper.addOnClickListener(R.id.tv_show_order_list,R.id.tv_start_sail_btn);
        helper.setText(R.id.tv_zfb_account, item.getAccount());
        helper.setText(R.id.tv_zfb_nike_name, item.getNick());
        helper.setText(R.id.tv_last_time, String.valueOf(item.getSellcount()));
        helper.setGone(R.id.ll_1, item.isShowItems());
        helper.setGone(R.id.ll_2, item.isShowItems());
        helper.setGone(R.id.ll_3, item.isShowItems());
        helper.setGone(R.id.ll_4, item.isShowItems());
        helper.setImageResource(R.id.tv_show_order_list, item.isShowItems() == true ? R.drawable.ds_btn_sq : R.drawable.ds_btn_zk);
        if (item.getQrcodes() != null && item.getQrcodes().size() > 0) {
            for (int i = 0; i < item.getQrcodes().size(); i++) {
                setQRCodeStatus(item.getQrcodes().get(i), helper, i);
            }
        }
        helper.setText(R.id.tv_start_sail_btn,item.getStatus()==0?"开始代售":"停止代售");
    }

    private void setQRCodeStatus(QRCodeStallBean.QrcodesBean qrcodesBean, BaseViewHolder helper, int tag) {
        switch (tag) {
            case 0:
                helper.setText(R.id.tv_up_303, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_303, "赚"+StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_303, setSellState(qrcodesBean.getStatus()));
                break;
            case 1:
                helper.setText(R.id.tv_up_313, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_313, "赚"+StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_313, setSellState(qrcodesBean.getStatus()));
                break;
            case 2:
                helper.setText(R.id.tv_up_785, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_785, "赚"+StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_785, setSellState(qrcodesBean.getStatus()));
                break;
            case 3:
                helper.setText(R.id.tv_up_786, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_786, "赚"+StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_786, setSellState(qrcodesBean.getStatus()));
                break;
            case 4:
                helper.setText(R.id.tv_up_1215, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_1215, "赚"+StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_1215, setSellState(qrcodesBean.getStatus()));
                break;
            case 5:
                helper.setText(R.id.tv_up_1216, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_1216, "赚"+StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_1216, setSellState(qrcodesBean.getStatus()));
                break;
            case 6:
                helper.setText(R.id.tv_up_2515, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_2515, "赚"+StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_2515, setSellState(qrcodesBean.getStatus()));
                break;
            case 7:
                helper.setText(R.id.tv_up_2516, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_2516, "赚"+StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_2516, setSellState(qrcodesBean.getStatus()));
                break;
            case 8:
                helper.setText(R.id.tv_up_4985, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_4985, "赚"+StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_4985, setSellState(qrcodesBean.getStatus()));
                break;
            case 9:
                helper.setText(R.id.tv_up_4988, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_4988, "赚"+StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_4988, setSellState(qrcodesBean.getStatus()));
                break;
            case 10:
                helper.setText(R.id.tv_up_7988, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_7988, "赚"+StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_7988, setSellState(qrcodesBean.getStatus()));
                break;
            case 11:
                helper.setText(R.id.tv_up_9988, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_9988, "赚"+StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_9988, setSellState(qrcodesBean.getStatus()));
                break;
            default:
                break;
        }
    }

    private String setSellState(int state) {
        String text = "";
        switch (state) {
            case 0:
                text = "空闲";
                break;
            case 1:
                text = "审核通过";
                break;
            case 2:
                text = "等待审核";
                break;
            case 3:
                text = "审核拒绝";
                break;
            case 4:
                text = "排队中";
                break;
            case 5:
                text = "等待支付";
                break;
            case 6:
                text = "已支付";
                break;
            case 7:
                text = "准备加入队列";
                break;
            default:
                break;

        }
        return text;
    }
}
