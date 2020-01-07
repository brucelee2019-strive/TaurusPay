package com.mchaw.tauruspay.ui.main.mine.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.BaseDialogFragment;
import com.mchaw.tauruspay.common.util.DensityUtils;
import com.mchaw.tauruspay.common.util.ScreenUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.common.widget.Solve7PopupWindow;

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
    @BindView(R.id.iv_choice)
    ImageView ivChoice;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;

    /**
     * 筛选PopWindow
     */
    private Solve7PopupWindow filterPop;
    /**
     * 筛选UI宽度
     */
    private int filterWidth;

    //private final int WEIXIN_PAY = 0;
    //private final int ALI_PAY = 1;
    private final int ALI_PAY_AT_WILL = 3;
    private final int WEIXIN_PAY_AT_WILL = 4;
    private int payType;

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
        filterPopWindow();
        tvPayType.setText("支付宝");
        payType = ALI_PAY_AT_WILL;
        etIncomeAccount.setHint("请输入支付宝账号");
        etIncomeNick.setHint("请输入支付宝昵称");
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

    @OnClick({R.id.tv_cancel, R.id.tv_sure, R.id.iv_choice,R.id.tv_pay_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                if (dialog != null) {
                    dialog.dismiss();
                }
                break;
            case R.id.tv_sure:
                if (TextUtils.isEmpty(etIncomeAccount.getText().toString())) {
                    ToastUtils.showShortToast(getContext(), "账号不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(etIncomeNick.getText().toString())) {
                    ToastUtils.showShortToast(getContext(), "昵称不能为空！");
                    return;
                }
                if (dialog != null) {
                    dialog.dismiss();
                }
                QRCodeGroupDialog.ConfirmListener confirmListener = (QRCodeGroupDialog.ConfirmListener) getParentFragment();
                confirmListener.onClickComplete(String.valueOf(payType), etIncomeAccount.getText().toString(), etIncomeNick.getText().toString());
                break;
            case R.id.iv_choice:
            case R.id.tv_pay_type:
                if (filterPop.isShowing()) {
                    filterPop.dismiss();
                } else {
                    int[] location = new int[2];
                    ivChoice.getLocationOnScreen(location);
                    /**
                     * x,y坐标说明
                     * x:屏幕宽度-pop布局宽度
                     * y:toolbar的y坐标+toolbar的高度-pop布局margin
                     */
                    filterPop.showAtLocation(ivChoice, Gravity.NO_GRAVITY, ScreenUtils.getScreenWidth(getActivity()) - filterWidth - DensityUtils.dp2px(getActivity(), 25), location[1] + DensityUtils.dp2px(getActivity(), 0));
                }
                break;
            default:
                break;
        }
    }

    public interface ConfirmListener {
        void onClickComplete(String code, String account, String nick);
    }

    /**
     * 筛选对话框
     */
    private void filterPopWindow() {
        final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.pop_pay_filter, null);
        filterWidth = ScreenUtils.getWidgetWidth(layout);
        filterPop = new Solve7PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        // 允许点击外部消失
        //注意这里如果不设置，下面的setOutsideTouchable(true);允许点击外部消失会失效
        filterPop.setBackgroundDrawable(new BitmapDrawable());
        //设置显示的动画
        filterPop.setAnimationStyle(R.style.PopWindowTopShow);
        //设置外部点击关闭ppw窗口
        filterPop.setOutsideTouchable(true);
        filterPop.setFocusable(true);
        //popSkipFilter(layout, R.id.tv_alipay, ALI_PAY);
        //popSkipFilter(layout, R.id.tv_weixin, WEIXIN_PAY);
        popSkipFilter(layout, R.id.tv_alipay_at_will, ALI_PAY_AT_WILL);
        popSkipFilter(layout, R.id.tv_weixin_at_will, WEIXIN_PAY_AT_WILL);
    }

    /**
     * 跳转指筛选
     */
    private void popSkipFilter(View layout, int id, final int type) {
        TextView textView = layout.findViewById(id);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type) {
//                    case ALI_PAY:
//                        tvPayType.setText("支付宝");
//                        payType = ALI_PAY;
//                        etIncomeAccount.setHint("请输入支付宝账号");
//                        etIncomeNick.setHint("请输入支付宝昵称");
//                        break;
//                    case WEIXIN_PAY:
//                        tvPayType.setText("微信");
//                        payType = WEIXIN_PAY;
//                        etIncomeAccount.setHint("请输入微信账号");
//                        etIncomeNick.setHint("请输入微信昵称");
//                        break;
                    case ALI_PAY_AT_WILL:
                        tvPayType.setText("支付宝");
                        payType = ALI_PAY_AT_WILL;
                        etIncomeAccount.setHint("请输入支付宝账号");
                        etIncomeNick.setHint("请输入支付宝昵称");
                        break;
                    case WEIXIN_PAY_AT_WILL:
//                        tvPayType.setText("微信");
//                        payType = WEIXIN_PAY_AT_WILL;
//                        etIncomeAccount.setHint("请输入微信账号");
//                        etIncomeNick.setHint("请输入微信昵称");
                        ToastUtils.showShortToast(getContext(),"暂未开放！");
                        break;
                    default:
                        break;
                }
                filterPop.dismiss();
            }
        });
    }
}
