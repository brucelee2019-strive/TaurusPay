package com.mchaw.tauruspay.base.fragment.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.mchaw.tauruspay.base.activity.BaseActivity;
import com.mchaw.tauruspay.base.fragment.BaseFragment;
import com.mchaw.tauruspay.common.util.ToastUtils;

/**
 * @author : Bruce Lee
 * @description : implements FragmentStartHelperView
 * @date : 2019/10/28 0028 07:59
 */
public class FragmentStartHelper /*implements FragmentStartHelperView*/ {

    public static final String INTENT_FRAGMENT = "INTENT_FRAGMENT";

    /**
     * 启动BaseActivity
     *
     * @param context
     * @param baseFragment
     */
    public static void startFragment(Context context, BaseFragment baseFragment) {
        startFragmentForResult(context, baseFragment, -1);
    }

    public static void startFragmentForResult(Context context, BaseFragment baseFragment, int requestCode) {
        startFragmentForResult(context, baseFragment, BaseActivity.class, null, requestCode);
    }

    /**
     * 启动CustomActivity
     *
     * @param context
     */

    public static void startActivity(Context context, Class<?> cls, Bundle bundle) {
        startActivityForResult(context, cls, bundle, -1);
    }

    public static void startActivityForResult(Context context, Class<?> cls, Bundle bundle, int requestCode) {
        startFragmentForResult(context, null, cls, bundle, requestCode);
    }

    /**
     * @param context
     * @param baseFragment
     * @param requestCode
     */
    public static void startFragmentForResult(Context context, BaseFragment baseFragment, Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, cls);
        if (context == context.getApplicationContext()) {
            requestCode = -1;
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (baseFragment != null) {
            intent.putExtra(INTENT_FRAGMENT,baseFragment.getClass().getName());
            if (baseFragment.getArguments() != null) {
                intent.putExtras(baseFragment.getArguments());
            }
        } else {
            if (bundle != null) {
                intent.putExtras(bundle);
            }
        }
        try {
            if (requestCode == -1) {
                context.startActivity(intent);
            } else if (context instanceof FragmentActivity) {
                ((FragmentActivity)context).startActivityForResult(intent,requestCode);
            }
        } catch (Exception e){
            if (context != context.getApplicationContext()) {
                startFragmentForResult(context.getApplicationContext(),baseFragment,cls,bundle,requestCode);
            } else {
                ToastUtils.showShortToast(context,e.getMessage());
            }
        }
    }
}
