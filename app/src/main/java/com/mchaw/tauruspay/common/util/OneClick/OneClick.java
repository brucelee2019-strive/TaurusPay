package com.mchaw.tauruspay.common.util.OneClick;

import java.util.Calendar;

/**
 * @author Bruce Lee
 * @date : 2019/11/28 16:54
 * @description:
 */
public class OneClick {
    private String methodName;
    private static final int CLICK_DELAY_TIME = 1500;
    private long lastClickTime = 0;

    public OneClick(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean check() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            return false;
        } else {
            return true;
        }
    }

}
