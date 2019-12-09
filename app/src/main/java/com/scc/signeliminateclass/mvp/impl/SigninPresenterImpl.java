package com.scc.signeliminateclass.mvp.impl;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.scc.signeliminateclass.base.BasePresenterImpl;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.SaveMessageInfo;
import com.scc.signeliminateclass.bean.TestFacePassInfo;
import com.scc.signeliminateclass.bean.TestUserFacePassInfo;
import com.scc.signeliminateclass.bean.UserOutFaceExitInfo;
import com.scc.signeliminateclass.bean.UserOutListInfo;
import com.scc.signeliminateclass.bean.UserSaveMessageInfo;
import com.scc.signeliminateclass.mvp.presenter.SinginPresenter;
import com.scc.signeliminateclass.mvp.model.SigninBiz;
import com.scc.signeliminateclass.mvp.uiinterface.SigninUiInterface;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author
 * @data 2019/9/22
 */
public class SigninPresenterImpl extends BasePresenterImpl<SigninUiInterface> implements SinginPresenter {
    /**
     * 注解构造器
     */
    @Inject
    public SigninPresenterImpl() {
    }

    /**
     * 注解M层
     */
    @Inject
    SigninBiz biz;

    /**
     * 初始化 UI层
     */
    SigninUiInterface uiInterface;

    /**
     * 设置UI层
     * @param uiInterface uiInterface
     */
    public void setUiInterface(SigninUiInterface uiInterface) {
        this.uiInterface = uiInterface;
    }

    /**
     * mDisposable
     */
    private Disposable mDisposable;

    @Override
    public void testFacePass(Context context, String orgId, String store, String faceId) {
        biz.testFasePass(context, orgId, store, faceId)
                .subscribe(new Observer<TestFacePassInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        mDisposable = d;
                    }

                    @Override
                    public void onNext(TestFacePassInfo testFacePassInfo) {
                        uiInterface.setFasePass(testFacePassInfo);
//                        mDisposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("song", "testFasePass" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void testUserFacePass(Context context, String orgId, String store, String faceId) {
        biz.testUserFasePass(context, orgId, store, faceId)
                .subscribe(new Observer<TestUserFacePassInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        mDisposable = d;
                    }

                    @Override
                    public void onNext(TestUserFacePassInfo testUserFacePassInfo) {
                        uiInterface.setUserFasePass(testUserFacePassInfo);
                        Log.d("song", "用户请求成功；" + testUserFacePassInfo.getCode());
//                        mDisposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("song", "用户请求异常；" + e.toString());
                        if (e.toString().contains("com.google.gson.JsonSyntaxException")) {
                            Toast.makeText(context, "未查到此人！", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void upLoadPicture(Context context, String file) {
        Log.d("song", "upLoadPicture：");
        // string转化为file文件
        File filePath = new File(file);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), filePath);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", filePath.getName(), requestBody);

        biz.upLoadPicture(context, part)
                .subscribe(new Observer<PictureInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PictureInfo pictureInfo) {
                        uiInterface.setUpLoadPicture(pictureInfo);
                        Log.d("song", "上传成功：");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("song", "上传失败：" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void saveMessage(Context context, String orgId, String stroeId, String privateId,
                            String privateImageUrl, String userId, String userImgUrl, String time) {
        biz.saveMessage(context, orgId, stroeId, privateId, privateImageUrl, userId,
                userImgUrl, time).subscribe(new Observer<SaveMessageInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(SaveMessageInfo saveMessageInfo) {
                uiInterface.setSaveMessage(saveMessageInfo);
                Log.d("song", "签课保存成功：" + saveMessageInfo.getCode());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("song", "签课保存识别：" + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getPrivateCourse(Context context, String orgId, String store, String employee_id) {
        biz.getPrivateCourse(context, orgId, store, employee_id)
                .subscribe(new Observer<UserOutListInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(UserOutListInfo outListInfo) {
                        uiInterface.getPrivateCourse(outListInfo);
                        Log.d("song", "sign---onNext:" + outListInfo.getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("song", "sign---onError" + e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void testMemberFaceExit(Context context, String orgId, String store, String employee_id, String classId) {
        biz.testMemberFaceExit(context, orgId, store, employee_id, classId)
                .subscribe(new Observer<UserOutFaceExitInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(UserOutFaceExitInfo outFaceExitInfo) {
                        uiInterface.testMemberFaceExit(outFaceExitInfo);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void saveExitMessage(Context context, String orgId, String store, String classId,
                                String mPrImgUrl, String mUserImgUrl, String time) {
        biz.saveExitMessage(context, orgId, store, classId, mPrImgUrl, mUserImgUrl, time)
                .subscribe(new Observer<UserSaveMessageInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(UserSaveMessageInfo userSaveMessageInfo) {
                        uiInterface.saveExitMessage(userSaveMessageInfo);
                        mDisposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//    @Override
//    public void getMemberByCourse(Context context, String orgid, String stroe, String classId) {
//        biz.getMemberByCourse(context, orgid, stroe, classId)
//                .subscribe(new Observer<UserOutFaceErrorListInfo>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        mDisposable = d;
//                    }
//
//                    @Override
//                    public void onNext(UserOutFaceErrorListInfo userOutFaceErrorListInfo) {
//                        uiInterface.getMemberByCourse(userOutFaceErrorListInfo);
//                        mDisposable.dispose();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
}
