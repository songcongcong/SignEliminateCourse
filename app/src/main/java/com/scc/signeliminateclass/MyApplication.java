package com.scc.signeliminateclass;

import android.app.Application;
import android.os.Handler;

import androidx.multidex.MultiDex;

import com.scc.signeliminateclass.di.compontent.DaggerApplicationCompontent;
import com.scc.signeliminateclass.di.module.ApplicationModule;


/**
 * @author
 * @data 2019/11/8
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    protected static Handler handler;
    protected static int mainThreadId;
    @Override
    public void onCreate() {
        super.onCreate();
        //解决65535
        MultiDex.install(this);
        instance = this;
        handler = new Handler();
        //初始化dagger2
        initApplicationCompontent();
    }
    private void initApplicationCompontent() {
        DaggerApplicationCompontent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }


    public static MyApplication instance() {
        return instance;
    }
}
