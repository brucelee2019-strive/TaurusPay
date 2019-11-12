package com.mchaw.tauruspay.ui.repository;

import com.mchaw.tauruspay.bean.home.TransferAccountsBean;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;
import com.mchaw.tauruspay.bean.recharge.RechargeNextBean;
import com.mchaw.tauruspay.bean.recharge.RechargeSureBean;
import com.mchaw.tauruspay.http.ResultDisposable;
import com.mchaw.tauruspay.http.ScheduleTranformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Bruce Lee
 * @date : 2019/11/6 17:03
 * @description:
 */
public class FundModel extends BaseModel{
    @Inject
    public FundModel() {

    }

    //转账
    public Observable<TransferAccountsBean> getTransferAccountsBean(String api_token, String code, String account, String payname, String amount) {
        return apiService.getTransferAccountsBean(api_token,code,account,payname,amount)
                .compose(new ResultDisposable<TransferAccountsBean>())
                .compose(new ScheduleTranformer<TransferAccountsBean>());
    }

    //请求将汇款到的银行卡信息
    public Observable<RechargeNextBean> getRechargeNextBean(String paymentNum,String api_token) {
        return apiService.getRechargeNextBean(paymentNum,api_token)
                .compose(new ResultDisposable<RechargeNextBean>())
                .compose(new ScheduleTranformer<RechargeNextBean>());
    }

    //确认我已汇款
    public Observable<RechargeSureBean> getRechargeSureBean(String orderId,String api_token) {
        return apiService.getRechargeSureBean(orderId,api_token)
                .compose(new ResultDisposable<RechargeSureBean>())
                .compose(new ScheduleTranformer<RechargeSureBean>());
    }

    //订单列表
    public Observable<List<RechargeBean>> getRechargeList(String api_token) {
        return apiService.getRechargeList(api_token)
                .compose(new ResultDisposable<List<RechargeBean>>())
                .compose(new ScheduleTranformer<List<RechargeBean>>());
    }
}
