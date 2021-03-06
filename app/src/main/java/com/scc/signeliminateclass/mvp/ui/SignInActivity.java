package com.scc.signeliminateclass.mvp.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.scc.signeliminateclass.MHFace;
import com.scc.signeliminateclass.MainActivity;
import com.scc.signeliminateclass.R;
import com.scc.signeliminateclass.adapter.SignInAdapter;
import com.scc.signeliminateclass.base.BaseMvpActivity;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.SaveMessageInfo;
import com.scc.signeliminateclass.bean.TestFacePassInfo;
import com.scc.signeliminateclass.bean.TestUserFacePassInfo;
import com.scc.signeliminateclass.bean.UserInfo;
import com.scc.signeliminateclass.bean.UserOutFaceExitInfo;
import com.scc.signeliminateclass.bean.UserOutListInfo;
import com.scc.signeliminateclass.bean.UserSaveMessageInfo;
import com.scc.signeliminateclass.eventbus.FileEventBus;
import com.scc.signeliminateclass.mvp.impl.SigninPresenterImpl;
import com.scc.signeliminateclass.mvp.uiinterface.SigninUiInterface;
import com.scc.signeliminateclass.surfaceview.CameraSurfaceHolder;
import com.scc.signeliminateclass.utils.AppUtils;
import com.scc.signeliminateclass.utils.ContensUtils;
import com.scc.signeliminateclass.utils.NetWorkUtils;
import com.scc.signeliminateclass.utils.SPUtils;
import com.scc.signeliminateclass.utils.TimeUtil;
import com.scc.signeliminateclass.widget.ScreenUtil;
import com.scc.signeliminateclass.widget.SurFaceView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * SignInActivity
 */
public class SignInActivity extends BaseMvpActivity<SigninPresenterImpl> implements SigninUiInterface {
    /**
     * TAG
     */
    private static final String TAG = "SignInActivity";
    /**
     * 添加p层注入
     */
    @Inject
    SigninPresenterImpl impl;
    /**
     * 相机权限
     */
    public static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;
    /**
     * WiFi权限
     */
    public static final String NETWORK_PERMISSION = Manifest.permission.ACCESS_NETWORK_STATE;
    /**
     * 相机请求码
     */
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    /**
     * mCameraSurfaceHolder
     */
    CameraSurfaceHolder mCameraSurfaceHolder = new CameraSurfaceHolder();
    /**
     * imgBack
     */
    @BindView(R.id.img_back)
    ImageView imgBack;
    /**
     * supSufviewIn
     */
    @BindView(R.id.sup_sufview_in)
    SurFaceView supSufviewIn;
    /**
     * tvIn
     */
    @BindView(R.id.tv_in)
    CheckBox tvIn;
    /**
     * tvOut
     */
    @BindView(R.id.tv_out)
    CheckBox tvOut;
    /**
     * img
     */
    @BindView(R.id.img)
    ImageView img;
    /**
     * imgIc
     */
    @BindView(R.id.img_ic)
    ImageView imgIc;
    /**
     * checkboxIn
     */
    @BindView(R.id.checkbox_in)
    CheckBox checkboxIn;
    /**
     * tvPrivateName
     */
    @BindView(R.id.tv_private_name)
    TextView tvPrivateName;
    /**
     * tvPrivateTime
     */
    @BindView(R.id.tv_private_time)
    TextView tvPrivateTime;
    /**
     * checkboxOut
     */
    @BindView(R.id.checkbox_out)
    CheckBox checkboxOut;
    /**
     * tvUserName
     */
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    /**
     * tvUserTime
     */
    @BindView(R.id.tv_user_time)
    TextView tvUserTime;
    /**
     * viewSelector
     */
    @BindView(R.id.view_selector)
    CheckBox viewSelector;
    /**
     * siginRecycle
     */
    @BindView(R.id.sigin_recycle)
    RecyclerView siginRecycle;
    /**
     * winpayLoadingBg
     */
    @BindView(R.id.winpay_loading_bg)
    ImageView winpayLoadingBg;
    /**
     * signLinear
     */
    @BindView(R.id.sign_linear)
    LinearLayout signLinear;
    /**
     * 获取从那个页面跳转过来
     */
    private int flag;
    /**
     * 识别人脸File
     */
    private ByteArrayOutputStream mFile = null;

