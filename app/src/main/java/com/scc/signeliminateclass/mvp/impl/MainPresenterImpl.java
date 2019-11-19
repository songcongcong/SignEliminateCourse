package com.scc.signeliminateclass.mvp.impl;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.scc.signeliminateclass.base.BasePresenterImpl;
import com.scc.signeliminateclass.bean.MainCheckMessage;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.SaveMessageInfo;
import com.scc.signeliminateclass.bean.TestFacePassInfo;
import com.scc.signeliminateclass.bean.TestUserFacePassInfo;
import com.scc.signeliminateclass.mvp.model.MainBiz;
import com.scc.signeliminateclass.mvp.model.SigninBiz;
import com.scc.signeliminateclass.mvp.presenter.MainPresenter;
import com.scc.signeliminateclass.mvp.presenter.SinginPresenter;
import com.scc.signeliminateclass.mvp.uiinterface.MainUiInterface;
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
public class MainPresenterImpl extends BasePresenterImpl<MainUiInterface> implements MainPresenter {
    @Inject
    public MainPresenterImpl() {
    }

    //注解M层
    @Inject
    MainBiz biz;

    //初始化 UI层
    MainUiInterface uiInterface;

    public void setUiInterface(MainUiInterface uiInterface) {
        this.uiInterface = uiInterface;
    }

    private Disposable mDisposable;

    @Override
    public void checkMessage(Context context, String orgId, String store) {
        biz.checkIsMessage(context, orgId, store)
                .subscribe(new Observer<MainCheckMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(MainCheckMessage mainCheckMessage) {
                        uiInterface.setExitMessage(mainCheckMessage);
                        mDisposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("song","检查失败："+e.toString());
                        Toast.makeText(context, "网络连接失败！",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
