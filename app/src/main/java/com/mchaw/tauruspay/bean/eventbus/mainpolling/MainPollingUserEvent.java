package com.mchaw.tauruspay.bean.eventbus.mainpolling;

/**
 * @author Bruce Lee
 * @date : 2019/12/3 17:52
 * @description:用户信息轮询通知
 */
public class MainPollingUserEvent {
    private long kucun;
    private long dangrikeshouedu;
    private long dangrikeshoudanshu;
    private long dangrishouyi;
    private long dangriyishouedu;
    private long zaishouzhong;

    public long getZaishouzhong() {
        return zaishouzhong;
    }

    public void setZaishouzhong(long zaishouzhong) {
        this.zaishouzhong = zaishouzhong;
    }

    public long getKucun() {
        return kucun;
    }

    public void setKucun(long kucun) {
        this.kucun = kucun;
    }

    public long getDangrikeshouedu() {
        return dangrikeshouedu;
    }

    public void setDangrikeshouedu(long dangrikeshouedu) {
        this.dangrikeshouedu = dangrikeshouedu;
    }

    public long getDangrikeshoudanshu() {
        return dangrikeshoudanshu;
    }

    public void setDangrikeshoudanshu(long dangrikeshoudanshu) {
        this.dangrikeshoudanshu = dangrikeshoudanshu;
    }

    public long getDangrishouyi() {
        return dangrishouyi;
    }

    public void setDangrishouyi(long dangrishouyi) {
        this.dangrishouyi = dangrishouyi;
    }

    public long getDangriyishouedu() {
        return dangriyishouedu;
    }

    public void setDangriyishouedu(long dangriyishouedu) {
        this.dangriyishouedu = dangriyishouedu;
    }
}
