package com.mchaw.tauruspay.service;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.mchaw.tauruspay.MyFrameApplication;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bruce Lee
 * @date : 2020/2/6 18:23
 * @description:
 */
public class AliAccessibilityService extends AccessibilityService {
    private String nMessage_text;
    private String nMessage_channelid;
    private String nMessage_groupkey;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        try {
            Log.i("KEVIN","onAccessibilityEvent");
            Notification notification = (Notification) event.getParcelableData();
            nMessage_text = notification.tickerText.toString();

            nMessage_channelid = Build.VERSION.SDK_INT >= 26 ? notification.getChannelId() : "_old";
            nMessage_groupkey = Build.VERSION.SDK_INT >= 20 ? notification.getGroup() : "_old";

            boolean b = Pattern.compile(MyFrameApplication.alipayContextA).matcher(nMessage_text).find();
            boolean b2 = Pattern.compile(MyFrameApplication.alipayContextB).matcher(nMessage_text).find();
            boolean b3 = Pattern.compile(MyFrameApplication.wechatContextA).matcher(nMessage_groupkey).find();
            boolean b4 = Pattern.compile(MyFrameApplication.wechatContextB).matcher(nMessage_channelid).find();
            if (!b3 && nMessage_channelid == "_old") {
                b3 = true;
            }
            if (!b4 && nMessage_groupkey == "_old") {
                b4 = true;
            }

            if ((b || b2) && b3 && b4) {
                String money = getNum(nMessage_text);
                if (isNumber(money)) {
                    double vals = roundForNumber(Double.valueOf(money), 2);
                    Log.i("KEVIN", "---------------------------------------------------------------Service is money:" + (int) (vals * 100));
                    Intent intent = new Intent();
                    intent.putExtra("amout", (int) (vals * 100));
                    intent.setAction("com.mchaw.tauruspay.service.AliAccessibilityService");
                    sendBroadcast(intent);
                }
            }
        } catch (Exception e) {
            //Toast.makeText(PayNotifiService.this, "不可解析的通知", Toast.LENGTH_SHORT).show();
        }
    }

    private String getNum(String str) {
        // 控制正则表达式的匹配行为的参数(小数)
        Pattern p = Pattern.compile("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])");
        //Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例.
        Matcher m = p.matcher(str);
        //m.find用来判断该字符串中是否含有与"(\\d+\\.\\d+)"相匹配的子串
        String str2 = "";
        while (m.find()) {
            if (m.group(1) == null) {
                str2 = m.group(2) == null ? "" : m.group(2);
            }
            if (m.group(2) == null) {
                str2 = m.group(1) == null ? "" : m.group(1);
            }
        }
        return str2;
    }

    private static double roundForNumber(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
