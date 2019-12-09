package com.scc.signeliminateclass.mvp.uiinterface;

import com.example.retrofitmvplibrary.base.BaseUiInterface;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.SaveMessageInfo;
import com.scc.signeliminateclass.bean.TestFacePassInfo;
import com.scc.signeliminateclass.bean.TestUserFacePassInfo;
import com.scc.signeliminateclass.bean.UserOutFaceExitInfo;
import com.scc.signeliminateclass.bean.UserOutListInfo;
import com.scc.signeliminateclass.bean.UserSaveMessageInfo;

/**
 * @author
 * @data 2019/9/22
 */
public interface SigninUiInterface extends BaseUiInterface {
    /**
     * UI展示
     * @param info info
     */
    void setFasePass(TestFacePassInfo info);

    /**
     * setUserFasePass
     * @param info info
     */
    void setUserFasePass(TestUserFacePassInfo info);


    /**
     * setUpLoadPicture
     * @param pictureInfo pictureInfo
     */
    void setUpLoadPicture(PictureInfo pictureInfo);

    /**
     * setSaveMessage
     * @param saveMessage saveMessage
     */
    void setSaveMessage(SaveMessageInfo saveMessage);

    /**
     * getPrivateCourse
     * @param outListInfo outListInfo
     */
    void getPrivateCourse(UserOutListInfo outListInfo);

    /**
     * 消课---用户识别成功传faceid和课程id
     * @param outFaceExitInfo outFaceExitInfo
     */
    void testMemberFaceExit(UserOutFaceExitInfo outFaceExitInfo);

    /**
     * 消课---保存用户数据
     * @param userSaveMessageInfo userSaveMessageInfo
     */
    void saveExitMessage(UserSaveMessageInfo userSaveMessageInfo);
//    void getMemberByCourse(UserOutFaceErrorListInfo userOutFaceErrorListInfo);

}
