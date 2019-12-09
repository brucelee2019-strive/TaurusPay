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
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingGroupInfoEvent;
import com.mchaw.tauruspay.bean.home.StartOrOverSellBean;
import com.mchaw.tauruspay.bean.qrcode.GroupinfoBean;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.forsale.adapter.ForSaleListAdapter;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.ForSaleListConstract;
import com.mchaw.tauruspay.ui.main.home.forsale.dialog.CollectionListDialog;
import com.mchaw.tauruspay.ui.main.home.forsale.presenter.ForSaleListPresenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 11:56
 * @description:待售列表Fragment
 */
public class ForSaleListFragment extends BasePresentListFragment<ForSaleListPresenter> implements ForSaleListConstract.View, BaseQuickAdapter.OnItemChildClickListener, CollectionListDialog.ConfirmListener {

    @BindView(R.id.rv_for_sale_list)
    RecyclerView rvForSalelist;

    private List<GroupinfoBean> qrCodeGroupBeanList = new ArrayList<>();
    private ForSaleListAdapter forSaleListAdapter;
    private GroupinfoBean qrCodeGroupBean;
    private int recordGroupid;
    private int recordPosition;//记录点击条目位置

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_for_sale_list;
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
        rvForSalelist.setLayoutManager(new LinearLayoutManager(getContext()));
        forSaleListAdapter = new ForSaleListAdapter(qrCodeGroupBeanList);
        forSaleListAdapter.setOnItemChildClickListener(this);
        rvForSalelist.setAdapter(forSaleListAdapter);
        onRefresh();
    }

    @Override
    protected void initHintViews() {
        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rvForSalelist.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rvForSalelist.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rvForSalelist.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    protected void onRefresh() {
        forSaleListAdapter.setEmptyView(loadingView);
        presenter.getQRCodeGroupList(PreferencesUtils.getString(getContext(), "token"));
    }

    /**
     * 代售分组
     *
     * @param list
     */
    @Override
    public void setQRCodeGroupList(List<GroupinfoBean> list) {
        qrCodeGroupBeanList = list;
        if (list != null && list.size() > 0) {
            forSaleListAdapter.setNewData(list);
        } else {
            forSaleListAdapter.setNewData(null);
            forSaleListAdapter.setEmptyView(notDataView);
        }
    }

    @Override
    public void setQRCodeGroupListFail() {
        forSaleListAdapter.setEmptyView(errorView);
    }

    /**
     * 设置代售分组详细信息
     *
     * @param bean
     */
    @Override
    public void setQRCodeStalls(GroupinfoBean bean) {
        //组装数据给qrCodeListAdapter
        if (bean == null) {
            ToastUtils.showShortToast(getContext(), "服务器返回数据为null!");
            return;
        }
        if (bean.getQrcodes() == null || bean.getQrcodes().size() <= 0) {
            ToastUtils.showShortToast(getContext(), "服务器返回数据为null!");
            return;
        }
        if (recordGroupid == bean.getGroupid()) {//确保同一组
            //赋值 list(12个二维码档口id)
            qrCodeGroupBean.setQrcodes(bean.getQrcodes());
        }
        forSaleListAdapter.notifyItemChanged(recordPosition);
    }

    //来自大轮询
    @Subscribe
    public void setQRCodeStallsEvent(MainPollingGroupInfoEvent event) {
        if (event == null) {
            return;
        }
        //Groupinfo为null 所有分组置为待售状态
        if (event.getGroupinfo() == null) {
            for (GroupinfoBean qrCodeGroupBean : qrCodeGroupBeanList) {
                qrCodeGroupBean.setStatus(0);
            }
            MyFrameApplication.startingPosition = -1;
            forSaleListAdapter.notifyDataSetChanged();
            return;
        }
        if (event.getGroupinfo().getQrcodes() == null || event.getGroupinfo().getQrcodes().size() <= 0) {
            return;
        }
        //status为0 表示停售状态
        if (MyFrameApplication.groupid == event.getGroupinfo().getGroupid()) {//确保同一组
            //赋值 list(12个二维码档口id)
//            if (qrCodeGroupBean != null) {
//                qrCodeGroupBean.setDaycount(event.getGroupinfo().getDaycount());
//                qrCodeGroupBean.setStatus(event.getGroupinfo().getStatus());
//                qrCodeGroupBean.setQrcodes(event.getGroupinfo().getQrcodes());
//            }
            if (qrCodeGroupBeanList != null && qrCodeGroupBeanList.size() > 0) {
                if (MyFrameApplication.startingPosition == -1) {
                    return;
                }
                GroupinfoBean startingGroupinfoBean = qrCodeGroupBeanList.get(MyFrameApplication.startingPosition);
                if (startingGroupinfoBean != null) {
                    startingGroupinfoBean.setDaycount(event.getGroupinfo().getDaycount());
                    startingGroupinfoBean.setStatus(event.getGroupinfo().getStatus());
                    startingGroupinfoBean.setQrcodes(event.getGroupinfo().getQrcodes());
                }
                //forSaleListAdapter.notifyItemChanged(MyFrameApplication.startingPosition);
                forSaleListAdapter.notifyDataSetChanged();
            }
        }

    }

    //点击开始待售成功
    @Override
    public void setStartingOrOverSell(StartOrOverSellBean startOrOverSellBean) {
        //开始待售的订单位置
        MyFrameApplication.startingPosition = (startOrOverSellBean.getStatus() == 1)?recordPosition:-1;
        qrCodeGroupBean.setStatus(startOrOverSellBean.getStatus());
        MyFrameApplication.groupid = (startOrOverSellBean.getStatus() == 1) ? qrCodeGroupBean.getGroupid() : 0;
        recordGroupid = qrCodeGroupBean.getGroupid();
        presenter.getQRCodeStalls(String.valueOf(qrCodeGroupBean.getGroupid()), MyFrameApplication.tokenStr);
        forSaleListAdapter.notifyItemChanged(recordPosition);
        ToastUtils.showShortToast(getContext(), startOrOverSellBean.getStatus() == 1 ? "已开始代售" : "已停止代售");
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (AntiShake.check(view.getId())) {    //判断是否多次点击
            ToastUtils.showShortToast(getContext(), "客官，请慢点点击！");
            return;
        }
        qrCodeGroupBean = (GroupinfoBean) adapter.getItem(position);
        recordGroupid = qrCodeGroupBean.getGroupid();
        recordPosition = position;
        switch (view.getId()) {
            case R.id.tv_show_order_list://点击收缩按钮
                boolean ishow = qrCodeGroupBean.isShowItems();
                qrCodeGroupBean.setShowItems(!ishow);
                if (qrCodeGroupBean.isShowItems()) {
                    presenter.getQRCodeStalls(String.valueOf(qrCodeGroupBean.getGroupid()), MyFrameApplication.tokenStr);
                }
                adapter.notifyItemChanged(position);
                break;
            case R.id.tv_start_sail_btn://点击开始代售
                if (qrCodeGroupBean.getStatus() == 0) {
                    //代售之前检查是否已有组正在代售 list应该是轮询的list
                    for (GroupinfoBean bean : qrCodeGroupBeanList) {
                        if (bean.getStatus() == 1) {
                            ToastUtils.showShortToast(getContext(), "当前有在售的分组，请先关闭当前的分组");
                            return;
                        }
                    }
                    presenter.startingOrOverSell(String.valueOf(qrCodeGroupBean.getGroupid()), 1, PreferencesUtils.getString(getContext(), "token"));
                } else {
                    //停止代售前 检查是否有未完成的收款
//                    if(){
//                        ToastUtils.showShortToast(getContext(), "当前有收款未完成，请完成后再停止代售！");
//                        return;
//                    }
                    presenter.startingOrOverSell(String.valueOf(qrCodeGroupBean.getGroupid()), 0, PreferencesUtils.getString(getContext(), "token"));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClickComplete() {

    }
}
