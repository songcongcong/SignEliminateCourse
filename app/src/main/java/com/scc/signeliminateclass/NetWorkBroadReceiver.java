package com.scc.signeliminateclass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import com.scc.signeliminateclass.base.BaseMvpActivity;
import com.scc.signeliminateclass.eventbus.NetWorkStateEventbus;
import com.scc.signeliminateclass.utils.NetUtil;


import org.greenrobot.eventbus.EventBus;

/**
 * @author
 * @data 2019/11/8
 */
public class NetWorkBroadReceiver extends BroadcastReceiver {

    public NetWorkState evevt = BaseMvpActivity.evevt;
    // 检查手机网络状态是否切换的广播
    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果相等代表网络状态发生了变化，就去通知
        // TODO Auto-generated method stub
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtil.getNetWorkState(context);
            // 接口回调传过去状态的类型
            evevt.onNetWorkChange(netWorkState);
            EventBus.getDefault().post(new NetWorkStateEventbus(netWorkState));
            Log.d("song","通知网络变化："+intent.getAction());
        }
    }

    // 定义接口，实现传值
    public interface NetWorkState{
        void onNetWorkChange(int state);
    }

}
