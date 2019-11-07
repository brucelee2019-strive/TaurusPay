package com.mchaw.tauruspay.ui.main.recharge.record;

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
 * @date : 2019/11/7 19:57
 * @description:
 */
public class RecordNextFragment extends BaseFragment {
    @BindView(R.id.tv_back_title)
    TextView tvBackTitle;

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
        tvBackTitle.setText("充值");
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            default:
                break;
        }
    }
}
