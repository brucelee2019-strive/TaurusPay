package com.mchaw.tauruspay.ui.main.recharge;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.recharge.RechargeNextBean;
import com.mchaw.tauruspay.bean.recharge.RechargeSureBean;
import com.mchaw.tauruspay.bean.recharge.RechargeTraBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.dialog.LoadingDialog;
import com.mchaw.tauruspay.common.util.OneClick.AntiShake;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.StringUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.recharge.constract.RechargeNextConstract;
import com.mchaw.tauruspay.ui.main.recharge.presenter.RechargeNextPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 19:57
 * @description:
 */
public class RechargeNextFragment extends BasePresentFragment<RechargeNextPresenter> implements RechargeNextConstract.View, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;
    @BindView(R.id.btn_remittance_btn)
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
    private String orderNum;

    private int RECHARGEING = 0;
    private int AUDITING = 1;
    private int state;
    private List<RechargeTraBean> list = new ArrayList();
    private RechargeNextAdapter rechargeNextAdapter;

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
        super.initFragment();
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
        rvRechargeMes.setLayoutManager(new LinearLayoutManager(getContext()));
        rechargeNextAdapter = new RechargeNextAdapter(list);
        rechargeNextAdapter.setOnItemChildClickListener(this);
        rvRechargeMes.setAdapter(rechargeNextAdapter);
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_remittance_btn,R.id.btn_copy_btn})
    public void onClick(View view) {
        if (AntiShake.check(view.getId())) {    //判断是否多次点击
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.btn_remittance_btn:
                if (state == RECHARGEING) {
                    LoadingDialog.showDialog(getChildFragmentManager());
                    //服务器要的单位是分
                    presenter.getRechargeNextBean(outToNum+"00", PreferencesUtils.getString(getContext(), "token"));
                } else {
                    if (TextUtils.isEmpty(orderNum)) {
                        ToastUtils.showShortToast(getContext(), "订单号有误！");
                        return;
                    }
                    LoadingDialog.showDialog(getChildFragmentManager());
                    presenter.getRechargeSureBean(orderNum, PreferencesUtils.getString(getContext(), "token"));
                }
                break;
            case R.id.btn_copy_btn:
                if(TextUtils.isEmpty(tvIncomeNum.getText())){
                    return;
                }
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", tvIncomeNum.getText());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                if(TextUtils.isEmpty(tvIncomeNum.getText())){
                    ToastUtils.showShortToast(getContext(),"没有可复制的内容");
                    return;
                }
                ToastUtils.showShortToast(getContext(),"已复制<"+tvIncomeNum.getText()+">到剪切板");
                break;
            default:
                break;
        }
    }

    private void showByState(int state) {
        tvRemittanceBtn.setText(state == RECHARGEING ? "下一步" : "我已汇款");
        tvRemittanceNotice.setText(state == RECHARGEING ? "点击下一步可显示对应的收款卡号" : "如以完成，请点击我已汇款");
        ivStateRecharging.setImageResource(state == RECHARGEING ? R.drawable.cz_btn_cz_xz : R.drawable.cz_btn_cz_mr);
        ivStateAuditing.setImageResource(state == RECHARGEING ? R.drawable.cz_btn_sh_mr : R.drawable.cz_btn_sh_xz);
        clRechargeNum.setVisibility(state == RECHARGEING ? View.VISIBLE : View.GONE);
        rvRechargeMes.setVisibility(state == RECHARGEING ? View.GONE : View.VISIBLE);
        etRechargeNum.setFocusable(state == RECHARGEING ? true : false);
        etRechargeNum.setFocusableInTouchMode(state == RECHARGEING ? true : false);
    }

    //下一步成功
    @Override
    public void setRechargeNextBean(RechargeNextBean rechargeNextBean) {
        LoadingDialog.dismissDailog();
        if (rechargeNextBean == null) {
            ToastUtils.showShortToast(getContext(), "服务器返回结果为空");
            return;
        }
        orderNum = rechargeNextBean.getOrderid();
        state = AUDITING;
        showByState(AUDITING);
        RechargeTraBean r1 = new RechargeTraBean();
        r1.setTitle("到账额度");
        r1.setContent(StringUtils.fenToYuan(rechargeNextBean.getAmount()));
        list.add(r1);
        RechargeTraBean r2 = new RechargeTraBean();
        r2.setTitle("收款账户");
        r2.setContent(rechargeNextBean.getAccount());
        list.add(r2);
        RechargeTraBean r3 = new RechargeTraBean();
        r3.setTitle("收款银行");
        r3.setContent(rechargeNextBean.getBank());
        list.add(r3);
        RechargeTraBean r4 = new RechargeTraBean();
        r4.setTitle("支行信息");
        r4.setContent(rechargeNextBean.getBankname());
        list.add(r4);
        RechargeTraBean r5 = new RechargeTraBean();
        r5.setTitle("收款卡号");
        r5.setContent(rechargeNextBean.getCardnumber());
        list.add(r5);
        RechargeTraBean r6 = new RechargeTraBean();
        r6.setTitle("留言码");
        r6.setContent(rechargeNextBean.getRemarks());
        list.add(r6);
        rechargeNextAdapter.notifyDataSetChanged();
    }

    //请求失败
    @Override
    public void setRechargeNextFail() {
        LoadingDialog.dismissDailog();
    }

    //我已汇款成功
    @Override
    public void setRechargeSureBean(RechargeSureBean rechargeSureBean) {
        LoadingDialog.dismissDailog();
        if (rechargeSureBean == null) {
            ToastUtils.showShortToast(getContext(), "服务器返回结果为空");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(Constant.INTENT_ID, rechargeSureBean.getOrderid());
        intent.putExtra(Constant.INTENT_MSG, outToNum);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        RechargeTraBean rechargeTraBean = (RechargeTraBean) adapter.getItem(position);
        switch (view.getId()) {
            case R.id.btn_copy_btn:
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", rechargeTraBean.getContent());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                if(TextUtils.isEmpty(rechargeTraBean.getContent())){
                    ToastUtils.showShortToast(getContext(),"没有可复制的内容");
                    return;
                }
                ToastUtils.showShortToast(getContext(),"已复制<"+rechargeTraBean.getContent()+">到剪切板");
                break;
            default:
                break;
        }
    }
}
