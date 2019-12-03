package com.mchaw.tauruspay.ui.main.home.forsale.dialog;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.UiThread;
import androidx.fragment.app.FragmentManager;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.dialog.BaseDialogFragment;
import com.mchaw.tauruspay.ui.main.mine.dialog.ChangeBankCardDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/12/3 10:00
 * @description:
 */
public class CollectionListDialog extends BaseDialogFragment {
    @BindView(R.id.tv_context)
    TextView tvContext;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;
    @BindView(R.id.tv_sure)
    TextView tvSure;

    public static void showDialog(FragmentManager manager) {
        CollectionListDialog collectionListDialog = new CollectionListDialog();
        collectionListDialog.show(manager, null);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.dialog_collection_list;
    }

    @Override
    protected void initDialogFragment(View view) {
        tvContext.setText("请确定收到此订单的付款!\n确认后话费将直接进入对方账户，将无法追回!");
        tvSure.setClickable(false);
        new CountDownTimer(4*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                if(tvCountDown!=null) {
                    tvCountDown.setText("" + millisUntilFinished / 1000);
                }
            }
            public void onFinish() {
                if(tvCountDown!=null) {
                    tvCountDown.setText("0");
                }
                if(tvSure!=null) {
                    tvSure.setClickable(true);
                }
            }
        }.start();
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
                CollectionListDialog.ConfirmListener confirmListener = (CollectionListDialog.ConfirmListener) getParentFragment();
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
