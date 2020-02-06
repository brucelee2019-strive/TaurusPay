package com.mchaw.tauruspay.ui.main.mine.withdraw;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.DialogCallBack;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.login.PasswordBean;
import com.mchaw.tauruspay.bean.withdraw.WithdrawBean;
import com.mchaw.tauruspay.common.dialog.LoadingDialog;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.StringUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.login.password.constract.PasswordConstract;
import com.mchaw.tauruspay.ui.login.password.presenter.PasswordPresenter;
import com.mchaw.tauruspay.ui.main.home.forsale.dialog.ConfirmDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mchaw.tauruspay.base.dialog.BaseDialogFragment.DIALOG_CONFIRM;

/**
 * @author Bruce Lee
 * @date : 2020/2/4 15:52
 * @description:
 */
public class WithdrawFragment extends BasePresentFragment<WithdrawPresenter> implements WithdrawConstract.View {

    @BindView(R.id.tv_back_title)
    TextView tvTitle;
    @BindView(R.id.tv_all_cost)
    TextView tvAllCost;
    @BindView(R.id.et_quota)
    EditText etQuota;
    @BindView(R.id.et_card_no)
    EditText etCardNumber;
    @BindView(R.id.et_card_brand)
    EditText etBank;
    @BindView(R.id.et_card_site)
    EditText etBankName;
    @BindView(R.id.et_card_brand_sub)
    EditText etBankName2;
    @BindView(R.id.et_name)
    EditText etAccount;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;

    private String allKuCun;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_withdraw;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        tvTitle.setText("提现");
        presenter.getHomeDataBean(MyFrameApplication.tokenStr);
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }


    @OnClick({R.id.btn_reset_btn, R.id.iv_back, R.id.tv_back_title, R.id.tv_all_quota})
    public void onClick(View view) {
        if (AntiShake.check(view.getId())) {    //判断是否多次点击
            return;
        }
        switch (view.getId()) {
            case R.id.btn_reset_btn:
                Cash(MyFrameApplication.tokenStr, etQuota.getText().toString(), etBank.getText().toString(),
                        etBankName.getText().toString(), etBankName2.getText().toString(), etAccount.getText().toString(),
                        etCardNumber.getText().toString(), etPhone.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.iv_back:
            case R.id.tv_back_title:
                getActivity().finish();
                break;
            case R.id.tv_all_quota:
                if ("0".equals(allKuCun)) {
                    ToastUtils.showShortToast(getContext(),"亲，木有库存话费");
                    return;
                }
                etQuota.setText(allKuCun);
                break;
            default:
                break;
        }
    }

    private void Cash(String api_token, String quota, String bank, String bankname, String bankname2, String account, String cardnumber, String phone, String password) {
        if (TextUtils.isEmpty(api_token)) {
            ToastUtils.showShortToast(getContext(), "token无效!(查看是否登录或注册)");
            return;
        }
        if (TextUtils.isEmpty(quota)) {
            ToastUtils.showShortToast(getContext(), "提现金额不能为空！");
            return;
        }
        if (TextUtils.isEmpty(bank)) {
            ToastUtils.showShortToast(getContext(), "总行名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(bankname)) {
            ToastUtils.showShortToast(getContext(), "开户行不能为空！");
            return;
        }
        if (TextUtils.isEmpty(bankname2)) {
            ToastUtils.showShortToast(getContext(), "支行名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(account)) {
            ToastUtils.showShortToast(getContext(), "姓名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(cardnumber)) {
            ToastUtils.showShortToast(getContext(), "卡号不能为空！");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShortToast(getContext(), "电话不能为空！");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShortToast(getContext(), "密码不能为空！");
            return;
        }
        presenter.getCash(api_token, quota, bank, bankname, bankname2, account, cardnumber, phone, password);
        LoadingDialog.showDialog(getChildFragmentManager());
    }

    @Override
    public void setCashSucceed(WithdrawBean withdrawBean) {
        LoadingDialog.dismissDailog();
        //弹窗
        ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance();
        confirmDialogFragment.setMsg("提现发起成功");
        confirmDialogFragment.setContent("请耐心等待！资金一般会在24小时\n内到账，请勿重复发起!");
        confirmDialogFragment.setConfirmText("确认");
        confirmDialogFragment.setCancelVisible(false);
        confirmDialogFragment.setCloseVisible(false);
        confirmDialogFragment.setListenCancel(false);
        confirmDialogFragment.setDialogCallBack(new DialogCallBack() {
            @Override
            public void onDialogViewClick(int type, Object value) {
            }
        });
        confirmDialogFragment.show(this.getFragmentManager(), "confirmDialogFragment");

    }

    @Override
    public void setCashFailed() {
        LoadingDialog.dismissDailog();
        //弹窗
        ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance();
        confirmDialogFragment.setMsg("提现发起失败");
        confirmDialogFragment.setContent("请检查网络并核对好填写信息后\n重新发起!");
        confirmDialogFragment.setConfirmText("确认");
        confirmDialogFragment.setCancelVisible(false);
        confirmDialogFragment.setCloseVisible(false);
        confirmDialogFragment.setListenCancel(false);
        confirmDialogFragment.setDialogCallBack(new DialogCallBack() {
            @Override
            public void onDialogViewClick(int type, Object value) {
            }
        });
        confirmDialogFragment.show(this.getFragmentManager(), "confirmDialogFragment");
    }

    @Override
    public void setHomeDataBean(UserBean userBean) {
        tvAllCost.setText(StringUtils.fenToYuan(userBean.getDeposit()));
        allKuCun = StringUtils.fenToYuan(userBean.getDeposit());
    }
}
