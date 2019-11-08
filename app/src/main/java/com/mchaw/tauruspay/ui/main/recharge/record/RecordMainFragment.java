package com.mchaw.tauruspay.ui.main.recharge.record;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.common.adapter.TabPageAdapter;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/4 10:35
 * @description:
 */
public class RecordMainFragment extends BaseFragment implements ViewPager.OnPageChangeListener{
    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_page)
    ViewPager viewPage;

    @BindArray(R.array.record_list_choise)
    String[] saleTabs;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int currentPage = 0;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_record_main;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        tvBackTitle.setText("审核");
        mFragments.add(new RecordSucceedFragment());
        mFragments.add(new RecordFailedFragment());
        TabPageAdapter adapter = new TabPageAdapter(getChildFragmentManager(), mFragments, saleTabs);
        viewPage.setAdapter(adapter);
        viewPage.setCurrentItem(currentPage);
        viewPage.setOffscreenPageLimit(adapter.getCount());
        tabLayout.setViewPager(viewPage, saleTabs);
        tabLayout.onPageSelected(currentPage);
        viewPage.addOnPageChangeListener(this);
    }

    @OnClick(R.id.iv_back)
    public void onClick(View view){
        getActivity().finish();
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
}
