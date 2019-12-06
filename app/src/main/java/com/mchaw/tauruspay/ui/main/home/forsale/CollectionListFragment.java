package com.mchaw.tauruspay.ui.main.home.forsale;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.DialogCallBack;
import com.mchaw.tauruspay.base.fragment.BasePresentListFragment;
import com.mchaw.tauruspay.bean.eventbus.LoginSucceedEvent;
import com.mchaw.tauruspay.bean.eventbus.TradedBeanEvent;
import com.mchaw.tauruspay.bean.eventbus.TradingBeanEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingReceivablesEvent;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.home.ReceivablesBean;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.forsale.adapter.CollectionListAdapter;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.CollectionListConstract;
import com.mchaw.tauruspay.ui.main.home.forsale.dialog.ConfirmDialogFragment;
import com.mchaw.tauruspay.ui.main.home.forsale.presenter.CollectionListPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.mchaw.tauruspay.base.dialog.BaseDialogFragment.DIALOG_CONFIRM;

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
        //onRefresh();
        //show = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        //show = false;
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
        //onRefresh();
    }

    @Override
    protected void initHintViews() {
        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rvForCollection.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rvForCollection.getParent(), false);
//        notDataView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onRefresh();
//            }
//        });
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
        presenter.getTradingList(MyFrameApplication.tokenStr);
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

    @Override
    public void setUpLodingReceivables() {
        //更新收款列表
        if (show) {
            onRefresh();
        }
    }

    @Subscribe
    public void tradingAmount(TradingBeanEvent event) {
        if (event != null) {
            if (show) {
                //onRefresh();
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

    @Subscribe
    public void pollingReceivablesEvent(MainPollingReceivablesEvent event) {
        if (event == null) {
            return;
        }
        collectionListAdapter.setNewData(event.getReceivables());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (AntiShake.check(view.getId())) {
            return;
        }
        ReceivablesBean receivablesBean = (ReceivablesBean) adapter.getItem(position);
        switch (view.getId()) {
            case R.id.btn_sure:
                //((ForSaleFragment) getParentFragment()).noticeOfCollection();
                ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance();
                confirmDialogFragment.setMsg("请确定收到此订单的付款!\n确认后话费将直接进入对方账户，将无法追回!");
                confirmDialogFragment.setCancelText("取消");
                confirmDialogFragment.setConfirmText("确认");
                confirmDialogFragment.setListenCancel(true);
                confirmDialogFragment.setDialogCallBack(new DialogCallBack() {
                    @Override
                    public void onDialogViewClick(int type, Object value) {
                        if (type == DIALOG_CONFIRM) {
                            presenter.upLodingReceivables(String.valueOf(receivablesBean.getId()), MyFrameApplication.tokenStr);
                        } else {

                        }
                    }
                });
                confirmDialogFragment.show(this.getFragmentManager(), "confirmDialogFragment");
                break;
            default:
                break;
        }
    }
}