    /**
     * 选择相机
     */
    private static final int RC_CHOOSE_CAMERA = 101;
    /**
     * file 相机回调的路径
     */
    private File cameraSavePath;

    /**
     * tempUri
     */
    private Uri tempUri;
    /**
     * 裁剪回调
     */
    private static final int CROP_SMALL_PICTURE = 102; //裁剪
    /**
     * 小米裁剪回调
     */
    private static final int CROP_SMALL_PICTURE_MIUI = 103; //小米裁剪
    /**
     * uritempFile
     */
    private Uri uritempFile;
    /**
     * 选择图片
     */
    private static final int RC_CHOOSE_PHOTO = 104;


    /**
     * 私教是否签课成功
     */
    private boolean isPrivateSuccess = false;

    /**
     * 是否点击签课
     */
    private boolean isCheckUser = false;

    /**
     * 消课---有签课列表----回调id
     */
    private int mOutUserId;
    /**
     * byteArrayOutputStream
     */
    private ByteArrayOutputStream byteArrayOutputStream;

    /**
     * 判断用户是否成功
     */
    private boolean isUserSaveSucc;
    /**
     * 用来判断点击的是签课还是消课
     */
    private int privatePass = -1;

    /**
     * 私教URL
     */
    private static final String PRIVATE_URL = "prfile";
    /**
     * 用户URL
     */
    private static final String USER_URL = "urfile";
    /**
     * 私教id
     */
    private static final String PRIVATE_ID = "orgId";
    /**
     * 用户id
     */
    private static final String USER_ID = "userId";
    /**
     * 用户---根据课程id查询人脸是否成功
     */
    private static final String CLASSOUT_URL = "outfile";

    /**
     * 上课私教时间
     */
    public static final String CURRENT_TIME = "currentPrTime";
    /**
     * 判断按钮只点击一次
     */
    private boolean isInCheck;
    /**
     * 解决人脸识别并发问题---每次只执行一个请求
     */
    private static Object obj = new Object();
    /**
     * animaition
     */
    private AnimationDrawable animaition;

    /**
     * 无网络时将图片存入sp
     */
    private static final String NO_NETWORK = "noNetWork";
    /**
     * mList
     */
    private List<UserOutListInfo.MessageBean> mList;
    /**
     * netWorkAvalible
     */
    private boolean netWorkAvalible;
    /**
     * signInAdapter
     */
    private SignInAdapter signInAdapter;

    /**
     * 失败的次数
     */
    private int mErrorNum = 0;
    /**
     * faceid请求成功，并且查询有当前的数据---testFacePass
     */
    private boolean isFaceSuccess;
    /**
     * 按钮是否点击
     */
    private boolean isBtnCheck;
    /**
     * thread
     */
    private Thread thread;
    /**
     * 私教成功，UI以刷新
     */
    private boolean isPrSuccess;
    /**
     * mPvUrl
     */
    private String mPvUrl;
    /**
     * mUserImgUrl
     */
    private String mUserImgUrl;
    /**
     * mTime
     */
    private String mTime;
    /**
     * 判断私教查询列表点击返回键
     */
    private boolean isResult = false;

    /**
     * facePacefile
     */
    private File facePacefile;
    /**
     * userFaceFile
     */
    private File userFaceFile;
    /**
     * memberFile
     */
    private File memberFile;

