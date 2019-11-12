package com.mchaw.tauruspay.ui.main.home.transferaccounts;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.home.TransferAccountsBean;
import com.mchaw.tauruspay.common.dialog.LoadingDialog;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
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

    @BindView(R.id.et_amout)
    EditText etAmout;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_passwd)
    EditText etCode;


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
        LoadingDialog.dismissDailog();
        if(transferAccountsBean == null){
            ToastUtils.showShortToast(getContext(),"服务器返回数据为空！");
            return;
        }
        ToastUtils.showShortToast(getContext(),"转账成功");
        getActivity().finish();
    }

    @Override
    public void setTransferAccountsFail() {
        LoadingDialog.dismissDailog();
    }

    @OnClick({R.id.iv_back, R.id.tv_transfer_account_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.tv_transfer_account_sure:
                presenter.getTransferAccountsBean(PreferencesUtils.getString(getContext(),"token"),etCode.getText().toString(),
                        etAccount.getText().toString(),
                        etName.getText().toString(),
                        etAmout.getText().toString());
                LoadingDialog.showDialog(getChildFragmentManager());
                break;
            default:
                break;
        }
    }


}
