package com.scc.signeliminateclass.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;

import com.scc.signeliminateclass.utils.ContensUtils;

/**
 * Created by zhousong on 2016/9/18.
 * 相机类，相机的调用
 */
public class FrontCamera {
    /**
     * TAG
     */
    static final String TAG = "Camera";
    /**
     * mCamera
     */
    Camera mCamera;
    /**
     * mCurrentCamIndex
     */
    int mCurrentCamIndex = 0;
    /**
     * previewing
     */
    boolean previewing;
    private Camera camera;

    /**
     * setCamera
     * @param camera camera
     */
    public void setCamera(Camera camera) {
        this.mCamera = camera;
    }

    /**
     * getCurrentCamIndex
     * @return int
     */
    public int getCurrentCamIndex() {
        return this.mCurrentCamIndex;
    }

    /**
     * getPreviewing
     * @return boolean
     */
    public boolean getPreviewing() {
        return this.previewing;
    }

    /**
     * shutterCallback
     */
    Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {

        }
    };
    /**
     * rawPictureCallback
     */
    Camera.PictureCallback rawPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

        }
    };
    /**
     * jpegPictureCallback
     */
    Camera.PictureCallback jpegPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = null;
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Log.i(TAG, "已经获取了bitmap:" + bitmap.toString());
            previewing = false;
            //需要保存可执行下面
/*            new Thread(new Runnable() {
                @Override
                public void run() {
                    String filePath = ImageUtil.getSaveImgePath();
                    File file = new File(filePath);
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(file, true);
                        fos.write(data);
                        ImageUtil.saveImage(file, data, filePath);
                        fos.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();*/
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mCamera.startPreview(); //重新开启预览 ，不然不能继续拍照
            previewing = true;
        }


    };

    /**
     * 初始化相机
     * @return Camera
     */
    public Camera initCamera(Context context) {
        int cameraCount = 0;
        Camera cam = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();

        previewing = true;

        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            //在这里打开的是前置摄像头,可修改打开后置OR前置   Camera.CameraInfo.CAMERA_FACING_FRONT  前置，
            // Camera.CameraInfo.CAMERA_FACING_BACK  后置
            if (ContensUtils.getScrenn(context)) { // 判断屏幕宽度为1200
                camera = setCameraFace(cameraInfo, cam, camIdx, Camera.CameraInfo.CAMERA_FACING_FRONT);
            } else {
                camera = setCameraFace(cameraInfo, cam, camIdx, Camera.CameraInfo.CAMERA_FACING_BACK);
            }
//            if (cameraInfo.facing ==  Camera.CameraInfo.CAMERA_FACING_BACK) {
//                try {
//                    cam = Camera.open(camIdx);
//                    mCurrentCamIndex = camIdx;
////                    Camera.Parameters parameters = cam.getParameters();
////                    parameters.setPictureSize(1920,1080);
////                    Log.e("song","分辨率："+parameters.getPictureSize().width+":"+parameters.getPictureSize().height);
////                    cam.setParameters(parameters);
//                    Log.d("song","后置摄像头");
//                } catch (RuntimeException e) {
//                    Log.e(TAG, "Camera failed to open: " + e.getLocalizedMessage());
//                }
//            }
        }
        return camera;
    }

    /**
     * 设置摄像头的前置或后置，并开启摄像头
     * @param cameraInfo cameraInfo
     * @param cam cam
     * @param camIdx camIdx
     */
    private Camera setCameraFace( Camera.CameraInfo cameraInfo, Camera cam, int camIdx, int screen) {
        if (cameraInfo.facing == screen) {
            try {
                cam = Camera.open(camIdx);
                mCurrentCamIndex = camIdx;
//                    Camera.Parameters parameters = cam.getParameters();
//                    parameters.setPictureSize(1920,1080);
//                    Log.e("song","分辨率："+parameters.getPictureSize().width+":"+parameters.getPictureSize().height);
//                    cam.setParameters(parameters);
            } catch (RuntimeException e) {
                Log.e(TAG, "Camera failed to open: " + e.getLocalizedMessage());
            }
        }
        return cam;
    }

    /**
     * 停止相机
     *
     * @param camera 需要停止的相机对象
     */
    public void StopCamera(Camera camera) {
        if (camera != null) {
            if (previewing) {
                camera.setPreviewCallback(null);
                camera.stopPreview();
//                camera.lock();
                camera.release();
                camera = null;
                previewing = false;
            }
        }
    }

    /**
     * 旋转屏幕后自动适配（若只用到竖的，也可不要）
     * 已经在manifests中让此Activity只能竖屏了
     *
     * @param activity 相机显示在的Activity
     * @param cameraId 相机的ID
     * @param camera   相机对象
     */
    public static void setCameraDisplayOrientation(Activity activity, int cameraId, Camera camera) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
            default:
                break;
        }
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {
            // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    /**
     * 解决平板上的预览图片旋转
     * @param activity activity
     * @return int
     */
    public static int getDisplayRotation(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        switch (rotation) {
            case Surface.ROTATION_0: return 0;
            case Surface.ROTATION_90: return 90;
            case Surface.ROTATION_180: return 180;
            case Surface.ROTATION_270: return 270;
        }
        return 0;
    }

    /**
     * 解决平板上预览图片旋转其他方向
     * @param activity  activity
     * @param cameraId cameraId
     * @param camera camera
     */
    public static void setCameraDisplayOrientationScreen(Activity activity,
                                                   int cameraId, Camera camera) {
        // See android.hardware.Camera.setCameraDisplayOrientation for
        // documentation.
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int degrees = getDisplayRotation(activity);
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }
}
