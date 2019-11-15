package com.mchaw.tauruspay.bean;

/**
 * @author Bruce Lee
 * @date : 2019/11/14 16:12
 * @description:
 */
public class ALiYunCodeBean {

    /**
     * ver_test : 2
     * status : 1
     * data : {"raw_text":"https://qr.alipay.com/fkx00311b8vcbjuespulx8d","raw_type":"QR-Code"}
     * msg : success
     */

    private int ver_test;
    private int status;
    private DataBean data;
    private String msg;

    public int getVer_test() {
        return ver_test;
    }

    public void setVer_test(int ver_test) {
        this.ver_test = ver_test;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * raw_text : https://qr.alipay.com/fkx00311b8vcbjuespulx8d
         * raw_type : QR-Code
         */

        private String raw_text;
        private String raw_type;

        public String getRaw_text() {
            return raw_text;
        }

        public void setRaw_text(String raw_text) {
            this.raw_text = raw_text;
        }

        public String getRaw_type() {
            return raw_type;
        }

        public void setRaw_type(String raw_type) {
            this.raw_type = raw_type;
        }
    }
}
