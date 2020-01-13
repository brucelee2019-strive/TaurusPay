package com.mchaw.tauruspay.ui.main.mine.dialog;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.BaseDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 17:20
 * @description:
 */
public class NotifyDialog extends BaseDialogFragment {
    @BindView(R.id.tv_context)
    TextView tvContext;

    public static void showDialog(FragmentManager manager) {
        NotifyDialog notifyDialog = new NotifyDialog();
        notifyDialog.show(manager, null);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.dialog_base;
    }

    @Override
    protected void initDialogFragment(View view) {
        tvContext.setText("请打开金牛话费通知使用权");
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
                    gotoNotificationAccessSetting(getContext());
                    dialog.dismiss();
                }
                break;
            default:
                break;
        }
    }
    private boolean gotoNotificationAccessSetting(Context context) {
        try {
            Intent intent;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            } else {
                intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        } catch(ActivityNotFoundException e) {
            try {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ComponentName cn = new ComponentName("com.android.settings","com.android.settings.Settings$NotificationAccessSettingsActivity");
                intent.setComponent(cn);
                intent.putExtra(":settings:show_fragment", "NotificationAccessSettings");
                context.startActivity(intent);
                return true;
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }
}
