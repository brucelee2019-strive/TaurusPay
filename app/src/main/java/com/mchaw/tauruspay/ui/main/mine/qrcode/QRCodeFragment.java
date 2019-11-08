package com.mchaw.tauruspay.ui.main.mine.qrcode;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.ui.main.home.forsale.adapter.ForSaleListAdapter;
import com.mchaw.tauruspay.ui.main.mine.qrcode.adapter.QRCodeListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 19:23
 * @description:
 */
public class QRCodeFragment extends BaseFragment {
    @BindView(R.id.rv_qr_list)
    RecyclerView rvQRList;

    private List<String> list = new ArrayList<String>();

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_qr_code;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        list.add("成功");
        list.add("未通过");
        list.add("成功");
        list.add("未通过");
        list.add("成功");
        list.add("未通过");
        list.add("成功");
        list.add("成功");
        list.add("未通过");
        list.add("成功");
        list.add("成功");
        list.add("成功");
        rvQRList.setLayoutManager(new LinearLayoutManager(getContext()));
        QRCodeListAdapter qrCodeListAdapter = new QRCodeListAdapter(list);
        rvQRList.setAdapter(qrCodeListAdapter);
    }
}
