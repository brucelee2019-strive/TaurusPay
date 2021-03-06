package com.mchaw.tauruspay.common.util;

import android.text.TextUtils;

import com.mchaw.tauruspay.MyFrameApplication;

import java.text.DecimalFormat;

/**
 * @author Bruce Lee
 * @date : 2019/11/18 16:25
 * @description:
 */
public class StringUtils {
    public static String fenToYuan(int f){
        String fen = String.valueOf(f);
        if(fen.length() < 2){
            return "0.0"+fen;
        }
        if( fen.length() < 3 ){
            return "0."+fen;
        }
        String strm = fen.substring(0,fen.length()-2);   //截掉后两位
        String strh = fen.substring(fen.length() -2,fen.length());   //截取后两位
        String yuan = strm+"."+strh;
        return yuan;
    }

    public static String fenToYuan(long f){
        String fen = String.valueOf(f);
        if(fen.length() < 2){
            return "0.0"+fen;
        }
        if( fen.length() < 3 ){
            return "0."+fen;
        }
        String strm = fen.substring(0,fen.length()-2);   //截掉后两位
        String strh = fen.substring(fen.length() -2,fen.length());   //截取后两位
        String yuan = strm+"."+strh;
        return yuan;
    }

    public static String fenToYuan(String fen){
        if(TextUtils.isEmpty(fen)){
            return "0.00";
        }
        if(fen.length() < 2){
            return "0.0"+fen;
        }
        if( fen.length() < 3 ){
            return "0."+fen;
        }
        String strm = fen.substring(0,fen.length()-2);   //截掉后两位
        String strh = fen.substring(fen.length() -2,fen.length());   //截取后两位
        String yuan = strm+"."+strh;
        return yuan;
    }

    /**
     * 设置一级代理 返点规则 分转万元
     * @param fen
     * @return
     */
    public static String fenToWYuan(String fen){
        if( fen.length() < 7 ){
            return "0";
        }
        String strm = fen.substring(0,fen.length()-6);   //截掉后六位
        return strm;
    }

    public static String fenToYuanInt(int f){
        String fen = String.valueOf(f);
        if(fen.length() < 2){
            return "0.0"+fen;
        }
        if( fen.length() < 3 ){
            return "0."+fen;
        }
        String strm = fen.substring(0,fen.length()-2);   //截掉后两位
        return strm;
    }

    public static String earningsYuan(int f){
        DecimalFormat df=new DecimalFormat("0.00");
        String str = "";
        if(MyFrameApplication.userType == 2 || MyFrameApplication.userType == 3){
            str = df.format(Math.ceil(f * MyFrameApplication.userRate / 1000) / 100);
        }else {
            str = df.format(Math.ceil(f * 6 / 1000) / 100);
        }
        return str;
    }
}
