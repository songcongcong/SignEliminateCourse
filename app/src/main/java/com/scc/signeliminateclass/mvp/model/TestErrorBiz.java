package com.scc.signeliminateclass.mvp.model;


import android.content.Context;

import com.example.retrofitmvplibrary.retrofit.RetrofitSource;
import com.example.retrofitmvplibrary.retrofit.RxHelper;
import com.scc.signeliminateclass.api.RetrofitApi;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.PrivateErrorListInfo;
import com.scc.signeliminateclass.bean.UserOutListInfo;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * 作者：宋聪聪 on 2019/9/2.
 */

public class TestErrorBiz {
    @Inject
    public TestErrorBiz() {
    }

    // 网络请求

    public Observable<PrivateErrorListInfo> getPrivateEmployee(Context context, String orgid, String storeid) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .getPrivateEmployee(orgid, storeid)
                .compose(RxHelper.rxSchedulerHelper());
    }

    // 上传图片
    public Observable<PictureInfo> upLoadPicture(Context context, MultipartBody.Part file) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .uploadImg(file)
                .compose(RxHelper.rxSchedulerHelper());
    }

    // 消课----获取已签课的列表
    public Observable<UserOutListInfo> getPrivateCourse(Context context, String orgId, String store,
                                                        String employee_id) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .getPrivateCourse(orgId, store, employee_id)
                .compose(RxHelper.rxSchedulerHelper());
    }
}
