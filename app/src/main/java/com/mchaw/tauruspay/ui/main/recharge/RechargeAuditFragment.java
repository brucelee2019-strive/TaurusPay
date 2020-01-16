package com.mchaw.tauruspay.ui.main.recharge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.DialogCallBack;
import com.mchaw.tauruspay.base.fragment.BasePresentListFragment;
import com.mchaw.tauruspay.bean.eventbus.ForbiddenEvent;
import com.mchaw.tauruspay.bean.eventbus.PXVAuditEvent;
import com.mchaw.tauruspay.bean.eventbus.RechargeAuditEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingUserEvent;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.recharge.AuditBean;
import com.mchaw.tauruspay.bean.recharge.RechargeAuditBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.dialog.LoadingDialog;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.StringUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.main.MainActivity;
import com.mchaw.tauruspay.ui.login.LoginActivity;
import com.mchaw.tauruspay.ui.main.home.forsale.dialog.ConfirmDialogFragment;
import com.mchaw.tauruspay.ui.main.recharge.adapter.RechargeAuditAdapter;
import com.mchaw.tauruspay.ui.main.recharge.constract.RechargeAuditConstract;
import com.mchaw.tauruspay.ui.main.recharge.presenter.RechargeAuditPresenter;
import com.mchaw.tauruspay.ui.main.recharge.record.RecordMainFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mchaw.tauruspay.base.dialog.BaseDialogFragment.DIALOG_CANCEL;
import static com.mchaw.tauruspay.base.dialog.BaseDialogFragment.DIALOG_CONFIRM;

/**
 * @author Bruce Lee
 * @date : 2020/1/10 10:59
 * @description:
 */
public class RechargeAuditFragment extends BasePresentListFragment<RechargeAuditPresenter> implements RechargeAuditConstract.View, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.rv_income_audit)
    RecyclerView rvIncomeAudit;

    @BindView(R.id.tv_repertory_money_audit)
    TextView tvRepertokryMoneyAudit;

    private List<RechargeAuditBean> rechargeAuditBeanList = new ArrayList();
    private RechargeAuditAdapter rechargeAuditAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_recharge_audit;
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
        rvIncomeAudit.setLayoutManager(new LinearLayoutManager(getContext()));
        rechargeAuditAdapter = new RechargeAuditAdapter(rechargeAuditBeanList);
        rechargeAuditAdapter.setOnItemChildClickListener(this);
        rvIncomeAudit.setAdapter(rechargeAuditAdapter);
    }

    @Override
    protected void initHintViews() {
        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rvIncomeAudit.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rvIncomeAudit.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rvIncomeAudit.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    protected void onRefresh() {
        rechargeAuditAdapter.setEmptyView(loadingView);
        presenter.getHomeDataBean(PreferencesUtils.getString(getContext(), "token"));
        if (MyFrameApplication.userType == 1) {
            presenter.getRechargeAuditList(MyFrameApplication.tokenStr, 0, 0);
        }
    }

    @Override
    public void setHomeDataBean(UserBean userBean) {
        tvRepertokryMoneyAudit.setText(StringUtils.fenToYuan(userBean.getDeposit()));
    }

    @Override
    public void setRechargeAudit(AuditBean auditBean) {
        LoadingDialog.dismissDailog();
        EventBus.getDefault().post(new PXVAuditEvent());
        onRefresh();
        if (auditBean.getStatus() == 4) {
            ToastUtils.showShortToast(getContext(), "您已确认此笔充值");
        } else {
            ToastUtils.showShortToast(getContext(), "您已拒绝此笔充值");
        }
    }

    @Override
    public void setRechargeAuditFail() {
        LoadingDialog.dismissDailog();
    }

    @Override
    public void setRechargeAuditList(List<RechargeAuditBean> list) {
        if (list != null && list.size() > 0) {
            rechargeAuditAdapter.setNewData(list);
        } else {
            rechargeAuditAdapter.setNewData(null);
            rechargeAuditAdapter.setEmptyView(notDataView);
        }
    }

    @Override
    public void setRechargeAuditListFail() {
        rechargeAuditAdapter.setEmptyView(errorView);
    }

    @Subscribe
    public void sellInfo(MainPollingUserEvent event) {
        if (event != null) {
            tvRepertokryMoneyAudit.setText(StringUtils.fenToYuan(event.getKucun()));
        }
    }

    //生命周期管理
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        RechargeAuditBean rechargeAuditBean = (RechargeAuditBean) adapter.getItem(position);
        switch (view.getId()) {
            case R.id.btn_audit_btn:
                //弹窗
                ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance();
                confirmDialogFragment.setMsg("充值审核");
                confirmDialogFragment.setContent("确认银行卡是否收到账号\n[" + rechargeAuditBean.getName() + "]的[" + StringUtils.fenToYuan(rechargeAuditBean.getAmount()) + "]\n的充值款");
                confirmDialogFragment.setCancelText("没有收到");
                confirmDialogFragment.setConfirmText("确认收到");
                confirmDialogFragment.setListenCancel(true);
                confirmDialogFragment.setDialogCallBack(new DialogCallBack() {
                    @Override
                    public void onDialogViewClick(int type, Object value) {
                        if (type == DIALOG_CONFIRM) {
                            LoadingDialog.showDialog(getChildFragmentManager());
                            presenter.getRechargeAudit(MyFrameApplication.tokenStr, rechargeAuditBean.getOrderid(), 4);
                        } else if (type == DIALOG_CANCEL) {
                            LoadingDialog.showDialog(getChildFragmentManager());
                            presenter.getRechargeAudit(MyFrameApplication.tokenStr, rechargeAuditBean.getOrderid(), 3);
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

    @OnClick({R.id.tv_record_audit})
    public void onClick(View view) {
        if (AntiShake.check(view.getId())) {    //判断是否多次点击
            return;
        }
        switch (view.getId()) {
            case R.id.tv_record_audit:
                startFragment(new RecordMainFragment());
                break;
            default:
                break;
        }
    }

    /**
     * 账号禁用
     *
     * @param event
     */
    @Subscribe
    public void rechargeAuditUpdate(RechargeAuditEvent event) {
        onRefresh();
    }
}
