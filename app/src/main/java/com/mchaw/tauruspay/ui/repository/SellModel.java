package com.mchaw.tauruspay.ui.repository;

import com.mchaw.tauruspay.bean.bill.BillBean;
import com.mchaw.tauruspay.bean.home.SellingOrderBean;
import com.mchaw.tauruspay.bean.home.StartOrOverSellBean;
import com.mchaw.tauruspay.http.ResultDisposable;
import com.mchaw.tauruspay.http.ScheduleTranformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Bruce Lee
 * @date : 2019/11/21 10:22
 * @description:交易相关订阅名目
 */
public class SellModel extends BaseModel{
    @Inject
    public SellModel() {

    }

    public Observable<StartOrOverSellBean> startingOrOverSell(String groupid, int state, String token) {
        return apiService.startingOrOverSell(groupid,state,token)
                .compose(new ResultDisposable<StartOrOverSellBean>())
                .compose(new ScheduleTranformer<StartOrOverSellBean>());
    }

    public Observable<List<SellingOrderBean>> getTradingList(String api_token) {
        return apiService.getTradingList(api_token)
                .compose(new ResultDisposable<List<SellingOrderBean>>())
                .compose(new ScheduleTranformer<List<SellingOrderBean>>());
    }



    public Observable<Integer> upLodingReceivables(String codeId ,String api_token) {
        return apiService.upLodingReceivables(codeId,api_token)
                .compose(new ResultDisposable<Integer>())
                .compose(new ScheduleTranformer<Integer>());
    }

    public Observable<List<BillBean>> getBillList(String api_token) {
        return apiService.getBillList(api_token)
                .compose(new ResultDisposable<List<BillBean>>())
                .compose(new ScheduleTranformer<List<BillBean>>());
    }
}
