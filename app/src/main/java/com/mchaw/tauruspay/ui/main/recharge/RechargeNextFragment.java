package com.mchaw.tauruspay.ui.main.recharge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.common.Constant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 19:57
 * @description:
 */
public class RechargeNextFragment extends BaseFragment {
    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;
    @BindView(R.id.tv_remittance_btn)
    TextView tvRemittanceBtn;
    @BindView(R.id.tv_remittance_notice)
    TextView tvRemittanceNotice;
    @BindView(R.id.iv_state_recharging)
    ImageView ivStateRecharging;
    @BindView(R.id.iv_state_auditing)
    ImageView ivStateAuditing;
    @BindView(R.id.cl_recharge_num)
    ConstraintLayout clRechargeNum;
    @BindView(R.id.rv_recharge_mes)
    RecyclerView rvRechargeMes;
    @BindView(R.id.et_recharge_num)
    EditText etRechargeNum;
    @BindView(R.id.tv_income_num)
    TextView tvIncomeNum;

    private String outToNum;

    private int RECHARGEING = 0;
    private int AUDITING = 1;
    private int state;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_record_next;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        state = RECHARGEING;
        tvBackTitle.setText("充值");
        showByState(RECHARGEING);
        etRechargeNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvIncomeNum.setText(s.toString());
                outToNum = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_remittance_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.tv_remittance_btn:
                if(state == RECHARGEING){
                    state = AUDITING;
                    showByState(AUDITING);
                } else {
                    Intent intent = new Intent();
                    //intent.putExtra(Constant.INTENT_TYPE, type);
                    getActivity().setResult(Activity.RESULT_OK, intent);
                    getActivity().finish();
                }
                break;
            default:
                break;
        }
    }

    private void showByState(int state) {
        tvRemittanceBtn.setText(state==RECHARGEING?"下一步":"我已汇款");
        tvRemittanceNotice.setText(state==RECHARGEING?"点击下一步可显示对应的收款卡号":"如以完成，请点击我已汇款");
        ivStateRecharging.setImageResource(state==RECHARGEING?R.drawable.cz_btn_cz_xz:R.drawable.cz_btn_cz_mr);
        ivStateAuditing.setImageResource(state==RECHARGEING?R.drawable.cz_btn_sh_mr:R.drawable.cz_btn_sh_xz);
        clRechargeNum.setVisibility(state==RECHARGEING?View.VISIBLE:View.GONE);
        rvRechargeMes.setVisibility(state==RECHARGEING?View.GONE:View.VISIBLE);
        etRechargeNum.setFocusable(state==RECHARGEING?true:false);
        etRechargeNum.setFocusableInTouchMode(state==RECHARGEING?true:false);
    }


}
