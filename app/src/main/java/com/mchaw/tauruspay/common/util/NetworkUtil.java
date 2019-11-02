package com.mchaw.tauruspay.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/26 0026 19:22
 */
public class NetworkUtil {

    /**
     * 判断网络是否有效
     *
     * @return
     */
    public static boolean isNetworkValid(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
