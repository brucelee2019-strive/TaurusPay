package com.mchaw.tauruspay.ui.main.home;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.home.HomeDataBean;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.StringUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.constract.HomeConstract;
import com.mchaw.tauruspay.ui.main.home.forsale.ForSaleFragment;
import com.mchaw.tauruspay.ui.main.home.presenter.HomePresenter;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.TransferAccountsFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : Bruce Lee
 * @date : 2019/11/3 0003 21:02
 * @description :
 */
public class HomeFragment extends BasePresentFragment<HomePresenter> implements HomeConstract.View {

    @BindView(R.id.tv_notice_text)
    TextView tvNotiveText;

    @BindView(R.id.tv_pre_sale_txt)
    TextView tvPreSaleTxt;

    @BindView(R.id.tv_after_sale_txt)
    TextView tvAfterSaleTxt;

    //tv_repertory 余额
    @BindView(R.id.tv_repertory)
    TextView tvRepertory;

    //今日收益
    @BindView(R.id.tv_today_sell_income_money)
    TextView tvTodayAgencyIncome;

    //今日代售额度
    @BindView(R.id.tv_today_money_for_sale)
    TextView tvTodayMoneyForSale;

    //今日代售次数
    @BindView(R.id.tv_today_time_for_sale)
    TextView tvTodayTimeForSale;

    //当天已售额度
    @BindView(R.id.tv_already_income)
    TextView tvAlreadyIncome;

    private String strPre = "*开始代售前，请保持<font color='#FF9600'>金牛话费</font>与<font color='#00aaef'>支付宝</font>在线";
    private String strAfter = "*开始代售时，请及时查询确认收款";

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){

        }else{
            presenter.getHomeDataBean(PreferencesUtils.getString(getContext(),"token"));
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
        tvNotiveText.setSelected(true);
        tvPreSaleTxt.setText(Html.fromHtml(strPre));
        tvAfterSaleTxt.setText(strAfter);
        presenter.getHomeDataBean(PreferencesUtils.getString(getContext(),"token"));
    }

    @OnClick({R.id.tv_transfer_btn, R.id.tv_start_sail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_transfer_btn:
                startFragment(new TransferAccountsFragment());
                break;
            case R.id.tv_start_sail:
                startFragment(new ForSaleFragment());
                break;
            default:
                break;
        }
    }

    @Override
    public void setHomeDataBean(HomeDataBean homeDataBean) {
        tvRepertory.setText(StringUtils.fenToYuan(homeDataBean.getDeposit()));
        tvTodayAgencyIncome.setText(StringUtils.fenToYuan(homeDataBean.getDaypoint()));
        tvTodayMoneyForSale.setText(StringUtils.fenToYuan(homeDataBean.getDayamount()));
        tvTodayTimeForSale.setText(StringUtils.fenToYuan(homeDataBean.getDaycount()));
        tvAlreadyIncome.setText(StringUtils.fenToYuan(homeDataBean.getDaydeposit()));
    }
}
