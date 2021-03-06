package com.scc.signeliminateclass.mvp.model;


import android.content.Context;


import com.example.retrofitmvplibrary.retrofit.RetrofitSource;
import com.example.retrofitmvplibrary.retrofit.RxHelper;
import com.scc.signeliminateclass.api.RetrofitApi;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.UserPhoneListInfo;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * 作者：宋聪聪 on 2019/9/2.
 */

public class UserErrorBiz {
    /**
     * 构造器
     */
    @Inject
    public UserErrorBiz() {
    }

    /**
     * 网络请求
     * @param context context
     * @param orgId orgId
     * @param storeId storeId
     * @param phone phone
     * @return UserPhoneListInfo
     */

    public Observable<UserPhoneListInfo> getUserPhoneList(Context context, String orgId,
                                                          String storeId, String phone) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .getUserPhoneList(orgId, storeId, phone)
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

//    // 消课--用户识别失败----根据课程查询id
//    public Observable<UserOutFaceErrorListInfo> getMemberByCourse(Context context,
//    String orgId, String store, String classId) {
//        return RetrofitSource.createApi(RetrofitApi.class, context)
//                .getMemberByCourse(orgId, store, classId)
//                .compose(RxHelper.rxSchedulerHelper());
//    }

}
