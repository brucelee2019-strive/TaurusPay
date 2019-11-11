package com.mchaw.tauruspay.ui.repository;

import com.mchaw.tauruspay.bean.home.TransferAccountsBean;
import com.mchaw.tauruspay.bean.recharge.RechargeNextBean;
import com.mchaw.tauruspay.http.ResultDisposable;
import com.mchaw.tauruspay.http.ScheduleTranformer;

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

    //请求充值到的银行卡信息
    public Observable<RechargeNextBean> getRechargeNextBean(String api_token) {
        return apiService.getRechargeNextBean(api_token)
                .compose(new ResultDisposable<RechargeNextBean>())
                .compose(new ScheduleTranformer<RechargeNextBean>());
    }
}
