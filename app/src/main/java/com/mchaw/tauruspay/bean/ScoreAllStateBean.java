package com.mchaw.tauruspay.bean;

import java.util.List;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/29 0029 21:37
 */
public class ScoreAllStateBean {
    private List<ScoreMatchBean> finished;
    private List<ScoreMatchBean> live;
    private List<ScoreMatchBean> notStart;

    public List<ScoreMatchBean> getFinished() {
        return finished;
    }

    public void setFinished(List<ScoreMatchBean> finished) {
        this.finished = finished;
    }

    public List<ScoreMatchBean> getLive() {
        return live;
    }

    public void setLive(List<ScoreMatchBean> live) {
        this.live = live;
    }

    public List<ScoreMatchBean> getNotStart() {
        return notStart;
    }

    public void setNotStart(List<ScoreMatchBean> notStart) {
        this.notStart = notStart;
    }
}
