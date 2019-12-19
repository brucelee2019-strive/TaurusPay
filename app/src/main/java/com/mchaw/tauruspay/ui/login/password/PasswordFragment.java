package com.mchaw.tauruspay.ui.login.password;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.login.PasswordBean;
import com.mchaw.tauruspay.common.dialog.LoadingDialog;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.login.password.constract.PasswordConstract;
import com.mchaw.tauruspay.ui.login.password.presenter.PasswordPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/6 10:23
 * @description:
 */
public class PasswordFragment extends BasePresentFragment<PasswordPresenter> implements PasswordConstract.View {

    @BindView(R.id.tv_back_title)
    TextView tvTitle;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_passwd)
    EditText etPasswd;
    @BindView(R.id.et_passwd_sure)
    EditText etPasswdSure;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_password;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        tvTitle.setText("修改密码");
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    public void setPasswordBean(PasswordBean passwordBean) {
        LoadingDialog.dismissDailog();
        if (passwordBean == null) {
            ToastUtils.showShortToast(getContext(), "密码重置失败！");
            return;
        }
        ToastUtils.showShortToast(getContext(), "密码重置成功！");
        getActivity().finish();
    }

    @Override
    public void setPasswordFail() {

    }


    @OnClick({R.id.btn_reset_btn, R.id.iv_back, R.id.tv_back_title})
    public void onClick(View view) {
        if (AntiShake.check(view.getId())) {    //判断是否多次点击
            return;
        }
        switch (view.getId()) {
            case R.id.btn_reset_btn:
                resetPasswd(PreferencesUtils.getString(getContext(), "token"), "1234", etPasswd.getText().toString(), etPasswdSure.getText().toString());
                break;
            case R.id.iv_back:
            case R.id.tv_back_title:
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    private void resetPasswd(String token, String code, String passwd, String passwdConfirmation) {
        if (TextUtils.isEmpty(token)) {
            ToastUtils.showShortToast(getContext(), "token无效!(查看是否登录或注册)");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShortToast(getContext(), "验证码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(passwd)) {
            ToastUtils.showShortToast(getContext(), "登录密码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(passwdConfirmation)) {
            ToastUtils.showShortToast(getContext(), "确认密码不能为空！");
            return;
        }
        if (!passwd.equals(passwdConfirmation)) {
            ToastUtils.showShortToast(getContext(), "密码与确认密码必须一致");
            return;
        }
        presenter.getPasswordBean(token, code, passwd, passwdConfirmation);
        LoadingDialog.showDialog(getChildFragmentManager());
    }
}
