package com.scc.signeliminateclass.mvp.impl;


import android.content.Context;
import android.provider.Telephony;
import android.util.Log;
import android.widget.EditText;

import com.scc.signeliminateclass.base.BasePresenterImpl;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.PrivateErrorListInfo;
import com.scc.signeliminateclass.bean.UserOutListInfo;
import com.scc.signeliminateclass.mvp.model.SigninBiz;
import com.scc.signeliminateclass.mvp.model.TestErrorBiz;
import com.scc.signeliminateclass.mvp.presenter.SinginPresenter;
import com.scc.signeliminateclass.mvp.presenter.TestErrorPresenter;
import com.scc.signeliminateclass.mvp.uiinterface.SigninUiInterface;
import com.scc.signeliminateclass.mvp.uiinterface.TestErrorUiInterface;

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
public class TestErrorPresenterImpl extends BasePresenterImpl<TestErrorUiInterface> implements TestErrorPresenter {
    @Inject
    public TestErrorPresenterImpl() {
    }

    //注解M层
    @Inject
    TestErrorBiz biz;

    //初始化 UI层
    TestErrorUiInterface uiInterface;

    public void setUiInterface(TestErrorUiInterface uiInterface) {
        this.uiInterface = uiInterface;
    }

    private Disposable mDisposable;

    @Override
    public void getPrivateEmployee(Context context, String orgId, String storeId) {
        biz.getPrivateEmployee(context, orgId, storeId)
                .subscribe(new Observer<PrivateErrorListInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(PrivateErrorListInfo privateErrorListInfo) {
                        uiInterface.setPrivateList(privateErrorListInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("song", "请求失败：" + e.toString());
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
                        Log.d("song", "私教上传失败：" + e.toString());

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
                mDisposable = d;
            }

            @Override
            public void onNext(UserOutListInfo outListInfo) {
                uiInterface.getPrivateCourse(outListInfo);
                Log.d("song","会员请求成功66："+outListInfo.getMessage().toString());
                mDisposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("song","会员请求失败："+ e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
