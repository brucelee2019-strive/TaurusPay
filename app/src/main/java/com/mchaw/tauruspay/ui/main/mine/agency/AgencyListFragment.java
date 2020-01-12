package com.mchaw.tauruspay.ui.main.mine.agency;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.mchaw.tauruspay.bean.agency.AgencyBean;
import com.mchaw.tauruspay.bean.agency.AgencyItemBean;
import com.mchaw.tauruspay.bean.agency.AgencyUser;
import com.mchaw.tauruspay.bean.agency.LowerRateBean;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.forsale.dialog.AgencyDialogFragment;
import com.mchaw.tauruspay.ui.main.mine.agency.adapter.AgencyListAdapter;
import com.mchaw.tauruspay.ui.main.mine.agency.constract.AgencyListConstract;
import com.mchaw.tauruspay.ui.main.mine.agency.presenter.AgencyListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2020/1/11 14:01
 * @description:
 */
public class AgencyListFragment extends BasePresentListFragment<AgencyListPresenter> implements AgencyListConstract.View, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.tv_back_title)
    TextView tvTitle;
    @BindView(R.id.rv_agency_list)
    RecyclerView rvAgencyList;
    @BindView(R.id.tv_acount)
    TextView tvAcount;
    @BindView(R.id.tv_inventory)
    TextView tvInventory;
    @BindView(R.id.tv_live)
    TextView tvLive;

    private List<AgencyItemBean> list = new ArrayList<>();
    private AgencyListAdapter agencyListAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_agency_list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        tvTitle.setText("推广下级赚钱");
        rvAgencyList.setLayoutManager(new LinearLayoutManager(getContext()));
        agencyListAdapter = new AgencyListAdapter(list);
        agencyListAdapter.setOnItemChildClickListener(this);
        rvAgencyList.setAdapter(agencyListAdapter);
        tvLive.setText(MyFrameApplication.userType==1?"(一级代理)":"(二级代理)");
        if(MyFrameApplication.userType==1){
            agencyList.add("4");
            agencyList.add("5");
            agencyList.add("6");
            agencyList.add("7");
            agencyList.add("8");
            agencyList.add("9");
            agencyList.add("10");
            agencyList.add("11");
            agencyList.add("12");
            agencyList.add("13");
            agencyList.add("14");
            agencyList.add("15");
        }else{
            agencyList.add("6");
            agencyList.add("7");
            agencyList.add("8");
            agencyList.add("9");
        }
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
        presenter.getAgent(MyFrameApplication.tokenStr);
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
                roundDialogFragment.setDialogCallBack(new DialogCallBack() {
                    @Override
                    public void onDialogViewClick(int type, Object value) {
                        currentAgency = (String) value;
                        if (TextUtils.isEmpty(currentAgency)) {
                            return;
                        }
                        presenter.changeLowerRate(MyFrameApplication.tokenStr,agencyItemBean.getId(),currentAgency);
                    }
                });
                roundDialogFragment.show(getSupportFragmentManager(), null);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_back_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
            case R.id.tv_back_title:
                this.getActivity().finish();
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
            tvInventory.setText(agencyUser.getPoint());
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
    }
}
