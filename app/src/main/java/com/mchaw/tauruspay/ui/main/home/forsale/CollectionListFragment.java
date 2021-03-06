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
import com.mchaw.tauruspay.base.dialog.DialogCallBack;
import com.mchaw.tauruspay.base.fragment.BasePresentListFragment;
import com.mchaw.tauruspay.bean.eventbus.TradedBeanEvent;
import com.mchaw.tauruspay.bean.eventbus.TradingBeanEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingReceivablesEvent;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.home.ReceivablesBean;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.forsale.adapter.CollectionListAdapter;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.CollectionListConstract;
import com.mchaw.tauruspay.ui.main.home.forsale.dialog.ConfirmDialogFragment;
import com.mchaw.tauruspay.ui.main.home.forsale.presenter.CollectionListPresenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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

        } else {
            onRefresh();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
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
        startPolling(0, 1);
    }

    @Override
    protected void initHintViews() {
        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rvForCollection.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rvForCollection.getParent(), false);
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
        //collectionListAdapter.setEmptyView(loadingView);
        presenter.getTradingList(MyFrameApplication.tokenStr);
    }

    @Override
    public void setTradingList(List<ReceivablesBean> list) {
        this.list = list;
        if (list != null && list.size() > 0) {
            collectionListAdapter.setNewData(list);
            ((ForSaleFragment)getParentFragment()).setRedPoint(list.size());
        } else {
            collectionListAdapter.setNewData(null);
            collectionListAdapter.setEmptyView(notDataView);
            ((ForSaleFragment)getParentFragment()).setRedPoint(0);
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
        onRefresh();
    }

    @Subscribe
    public void tradingAmount(TradingBeanEvent event) {
        if (event != null) {
            onRefresh();
        }
    }

    @Subscribe
    public void tradedAmount(TradedBeanEvent event) {
        if (event != null) {
            onRefresh();
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
        ReceivablesBean receivablesBean = (ReceivablesBean) adapter.getItem(position);
        switch (view.getId()) {
            case R.id.btn_sure:
                ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance();
                confirmDialogFragment.setMsg("*注意！");
                //confirmDialogFragment.setContent("请确定收到此订单的付款!\n确认后话费将直接进入对方账户,\n将无法追回!");
                confirmDialogFragment.setContent("  骗子会将支付宝昵称修改为:某某向某某转账xxx元(如:吴建向你支付3000.00元)括号里只是他的一个昵称,以此麻痹商家，请进入支付宝账单确认收到订单金额再确认!");
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

    //为了倒计时的轮询
    private Disposable disposable;

    public void startPolling(int start, int time) {
        disposable = Observable.interval(start, time, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (list != null && list.size() > 0) {
                            countDown(list);
                        }
                    }
                });
    }

    public void stopPolling() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPolling();
    }

    private void countDown(List<ReceivablesBean> list) {
        if(list==null||list.size()<=0){
            return;
        }
        List<ReceivablesBean> receivablesBeanList = new ArrayList<>();
        for (ReceivablesBean bean : list) {
            int j = bean.getEndtime() - 1 < 0 ? 0 : bean.getEndtime() - 1;
            bean.setEndtime(j);
            receivablesBeanList.add(bean);
            if(bean.getEndtime()<120){
                bean.setSureBtn(true);
            }
            collectionListAdapter.setNewData(receivablesBeanList);
        }
    }
}
