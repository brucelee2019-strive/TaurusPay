package com.mchaw.tauruspay.di.component;

import com.mchaw.tauruspay.base.activity.BaseActivity;
import com.mchaw.tauruspay.main.MainActivity;
import com.mchaw.tauruspay.di.ActivityScoped;
import com.mchaw.tauruspay.ui.SplashActivity;
import com.mchaw.tauruspay.ui.login.LoginFragment;
import com.mchaw.tauruspay.ui.login.LoginFragmentForFirst;
import com.mchaw.tauruspay.ui.login.password.PasswordFragment;
import com.mchaw.tauruspay.ui.login.register.RegisterFragment;
import com.mchaw.tauruspay.ui.main.home.HomeFragment;
import com.mchaw.tauruspay.ui.main.home.forsale.CollectionListFragment;
import com.mchaw.tauruspay.ui.main.home.forsale.ForSaleFragment;
import com.mchaw.tauruspay.ui.main.home.forsale.ForSaleListFragment;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.TransferAccountsFragment;
import com.mchaw.tauruspay.ui.main.mine.MineFragment;
import com.mchaw.tauruspay.ui.main.mine.activate.ActivateCodeFragment;
import com.mchaw.tauruspay.ui.main.mine.bill.BillFragment;
import com.mchaw.tauruspay.ui.main.mine.notice.NoticeDetailFragment;
import com.mchaw.tauruspay.ui.main.mine.notice.NoticeFragment;
import com.mchaw.tauruspay.ui.main.mine.qrcode.QRCodeFragment;
import com.mchaw.tauruspay.ui.main.recharge.RechargeFragment;
import com.mchaw.tauruspay.ui.main.recharge.RechargeNextFragment;
import com.mchaw.tauruspay.ui.main.recharge.record.RecordFailedFragment;
import com.mchaw.tauruspay.ui.main.recharge.record.RecordSucceedFragment;

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

    void inject(SplashActivity splashActivity);

    void inject(LoginFragment loginFragment);

    void inject(RegisterFragment registerFragment);

    void inject(PasswordFragment passwordFragment);

    void inject(TransferAccountsFragment transferAccountsFragment);

    void inject(ForSaleFragment forSaleFragment);

    void inject(RechargeNextFragment rechargeNextFragment);

    void inject(RechargeFragment rechargeFragment);

    void inject(HomeFragment homeFragment);

    void inject(QRCodeFragment qrCodeFragment);

    void inject(ForSaleListFragment forSaleListFragment);

    void inject(CollectionListFragment collectionListFragment);

    void inject(ActivateCodeFragment activateCodeFragment);

    void inject(BillFragment billFragment);

    void inject(MineFragment mineFragment);

    void inject(RecordFailedFragment recordFailedFragment);

    void inject(RecordSucceedFragment recordSucceedFragment);

    void inject(NoticeFragment noticeFragment);

    void inject(NoticeDetailFragment noticeDetailFragment);

    void inject(LoginFragmentForFirst loginFragmentForFirst);
}
