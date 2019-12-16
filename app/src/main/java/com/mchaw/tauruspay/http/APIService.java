package com.mchaw.tauruspay.http;


import com.mchaw.tauruspay.bean.MainPollingBean;
import com.mchaw.tauruspay.bean.ResultBean;
import com.mchaw.tauruspay.bean.activate.ActivateCodeBean;
import com.mchaw.tauruspay.bean.bill.BillBean;
import com.mchaw.tauruspay.bean.bill.BillTotalBean;
import com.mchaw.tauruspay.bean.bill.TradingBean;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.home.ReceivablesBean;
import com.mchaw.tauruspay.bean.home.StartOrOverSellBean;
import com.mchaw.tauruspay.bean.home.TransferAccountsBean;
import com.mchaw.tauruspay.bean.login.LoginBean;
import com.mchaw.tauruspay.bean.login.LoginOutBean;
import com.mchaw.tauruspay.bean.login.PasswordBean;
import com.mchaw.tauruspay.bean.login.RegisterBean;
import com.mchaw.tauruspay.bean.qrcode.DeleteQRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupCreateBean;
import com.mchaw.tauruspay.bean.qrcode.GroupinfoBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeUrlBean;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;
import com.mchaw.tauruspay.bean.recharge.RechargeNextBean;
import com.mchaw.tauruspay.bean.recharge.RechargeSureBean;
import com.mchaw.tauruspay.bean.updata.UpDataBean;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author : Bruce Lee
 * @description : 接口文件
 * @date : 2019/10/26 0026 09:58
 */
public interface APIService {

    @FormUrlEncoded
    @POST("login")
    Observable<ResultBean<LoginBean>> getLoginBean(@Field("username") String username, @Field("code") String code,@Field("passwd") String passwd);

    @FormUrlEncoded
    @POST("register")
    Observable<ResultBean<RegisterBean>> getRegisterBean(@Field("account") String account,@Field("mobile") String mobile,@Field("code") String code,@Field("passwd") String passwd,@Field("passwd_confirmation") String passwd_confirmation,@Field("payaccount") String payaccount,@Field("activecode") String activecode);

    @FormUrlEncoded
    @POST("password/token")
    Observable<ResultBean<PasswordBean>> getRegisterBean(@Field("token") String token,@Field("code") String code, @Field("passwd") String passwd, @Field("passwd_confirmation") String passwd_confirmation);

    @FormUrlEncoded
    @POST("recharge/transfer")
    Observable<ResultBean<TransferAccountsBean>> getTransferAccountsBean(@Field("api_token") String api_token, @Field("code") String code, @Field("account") String account, @Field("payname") String payname,@Field("amount") String amount);

    @FormUrlEncoded
    @POST("recharge/request/{paymentNum}")
    Observable<ResultBean<RechargeNextBean>> getRechargeNextBean(@Path("paymentNum")String paymentNum,@Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("recharge/complete/{orderId}")
    Observable<ResultBean<RechargeSureBean>> getRechargeSureBean(@Path("orderId")String orderId, @Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("recharge/list")
    Observable<ResultBean<List<RechargeBean>>> getRechargeList(@Field("api_token") String api_token);

    @GET("my/sellinfo")
    Observable<ResultBean<UserBean>> getHomeDataBean(@Query("api_token") String api_token);

    @FormUrlEncoded
    @POST("sell/mygroup")
    Observable<ResultBean<List<GroupinfoBean>>> getQRCodeGroupList(@Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("sell/creategroup")
    Observable<ResultBean<QRCodeGroupCreateBean>> getQRCodeGroupBean(@Field("api_token") String api_token,@Field("account") String account,@Field("nick") String nick,@Field("paytype") String paytype);

    @FormUrlEncoded
    @POST("sell/updateqrcode")
    Observable<ResultBean<QRCodeUrlBean>> getUpLoadingQRCodeUrlBean(@Field("api_token") String api_token,@Field("codeid") String codeid,@Field("url") String url);

    @FormUrlEncoded
    @POST("sell/groupinfo/{groupid}")
    Observable<ResultBean<GroupinfoBean>> getQRCodeStalls(@Path("groupid") String groupid, @Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("sell/deletegroup/{groupid}")
    Observable<ResultBean<DeleteQRCodeGroupBean>> deleteQRCodeGroup(@Path("groupid") String groupid, @Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("recharge/update")
    Observable<ResultBean<List<RechargeBean>>> getRechargeUpdateList(@Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("sell/changestate/{groupid}/{state}")
    Observable<ResultBean<StartOrOverSellBean>> startingOrOverSell(@Path("groupid") String groupid,@Path("state") int state,@Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("sell/myreceivables")
    Observable<ResultBean<List<ReceivablesBean>>> getTradingList(@Field("api_token") String api_token);


    @FormUrlEncoded
    @POST("sell/receivables/{codeid}")
    Observable<ResultBean<TradingBean>> upLodingReceivables(@Path("codeid") String codeid, @Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("my/activecode")
    Observable<ResultBean<List<ActivateCodeBean>>> getActiveCodeList(@Field("api_token") String api_token);

    @FormUrlEncoded
    @POST("my/selllog")
    Observable<ResultBean<BillTotalBean>> getBillList(@Field("api_token") String api_token, @Field("status") int status, @Field("page") int page);

    @FormUrlEncoded
    @POST("my/polling")
    Observable<ResultBean<MainPollingBean>> getMainPollingBean(@Field("api_token") String api_token,@Field("groupid") int groupid);


    @FormUrlEncoded
    @POST("logout")
    Observable<ResultBean<LoginOutBean>> getLoginOutBean(@Field("api_token") String api_token);

    @POST("ts")
    Observable<ResultBean<UpDataBean>> getVersion();
}
