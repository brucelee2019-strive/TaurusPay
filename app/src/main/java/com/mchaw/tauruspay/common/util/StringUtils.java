package com.mchaw.tauruspay.common.util;

import java.text.DecimalFormat;

/**
 * @author Bruce Lee
 * @date : 2019/11/18 16:25
 * @description:
 */
public class StringUtils {
    public static String fenToYuan(long f){
        String fen = String.valueOf(f);
        if( fen.length() < 3 ){
            return fen;
        }
        String strm = fen.substring(0,fen.length()-2);   //截掉后两位
        String strh = fen.substring(fen.length() -2,fen.length());   //截取后两位
        String yuan = strm+"."+strh;
        return yuan;
    }

    public static String fenToYuan(String fen){
        if( fen.length() < 3 ){
            return fen;
        }
        String strm = fen.substring(0,fen.length()-2);   //截掉后两位
        String strh = fen.substring(fen.length() -2,fen.length());   //截取后两位
        String yuan = strm+"."+strh;
        return yuan;
    }

    public static String fenToYuanInt(int f){
        String fen = String.valueOf(f);
        if( fen.length() < 3 ){
            return fen;
        }
        String strm = fen.substring(0,fen.length()-2);   //截掉后两位
        return strm;
    }

    public static String earningsYuan(int f){
        DecimalFormat df=new DecimalFormat("0.00");
        String str = df.format((float)f/100000*5);
        return str;
    }
}
