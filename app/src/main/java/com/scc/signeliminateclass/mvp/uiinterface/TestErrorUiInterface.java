package com.scc.signeliminateclass.mvp.uiinterface;

import com.example.retrofitmvplibrary.base.BaseUiInterface;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.PrivateErrorListInfo;
import com.scc.signeliminateclass.bean.UserOutListInfo;

/**
 * @author
 * @data 2019/9/22
 */
public interface TestErrorUiInterface extends BaseUiInterface {
    /**
     * UI展示
     * @param info info
     */
    void setPrivateList(PrivateErrorListInfo info);

    /**
     * setUpLoadPicture
     * @param pictureInfo pictureInfo
     */
    void setUpLoadPicture(PictureInfo pictureInfo);

    /**
     * getPrivateCourse
     * @param outListInfo outListInfo
     */
    void getPrivateCourse(UserOutListInfo outListInfo);

}