    /**
     * handler
     */
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (mErrorNum >= 5 || isFaceSuccess) { // 如果失败次数大于等于5或者faceid查询有数据就return，不再往下走
                    return;
                }
                JSONObject json = (JSONObject) msg.obj;
                if (json != null) {
                    Log.d(TAG, "json：" + json.toString());
                    UserInfo userInfo = new Gson().fromJson(json.toString(), UserInfo.class);
                    if (userInfo.getError_msg().equals("SUCCESS")) {
                        for (int i = 0; i < userInfo.getResult().getUser_list().size(); i++) {
                            String mUserID = userInfo.getResult().getUser_list().get(i).getUser_id();
                            //  签课/消课--- 私教人脸识别成功 --- 接口传faceid查询是否有某一个私教信息
                            impl.testFacePass(SignInActivity.this, AppUtils.ORG_ID, AppUtils.STORE_ID, mUserID);
                            if (isCheckUser) {
                                if (flag == 1) { // 根据faceID 查询是否有会员
                                    impl.testUserFacePass(SignInActivity.this,
                                            AppUtils.ORG_ID, AppUtils.STORE_ID, mUserID);
                                } else {
                                    if (mOutUserId != 0) {
                                        impl.testMemberFaceExit(SignInActivity.this, AppUtils.ORG_ID, AppUtils.STORE_ID,
                                                mUserID, String.valueOf(mOutUserId));
                                    }
                                }
                            }
                        }
                    } else {
                        faceErrorStart();
                    }
                } else {
                    faceErrorStart();
                }

            } else if (msg.what == 2) {
                faceErrorStart();
                Log.e(TAG, "人脸识别异常----检测失败");
            }
        }
    };


    @Override
    protected SigninPresenterImpl initInjector() {
        component.inject(this);
        return impl;
    }

    @Override
    protected int getLayoutId() {
        if (ContensUtils.getScrenn(this)) {
            return R.layout.activity_sign_in_screen;
        }
        return R.layout.activity_sign_in;
    }

    @Override
    protected void findViews(Bundle savedInstanceState) {
        // 将view设置给presenter
        impl.setUiInterface(this);
    }

    @Override
    protected void init() {
        // 获取从那个页面跳转过来
        flag = getIntent().getIntExtra("flag", 0);
        EventBus.getDefault().register(this);
        netWorkAvalible = NetWorkUtils.isNetWorkAvalible(this);
        mList = new ArrayList<>();
        // 接收数据
        getDate();
        subscribeClick(imgBack, o -> {
            finish();
        });
    }

    /**
     * 查询人脸信息根据人脸库中的用户id
     *
     * @param file file
     */
    public void queryFaceUserId(ByteArrayOutputStream file) {
        try {
            try {
                JSONObject json = MHFace.detectUserIdByFace(file, "ezu_kj2");
                this.byteArrayOutputStream = file;
                Message message = new Message();
                message.what = 1;
                message.obj = json;
                handler.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "请求异常：" + e.toString());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            handler.sendEmptyMessage(2);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContensUtils.checkAndApplyfPermissionActivity(SignInActivity.this,
                new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                RC_CHOOSE_CAMERA)) {
            surViewShowInit();
        } else {
            surViewShowInit();
        }
        mErrorNum = 0; // 初始化次数
        if (flag == 2 && isResult) { // 下课---
            siginRecycle.setVisibility(View.VISIBLE);
            if (signInAdapter != null) {
                signInAdapter.notifyDataSetChanged();
            }
        }
    }


    /**
     * 接收来自哪一个activity
     **/
    private void getDate() {
        if (flag == 1) { // 上课
            tvIn.setText(getResources().getString(R.string.private_class_name));
            tvOut.setText(getResources().getString(R.string.user_class_name));
            tvPrivateName.setText(getResources().getString(R.string.private_class_name));
            tvUserName.setText(getResources().getString(R.string.user_class_name));
        } else { // 下课
            tvIn.setText(getResources().getString(R.string.private_out_class_name));
            tvOut.setText(getResources().getString(R.string.user_out_class_name));
            tvPrivateName.setText(getResources().getString(R.string.private_out_class_name));
            tvUserName.setText(getResources().getString(R.string.user_out_class_name));
        }

        surViewShowInit(); //初始化surView
        // 点击签课
        subscribeClick(tvIn, o -> {
            isInCheck = false;
            if (!isInCheck) {
                startAnimtion();
                isBtnCheck = true;
                mErrorNum = 0;
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RC_CHOOSE_CAMERA:
                if (permissions[0].equals(CAMERA_PERMISSION)) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Log.e(TAG, "===========权限回调---用户同意了");
                        surViewShowInit();
                    }
                    break;
                }
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TestErrorActivity.TEST_REQUESTCODE: // 私教拍照完成回调
                if (resultCode == TestErrorActivity.TEST_RESULTCODE) {
                    mCameraSurfaceHolder.setCameraSurfaceHolder(this, supSufviewIn);
                    if (data != null) {
                        String primgUrl = data.getStringExtra("primgUrl"); // 相机拍照的图片
                        int prId = data.getIntExtra("prId", 0); // 点击某一个私教的id
                        List<UserOutListInfo.MessageBean> mListIntent =
                                (List<UserOutListInfo.MessageBean>) data.getSerializableExtra("mListIntent");
                        boolean check = data.getBooleanExtra("check", false);
                        int position = data.getIntExtra("position", 0);
                        SPUtils.remove(this, PRIVATE_ID);
                        SPUtils.remove(this, PRIVATE_URL);
                        SPUtils.put(this, PRIVATE_ID, prId);
                        SPUtils.put(this, PRIVATE_URL, primgUrl);
                        if (flag == 2) {
                            isResult = true;
                            SPUtils.remove(this, "prid");
                            SPUtils.put(this, "prid", prId);
                            if (mListIntent != null && mListIntent.size() > 0) {
                                getPrivateCourse(mListIntent, check, position);
                            }
//                            // 查询私教列表
//                            impl.getPrivateCourse(SignInActivity.this, AppUtils.ORG_ID,
//                                    AppUtils.STORE_ID, String.valueOf(prId));
                        }
                    }
                    isPrivateSuccess = true;
                    setPrivateUi();
                    // 点击消课
                    subscribeClick(tvOut, o -> {
                        isInCheck = false;
                        if (!isInCheck) {
                            onCheckUserBtn();
                        }
                    });
                }
                break;
            case UserErrorActivity.USER_REQUESTCODE: // 会员拍照完成回调
                if (resultCode == UserErrorActivity.USER_RESULTCODE) {
                    mCameraSurfaceHolder.setCameraSurfaceHolder(this, supSufviewIn);
                    if (data != null) {
                        isUserSaveSucc = true;
                        String userImg = data.getStringExtra("userImg");
                        int userIdError = data.getIntExtra("userId", 0);
                        // 存储之前先删除
                        SPUtils.remove(this, USER_ID);
                        SPUtils.remove(this, USER_URL);
                        SPUtils.put(this, USER_ID, userIdError);
                        SPUtils.put(this, USER_URL, userImg);

                        // 请求成功
                        String prfile = (String) SPUtils.get(this, PRIVATE_URL, "");
                        int privated = (int) SPUtils.get(this, PRIVATE_ID, 0);

                        // 会员跳转传递
//                        String userTime = (String) SPUtils.get(this, USER_TIME, "");

                        // 获取课程id----私教人脸识别成功，显示的列表
                        int mClassId = (int) SPUtils.get(this, "outUserId", 0);

                        if (flag == 1) {
                            impl.saveMessage(this, AppUtils.ORG_ID, AppUtils.STORE_ID, String.valueOf(privated),
                                    prfile, String.valueOf(userIdError), userImg, TimeUtil.getCurrentTime());
                        } else {
                            impl.saveExitMessage(this, AppUtils.ORG_ID, AppUtils.STORE_ID, String.valueOf(mClassId),
                                    prfile, userImg, TimeUtil.getCurrentTime());
                        }
                    }
                }
                break;
            case RC_CHOOSE_CAMERA:
                surViewShowInit();
                break;
            default:
                break;
        }
    }

    /**
     * 开启动画
     */
    private void startAnimtion() {
        signLinear.setVisibility(View.VISIBLE);
        winpayLoadingBg.setBackgroundResource(R.drawable.loading_pic);
        animaition = (AnimationDrawable) winpayLoadingBg.getBackground();
        animaition.setOneShot(false);   //设置是否只播放一次，和上面xml配置效果一致
        animaition.start();             //启动动画
    }

    /**
     * 关闭动画
     */
    private void stopAnimtion() {
        if (signLinear != null) {
            signLinear.setVisibility(View.INVISIBLE);
        }
        if (animaition != null) {
            animaition.stop();
        }
    }

    /**
     * 改变上课UI
     */
    private void setPrivateUi() {
        tvIn.setFocusable(false);
        tvIn.setClickable(false);
        tvIn.setBackgroundResource(R.drawable.tv_backgrount);

        checkboxIn.setSelected(true);
        tvPrivateName.setSelected(true);
        tvPrivateTime.setVisibility(View.VISIBLE);
        SPUtils.put(this, CURRENT_TIME, TimeUtil.getCurrentTime());
        String time = (String) SPUtils.get(this, CURRENT_TIME, "");
        tvPrivateTime.setText(time);
    }

    /**
     * 改变下课签到,
     */
    private void setUserUi() {
        tvOut.setFocusable(false);
        tvOut.setClickable(false);
        tvOut.setBackgroundResource(R.drawable.tv_backgrount);

        checkboxOut.setSelected(true);
        tvUserName.setSelected(true);
        tvUserTime.setVisibility(View.VISIBLE);
        tvUserTime.setText(TimeUtil.getCurrentTime());

        // 改变中间的连接线状态
        viewSelector.setSelected(true);

    }



    /**
     * 接受到的File文件
     *
     * @param fileEventBus fileEventBus
     */
    @Subscribe
    public void onMessageEvent(FileEventBus fileEventBus) throws InterruptedException {
        if (fileEventBus.getFile() != null) {
            this.mFile = fileEventBus.getFile();
            if (isBtnCheck || isPrSuccess) {
                Thread.sleep(1000);
                setScanFace(mFile);
            }
        }
    }


    /**
     * 请求人脸识别
     *
     * @param file file
     */
    private void setScanFace(ByteArrayOutputStream file) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                isInCheck = true;
