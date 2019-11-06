package com.mchaw.tauruspay.ui.main.home.transferaccounts;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.home.TransferAccountsBean;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.constract.TransferAccountsConstract;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.presenter.TransferAccountsPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/6 16:41
 * @description:
 */
public class TransferAccountsFragment extends BasePresentFragment<TransferAccountsPresenter> implements TransferAccountsConstract.View{

    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_transfer_account;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        tvBackTitle.setText("转账");
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    public void setTransferAccountsBean(TransferAccountsBean transferAccountsBean) {

    }

    @OnClick({R.id.iv_back, R.id.tv_transfer_account_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.tv_transfer_account_sure:
                //presenter.getTransferAccountsBean();
                break;
            default:
                break;
        }
    }


}
