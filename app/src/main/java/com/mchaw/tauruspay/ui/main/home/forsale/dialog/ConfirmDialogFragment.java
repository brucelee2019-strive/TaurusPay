package com.mchaw.tauruspay.ui.main.home.forsale.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.BaseDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 陈国权
 * @date 2017/6/13
 * @description 确认对话框
 */

public class ConfirmDialogFragment extends BaseDialogFragment {

    @BindView(R.id.tv_mgs)
    TextView tvMsg;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.iv_close)
    ImageView ivClose;

    private String msg;
    private String content;
    private String cancel;
    private String confirm;
    private int cancelColor = -1;
    private int confirmColor = -1;
    private boolean cancelVisible = true;
    private boolean confirmVisible = true;
    private boolean listenCancel = false;
    private boolean closeVisible = true;

    public static ConfirmDialogFragment newInstance() {
        return new ConfirmDialogFragment();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCancelText(String cancel) {
        this.cancel = cancel;
    }

    public void setCancelTextColor(int cancelColor) {
        this.cancelColor = cancelColor;
    }

    public void setCancelVisible(boolean cancelVisible) {
        this.cancelVisible = cancelVisible;
    }

    public void setConfirmText(String confirm) {
        this.confirm = confirm;
    }

    public void setConfirmTextColor(int confirmColor) {
        this.confirmColor = confirmColor;
    }

    public void setConfirmVisible(boolean confirmVisible) {
        this.confirmVisible = confirmVisible;
    }

    public void setListenCancel(boolean listenCancel) {
        this.listenCancel = listenCancel;
    }

    public void setCloseVisible(boolean closeVisible) {
        this.closeVisible = closeVisible;
    }

    @Override
    protected boolean showBottomAnim() {
        return false;
    }

    @Override
    protected boolean setCancelable() {
        return false;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.dialog_confirm;
    }

    @Override
    protected void initDialogFragment(View view) {
        if (!TextUtils.isEmpty(msg)) {
            tvMsg.setText(msg);
        }

        if (TextUtils.isEmpty(content)) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(content);
        }

        if (!TextUtils.isEmpty(cancel)) {
            btnCancel.setText(cancel);
        }
        if (cancelColor != -1) {
            btnCancel.setTextColor(cancelColor);
        }
        btnCancel.setVisibility(cancelVisible ? View.VISIBLE : View.GONE);

        if (!TextUtils.isEmpty(confirm)) {
            btnConfirm.setText(confirm);
        }
        if (confirmColor != -1) {
            btnConfirm.setTextColor(confirmColor);
        }
        btnConfirm.setVisibility(confirmVisible ? View.VISIBLE : View.GONE);
        ivClose.setVisibility(closeVisible ? View.VISIBLE : View.GONE);
    }

    @OnClick({R.id.btn_cancel, R.id.btn_confirm, R.id.iv_close})
    public void onViewClicked(View view) {
        dialog.dismiss();
        switch (view.getId()) {
            case R.id.btn_cancel:
                if (callBack != null && listenCancel) {
                    callBack.onDialogViewClick(DIALOG_CANCEL, null);
                }
                break;
            case R.id.btn_confirm:
                if (callBack != null) {
                    callBack.onDialogViewClick(DIALOG_CONFIRM, null);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            Window window = dialog.getWindow();
            window.setWindowAnimations(R.style.SignDialogAnim);
            WindowManager.LayoutParams wl = window.getAttributes();
            window.setAttributes(wl);
        }
    }
}
