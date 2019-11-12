package com.mchaw.tauruspay.ui.main.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.MainActivity;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.login.LoginActivity;
import com.mchaw.tauruspay.ui.login.password.PasswordFragment;
import com.mchaw.tauruspay.ui.main.mine.about.AboutFragment;
import com.mchaw.tauruspay.ui.main.mine.bank.MyBankCardFragment;
import com.mchaw.tauruspay.ui.main.mine.qrcode.QRCodeFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : Bruce Lee
 * @date : 2019/11/3 0003 21:11
 * @description :
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.tv_user_nickname)
    TextView tvUserNickname;
    @BindView(R.id.tv_user_anme)
    TextView tvPayName;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        tvUserNickname.setText(PreferencesUtils.getString(getContext(),"name"));
        tvPayName.setText(PreferencesUtils.getString(getContext(),"payname"));
    }

    @OnClick({R.id.tv_login_out,R.id.cl_bill,R.id.cl_bank_set,R.id.cl_qr_code,R.id.cl_activate_word,R.id.cl_change_password,R.id.cl_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_out:
//                PreferencesUtils.removeKey(getContext(),"token");
//                PreferencesUtils.removeKey(getContext(),"name");
//                PreferencesUtils.removeKey(getContext(),"payname");
                PreferencesUtils.putString(getContext(),"token","");
                PreferencesUtils.putString(getContext(),"name","");
                PreferencesUtils.putString(getContext(),"payname","");
                PreferencesUtils.putString(getContext(),"sellamount","");
                PreferencesUtils.putString(getContext(),"sellcount","");
                PreferencesUtils.putString(getContext(),"point","");
                PreferencesUtils.putString(getContext(),"deposit","");
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                ToastUtils.showShortToast(getContext(),"退出成功！");
                break;
            case R.id.cl_bill:
                break;
            case R.id.cl_bank_set:
                startFragment(new MyBankCardFragment());
                break;
            case R.id.cl_qr_code:
                startFragment(new QRCodeFragment());
                break;
            case R.id.cl_activate_word:
                ToastUtils.showShortToast(getContext(),"暂未开启");
                break;
            case R.id.cl_change_password:
                startFragment(new PasswordFragment());
                break;
            case R.id.cl_about:
                startFragment(new AboutFragment());
                break;
        }
    }
}
