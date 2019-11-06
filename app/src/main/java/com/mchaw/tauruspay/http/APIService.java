package com.mchaw.tauruspay.http;


import com.mchaw.tauruspay.bean.Movie;
import com.mchaw.tauruspay.bean.ResultBean;
import com.mchaw.tauruspay.bean.ScoreAllStateBean;
import com.mchaw.tauruspay.bean.ShopBean;
import com.mchaw.tauruspay.bean.home.TransferAccountsBean;
import com.mchaw.tauruspay.bean.login.LoginBean;
import com.mchaw.tauruspay.bean.login.PasswordBean;
import com.mchaw.tauruspay.bean.login.RegisterBean;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author : Bruce Lee
 * @description : 接口文件
 * @date : 2019/10/26 0026 09:58
 */
public interface APIService {

    /**
     * 获取商城列表
     *
     * @param pageSize    每页多少条数据
     * @param currentPage 第几页
     * @return
     */
    @GET("mall/mall/commodity/list")
    Observable<ResultBean<List<ShopBean>>> getShopList(@Query("pageSize") int pageSize, @Query("currentPage") int currentPage);

    @FormUrlEncoded
    @POST("mat/match/live/query/score")
    Observable<ResultBean<ScoreAllStateBean>> getScoreList(@Field("search") String search, @Field("startDate") String startDate, @Field("searchType") int searchType);

    //获取豆瓣Top250 榜单
    @FormUrlEncoded
    @POST("satinApi")
    Observable<ResultBean<List<Movie>>> getTop250(@Field("type") int type, @Field("page")int page);

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
}
