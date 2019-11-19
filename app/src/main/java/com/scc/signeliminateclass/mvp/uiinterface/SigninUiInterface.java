package com.scc.signeliminateclass.mvp.uiinterface;

import com.example.retrofitmvplibrary.base.BaseUiInterface;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.SaveMessageInfo;
import com.scc.signeliminateclass.bean.TestFacePassInfo;
import com.scc.signeliminateclass.bean.TestUserFacePassInfo;
import com.scc.signeliminateclass.bean.UserOutFaceErrorListInfo;
import com.scc.signeliminateclass.bean.UserOutFaceExitInfo;
import com.scc.signeliminateclass.bean.UserOutListInfo;
import com.scc.signeliminateclass.bean.UserSaveMessageInfo;

/**
 * @author
 * @data 2019/9/22
 */
public interface SigninUiInterface extends BaseUiInterface {
    // UI展示
    void setFasePass(TestFacePassInfo info);
    void setUserFasePass(TestUserFacePassInfo info);
    void setUserError();
    void setUpLoadPicture(PictureInfo pictureInfo);
    void setSaveMessage(SaveMessageInfo saveMessage);
    void getPrivateCourse(UserOutListInfo outListInfo);
    void testMemberFaceExit(UserOutFaceExitInfo outFaceExitInfo);  // 消课---用户识别成功传faceid和课程id
    void saveExitMessage(UserSaveMessageInfo userSaveMessageInfo);  // 消课---保存用户数据
//    void getMemberByCourse(UserOutFaceErrorListInfo userOutFaceErrorListInfo);

}
