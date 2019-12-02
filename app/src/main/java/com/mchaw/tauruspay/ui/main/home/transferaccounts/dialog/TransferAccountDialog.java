package com.mchaw.tauruspay.ui.main.home.transferaccounts.dialog;

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
import com.mchaw.tauruspay.ui.main.mine.dialog.ChangeBankCardDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/12/2 11:23
 * @description:
 */
public class TransferAccountDialog extends BaseDialogFragment {
    @BindView(R.id.tv_context)
    TextView tvContext;

    public static void showDialog(FragmentManager manager) {
        TransferAccountDialog transferAccountDialog = new TransferAccountDialog();
        transferAccountDialog.show(manager, null);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.dialog_base;
    }

    @Override
    protected void initDialogFragment(View view) {
        tvContext.setText("转账后，对应金额将直接进入对方账号！");
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
                TransferAccountDialog.ConfirmListener confirmListener = (TransferAccountDialog.ConfirmListener) getParentFragment();
                confirmListener.onClickComplete();
                break;
            default:
                break;
        }
    }

    public interface ConfirmListener {
        void onClickComplete();
    }
}
