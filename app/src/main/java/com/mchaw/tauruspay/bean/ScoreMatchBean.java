package com.mchaw.tauruspay.bean;

import android.text.TextUtils;

import com.mchaw.tauruspay.common.Constant;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/29 0029 21:38
 */
public class ScoreMatchBean {
    public int getIsFir() {
        return isFir;
    }

    public void setIsFir(int isFir) {
        this.isFir = isFir;
    }

    /**
     * addTime : 0
     * day : string
     * halfHomeScore : 0
     * halfVisitScore : 0
     * homeCorner : 0
     * homeName : string
     * homeRed : 0
     * homeScore : 0
     * homeYellow : 0
     * id : 0
     * informationNum : 0
     * isFocus : 0
     * leisuId : 0
     * liveType : 0
     * openTime : 2018-11-30T03:49:07.674Z
     * startTime : 2018-11-30T03:49:07.674Z
     * status : 0
     * teeTime : string
     * typeName : string
     * visitCorner : 0
     * visitName : string
     * visitRed : 0
     * visitScore : 0
     * visitYellow : 0
     */
    private int isFir;
    private int addTime;
    private String day;
    private int halfHomeScore;
    private int halfVisitScore;
    private int homeCorner;
    private String homeName;
    private int homeRed;
    private int homeScore;
    private int homeYellow;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLeisuId() {
        return leisuId;
    }

    public void setLeisuId(long leisuId) {
        this.leisuId = leisuId;
    }

    private int informationNum;
    private int isFocus;
    private long leisuId;
    private int liveType;
    private String openTime;
    private String startTime;
    private int status;
    private String teeTime;
    private String typeName;
    private int visitCorner;
    private String visitName;
    private int visitRed;
    private int visitScore;
    private int visitYellow;
    private int matchState;

    private String homeRank;
    private String awayRank;
    private String issue;

    public String getHomeRank() {
        return TextUtils.isEmpty(homeRank) || (matchState != Constant.SCORE_MATCH_STATUE_DNS) ? "" : "[" + homeRank + "]";
    }

    public void setHomeRank(String homeRank) {
        this.homeRank = homeRank;
    }

    public String getAwayRank() {
        return TextUtils.isEmpty(awayRank) || (matchState != Constant.SCORE_MATCH_STATUE_DNS) ? "" : "[" + awayRank + "]";
    }

    public void setAwayRank(String awayRank) {
        this.awayRank = awayRank;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public int getLiveAnimation() {
        return liveAnimation;
    }

    public void setLiveAnimation(int liveAnimation) {
        this.liveAnimation = liveAnimation;
    }

    private int liveAnimation;

    public int getMatchState() {
        return matchState;
    }

    public void setMatchState(int matchState) {
        this.matchState = matchState;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHalfHomeScore() {
        return halfHomeScore;
    }

    public void setHalfHomeScore(int halfHomeScore) {
        this.halfHomeScore = halfHomeScore;
    }

    public int getHalfVisitScore() {
        return halfVisitScore;
    }

    public void setHalfVisitScore(int halfVisitScore) {
        this.halfVisitScore = halfVisitScore;
    }

    public int getHomeCorner() {
        return homeCorner;
    }

    public void setHomeCorner(int homeCorner) {
        this.homeCorner = homeCorner;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public int getHomeRed() {
        return homeRed;
    }

    public void setHomeRed(int homeRed) {
        this.homeRed = homeRed;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getHomeYellow() {
        return homeYellow;
    }

    public void setHomeYellow(int homeYellow) {
        this.homeYellow = homeYellow;
    }


    public int getInformationNum() {
        return informationNum;
    }

    public void setInformationNum(int informationNum) {
        this.informationNum = informationNum;
    }

    public int getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(int isFocus) {
        this.isFocus = isFocus;
    }


    public int getLiveType() {
        return liveType;
    }

    public void setLiveType(int liveType) {
        this.liveType = liveType;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTeeTime() {
        return teeTime;
    }

    public void setTeeTime(String teeTime) {
        this.teeTime = teeTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getVisitCorner() {
        return visitCorner;
    }

    public void setVisitCorner(int visitCorner) {
        this.visitCorner = visitCorner;
    }

    public String getVisitName() {
        return visitName;
    }

    public void setVisitName(String visitName) {
        this.visitName = visitName;
    }

    public int getVisitRed() {
        return visitRed;
    }

    public void setVisitRed(int visitRed) {
        this.visitRed = visitRed;
    }

    public int getVisitScore() {
        return visitScore;
    }

    public void setVisitScore(int visitScore) {
        this.visitScore = visitScore;
    }

    public int getVisitYellow() {
        return visitYellow;
    }

    public void setVisitYellow(int visitYellow) {
        this.visitYellow = visitYellow;
    }
}
