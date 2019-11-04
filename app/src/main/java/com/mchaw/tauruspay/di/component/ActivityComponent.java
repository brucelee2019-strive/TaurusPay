package com.mchaw.tauruspay.di.component;

import com.mchaw.tauruspay.base.activity.BaseActivity;
import com.mchaw.tauruspay.MainActivity;
import com.mchaw.tauruspay.di.ActivityScoped;
import com.mchaw.tauruspay.ui.fragment.ScoreFragment;
import com.mchaw.tauruspay.ui.login.LoginFragment;

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

    void inject(MainActivity activity);

    void inject(BaseActivity activity);

    void inject(ScoreFragment scoreFragment);

    void inject(LoginFragment loginFragment);
}
