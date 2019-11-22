package com.scc.signeliminateclass.utils;

import android.app.Activity;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;

import com.youth.banner.Banner;

/**
 * 无操作定时器
 */
public class CountTimer extends CountDownTimer {
    /**
     * context
     */
    private Activity context;
    /**
     * mBanner
     */
    private Banner mBanner;
    /**
     * mLiean
     */
    private LinearLayout mLiean;

    /**
     * @param millisInFuture  millisInFuture       倒计时总时间（如60S，120s等）
     * @param countDownInterval  countDownInterval    渐变时间（每次倒计1s）
     * @param context context
     * @param banner banner
     * @param linearLayout  linearLayout
     */
    public CountTimer(long millisInFuture, long countDownInterval, Activity context,
                      Banner banner, LinearLayout linearLayout) {
        super(millisInFuture, countDownInterval);
        this.context = context;
        this.mBanner = banner;
        this.mLiean = linearLayout;
    }

    // 计时完毕时触发
    @Override
    public void onFinish() {
        mBanner.setVisibility(View.VISIBLE);
        mLiean.setVisibility(View.GONE);
    }

    // 计时过程显示
    @Override
    public void onTick(long millisUntilFinished) {
    }
}