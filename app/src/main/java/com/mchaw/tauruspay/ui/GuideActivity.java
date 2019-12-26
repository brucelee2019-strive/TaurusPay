package com.mchaw.tauruspay.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.lwj.widget.viewpagerindicator.ViewPagerIndicator;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.activity.BaseActivity;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/12/25 13:54
 * @description:
 */
public class GuideActivity extends BaseActivity {
    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.indicator_line)
    ViewPagerIndicator indicatorLine;

    private View view1, view2, view3,view4,view5;
    private List<View> viewList;

    @Override
    public int getContentViewId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initActivity() {
        super.initActivity();
        LayoutInflater lf = getLayoutInflater().from(this);
        view1 = lf.inflate(R.layout.guide_layout1, null);
        view2 = lf.inflate(R.layout.guide_layout2, null);
        view3 = lf.inflate(R.layout.guide_layout3, null);
        view4 = lf.inflate(R.layout.guide_layout4, null);
        view5 = lf.inflate(R.layout.guide_layout5, null);

        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);
        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));//删除页卡
            }


            @Override
            public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
                container.addView(viewList.get(position), 0);//添加页卡
                return viewList.get(position);
            }

            @Override
            public int getCount() {
                return viewList.size();//返回页卡的数量
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;//官方提示这样写
            }

        };
        viewPage.setAdapter(pagerAdapter);

        //viewpager是固定页数, 传入viewpager即可
        indicatorLine.setViewPager(viewPage);

        //viewpager是轮播图 ,如:总数为100000个 实际展示页为6个
        //需要传入实际展示个数num
        indicatorLine.setViewPager(viewPage,5);

        //真无限循环BannerView,配合BannerView,增加以下setViewPager
        //https://github.com/LinweiJ/BannerView
        // 两种情况 更多细节请看demo/BannerViewActivity
        // if mBannerView.setCircle(true);无限循环
        //indicatorLine.setViewPager(mBannerView.getViewPager(),true);
        // if mBannerView.setCircle(false);固定 不循环
        //indicatorLine.setViewPager(mBannerView.getViewPager(),false);
    }

    @OnClick({R.id.iv_back, R.id.tv_back_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
            case R.id.tv_back_title:
                GuideActivity.this.finish();
                break;
            default:
                break;
        }
    }
}
