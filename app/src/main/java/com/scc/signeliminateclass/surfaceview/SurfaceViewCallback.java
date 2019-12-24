package com.scc.signeliminateclass.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by zhousong on 2016/9/19.
 * 相机界面SurfaceView的回调类
 */
public final class SurfaceViewCallback implements SurfaceHolder.Callback, Camera.PreviewCallback {
    /**
     * context
     */
    Context context;
    /**
     * TAG
     */
    static final String TAG = "Camera";
    /**
     * mFrontCamera
     */
    FrontCamera mFrontCamera = new FrontCamera();
    /**
     * previewing
     */
    boolean previewing = mFrontCamera.getPreviewing();
    /**
     * mCamera
     */
    Camera mCamera;
    /**
     * mFaceTask
     */
    FaceTask mFaceTask;

    /**
     * setContext
     * @param context context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * surfaceChanged
     * @param arg0 arg0
     * @param arg1 arg1
     * @param arg2 arg2
     * @param arg3 arg3
     */
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        if (previewing) {
            mCamera.stopPreview();
            Log.i(TAG, "停止预览");
        }

        try {
            mCamera.setPreviewDisplay(arg0);
            mCamera.startPreview();
            mCamera.setPreviewCallback(this);
            Log.i(TAG, "开始预览");
            //调用旋转屏幕时自适应
            //setCameraDisplayOrientation(MainActivity.this, mCurrentCamIndex, mCamera);
        } catch (Exception e) {
        }
    }

    /**
     *
     * @param holder holder
     */
    public void surfaceCreated(SurfaceHolder holder) {
        //初始化前置摄像头
        mFrontCamera.setCamera(mCamera);
        mCamera = mFrontCamera.initCamera(context);
        if (mCamera != null) {
            mCamera.setPreviewCallback(this);
            //适配竖排固定角度
            Log.i(TAG, "context: " + context.toString());
            Log.i(TAG, "mFrontCamera: " + mFrontCamera.toString());
            Log.i(TAG, "mCamera: " + mCamera.toString());
            FrontCamera.setCameraDisplayOrientation((Activity) context, mFrontCamera.getCurrentCamIndex(), mCamera);
//            FrontCamera.setCameraDisplayOrientationScreen((Activity) context, mFrontCamera.getCurrentCamIndex(), mCamera);
        }


    }

    /**
     * surfaceDestroyed
     * @param holder holder
     */
    public void surfaceDestroyed(SurfaceHolder holder) {
        holder.removeCallback(this);
        mFrontCamera.StopCamera(mCamera);
    }

    /**
     * surfaceDestroyedView
     * @param holder holder
     */
    public void surfaceDestroyedView(SurfaceHolder holder) {
//        holder.removeCallback(this);
        mCamera.stopPreview();
    }

    /**
     * 相机实时数据的回调
     *
     * @param data   相机获取的数据，格式是YUV
     * @param camera 相应相机的对象
     */
    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if (mFaceTask != null) {
            switch (mFaceTask.getStatus()) {
                case RUNNING:
                    return;
                case PENDING:
                    mFaceTask.cancel(false);
                    break;
                default:
                    break;
            }

        }
        mFaceTask = new FaceTask(data, camera);
        mFaceTask.execute((Void) null);
        //Log.i(TAG, "onPreviewFrame: 启动了Task");

    }

}