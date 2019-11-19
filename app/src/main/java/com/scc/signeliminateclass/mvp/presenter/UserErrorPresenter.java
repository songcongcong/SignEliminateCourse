package com.scc.signeliminateclass.mvp.presenter;

import android.content.Context;

/**
 * @author
 * @data 2019/9/22
 */
public interface UserErrorPresenter {
    void getUserPhoneList(Context context, String orgId, String storeId,
                          String phone);

    void upLoadPicture(Context context, String file);
//    void getMemberByCourse(Context context, String orgid, String stroe, String classId);

}
