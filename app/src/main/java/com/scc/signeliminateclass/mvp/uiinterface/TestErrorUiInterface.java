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
    // UI展示
    void setPrivateList(PrivateErrorListInfo info);
    void setUpLoadPicture(PictureInfo pictureInfo);
    void getPrivateCourse(UserOutListInfo outListInfo);

}
