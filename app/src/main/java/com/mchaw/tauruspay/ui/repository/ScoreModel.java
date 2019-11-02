package com.mchaw.tauruspay.ui.repository;

import com.mchaw.tauruspay.http.ResultDisposable;
import com.mchaw.tauruspay.http.ScheduleTranformer;
import com.mchaw.tauruspay.bean.Movie;
import com.mchaw.tauruspay.bean.ScoreAllStateBean;
import com.mchaw.tauruspay.bean.ShopBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/30 0030 10:45
 */
public class ScoreModel extends BaseModel {
    @Inject
    public ScoreModel() {

    }

    /**
     * 比分页
     *
     * @return
     */
    public Observable<ScoreAllStateBean> getScoreList(String search, String startDate, int searchType) {
        return apiService.getScoreList(search,startDate,searchType)
                .compose(new ResultDisposable<ScoreAllStateBean>())
                .compose(new ScheduleTranformer<ScoreAllStateBean>());
    }

    public Observable<List<ShopBean>> getShopList(int pageSize,int currentPage){
        return apiService.getShopList(pageSize,currentPage)
                .compose(new ResultDisposable<List<ShopBean>>())
                .compose(new ScheduleTranformer<List<ShopBean>>());
    }

    public Observable<List<Movie>> getTop250(int type, int page){
        return apiService.getTop250(type,page)
                .compose(new ResultDisposable<List<Movie>>())
                .compose(new ScheduleTranformer<List<Movie>>());
    }
}
