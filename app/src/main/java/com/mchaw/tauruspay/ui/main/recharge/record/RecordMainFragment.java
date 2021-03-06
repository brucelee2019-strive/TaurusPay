package com.mchaw.tauruspay.ui.main.recharge.record;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.common.adapter.TabPageAdapter;
import com.mchaw.tauruspay.ui.main.recharge.record.LowerRechargeRecord.LowerRechargeFailFragment;
import com.mchaw.tauruspay.ui.main.recharge.record.LowerRechargeRecord.LowerRechargeSucceedFragment;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/4 10:35
 * @description:
 */
public class RecordMainFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
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
        if (MyFrameApplication.userType == 1) {
            tvBackTitle.setText("我的审核");
            mFragments.add(new LowerRechargeSucceedFragment());
            mFragments.add(new LowerRechargeFailFragment());
        } else {
            tvBackTitle.setText("审核");
            mFragments.add(new RecordSucceedFragment());
            mFragments.add(new RecordFailedFragment());
        }
        TabPageAdapter adapter = new TabPageAdapter(getChildFragmentManager(), mFragments, saleTabs);
        viewPage.setAdapter(adapter);
        viewPage.setCurrentItem(currentPage);
        viewPage.setOffscreenPageLimit(adapter.getCount());
        tabLayout.setViewPager(viewPage, saleTabs);
        tabLayout.onPageSelected(currentPage);
        viewPage.addOnPageChangeListener(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_back_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
            case R.id.tv_back_title:
                getActivity().finish();
                break;
            default:
                break;
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
}
