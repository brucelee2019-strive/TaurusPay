package com.mchaw.tauruspay.ui.main.mine.bill;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.bill.BillBean;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.mine.bill.constract.BillConstract;
import com.mchaw.tauruspay.ui.main.mine.bill.presenter.BillPresenter;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/25 18:29
 * @description:
 */
public class BillFragment extends BasePresentFragment<BillPresenter> implements BillConstract.View{
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
    protected void initFragment() {
        super.initFragment();
    }

    @Override
    public void setBillList(List<BillBean> list) {

    }
}
