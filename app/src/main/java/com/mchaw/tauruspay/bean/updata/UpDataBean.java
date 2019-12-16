package com.mchaw.tauruspay.bean.updata;

/**
 * @author Bruce Lee
 * @date : 2019/12/16 11:45
 * @description:
 */
public class UpDataBean {

    /**
     * versionName : 1.0.0
     * versionCode : 1
     * description : 1.支持Android M N O P Q\n2.支持自定义下载过程\n3.支持 设备>=Android M 动态权限的申请\n4.支持通知栏进度条展示\n5.支持文字国际化
     * apkSize : 3.0
     * download : www.baidu.com
     */

    private String versionName;
    private int versionCode;
    private String description;
    private String apkSize;
    private String download;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApkSize() {
        return apkSize;
    }

    public void setApkSize(String apkSize) {
        this.apkSize = apkSize;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }
}
