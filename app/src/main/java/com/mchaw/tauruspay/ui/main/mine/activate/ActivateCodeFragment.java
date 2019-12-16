package com.mchaw.tauruspay.ui.main.mine.activate;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentListFragment;
import com.mchaw.tauruspay.bean.activate.ActivateCodeBean;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.mine.activate.adapter.ActivateCodeAdapter;
import com.mchaw.tauruspay.ui.main.mine.activate.constract.ActivateConstract;
import com.mchaw.tauruspay.ui.main.mine.activate.presenter.ActivatePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/25 10:30
 * @description:
 */
public class ActivateCodeFragment extends BasePresentListFragment<ActivatePresenter> implements ActivateConstract.View, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.tv_back_title)
    TextView tvTitle;

    @BindView(R.id.rv_activate)
    RecyclerView rvActivate;

    private List<ActivateCodeBean> list = new ArrayList<>();
    private ActivateCodeAdapter activateCodeAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_activate_code;
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        tvTitle.setText("激活口令");
        rvActivate.setLayoutManager(new LinearLayoutManager(getContext()));
        activateCodeAdapter = new ActivateCodeAdapter(list);
        activateCodeAdapter.setOnItemChildClickListener(this);
        rvActivate.setAdapter(activateCodeAdapter);
        onRefresh();
    }

    @Override
    protected void initHintViews() {
        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rvActivate.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rvActivate.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rvActivate.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    protected void onRefresh() {
        activateCodeAdapter.setEmptyView(loadingView);
        presenter.getActiveCodeList(PreferencesUtils.getString(getContext(), "token"));
    }

    @OnClick({R.id.iv_back, R.id.tv_back_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
            case R.id.tv_back_title:
                this.getActivity().finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void setActiveCodeList(List<ActivateCodeBean> list) {
        if (list != null && list.size() > 0) {
            activateCodeAdapter.setNewData(list);
        } else {
            activateCodeAdapter.setNewData(null);
            activateCodeAdapter.setEmptyView(notDataView);
        }
    }

    @Override
    public void setActiveCodeListFail() {
        activateCodeAdapter.setEmptyView(errorView);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ActivateCodeBean activateCodeBean = (ActivateCodeBean) adapter.getItem(position);
        switch (view.getId()) {
            case R.id.btn_copy:
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", activateCodeBean.getCode());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                if (TextUtils.isEmpty(activateCodeBean.getCode())) {
                    ToastUtils.showShortToast(getContext(), "没有可复制的内容");
                    return;
                }
                ToastUtils.showShortToast(getContext(), "已复制<" + activateCodeBean.getCode() + ">到剪切板");
                break;
            default:
                break;
        }
    }
}
