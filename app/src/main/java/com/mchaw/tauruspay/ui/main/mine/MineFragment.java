package com.mchaw.tauruspay.ui.main.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.DialogCallBack;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.eventbus.LoginoutEvent;
import com.mchaw.tauruspay.bean.eventbus.NoticeEvent;
import com.mchaw.tauruspay.bean.eventbus.NoticeSureEvent;
import com.mchaw.tauruspay.bean.eventbus.TradingBeanEvent;
import com.mchaw.tauruspay.bean.login.LoginBean;
import com.mchaw.tauruspay.bean.login.LoginOutBean;
import com.mchaw.tauruspay.bean.updata.UpDataBean;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.login.LoginFragment;
import com.mchaw.tauruspay.ui.login.constract.LoginConstract;
import com.mchaw.tauruspay.ui.login.password.PasswordFragment;
import com.mchaw.tauruspay.ui.login.presenter.LoginPresenter;
import com.mchaw.tauruspay.ui.main.home.forsale.dialog.ConfirmDialogFragment;
import com.mchaw.tauruspay.ui.main.mine.about.AboutFragment;
import com.mchaw.tauruspay.ui.main.mine.activate.ActivateCodeFragment;
import com.mchaw.tauruspay.ui.main.mine.bill.BillFragment;
import com.mchaw.tauruspay.ui.main.mine.notice.NoticeFragment;
import com.mchaw.tauruspay.ui.main.mine.qrcode.QRCodeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import static com.mchaw.tauruspay.base.dialog.BaseDialogFragment.DIALOG_CONFIRM;

/**
 * @author : Bruce Lee
 * @date : 2019/11/3 0003 21:11
 * @description :
 */
public class MineFragment extends BasePresentFragment<LoginPresenter> implements LoginConstract.View {

    @BindView(R.id.tv_user_nickname)
    TextView tvUserNickname;
    @BindView(R.id.tv_user_anme)
    TextView tvPayName;
    @BindView(R.id.tv_red_icon)
    TextView tvRedIcon;

    private QBadgeView qBadgeView;

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
        if (hidden) {

        } else {
            tvUserNickname.setText(PreferencesUtils.getString(getContext(), "name"));
            tvPayName.setText(PreferencesUtils.getString(getContext(), "payname"));
        }
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        tvUserNickname.setText(PreferencesUtils.getString(getContext(), "name"));
        tvPayName.setText(PreferencesUtils.getString(getContext(), "payname"));
        qBadgeView = new QBadgeView(getActivity());
        qBadgeView.setBadgeNumber(0)
                .setGravityOffset(0, 0, true)
                .bindTarget(tvRedIcon);
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @OnClick({R.id.tv_login_out, R.id.cl_bill, R.id.cl_qr_code, R.id.cl_activate_word, R.id.cl_change_password, R.id.cl_about, R.id.cl_notice})
    public void onClick(View view) {
        if (AntiShake.check(view.getId())) {    //判断是否多次点击
            return;
        }
        switch (view.getId()) {
            case R.id.tv_login_out:
                ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance();
                confirmDialogFragment.setMsg("提示");
                confirmDialogFragment.setContent("确定要退出么？");
                confirmDialogFragment.setCancelText("取消");
                confirmDialogFragment.setConfirmText("确认");
                confirmDialogFragment.setListenCancel(true);
                confirmDialogFragment.setDialogCallBack(new DialogCallBack() {
                    @Override
                    public void onDialogViewClick(int type, Object value) {
                        if (type == DIALOG_CONFIRM) {
                            presenter.getLoginOutBean(MyFrameApplication.tokenStr);
                        } else {

                        }
                    }
                });
                confirmDialogFragment.show(this.getFragmentManager(), "confirmDialogFragment");
                break;
            case R.id.cl_bill:
                startFragment(new BillFragment());
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
            case R.id.cl_notice:
                startFragment(new NoticeFragment());
                break;
            default:
                break;
        }
    }

    @Override
    public void setLoginBean(LoginBean loginBean) {

    }

    @Override
    public void setLoginOutBean(LoginOutBean loginOutBean) {
        MyFrameApplication.tokenStr = "";
        MyFrameApplication.groupid = 0;
        MyFrameApplication.startingPosition = -1;
        PreferencesUtils.putString(getContext(), "token", "");
        PreferencesUtils.putString(getContext(), "name", "");
        PreferencesUtils.putString(getContext(), "payname", "");
        EventBus.getDefault().post(new LoginoutEvent());
        startFragment(new LoginFragment());
        ToastUtils.showShortToast(getContext(), "退出成功！");
    }

    @Override
    public void setLoginFail() {

    }

    @Override
    public void setVersion(UpDataBean upDataBean) {

    }

    @Subscribe
    public void noticeNum(NoticeEvent noticeEvent) {
        if(noticeEvent == null){
            return;
        }
        qBadgeView.setBadgeNumber(noticeEvent.getNoticeNum());
    }

    @Subscribe
    public void setNoticeRedPoint(NoticeSureEvent noticeSureEvent) {
        if(noticeSureEvent == null){
            return;
        }
        qBadgeView.setBadgeNumber(noticeSureEvent.getNoticeNum());
    }
}
