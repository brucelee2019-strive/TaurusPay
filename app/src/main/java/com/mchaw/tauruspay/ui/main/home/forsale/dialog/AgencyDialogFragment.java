package com.mchaw.tauruspay.ui.main.home.forsale.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.BaseDialogFragment;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.widget.WheelPicker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : Bruce Lee
 * @date : 2020/1/12 0012 10:41
 * @description :
 */
public class AgencyDialogFragment extends BaseDialogFragment implements  WheelPicker.OnWheelChangeListener {

    @BindView(R.id.tv_mgs)
    TextView tvMsg;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.iv_close)
    ImageView ivClose;

    @BindView(R.id.wheel_picker)
    WheelPicker wheelPicker;

    private List<String> list;
    private String round;

    private int position;

    private String msg;
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

    public static <T> AgencyDialogFragment newInstance(List<T> list) {
        return newInstance(list,0);
    }

    public static <T> AgencyDialogFragment newInstance(List<T> list , int position) {
        Bundle bundle = new Bundle();
        //bundle.putSerializable(Constant.INTENT_LIST, (Serializable) list);
        bundle.putStringArrayList(Constant.INTENT_LIST, (ArrayList<String>) list);
        bundle.putInt(Constant.INTENT_POSITION,position);
        AgencyDialogFragment dialog = new AgencyDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.dialog_lower_agency;
    }

    @Override
    protected void initDialogFragment(View view) {
        if (!TextUtils.isEmpty(msg)) {
            tvMsg.setText(msg);
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

        position = Math.min(position, list.size() - 1);
        position = Math.max(position, 0);
        wheelPicker.setData(getWheelList());
        wheelPicker.setSelectedItemPosition(position);
        wheelPicker.setOnWheelChangeListener(this);
        onWheelSelected(position);
    }

    @Override
    public void onWheelScrolled(int offset) {
    }

    @Override
    public void onWheelSelected(int position) {
        if (list != null && !list.isEmpty()) {
            round = list.get(position);
        }
    }

    @Override
    public void onWheelScrollStateChanged(int state) {

    }
    private List getWheelList() {
        List<String> array = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //DialogItemConvert bean = list.get(i);
            array.add(list.get(i));
        }
        return array;
    }

    @OnClick({R.id.btn_cancel, R.id.btn_confirm, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
            case R.id.iv_close:
                dialog.dismiss();
                break;
            case R.id.btn_confirm:
                if (callBack != null) {
                    callBack.onDialogViewClick(DIALOG_CONFIRM, round);
                }
                dialog.dismiss();
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            list = (List<String>) getArguments().getSerializable(Constant.INTENT_LIST);
            position = getArguments().getInt(Constant.INTENT_POSITION);
        }
    }
}
