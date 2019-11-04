package com.mchaw.tauruspay.ui.main.mine.about;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/4 17:53
 * @description:
 */
public class AboutFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_back_btn)
    TextView tvBcakBtn;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_about;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        tvTitle.setText("关于");
        tvBcakBtn.setText(" < ");
    }

    @OnClick(R.id.tv_back_btn)
    public void onClick(View view) {
        this.getActivity().finish();
    }
}
