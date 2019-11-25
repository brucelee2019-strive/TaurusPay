package com.mchaw.tauruspay.ui.main.mine.bill;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.bill.BillBean;
import com.mchaw.tauruspay.common.util.DensityUtils;
import com.mchaw.tauruspay.common.util.ScreenUtils;
import com.mchaw.tauruspay.common.widget.Solve7PopupWindow;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.mine.bill.constract.BillConstract;
import com.mchaw.tauruspay.ui.main.mine.bill.presenter.BillPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/25 18:29
 * @description:
 */
public class BillFragment extends BasePresentFragment<BillPresenter> implements BillConstract.View {
    /**
     * 筛选PopWindow
     */
    private Solve7PopupWindow filterPop;
    /**
     * 筛选UI宽度
     */
    private int filterWidth;

    @BindView(R.id.tv_back_title)
    TextView tvTitle;

    @BindView(R.id.tv_filtrate)
    TextView tvFiltrate;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_bill;
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
        filterPopWindow();
        tvTitle.setText("账单");
        tvFiltrate.setText("全部");
    }

    @OnClick({R.id.iv_back, R.id.tv_filtrate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                this.getActivity().finish();
                break;
            case R.id.tv_filtrate:
                if (filterPop.isShowing()) {
                    filterPop.dismiss();
                } else {
                    int[] location = new int[2];
                    tvFiltrate.getLocationOnScreen(location);
                    /**
                     * x,y坐标说明
                     * x:屏幕宽度-pop布局宽度
                     * y:toolbar的y坐标+toolbar的高度-pop布局margin
                     */
                    filterPop.showAtLocation(tvFiltrate, Gravity.NO_GRAVITY, ScreenUtils.getScreenWidth(getActivity()) - filterWidth, location[1] + DensityUtils.dp2px(getActivity(), 16));
                }
                break;
            default:
                break;
        }
    }

    /**
     * 筛选对话框
     */
    private void filterPopWindow() {
        final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.pop_score_filter, null);
        filterWidth = ScreenUtils.getWidgetWidth(layout);
        filterPop = new Solve7PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        // 允许点击外部消失
        //注意这里如果不设置，下面的setOutsideTouchable(true);允许点击外部消失会失效
        filterPop.setBackgroundDrawable(new BitmapDrawable());
        //设置显示的动画
        filterPop.setAnimationStyle(R.style.PopWindowTopShow);
        //设置外部点击关闭ppw窗口
        filterPop.setOutsideTouchable(true);
        filterPop.setFocusable(true);
    }

    @Override
    public void setBillList(List<BillBean> list) {

    }
}
