package com.mchaw.tauruspay.ui.main.mine;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.mine.about.AboutFragment;

import butterknife.OnClick;

/**
 * @author : Bruce Lee
 * @date : 2019/11/3 0003 21:11
 * @description :
 */
public class MineFragment extends BaseFragment {
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {

    }

    @OnClick({R.id.cl_bill,R.id.cl_bank_set,R.id.cl_qr_code,R.id.cl_activate_word,R.id.cl_change_password,R.id.cl_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cl_bill:
                break;
            case R.id.cl_bank_set:
                break;
            case R.id.cl_qr_code:
                break;
            case R.id.cl_activate_word:
                break;
            case R.id.cl_change_password:
                break;
            case R.id.cl_about:
                startFragment(new AboutFragment());
                break;
        }
    }
}
