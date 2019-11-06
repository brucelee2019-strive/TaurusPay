package com.mchaw.tauruspay.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.MainActivity;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;

/**
 * @author Bruce Lee
 * @date : 2019/11/6 13:14
 * @description:
 */
public class SplashFragment extends BaseFragment {
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                SplashFragment.this.getActivity().finish();
            }
        },1000);
    }
}
