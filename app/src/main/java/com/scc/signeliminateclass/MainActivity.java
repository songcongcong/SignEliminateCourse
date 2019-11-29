package com.scc.signeliminateclass;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.scc.signeliminateclass.base.BaseMvpActivity;
import com.scc.signeliminateclass.bean.CheckPrivateUdInfo;
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
    /**
     * 判断点击的事签课还是消课
     */
    private int isCheck = -1;

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
            finish();
        }

        // 签课
        subscribeClick(relaySignIn, o -> {
            isCheck = 1;
            getDialog();
        });
        // 消课
        subscribeClick(relaySignOut, o -> {
            isCheck = 2;
            getDialog();
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

    /**
     * 检查私教id
     *
     * @param checkPrivateUdInfo checkPrivateUdInfo
     */
    @Override
    public void checkedPrivatePersonal(CheckPrivateUdInfo checkPrivateUdInfo) {
         if (checkPrivateUdInfo.getCode().equals("200")) {
            if (isCheck == 1) {
                SignInActivity.startActivity(MainActivity.this, 1);
            } else if (isCheck == 2) {
                impl.checkMessage(this, AppUtils.ORG_ID, AppUtils.STORE_ID);
            }
        } else {
            Toast.makeText(this, checkPrivateUdInfo.getData(), Toast.LENGTH_LONG).show();
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

    /**
     * 弹出对话框
     */
    private void getDialog() {
        View view = View.inflate(this, R.layout.dialog_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        EditText edUuid = view.findViewById(R.id.ed_uuid);
        TextView mCancle = view.findViewById(R.id.tv_cancle);
        TextView mSure = view.findViewById(R.id.tv_sure);
        builder.setView(view);
        AlertDialog  alertDialog = builder.create();
        mCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEdUuid = edUuid.getText().toString().trim();
                if (!TextUtils.isEmpty(mEdUuid)) {
                    impl.checkedPrivatePersonal(MainActivity.this, mEdUuid);
                } else {
                    Toast.makeText(MainActivity.this, "私教id不能为空！", Toast.LENGTH_LONG).show();
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                alertDialog.dismiss();
            }
        });
        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }
    }
}
