package com.mchaw.tauruspay.http.environment;

import com.mchaw.tauruspay.BuildConfig;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.PreferencesUtils;

import static com.mchaw.tauruspay.common.Constant.ENVIRONMENT_CUSTOM;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/26 0026 19:49
 */
public class EnvironmentManager {

    private static EnvironmentManager instance = null;

    private String api;
    private String ws;
    private String h5;

    public static EnvironmentManager newInstance(){
        if (instance == null) {
            instance = new EnvironmentManager();
        }
        return instance;
    }

    public EnvironmentManager() {
        String env = BuildConfig.ENVIRONMENT;
        if (env.equals(Environment.PROD)) {
            api = BuildConfig.PROD_API;
            ws = BuildConfig.PROD_WS;
            h5 = BuildConfig.PROD_H5;
        }else{
            String cus = PreferencesUtils.getString(MyFrameApplication.getInstance(), Constant.ENVIRONMENT,env);
            switch (cus) {
                case Environment.DEV:
                    api = BuildConfig.DEV_API;
                    ws = BuildConfig.DEV_WS;
                    h5 = BuildConfig.DEV_H5;
                    break;
                case Environment.TEST:
                    api = BuildConfig.TEST_API;
                    ws = BuildConfig.TEST_WS;
                    h5 = BuildConfig.TEST_H5;
                    break;
                case Environment.PROD:
                    api = BuildConfig.PROD_API;
                    ws = BuildConfig.PROD_WS;
                    h5 = BuildConfig.PROD_H5;
                    break;
                    default:
                        api = String.format("http://%s/",PreferencesUtils.getString(MyFrameApplication.getInstance(), ENVIRONMENT_CUSTOM,""));
                        ws = BuildConfig.DEV_WS;
                        h5 = BuildConfig.DEV_H5;
                        break;
            }
        }
    }

    public String baseUrl() {
        return api;
    }

    public String socketUrl() {
        return ws;
    }

    public String baseUrlAboutUs() {
        return h5 + "#/about";
    }

    public String baseUrlRegister() {
        return h5 + "#/registration";
    }

    public String baseUrlStandard() {
        return h5 + "#/standard";
    }

    public String baseUrlRecharge() {
        return h5 + "#/rechargeProtocol";
    }
}
