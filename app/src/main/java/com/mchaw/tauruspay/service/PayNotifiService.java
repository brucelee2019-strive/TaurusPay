package com.mchaw.tauruspay.service;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.mchaw.tauruspay.MyFrameApplication;

@SuppressLint("OverrideAbstract")
public class PayNotifiService extends NotificationListenerService {

    private BufferedWriter bw;
    private SimpleDateFormat sdf;
    private MyHandler handler = new MyHandler();
    private String nMessage_text;
    private String nMessage_channelid;
    private String nMessage_groupkey;
    private String data;
    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                //String msgString = (String) msg.obj;
                //Toast.makeText(getApplicationContext(), msgString, Toast.LENGTH_LONG).show();
                toggleNotificationListenerService();
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.i("KEVIN", "-------------------------------------------------------------------------------------------Service started" + "-----");
        data = intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);
    }

    private void toggleNotificationListenerService() {
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(this, PayNotifiService.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(new ComponentName(this, PayNotifiService.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        try {
            Log.i("KEVIN", "-------------------------------------------------------------------------------------------Service Posted");
            if (sbn.getNotification().tickerText != null) {
                SharedPreferences sp = getSharedPreferences("msg", MODE_PRIVATE);
                nMessage_text = sbn.getNotification().tickerText.toString();
                Log.i("KEVIN", "Service is started" + nMessage_text);
//                Log.i("KEVIN", "-------------------------------------------------------------------------------------------Service Posted" + "-----"+nMessage_text);

                nMessage_channelid = Build.VERSION.SDK_INT >= 26 ? sbn.getNotification().getChannelId() : "_old";
                nMessage_groupkey = Build.VERSION.SDK_INT >= 20 ? sbn.getNotification().getGroup() : "_old";

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
                        intent.setAction("com.mchaw.tauruspay.service.PayNotifyService");
                        sendBroadcast(intent);
                    }

                }

            }
        } catch (Exception e) {
            //Toast.makeText(PayNotifiService.this, "不可解析的通知", Toast.LENGTH_SHORT).show();
        }
    }

    private void writeData(String str) {
        try {
//            bw.newLine();
//            bw.write("NOTE");
            bw.newLine();
            bw.write(str);
            bw.newLine();
//            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            FileOutputStream fos = new FileOutputStream(newFile(), true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
        } catch (IOException e) {
            Log.d("KEVIN", "BufferedWriter Initialization error");
        }
        Log.d("KEVIN", "Initialization Successful");
    }

    private File newFile() {
        File fileDir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "ANotification");
        fileDir.mkdir();
        String basePath = Environment.getExternalStorageDirectory() + File.separator + "ANotification" + File.separator + "record.txt";
        return new File(basePath);

    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
//                    Toast.makeText(MyService.this,"Bingo",Toast.LENGTH_SHORT).show();
                    break;
            }
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
}
