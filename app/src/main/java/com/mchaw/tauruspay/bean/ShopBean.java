package com.mchaw.tauruspay.bean;

import java.io.Serializable;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/30 0030 22:12
 */
public class ShopBean implements Serializable {
    /**
     * coverUrl : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522837080403&di=f8dfb4756113dfb391216061ae858342&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171109%2Ff7c9f830ce934d3c81581106290f235f.jpeg
     * availableCount : 3
     * code :
     * price : 2.22
     * name : dfsa
     * id : 1
     * detailUrl : http://www.baidu.com/index.html?id=111111
     * type : 1
     */

    private String coverUrl;
    private int availableCount;
    private String code;
    private double price;
    private String name;
    private long id;
    private String detailUrl;
    private int type;

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
