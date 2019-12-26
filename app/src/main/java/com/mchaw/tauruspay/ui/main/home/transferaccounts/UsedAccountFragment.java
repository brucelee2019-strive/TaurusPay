package com.mchaw.tauruspay.ui.main.home.transferaccounts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.bean.home.TransferUsedAccountsBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.GsonUtils;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.adapter.UsedAccountAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/12/2 9:27
 * @description:常用转账账户记录
 */
public class UsedAccountFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;
    @BindView(R.id.rv_used_account)
    RecyclerView rvUsedAccount;

    private List<TransferUsedAccountsBean> list;

    @Override
    protected void getIntentData() {
        super.getIntentData();
        list = GsonUtils.parseListFromJson(PreferencesUtils.getString(getActivity(), Constant.USED_ACCOUNT), TransferUsedAccountsBean.class);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_used_account;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        tvBackTitle.setText("常用账号");
        rvUsedAccount.setLayoutManager(new LinearLayoutManager(getContext()));
        UsedAccountAdapter adapter = new UsedAccountAdapter(list);
        adapter.setOnItemClickListener(this);
        rvUsedAccount.setAdapter(adapter);
    }

    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TransferUsedAccountsBean bean = (TransferUsedAccountsBean) adapter.getItem(position);
        Intent intent = new Intent();
        intent.putExtra(Constant.INTENT_ACCOUNT, bean.getAccount());
        intent.putExtra(Constant.INTENT_NAME, bean.getName());
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }
}
