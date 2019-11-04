package com.mchaw.tauruspay.ui.main.home;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.di.component.ActivityComponent;

import butterknife.BindView;

/**
 * @author : Bruce Lee
 * @date : 2019/11/3 0003 21:02
 * @description :
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.tv_notice_text)
    TextView tvNotiveText;

    @BindView(R.id.tv_pre_sale_txt)
    TextView tvPreSaleTxt;

    @BindView(R.id.tv_after_sale_txt)
    TextView tvAfterSaleTxt;

    private String strPre = "*开始代售前，请保持<font color='#8d64cb'>金牛话费</font>与<font color='#ff0000'>支付宝</font>在线";
    private String strAfter = "*开始代售时，请及时查询确认收款";

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        tvNotiveText.setSelected(true);
        tvPreSaleTxt.setText(Html.fromHtml(strPre));
        tvAfterSaleTxt.setText(strAfter);
    }
}
