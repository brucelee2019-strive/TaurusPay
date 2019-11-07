package com.mchaw.tauruspay.ui.main.home.forsale;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.ui.main.home.forsale.adapter.ForSaleListAdapter;
import com.mchaw.tauruspay.ui.main.recharge.RechargeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 11:56
 * @description:待售列表Fragment
 */
public class ForSaleListFragment extends BaseFragment {

    @BindView(R.id.rv_for_sale_list)
    RecyclerView rvForSalelist;

    private List<String> list = new ArrayList<String>();

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_for_sale_list;
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
        rvForSalelist.setLayoutManager(new LinearLayoutManager(getContext()));
        ForSaleListAdapter forSaleListAdapter = new ForSaleListAdapter(list);
        rvForSalelist.setAdapter(forSaleListAdapter);
    }
}