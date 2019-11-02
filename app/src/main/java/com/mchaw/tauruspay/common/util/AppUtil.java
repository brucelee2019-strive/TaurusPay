package com.mchaw.tauruspay.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.content.FileProvider;

import com.mchaw.tauruspay.MyFrameApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/26 0026 14:11
 */
public class AppUtil {
    /**
     * 设备厂商
     *
     * @return
     */
    public static String getPhoneBrand() {
        return Build.BOARD + "  " + Build.MANUFACTURER;
    }

    /**
     * 设备名称
     *
     * @return
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }


    /**
     * 获取系统OS
     *
     * @return
     */
    public static String getPhoneOSVersion() {
        return Build.VERSION.RELEASE;
    }


    /**
     * 获取应用名称
     *
     * @param context
     * @return
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "unKnown";
        }
    }

    /**
     * 获取应用版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取应用版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = -1;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取当前应用包名
     *
     * @return
     */
    public static String getPacketName() {
        try {
            PackageManager manager = MyFrameApplication.getInstance().getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    MyFrameApplication.getInstance().getPackageName(), 0);
            return info.packageName;
        } catch (Exception e) {
            return "com.runx.android";
        }
    }


    /**
     * 安装应用
     *
     * @param context
     * @param apk
     * @return
     */
    public static boolean installApk(Context context, String apk) {
        File file = new File(apk);
        if (!file.exists() || file.isDirectory()) {
            return false;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri contentUri = FileProvider.getUriForFile(context, "com.runx.android.fileprovider", file);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        return true;
    }


    /**
     * 获取当前手机上的应用商店数量
     *
     * @param context
     * @return
     */
    public static ArrayList<String> getInstalledMarketPkgs(Context context) {
        ArrayList<String> pkgs = new ArrayList<>();
        if (context == null) {
            return pkgs;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("market://details?id="));
        PackageManager pm = context.getPackageManager();
        // 通过queryIntentActivities获取ResolveInfo对象
        List<ResolveInfo> infos = pm.queryIntentActivities(intent,
                0);
        if (infos == null || infos.size() == 0) {
            return pkgs;
        }
        int size = infos.size();
        for (int i = 0; i < size; i++) {
            String pkgName = "";
            try {
                ActivityInfo activityInfo = infos.get(i).activityInfo;
                pkgName = activityInfo.packageName;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(pkgName)) {
                pkgs.add(pkgName);
            }
        }
        return pkgs;
    }


    /**
     * 打开指定应用市场
     *
     * @param mContext
     * @param marketId
     */
    public static void openMarket(Context mContext, String marketId) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        /**
         * apk包名
         */
        Uri uri = Uri.parse("market://details?id=" + getPacketName());
        intent.setData(uri);
        /**
         * 应用市场包名
         */
        intent.setPackage(marketId);
        mContext.startActivity(intent);
    }
}
