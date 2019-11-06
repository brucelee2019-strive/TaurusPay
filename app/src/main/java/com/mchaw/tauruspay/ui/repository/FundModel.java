package com.mchaw.tauruspay.ui.repository;

import com.mchaw.tauruspay.bean.home.TransferAccountsBean;
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

    public Observable<TransferAccountsBean> getTransferAccountsBean(String api_token, String code, String account, String payname, String amount) {
        return apiService.getTransferAccountsBean(api_token,code,account,payname,amount)
                .compose(new ResultDisposable<TransferAccountsBean>())
                .compose(new ScheduleTranformer<TransferAccountsBean>());
    }
}
