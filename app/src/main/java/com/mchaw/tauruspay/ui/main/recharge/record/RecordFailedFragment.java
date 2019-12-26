package com.mchaw.tauruspay.ui.main.recharge.record;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentListFragment;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.recharge.RechargeAdapter;
import com.mchaw.tauruspay.ui.main.recharge.constract.RechargeListConstract;
import com.mchaw.tauruspay.ui.main.recharge.presenter.RechargeListPresenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 15:31
 * @description:
 */
public class RecordFailedFragment extends BasePresentListFragment<RechargeListPresenter> implements RechargeListConstract.View {

    @BindView(R.id.rv_base)
    RecyclerView recyclerView;

    private List<RechargeBean> rechargeBeanList = new ArrayList();
    private RechargeAdapter rechargeAdapter;

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
        super.initFragment();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rechargeAdapter = new RechargeAdapter(rechargeBeanList);
        recyclerView.setAdapter(rechargeAdapter);
        onRefresh();
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    protected void initHintViews() {
        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) recyclerView.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) recyclerView.getParent(), false);
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
    }

    @Override
    public void setRechargeList(List<RechargeBean> list) {
        rechargeBeanList = list;
        if (list != null && list.size() > 0) {
            List<RechargeBean> reList = filtrateList(list);
            if(reList == null || list.size()<=0){
                rechargeAdapter.setNewData(null);
                rechargeAdapter.setEmptyView(notDataView);
                return;
            }
            rechargeAdapter.setNewData(reList);
        } else {
            rechargeAdapter.setNewData(null);
            rechargeAdapter.setEmptyView(notDataView);
        }
    }

    @Override
    public void setRecaargeListFail() {
        rechargeAdapter.setEmptyView(errorView);
    }

    @Override
    public void setHomeDataBean(UserBean userBean) {

    }

    private List<RechargeBean> filtrateList(List<RechargeBean> list){
        Iterator<RechargeBean> iter = list.iterator();
        while (iter.hasNext()) {
            RechargeBean bean = (RechargeBean) iter.next();
            if(bean.getStatus() != 3){
                iter.remove();
            }
        }
        return list;
    }
}
