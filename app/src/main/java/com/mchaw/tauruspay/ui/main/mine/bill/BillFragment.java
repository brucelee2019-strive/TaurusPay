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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.bill.BillBean;
import com.mchaw.tauruspay.common.util.DensityUtils;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.ScreenUtils;
import com.mchaw.tauruspay.common.widget.Solve7PopupWindow;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.mine.bill.adapter.BillAdapter;
import com.mchaw.tauruspay.ui.main.mine.bill.constract.BillConstract;
import com.mchaw.tauruspay.ui.main.mine.bill.presenter.BillPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/25 18:29
 * @description:
 */
public class BillFragment extends BasePresentFragment<BillPresenter> implements BillConstract.View {
    private final int ALL = 0;
    private final int RECHARGE_SUCCEED = 1;
    private final int ORDER_FEE = 2;
    private final int ORDER_RETURN = 3;
    private final int ORDER_BONUS = 4;
    /**
     * 筛选PopWindow
     */
    private Solve7PopupWindow filterPop;
    /**
     * 筛选UI宽度
     */
    private int filterWidth;

    private BillAdapter billAdapter;

    private List<BillBean> billBeanList = new ArrayList<>();

    @BindView(R.id.tv_back_title)
    TextView tvTitle;

    @BindView(R.id.tv_filtrate)
    TextView tvFiltrate;

    @BindView(R.id.rv_activate)
    RecyclerView rvActivate;

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
        rvActivate.setLayoutManager(new LinearLayoutManager(getContext()));
        billAdapter = new BillAdapter(billBeanList);
        rvActivate.setAdapter(billAdapter);
        presenter.getBillList(PreferencesUtils.getString(getContext(), "token"));
    }

    @OnClick({R.id.iv_back, R.id.tv_filtrate,R.id.iv_filtrate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                this.getActivity().finish();
                break;
            case R.id.tv_filtrate:
            case R.id.iv_filtrate:
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
        popSkipFilter(layout, R.id.tv_all, ALL);
        popSkipFilter(layout, R.id.tv_recharge_succeed, RECHARGE_SUCCEED);
        popSkipFilter(layout, R.id.tv_order_fee, ORDER_FEE);
        popSkipFilter(layout, R.id.tv_order_return, ORDER_RETURN);
        popSkipFilter(layout, R.id.tv_order_bonus, ORDER_BONUS);
    }

    /**
     * 跳转指筛选
     */
    private void popSkipFilter(View layout, int id, final int type) {
        TextView textView = layout.findViewById(id);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type) {
                    case ALL:
                        tvFiltrate.setText("全部");
                        break;
                    case RECHARGE_SUCCEED:
                        tvFiltrate.setText("充值成功");
                        break;
                    case ORDER_FEE:
                        tvFiltrate.setText("接单扣费");
                        break;
                    case ORDER_RETURN:
                        tvFiltrate.setText("订单退费");
                        break;
                    case ORDER_BONUS:
                        tvFiltrate.setText("结单红利");
                        break;
                    default:
                        break;
                }
                filterPop.dismiss();
            }
        });
    }

    @Override
    public void setBillList(List<BillBean> list) {
        billBeanList = list;
        setBilllistByType(list, ALL);
    }

    private void setBilllistByType(List<BillBean> list, int type) {
        switch (type) {
            case ALL:
                billAdapter.setNewData(list);
                break;
            case RECHARGE_SUCCEED:
                break;
            case ORDER_FEE:
                break;
            case ORDER_RETURN:
                break;
            case ORDER_BONUS:
                break;
            default:
                break;
        }
    }
}
