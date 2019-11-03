package com.mchaw.tauruspay.ui.main.besure;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.di.component.ActivityComponent;

/**
 * @author : Bruce Lee
 * @date : 2019/11/3 0003 21:09
 * @description :
 */
public class BesureFragment extends BasePresentFragment {
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_besure;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }
}
