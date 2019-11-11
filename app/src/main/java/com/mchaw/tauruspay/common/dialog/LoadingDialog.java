package com.mchaw.tauruspay.common.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.FragmentManager;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.BaseDialogFragment;
import com.mchaw.tauruspay.ui.main.mine.dialog.ChangeBankCardDialog;

/**
 * @author Bruce Lee
 * @date : 2019/11/11 15:15
 * @description:
 */
public class LoadingDialog extends BaseDialogFragment {
    private static LoadingDialog loadingDialog;

    public static void showDialog(FragmentManager manager) {
        loadingDialog = new LoadingDialog();
        loadingDialog.show(manager, null);
    }

    public static void dismissDailog(){
        loadingDialog.dismiss();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void initDialogFragment(View view) {
        if (dialog != null) {
            dialog.dismiss();
        }
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

}
