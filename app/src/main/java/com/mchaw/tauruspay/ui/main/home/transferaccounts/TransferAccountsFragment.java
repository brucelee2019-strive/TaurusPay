package com.mchaw.tauruspay.ui.main.home.transferaccounts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingUserEvent;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.home.TransferAccountsBean;
import com.mchaw.tauruspay.bean.home.TransferUsedAccountsBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.dialog.LoadingDialog;
import com.mchaw.tauruspay.common.util.GsonUtils;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.StringUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.constract.TransferAccountsConstract;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.dialog.TransferAccountDialog;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.presenter.TransferAccountsPresenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/6 16:41
 * @description:
 */
public class TransferAccountsFragment extends BasePresentFragment<TransferAccountsPresenter> implements TransferAccountsConstract.View, TransferAccountDialog.ConfirmListener {

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

    private List<TransferUsedAccountsBean> transferUsedAccountsBeanList = new ArrayList<>();

    @Override
    protected void getIntentData() {
        super.getIntentData();
        transferUsedAccountsBeanList = GsonUtils.parseListFromJson(PreferencesUtils.getString(getActivity(), Constant.USED_ACCOUNT), TransferUsedAccountsBean.class);
    }

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
        //进入转账界面关闭待售(先不要胡来，让服务器处理)
        //MyFrameApplication.groupid = 0;
        //MyFrameApplication.startingPosition = -1;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getHomeDataBean(PreferencesUtils.getString(getContext(),"token"));
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
        TransferUsedAccountsBean transferUsedAccountsBean = new TransferUsedAccountsBean();
        transferUsedAccountsBean.setAccount(etAccount.getText().toString());
        transferUsedAccountsBean.setName(etName.getText().toString());
        if (transferUsedAccountsBeanList == null) {
            transferUsedAccountsBeanList = new ArrayList<>();
        }
        transferUsedAccountsBeanList.add(transferUsedAccountsBean);
        PreferencesUtils.putString(getActivity(), Constant.USED_ACCOUNT, GsonUtils.parserListToJson(transferUsedAccountsBeanList));
        ToastUtils.showShortToast(getContext(), "转账成功！");
        getActivity().finish();
    }

    @Override
    public void setTransferAccountsFail() {
        LoadingDialog.dismissDailog();
        ToastUtils.showShortToast(getContext(), "转账失败！");
    }

    @Override
    public void setHomeDataBean(UserBean userBean) {
        tvAllCost.setText(StringUtils.fenToYuan(userBean.getDeposit()));
        allKuCun = StringUtils.fenToYuan(userBean.getDeposit());
    }

    @Subscribe
    public void sellInfo(MainPollingUserEvent event) {
        if (event != null) {
            tvAllCost.setText(StringUtils.fenToYuan(event.getKucun()));
            allKuCun = StringUtils.fenToYuan(event.getKucun());
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_transfer_account_sure, R.id.tv_all_repertory,R.id.iv_account})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.tv_transfer_account_sure:
                TransferAccountDialog.showDialog(getChildFragmentManager());
                break;
            case R.id.tv_all_repertory:
                if ("0".equals(allKuCun)) {
                    ToastUtils.showShortToast(getContext(),"亲，木有话费");
                    return;
                }
                etAmout.setText(allKuCun);
                break;
            case R.id.iv_account:
                startFragmentForResult(new UsedAccountFragment(), Constant.USED_ACCOUNT_BACK);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constant.USED_ACCOUNT_BACK:
                    String account = data.getStringExtra(Constant.INTENT_ACCOUNT);
                    String name = data.getStringExtra(Constant.INTENT_NAME);
                    etAccount.setText(account);
                    etName.setText(name);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onClickComplete() {
        presenter.getTransferAccountsBean(PreferencesUtils.getString(getContext(), "token"), etCode.getText().toString(),
                etAccount.getText().toString(),
                etName.getText().toString(),
                etAmout.getText().toString());
        LoadingDialog.showDialog(getChildFragmentManager());
    }
}
