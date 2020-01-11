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
import com.mchaw.tauruspay.bean.entry.MultipleItem;
import com.mchaw.tauruspay.bean.eventbus.QRCodeNotPassEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingGroupInfoEvent;
import com.mchaw.tauruspay.bean.home.StartOrOverSellBean;
import com.mchaw.tauruspay.bean.qrcode.GroupinfoBean;
import com.mchaw.tauruspay.common.dialog.LoadingDialog;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.forsale.adapter.ForSaleListAdapter;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.ForSaleListConstract;
import com.mchaw.tauruspay.ui.main.home.forsale.dialog.ConfirmDialogFragment;
import com.mchaw.tauruspay.ui.main.home.forsale.presenter.ForSaleListPresenter;
import com.mchaw.tauruspay.ui.main.mine.qrcode.QRCodeFragment;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.mchaw.tauruspay.base.dialog.BaseDialogFragment.DIALOG_CONFIRM;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 11:56
 * @description:待售列表Fragment
 */
public class ForSaleListFragment extends BasePresentListFragment<ForSaleListPresenter> implements ForSaleListConstract.View, BaseQuickAdapter.OnItemChildClickListener {

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
        forSaleListAdapter = new ForSaleListAdapter(multipleItemList);
        forSaleListAdapter.setOnItemChildClickListener(this);
        rvForSalelist.setAdapter(forSaleListAdapter);
        MyFrameApplication.startingPosition = PreferencesUtils.getInt(MyFrameApplication.getInstance(), "startingPosition", -1);
        onRefresh();
    }

    @Override
    protected void initHintViews() {
        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rvForSalelist.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.qrcode_for_sail_empty_view, (ViewGroup) rvForSalelist.getParent(), false);
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

    private List<MultipleItem> multipleItemList = new ArrayList<>();

    /**
     * 代售分组
     *
     * @param list
     */
    @Override
    public void setQRCodeGroupList(List<GroupinfoBean> list) {
        qrCodeGroupBeanList = list;
        if (list != null && list.size() > 0) {
            multipleItemList = new ArrayList<>();
            for (GroupinfoBean groupinfoBean : list) {
                switch (groupinfoBean.getPaytype()) {
                    case 0:
                        multipleItemList.add(new MultipleItem<>(MultipleItem.ER_CODE_FOR_SAIL_FIXED_WX, groupinfoBean));
                        break;
                    case 1:
                        multipleItemList.add(new MultipleItem<>(MultipleItem.ER_CODE_FOR_SAIL_FIXED_ALIPAY, groupinfoBean));
                        break;
                    case 3:
                        multipleItemList.add(new MultipleItem<>(MultipleItem.ER_CODE_OR_SAIL_AT_WILL_ALIPAY, groupinfoBean));
                        break;
                    case 4:
                        multipleItemList.add(new MultipleItem<>(MultipleItem.ER_CODE_OR_SAIL_AT_WILL_WX, groupinfoBean));
                        break;
                    default:
                        multipleItemList.add(new MultipleItem<>(MultipleItem.ER_CODE_FOR_SAIL_FIXED_WX, groupinfoBean));
                        break;
                }
            }
            forSaleListAdapter.setNewData(multipleItemList);
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
        //forSaleListAdapter.notifyItemChanged(recordPosition);
        forSaleListAdapter.notifyDataSetChanged();
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
            PreferencesUtils.putInt(MyFrameApplication.getInstance(), "startingPosition", MyFrameApplication.startingPosition);
            forSaleListAdapter.notifyDataSetChanged();
            return;
        }
        if (event.getGroupinfo().getQrcodes() == null || event.getGroupinfo().getQrcodes().size() <= 0) {
            return;
        }
        if (MyFrameApplication.groupid == event.getGroupinfo().getGroupid()) {
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
                forSaleListAdapter.notifyDataSetChanged();
            }
        }
    }

    //点击开始待售成功
    @Override
    public void setStartingOrOverSell(StartOrOverSellBean startOrOverSellBean) {
        LoadingDialog.dismissDailog();
        //开始待售的订单位置
        MyFrameApplication.startingPosition = (startOrOverSellBean.getStatus() == 1) ? recordPosition : -1;
        PreferencesUtils.putInt(MyFrameApplication.getInstance(), "startingPosition", MyFrameApplication.startingPosition);
        qrCodeGroupBean.setStatus(startOrOverSellBean.getStatus());
        MyFrameApplication.groupid = (startOrOverSellBean.getStatus() == 1) ? qrCodeGroupBean.getGroupid() : 0;
        recordGroupid = qrCodeGroupBean.getGroupid();
        presenter.getQRCodeStalls(String.valueOf(qrCodeGroupBean.getGroupid()), MyFrameApplication.tokenStr);
        forSaleListAdapter.notifyItemChanged(recordPosition);
        ToastUtils.showShortToast(getContext(), startOrOverSellBean.getStatus() == 1 ? "已开始代售" : "已停止代售");
    }

    @Override
    public void setStartingOrOverSellFail() {
        LoadingDialog.dismissDailog();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (AntiShake.check(view.getId())) {    //判断是否多次点击
            ToastUtils.showShortToast(getContext(), "客官，请慢点点击！");
            return;
        }
        MultipleItem multipleItem = (MultipleItem) adapter.getData().get(position);
        if (multipleItem.getItemType() == MultipleItem.ER_CODE_FOR_SAIL_FIXED_WX || multipleItem.getItemType() == MultipleItem.ER_CODE_FOR_SAIL_FIXED_ALIPAY) {
            qrCodeGroupBean = (GroupinfoBean) multipleItem.getData();
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
                        //弹窗
                        ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance();
                        confirmDialogFragment.setMsg("代售警告");
                        confirmDialogFragment.setContent("请确保[支付宝|微信]账号为\n[" + qrCodeGroupBean.getAccount() + "]再代售\n否则有可能造成自动结单异常！");
                        confirmDialogFragment.setCancelText("取消");
                        confirmDialogFragment.setConfirmText("确认");
                        confirmDialogFragment.setListenCancel(true);
                        confirmDialogFragment.setDialogCallBack(new DialogCallBack() {
                            @Override
                            public void onDialogViewClick(int type, Object value) {
                                if (type == DIALOG_CONFIRM) {
                                    LoadingDialog.showDialog(getChildFragmentManager());
                                    presenter.startingOrOverSell(String.valueOf(qrCodeGroupBean.getGroupid()), 1, PreferencesUtils.getString(getContext(), "token"));
                                } else {

                                }
                            }
                        });
                        confirmDialogFragment.show(this.getFragmentManager(), "confirmDialogFragment");
                    } else {
                        //停止代售前 检查是否有未完成的收款
                        //弹窗
                        ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance();
                        confirmDialogFragment.setMsg("提示");
                        confirmDialogFragment.setContent("是否停止接单\n停止接单后请确保所有的单已结单\n停止接单后再次接单将重新排队");
                        confirmDialogFragment.setCancelText("取消");
                        confirmDialogFragment.setConfirmText("确认");
                        confirmDialogFragment.setListenCancel(true);
                        confirmDialogFragment.setDialogCallBack(new DialogCallBack() {
                            @Override
                            public void onDialogViewClick(int type, Object value) {
                                if (type == DIALOG_CONFIRM) {
                                    LoadingDialog.showDialog(getChildFragmentManager());
                                    presenter.startingOrOverSell(String.valueOf(qrCodeGroupBean.getGroupid()), 0, PreferencesUtils.getString(getContext(), "token"));
                                } else {

                                }
                            }
                        });
                        confirmDialogFragment.show(this.getFragmentManager(), "confirmDialogFragment");
                    }
                    break;
                default:
                    break;
            }
        } else if (multipleItem.getItemType() == MultipleItem.ER_CODE_OR_SAIL_AT_WILL_ALIPAY || multipleItem.getItemType() == MultipleItem.ER_CODE_OR_SAIL_AT_WILL_WX) {
            qrCodeGroupBean = (GroupinfoBean) multipleItem.getData();
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
                        //弹窗
                        ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance();
                        confirmDialogFragment.setMsg("代售警告");
                        confirmDialogFragment.setContent("请确保[支付宝|微信]账号为\n[" + qrCodeGroupBean.getAccount() + "]再代售\n否则有可能造成自动结单异常！");
                        confirmDialogFragment.setCancelText("取消");
                        confirmDialogFragment.setConfirmText("确认");
                        confirmDialogFragment.setListenCancel(true);
                        confirmDialogFragment.setDialogCallBack(new DialogCallBack() {
                            @Override
                            public void onDialogViewClick(int type, Object value) {
                                if (type == DIALOG_CONFIRM) {
                                    LoadingDialog.showDialog(getChildFragmentManager());
                                    presenter.startingOrOverSell(String.valueOf(qrCodeGroupBean.getGroupid()), 1, PreferencesUtils.getString(getContext(), "token"));
                                } else {

                                }
                            }
                        });
                        confirmDialogFragment.show(this.getFragmentManager(), "confirmDialogFragment");
                    } else {
                        //停止代售前 检查是否有未完成的收款
                        // 弹窗
                        ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance();
                        confirmDialogFragment.setMsg("提示");
                        confirmDialogFragment.setContent("是否停止接单\n停止接单后请确保所有的单已结单\n停止接单后再次接单将重新排队");
                        confirmDialogFragment.setCancelText("取消");
                        confirmDialogFragment.setConfirmText("确认");
                        confirmDialogFragment.setListenCancel(true);
                        confirmDialogFragment.setDialogCallBack(new DialogCallBack() {
                            @Override
                            public void onDialogViewClick(int type, Object value) {
                                if (type == DIALOG_CONFIRM) {
                                    LoadingDialog.showDialog(getChildFragmentManager());
                                    presenter.startingOrOverSell(String.valueOf(qrCodeGroupBean.getGroupid()), 0, PreferencesUtils.getString(getContext(), "token"));
                                } else {

                                }
                            }
                        });
                        confirmDialogFragment.show(this.getFragmentManager(), "confirmDialogFragment");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 二维码库二维码没有通过
     *
     * @param event
     */
    @Subscribe
    public void qrCodeNotPass(QRCodeNotPassEvent event) {
        startFragment(new QRCodeFragment());
    }
}
