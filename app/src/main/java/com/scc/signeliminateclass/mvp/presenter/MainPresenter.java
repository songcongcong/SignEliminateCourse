package com.scc.signeliminateclass.mvp.presenter;

import android.content.Context;

/**
 * @author
 * @data 2019/9/22
 */
public interface MainPresenter {
    /**
     * checkMessage
     * @param context context
     * @param orgId orgId
     * @param  store store
     */
    void checkMessage(Context context, String orgId, String store);
}
