package com.scc.signeliminateclass.mvp.presenter;

import android.content.Context;

/**
 * @author
 * @data 2019/9/22
 */
public interface SinginPresenter {
    void testFacePass(Context context, String orgId, String store, String faceId);
    void testUserFacePass(Context context, String orgId, String store, String faceId);
    void upLoadPicture(Context context, String file);
    // 签课---保存用户数据
    void saveMessage(Context context, String orgId, String stroeId, String privateId,
                     String privateImageUrl, String userId, String userImgUrl, String time);
    void getPrivateCourse(Context context, String orgId, String store, String employee_id);
    void testMemberFaceExit(Context context, String orgId, String store, String employee_id, String classId);
    // 消课--保存用户数据
    void saveExitMessage(Context context, String orgId, String store, String classId, String mPrImgUrl, String mUserImgUrl, String time);
    // 消课---用户人脸是否识别成功
//    void getMemberByCourse(Context context, String orgid, String stroe, String classId);

}
