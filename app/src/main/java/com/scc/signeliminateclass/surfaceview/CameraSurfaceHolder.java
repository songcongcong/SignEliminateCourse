package com.scc.signeliminateclass.surfaceview;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by zhousong on 2016/9/18.
 * 相机界面SurfaceView的Holder类
 */
public class CameraSurfaceHolder {
    /**
     * context
     */
    Context context;
    /**
     * surfaceHolder
     */
    SurfaceHolder surfaceHolder;
    /**
     * surfaceView
     */
    SurfaceView surfaceView;
    /**
     * callback
     */
    SurfaceViewCallback callback = new SurfaceViewCallback();

    /**
     * 设置相机界面SurfaceView的Holder
     *
     * @param context     从相机所在的Activity传入的context
     * @param surfaceView Holder所绑定的响应的SurfaceView
     */
    public void setCameraSurfaceHolder(Context context, SurfaceView surfaceView) {
        this.context = context;
        this.surfaceView = surfaceView;
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(callback);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        callback.setContext(context);
    }

    /**
     * Stop
     */
    public void Stop() {
//        if (surfaceHolder != null) {
//        surfaceHolder.removeCallback(callback);
        callback.surfaceDestroyed(surfaceHolder);
//        }
    }

    /**
     * start
     */
    public void start() {
        callback.surfaceChanged(surfaceHolder, 0, 0, 0);
    }
}


