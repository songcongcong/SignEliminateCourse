package com.scc.signeliminateclass.mvp.presenter;

import android.content.Context;

/**
 * @author
 * @data 2019/9/22
 */
public interface TestErrorPresenter {
    /**
     * getPrivateEmployee
     * @param context context
     * @param orgId orgId
     * @param storeId storeId
     * @param pageNum pageNum
     * @param pageSize  pageSize
     */
    void getPrivateEmployee(Context context, String orgId, String storeId, String pageNum, String pageSize);

    /**
     * upLoadPicture
     * @param context context
     * @param file file
     */
    void upLoadPicture(Context context, String file);

    /**
     * getPrivateCourse
     * @param context context
     * @param orgId orgId
     * @param store store
     * @param employee_id employee_id
     */
    void getPrivateCourse(Context context, String orgId, String store, String employee_id);

}
