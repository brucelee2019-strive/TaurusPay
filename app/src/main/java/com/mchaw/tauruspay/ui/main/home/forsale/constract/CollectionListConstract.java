package com.mchaw.tauruspay.ui.main.home.forsale.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.home.HomeDataBean;
import com.mchaw.tauruspay.bean.home.SellingOrderBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/21 9:53
 * @description:
 */
public interface CollectionListConstract {
    interface View extends BaseView {
        void setTradingList(List<SellingOrderBean> list);
        //void setUpLodingReceivables(int succeed);

        void setHomeDataBean(HomeDataBean homeDataBean);
    }

    interface Presenter extends BasePresenter<CollectionListConstract.View> {
        void getTradingList(String api_token);
        void upLodingReceivables(String codeId,String api_token);

        void getHomeDataBean(String api_token);
    }
}
