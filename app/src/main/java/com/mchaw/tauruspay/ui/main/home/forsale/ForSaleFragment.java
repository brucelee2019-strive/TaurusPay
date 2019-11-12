package com.mchaw.tauruspay.ui.main.home.forsale;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.home.ForSaleBean;
import com.mchaw.tauruspay.bean.home.HomeDataBean;
import com.mchaw.tauruspay.bean.home.SelledOrderBean;
import com.mchaw.tauruspay.bean.home.SellingOrderBean;
import com.mchaw.tauruspay.common.adapter.TabPageAdapter;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.ForSaleConstract;
import com.mchaw.tauruspay.ui.main.home.forsale.presenter.ForSalePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 10:30
 * @description:代售Fragment
 */
public class ForSaleFragment extends BasePresentFragment<ForSalePresenter> implements ForSaleConstract.View,ViewPager.OnPageChangeListener{

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

    @BindArray(R.array.sale_list_choise)
    String[] saleTabs;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int currentPage = 0;

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
        presenter.getHomeDataBean(PreferencesUtils.getString(getContext(),"token"));
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    public void setForSaleBean(ForSaleBean forSaleBean) {

    }

    @Override
    public void setForSaleList(List<SellingOrderBean> list) {

    }

    @Override
    public void setCollectionlist(List<SelledOrderBean> list) {

    }

    @Override
    public void setHomeDataBean(HomeDataBean homeDataBean) {
        tvAllCoinNum.setText(String.valueOf(homeDataBean.getDeposit()));
        tvTodayIncomeNum.setText(String.valueOf(0));
        tvSailingCoinNum.setText(String.valueOf(0));
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

    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            default:
                break;
        }
    }


}
