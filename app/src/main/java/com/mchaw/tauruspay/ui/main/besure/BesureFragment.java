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
    @BindView(R.id.tv_txt8)
    TextView tvTxt8;
    @BindView(R.id.tv_txt9)
    TextView tvTxt9;

    String str1 = "1：接单代售时，支付宝必须保持后行正常运行，且网络流畅电量充足；";
    String str2 = "2：接单前，请自己检查支付宝收款是否正常，确认正常后再开始接单；";
    String str3 = "3：接单后，请确认您的支付宝收款金额与代售提示的金额一致后再确认收款，否则造成资金损失后果自负；";
    String str4 = "4：停止接单后，请确保所有订单处理完毕，出现纠纷可能会封号；";
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

        tvTxt8.setText(str8);
        tvTxt9.setText(str9);
    }
}
