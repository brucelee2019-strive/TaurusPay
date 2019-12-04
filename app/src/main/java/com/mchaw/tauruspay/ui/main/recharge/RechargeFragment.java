package com.mchaw.tauruspay.ui.main.recharge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentListFragment;
import com.mchaw.tauruspay.bean.eventbus.LoginSucceedEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingRechargeEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingUserEvent;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.StringUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.recharge.constract.RechargeListConstract;
import com.mchaw.tauruspay.ui.main.recharge.presenter.RechargeListPresenter;
import com.mchaw.tauruspay.ui.main.recharge.record.RecordMainFragment;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Iterator;
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
public class RechargeFragment extends BasePresentListFragment<RechargeListPresenter> implements RechargeListConstract.View {

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
        if (hidden) {

        } else {
            onRefresh();
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
        //onRefresh();
        //presenter.getHomeDataBean(PreferencesUtils.getString(getContext(),"token"));
        Log.i("cici", PreferencesUtils.getString(getContext(), "token"));
    }

    @Override
    protected void initHintViews() {
        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rvIncomeRecoed.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rvIncomeRecoed.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rvIncomeRecoed.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    protected void onRefresh() {
        rechargeAdapter.setEmptyView(loadingView);
        presenter.getRechargeList(PreferencesUtils.getString(getContext(), "token"));
        presenter.getHomeDataBean(PreferencesUtils.getString(getContext(), "token"));
    }

    @OnClick({R.id.btn_recharge_btn, R.id.tv_record})
    public void onClick(View view) {
        if (AntiShake.check(view.getId())) {    //判断是否多次点击
            return;
        }
        switch (view.getId()) {
            case R.id.btn_recharge_btn:
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
                    presenter.getRechargeList(PreferencesUtils.getString(getContext(), "token"));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void setRechargeList(List<RechargeBean> list) {
        rechargeBeanList = list;
        if (list != null && list.size() > 0) {
            rechargeAdapter.setNewData(list);
        } else {
            rechargeAdapter.setEmptyView(notDataView);
        }
    }

    @Override
    public void setRecaargeListFail() {
        rechargeAdapter.setEmptyView(errorView);
    }

    @Override
    public void setHomeDataBean(UserBean userBean) {
        tvRepertoryMoney.setText(StringUtils.fenToYuan(userBean.getDeposit()));
    }

    @Subscribe
    public void sellInfo(MainPollingUserEvent event) {
        if (event != null) {
            tvRepertoryMoney.setText(StringUtils.fenToYuan(event.getKucun()));
        }
    }

    @Subscribe
    public void rechargeUpdateList(MainPollingRechargeEvent event) {
        if (event == null) {
            return;
        }
        List<RechargeBean> list = event.getList();
        if (list == null || list.size() <= 0) {
            return;
        }
        for (RechargeBean rechargeBean : list) {
            Iterator<RechargeBean> iter = rechargeBeanList.iterator();
            while (iter.hasNext()) {
                RechargeBean bean = (RechargeBean) iter.next();
                if(rechargeBean.getOrderid().equals(bean.getOrderid())){
                    iter.remove();
                }
            }
        }
        list.addAll(rechargeBeanList);
        rechargeAdapter.setNewData(list);
    }

    //生命周期管理
    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }
}
