package com.mchaw.tauruspay.ui.main.mine.qrcode.adapter;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.entry.MultipleItem;
import com.mchaw.tauruspay.bean.qrcode.GroupinfoBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.StringUtils;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 19:34
 * @description:
 */
public class QRCodeListAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private void setQRCodeStatus(GroupinfoBean.QrcodesBean qrcodesBean, BaseViewHolder helper, int tag, int type) {
        switch (tag) {
            case 0:
                helper.setImageResource(R.id.iv_303, qrcodesBean.getStatus() == 2 ? ((type == 1 || type == 3) ? R.drawable.qrcode_auditing : R.drawable.qrcode_auditing_wx) : (qrcodesBean.getStatus() == 0 ? ((type == 1 || type == 3) ? R.drawable.qrcode_add : R.drawable.qrcode_add_wx) : ((type == 1 || type == 3) ? R.drawable.qrcode_change : R.drawable.qrcode_change_wx)));
                helper.setText(R.id.tv_303, (type == 3 || type == 4)?"任意金额":StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 1:
                helper.setImageResource(R.id.iv_313, qrcodesBean.getStatus() == 2 ? ((type == 1 || type == 3) ? R.drawable.qrcode_auditing : R.drawable.qrcode_auditing_wx) : (qrcodesBean.getStatus() == 0 ? ((type == 1 || type == 3) ? R.drawable.qrcode_add : R.drawable.qrcode_add_wx) : ((type == 1 || type == 3) ? R.drawable.qrcode_change : R.drawable.qrcode_change_wx)));
                helper.setText(R.id.tv_313, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 2:
                helper.setImageResource(R.id.iv_785, qrcodesBean.getStatus() == 2 ? ((type == 1 || type == 3) ? R.drawable.qrcode_auditing : R.drawable.qrcode_auditing_wx) : (qrcodesBean.getStatus() == 0 ? ((type == 1 || type == 3) ? R.drawable.qrcode_add : R.drawable.qrcode_add_wx) : ((type == 1 || type == 3) ? R.drawable.qrcode_change : R.drawable.qrcode_change_wx)));
                helper.setText(R.id.tv_785, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 3:
                helper.setImageResource(R.id.iv_786, qrcodesBean.getStatus() == 2 ? ((type == 1 || type == 3) ? R.drawable.qrcode_auditing : R.drawable.qrcode_auditing_wx) : (qrcodesBean.getStatus() == 0 ? ((type == 1 || type == 3) ? R.drawable.qrcode_add : R.drawable.qrcode_add_wx) : ((type == 1 || type == 3) ? R.drawable.qrcode_change : R.drawable.qrcode_change_wx)));
                helper.setText(R.id.tv_786, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 4:
                helper.setImageResource(R.id.iv_1215, qrcodesBean.getStatus() == 2 ? ((type == 1 || type == 3) ? R.drawable.qrcode_auditing : R.drawable.qrcode_auditing_wx) : (qrcodesBean.getStatus() == 0 ? ((type == 1 || type == 3) ? R.drawable.qrcode_add : R.drawable.qrcode_add_wx) : ((type == 1 || type == 3) ? R.drawable.qrcode_change : R.drawable.qrcode_change_wx)));
                helper.setText(R.id.tv_1215, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 5:
                helper.setImageResource(R.id.iv_1216, qrcodesBean.getStatus() == 2 ? ((type == 1 || type == 3) ? R.drawable.qrcode_auditing : R.drawable.qrcode_auditing_wx) : (qrcodesBean.getStatus() == 0 ? ((type == 1 || type == 3) ? R.drawable.qrcode_add : R.drawable.qrcode_add_wx) : ((type == 1 || type == 3) ? R.drawable.qrcode_change : R.drawable.qrcode_change_wx)));
                helper.setText(R.id.tv_1216, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 6:
                helper.setImageResource(R.id.iv_2515, qrcodesBean.getStatus() == 2 ? ((type == 1 || type == 3) ? R.drawable.qrcode_auditing : R.drawable.qrcode_auditing_wx) : (qrcodesBean.getStatus() == 0 ? ((type == 1 || type == 3) ? R.drawable.qrcode_add : R.drawable.qrcode_add_wx) : ((type == 1 || type == 3) ? R.drawable.qrcode_change : R.drawable.qrcode_change_wx)));
                helper.setText(R.id.tv_2515, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 7:
                helper.setImageResource(R.id.iv_2516, qrcodesBean.getStatus() == 2 ? ((type == 1 || type == 3) ? R.drawable.qrcode_auditing : R.drawable.qrcode_auditing_wx) : (qrcodesBean.getStatus() == 0 ? ((type == 1 || type == 3) ? R.drawable.qrcode_add : R.drawable.qrcode_add_wx) : ((type == 1 || type == 3) ? R.drawable.qrcode_change : R.drawable.qrcode_change_wx)));
                helper.setText(R.id.tv_2516, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 8:
                helper.setImageResource(R.id.iv_4985, qrcodesBean.getStatus() == 2 ? ((type == 1 || type == 3) ? R.drawable.qrcode_auditing : R.drawable.qrcode_auditing_wx) : (qrcodesBean.getStatus() == 0 ? ((type == 1 || type == 3) ? R.drawable.qrcode_add : R.drawable.qrcode_add_wx) : ((type == 1 || type == 3) ? R.drawable.qrcode_change : R.drawable.qrcode_change_wx)));
                helper.setText(R.id.tv_4985, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 9:
                helper.setImageResource(R.id.iv_4988, qrcodesBean.getStatus() == 2 ? ((type == 1 || type == 3) ? R.drawable.qrcode_auditing : R.drawable.qrcode_auditing_wx) : (qrcodesBean.getStatus() == 0 ? ((type == 1 || type == 3) ? R.drawable.qrcode_add : R.drawable.qrcode_add_wx) : ((type == 1 || type == 3) ? R.drawable.qrcode_change : R.drawable.qrcode_change_wx)));
                helper.setText(R.id.tv_4988, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 10:
                helper.setImageResource(R.id.iv_7988, qrcodesBean.getStatus() == 2 ? ((type == 1 || type == 3) ? R.drawable.qrcode_auditing : R.drawable.qrcode_auditing_wx) : (qrcodesBean.getStatus() == 0 ? ((type == 1 || type == 3) ? R.drawable.qrcode_add : R.drawable.qrcode_add_wx) : ((type == 1 || type == 3) ? R.drawable.qrcode_change : R.drawable.qrcode_change_wx)));
                helper.setText(R.id.tv_7988, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 11:
                helper.setImageResource(R.id.iv_9988, qrcodesBean.getStatus() == 2 ? ((type == 1 || type == 3) ? R.drawable.qrcode_auditing : R.drawable.qrcode_auditing_wx) : (qrcodesBean.getStatus() == 0 ? ((type == 1 || type == 3) ? R.drawable.qrcode_add : R.drawable.qrcode_add_wx) : ((type == 1 || type == 3) ? R.drawable.qrcode_change : R.drawable.qrcode_change_wx)));
                helper.setText(R.id.tv_9988, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            default:
                break;
        }
    }

    public QRCodeListAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ER_CODE_FIXED_WX, R.layout.item_qr_code_list);
        addItemType(MultipleItem.ER_CODE_FIXED_ALIPAY, R.layout.item_qr_code_list);
        addItemType(MultipleItem.ER_CODE_AT_WILL_ALIPAY, R.layout.item_qr_code_at_will_list);
        addItemType(MultipleItem.ER_CODE_AT_WILL_WX, R.layout.item_qr_code_at_will_list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {
            case MultipleItem.ER_CODE_FIXED_WX:
            case MultipleItem.ER_CODE_FIXED_ALIPAY:
                GroupinfoBean groupinfoBean = (GroupinfoBean) item.getData();
                int yscQRCodeNum = 0;
                helper.addOnClickListener(R.id.cl_303, R.id.cl_313, R.id.cl_785, R.id.cl_786, R.id.cl_1215, R.id.cl_1216, R.id.cl_2515,
                        R.id.cl_2516, R.id.cl_4985, R.id.cl_4988, R.id.cl_7988, R.id.cl_9988
                        , R.id.tv_show_order_list, R.id.iv_delete);
                helper.setImageResource(R.id.iv_ds_icon, (groupinfoBean.getPaytype() == 1 || groupinfoBean.getPaytype() == 3)? R.drawable.ds_icon_zfb : R.drawable.ds_icon_wx);
                helper.setText(R.id.tv_position, String.valueOf((helper.getPosition() + 1)));
                helper.setText(R.id.tv_zfb_account, groupinfoBean.getAccount());
                helper.setText(R.id.tv_zfb_nike_name, groupinfoBean.getNick());
                helper.setGone(R.id.ll_1, groupinfoBean.isShowItems());
                helper.setGone(R.id.ll_2, groupinfoBean.isShowItems());
                helper.setGone(R.id.ll_3, groupinfoBean.isShowItems());
                helper.setImageResource(R.id.tv_show_order_list, groupinfoBean.isShowItems() == true ? R.drawable.ds_btn_sq : R.drawable.ds_btn_zk);
                if (groupinfoBean.getQrcodes() != null && groupinfoBean.getQrcodes().size() > 0) {
                    for (int i = 0; i < groupinfoBean.getQrcodes().size(); i++) {
                        setQRCodeStatus(groupinfoBean.getQrcodes().get(i), helper, i, groupinfoBean.getPaytype());
                        if (groupinfoBean.getQrcodes().get(i).getStatus() != 0 && groupinfoBean.getQrcodes().get(i).getStatus() != 3) {
                            yscQRCodeNum++;
                        }
                    }
                }
                helper.setText(R.id.tv_last_time, yscQRCodeNum >= groupinfoBean.getCount() ? yscQRCodeNum + "张" : groupinfoBean.getCount() + "张");
                helper.setGone(R.id.iv_delete, groupinfoBean.getCanDelete() == Constant.PAGE_DELETE_STATE);
                helper.setGone(R.id.tv_show_order_list, !groupinfoBean.isCanClickShowItems());
                break;
            case MultipleItem.ER_CODE_AT_WILL_ALIPAY:
            case MultipleItem.ER_CODE_AT_WILL_WX:
                GroupinfoBean groupinfoBean1 = (GroupinfoBean) item.getData();
                int yscQRCodeNum1 = 0;
                helper.addOnClickListener(R.id.cl_303, R.id.cl_313, R.id.cl_785, R.id.cl_786, R.id.cl_1215, R.id.cl_1216, R.id.cl_2515,
                        R.id.cl_2516, R.id.cl_4985, R.id.cl_4988, R.id.cl_7988, R.id.cl_9988
                        , R.id.tv_show_order_list, R.id.iv_delete);
                helper.setImageResource(R.id.iv_ds_icon, (groupinfoBean1.getPaytype() == 1 || groupinfoBean1.getPaytype() == 3) ? R.drawable.ds_icon_zfb : R.drawable.ds_icon_wx);
                helper.setText(R.id.tv_position, String.valueOf((helper.getPosition() + 1)));
                helper.setText(R.id.tv_zfb_account, groupinfoBean1.getAccount());
                helper.setText(R.id.tv_zfb_nike_name, groupinfoBean1.getNick());
                helper.setGone(R.id.ll_1, groupinfoBean1.isShowItems());
                helper.setImageResource(R.id.tv_show_order_list, groupinfoBean1.isShowItems() == true ? R.drawable.ds_btn_sq : R.drawable.ds_btn_zk);
                if (groupinfoBean1.getQrcodes() != null && groupinfoBean1.getQrcodes().size() > 0) {
                    for (int i = 0; i < groupinfoBean1.getQrcodes().size(); i++) {
                        setQRCodeStatus(groupinfoBean1.getQrcodes().get(i), helper, i, groupinfoBean1.getPaytype());
                        if (groupinfoBean1.getQrcodes().get(i).getStatus() != 0 && groupinfoBean1.getQrcodes().get(i).getStatus() != 3) {
                            yscQRCodeNum1++;
                        }
                    }
                }
                helper.setText(R.id.tv_last_time, yscQRCodeNum1 >= groupinfoBean1.getCount() ? yscQRCodeNum1 + "张" : groupinfoBean1.getCount() + "张");
                helper.setGone(R.id.iv_delete, groupinfoBean1.getCanDelete() == Constant.PAGE_DELETE_STATE);
                helper.setGone(R.id.tv_show_order_list, !groupinfoBean1.isCanClickShowItems());
                break;
        }
    }
}