package com.mchaw.tauruspay.ui.main.mine.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.BaseDialogFragment;
import com.mchaw.tauruspay.common.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/13 15:08
 * @description:
 */
public class QRCodeGroupDialog extends BaseDialogFragment {
    @BindView(R.id.et_income_account)
    EditText etIncomeAccount;
    @BindView(R.id.et_income_nick)
    EditText etIncomeNick;

    public static void showDialog(FragmentManager manager) {
        QRCodeGroupDialog qrCodeGroupDialog = new QRCodeGroupDialog();
        qrCodeGroupDialog.show(manager, null);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.dialog_qrcode_group;
    }

    @Override
    protected void initDialogFragment(View view) {

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
                if(TextUtils.isEmpty(etIncomeAccount.getText().toString())){
                    ToastUtils.showShortToast(getContext(),"账号不能为空！");
                    return;
                }
                if(TextUtils.isEmpty(etIncomeNick.getText().toString())){
                    ToastUtils.showShortToast(getContext(),"昵称不能为空！");
                    return;
                }
                if (dialog != null) {
                    dialog.dismiss();
                }
                QRCodeGroupDialog.ConfirmListener confirmListener = (QRCodeGroupDialog.ConfirmListener) getParentFragment();
                confirmListener.onClickComplete("1",etIncomeAccount.getText().toString(),etIncomeNick.getText().toString());
                break;
            default:
                break;
        }
    }

    public interface ConfirmListener {
        void onClickComplete(String code ,String account,String nick);
    }
}
