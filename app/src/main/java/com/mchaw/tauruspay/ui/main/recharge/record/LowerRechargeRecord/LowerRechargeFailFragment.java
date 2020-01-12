package com.mchaw.tauruspay.ui.main.recharge.record.LowerRechargeRecord;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentListFragment;
import com.mchaw.tauruspay.bean.recharge.RechargeAuditBean;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : Bruce Lee
 * @date : 2020/1/12 0012 14:28
 * @description :
 */
public class LowerRechargeFailFragment extends BasePresentListFragment<LowerRecordPresenter> implements LowerRecordConstract.View{
    private static final int PAGE_SIZE = 10;

    @BindView(R.id.rv_base)
    RecyclerView recyclerView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<RechargeAuditBean> rechargeAuditBeanList = new ArrayList();
    private LowerRechargeAdapter rechargeAuditAdapter;

    private int page = 0;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_base_load_list;
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
        rechargeAuditAdapter = new LowerRechargeAdapter(rechargeAuditBeanList);
        rechargeAuditAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        recyclerView.setAdapter(rechargeAuditAdapter);
        onRefresh();
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#f0a300"));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                rechargeAuditAdapter.setEnableLoadMore(false);
                page = 0;
                presenter.getRechargeAuditList(PreferencesUtils.getString(getContext(), "token"), 3, page);
            }
        });
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
        swipeRefreshLayout.setRefreshing(true);
        rechargeAuditAdapter.setEnableLoadMore(false);
        page = 0;
        presenter.getRechargeAuditList(PreferencesUtils.getString(getContext(), "token"), 3, page);
    }

    //加载更多
    private void loadMore() {
        presenter.getRechargeAuditList(PreferencesUtils.getString(getContext(), "token"), 3, page);
    }

    @Override
    public void setRechargeAuditList(List<RechargeAuditBean> list) {
        swipeRefreshLayout.setRefreshing(false);
        if (list != null && list.size() > 0) {
            boolean isRefresh = page == 0;
            setBilllistByType(isRefresh, list);
        } else {
            if (page == 0) {
                rechargeAuditAdapter.setNewData(null);
                rechargeAuditAdapter.setEmptyView(notDataView);
            } else {
                rechargeAuditAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void setRechargeAuditListFail() {
        if(page == 0){
            rechargeAuditAdapter.setEmptyView(errorView);
        }
        swipeRefreshLayout.setRefreshing(false);
        rechargeAuditAdapter.loadMoreComplete();
    }

    private void setBilllistByType(boolean isRefresh, List<RechargeAuditBean> list) {
        page++;
        final int size = list == null ? 0 : list.size();
        if (isRefresh) {
            rechargeAuditAdapter.setNewData(list);
        } else {
            if (size > 0) {
                rechargeAuditAdapter.addData(list);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            rechargeAuditAdapter.loadMoreEnd(isRefresh);
        } else {
            rechargeAuditAdapter.loadMoreComplete();
        }
    }
}
