package com.mchaw.tauruspay.common.util.OneClick;

/**
 * @author Bruce Lee
 * @date : 2019/11/28 16:57
 * @description:
 */
public class AntiShake {
    private static LimitQueue<OneClick> queue = new LimitQueue<>(20);

    public static boolean check(Object o) {
        String flag;
        if (o == null) {
            flag = Thread.currentThread().getStackTrace()[2].getMethodName();
        } else {
            flag = o.toString();
        }
        for (OneClick util : queue.getArrayList()) {
            if (util.getMethodName().equals(flag)) {
                return util.check();
            }
        }
        OneClick clickUtil = new OneClick(flag);
        queue.offer(clickUtil);
        return clickUtil.check();
    }

}
