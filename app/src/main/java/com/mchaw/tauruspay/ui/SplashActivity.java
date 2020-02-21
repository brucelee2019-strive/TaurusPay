package com.mchaw.tauruspay.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.base.activity.BasePresenterActivity;
import com.mchaw.tauruspay.base.fragment.helper.FragmentStartHelper;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.updata.UpDataBean;
import com.mchaw.tauruspay.main.MainActivity;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.login.LoginActivityForFirst;
import com.mchaw.tauruspay.ui.main.home.constract.HomeConstract;
import com.mchaw.tauruspay.ui.main.home.presenter.HomePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Bruce Lee
 * @date : 2019/11/19 16:38
 * @description:
 */
public class SplashActivity extends BasePresenterActivity<HomePresenter> implements HomeConstract.View {
    private Handler handler;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void initActivity() {
        super.initActivity();
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    MyFrameApplication.pIp = (String) msg.obj;
                }
            }
        };
        if (!TextUtils.isEmpty(MyFrameApplication.tokenStr)) {
            presenter.getHomeDataBean(MyFrameApplication.tokenStr);
        }
        GetNetIp();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (TextUtils.isEmpty(MyFrameApplication.tokenStr)) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivityForFirst.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
            }
        }, 1000);
    }

    public void GetNetIp() {
        new Thread() {
            @Override
            public void run() {
                String line = "";
                URL infoUrl = null;
                InputStream inStream = null;
                try {
                    infoUrl = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
                    URLConnection connection = infoUrl.openConnection();
                    HttpURLConnection httpConnection = (HttpURLConnection) connection;
                    int responseCode = httpConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        inStream = httpConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                        StringBuilder strber = new StringBuilder();
                        while ((line = reader.readLine()) != null)
                            strber.append(line + "\n");
                        inStream.close();
                        // 从反馈的结果中提取出IP地址
                        int start = strber.indexOf("{");
                        int end = strber.indexOf("}");
                        String json = strber.substring(start, end + 1);
                        if (json != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                line = jsonObject.optString("cip");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = line;
                        //向主线程发送消息
                        handler.sendMessage(msg);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void injectActivityComponent(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    public void setHomeDataBean(UserBean userBean) {
        MyFrameApplication.groupid = userBean.getGroupid();
        MyFrameApplication.userType = userBean.getType();
        MyFrameApplication.userInviteCode = userBean.getCode();
        MyFrameApplication.userRate = userBean.getRate();
    }

    @Override
    public void setVersion(UpDataBean upDataBean) {

    }
}
