package com.mchaw.tauruspay.ui.main.mine.notice;

import android.os.Bundle;
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
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.mine.activate.adapter.ActivateCodeAdapter;
import com.mchaw.tauruspay.ui.main.mine.bill.constract.BillConstract;
import com.mchaw.tauruspay.ui.main.mine.bill.presenter.BillPresenter;
import com.mchaw.tauruspay.ui.main.mine.notice.adapter.NoticeAdapter;
import com.mchaw.tauruspay.ui.main.mine.notice.constract.NoticeConstract;
import com.mchaw.tauruspay.ui.main.mine.notice.presenter.NoticePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : Bruce Lee
 * @date : 2019/12/17 0017 07:47
 * @description :
 */
public class NoticeFragment extends BasePresentListFragment<NoticePresenter> implements NoticeConstract.View, BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.tv_back_title)
    TextView tvTitle;

    @BindView(R.id.rv_notice)
    RecyclerView rvNotice;

    //private List<ActivateCodeBean> list = new ArrayList<>();
    private NoticeAdapter noticeAdapter;

    @Override
    protected void initHintViews() {
        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rvNotice.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rvNotice.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rvNotice.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    protected void onRefresh() {
        noticeAdapter.setEmptyView(loadingView);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_notice_list;
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
        tvTitle.setText("通知");
        rvNotice.setLayoutManager(new LinearLayoutManager(getContext()));
        noticeAdapter = new NoticeAdapter(list);
        noticeAdapter.setOnItemClickListener(this);
        rvNotice.setAdapter(noticeAdapter);
        onRefresh();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
