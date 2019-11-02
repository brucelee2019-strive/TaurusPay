package com.mchaw.tauruspay.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author:Bruce Lee
 * @description:Module
 * 就是提供这些依赖的地方，dagger会根据@Provides标记的方法返回依赖对象，
 * 这个AppModule中提供了Application对象的创建.
 * @date:2019/10/26 0026 09:50
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return application;
    }
}
