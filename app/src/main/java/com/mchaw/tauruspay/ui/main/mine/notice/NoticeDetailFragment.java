package com.mchaw.tauruspay.ui.main.mine.notice;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.notice.NoticeBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.mine.notice.constract.NoticeConstract;
import com.mchaw.tauruspay.ui.main.mine.notice.presenter.NoticePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/12/17 10:55
 * @description:
 */
public class NoticeDetailFragment extends BasePresentFragment<NoticePresenter> implements NoticeConstract.View {
    private String title,content,date,id;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_content)
    TextView tvContent;

    public static NoticeDetailFragment newInstance(String title,String content,String date,String id) {
        NoticeDetailFragment fragment = new NoticeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.INTENT_NAME,title);
        bundle.putString(Constant.INTENT_TEXT,content);
        bundle.putString(Constant.INTENT_DATE,date);
        bundle.putString(Constant.INTENT_ID,id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void getIntentData() {
        super.getIntentData();
        title = getArguments().getString(Constant.INTENT_NAME);
        content = getArguments().getString(Constant.INTENT_TEXT);
        date = getArguments().getString(Constant.INTENT_DATE);
        id = getArguments().getString(Constant.INTENT_ID);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_notice_detial;
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        tvDate.setText(date);
        tvTitle.setText(title);
        tvContent.setText(content);
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

    @OnClick({R.id.btn_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sure:
                presenter.getNotice(MyFrameApplication.tokenStr,id);
                break;
            default:
                break;
        }
    }

    @Override
    public void setNotice(NoticeBean noticeBean) {
        getActivity().finish();
    }

    @Override
    public void setNoticeFail() {
        getActivity().finish();
    }
}
