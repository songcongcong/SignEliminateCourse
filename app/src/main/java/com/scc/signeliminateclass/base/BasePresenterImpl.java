package com.scc.signeliminateclass.base;

import com.example.retrofitmvplibrary.base.BasePresenter;
import com.example.retrofitmvplibrary.base.BaseUiInterface;

/**
 * 作者：宋聪聪 on 2019/6/18.
 * @param <UiType> UiType
 */

public class BasePresenterImpl<UiType extends BaseUiInterface> implements BasePresenter<UiType> {
    /**
     * uiType
     */
    private UiType uiType;

    @Override
    public void attachView(UiType view) {
        this.uiType = view;
    }

    @Override
    public void detachView() {
        this.uiType = null;
    }
}
