package com.mchaw.tauruspay.ui.main.mine.notice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentListFragment;
import com.mchaw.tauruspay.bean.eventbus.NoticeSureEvent;
import com.mchaw.tauruspay.bean.notice.NoticeBean;
import com.mchaw.tauruspay.bean.notice.NoticeListBean;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.mine.notice.adapter.NoticeAdapter;
import com.mchaw.tauruspay.ui.main.mine.notice.constract.NoticeConstract;
import com.mchaw.tauruspay.ui.main.mine.notice.presenter.NoticePresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : Bruce Lee
 * @date : 2019/12/17 0017 07:47
 * @description :
 */
public class NoticeFragment extends BasePresentListFragment<NoticePresenter> implements NoticeConstract.View, BaseQuickAdapter.OnItemClickListener {
    private static final int REQUEST_CODE_READ_NOTICE= 112;
    @BindView(R.id.tv_back_title)
    TextView tvTitle;

    @BindView(R.id.rv_notice)
    RecyclerView rvNotice;

    private List<NoticeListBean> list = new ArrayList<>();
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
        presenter.getNotice(MyFrameApplication.tokenStr, "0");
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
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        NoticeListBean noticeListBean = (NoticeListBean) adapter.getItem(position);
        if(noticeListBean == null ){
            return;
        }
        startFragmentForResult(NoticeDetailFragment.newInstance(noticeListBean.getTitle(),noticeListBean.getContent(),noticeListBean.getCreated_at(),noticeListBean.getNoticeid()),REQUEST_CODE_READ_NOTICE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_READ_NOTICE && resultCode == Activity.RESULT_OK) {
            onRefresh();
        }
    }

    @Override
    public void setNotice(NoticeBean noticeBean) {
        if (noticeBean == null) {
            noticeAdapter.setEmptyView(notDataView);
            return;
        }
        NoticeSureEvent noticeSureEvent = new NoticeSureEvent();
        noticeSureEvent.setNoticeNum(noticeBean.getNotice());
        EventBus.getDefault().post(noticeSureEvent);
        list = noticeBean.getList();
        if(list == null || list.size()<=0){
            noticeAdapter.setEmptyView(notDataView);
            return;
        }
        noticeAdapter.setNewData(list);
    }

    @Override
    public void setNoticeFail() {
        noticeAdapter.setEmptyView(errorView);
    }
}
