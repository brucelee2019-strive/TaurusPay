package com.mchaw.tauruspay.common.util;


import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author Bruce Lee
 * @date : 2019/12/17 14:34
 * @description:
 */
public class TimeUtils {
    /**
     * 分转换为分秒格式
     *
     * @param duration
     * @return
     */
    public static String timeParse(int duration) {
        String time = "";
        long minute = duration / 60;
        long second = duration % 60;
        if (minute < 10) {
            time += "0";
        }
        time += minute + ":";
        if (second < 10) {
            time += "0";
        }
        time += second;
        return time;
    }

}
