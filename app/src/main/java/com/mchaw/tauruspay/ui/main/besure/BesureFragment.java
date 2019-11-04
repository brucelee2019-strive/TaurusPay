package com.mchaw.tauruspay.ui.main.besure;

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
 * @date : 2019/11/3 0003 21:09
 * @description :
 */
public class BesureFragment extends BaseFragment {

    @BindView(R.id.tv_txt1)
    TextView tvTxt1;
    @BindView(R.id.tv_txt2)
    TextView tvTxt2;
    @BindView(R.id.tv_txt3)
    TextView tvTxt3;
    @BindView(R.id.tv_txt4)
    TextView tvTxt4;
    @BindView(R.id.tv_txt5)
    TextView tvTxt5;
    @BindView(R.id.tv_txt6)
    TextView tvTxt6;
    @BindView(R.id.tv_txt7)
    TextView tvTxt7;
    @BindView(R.id.tv_txt8)
    TextView tvTxt8;
    @BindView(R.id.tv_txt9)
    TextView tvTxt9;

    String str1 = "1：必须使用绑定银行卡充值，请确保绑定银行卡填写正确，否则会造成资金损失；";
    String str2 = "2：接单时，必须手机后台运行支付宝，且保持电量充足，网络流畅";
    String str3 = "3：接单前，请自测您的支付宝成功收款后再接单，否则会造成资金损失";
    String str4 = "4：接单后，请确保您的<font color='#FF0000'>支付宝收到对应额度后</font>再确认收款，否则造成资金损失后果自负；";
    String str5 = "5：确认订单时，请务必<font color='#FF0000'>反复核实收款额度与所确定的订单数额完全一致</font>，如188.01.否则造成的资金损失后果自负";
    String str6 = "6：接单过程人必须在线，支付宝到账后必须在<font color='#FF0000'>60秒内确认订单，否则可能会被封号</font>";
    String str7 = "7：停止接单后，请确保所有订单已处理完毕，否则出现纠纷直接封号";
    String str8 = "*请务必仔细认真阅读并理解条款，代售服务为现金交易；";
    String str9 = "请务必谨慎操作，操作失误造成的资金损失，我们不负责赔偿。";

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_besure;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        tvTxt1.setText(str1);
        tvTxt2.setText(str2);
        tvTxt3.setText(str3);
        tvTxt4.setText(Html.fromHtml(str4));
        tvTxt5.setText(Html.fromHtml(str5));
        tvTxt6.setText(Html.fromHtml(str6));
        tvTxt7.setText(str7);
        tvTxt8.setText(str8);
        tvTxt9.setText(str9);
    }
}
