package com.scc.signeliminateclass.mvp.model;


import android.content.Context;
import android.service.autofill.SaveInfo;

import com.example.retrofitmvplibrary.retrofit.RetrofitSource;
import com.example.retrofitmvplibrary.retrofit.RxHelper;
import com.scc.signeliminateclass.api.RetrofitApi;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.PrivateErrorListInfo;
import com.scc.signeliminateclass.bean.SaveMessageInfo;
import com.scc.signeliminateclass.bean.TestFacePassInfo;
import com.scc.signeliminateclass.bean.TestUserFacePassInfo;
import com.scc.signeliminateclass.bean.UserOutFaceErrorListInfo;
import com.scc.signeliminateclass.bean.UserOutFaceExitInfo;
import com.scc.signeliminateclass.bean.UserOutListInfo;
import com.scc.signeliminateclass.bean.UserSaveMessageInfo;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;

/**
 * 作者：宋聪聪 on 2019/9/2.
 */

public class SigninBiz {
    @Inject
    public SigninBiz() {
    }

    // 查询是否有私教
    public Observable<TestFacePassInfo> testFasePass(Context context, String orgId, String storeId,
                                                     String faceId) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .testFaceIsSuccess(orgId, storeId, faceId)
                .compose(RxHelper.rxSchedulerHelper());
    }

    // 查询是否有会员
    public Observable<TestUserFacePassInfo> testUserFasePass(Context context, String orgId, String storeId,
                                                             String faceId) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .testUserFaceIsSuccess(orgId, storeId, faceId)
                .compose(RxHelper.rxSchedulerHelper());
    }

    // 上传图片
    public Observable<PictureInfo> upLoadPicture(Context context, MultipartBody.Part file) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .uploadImg(file)
                .compose(RxHelper.rxSchedulerHelper());
    }

    // 签课成功，保存数据
    public Observable<SaveMessageInfo> saveMessage(Context context, String orgId, String stroeId, String privateId,
                                                   String privateImageUrl, String userId, String userImgUrl, String time) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .saveMessage(orgId, stroeId, privateId, privateImageUrl, userId, userImgUrl, time)
                .compose(RxHelper.rxSchedulerHelper());
    }

    // 消课----获取已签课的列表
    public Observable<UserOutListInfo> getPrivateCourse(Context context, String orgId, String store,
                                                        String employee_id) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .getPrivateCourse(orgId, store, employee_id)
                .compose(RxHelper.rxSchedulerHelper());
    }
    // 消课---用户识别成功，传faceid--和课程id
    public Observable<UserOutFaceExitInfo> testMemberFaceExit(Context context, String orgId, String store,
                                                              String employee_id, String courId) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .testMemberFaceExit(orgId, store, employee_id, courId)
                .compose(RxHelper.rxSchedulerHelper());
    }

    // 消课---保存用户数据
    public Observable<UserSaveMessageInfo> saveExitMessage(Context context, String orgId, String store,
                                                           String classid, String prImgUrl, String userImgUrl, String time) {
        return RetrofitSource.createApi(RetrofitApi.class, context)
                .saveExitMessage(orgId, store, classid, prImgUrl, userImgUrl, time)
                .compose(RxHelper.rxSchedulerHelper());
    }
//    // 消课--用户识别失败----根据课程查询id
//    public Observable<UserOutFaceErrorListInfo> getMemberByCourse(Context context, String orgId, String store, String classId) {
//        return RetrofitSource.createApi(RetrofitApi.class, context)
//                .getMemberByCourse(orgId, store, classId)
//                .compose(RxHelper.rxSchedulerHelper());
//    }
}
