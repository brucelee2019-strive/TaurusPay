package com.mchaw.tauruspay.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.MainActivity;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.login.LoginBean;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.login.constract.LoginConstract;
import com.mchaw.tauruspay.ui.login.password.PasswordFragment;
import com.mchaw.tauruspay.ui.login.presenter.LoginPresenter;
import com.mchaw.tauruspay.ui.login.register.RegisterFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : Bruce Lee
 * @date : 2019/11/4 0004 21:30
 * @description :
 */
public class LoginFragment extends BasePresentFragment<LoginPresenter> implements LoginConstract.View {

    @BindView(R.id.et_account)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPasswd;
    @BindView(R.id.tv_login_btn)
    TextView tvLoginBtn;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);

    }

    @Override
    public void setLoginBean(LoginBean loginBean) {
        if(loginBean == null){
            return;
        }
        PreferencesUtils.putString(getContext(),"token",loginBean.getToken());
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick({R.id.tv_login_btn, R.id.tv_register, R.id.tv_find_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_btn:
                login(etUserName.getText().toString(), "1234", etPasswd.getText().toString());
                break;
            case R.id.tv_register:
                startFragment(new RegisterFragment());
                break;
            case R.id.tv_find_password:
                startFragment(new PasswordFragment());
                break;
            default:
                break;
        }
    }

    private void login(String username, String code, String passwd) {
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShortToast(getContext(), "用户名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(passwd)) {
            ToastUtils.showShortToast(getContext(), "密码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShortToast(getContext(), "验证码不能为空！");
            return;
        }
        presenter.getLoginBean(username, code, passwd);
    }
}
