package com.mchaw.tauruspay.ui.main.home;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.eventbus.LoginSucceedEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingUserEvent;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.StringUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.login.LoginFragment;
import com.mchaw.tauruspay.ui.main.home.constract.HomeConstract;
import com.mchaw.tauruspay.ui.main.home.forsale.ForSaleFragment;
import com.mchaw.tauruspay.ui.main.home.presenter.HomePresenter;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.TransferAccountsFragment;

import org.greenrobot.eventbus.Subscribe;

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
            if(!TextUtils.isEmpty(MyFrameApplication.getInstance().tokenStr)) {
                presenter.getHomeDataBean(MyFrameApplication.getInstance().tokenStr);
            }
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
        if(TextUtils.isEmpty(MyFrameApplication.getInstance().tokenStr)){
            startFragment(new LoginFragment());
        }
        tvNotiveText.setSelected(true);
        tvPreSaleTxt.setText(Html.fromHtml(strPre));
        tvAfterSaleTxt.setText(strAfter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!TextUtils.isEmpty(MyFrameApplication.getInstance().tokenStr)) {
            presenter.getHomeDataBean(MyFrameApplication.getInstance().tokenStr);
        }
    }

    @OnClick({R.id.btn_transfer_btn, R.id.btn_start_sail})
    public void onClick(View view) {
        if (AntiShake.check(view.getId())) {    //判断是否多次点击
            return;
        }
        switch (view.getId()) {
            case R.id.btn_transfer_btn:
                startFragment(new TransferAccountsFragment());
                break;
            case R.id.btn_start_sail:
                startFragment(new ForSaleFragment());
                break;
            default:
                break;
        }
    }

    @Override
    public void setHomeDataBean(UserBean userBean) {
        tvRepertory.setText(StringUtils.fenToYuan(userBean.getDeposit()));
        tvTodayAgencyIncome.setText(StringUtils.fenToYuan(userBean.getDaypoint()));
        tvTodayMoneyForSale.setText(StringUtils.fenToYuan(userBean.getDayamount()));
        tvTodayTimeForSale.setText(String.valueOf(userBean.getDaycount()));
        tvAlreadyIncome.setText(StringUtils.fenToYuan(userBean.getDaydeposit()));
    }

    @Subscribe
    public void sellInfo(MainPollingUserEvent event) {
        if(event != null){
            tvRepertory.setText(StringUtils.fenToYuan(event.getKucun()));
            tvTodayAgencyIncome.setText(StringUtils.fenToYuan(event.getDangrishouyi()));
            tvTodayMoneyForSale.setText(StringUtils.fenToYuan(event.getDangrikeshouedu()));
            tvTodayTimeForSale.setText(String.valueOf(event.getDangrikeshoudanshu()));
            tvAlreadyIncome.setText(StringUtils.fenToYuan(event.getDangriyishouedu()));
        }
    }

    @Subscribe
    public void loginSucceed(LoginSucceedEvent event) {
        if(!TextUtils.isEmpty(MyFrameApplication.getInstance().tokenStr)) {
            presenter.getHomeDataBean(MyFrameApplication.getInstance().tokenStr);
        }
    }
}
