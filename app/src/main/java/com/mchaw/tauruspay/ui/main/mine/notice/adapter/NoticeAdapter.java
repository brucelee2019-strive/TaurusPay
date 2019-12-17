package com.mchaw.tauruspay.ui.main.mine.notice.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.bean.notice.NoticeListBean;

import java.util.List;

/**
 * @author : Bruce Lee
 * @date : 2019/12/17 0017 07:55
 * @description :
 */
public class NoticeAdapter extends BaseQuickAdapter<NoticeListBean, BaseViewHolder> {

    public NoticeAdapter(@Nullable List<NoticeListBean> data) {
        super(R.layout.item_notice_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NoticeListBean item) {
        helper.setText(R.id.tv_time,item.getCreated_at());
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_content,item.getContent());
        helper.setImageResource(R.id.iv_type,item.getType()==0?R.drawable.xinfengweidu:R.drawable.xinfengyidu);
    }
}
