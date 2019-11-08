package com.mchaw.tauruspay.ui.main.recharge.record;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;

import butterknife.BindView;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 15:30
 * @description:
 */
public class RecordSucceedFragment extends BaseFragment {

    @BindView(R.id.rv_base)
    RecyclerView recyclerView;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_base_list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {

    }
}
