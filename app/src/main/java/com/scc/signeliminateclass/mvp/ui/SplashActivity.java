package com.scc.signeliminateclass.mvp.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.scc.signeliminateclass.MainActivity;
import com.scc.signeliminateclass.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 轮播页
 */
public class SplashActivity extends AppCompatActivity implements  OnBannerListener {

    /**
     * banner
     */
    @BindView(R.id.banner)
    Banner banner;
    /**
     * list_path
     */
    private ArrayList<String> list_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initDate();
    }


    /**
     * 初始化banner数据
     */
    private void initDate() {
        //放图片地址的集合
        list_path = new ArrayList<>();
        list_path.add("https://yizutiyu.oss-cn-beijing.aliyuncs."
                + "com/heimingdan/yingyezhizhao/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20191127152355.png");
        list_path.add("https://yizutiyu.oss-cn-beijing.aliyuncs"
                + ".com/heimingdan/yingyezhizhao/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20191127153957.png");
        list_path.add("https://yizutiyu.oss-cn-beijing.aliyuncs"
                + ".com/heimingdan/yingyezhizhao/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20191127162010.png");
        list_path.add("https://yizutiyu.oss-cn-beijing"
                + ".aliyuncs.com/heimingdan/yingyezhizhao/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20191128151914.png");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.ZoomIn);
        //设置轮播图的标题集合
//        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(8000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true)
//        //设置指示器的位置，小点点，左中右。
//        banner.setIndicatorGravity(BannerConfig.CENTER)
//                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();

    }

    /**
     * 自定义的图片加载器
     */
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter().into(imageView);
        }
    }
    @Override
    public void OnBannerClick(int position) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("startend", 1);
        startActivity(intent);
        finish();
    }
}
