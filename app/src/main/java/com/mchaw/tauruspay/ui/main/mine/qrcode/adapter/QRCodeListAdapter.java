package com.mchaw.tauruspay.ui.main.mine.qrcode.adapter;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeStallBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.StringUtils;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 19:34
 * @description:
 */
public class QRCodeListAdapter extends BaseQuickAdapter<QRCodeGroupBean, BaseViewHolder> {
    private Context mContext;

    public QRCodeListAdapter(Context context, @Nullable List<QRCodeGroupBean> data) {
        super(R.layout.item_qr_code_list, data);
        mContext = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QRCodeGroupBean item) {
        helper.addOnClickListener(R.id.cl_303, R.id.cl_313, R.id.cl_785, R.id.cl_786, R.id.cl_1215, R.id.cl_1216, R.id.cl_2515,
                R.id.cl_2516, R.id.cl_4985, R.id.cl_4988, R.id.cl_7988, R.id.cl_9988
                , R.id.tv_show_order_list,R.id.iv_delete);
        helper.setText(R.id.tv_zfb_account, item.getAccount());
        helper.setText(R.id.tv_zfb_nike_name, item.getNick());
        helper.setGone(R.id.ll_1, item.isShowItems());
        helper.setGone(R.id.ll_2, item.isShowItems());
        helper.setGone(R.id.ll_3, item.isShowItems());
        helper.setImageResource(R.id.tv_show_order_list, item.isShowItems() == true ? R.drawable.ds_btn_sq : R.drawable.ds_btn_zk);
        if(item.getQrcodes()!=null&&item.getQrcodes().size()>0) {
            for(int i =0; i<item.getQrcodes().size();i++){
                setQRCodeStatus(item.getQrcodes().get(i), helper,i);
            }
        }
        helper.setGone(R.id.iv_delete,item.getCanDelete()== Constant.PAGE_DELETE_STATE);
        helper.setGone(R.id.tv_show_order_list,!item.isCanClickShowItems());
    }

    private void setQRCodeStatus(QRCodeStallBean.QrcodesBean qrcodesBean,BaseViewHolder helper,int tag) {
        switch (tag) {
            case 0:
                helper.setImageResource(R.id.iv_303,qrcodesBean.getStatus()==2?R.drawable.qrcode_auditing:(qrcodesBean.getStatus()==1?R.drawable.qrcode_change:R.drawable.qrcode_add));
                helper.setText(R.id.tv_303, StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 1:
                helper.setImageResource(R.id.iv_313,qrcodesBean.getStatus()==2?R.drawable.qrcode_auditing:(qrcodesBean.getStatus()==1?R.drawable.qrcode_change:R.drawable.qrcode_add));
                helper.setText(R.id.tv_313,StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 2:
                helper.setImageResource(R.id.iv_785,qrcodesBean.getStatus()==2?R.drawable.qrcode_auditing:(qrcodesBean.getStatus()==1?R.drawable.qrcode_change:R.drawable.qrcode_add));
                helper.setText(R.id.tv_785,StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 3:
                helper.setImageResource(R.id.iv_786,qrcodesBean.getStatus()==2?R.drawable.qrcode_auditing:(qrcodesBean.getStatus()==1?R.drawable.qrcode_change:R.drawable.qrcode_add));
                helper.setText(R.id.tv_786,StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 4:
                helper.setImageResource(R.id.iv_1215,qrcodesBean.getStatus()==2?R.drawable.qrcode_auditing:(qrcodesBean.getStatus()==1?R.drawable.qrcode_change:R.drawable.qrcode_add));
                helper.setText(R.id.tv_1215,StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 5:
                helper.setImageResource(R.id.iv_1216,qrcodesBean.getStatus()==2?R.drawable.qrcode_auditing:(qrcodesBean.getStatus()==1?R.drawable.qrcode_change:R.drawable.qrcode_add));
                helper.setText(R.id.tv_1216,StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 6:
                helper.setImageResource(R.id.iv_2515,qrcodesBean.getStatus()==2?R.drawable.qrcode_auditing:(qrcodesBean.getStatus()==1?R.drawable.qrcode_change:R.drawable.qrcode_add));
                helper.setText(R.id.tv_2515,StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 7:
                helper.setImageResource(R.id.iv_2516,qrcodesBean.getStatus()==2?R.drawable.qrcode_auditing:(qrcodesBean.getStatus()==1?R.drawable.qrcode_change:R.drawable.qrcode_add));
                helper.setText(R.id.tv_2516,StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 8:
                helper.setImageResource(R.id.iv_4985,qrcodesBean.getStatus()==2?R.drawable.qrcode_auditing:(qrcodesBean.getStatus()==1?R.drawable.qrcode_change:R.drawable.qrcode_add));
                helper.setText(R.id.tv_4985,StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 9:
                helper.setImageResource(R.id.iv_4988,qrcodesBean.getStatus()==2?R.drawable.qrcode_auditing:(qrcodesBean.getStatus()==1?R.drawable.qrcode_change:R.drawable.qrcode_add));
                helper.setText(R.id.tv_4988,StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 10:
                helper.setImageResource(R.id.iv_7988,qrcodesBean.getStatus()==2?R.drawable.qrcode_auditing:(qrcodesBean.getStatus()==1?R.drawable.qrcode_change:R.drawable.qrcode_add));
                helper.setText(R.id.tv_7988,StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            case 11:
                helper.setImageResource(R.id.iv_9988,qrcodesBean.getStatus()==2?R.drawable.qrcode_auditing:(qrcodesBean.getStatus()==1?R.drawable.qrcode_change:R.drawable.qrcode_add));
                helper.setText(R.id.tv_9988,StringUtils.fenToYuanInt(qrcodesBean.getQuota()));
                break;
            default:
                break;
        }
    }
}