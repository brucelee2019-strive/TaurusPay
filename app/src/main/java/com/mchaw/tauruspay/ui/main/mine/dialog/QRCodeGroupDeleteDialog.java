package com.mchaw.tauruspay.ui.main.mine.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.BaseDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/19 11:04
 * @description:
 */
public class QRCodeGroupDeleteDialog extends BaseDialogFragment {
    @BindView(R.id.tv_context)
    TextView tvContext;

    private static final String EXTRA_ACCOUNT = "account";
    private static final String EXTRA_NICK = "nick";
    private static final String EXTRA_GROUPID = "groupid";
    private static final String EXTRA_TYPE = "paytype";

    private String account,nick;
    private int groupId,payType;
    private String payTypeName;

    public static void showDialog(FragmentManager manager,String account,String nick,int groupId,int payType) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_ACCOUNT, account);
        bundle.putString(EXTRA_NICK, nick);
        bundle.putInt(EXTRA_GROUPID, groupId);
        bundle.putInt(EXTRA_TYPE, payType);
        QRCodeGroupDeleteDialog qrCodeGroupDeleteDialog = new QRCodeGroupDeleteDialog();
        qrCodeGroupDeleteDialog.setArguments(bundle);
        qrCodeGroupDeleteDialog.show(manager, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account = getArguments().getString(EXTRA_ACCOUNT);
        nick = getArguments().getString(EXTRA_NICK);
        groupId = getArguments().getInt(EXTRA_GROUPID);
        payType = getArguments().getInt(EXTRA_TYPE);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.dialog_base;
    }

    @Override
    protected void initDialogFragment(View view) {
        payTypeName = payType == 1?"支付宝":"微信";
        tvContext.setText("确定要删除：\n" +
                payTypeName+"账号为：" + account + "\n" +
                payTypeName+"昵称为：" + nick + "\n"+
                "的二维码库么？");
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
                QRCodeGroupDeleteDialog.ConfirmListener confirmListener = (QRCodeGroupDeleteDialog.ConfirmListener) getParentFragment();
                confirmListener.onClickComplete(groupId);
                break;
            default:
                break;
        }
    }


    public interface ConfirmListener {
        void onClickComplete(int id);
    }
}
