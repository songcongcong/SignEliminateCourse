package com.scc.signeliminateclass.mvp.model;


import android.content.Context;

import com.example.retrofitmvplibrary.retrofit.RetrofitSource;
import com.example.retrofitmvplibrary.retrofit.RxHelper;
import com.scc.signeliminateclass.api.RetrofitApi;
import com.scc.signeliminateclass.bean.MainCheckMessage;


import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * 作者：宋聪聪 on 2019/9/2.
 */

public class MainBiz {
    /**
     * 构造器
     */
    @Inject
    public MainBiz() {
    }

    /**
     * 查询是否有私教
     * @param context context
     * @param orgId orgId
     * @param storeId storeId
     * @return MainCheckMessage
     */
    public Observable<MainCheckMessage> checkIsMessage(Context context, String orgId, String storeId) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .checkIsMessage(orgId, storeId)
                .compose(RxHelper.rxSchedulerHelper());
    }
}
