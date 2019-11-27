package com.scc.signeliminateclass;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.scc.signeliminateclass.base.BaseMvpActivity;
import com.scc.signeliminateclass.bean.MainCheckMessage;
import com.scc.signeliminateclass.mvp.impl.MainPresenterImpl;
import com.scc.signeliminateclass.mvp.ui.SignInActivity;
import com.scc.signeliminateclass.mvp.ui.SplashActivity;
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
    /**
     * mainLiean
     */
    @BindView(R.id.main_liean)
    LinearLayout mainLiean;


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
        int stardend = getIntent().getIntExtra("startend", 0);
        if (stardend != 1) {
            startActivity(new Intent(this, SplashActivity.class));
        }

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 当按下返回键时所执行的命令
        if ((keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)) {
            // 此处写你按返回键之后要执行的事件的逻辑
            AppUtils.exit(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
