package com.mchaw.tauruspay.ui.login.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.login.RegisterBean;
import com.mchaw.tauruspay.common.dialog.LoadingDialog;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.login.register.constract.RegisterConstract;
import com.mchaw.tauruspay.ui.login.register.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/5 14:53
 * @description:
 */
public class RegisterFragment extends BasePresentFragment<RegisterPresenter> implements RegisterConstract.View {

    @BindView(R.id.tv_back_title)
    TextView tvTitle;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_passwd)
    EditText etPasswd;
    @BindView(R.id.et_passwd_sure)
    EditText etPasswdSure;
    @BindView(R.id.et_real_name)
    EditText etRealName;
    @BindView(R.id.et_active_code)
    EditText etActiveCode;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_register;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        tvTitle.setText("注册");
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    public void setRegisterBean(RegisterBean registerBean) {
        LoadingDialog.dismissDailog();
        if (registerBean == null) {
            ToastUtils.showShortToast(getContext(), "异常,注册失败！请重新尝试");
            return;
        }
        if (!TextUtils.isEmpty(registerBean.getTime())) {
            ToastUtils.showShortToast(getContext(), "注册成功！");
            getActivity().finish();
        } else {
            ToastUtils.showShortToast(getContext(), "异常,注册失败！请重新尝试");
        }
    }

    @Override
    public void setRegisterFail() {
        LoadingDialog.dismissDailog();
    }

    @OnClick({R.id.btn_register_btn, R.id.iv_back, R.id.tv_back_title})
    public void onClick(View view) {
        if (AntiShake.check(view.getId())) {    //判断是否多次点击
            return;
        }
        switch (view.getId()) {
            case R.id.btn_register_btn:
                register(etAccount.getText().toString(), etPhoneNumber.getText().toString(), "1234",
                        etPasswd.getText().toString(), etPasswdSure.getText().toString(), etRealName.getText().toString(), etActiveCode.getText().toString());
                break;
            case R.id.iv_back:
            case R.id.tv_back_title:
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    private void register(String account, String phoneNumber, String authCode, String passwd, String passwdSure, String realName, String activeCode) {
        if (TextUtils.isEmpty(account)) {
            ToastUtils.showShortToast(getContext(), "用户账号不能为空！");
            return;
        }
        if (TextUtils.isEmpty(phoneNumber)) {
            ToastUtils.showShortToast(getContext(), "手机号不能为空！");
            return;
        }
        if (TextUtils.isEmpty(authCode)) {
            ToastUtils.showShortToast(getContext(), "验证码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(passwd)) {
            ToastUtils.showShortToast(getContext(), "登录密码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(passwdSure)) {
            ToastUtils.showShortToast(getContext(), "确认密码不能为空！");
            return;
        }
        if (!passwd.equals(passwdSure)) {
            ToastUtils.showShortToast(getContext(), "密码与确认密码必须一致");
            return;
        }
        if (TextUtils.isEmpty(realName)) {
            ToastUtils.showShortToast(getContext(), "付款人姓名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(activeCode)) {
            ToastUtils.showShortToast(getContext(), "激活口令不能为空！");
            return;
        }
        presenter.getRegisterBean(account, phoneNumber, authCode, passwd, passwdSure, realName, activeCode);
        LoadingDialog.showDialog(getChildFragmentManager());
    }
}
