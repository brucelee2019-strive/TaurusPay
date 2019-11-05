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
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.login.constract.LoginConstract;
import com.mchaw.tauruspay.ui.login.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : Bruce Lee
 * @date : 2019/11/4 0004 21:30
 * @description :
 */
public class LoginFragment extends BasePresentFragment<LoginPresenter> implements LoginConstract.View {

    @BindView(R.id.test)
    TextView test;
    @BindView(R.id.et_account)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPasswd;
    @BindView(R.id.et_auth_code)
    EditText etAuthCode;
    @BindView(R.id.tv_login_btn)
    TextView tvLoginBtn;

    private String username,code,passwd;

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
        //presenter.getLoginBean("wuhuaxiang","1234","123457");
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    public void setLoginBean(LoginBean loginBean) {
        test.setText(loginBean.getName());
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_login_btn)
    public void onClick(View view) {
        login(etUserName.getText().toString(),etAuthCode.getText().toString(),etPasswd.getText().toString());
    }

    private void login(String username,String code,String passwd){
        if(TextUtils.isEmpty(username)){
            ToastUtils.showShortToast(getContext(),"用户名不能为空！");
            return;
        }
        if(TextUtils.isEmpty(passwd)){
            ToastUtils.showShortToast(getContext(),"密码不能为空！");
            return;
        }
        if(TextUtils.isEmpty(code)){
            ToastUtils.showShortToast(getContext(),"验证码不能为空！");
            return;
        }
        presenter.getLoginBean(username,code,passwd);
    }
}
