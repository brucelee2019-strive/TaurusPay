package com.mchaw.tauruspay.ui.main.home.forsale;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.forsale.adapter.CollectionListAdapter;
import com.mchaw.tauruspay.ui.main.home.forsale.adapter.ForSaleListAdapter;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.CollectionListConstract;
import com.mchaw.tauruspay.ui.main.home.forsale.presenter.CollectionListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 11:58
 * @description:收款Fragment
 */
public class CollectionListFragment extends BasePresentFragment<CollectionListPresenter> implements CollectionListConstract.View {

    @BindView(R.id.rv_for_collection)
    RecyclerView rvForCollection;

    private List<String> list = new ArrayList<String>();

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_for_collection_list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void injectFragmentComponent(ActivityComponent component) {
        super.injectFragmentComponent(component);
        component.inject(this);
    }

    @Override
    protected void initFragment() {
        super.initFragment();
        list.add("成功");
        list.add("未通过");
        list.add("成功");
        list.add("未通过");
        list.add("成功");
        list.add("未通过");
        list.add("成功");
        list.add("成功");
        list.add("未通过");
        list.add("成功");
        list.add("成功");
        list.add("成功");
        rvForCollection.setLayoutManager(new LinearLayoutManager(getContext()));
        CollectionListAdapter collectionListAdapter = new CollectionListAdapter(list);
        rvForCollection.setAdapter(collectionListAdapter);
    }
}
