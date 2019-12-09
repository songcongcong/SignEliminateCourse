package com.scc.signeliminateclass.mvp.impl;


import android.content.Context;
import android.util.Log;

import com.scc.signeliminateclass.base.BasePresenterImpl;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.UserPhoneListInfo;
import com.scc.signeliminateclass.mvp.model.UserErrorBiz;
import com.scc.signeliminateclass.mvp.presenter.UserErrorPresenter;
import com.scc.signeliminateclass.mvp.uiinterface.UserUiInterface;

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
public class UserErrorPresenterImpl extends BasePresenterImpl<UserUiInterface> implements UserErrorPresenter {
    /**
     * 注解构造器
     */
    @Inject
    public UserErrorPresenterImpl() {
    }

    /**
     * 注解M层
     */
    @Inject
    UserErrorBiz biz;

    /**
     * 初始化 UI层
     */
    UserUiInterface uiInterface;

    /**
     * 设置UI层
     * @param uiInterface uiInterface
     */
    public void setUiInterface(UserUiInterface uiInterface) {
        this.uiInterface = uiInterface;
    }

    /**
     * mDisposable
     */
    private Disposable mDisposable;

    @Override
    public void getUserPhoneList(Context context, String orgId, String storeId, String phone) {
        biz.getUserPhoneList(context, orgId, storeId, phone)
                .subscribe(new Observer<UserPhoneListInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(UserPhoneListInfo userPhoneListInfo) {
                        uiInterface.setUserPhoneList(userPhoneListInfo);
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
    public void upLoadPicture(Context context, String file) {
        // string转化为file文件
        File filePath = new File(file);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), filePath);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", filePath.getName(), requestBody);

        biz.upLoadPicture(context, part)
                .subscribe(new Observer<PictureInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(PictureInfo pictureInfo) {
                        uiInterface.setUpLoadPicture(pictureInfo);
                        mDisposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("song", "会员上传失败：" + e .toString());
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
