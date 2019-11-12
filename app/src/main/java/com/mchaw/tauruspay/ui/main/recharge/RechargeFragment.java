package com.mchaw.tauruspay.ui.main.recharge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.recharge.constract.RechargeListConstract;
import com.mchaw.tauruspay.ui.main.recharge.presenter.RechargeListPresenter;
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
public class RechargeFragment extends BasePresentFragment<RechargeListPresenter> implements RechargeListConstract.View {

    @BindView(R.id.rv_income_record)
    RecyclerView rvIncomeRecoed;

    private List<RechargeBean> rechargeBeanList = new ArrayList();
    private RechargeAdapter rechargeAdapter;

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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){

        }else{
            presenter.getRechargeList(PreferencesUtils.getString(getContext(),"token"));
        }
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        rvIncomeRecoed.setLayoutManager(new LinearLayoutManager(getContext()));
        rechargeAdapter = new RechargeAdapter(rechargeBeanList);
        rvIncomeRecoed.setAdapter(rechargeAdapter);
        presenter.getRechargeList(PreferencesUtils.getString(getContext(),"token"));
        Log.i("lililili",PreferencesUtils.getString(getContext(),"token"));
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
                    presenter.getRechargeList(PreferencesUtils.getString(getContext(),"token"));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void setRechargeList(List<RechargeBean> list) {
        rechargeAdapter.setNewData(list);
    }
}
