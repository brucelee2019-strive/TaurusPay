package com.mchaw.tauruspay.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.listener.OnButtonClickListener;
import com.azhon.appupdate.listener.OnDownloadListener;
import com.azhon.appupdate.manager.DownloadManager;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.activity.BasePresenterActivity;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.base.fragment.helper.FragmentStartHelper;
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
import com.mchaw.tauruspay.ui.main.mine.dialog.NotifyDialog;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/12/18 15:18
 * @description:
 */
public class LoginActivityForFirst extends BasePresenterActivity<LoginPresenter> implements LoginConstract.View, OnDownloadListener, View.OnClickListener, OnButtonClickListener {

    @BindView(R.id.et_account)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPasswd;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    private DownloadManager manager;
    private String versionName;
    private int versionCode;
    private String description;
    private String apkSize;
    private String download;
    private int qzgx;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initActivity() {
        super.initActivity();
        if (!notificationListenerEnable()) {
            NotifyDialog.showDialog(getSupportFragmentManager());
        }
        tvVersion.setText("版本:v"+ versionUtils.getAppVersionName(this));
        presenter.getVersion();
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
        MyFrameApplication.tokenStr = loginBean.getToken();
        MyFrameApplication.userType = loginBean.getType();
        PreferencesUtils.putString(this,"token",loginBean.getToken());
        PreferencesUtils.putString(this,"name",loginBean.getName());
        PreferencesUtils.putString(this,"payname",loginBean.getPayname());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void setLoginOutBean(LoginOutBean loginOutBean) {

    }

    @Override
    public void setLoginFail() {
        LoadingDialog.dismissDailog();
        //ToastUtils.showShortToast(getContext(),"未知错误！");
    }

    @Override
    public void setVersion(UpDataBean upDataBean) {
        if (upDataBean == null) {
            return;
        }
        versionName = upDataBean.getVersionName();
        versionCode = upDataBean.getVersionCode();
        description = upDataBean.getDescription();
        apkSize = upDataBean.getApkSize();
        download = upDataBean.getDownload();
        qzgx = upDataBean.getType();
        if (versionUtils.getAppVersionCode(this) < versionCode) {
            startUpdate(versionCode, versionName, apkSize, description, download, qzgx);
        }
    }

    @OnClick({R.id.btn_login_btn, R.id.tv_register, R.id.tv_find_password})
    public void onClick(View view) {
        if (!notificationListenerEnable()) {
            NotifyDialog.showDialog(getSupportFragmentManager());
            return;
        }
        if (AntiShake.check(view.getId())) {    //判断是否多次点击
            return;
        }
        switch (view.getId()) {
            case R.id.btn_login_btn:
                login(etUserName.getText().toString(), "1234", etPasswd.getText().toString());
                break;
            case R.id.tv_register:
                FragmentStartHelper.startFragment(this,new RegisterFragment());
                break;
            case R.id.tv_find_password:
                //startFragment(new PasswordFragment());
                ToastUtils.showShortToast(this,"请联系客服！");
                break;
            default:
                break;
        }
    }

    private void login(String username, String code, String passwd) {
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShortToast(this, "用户名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(passwd)) {
            ToastUtils.showShortToast(this, "密码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShortToast(this, "验证码不能为空！");
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

    private void startUpdate(int versionCode, String versionName, String apkSize, String description, String download, int qzgx) {
        /*
         * 整个库允许配置的内容
         * 非必选
         */
        UpdateConfiguration configuration = new UpdateConfiguration()
                //输出错误日志
                .setEnableLog(true)
                //设置自定义的下载
                //.setHttpManager()
                //下载完成自动跳动安装页面
                .setJumpInstallPage(true)
                //设置对话框背景图片 (图片规范参照demo中的示例图)
                .setDialogImage(R.drawable.ic_dialog)
                //设置按钮的颜色
                .setDialogButtonColor(Color.parseColor("#FF9600"))
                //设置对话框强制更新时进度条和文字的颜色
                .setDialogProgressBarColor(Color.parseColor("#FF9600"))
                //设置按钮的文字颜色
                .setDialogButtonTextColor(Color.WHITE)
                //设置是否显示通知栏进度
                .setShowNotification(true)
                //设置是否提示后台下载toast
                .setShowBgdToast(true)
                //设置强制更新
                .setForcedUpgrade(qzgx == 1 ? true : false);

        manager = DownloadManager.getInstance(this);
        manager.setApkName("jnhf025.apk")
                .setApkUrl(download)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setShowNewerToast(true)
                .setConfiguration(configuration)
                .setApkVersionCode(versionCode)
                .setApkVersionName(versionName)
                .setApkSize(apkSize)
                .setAuthorities(this.getPackageName())
                .setApkDescription(description)
                .download();
    }


    @Override
    public void onButtonClick(int id) {

    }

    @Override
    public void start() {

    }

    @Override
    public void downloading(int max, int progress) {

    }

    @Override
    public void done(File apk) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void error(Exception e) {

    }

    private boolean notificationListenerEnable() {
        boolean enable = false;
        String packageName = this.getPackageName();
        String flat= Settings.Secure.getString(this.getContentResolver(),"enabled_notification_listeners");
        if (flat != null) {
            enable= flat.contains(packageName);
        }
        return enable;
    }
}
