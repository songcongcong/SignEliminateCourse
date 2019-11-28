package com.scc.signeliminateclass.api;

import com.scc.signeliminateclass.bean.MainCheckMessage;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.PrivateErrorListInfo;
import com.scc.signeliminateclass.bean.SaveMessageInfo;
import com.scc.signeliminateclass.bean.TestFacePassInfo;
import com.scc.signeliminateclass.bean.TestUserFacePassInfo;
import com.scc.signeliminateclass.bean.UserOutFaceExitInfo;
import com.scc.signeliminateclass.bean.UserOutListInfo;
import com.scc.signeliminateclass.bean.UserPhoneListInfo;
import com.scc.signeliminateclass.bean.UserSaveMessageInfo;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @author
 * @data 2019/11/12
 */
public interface RetrofitApi {

    /**
     * 根据faceID查询私教
     * @param mOrgId mOrgId
     * @param mStoreId mStoreId
     * @param faceid faceid
     * @return TestFacePassInfo
     */
    @GET("ezapp/share/private/testEmployeeFace")
    Observable<TestFacePassInfo> testFaceIsSuccess(@Query("orgId") String mOrgId,
                                                   @Query("store_id") String mStoreId,
                                                   @Query("face_id") String faceid);

    /**
     * 根据faceID查询会员
     * @param mOrgId mOrgId
     * @param mStoreId mStoreId
     * @param faceid faceid
     * @return TestUserFacePassInfo
     */
    @GET("ezapp/share/private/testMemberFace")
    Observable<TestUserFacePassInfo> testUserFaceIsSuccess(@Query("orgId") String mOrgId,
                                                           @Query("store_id") String mStoreId,
                                                           @Query("face_id") String faceid);

    /**
     * 私教人脸识别失败----私教列表
     * @param mOrgId mOrgId
     * @param mStoreId mStoreId
     * @param mPageNum mPageNum
     * @param mPageSize mPageSize
     * @return PrivateErrorListInfo
     */
    @GET("ezapp/share/private/getPrivateEmployee")
    Observable<PrivateErrorListInfo> getPrivateEmployee(@Query("orgId") String mOrgId,
                                                        @Query("store_id") String mStoreId,
                                                        @Query("pageNum") String mPageNum,
                                                        @Query("pageSize") String mPageSize);

    /**
     * 会员识别失败------根据手机号查询会员
     * @param mOrgId mOrgId
     * @param mStoreId mStoreId
     * @param phone phone
     * @return UserPhoneListInfo
     */
    @GET("ezapp/share/private/getMemberByPhone")
    Observable<UserPhoneListInfo> getUserPhoneList(@Query("orgId") String mOrgId,
                                                   @Query("store_id") String mStoreId,
                                                   @Query("phone") String phone);

    /**
     * 上传图片接口
     * @param parts parts
     * @return PictureInfo
     */
    @POST("ezapp/share/private/uploadImage")
    @Multipart
    Observable<PictureInfo> uploadImg(@Part MultipartBody.Part parts);

    /**
     * 私教，会员签课成功，保存数据
     * @param mOrgId mOrgId
     * @param mStoreId mStoreId
     * @param mEmployeeId mEmployeeId
     * @param mEmSignImage mEmSignImage
     * @param mMemberId mMemberId
     * @param mMemberSignImage mMemberSignImage
     * @param mSignTime mSignTime
     * @return SaveMessageInfo
     */
    @POST("ezapp/share/private/saveSignMessage")
    @FormUrlEncoded
    Observable<SaveMessageInfo> saveMessage(@Field("orgId") String mOrgId,
                                            @Field("store_id") String mStoreId,
                                            @Field("employee_id") String mEmployeeId,
                                            @Field("employee_sign_image") String mEmSignImage,
                                            @Field("member_id") String mMemberId,
                                            @Field("member_sign_image") String mMemberSignImage,
                                            @Field("sign_time") String mSignTime);


    /**
     * 私教人脸识别失败----私教列表
     * @param mOrdId mOrdId
     * @param mStoreId mStoreId
     * @return MainCheckMessage
     */
    @GET("ezapp/share/private/checkPrivateExitMessage")
    Observable<MainCheckMessage> checkIsMessage(@Query("orgId") String mOrdId,
                                                @Query("store_id") String mStoreId);


    /**
     * 消课获取已签课完成的列表
     * @param mOrdId mOrdId
     * @param mStoreId mStoreId
     * @param mEmployeeId   mEmployeeId   私教id/课程id
     * @return UserOutListInfo
     */
    @GET("ezapp/share/private/getPrivateCourse")
    Observable<UserOutListInfo> getPrivateCourse(@Query("orgId") String mOrdId,
                                                 @Query("store_id") String mStoreId,
                                                 @Query("employee_id") String mEmployeeId);

    /**
     * 消课--用户识别成功传课程id和faceid
     * @param mOrgId mOrgId
     * @param mStoreId mStoreId
     * @param mFaceId mFaceId
     * @param mCourseId mCourseId
     * @return UserOutFaceExitInfo
     */
    @GET("ezapp/share/private/testMemberFaceExit")
    Observable<UserOutFaceExitInfo> testMemberFaceExit(@Query("orgId") String mOrgId,
                                                       @Query("store_id") String mStoreId,
                                                       @Query("face_id") String mFaceId,
                                                       @Query("course_id") String mCourseId);

//    /**
//     * 消课--用户识别失败----根据课程查询id
//     *
//     * @param org_id
//     * @param store_id
//     * @return
//     */
//    @GET("ezapp/share/private/getMemberByCourse")
//    Observable<UserOutFaceErrorListInfo> getMemberByCourse(@Query("orgId") String org_id,
//                                                           @Query("store_id") String store_id,
//                                                           @Query("course_id") String course_id);

    /**
     * 消课--保存数据完成
     * @param mOrgId mOrgId
     * @param mStoreId mStoreId
     * @param id id
     * @param mEmployeeImage mEmployeeImage
     * @param mExitImage mExitImage
     * @param mExitTime mExitTime
     * @return UserSaveMessageInfo
     */
    @POST("ezapp/share/private/saveExitMessage")
    @FormUrlEncoded
    Observable<UserSaveMessageInfo> saveExitMessage(@Field("orgId") String mOrgId,
                                                    @Field("store_id") String mStoreId,
                                                    @Field("id") String id,
                                                    @Field("employee_exit_image") String mEmployeeImage,
                                                    @Field("member_exit_image") String mExitImage,
                                                    @Field("exit_time") String mExitTime);

//    212955240386200_2137860729
}
