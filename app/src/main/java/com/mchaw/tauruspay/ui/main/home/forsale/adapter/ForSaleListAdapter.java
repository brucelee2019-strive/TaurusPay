package com.mchaw.tauruspay.ui.main.home.forsale.adapter;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.entry.MultipleItem;
import com.mchaw.tauruspay.bean.qrcode.GroupinfoBean;
import com.mchaw.tauruspay.common.util.StringUtils;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 16:16
 * @description:
 */
public class ForSaleListAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private void setQRCodeStatus(GroupinfoBean.QrcodesBean qrcodesBean, BaseViewHolder helper, int tag, int type) {
        switch (tag) {
            case 0:
                helper.setText(R.id.tv_up_303, (type == 3 || type == 4) ? "任意金额(>200)" : StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_303, (type == 3 || type == 4) ? "" : "赚" + StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_303, setSellState(qrcodesBean.getStatus()));
                helper.setTextColor(R.id.tv_dow_303, ContextCompat.getColor(mContext, qrcodesBean.getStatus() == 4 ? R.color.blue00aaef : (qrcodesBean.getStatus() == 5 ? R.color.color_special : (qrcodesBean.getStatus() == 1 ? R.color.color_match_type_3 : R.color.color_match_win))));
                break;
            case 1:
                helper.setText(R.id.tv_up_313, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_313, "赚" + StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_313, setSellState(qrcodesBean.getStatus()));
                helper.setTextColor(R.id.tv_dow_313, ContextCompat.getColor(mContext, qrcodesBean.getStatus() == 4 ? R.color.blue00aaef : (qrcodesBean.getStatus() == 5 ? R.color.color_special : (qrcodesBean.getStatus() == 1 ? R.color.color_match_type_3 : R.color.color_match_win))));
                break;
            case 2:
                helper.setText(R.id.tv_up_785, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_785, "赚" + StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_785, setSellState(qrcodesBean.getStatus()));
                helper.setTextColor(R.id.tv_dow_785, ContextCompat.getColor(mContext, qrcodesBean.getStatus() == 4 ? R.color.blue00aaef : (qrcodesBean.getStatus() == 5 ? R.color.color_special : (qrcodesBean.getStatus() == 1 ? R.color.color_match_type_3 : R.color.color_match_win))));
                break;
            case 3:
                helper.setText(R.id.tv_up_786, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_786, "赚" + StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_786, setSellState(qrcodesBean.getStatus()));
                helper.setTextColor(R.id.tv_dow_786, ContextCompat.getColor(mContext, qrcodesBean.getStatus() == 4 ? R.color.blue00aaef : (qrcodesBean.getStatus() == 5 ? R.color.color_special : (qrcodesBean.getStatus() == 1 ? R.color.color_match_type_3 : R.color.color_match_win))));
                break;
            case 4:
                helper.setText(R.id.tv_up_1215, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_1215, "赚" + StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_1215, setSellState(qrcodesBean.getStatus()));
                helper.setTextColor(R.id.tv_dow_1215, ContextCompat.getColor(mContext, qrcodesBean.getStatus() == 4 ? R.color.blue00aaef : (qrcodesBean.getStatus() == 5 ? R.color.color_special : (qrcodesBean.getStatus() == 1 ? R.color.color_match_type_3 : R.color.color_match_win))));
                break;
            case 5:
                helper.setText(R.id.tv_up_1216, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_1216, "赚" + StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_1216, setSellState(qrcodesBean.getStatus()));
                helper.setTextColor(R.id.tv_dow_1216, ContextCompat.getColor(mContext, qrcodesBean.getStatus() == 4 ? R.color.blue00aaef : (qrcodesBean.getStatus() == 5 ? R.color.color_special : (qrcodesBean.getStatus() == 1 ? R.color.color_match_type_3 : R.color.color_match_win))));
                break;
            case 6:
                helper.setText(R.id.tv_up_2515, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_2515, "赚" + StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_2515, setSellState(qrcodesBean.getStatus()));
                helper.setTextColor(R.id.tv_dow_2515, ContextCompat.getColor(mContext, qrcodesBean.getStatus() == 4 ? R.color.blue00aaef : (qrcodesBean.getStatus() == 5 ? R.color.color_special : (qrcodesBean.getStatus() == 1 ? R.color.color_match_type_3 : R.color.color_match_win))));
                break;
            case 7:
                helper.setText(R.id.tv_up_2516, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_2516, "赚" + StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_2516, setSellState(qrcodesBean.getStatus()));
                helper.setTextColor(R.id.tv_dow_2516, ContextCompat.getColor(mContext, qrcodesBean.getStatus() == 4 ? R.color.blue00aaef : (qrcodesBean.getStatus() == 5 ? R.color.color_special : (qrcodesBean.getStatus() == 1 ? R.color.color_match_type_3 : R.color.color_match_win))));
                break;
            case 8:
                helper.setText(R.id.tv_up_4985, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_4985, "赚" + StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_4985, setSellState(qrcodesBean.getStatus()));
                helper.setTextColor(R.id.tv_dow_4985, ContextCompat.getColor(mContext, qrcodesBean.getStatus() == 4 ? R.color.blue00aaef : (qrcodesBean.getStatus() == 5 ? R.color.color_special : (qrcodesBean.getStatus() == 1 ? R.color.color_match_type_3 : R.color.color_match_win))));
                break;
            case 9:
                helper.setText(R.id.tv_up_4988, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_4988, "赚" + StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_4988, setSellState(qrcodesBean.getStatus()));
                helper.setTextColor(R.id.tv_dow_4988, ContextCompat.getColor(mContext, qrcodesBean.getStatus() == 4 ? R.color.blue00aaef : (qrcodesBean.getStatus() == 5 ? R.color.color_special : (qrcodesBean.getStatus() == 1 ? R.color.color_match_type_3 : R.color.color_match_win))));
                break;
            case 10:
                helper.setText(R.id.tv_up_7988, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_7988, "赚" + StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_7988, setSellState(qrcodesBean.getStatus()));
                helper.setTextColor(R.id.tv_dow_7988, ContextCompat.getColor(mContext, qrcodesBean.getStatus() == 4 ? R.color.blue00aaef : (qrcodesBean.getStatus() == 5 ? R.color.color_special : (qrcodesBean.getStatus() == 1 ? R.color.color_match_type_3 : R.color.color_match_win))));
                break;
            case 11:
                helper.setText(R.id.tv_up_9988, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_mid_9988, "赚" + StringUtils.earningsYuan(qrcodesBean.getQuota()));
                helper.setText(R.id.tv_dow_9988, setSellState(qrcodesBean.getStatus()));
                helper.setTextColor(R.id.tv_dow_9988, ContextCompat.getColor(mContext, qrcodesBean.getStatus() == 4 ? R.color.blue00aaef : (qrcodesBean.getStatus() == 5 ? R.color.color_special : (qrcodesBean.getStatus() == 1 ? R.color.color_match_type_3 : R.color.color_match_win))));
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
                text = "可代售";
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
            case 8:
                text = "余额不足";
                break;
            default:
                break;

        }
        return text;
    }

    public ForSaleListAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ER_CODE_FOR_SAIL_FIXED_WX, R.layout.item_for_sale_list);
        addItemType(MultipleItem.ER_CODE_FOR_SAIL_FIXED_ALIPAY, R.layout.item_for_sale_list);
        addItemType(MultipleItem.ER_CODE_OR_SAIL_AT_WILL_ALIPAY, R.layout.item_for_sale_list_at_will);
        addItemType(MultipleItem.ER_CODE_OR_SAIL_AT_WILL_WX, R.layout.item_for_sale_list_at_will);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {
            case MultipleItem.ER_CODE_FOR_SAIL_FIXED_WX:
            case MultipleItem.ER_CODE_FOR_SAIL_FIXED_ALIPAY:
                GroupinfoBean groupinfoBean = (GroupinfoBean) item.getData();
                helper.addOnClickListener(R.id.tv_show_order_list, R.id.tv_start_sail_btn);
                helper.setImageResource(R.id.iv_ds_icon, (groupinfoBean.getPaytype() == 1 || groupinfoBean.getPaytype() == 3) ? R.drawable.ds_icon_zfb : R.drawable.ds_icon_wx);
                helper.setText(R.id.tv_position, String.valueOf((helper.getPosition() + 1)));
                helper.setText(R.id.tv_zfb_account, groupinfoBean.getAccount());
                helper.setText(R.id.tv_zfb_nike_name, groupinfoBean.getNick());
                helper.setText(R.id.tv_last_time, TextUtils.isEmpty(groupinfoBean.getDaycount()) ? "16" : groupinfoBean.getDaycount());
                helper.setGone(R.id.ll_1, groupinfoBean.isShowItems());
                helper.setGone(R.id.ll_2, groupinfoBean.isShowItems());
                helper.setGone(R.id.ll_3, groupinfoBean.isShowItems());
                helper.setGone(R.id.ll_4, groupinfoBean.isShowItems());
                helper.setImageResource(R.id.tv_show_order_list, groupinfoBean.isShowItems() == true ? R.drawable.ds_btn_sq : R.drawable.ds_btn_zk);
                if (groupinfoBean.getQrcodes() != null && groupinfoBean.getQrcodes().size() > 0) {
                    for (int i = 0; i < groupinfoBean.getQrcodes().size(); i++) {
                        setQRCodeStatus(groupinfoBean.getQrcodes().get(i), helper, i, groupinfoBean.getPaytype());
                    }
                }
                helper.setText(R.id.tv_start_sail_btn, groupinfoBean.getStatus() == 0 ? "开始代售" : "停止代售");
                helper.setTextColor(R.id.tv_start_sail_btn, ContextCompat.getColor(mContext, groupinfoBean.getStatus() == 0 ? R.color.white : R.color.color_match_win));
                break;
            case MultipleItem.ER_CODE_OR_SAIL_AT_WILL_ALIPAY:
            case MultipleItem.ER_CODE_OR_SAIL_AT_WILL_WX:
                GroupinfoBean groupinfoBean1 = (GroupinfoBean) item.getData();
                helper.addOnClickListener(R.id.tv_show_order_list, R.id.tv_start_sail_btn);
                helper.setImageResource(R.id.iv_ds_icon, (groupinfoBean1.getPaytype() == 1 || groupinfoBean1.getPaytype() == 3) ? R.drawable.ds_icon_zfb : R.drawable.ds_icon_wx);
                helper.setText(R.id.tv_position, String.valueOf((helper.getPosition() + 1)));
                helper.setText(R.id.tv_zfb_account, groupinfoBean1.getAccount());
                helper.setText(R.id.tv_zfb_nike_name, groupinfoBean1.getNick());
                helper.setText(R.id.tv_last_time, TextUtils.isEmpty(groupinfoBean1.getDaycount()) ? "16" : groupinfoBean1.getDaycount());
                helper.setGone(R.id.ll_1, groupinfoBean1.isShowItems());
                helper.setImageResource(R.id.tv_show_order_list, groupinfoBean1.isShowItems() == true ? R.drawable.ds_btn_sq : R.drawable.ds_btn_zk);
                if (groupinfoBean1.getQrcodes() != null && groupinfoBean1.getQrcodes().size() > 0) {
                    for (int i = 0; i < groupinfoBean1.getQrcodes().size(); i++) {
                        setQRCodeStatus(groupinfoBean1.getQrcodes().get(i), helper, i, groupinfoBean1.getPaytype());
                    }
                }
                helper.setText(R.id.tv_start_sail_btn, groupinfoBean1.getStatus() == 0 ? "开始代售" : "停止代售");
                helper.setTextColor(R.id.tv_start_sail_btn, ContextCompat.getColor(mContext, groupinfoBean1.getStatus() == 0 ? R.color.white : R.color.color_match_win));
                break;
        }
    }
}
