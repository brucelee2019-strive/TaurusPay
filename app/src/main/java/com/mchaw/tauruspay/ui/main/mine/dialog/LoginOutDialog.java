package com.mchaw.tauruspay.ui.main.mine.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.BaseDialogFragment;
import com.mchaw.tauruspay.bean.eventbus.LoginoutEvent;
import com.mchaw.tauruspay.common.util.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/19 17:18
 * @description:
 */
public class LoginOutDialog extends BaseDialogFragment {
    @BindView(R.id.tv_context)
    TextView tvContext;

    public static void showDialog(FragmentManager manager) {
        LoginOutDialog loginOutDialog = new LoginOutDialog();
        loginOutDialog.show(manager, null);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.dialog_base;
    }

    @Override
    protected void initDialogFragment(View view) {
        tvContext.setText("确定要退出么？");
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);

            Window window = dialog.getWindow();
            //window.setWindowAnimations(R.style.SignDialogAnim);
            WindowManager.LayoutParams wl = window.getAttributes();
            window.setAttributes(wl);
            dialog.setCanceledOnTouchOutside(true);
        }
    }

    @OnClick({R.id.tv_cancel, R.id.tv_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                if (dialog != null) {
                    dialog.dismiss();
                }
                break;
            case R.id.tv_sure:
                if (dialog != null) {
                    dialog.dismiss();
                }
                PreferencesUtils.putString(getContext(),"token","");
                PreferencesUtils.putString(getContext(),"name","");
                PreferencesUtils.putString(getContext(),"payname","");
                PreferencesUtils.putString(getContext(),"sellamount","");
                PreferencesUtils.putString(getContext(),"sellcount","");
                PreferencesUtils.putString(getContext(),"point","");
                PreferencesUtils.putString(getContext(),"deposit","");
                LoginOutDialog.ConfirmListener confirmListener = (LoginOutDialog.ConfirmListener) getParentFragment();
                confirmListener.onClickComplete();
                EventBus.getDefault().post(new LoginoutEvent());
                break;
            default:
                break;
        }
    }


    public interface ConfirmListener {
        void onClickComplete();
    }
}
