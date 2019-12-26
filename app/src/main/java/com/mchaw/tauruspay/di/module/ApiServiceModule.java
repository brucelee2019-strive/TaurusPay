package com.mchaw.tauruspay.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mchaw.tauruspay.BuildConfig;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.NetworkUtil;
import com.mchaw.tauruspay.http.APIService;
import com.mchaw.tauruspay.http.environment.EnvironmentManager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author : Bruce Lee
 * @description :
 * 提供了Cache,OkHttpClient,Retrofit,Gson,APIService对象的创建.
 * @date : 2019/10/26 0026 11:25
 */
@Module
public class ApiServiceModule {

    @Provides
    @Singleton
    public Cache provideCache() {
        //设置缓存路径
        File cacheDir = new File(MyFrameApplication.getInstance().getExternalCacheDir(), "Cache");
        return new Cache(cacheDir, Constant.HTTP_RESPONSE_CACHE_MAX_SIZE);
    }

    @Provides
    @Singleton
    public OkHttpClient prvideOkHttpClient(Cache cache) {
        //日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        //公共参数
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder();
//                        .header("osVresion", AppUtil.getPhoneOSVersion())
//                        .header("appVersion", AppUtil.getVersionName(MyFrameApplication.getInstance()))
//                        .header("machType", AppUtil.getPhoneModel())
//                        .header("clientType", "A");
//                if (!TextUtils.isEmpty(MyFrameApplication.getInstance().getSessionKey())) {
//                    builder.header("sessionKey", MyFrameApplication.getInstance().getSessionKey());
//                }
                Request request = builder.build();
                return chain.proceed(request);
            }
        };

        //缓存
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtil.isNetworkValid(MyFrameApplication.getInstance())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtil.isNetworkValid(MyFrameApplication.getInstance())) {
                    int maxAge = 0;
                    response = response.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public,max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28;
                    response = response.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
                return response;
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor);
        return builder.build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(EnvironmentManager.newInstance().baseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public APIService provideAPIService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }
}
