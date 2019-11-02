package com.mchaw.tauruspay.common.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mchaw.tauruspay.R;

/**
 * @author Bruce Lee
 * @date 2018/3/7 0007
 * @describe Toast工具类
 */

public class ToastUtils {

    /**
     * 显示Short提示
     *
     * @param mContext 上下文
     * @param strRes   提示文字资源
     */
    public static void showShortToast(Context mContext, int strRes) {
        showShortToast(mContext, mContext.getResources().getString(strRes));
    }

    /**
     * 显示Short提示
     *
     * @param mContext 上下文
     * @param msg      提示文字
     */
    public static void showShortToast(Context mContext, String msg) {
        showToast(mContext, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示Long提示
     *
     * @param mContext 上下文
     * @param strRes   提示文字资源
     */
    public static void showLongToast(Context mContext, int strRes) {
        showLongToast(mContext, mContext.getResources().getString(strRes));
    }

    /**
     * 显示Long提示
     *
     * @param mContext 上下文
     * @param msg      提示文字
     */
    public static void showLongToast(Context mContext, String msg) {
        showToast(mContext, msg, Toast.LENGTH_LONG);
    }

    /**
     * 显示提示
     *
     * @param context  上下文
     * @param msg      提示文字
     * @param duration 提示显示时间
     */
    public static void showToast(Context context, String msg, int duration) {
        showToast(context, msg, duration, Gravity.CENTER);
    }

    /**
     * 显示提示
     *
     * @param context  上下文
     * @param msg      提示文字
     * @param duration 提示显示时间
     * @param gravity  提示显示位置
     */
    public static void showToast(Context context, String msg, int duration, int gravity) {
        Toast toast = new Toast(context);
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.view_toast, null);
        TextView tv = v.findViewById(R.id.message);
        tv.setText(msg);
        toast.setDuration(duration);
        toast.setGravity(gravity, 0, 0);
        toast.setView(v);
        toast.show();
    }

    /**
     * 显示带图标提示
     *
     * @param context 上下文
     * @param msg     提示文字
     * @param image   提示图标
     */
    public static void showToastIcon(Context context, String msg, int image) {
        Toast toast = new Toast(context);
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.view_toast_icon, null);
        TextView tv = v.findViewById(R.id.message);
        tv.setText(msg);
        ImageView icon = v.findViewById(R.id.iv_icon);
        icon.setImageResource(image);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(v);
        toast.show();
    }

    /**
     * 显示指定布局的提示
     *
     * @param context  上下文
     * @param layoutId 指定布局
     */
    public static void showToastLayout(Context context, int layoutId) {
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
}
