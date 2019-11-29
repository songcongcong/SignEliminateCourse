package com.scc.signeliminateclass.mvp.uiinterface;

import com.example.retrofitmvplibrary.base.BaseUiInterface;
import com.scc.signeliminateclass.bean.CheckPrivateUdInfo;
import com.scc.signeliminateclass.bean.MainCheckMessage;

/**
 * @author
 * @data 2019/9/22
 */
public interface MainUiInterface extends BaseUiInterface {
    /**
     * UI展示
     * @param message message
     */
    void setExitMessage(MainCheckMessage message);

    /**
     * checkedPrivatePersonal
     * @param checkPrivateUdInfo checkPrivateUdInfo
     */
    void checkedPrivatePersonal(CheckPrivateUdInfo checkPrivateUdInfo);
}
