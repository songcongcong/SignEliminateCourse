package com.scc.signeliminateclass.mvp.presenter;

import android.content.Context;

/**
 * @author
 * @data 2019/9/22
 */
public interface UserErrorPresenter {
    /**
     * getUserPhoneList
     * @param context context
     * @param orgId orgId
     * @param storeId storeId
     * @param phone phone
     */
    void getUserPhoneList(Context context, String orgId, String storeId,
                          String phone);

    /**
     * upLoadPicture
     * @param context context
     * @param file file
     */
    void upLoadPicture(Context context, String file);
//    void getMemberByCourse(Context context, String orgid, String stroe, String classId);

}
