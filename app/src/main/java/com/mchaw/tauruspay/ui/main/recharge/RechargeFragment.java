package com.mchaw.tauruspay.ui.main.recharge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.home.HomeDataBean;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.StringUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.recharge.constract.RechargeListConstract;
import com.mchaw.tauruspay.ui.main.recharge.presenter.RechargeListPresenter;
import com.mchaw.tauruspay.ui.main.recharge.record.RecordMainFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : Bruce Lee
 * @date : 2019/11/3 0003 21:07
 * @description :
 */
public class RechargeFragment extends BasePresentFragment<RechargeListPresenter> implements RechargeListConstract.View {

    @BindView(R.id.rv_income_record)
    RecyclerView rvIncomeRecoed;

    @BindView(R.id.tv_repertory_money)
    TextView tvRepertoryMoney;

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
            //结束轮询
            stopPolling();
        }else{
            //presenter.getRechargeList(PreferencesUtils.getString(getContext(),"token"));
            //presenter.getHomeDataBean(PreferencesUtils.getString(getContext(),"token"));
            //开启轮询
            startPolling(1);
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
        //presenter.getHomeDataBean(PreferencesUtils.getString(getContext(),"token"));
        Log.i("cici",PreferencesUtils.getString(getContext(),"token"));
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
                    //presenter.getRechargeList(PreferencesUtils.getString(getContext(),"token"));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void setRechargeList(List<RechargeBean> list) {
        rechargeBeanList = list;
        rechargeAdapter.setNewData(list);
    }

    @Override
    public void setHomeDataBean(HomeDataBean homeDataBean) {
        tvRepertoryMoney.setText(StringUtils.fenToYuan(homeDataBean.getDeposit()));
    }

    @Override
    public void setRechargeUpdateList(List<RechargeBean> list) {
        for (int i = 0; i < rechargeBeanList.size(); i++) {
            // 判断listTemp集合中是否包含list中的元素
            if (!list.contains(rechargeBeanList.get(i))) {
                // 将未包含的元素添加进listTemp集合中
                list.add(rechargeBeanList.get(i));
            }
        }
        rechargeAdapter.setNewData(list);
    }

    //以下是轮询
    private Disposable disposable;
    public void startPolling(int time) {
        disposable = Observable.interval(0, time, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i("cici","轮询中...");
                        presenter.getHomeDataBean(PreferencesUtils.getString(getContext(),"token"));
                        presenter.getRechargeUpdateList(PreferencesUtils.getString(getContext(),"token"));
                    }
                });
    }

    public void stopPolling() {
        Log.i("cici","结束轮询");
        if(disposable!=null) {
            disposable.dispose();
        }
    }

    //生命周期管理
    @Override
    public void onResume() {
        super.onResume();
        startPolling(1);
    }

    @Override
    public void onPause() {
        super.onPause();
        stopPolling();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopPolling();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopPolling();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPolling();
    }
}
