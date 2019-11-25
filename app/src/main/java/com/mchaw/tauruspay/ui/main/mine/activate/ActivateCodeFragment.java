package com.mchaw.tauruspay.ui.main.mine.activate;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.common.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/25 10:30
 * @description:
 */
public class ActivateCodeFragment extends BaseFragment {

    @BindView(R.id.tv_back_title)
    TextView tvTitle;

    @BindView(R.id.tv_activate_code)
    TextView tvActivateCode;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_activate_code;

    }

    @Override
    protected void initFragment() {
        tvTitle.setText("激活口令");
    }

    @OnClick({R.id.iv_back,R.id.tv_copy})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                this.getActivity().finish();
                break;
            case R.id.tv_copy:
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", tvActivateCode.getText());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                if(TextUtils.isEmpty(tvActivateCode.getText())){
                    ToastUtils.showShortToast(getContext(),"没有可复制的内容");
                    return;
                }
                ToastUtils.showShortToast(getContext(),"已复制<"+tvActivateCode.getText()+">到剪切板");
                break;
        }
    }
}
