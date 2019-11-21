package com.scc.signeliminateclass.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author
 * @data 2019/11/8
 */
public class NetWorkUtils {

    /**
     * 判断当前网络是否连接
     * @param context context
     * @return boolean
     */
    public static boolean isNetWorkAvalible(Context context) {
        // 获取网络状态管理器
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        }
        // 建立连接
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return false;
        }
        return true;
    }
}