//                synchronized (obj) { // 保证一个请求执行完再执行下一个请求
                // 检测人脸
                queryFaceUserId(file);
//                }
            }
        });
        thread.start();
    }

    /**
     * activity跳转---- 1、签课  2、消课
     *
     * @param activity activity
     * @param flag     flag
     */
    public static void startActivity(Activity activity, int flag) {
        Intent intent = new Intent(activity, SignInActivity.class);
        intent.putExtra("flag", flag);
        activity.startActivity(intent);
    }


    /**
     * 签课------点击私教--根据faceid查询是否 有私教信息
     * 消课 ---- 点击私教---根据私教id查询私教列表
     *
     * @param info
     */
    @Override
    public void setFasePass(TestFacePassInfo info) {
        if (info.isIsPass()) {
            isBtnCheck = false; // 请求成功，就不在调人脸识别接口
            isPrivateSuccess = true;
            isFaceSuccess = true;
            if (flag == 1) { //如果是从消课进入---点击私教人脸识别成功，查询已签课的私教列表
                if (isCheckUser) { // 判断如果点击了用户按钮，并且人脸识别私教也通过了（只有一个faceid），就继续人脸查询
                    isFaceSuccess = false;
                } else {
                    stopAnimtion();
                    setPrivateUi();
                    // 点击签课
                    subscribeClick(tvOut, o -> {
                        isInCheck = false;
                        if (!isInCheck) {
                            onCheckUserBtn();
                        }
                    });
                }
            } else {
                if (isCheckUser) { // 用户继续查询
                    isFaceSuccess = false;
                } else { // 下课---如果未点击用户按钮，就进行私教处理
                    isResult = true;
                    // 查询私教列表
                    impl.getPrivateCourse(SignInActivity.this, AppUtils.ORG_ID, AppUtils.STORE_ID,
                            String.valueOf(info.getPrivateMessage().getId()));
                }
            }
            SPUtils.put(this, PRIVATE_ID, info.getPrivateMessage().getId());  // 将私教id存入sp

            if (byteArrayOutputStream != null) {  // 根据拍照成功的file流添加水印，并上传有水印的图片
                privatePass = 1;  // 区分是签课人脸图片上传成功
                if (!TextUtils.isEmpty(info.getPrivateMessage().getNickname())) {
                    Bitmap bitmap = pictDrawText();
                    if (ContensUtils.getScrenn(this)) {
                        facePacefile = AppUtils.drawTextFaceSuccPictureScreen(this, bitmap,
                                info.getPrivateMessage().getNickname());
                    } else {
                        facePacefile = AppUtils.drawTextFaceSuccPicture(this, bitmap,
                                info.getPrivateMessage().getNickname());
                    }

                    if (!netWorkAvalible) {
                        SPUtils.put(this, NO_NETWORK, facePacefile.toString());
                    }
                    impl.upLoadPicture(this, facePacefile.toString()); // 上传图片
                }
            }
        } else {
            faceErrorStart();
        }
    }
    private  Bitmap rotateImage(int angle,Bitmap bitmap) {
        //旋转图片
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }
    /**
     * 对人脸识别图片旋转角度
     * @param bm
     * @param orientationDegree
     * @return
     */
   private Bitmap adjustPhotoRotation(Bitmap bm, final int orientationDegree) {

        Matrix m = new Matrix();
        m.setRotate(orientationDegree, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        float targetX, targetY;
        if (orientationDegree == 90) {
            targetX = bm.getHeight();
            targetY = 0;
        } else {
            targetX = bm.getHeight();
            targetY = bm.getWidth();
        }

        final float[] values = new float[9];
        m.getValues(values);

        float x1 = values[Matrix.MTRANS_X];
        float y1 = values[Matrix.MTRANS_Y];

        m.postTranslate(targetX - x1, targetY - y1);

        Bitmap bm1 = Bitmap.createBitmap(bm.getHeight(), bm.getWidth(), Bitmap.Config.ARGB_8888);

        Paint paint = new Paint();
        Canvas canvas = new Canvas(bm1);
        canvas.drawBitmap(bm, m, paint);


        return bm1;
    }





    /**
     * 签课---会员---根据faceID查询某一个会员信息
     *
     * @param info
     */
    @Override
    public void setUserFasePass(TestUserFacePassInfo info) {
        if (info.isIsPass()) {
            stopAnimtion();
            isBtnCheck = false;
            isPrSuccess = false;
            isUserSaveSucc = true; // 只用来最后一步保存数据（私教和用户全部识别完成）
            setUserUi();
            SPUtils.put(this, USER_ID, info.getMemberMessage().getMembership_id());  // 把会员id存入sp
            if (byteArrayOutputStream != null) {  // 将成功的图片添加水印，并上传返回有水印的URL
                privatePass = 2;
                if (!TextUtils.isEmpty(info.getMemberMessage().getName())) {
                    Bitmap bitmap = pictDrawText();
                    if (ContensUtils.getScrenn(this)) {
                        userFaceFile = AppUtils.drawTextFaceSuccPictureScreen(this, bitmap, info.getMemberMessage().getName());
                    } else {
                        userFaceFile = AppUtils.drawTextFaceSuccPicture(this, bitmap, info.getMemberMessage().getName());
                    }
                    if (!netWorkAvalible) {
                        SPUtils.put(this, NO_NETWORK, userFaceFile.toString());
                    }
                    impl.upLoadPicture(this, userFaceFile.toString()); // 上传有水印图片
                }
            }
        } else {
            faceErrorStart();
        }
    }


    /**
     * 图片上传成功
     *
     * @param pictureInfo pictureInfo
     */
    @Override
    public void setUpLoadPicture(PictureInfo pictureInfo) {
        // privatePass  区分是签课私教，还是签课用户，还是消课私教，还是消课用户
        if (privatePass == 1) { // 签课---私教
            SPUtils.remove(this, PRIVATE_URL);
            SPUtils.put(this, PRIVATE_URL, pictureInfo.getImage());
        } else if (privatePass == 2) {  // 签课 -- 会员
            SPUtils.remove(this, USER_URL);
            SPUtils.put(this, USER_URL, pictureInfo.getImage());
        } else if (privatePass == 3) {
            SPUtils.remove(this, CLASSOUT_URL);
            SPUtils.put(this, CLASSOUT_URL, pictureInfo.getImage());
        }
        String prfile = (String) SPUtils.get(this, PRIVATE_URL, ""); // 签课--私教图片
        String urfile = (String) SPUtils.get(this, USER_URL, ""); // 签课-- 会员图片
        int privated = (int) SPUtils.get(this, PRIVATE_ID, 0); // 签课--私教id
        int userId = (int) SPUtils.get(this, USER_ID, 0); //签课 --会员id

        if (isUserSaveSucc) {
            if (flag == 1) {
                impl.saveMessage(this, AppUtils.ORG_ID, AppUtils.STORE_ID, String.valueOf(privated),
                        prfile, String.valueOf(userId), urfile, TimeUtil.getCurrentTime());
            } else {
                String outfile = (String) SPUtils.get(this, CLASSOUT_URL, "");
                // 获取课程id----私教人脸识别成功，显示的列表
                int mClassId = (int) SPUtils.get(this, "outUserId", 0);
                impl.saveExitMessage(this, AppUtils.ORG_ID, AppUtils.STORE_ID, String.valueOf(mClassId),
                        prfile, outfile, TimeUtil.getCurrentTime());
            }
        }
    }

    @Override
    public void setSaveMessage(SaveMessageInfo saveMessage) {
        if (saveMessage.getCode() == 200) {
            setUserUi();
            isPrivateSuccess = false;
            stopCameraIntent();
            Intent intent = new Intent(this, StartClassActivity.class);
            intent.putExtra("isStart", 1);
            startActivity(intent);
            finish();
        }
    }

    /**
     * 下课---获取已签课列表
     *
     * @param outListInfo outListInfo
     */
    @Override
    public void getPrivateCourse(UserOutListInfo outListInfo) {
        mList = outListInfo.getMessage();
        stopAnimtion();
        setPrivateUi();
        getPrivateCourse(mList, false, -1);
    }


    // 消课---用户识别成功传faceid和课程id
    @Override
    public void testMemberFaceExit(UserOutFaceExitInfo outFaceExitInfo) {
        Log.d(TAG, "消课用户识别成功---:" + outFaceExitInfo.isIsPass());
        if (outFaceExitInfo.isIsPass()) {
            stopAnimtion();
            isUserSaveSucc = true; // 只用来最后一步保存数据（私教和用户全部识别完成）
            setUserUi();
            if (byteArrayOutputStream != null) {  // 将成功的图片添加水印，并上传返回有水印的URL
                privatePass = 3;
                Bitmap bitmap = pictDrawText();
                if (ContensUtils.getScrenn(this)) {
                    memberFile = AppUtils.drawTextFaceSuccPictureScreen(this, bitmap,
                            outFaceExitInfo.getMemberMessage().getName());
                } else {
                    memberFile = AppUtils.drawTextFaceSuccPicture(this, bitmap,
                            outFaceExitInfo.getMemberMessage().getName());
                }
                if (!netWorkAvalible) {
                    SPUtils.put(this, NO_NETWORK, memberFile.toString());
                }
                impl.upLoadPicture(this, memberFile.toString()); // 上传有水印图片
            }
        } else {
            faceErrorStart(); //跳转失败页面
        }
    }

    // 消课---保存数据
    @Override
    public void saveExitMessage(UserSaveMessageInfo userSaveMessageInfo) {
        Log.d(TAG, "消课完成" + userSaveMessageInfo.isSuccess());
        if (userSaveMessageInfo.isSuccess()) {
            stopCameraIntent(); //关闭相机
            setUserUi(); // 刷新UI
            Intent intent = new Intent(this, StartClassActivity.class);
            intent.putExtra("isStart", 2);
            startActivity(intent);
            finish();
        }
    }

//    // 消课---人脸识别是否成功
//    @Override
//    public void getMemberByCourse(UserOutFaceErrorListInfo userOutFaceErrorListInfo) {
//        if (userOutFaceErrorListInfo.getCode().equals("200")) {
//            UserOutFaceErrorListInfo.MessageBean message = userOutFaceErrorListInfo.getMessage();
//            isUserSuccess = true;
//            setUserUi();
//            if (byteArrayOutputStream != null) {
//                privatePass = 3;
//                final Bitmap rawbitmap = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(),
//                0, byteArrayOutputStream.size());
//                Bitmap bitmap = ImageUtil.drawTextToCenter(this, rawbitmap, message.getName(),
//                        26, getResources().getColor(R.color.img_color));
//                Bitmap bitmap1 = ImageUtil.drawTextToLeftBottom(this, bitmap, TimeUtil.getPictureCurrentTime(), 26,
//                        getResources().getColor(R.color.img_color), 30, 80);
//                File file = FileUtil.getFile(bitmap1);
//                impl.upLoadPicture(this, file.toString());
//            }
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        pauseRelease(); //释放资源
        if (signInAdapter != null) {
            List<SignInAdapter.MyViewHolder> myViewHolderList = SignInAdapter.getMyViewHolderList();
            myViewHolderList.clear();
            myViewHolderList = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseRelease(); // 释放资源
    }

    /**
     * 适配已签课列表
     *
     * @param list     list
     * @param check    check
     * @param position position
     */
    private void getPrivateCourse(List<UserOutListInfo.MessageBean> list, boolean check, int position) {
        if (list.size() > 0) {
            siginRecycle.setVisibility(View.VISIBLE);
            signInAdapter = new SignInAdapter(this, list, check, position);
            siginRecycle.setLayoutManager(new LinearLayoutManager(this));
            siginRecycle.setAdapter(signInAdapter);

            // 点击button选中状态
            signInAdapter.setOnItemListener(new SignInAdapter.onItemListener() {
                @Override
                public void onChilkListener(boolean isSelector, int id, int position) {
                    mOutUserId = id;
                    SPUtils.put(SignInActivity.this, "outUserId", id); //已签课的课程id存入sp
                    if (isSelector) {
                        // 点击签课
                        subscribeClick(tvOut, o -> {
                            isInCheck = false;
                            if (!isInCheck) { // 判断按钮只点击一次
                                onCheckUserBtn();
                            }
                        });
                    }
                }
            });

            signInAdapter.setOnItemChilkListener(new SignInAdapter.onItemChilkListener() {
                @Override
                public void onChilkListener(String mPrUrl, String mUserUrl, String time, boolean isCheck) {
                    if (isCheck) {
                        mPvUrl = mPrUrl;
                        mUserImgUrl = mUserUrl;
                        mTime = time;
                    }
                }
            });
        } else {
            Toast.makeText(this, "暂无可消课程！", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("startend", 1);
            startActivity(intent);
            finish();
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
     * 人脸识别或者查询未有私教和会员失败时跳转失败页面
     */
    private void faceErrorStart() {
        mErrorNum++;
        if (mErrorNum >= 5) {
            isInCheck = false;
            isBtnCheck = false;
            isPrSuccess = false;
            stopCameraIntent();
            if (!isPrivateSuccess) {  //  isPrivateError判断失败只走一次
                TestErrorActivity.startActivity(SignInActivity.this, TestErrorActivity.TEST_REQUESTCODE, flag);
            }
            if (isPrivateSuccess) {
                // 用手机号查询页面
                UserErrorActivity.startActivity(SignInActivity.this, UserErrorActivity.USER_REQUESTCODE, flag,
                        mPvUrl, mUserImgUrl, mTime);
            }
        }
    }

    /**
     * onpause/ondestory释放资源
     */
    private void pauseRelease() {
        isBtnCheck = false;
        isInCheck = false;
        isPrSuccess = false;
        if (handler != null) {
            handler.removeMessages(1);
            handler.removeMessages(2);
        }
        if (animaition != null) {
            animaition.stop();
        }
        stopCameta();
    }

    /**
     * 初始化时显示supSufvView
     */
    private void surViewShowInit() {
        siginRecycle.setVisibility(View.GONE);
        supSufviewIn.setVisibility(View.VISIBLE);
        supSufviewIn.setClickable(true);
        mCameraSurfaceHolder.setCameraSurfaceHolder(this, supSufviewIn);
    }

    /**
     * 点击用户按钮的操作
     */
    private void onCheckUserBtn() {
        startAnimtion();
        mCameraSurfaceHolder.setCameraSurfaceHolder(SignInActivity.this, supSufviewIn);
        isCheckUser = true; // 是否点击下课按钮
        isBtnCheck = true; // 判断请求成功或者失败，终止人脸调用
        mErrorNum = 0; // 人脸识别的请求次数
        isFaceSuccess = false; // 用来判断私教签课是否成功
        isPrSuccess = true; // 判断私教请求成功，不走人脸识别接口
    }

    /**
     * 关闭相机--页面销毁时
     */
    private void stopCameta() {
        if (mCameraSurfaceHolder != null) {
            try {
                supSufviewIn.getHolder().getSurface().release();
                mCameraSurfaceHolder.Stop();
            } catch (Exception e) {
                Log.d(TAG, "onDestroy--相机已关闭！");
            }
        }
    }

    /**
     * 关闭相机---页面跳转时
     */
    private void stopCameraIntent() {
        try {
            stopAnimtion();
            supSufviewIn.getHolder().getSurface().release();
            mCameraSurfaceHolder.Stop();
        } catch (Exception e) {
            Log.d(TAG, "相机已关闭!");
        }
    }

    /**
     * 给图片添加水印
     */
    private Bitmap pictDrawText() {
        final Bitmap rawbitmap = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(),
                0, byteArrayOutputStream.size());
        if (ContensUtils.getScrenn(this)) {
            Bitmap rotateImage = rotateImage(-90, rawbitmap);
            return rotateImage;
        }
        return rawbitmap;
    }
}
