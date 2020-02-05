package com.mchaw.tauruspay.ui.repository;

import com.mchaw.tauruspay.bean.home.TransferAccountsBean;
import com.mchaw.tauruspay.bean.recharge.AuditBean;
import com.mchaw.tauruspay.bean.recharge.RechargeAuditBean;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;
import com.mchaw.tauruspay.bean.recharge.RechargeNextBean;
import com.mchaw.tauruspay.bean.recharge.RechargeSureBean;
import com.mchaw.tauruspay.bean.withdraw.WithdrawBean;
import com.mchaw.tauruspay.http.ResultDisposable;
import com.mchaw.tauruspay.http.ScheduleTranformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Bruce Lee
 * @date : 2019/11/6 17:03
 * @description:充值转账订阅名目
 */
public class FundModel extends BaseModel{
    @Inject
    public FundModel() {

    }

    //转账给另一个账号
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

    //充值订单列表
    public Observable<List<RechargeBean>> getRechargeList(String api_token) {
        return apiService.getRechargeList(api_token)
                .compose(new ResultDisposable<List<RechargeBean>>())
                .compose(new ScheduleTranformer<List<RechargeBean>>());
    }


    //轮询充值订单列表刷新订单状态
    public Observable<List<RechargeBean>> getRechargeUpdateList(String api_token) {
        return apiService.getRechargeUpdateList(api_token)
                .compose(new ResultDisposable<List<RechargeBean>>())
                .compose(new ScheduleTranformer<List<RechargeBean>>());
    }

    //用于一级代理审核充值订单
    public Observable<AuditBean> getRechargeAudit(String api_token,String order,int status) {
        return apiService.getRechargeAudit(api_token,order,status)
                .compose(new ResultDisposable<AuditBean>())
                .compose(new ScheduleTranformer<AuditBean>());
    }

    //用于一级代理的待审核订单列表
    public Observable<List<RechargeAuditBean>> getRechargeAuditList(String api_token, int type,int page) {
        return apiService.getRechargeAuditList(api_token,type,page)
                .compose(new ResultDisposable<List<RechargeAuditBean>>())
                .compose(new ScheduleTranformer<List<RechargeAuditBean>>());
    }

    //用于用户提现功能
    public Observable<WithdrawBean> getCash(String api_token, String quota, String bank, String bankname, String bankname2, String account, String cardnumber, String phone, String password) {
        return apiService.getCash(api_token,quota,bank,bankname,bankname2,account,cardnumber,phone,password)
                .compose(new ResultDisposable<WithdrawBean>())
                .compose(new ScheduleTranformer<WithdrawBean>());
    }
}
