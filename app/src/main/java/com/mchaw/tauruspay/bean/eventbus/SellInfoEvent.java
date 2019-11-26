package com.mchaw.tauruspay.bean.eventbus;

/**
 * @author Bruce Lee
 * @date : 2019/11/26 16:16
 * @description:
 */
public class SellInfoEvent {
    private long kucun;
    private long dangrikeshouedu;
    private long dangrikeshoudanshu;
    private long dangrishouyi;
    private long dangriyishouedu;

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
