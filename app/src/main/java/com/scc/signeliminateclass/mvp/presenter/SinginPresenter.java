package com.scc.signeliminateclass.mvp.presenter;

import android.content.Context;

/**
 * @author
 * @data 2019/9/22
 */
public interface SinginPresenter {
    /**
     * testFacePass
     * @param context context
     * @param orgId orgId
     * @param store store
     * @param faceId faceId
     */
    void testFacePass(Context context, String orgId, String store, String faceId);

    /**
     * testUserFacePass
     * @param context context
     * @param orgId orgId
     * @param store store
     * @param faceId faceId
     */
    void testUserFacePass(Context context, String orgId, String store, String faceId);

    /**
     * upLoadPicture
     * @param context context
     * @param file file
     */
    void upLoadPicture(Context context, String file);

    /**
     * 签课---保存用户数据
     * @param context context
     * @param orgId orgId
     * @param stroeId stroeId
     * @param privateId privateId
     * @param privateImageUrl privateImageUrl
     * @param userId userId
     * @param userImgUrl userImgUrl
     * @param time time
     */
    void saveMessage(Context context, String orgId, String stroeId, String privateId,
                     String privateImageUrl, String userId, String userImgUrl, String time);

    /**
     * getPrivateCourse
     * @param context context
     * @param orgId orgId
     * @param store store
     * @param employee_id employee_id
     */
    void getPrivateCourse(Context context, String orgId, String store, String employee_id);

    /**
     * testMemberFaceExit
     * @param context context
     * @param orgId  orgId
     * @param store store
     * @param employee_id employee_id
     * @param classId classId
     */
    void testMemberFaceExit(Context context, String orgId, String store, String employee_id, String classId);

    /**
     * 消课--保存用户数据
     * @param context context
     * @param orgId orgId
     * @param store store
     * @param classId classId
     * @param mPrImgUrl mPrImgUrl
     * @param mUserImgUrl mUserImgUrl
     * @param time time
     */
    void saveExitMessage(Context context, String orgId, String store, String classId,
                         String mPrImgUrl, String mUserImgUrl, String time);
    // 消课---用户人脸是否识别成功
//    void getMemberByCourse(Context context, String orgid, String stroe, String classId);

}
