package com.mchaw.tauruspay.ui.main.mine.bank;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.ui.main.mine.dialog.ChangeBankCardDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 17:06
 * @description:
 */
public class MyBankCardFragment extends BaseFragment {
    @BindView(R.id.tv_back_title)
    TextView tvTitle;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_my_bank_card;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        tvTitle.setText("我的银行卡");
    }

    @OnClick({R.id.iv_back,R.id.cl_my_bank_card})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                this.getActivity().finish();
                break;
            case R.id.cl_my_bank_card:
                ChangeBankCardDialog.showDialog(getChildFragmentManager());
                break;
        }
    }
}
