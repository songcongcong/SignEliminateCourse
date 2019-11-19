package com.scc.signeliminateclass.mvp.presenter;

import android.content.Context;

/**
 * @author
 * @data 2019/9/22
 */
public interface TestErrorPresenter {
    void getPrivateEmployee (Context context, String orgId, String storeId);
    void upLoadPicture(Context context, String file);
    void getPrivateCourse(Context context, String orgId, String store, String employee_id);

}
