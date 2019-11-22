package com.scc.signeliminateclass.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;


import com.scc.signeliminateclass.MyApplication;
import com.scc.signeliminateclass.R;

import java.io.File;

/**
 * Created by Horrarndoo on 2017/8/31.
 * <p>
 * App工具类
 */
public class AppUtils {

    /**
     * 参数orgid
     */
    public static final String ORG_ID = "234892mid";
    /**
     * 参数storeid
     */
    public static final String STORE_ID = "3";

    /**
     * 获取上下文对象
     *
     * @return 上下文对象
     */
    public static Context getContext() {
        return MyApplication.instance();
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return MyApplication.getHandler();
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return MyApplication.getMainThreadId();
    }

    /**
     * 获取版本名称
     *
     * @param context context
     * @return versionName
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 获取版本号
     *
     * @param context context
     * @return versioncode
     */
    public static int getAppVersionCode(Context context) {
        int versioncode = -1;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versioncode;
    }

    /**
     * 获取IMEL
     *
     * @param context context
     * @return id
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context
                .TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return tm.getDeviceId();
        } else {
            return null;
        }
    }

    /**
     * 显示软键盘
     *
     * @param et et
     */
    public static void openSoftInput(EditText et) {
        InputMethodManager inputMethodManager = (InputMethodManager) et.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(et, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏软键盘
     *
     * @param et et
     */
    public static void hideSoftInput(EditText et) {
        InputMethodManager inputMethodManager = (InputMethodManager) et.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), InputMethodManager
                .HIDE_NOT_ALWAYS);
    }

    /**
     * 获取SD卡路径
     *
     * @return 如果sd卡不存在则返回null
     */
    public static File getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment
                .MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory(); //获取跟目录
        }
        return sdDir;
    }

    /**
     * 安装文件
     *
     * @param context context
     * @param data    data
     */
    public static void promptInstall(Context context, Uri data) {
        Intent promptInstall = new Intent(Intent.ACTION_VIEW)
                .setDataAndType(data, "application/vnd.android.package-archive");
        // FLAG_ACTIVITY_NEW_TASK 可以保证安装成功时可以正常打开 app
        promptInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(promptInstall);
    }

    /**
     * copy2clipboard
     *
     * @param context context
     * @param text    text
     */
    public static void copy2clipboard(Context context, String text) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context
                .CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("clip", text);
        cm.setPrimaryClip(clip);
    }

    /**
     * 判断是否运行在主线程
     *
     * @return true：当前线程运行在主线程
     * fasle：当前线程没有运行在主线程
     */
    public static boolean isRunOnUIThread() {
        // 获取当前线程id, 如果当前线程id和主线程id相同, 那么当前就是主线程
        int myTid = android.os.Process.myTid();
        if (myTid == getMainThreadId()) {
            return true;
        }
        return false;
    }

    /**
     * 运行在主线程
     *
     * @param r 运行的Runnable对象
     */
    public static void runOnUIThread(Runnable r) {
        if (isRunOnUIThread()) {
            // 已经是主线程, 直接运行
            r.run();
        } else {
            // 如果是子线程, 借助handler让其运行在主线程
            getHandler().post(r);
        }
    }

    /**
     * 为图片添加水印
     *
     * @param context   context
     * @param mbitmap   mbitmap
     * @param mNickName mNickName
     * @return file
     */
    public static File drawTextPicture(Context context, Bitmap mbitmap, String mNickName) {
        if (!TextUtils.isEmpty(mNickName)) {
            Bitmap bitmap = ImageUtil.drawTextToCenter(context, mbitmap, mNickName,
                    150, context.getResources().getColor(R.color.img_color));
            Bitmap bitmap1 = ImageUtil.drawTextToLeftBottom(context, bitmap, TimeUtil.getPictureCurrentTime(), 130,
                    context.getResources().getColor(R.color.img_color), 500, 300);
            File file = FileUtil.getFile(bitmap1);
            return file;
        }
        return null;
    }

    /**
     * 为图片添加水印
     *
     * @param context   context
     * @param mbitmap   mbitmap
     * @param mNickName mNickName
     * @return file
     */
    public static File drawTextFaceSuccPicture(Context context, Bitmap mbitmap, String mNickName) {
        if (!TextUtils.isEmpty(mNickName)) {
            Bitmap bitmap = ImageUtil.drawTextToCenter(context, mbitmap, mNickName,
                    50, context.getResources().getColor(R.color.img_color));
            Bitmap bitmap1 = ImageUtil.drawTextToLeftBottom(context, bitmap, TimeUtil.getPictureCurrentTime(), 50,
                    context.getResources().getColor(R.color.img_color), 150, 160);
            File file = FileUtil.getFile(bitmap1);
            return file;
        }
        return null;
    }

    /**
     * 退出当前页面
     * @param activity activity
     */
    public static void exit(Activity activity) {
            //用户退出处理
        activity.finish();
        System.exit(0);
    }
}
