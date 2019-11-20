package com.scc.signeliminateclass.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.scc.signeliminateclass.MainActivity;
import com.scc.signeliminateclass.R;
import com.scc.signeliminateclass.utils.SPUtils;
import com.scc.signeliminateclass.utils.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartClassActivity extends AppCompatActivity {

    @BindView(R.id.class_success)
    TextView classSuccess;
    @BindView(R.id.liear_success)
    LinearLayout liearSuccess;
    @BindView(R.id.tv_start_class)
    TextView tvStartClass;
    @BindView(R.id.checkbox_in)
    CheckBox checkboxIn;
    @BindView(R.id.tv_private_name)
    TextView tvPrivateName;
    @BindView(R.id.tv_private_time)
    TextView tvPrivateTime;
    @BindView(R.id.liear_in)
    LinearLayout liearIn;
    @BindView(R.id.checkbox_out)
    CheckBox checkboxOut;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_time)
    TextView tvUserTime;
    @BindView(R.id.lear_out)
    LinearLayout learOut;
    @BindView(R.id.view_selector)
    CheckBox viewSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_class);
        ButterKnife.bind(this);
        setPrivateUi();
        setUserUi();
        tvStartClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartClassActivity.this, MainActivity.class));
                finish();
            }
        });
        int isStart = getIntent().getIntExtra("isStart", 0);
        if (isStart == 1) {
            tvStartClass.setText("开始上课吧");
            classSuccess.setText("签课成功");
            tvPrivateName.setText(getResources().getString(R.string.private_class_name));
            tvUserName.setText(getResources().getString(R.string.user_class_name));
        } else {
            tvStartClass.setText("恭喜上课完成");
            classSuccess.setText("消课成功");
            tvPrivateName.setText(getResources().getString(R.string.private_out_class_name));
            tvUserName.setText(getResources().getString(R.string.user_out_class_name));
        }
    }


    // 改变签课签到,
    private void setPrivateUi() {
        checkboxIn.setSelected(true);
        tvPrivateName.setSelected(true);
        tvPrivateTime.setVisibility(View.VISIBLE);
        String currentTime = (String) SPUtils.get(this, "currentTime", "");
        tvPrivateTime.setText(currentTime);

    }

    // 改变签课签到,
    private void setUserUi() {
        checkboxOut.setSelected(true);
        tvUserName.setSelected(true);
        tvUserTime.setVisibility(View.VISIBLE);
        String userTime = (String) SPUtils.get(this, "userTime", "");
        tvUserTime.setText(userTime);

        // 改变中间的连接线状态
        viewSelector.setSelected(true);
    }
}
