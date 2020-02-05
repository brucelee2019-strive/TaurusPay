package com.mchaw.tauruspay.ui.main.mine.agency;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.DialogCallBack;
import com.mchaw.tauruspay.base.fragment.BasePresentListFragment;
import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.agency.AgencyBean;
import com.mchaw.tauruspay.bean.agency.AgencyItemBean;
import com.mchaw.tauruspay.bean.agency.AgencyUser;
import com.mchaw.tauruspay.bean.agency.LowerRateBean;
import com.mchaw.tauruspay.bean.agency.RateBean;
import com.mchaw.tauruspay.bean.eventbus.LoginSucceedEvent;
import com.mchaw.tauruspay.common.util.StringUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.home.forsale.dialog.AgencyDialogFragment;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.TransferAccountsFragment;
import com.mchaw.tauruspay.ui.main.mine.agency.adapter.AgencyListAdapter;
import com.mchaw.tauruspay.ui.main.mine.agency.constract.AgencyListConstract;
import com.mchaw.tauruspay.ui.main.mine.agency.presenter.AgencyListPresenter;
import com.mchaw.tauruspay.ui.repository.LoginModel;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2020/1/15 11:30
 * @description:
 */
public class AgencyHomeFragment extends BasePresentListFragment<AgencyListPresenter> implements AgencyListConstract.View, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.rv_agency_list)
    RecyclerView rvAgencyList;
    @BindView(R.id.tv_acount)
    TextView tvAcount;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.tv_live)
    TextView tvLive;
    @BindView(R.id.tv_down_link)
    TextView tvDownLink;
    @BindView(R.id.tv_broadcast_code)
    TextView tvBroadcastCode;
    @BindView(R.id.tv_y_day_despoint)
    TextView tvYDayDespoint;
    @BindView(R.id.tv_y_day_point)
    TextView tvYDayPoint;
    @BindView(R.id.tv_day_despoint)
    TextView tvDayDespoint;
    @BindView(R.id.tv_day_point)
    TextView tvDayPoint;
    @BindView(R.id.btn_agency_rule)
    Button btnAgencyRule;


    private List<AgencyItemBean> list = new ArrayList<>();
    private AgencyListAdapter agencyListAdapter;
    private List<String> ruleList = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_agency_home;
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
            agentResume();
        }
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        tvDownLink.setText("http://115.144.238.240:8090/index.html");
        rvAgencyList.setLayoutManager(new LinearLayoutManager(getContext()));
        agencyListAdapter = new AgencyListAdapter(list);
        agencyListAdapter.setOnItemChildClickListener(this);
        rvAgencyList.setAdapter(agencyListAdapter);
        agentResume();
    }

    /**
     * 一级代理账号切换,初始话方法
     */
    private void agentResume(){
        tvLive.setText(MyFrameApplication.userType == 1 ? "(一级代理)" : "(二级代理)");
        btnAgencyRule.setVisibility(MyFrameApplication.userType == 1 ? View.VISIBLE : View.GONE);
        onRefresh();
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    protected void initHintViews() {
        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rvAgencyList.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rvAgencyList.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rvAgencyList.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    protected void onRefresh() {
        agencyListAdapter.setEmptyView(loadingView);
        if(MyFrameApplication.userType == 1) {
            presenter.getAgent(MyFrameApplication.tokenStr);
        }
    }

    private List<String> agencyList = new ArrayList<>();
    private String currentAgency;
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        AgencyItemBean agencyItemBean = (AgencyItemBean) adapter.getItem(position);
        currentAgency = String.valueOf(agencyItemBean.getRate());
        switch (view.getId()) {
            case R.id.btn_change_rate:
                int pos = agencyList.indexOf(currentAgency);
                AgencyDialogFragment roundDialogFragment = AgencyDialogFragment.newInstance(agencyList, pos < 0 ? 0 : pos);
                roundDialogFragment.setMsg("设置账号[" + agencyItemBean.getName() + "]的返点为:");
                roundDialogFragment.setDialogCallBack(new DialogCallBack() {
                    @Override
                    public void onDialogViewClick(int type, Object value) {
                        currentAgency = (String) value;
                        if (TextUtils.isEmpty(currentAgency)) {
                            return;
                        }
                        presenter.changeLowerRate(MyFrameApplication.tokenStr, agencyItemBean.getId(), currentAgency);
                    }
                });
                roundDialogFragment.show(getSupportFragmentManager(), null);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.btn_copy_link, R.id.btn_copy_code, R.id.btn_agency_rule,R.id.btn_transfer_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_copy_link:
                if (TextUtils.isEmpty(tvDownLink.getText())) {
                    return;
                }
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", tvDownLink.getText());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                if (TextUtils.isEmpty(tvDownLink.getText())) {
                    ToastUtils.showShortToast(getContext(), "没有可复制的内容");
                    return;
                }
                ToastUtils.showShortToast(getContext(), "已复制<" + tvDownLink.getText() + ">到剪切板");
                break;
            case R.id.btn_copy_code:
                if (TextUtils.isEmpty(tvBroadcastCode.getText())) {
                    return;
                }
                //获取剪贴板管理器：
                ClipboardManager cm1 = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData1 = ClipData.newPlainText("Label", tvBroadcastCode.getText());
                // 将ClipData内容放到系统剪贴板里。
                cm1.setPrimaryClip(mClipData1);
                if (TextUtils.isEmpty(tvBroadcastCode.getText())) {
                    ToastUtils.showShortToast(getContext(), "没有可复制的内容");
                    return;
                }
                ToastUtils.showShortToast(getContext(), "已复制<" + tvBroadcastCode.getText() + ">到剪切板");
                break;
            case R.id.btn_agency_rule:
                AgencyDialogFragment roundDialogFragment = AgencyDialogFragment.newInstance(ruleList, 1);
                roundDialogFragment.setMsg("一级代理的返点规则为:");
                roundDialogFragment.setCancelVisible(false);
                roundDialogFragment.setDialogCallBack(new DialogCallBack() {
                    @Override
                    public void onDialogViewClick(int type, Object value) {

                    }
                });
                roundDialogFragment.show(getSupportFragmentManager(), null);
                break;
            case R.id.btn_transfer_btn:
                startFragment(new TransferAccountsFragment());
                break;
            default:
                break;
        }
    }

    @Override
    public void setAgent(AgencyBean agencyBean) {
        List<AgencyItemBean> agencyItemBeanList = new ArrayList<>();
        agencyItemBeanList = agencyBean.getList();
        AgencyUser agencyUser = agencyBean.getAgencyUser();
        if (agencyUser != null) {
            tvAcount.setText(agencyUser.getName());
            tvRate.setText(StringUtils.fenToYuan(agencyUser.getQuota()));
            MyFrameApplication.userRate = agencyUser.getRate();
            tvYDayDespoint.setText(StringUtils.fenToYuan(agencyUser.getYdaydeposit()));
            tvYDayPoint.setText(StringUtils.fenToYuan(agencyUser.getYdaypoint()));
            tvDayDespoint.setText(StringUtils.fenToYuan(agencyUser.getDaydeposit()));
            tvDayPoint.setText(StringUtils.fenToYuan(agencyUser.getDaypoint()));
            tvBroadcastCode.setText(agencyUser.getCode());
        }
        agencyList = new ArrayList<>();
        if (MyFrameApplication.userType == 1) {
            for (int i = 0; i < MyFrameApplication.userRate; i++) {
                agencyList.add(String.valueOf(i));
            }
        } else {
            for (int i = 0; i < MyFrameApplication.userRate; i++) {
                agencyList.add(String.valueOf(i));
            }
        }
        List<RateBean> rateBeanList = new ArrayList<>();
        rateBeanList = agencyBean.getRate();
        if (rateBeanList != null && rateBeanList.size() > 0) {
            ruleList = new ArrayList<>();
            ruleList.add("当日代售额度 : 返点率");
            for (int i = 0; i < rateBeanList.size(); i++) {
                if (i == rateBeanList.size() - 1) {
                    ruleList.add("      大于" + StringUtils.fenToWYuan(rateBeanList.get(i).getMin()) + "万 :   " + rateBeanList.get(i).getRate() + "‰");
                } else {
                    ruleList.add("      " + StringUtils.fenToWYuan(rateBeanList.get(i).getMin()) + "-" + StringUtils.fenToWYuan(rateBeanList.get(i).getMax()) + "万 :   " + rateBeanList.get(i).getRate() + "‰");
                }
            }
        }
        if (agencyItemBeanList != null && agencyItemBeanList.size() > 0) {
            agencyListAdapter.setNewData(agencyItemBeanList);
        } else {
            agencyListAdapter.setNewData(null);
            agencyListAdapter.setEmptyView(notDataView);
        }
    }

    @Override
    public void setAgentFail() {
        agencyListAdapter.setEmptyView(errorView);
    }

    @Override
    public void setChangeLowerRate(LowerRateBean lowerRateBean) {
        onRefresh();
        ToastUtils.showShortToast(getContext(), "设置成功");
    }

    @Override
    public void setChangeLowerRateFail() {
        ToastUtils.showShortToast(getContext(), "设置失败");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Subscribe
    public void loginSucceed(LoginSucceedEvent event) {
        onRefresh();
    }
}