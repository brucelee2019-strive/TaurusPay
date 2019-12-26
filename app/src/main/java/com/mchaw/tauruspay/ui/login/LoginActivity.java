package com.mchaw.tauruspay.ui.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.activity.BasePresenterActivity;
import com.mchaw.tauruspay.base.fragment.helper.FragmentStartHelper;
import com.mchaw.tauruspay.bean.eventbus.LoginSucceedEvent;
import com.mchaw.tauruspay.bean.login.LoginBean;
import com.mchaw.tauruspay.bean.login.LoginOutBean;
import com.mchaw.tauruspay.bean.updata.UpDataBean;
import com.mchaw.tauruspay.common.dialog.LoadingDialog;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.common.util.versionUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.main.MainActivity;
import com.mchaw.tauruspay.ui.login.constract.LoginConstract;
import com.mchaw.tauruspay.ui.login.presenter.LoginPresenter;
import com.mchaw.tauruspay.ui.login.register.RegisterFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/12/20 14:49
 * @description:
 */
public class LoginActivity extends BasePresenterActivity<LoginPresenter> implements LoginConstract.View{
    @BindView(R.id.et_account)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPasswd;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initActivity() {
        super.initActivity();
        tvVersion.setText("版本:v"+ versionUtils.getAppVersionName(LoginActivity.this));
    }

    @Override
    public void injectActivityComponent(ActivityComponent component) {
        super.injectActivityComponent(component);
        component.inject(this);
    }

    @Override
    public void setLoginBean(LoginBean loginBean) {
        LoadingDialog.dismissDailog();
        if(loginBean == null){
            return;
        }
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        MyFrameApplication.tokenStr = loginBean.getToken();
        PreferencesUtils.putString(LoginActivity.this,"token",loginBean.getToken());
        PreferencesUtils.putString(LoginActivity.this,"name",loginBean.getName());
        PreferencesUtils.putString(LoginActivity.this,"payname",loginBean.getPayname());
        EventBus.getDefault().post(new LoginSucceedEvent());
        LoginActivity.this.finish();
    }

    @Override
    public void setLoginOutBean(LoginOutBean loginOutBean) {

    }

    @Override
    public void setLoginFail() {
        LoadingDialog.dismissDailog();
    }

    @Override
    public void setVersion(UpDataBean upDataBean) {

    }
    @OnClick({R.id.btn_login_btn, R.id.tv_register, R.id.tv_find_password})
    public void onClick(View view) {
        if (AntiShake.check(view.getId())) {    //判断是否多次点击
            return;
        }
        switch (view.getId()) {
            case R.id.btn_login_btn:
                login(etUserName.getText().toString(), "1234", etPasswd.getText().toString());
                break;
            case R.id.tv_register:
                FragmentStartHelper.startFragment(LoginActivity.this,new RegisterFragment());
                break;
            case R.id.tv_find_password:
                //startFragment(new PasswordFragment());
                ToastUtils.showShortToast(LoginActivity.this,"请联系客服！");
                break;
            default:
                break;
        }
    }

    private void login(String username, String code, String passwd) {
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShortToast(LoginActivity.this, "用户名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(passwd)) {
            ToastUtils.showShortToast(LoginActivity.this, "密码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShortToast(LoginActivity.this, "验证码不能为空！");
            return;
        }
        presenter.getLoginBean(username, code, passwd,MyFrameApplication.pIp);
        LoadingDialog.showDialog(getSupportFragmentManager());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }
}
