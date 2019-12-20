package com.mchaw.tauruspay.ui.main.home.forsale;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.eventbus.TradingBeanEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingUserEvent;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.adapter.TabPageAdapter;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.StringUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.ForSaleConstract;
import com.mchaw.tauruspay.ui.main.home.forsale.dialog.CollectionListDialog;
import com.mchaw.tauruspay.ui.main.home.forsale.presenter.ForSalePresenter;
import com.mchaw.tauruspay.ui.main.mine.dialog.ChangeBankCardDialog;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 10:30
 * @description:代售Fragment
 */
public class ForSaleFragment extends BasePresentFragment<ForSalePresenter> implements ForSaleConstract.View, ViewPager.OnPageChangeListener {

    @BindView(R.id.tv_all_coin_num)
    TextView tvAllCoinNum;
    @BindView(R.id.tv_today_income_num)
    TextView tvTodayIncomeNum;
    @BindView(R.id.tv_sailing_coin_num)
    TextView tvSailingCoinNum;

    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;
    @BindView(R.id.tv_notice_text)
    TextView tvNotiveText;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_page)
    ViewPager viewPage;

    @BindView(R.id.iv_yinxiao)
    ImageView ivYinXiao;

    @BindArray(R.array.sale_list_choise)
    String[] saleTabs;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int currentPage = 0;

    private QBadgeView qBadgeView;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_for_sale;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        tvBackTitle.setText("代售");
        tvNotiveText.setSelected(true);
        mFragments.add(new ForSaleListFragment());
        mFragments.add(new CollectionListFragment());
        TabPageAdapter adapter = new TabPageAdapter(getChildFragmentManager(), mFragments, saleTabs);
        viewPage.setAdapter(adapter);
        viewPage.setCurrentItem(currentPage);
        viewPage.setOffscreenPageLimit(adapter.getCount());
        tabLayout.setViewPager(viewPage, saleTabs);
        tabLayout.onPageSelected(currentPage);
        viewPage.addOnPageChangeListener(this);
        if (PreferencesUtils.getBoolean(getContext(), Constant.WARNING_TONE, true)) {
            ivYinXiao.setImageResource(R.drawable.ds_yinxiao_on);
        } else {
            ivYinXiao.setImageResource(R.drawable.ds_yinxiao_off);
        }

        qBadgeView = new QBadgeView(getActivity());
        qBadgeView.setBadgeNumber(0)
                .setGravityOffset(35, 10, true)
                .bindTarget(tabLayout);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getHomeDataBean(PreferencesUtils.getString(getContext(), "token"));
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    public void setHomeDataBean(UserBean userBean) {
        tvAllCoinNum.setText(StringUtils.fenToYuan(userBean.getDeposit()));
        tvTodayIncomeNum.setText(StringUtils.fenToYuan(userBean.getDaypoint()));
        tvSailingCoinNum.setText(StringUtils.fenToYuan(userBean.getDayonsale()));
    }

    @Subscribe
    public void sellInfo(MainPollingUserEvent event) {
        if (event != null) {
            tvAllCoinNum.setText(StringUtils.fenToYuan(event.getKucun()));
            tvTodayIncomeNum.setText(StringUtils.fenToYuan(event.getDangrishouyi()));
            tvSailingCoinNum.setText(StringUtils.fenToYuan(event.getZaishouzhong()));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPage = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick({R.id.iv_back, R.id.tv_back_title, R.id.iv_yinxiao, R.id.tv_yinxiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
            case R.id.tv_back_title:
                getActivity().finish();
                break;
            case R.id.iv_yinxiao:
            case R.id.tv_yinxiao:
                if (PreferencesUtils.getBoolean(getContext(), Constant.WARNING_TONE, true)) {
                    PreferencesUtils.putBoolean(getContext(), Constant.WARNING_TONE, false);
                    ivYinXiao.setImageResource(R.drawable.ds_yinxiao_off);
                } else {
                    PreferencesUtils.putBoolean(getContext(), Constant.WARNING_TONE, true);
                    ivYinXiao.setImageResource(R.drawable.ds_yinxiao_on);
                }
                break;
            default:
                break;
        }
    }

    @Subscribe
    public void tradingAmount(TradingBeanEvent event) {
        if (event != null) {
            //小红点
            qBadgeView.setBadgeNumber(event.getRedPoint());
        }
    }

    public void setRedPoint(int size){
        qBadgeView.setBadgeNumber(size);
    }

    public void noticeOfCollection() {
        CollectionListDialog.showDialog(getChildFragmentManager());
    }
}
