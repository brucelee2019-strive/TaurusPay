package com.mchaw.tauruspay.ui.main.home.forsale;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentListFragment;
import com.mchaw.tauruspay.bean.eventbus.TradedBeanEvent;
import com.mchaw.tauruspay.bean.eventbus.TradingBeanEvent;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.home.ReceivablesBean;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.forsale.adapter.CollectionListAdapter;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.CollectionListConstract;
import com.mchaw.tauruspay.ui.main.home.forsale.presenter.CollectionListPresenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 11:58
 * @description:收款Fragment
 */
public class CollectionListFragment extends BasePresentListFragment<CollectionListPresenter> implements CollectionListConstract.View, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.rv_for_collection)
    RecyclerView rvForCollection;

    private List<ReceivablesBean> list = new ArrayList<>();

    private CollectionListAdapter collectionListAdapter;

    private boolean show = false;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_for_collection_list;
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
            show = false;
        } else {
            show = true;
            onRefresh();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
        show = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        show = false;
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        rvForCollection.setLayoutManager(new LinearLayoutManager(getContext()));
        collectionListAdapter = new CollectionListAdapter(list);
        collectionListAdapter.setOnItemChildClickListener(this);
        rvForCollection.setAdapter(collectionListAdapter);
        onRefresh();
    }

    @Override
    protected void initHintViews() {
        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rvForCollection.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rvForCollection.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rvForCollection.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    protected void onRefresh() {
        collectionListAdapter.setEmptyView(loadingView);
        presenter.getTradingList(PreferencesUtils.getString(MyFrameApplication.getInstance(), "token"));
    }

    @Override
    public void setTradingList(List<ReceivablesBean> list) {
        if (list != null && list.size() > 0) {
            collectionListAdapter.setNewData(list);
        } else {
            collectionListAdapter.setEmptyView(notDataView);
        }
    }

    @Override
    public void setTradingListFail() {
        collectionListAdapter.setEmptyView(errorView);
    }

    @Override
    public void setHomeDataBean(UserBean userBean) {

    }

    @Subscribe
    public void tradingAmount(TradingBeanEvent event) {
        if (event != null) {
            if (show) {
                onRefresh();
            }
        }
    }

    @Subscribe
    public void tradedAmount(TradedBeanEvent event) {
        if (event != null) {
            if (show) {
                onRefresh();
            }
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (AntiShake.check(view.getId())) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_sure:
                ((ForSaleFragment)getParentFragment()).noticeOfCollection();
                break;
            default:
                break;
        }
    }
}
