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
    /**
     * 构造器
     */
    @Inject
    public TestErrorBiz() {
    }

    /**
     * 网络请求
     * @param context context
     * @param orgid orgid
     * @param storeid storeid
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PrivateErrorListInfo
     */

    public Observable<PrivateErrorListInfo> getPrivateEmployee(Context context, String orgid, String storeid,
                                                               String pageNum, String pageSize) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .getPrivateEmployee(orgid, storeid, pageNum, pageSize)
                .compose(RxHelper.rxSchedulerHelper());
    }

    /**
     * 上传图片
     * @param context context
     * @param file file
     * @return PictureInfo
     */
    public Observable<PictureInfo> upLoadPicture(Context context, MultipartBody.Part file) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .uploadImg(file)
                .compose(RxHelper.rxSchedulerHelper());
    }

    /**
     * 消课----获取已签课的列表
     * @param context context
     * @param orgId orgId
     * @param store store
     * @param employee_id employee_id
     * @return UserOutListInfo
     */
    public Observable<UserOutListInfo> getPrivateCourse(Context context, String orgId, String store,
                                                        String employee_id) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .getPrivateCourse(orgId, store, employee_id)
                .compose(RxHelper.rxSchedulerHelper());
    }
}
