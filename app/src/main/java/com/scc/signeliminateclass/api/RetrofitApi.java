package com.scc.signeliminateclass.api;

import com.scc.signeliminateclass.bean.MainCheckMessage;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.PrivateErrorListInfo;
import com.scc.signeliminateclass.bean.SaveMessageInfo;
import com.scc.signeliminateclass.bean.TestFacePassInfo;
import com.scc.signeliminateclass.bean.TestUserFacePassInfo;
import com.scc.signeliminateclass.bean.UserOutFaceErrorListInfo;
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
     *
     * @param org_id
     * @param store_id
     * @return
     */
    @GET("ezapp/share/private/testEmployeeFace")
    Observable<TestFacePassInfo> testFaceIsSuccess(@Query("orgId") String org_id,
                                                   @Query("store_id") String store_id,
                                                   @Query("face_id") String faceid);

    /**
     * 根据faceID查询会员
     *
     * @param org_id
     * @param store_id
     * @return
     */
    @GET("ezapp/share/private/testMemberFace")
    Observable<TestUserFacePassInfo> testUserFaceIsSuccess(@Query("orgId") String org_id,
                                                           @Query("store_id") String store_id,
                                                           @Query("face_id") String faceid);

    /**
     * 私教人脸识别失败----私教列表
     *
     * @param org_id
     * @param store_id
     * @return
     */
    @GET("ezapp/share/private/getPrivateEmployee")
    Observable<PrivateErrorListInfo> getPrivateEmployee(@Query("orgId") String org_id,
                                                        @Query("store_id") String store_id);

    /**
     * 会员识别失败------根据手机号查询会员
     *
     * @param org_id
     * @param store_id
     * @return
     */
    @GET("ezapp/share/private/getMemberByPhone")
    Observable<UserPhoneListInfo> getUserPhoneList(@Query("orgId") String org_id,
                                                   @Query("store_id") String store_id,
                                                   @Query("phone") String phone);

    // 上传图片接口
    @POST("ezapp/share/private/uploadImage")
    @Multipart
    Observable<PictureInfo> uploadImg(@Part MultipartBody.Part parts);

    // 私教，会员签课成功，保存数据
    @POST("ezapp/share/private/saveSignMessage")
    @FormUrlEncoded
    Observable<SaveMessageInfo> saveMessage(@Field("orgId") String org_id,
                                            @Field("store_id") String store_id,
                                            @Field("employee_id") String employee_id,
                                            @Field("employee_sign_image") String employee_sign_image,
                                            @Field("member_id") String member_id,
                                            @Field("member_sign_image") String member_sign_image,
                                            @Field("sign_time") String sign_time);


    /**
     * 私教人脸识别失败----私教列表
     *
     * @param org_id
     * @param store_id
     * @return
     */
    @GET("ezapp/share/private/checkPrivateExitMessage")
    Observable<MainCheckMessage> checkIsMessage(@Query("orgId") String org_id,
                                                @Query("store_id") String store_id);


    /**
     * 消课获取已签课完成的列表
     *
     * @param org_id
     * @param store_id
     * @param employee_id  employee_id  私教id/课程id
     * @return
     */
    @GET("ezapp/share/private/getPrivateCourse")
    Observable<UserOutListInfo> getPrivateCourse(@Query("orgId") String org_id,
                                                 @Query("store_id") String store_id,
                                                 @Query("employee_id") String employee_id);

    /**
     * 消课--用户识别成功传课程id和faceid
     *
     * @param org_id
     * @param store_id
     * @param face_id
     * @return
     */
    @GET("ezapp/share/private/testMemberFaceExit")
    Observable<UserOutFaceExitInfo> testMemberFaceExit(@Query("orgId") String org_id,
                                                       @Query("store_id") String store_id,
                                                       @Query("face_id") String face_id,
                                                       @Query("course_id") String course_id);

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
     *
     * @param org_id
     * @param store_id
     * @return
     */
    @POST("ezapp/share/private/saveExitMessage")
    @FormUrlEncoded
    Observable<UserSaveMessageInfo> saveExitMessage(@Field("orgId") String org_id,
                                                    @Field("store_id") String store_id,
                                                    @Field("id") String id,
                                                    @Field("employee_exit_image") String employee_exit_image,
                                                    @Field("member_exit_image") String member_exit_image,
                                                    @Field("exit_time") String exit_time);

//    212955240386200_2137860729
}
