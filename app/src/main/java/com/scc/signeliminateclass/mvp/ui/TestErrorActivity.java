package com.scc.signeliminateclass.mvp.ui;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.scc.signeliminateclass.MainActivity;
import com.scc.signeliminateclass.R;
import com.scc.signeliminateclass.adapter.ErrorRecycleAdapter;
import com.scc.signeliminateclass.adapter.SignInAdapter;
import com.scc.signeliminateclass.base.BaseMvpActivity;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.PrivateErrorListInfo;
import com.scc.signeliminateclass.bean.UserOutListInfo;
import com.scc.signeliminateclass.mvp.impl.TestErrorPresenterImpl;
import com.scc.signeliminateclass.mvp.uiinterface.TestErrorUiInterface;
import com.scc.signeliminateclass.utils.AppUtils;
import com.scc.signeliminateclass.utils.ContensUtils;
import com.scc.signeliminateclass.utils.FileUtil;
import com.scc.signeliminateclass.utils.ImageUtil;
import com.scc.signeliminateclass.utils.SPUtils;
import com.scc.signeliminateclass.utils.TimeUtil;
import com.scc.signeliminateclass.widget.CircleImageView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestErrorActivity extends BaseMvpActivity<TestErrorPresenterImpl> implements TestErrorUiInterface {

    private static final String TAG = "TestErrorActivity";
    @Inject
    TestErrorPresenterImpl impl;
    @BindView(R.id.text_sufview_in)
    CircleImageView testSufviewIn;
    @BindView(R.id.liear_error)
    LinearLayout liearError;
    @BindView(R.id.error_recycle)
    RecyclerView errorRecycle;
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
    @BindView(R.id.img_tips)
    ImageView imgTips;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.tv_picture)
    TextView tvPicture;

    /**
     * 选择相机
     */
    private static final int RC_CHOOSE_CAMERA = 101;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    @BindView(R.id.tv_test_user_name)
    TextView tvTestUserName;
    @BindView(R.id.error_recycle_out)
    RecyclerView errorRecycleOut;
    @BindView(R.id.img_back)
    ImageView imgBack;
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
    private static final int CROP_SMALL_PICTURE = 102;//裁剪
    /**
     * 小米裁剪回调
     */
    private static final int CROP_SMALL_PICTURE_MIUI = 103;//小米裁剪
    /**
     * uritempFile
     */
    private Uri uritempFile;

    //权限
    public static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;

    public static final int TEST_REQUESTCODE = 1;
    public static final int TEST_RESULTCODE = 2;

    private RelativeLayout mRelayoutItem;
    private int mId;
    private String mNickName;
    private int flag;

    private int mOutClassId;

    @Override
    protected TestErrorPresenterImpl initInjector() {
        component.inject(this);
        return impl;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_error;
    }

    @Override
    protected void findViews(Bundle savedInstanceState) {
        impl.setUiInterface(this);
        mRelayoutItem = findViewById(R.id.relat_item);
    }

    @Override
    protected void init() {
        // 保存拍照路径
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/" + System.currentTimeMillis() + ".jpg");
        if (!cameraSavePath.getParentFile().exists()) {
            cameraSavePath.getParentFile().mkdirs();
        }
        flag = getIntent().getIntExtra("flag", 0);
        subscribeClick(imgBack, o -> {
            finish();
        });
        // 请求接口---私教人脸识别失败，获取私教列表
        impl.getPrivateEmployee(this, AppUtils.ORG_ID, AppUtils.STORE_ID);
    }

    /**
     * 打开相机
     */
    public void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //改变Uri  com.xykj.customview.fileprovider注意和xml中的一致
            tempUri = FileProvider.getUriForFile(this, "com.scc.customview.fileprovider", cameraSavePath);
            //添加权限
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        } else {
            try {
                tempUri = Uri.fromFile(cameraSavePath); // 传递路径
                intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);// 更改系统默认存储路径
                startActivityForResult(intent, RC_CHOOSE_CAMERA);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RC_CHOOSE_CAMERA:
                if (permissions[0].equals(CAMERA_PERMISSION)) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Log.e(TAG, "===========权限回调---用户同意了");
                        startCamera();
                    }
                    break;
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_CHOOSE_CAMERA: // 相机回调
                photoClip(tempUri);  // 调用裁剪方法
                break;
            case CROP_SMALL_PICTURE: // 普通手机裁剪回调
                if (data != null) {
                    setImageToView(data);
                }
                break;
            case CROP_SMALL_PICTURE_MIUI:  // 小米适配----裁剪之后intent系统获取不到，只能显示裁剪之后的图片，而不是从intent中获取
                setImageMiuiToView();
                break;
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        if (Build.MODEL.contains("MI") || Build.MODEL.contains("Redmi 6A")) {
            uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/test/" + System.currentTimeMillis() + ".jpg");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection", true);
            startActivityForResult(intent, CROP_SMALL_PICTURE_MIUI);
            return;
        }
        try {
            startActivityForResult(intent, CROP_SMALL_PICTURE);
        } catch (ActivityNotFoundException exception) {
            Toast.makeText(this, "no activity", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "exception:" + exception.toString());
        }
    }


    /* * 保存裁剪之后的图片数据
     *
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            if (mNickName != null) {
                Bitmap bitmap = ImageUtil.drawTextToCenter(this, photo, mNickName,
                        26, getResources().getColor(R.color.img_color));
                Bitmap bitmap1 = ImageUtil.drawTextToLeftBottom(this, bitmap, TimeUtil.getPictureCurrentTime(), 26,
                        getResources().getColor(R.color.img_color), 30, 80);
                File file = FileUtil.getFile(bitmap1);
                impl.upLoadPicture(this, file.toString()); // 上传图片
            }

        }
    }

    // 将bitmap转化为string
    public String bitmapToString(Bitmap bitmap) {
        //将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    /* * 保存裁剪之后的图片数据----适配小米
     *
     * @param
     */
    protected void setImageMiuiToView() {
        //将Uri图片转换为Bitmap
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
//            img.setImageBitmap(mBitmap);
            File file = FileUtil.getFile(bitmap);
//            impl.updateImg(this, file.toString());
            Log.d("song", "保存剪裁之后小米file：" + file.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    //写入内存
    private void setFile(Bitmap photo) {
//        photoPath = Environment.getExternalStorageDirectory() + "/image2.jpg";
//        System.out.println("============pothphone" + photoPath);
        try {
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(cameraSavePath));
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bout);
            bout.flush();
            bout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // activity跳转----签课跳转
    public static void startActivity(Activity activity, int code, int flag) {
        Intent intent = new Intent(activity, TestErrorActivity.class);
        intent.putExtra("flag", flag);
        activity.startActivityForResult(intent, code);
    }

    // 私教失败列表请求成功
    @Override
    public void setPrivateList(PrivateErrorListInfo info) {
        errorRecycleOut.setVisibility(View.GONE);
        errorRecycle.setVisibility(View.VISIBLE);

        List<PrivateErrorListInfo.MessageBean> message = info.getMessage();
        ErrorRecycleAdapter errorRecycleAdapter = new ErrorRecycleAdapter(message, this);
        errorRecycle.setLayoutManager(new GridLayoutManager(this, 3));
        errorRecycle.setAdapter(errorRecycleAdapter);

        // 点击条目跳转
        errorRecycleAdapter.setOnItemChilkListenre(new ErrorRecycleAdapter.onItemChilkListenre() {
            @Override
            public void OnItemChilkListener(String nickName, String imgUrl, int id) {
                mId = id;
                mNickName = nickName;
                Log.d("song", "点击私教：" + mNickName + ":" + mId);
                SPUtils.put(TestErrorActivity.this, "nickName", nickName);
                // 列表隐藏，展示拍照按钮
                errorRecycle.setVisibility(View.GONE);
                tvPicture.setVisibility(View.VISIBLE);
                mRelayoutItem.setVisibility(View.VISIBLE);

                // 显示某一个私教的图片和名称
                Glide.with(TestErrorActivity.this).load(imgUrl).into(ivUser);
                tvTestUserName.setText(nickName);

                if (flag == 1) { //签课---就去点击拍照
                    subscribeClick(tvPicture, o -> {
                        if (ContensUtils.checkAndApplyfPermissionActivity(TestErrorActivity.this, new String[]{Manifest.permission.CAMERA,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                RC_CHOOSE_CAMERA)) {
                            startCamera();
                        }
                    });
                } else {
                    Log.d("song","点击课程");
                    // 消课--检查是否有课程--根据课程id
                    impl.getPrivateCourse(TestErrorActivity.this, AppUtils.ORG_ID, AppUtils.STORE_ID, String.valueOf(id));
                }
            }
        });
    }

    @Override
    public void setUpLoadPicture(PictureInfo pictureInfo) {
        Log.d("song", "testErrorActivity：" + ",:" + pictureInfo.getImage() + ",私教id：" + mId);
        // 拍照成功跳转到签课页面
        Intent intent = new Intent();
        intent.putExtra("primgUrl", pictureInfo.getImage());
        intent.putExtra("prId", mId);
        setResult(TEST_RESULTCODE, intent);
        finish();
    }

    @Override
    public void getPrivateCourse(UserOutListInfo outListInfo) {
        errorRecycle.setVisibility(View.GONE);
        errorRecycleOut.setVisibility(View.VISIBLE);
        List<UserOutListInfo.MessageBean> message = outListInfo.getMessage();
        Log.d("song","暂无可消课程:"+message.size());
        if (message.size() <= 0) {
            Toast.makeText(this, "暂无可消课程！", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            SignInAdapter signInAdapter = new SignInAdapter(this, message);
            errorRecycleOut.setLayoutManager(new LinearLayoutManager(this));
            errorRecycleOut.setAdapter(signInAdapter);

            signInAdapter.setOnItemListener(new SignInAdapter.onItemListener() {
                @Override
                public void onChilkListener(boolean isSelector, int id) {
                    mOutClassId = id;
                }
            });

            subscribeClick(tvPicture, o -> {
                if (ContensUtils.checkAndApplyfPermissionActivity(TestErrorActivity.this, new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        RC_CHOOSE_CAMERA)) {
                    startCamera();
                }
            });
        }

    }

}
