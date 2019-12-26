package com.mchaw.tauruspay.ui.repository;

import com.mchaw.tauruspay.bean.bill.BillTotalBean;
import com.mchaw.tauruspay.bean.bill.TradingBean;
import com.mchaw.tauruspay.bean.home.ReceivablesBean;
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

    public Observable<List<ReceivablesBean>> getTradingList(String api_token) {
        return apiService.getTradingList(api_token)
                .compose(new ResultDisposable<List<ReceivablesBean>>())
                .compose(new ScheduleTranformer<List<ReceivablesBean>>());
    }



    public Observable<TradingBean> upLodingReceivables(String codeId , String api_token) {
        return apiService.upLodingReceivables(codeId,api_token)
                .compose(new ResultDisposable<TradingBean>())
                .compose(new ScheduleTranformer<TradingBean>());
    }

    public Observable<BillTotalBean> getBillList(String api_token, int status, int page) {
        return apiService.getBillList(api_token,status,page)
                .compose(new ResultDisposable<BillTotalBean>())
                .compose(new ScheduleTranformer<BillTotalBean>());
    }
}
