package com.mchaw.tauruspay.ui.main.home;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.forsale.ForSaleFragment;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.TransferAccountsFragment;

import butterknife.BindView;
import butterknife.OnClick;

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

    private String strPre = "*开始代售前，请保持<font color='#FF9600'>金牛话费</font>与<font color='#00aaef'>支付宝</font>在线";
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

    @OnClick({R.id.tv_transfer_btn, R.id.tv_start_sail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_transfer_btn:
                startFragment(new TransferAccountsFragment());
                break;
            case R.id.tv_start_sail:
                startFragment(new ForSaleFragment());
                break;
            default:
                break;
        }
    }
}
