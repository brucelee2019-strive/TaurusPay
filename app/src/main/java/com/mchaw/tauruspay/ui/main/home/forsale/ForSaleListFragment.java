package com.mchaw.tauruspay.ui.main.home.forsale;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.home.StartOrOverSellBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeStallBean;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.forsale.adapter.ForSaleListAdapter;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.ForSaleListConstract;
import com.mchaw.tauruspay.ui.main.home.forsale.presenter.ForSaleListPresenter;
import com.mchaw.tauruspay.ui.main.recharge.RechargeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 11:56
 * @description:待售列表Fragment
 */
public class ForSaleListFragment extends BasePresentFragment<ForSaleListPresenter> implements ForSaleListConstract.View, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.rv_for_sale_list)
    RecyclerView rvForSalelist;

    private List<QRCodeGroupBean> qrCodeGroupBeanList = new ArrayList<>();
    private ForSaleListAdapter forSaleListAdapter;
    private QRCodeGroupBean qrCodeGroupBean;
    private int groupid;

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
        presenter.getQRCodeGroupList(PreferencesUtils.getString(getContext(), "token"));
    }

    /**
     * 代售分组
     * @param list
     */
    @Override
    public void setQRCodeGroupList(List<QRCodeGroupBean> list) {
        qrCodeGroupBeanList = list;
        forSaleListAdapter.setNewData(list);
    }

    /**
     * 代售分组详细信息
     * @param bean
     */
    @Override
    public void setQRCodeStalls(QRCodeStallBean bean) {
        //组装数据给qrCodeListAdapter
        if (bean == null) {
            ToastUtils.showShortToast(getContext(), "服务器返回数据为null!");
        }
        if (bean.getQrcodes() == null || bean.getQrcodes().size() <= 0) {
            ToastUtils.showShortToast(getContext(), "服务器返回数据为null!");
        }
        if (groupid == bean.getGroupid()) {//确保同一组
            //赋值 list(12个二维码档口id)
            qrCodeGroupBean.setQrcodes(bean.getQrcodes());
        }
        forSaleListAdapter.notifyDataSetChanged();
    }

    @Override
    public void setStartingOrOverSell(StartOrOverSellBean startOrOverSellBean) {
        ToastUtils.showShortToast(getContext(),qrCodeGroupBean.getStatus()==0?"已开始代售":"已停止代售");
        qrCodeGroupBean.setStatus(qrCodeGroupBean.getStatus()==0?1:0);
        forSaleListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        qrCodeGroupBean = (QRCodeGroupBean) adapter.getItem(position);
        groupid = qrCodeGroupBean.getId();
        switch (view.getId()) {
            case R.id.tv_show_order_list:
                boolean ishow = qrCodeGroupBean.isShowItems();
                qrCodeGroupBean.setShowItems(!ishow);
                adapter.notifyItemChanged(position);
                if (!ishow) {
                    presenter.getQRCodeStalls(String.valueOf(groupid), PreferencesUtils.getString(getContext(), "token"));
                }
                break;
            case R.id.tv_start_sail_btn://点击开始代售
                if(qrCodeGroupBean.getStatus() == 0) {
                    //代售之前检查是否已有组正在代售 list应该是轮询的list
                    for (QRCodeGroupBean bean : qrCodeGroupBeanList) {
                        if (bean.getStatus() == 1) {
                            ToastUtils.showShortToast(getContext(), "当前有在售的分组，请先关闭当前的分组");
                            return;
                        }
                    }
                    presenter.startingOrOverSell(String.valueOf(qrCodeGroupBean.getId()), 1, PreferencesUtils.getString(getContext(), "token"));
                }else{
                    //停止代售前 检查是否有未完成的收款
//                    if(){
//                        ToastUtils.showShortToast(getContext(), "当前有收款未完成，请完成后再停止代售！");
//                        return;
//                    }
                    presenter.startingOrOverSell(String.valueOf(qrCodeGroupBean.getId()), 0, PreferencesUtils.getString(getContext(), "token"));
                }
                break;
            default:
                break;
        }
    }
}
