package com.mchaw.tauruspay.common.widget;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

/**
 * @author : Bruce Lee
 * @date : 2019/11/25 0025 21:37
 * @description :PopWindow兼容N版本
 */
public class Solve7PopupWindow extends PopupWindow {

    public Solve7PopupWindow(View mMenuView, int matchParent, int matchParent1) {
        super(mMenuView, matchParent, matchParent1);
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }
}
