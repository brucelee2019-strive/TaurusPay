package com.mchaw.tauruspay.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.bean.Movie;
import com.mchaw.tauruspay.bean.ScoreAllStateBean;
import com.mchaw.tauruspay.bean.ShopBean;
import com.mchaw.tauruspay.ui.score.constract.ScoreConstract;
import com.mchaw.tauruspay.ui.score.presenter.ScorePresenter;

import java.util.List;

import butterknife.BindView;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/30 0030 11:29
 */
public class ScoreFragment extends BasePresentFragment<ScorePresenter> implements ScoreConstract.View {
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_score;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        presenter.getTop250(1,1);
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    public void setScoreList(ScoreAllStateBean bean) {
        tvContent.setText(bean.toString());
        ToastUtils.showShortToast(getContext(),"Score结果:"+bean.toString());
    }

    @Override
    public void setShopList(List<ShopBean> list) {
        tvContent.setText(list.toString());
        ToastUtils.showShortToast(getContext(),"Shop结果:"+list.toString());
    }

    @Override
    public void serError(String mesg) {
        tvContent.setText(mesg);
    }

    @Override
    public void setTop250(List<Movie> list) {
        tvContent.setText(list.toString());
    }
}
