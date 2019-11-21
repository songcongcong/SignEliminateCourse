package com.scc.signeliminateclass.mvp.uiinterface;

import com.example.retrofitmvplibrary.base.BaseUiInterface;
import com.scc.signeliminateclass.bean.MainCheckMessage;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.SaveMessageInfo;
import com.scc.signeliminateclass.bean.TestFacePassInfo;
import com.scc.signeliminateclass.bean.TestUserFacePassInfo;

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
}
