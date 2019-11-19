package com.scc.signeliminateclass.mvp.uiinterface;

import com.example.retrofitmvplibrary.base.BaseUiInterface;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.UserOutFaceErrorListInfo;
import com.scc.signeliminateclass.bean.UserOutListInfo;
import com.scc.signeliminateclass.bean.UserPhoneListInfo;

/**
 * @author
 * @data 2019/9/22
 */
public interface UserUiInterface extends BaseUiInterface {
    // UI展示
    void setUserPhoneList(UserPhoneListInfo phoneList);
    void setUpLoadPicture(PictureInfo pictureInfo);
//    void getMemberByCourse(UserOutFaceErrorListInfo userOutFaceErrorListInfo);
}
