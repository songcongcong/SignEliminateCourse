package com.scc.signeliminateclass;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.scc.signeliminateclass.base.BaseMvpActivity;
import com.scc.signeliminateclass.bean.MainCheckMessage;
import com.scc.signeliminateclass.mvp.impl.MainPresenterImpl;
import com.scc.signeliminateclass.mvp.ui.SignInActivity;
import com.scc.signeliminateclass.mvp.uiinterface.MainUiInterface;
import com.scc.signeliminateclass.utils.AppUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * MainActivity
 */
public class MainActivity extends BaseMvpActivity<MainPresenterImpl> implements MainUiInterface {
    /**
     * relaySignIn
     */
    @BindView(R.id.relay_sign_in)
    RelativeLayout relaySignIn;
    /**
     * relaySignOut
     */
    @BindView(R.id.relay_sign_out)
    RelativeLayout relaySignOut;
    /**
     * 注解P层
     */
    @Inject
    MainPresenterImpl impl;

    @Override
    protected MainPresenterImpl initInjector() {
        component.inject(this);
        return impl;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews(Bundle savedInstanceState) {
        impl.setUiInterface(this);
    }

    @Override
    protected void init() {
        // 签课
        subscribeClick(relaySignIn, o -> {
            SignInActivity.startActivity(MainActivity.this, 1);
        });
        // 消课
        subscribeClick(relaySignOut, o -> {
            impl.checkMessage(this, AppUtils.ORG_ID, AppUtils.STORE_ID);
        });
    }

    /**
     * 检查是否有消课--课程
     *
     * @param message message
     */
    @Override
    public void setExitMessage(MainCheckMessage message) {
        if (message.isIsHaveExit()) {
            SignInActivity.startActivity(MainActivity.this, 2);
        } else {
            Toast.makeText(this, "无可消课程！", Toast.LENGTH_LONG).show();
        }
    }
}
