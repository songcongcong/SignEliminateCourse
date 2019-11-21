package com.scc.signeliminateclass.di.module;

import android.content.Context;


import com.scc.signeliminateclass.MyApplication;
import com.scc.signeliminateclass.di.PerApp.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * 作者：宋聪聪 on 2019/6/18.
 */
@Module  //提供依赖对象的实例 （module是个容器）
public class ApplicationModule {
    /**
     * myApplication
     */
    private MyApplication myApplication;

    /**
     * 构造方法
     * @param application application
     */
    public ApplicationModule(MyApplication application) {
        this.myApplication = application;
    }

    /**
     * 关键字，标明该方法提供依赖对象
     * @return Context
     */
    @Provides
    @PerApp
    public Context provideApplicatinContext() {
        return myApplication.getApplicationContext();
    }

}
