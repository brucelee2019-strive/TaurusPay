package com.mchaw.tauruspay.ui.main.home.transferaccounts;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.eventbus.SellInfoEvent;
import com.mchaw.tauruspay.bean.home.HomeDataBean;
import com.mchaw.tauruspay.bean.home.TransferAccountsBean;
import com.mchaw.tauruspay.common.dialog.LoadingDialog;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.StringUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.constract.TransferAccountsConstract;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.presenter.TransferAccountsPresenter;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/6 16:41
 * @description:
 */
public class TransferAccountsFragment extends BasePresentFragment<TransferAccountsPresenter> implements TransferAccountsConstract.View {

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

    @BindView(R.id.tv_all_cost)
    TextView tvAllCost;

    private String allKuCun;


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
        //presenter.getHomeDataBean(PreferencesUtils.getString(getContext(),"token"));
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    public void setTransferAccountsBean(TransferAccountsBean transferAccountsBean) {
        LoadingDialog.dismissDailog();
        if (transferAccountsBean == null) {
            ToastUtils.showShortToast(getContext(), "服务器返回数据为空！");
            return;
        }
        ToastUtils.showShortToast(getContext(), "转账成功");
        getActivity().finish();
    }

    @Override
    public void setTransferAccountsFail() {
        LoadingDialog.dismissDailog();
    }

    @Override
    public void setHomeDataBean(HomeDataBean homeDataBean) {
        tvAllCost.setText(StringUtils.fenToYuan(homeDataBean.getDeposit()));
    }

    @Subscribe
    public void sellInfo(SellInfoEvent event) {
        if (event != null) {
            tvAllCost.setText(StringUtils.fenToYuan(event.getKucun()));
            allKuCun = StringUtils.fenToYuan(event.getKucun());
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_transfer_account_sure, R.id.tv_all_repertory})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.tv_transfer_account_sure:
                presenter.getTransferAccountsBean(PreferencesUtils.getString(getContext(), "token"), etCode.getText().toString(),
                        etAccount.getText().toString(),
                        etName.getText().toString(),
                        etAmout.getText().toString());
                LoadingDialog.showDialog(getChildFragmentManager());
                break;
            case R.id.tv_all_repertory:
                if (allKuCun.equals("0")) {
                    ToastUtils.showShortToast(getContext(),"亲，木有话费");
                    return;
                }
                etAmout.setText(allKuCun);
                break;
            default:
                break;
        }
    }


}
