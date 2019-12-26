package com.mchaw.tauruspay.base.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Bruce Lee
 * @date : 2019/11/8 16:16
 * @description:
 */
public abstract class BaseDialogFragment extends DialogFragment implements DialogInterface.OnKeyListener, DialogInterface.OnCancelListener{
    //确认
    public static final int DIALOG_CONFIRM = 5;
    //取消
    public static final int DIALOG_CANCEL = 6;

    protected Dialog dialog;

    protected DialogCallBack callBack;

    private Unbinder unbinder;

    private OnDismissListener mDismissListener;

    protected abstract int getContentViewId();

    protected abstract void initDialogFragment(View view);

    public void setDialogCallBack(DialogCallBack callBack){
        this.callBack = callBack;
    }

    protected boolean showBottomAnim(){
        return true;
    }

    protected  boolean setCancelable(){
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
       View view = inflater.inflate(getContentViewId(),container);
       unbinder = ButterKnife.bind(this,view);
       //DebouncingOnClickUtils.bind(unbinder);
        initDialogFragment(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog = getDialog();
        if(dialog != null){
            dialog.setOnKeyListener(this);
            dialog.setOnCancelListener(this);
            dialog.setOnCancelListener(this);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(setCancelable());
            if (showBottomAnim()) {
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wl = window.getAttributes();
                wl.x = 0;
                wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
                wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
                wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                dialog.onWindowAttributesChanged(wl);
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dialog = null;
        unbinder.unbind();
        if (mDismissListener != null) {
            mDismissListener.onDismiss();
        }
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && dialog != null) {
            dialog.dismiss();
            if (mDismissListener != null) {
                mDismissListener.onDismiss();
            }
        }
        return false;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (mDismissListener != null) {
            mDismissListener.onDismiss();
        }
    }

    public void setOnDismissListener(OnDismissListener listener) {
        mDismissListener = listener;
    }

    public interface OnDismissListener{
        void onDismiss();
    }

}
