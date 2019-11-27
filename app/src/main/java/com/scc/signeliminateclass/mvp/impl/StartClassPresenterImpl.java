package com.scc.signeliminateclass.mvp.impl;

import com.scc.signeliminateclass.base.BasePresenterImpl;
import com.scc.signeliminateclass.mvp.model.StartClassBiz;
import com.scc.signeliminateclass.mvp.presenter.StartClassPresenter;
import com.scc.signeliminateclass.mvp.uiinterface.StartClassUiInterface;
import javax.inject.Inject;


/**
 * @author
 * @data 2019/9/22
 */
public class StartClassPresenterImpl extends BasePresenterImpl<StartClassUiInterface> implements StartClassPresenter {
    /**
     * 注解构造器
     */
    @Inject
    public StartClassPresenterImpl() {
    }

    /**
     * 注解M层
     */
    @Inject
    StartClassBiz biz;

    /**
     * 初始化 UI层
     */
    StartClassUiInterface uiInterface;

    /**
     * 设置UI层
     * @param uiInterface uiInterface
     */
    public void setUiInterface(StartClassUiInterface uiInterface) {
        this.uiInterface = uiInterface;
    }

}
