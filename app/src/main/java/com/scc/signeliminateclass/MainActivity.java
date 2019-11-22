package com.scc.signeliminateclass;

import android.content.Context;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scc.signeliminateclass.base.BaseMvpActivity;
import com.scc.signeliminateclass.bean.MainCheckMessage;
import com.scc.signeliminateclass.mvp.impl.MainPresenterImpl;
import com.scc.signeliminateclass.mvp.ui.SignInActivity;
import com.scc.signeliminateclass.mvp.uiinterface.MainUiInterface;
import com.scc.signeliminateclass.utils.AppUtils;
import com.scc.signeliminateclass.utils.CountTimer;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * MainActivity
 */
public class MainActivity extends BaseMvpActivity<MainPresenterImpl> implements MainUiInterface, OnBannerListener {
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
     * banner
     */
    @BindView(R.id.banner)
    Banner banner;
    /**
     * mainLiean
     */
    @BindView(R.id.main_liean)
    LinearLayout mainLiean;
    /**
     * countTimerView
     */
    private CountTimer countTimerView;
    /**
     * list_path
     */
    private ArrayList<String> list_path;

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
        //初始化CountTimer，设置倒计时为2分钟。
//        countTimerView = new CountTimer(3000, 1000, MainActivity.this, banner, mainLiean);
//        initDate();
    }

    /**
     * 初始化banner数据
     */
    private void initDate() {
        //放图片地址的集合
        list_path = new ArrayList<>();
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
//        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true)
//        //设置指示器的位置，小点点，左中右。
//        banner.setIndicatorGravity(BannerConfig.CENTER)
//                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();

    }

    @Override
    public void OnBannerClick(int position) {
        banner.setVisibility(View.GONE);
        mainLiean.setVisibility(View.VISIBLE);
    }

    /**
     * 自定义的图片加载器
     */
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }


//    /**
//     * 主要的方法，重写dispatchTouchEvent
//     *
//     * @param ev ev
//     * @return boolean
//     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            //获取触摸动作，如果ACTION_UP，计时开始。
//            case MotionEvent.ACTION_UP:
//                countTimerView.start();
//                break;
//            //否则其他动作计时取消
//            default:
//                countTimerView.cancel();
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//    }

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
