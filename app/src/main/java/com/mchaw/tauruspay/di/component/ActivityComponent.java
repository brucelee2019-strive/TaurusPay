package com.mchaw.tauruspay.di.component;

import com.mchaw.tauruspay.base.activity.BaseActivity;
import com.mchaw.tauruspay.MainActivity;
import com.mchaw.tauruspay.di.ActivityScoped;
import com.mchaw.tauruspay.ui.fragment.ScoreFragment;
import com.mchaw.tauruspay.ui.login.LoginActivity;
import com.mchaw.tauruspay.ui.login.LoginFragment;
import com.mchaw.tauruspay.ui.login.password.PasswordFragment;
import com.mchaw.tauruspay.ui.login.register.RegisterFragment;
import com.mchaw.tauruspay.ui.main.home.HomeFragment;
import com.mchaw.tauruspay.ui.main.home.forsale.ForSaleFragment;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.TransferAccountsFragment;
import com.mchaw.tauruspay.ui.main.recharge.RechargeFragment;
import com.mchaw.tauruspay.ui.main.recharge.RechargeNextFragment;

import dagger.Component;

/**
 * @author : Bruce Lee
 * @description :@dependencies,
 * 这里就把AppComponent中提供的一些对象依赖了过来，
 * 实现了全局共用。同时声明一个inject方法，
 * 参数是你要注入到的类（方法名词不限，这里用inject比较形象）。
 * @date : 2019/10/27 0027 21:37
 */
@ActivityScoped
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(BaseActivity baseActivity);

    void inject(LoginActivity loginActivity);

    void inject(ScoreFragment scoreFragment);

    void inject(LoginFragment loginFragment);

    void inject(RegisterFragment registerFragment);

    void inject(PasswordFragment passwordFragment);

    void inject(TransferAccountsFragment transferAccountsFragment);

    void inject(ForSaleFragment forSaleFragment);

    void inject(RechargeNextFragment rechargeNextFragment);

    void inject(RechargeFragment rechargeFragment);

    void inject(HomeFragment homeFragment);
}
