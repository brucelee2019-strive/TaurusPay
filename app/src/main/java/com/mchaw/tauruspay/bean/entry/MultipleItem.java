package com.mchaw.tauruspay.bean.entry;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author Bruce Lee
 * @date : 2019/12/31 16:47
 * @description:
 */
public class MultipleItem<T> implements MultiItemEntity {
    public static final int SPAN_SIZE_SINGE = 1;
    public static final int SPAN_SIZE_GRID = 2;

    /**
     * 创建二维码库
     */
    public static final int ER_CODE_FIXED_WX= 10;
    public static final int ER_CODE_FIXED_ALIPAY= 11;
    public static final int ER_CODE_AT_WILL_ALIPAY = 13;
    public static final int ER_CODE_AT_WILL_WX = 14;

    /**
     * 代售二维码库
     */
    public static final int ER_CODE_FOR_SAIL_FIXED_WX= 20;
    public static final int ER_CODE_FOR_SAIL_FIXED_ALIPAY= 21;
    public static final int ER_CODE_OR_SAIL_AT_WILL_ALIPAY = 23;
    public static final int ER_CODE_OR_SAIL_AT_WILL_WX = 24;

    private int itemType;
    private int spanSize;
    private T data;

    public MultipleItem() {
    }

    public MultipleItem(int itemType, T data) {
        this(itemType, SPAN_SIZE_GRID, data);
    }

    public MultipleItem(int itemType, int spanSize, T data) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
