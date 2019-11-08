package com.mchaw.tauruspay.ui.main.recharge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.recharge.record.RecordMainFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : Bruce Lee
 * @date : 2019/11/3 0003 21:07
 * @description :
 */
public class RechargeFragment extends BaseFragment {

    @BindView(R.id.rv_income_record)
    RecyclerView rvIncomeRecoed;

    private List<String> list = new ArrayList<String>();

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_recharge;
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

        rvIncomeRecoed.setLayoutManager(new LinearLayoutManager(getContext()));
        RechargeAdapter rechargeAdapter = new RechargeAdapter(list);
        rvIncomeRecoed.setAdapter(rechargeAdapter);
    }

    @OnClick({R.id.tv_recharge_btn,R.id.tv_record})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_recharge_btn:
                startFragmentForResult(new RechargeNextFragment(), Constant.RECHARGE_NEXT_FRAGMENT_BACK);
                break;
            case R.id.tv_record:
                startFragment(new RecordMainFragment());
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constant.RECHARGE_NEXT_FRAGMENT_BACK:

                    break;
                default:
                    break;
            }
        }
    }
}
