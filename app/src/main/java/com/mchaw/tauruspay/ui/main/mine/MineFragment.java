package com.mchaw.tauruspay.ui.main.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.ui.login.LoginFragment;
import com.mchaw.tauruspay.ui.login.password.PasswordFragment;
import com.mchaw.tauruspay.ui.main.mine.about.AboutFragment;
import com.mchaw.tauruspay.ui.main.mine.activate.ActivateCodeFragment;
import com.mchaw.tauruspay.ui.main.mine.bank.MyBankCardFragment;
import com.mchaw.tauruspay.ui.main.mine.bill.BillFragment;
import com.mchaw.tauruspay.ui.main.mine.dialog.LoginOutDialog;
import com.mchaw.tauruspay.ui.main.mine.qrcode.QRCodeFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : Bruce Lee
 * @date : 2019/11/3 0003 21:11
 * @description :
 */
public class MineFragment extends BaseFragment implements LoginOutDialog.ConfirmListener{

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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){

        }else{
            tvUserNickname.setText(PreferencesUtils.getString(getContext(),"name"));
            tvPayName.setText(PreferencesUtils.getString(getContext(),"payname"));
        }
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
                LoginOutDialog.showDialog(getChildFragmentManager());
                break;
            case R.id.cl_bill:
                startFragment(new BillFragment());
                break;
            case R.id.cl_bank_set:
                startFragment(new MyBankCardFragment());
                break;
            case R.id.cl_qr_code:
                startFragment(new QRCodeFragment());
                break;
            case R.id.cl_activate_word:
                startFragment(new ActivateCodeFragment());
                break;
            case R.id.cl_change_password:
                startFragment(new PasswordFragment());
                break;
            case R.id.cl_about:
                startFragment(new AboutFragment());
                break;
        }
    }

    @Override
    public void onClickComplete() {
        startFragment(new LoginFragment());
        ToastUtils.showShortToast(getContext(),"退出成功！");
    }
}
