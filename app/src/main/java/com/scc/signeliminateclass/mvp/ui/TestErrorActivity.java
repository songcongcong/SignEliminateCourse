package com.scc.signeliminateclass.mvp.ui;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
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
import com.scc.signeliminateclass.utils.SPUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * TestErrorActivity
 */
public class TestErrorActivity extends BaseMvpActivity<TestErrorPresenterImpl> implements TestErrorUiInterface {
    /**
     * TAG
     */
    private static final String TAG = "TestErrorActivity";
    /**
     * 添加注解
     */
    @Inject
    TestErrorPresenterImpl impl;
    /**
     * testSufviewIn
     */
    @BindView(R.id.text_sufview_in)
    ImageView testSufviewIn;
    /**
     * liearError
     */
    @BindView(R.id.liear_error)
    LinearLayout liearError;
    /**
     * errorRecycle
     */
    @BindView(R.id.error_recycle)
    RecyclerView errorRecycle;
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
     * liearIn
     */
    @BindView(R.id.liear_in)
    LinearLayout liearIn;
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
     * learOut
     */
    @BindView(R.id.lear_out)
    LinearLayout learOut;
    /**
     * viewSelector
     */
    @BindView(R.id.view_selector)
    CheckBox viewSelector;
    /**
     * imgTips
     */
    @BindView(R.id.img_tips)
    ImageView imgTips;
    /**
     * tvTips
     */
    @BindView(R.id.tv_tips)
    TextView tvTips;
    /**
     * tvPicture
     */
    @BindView(R.id.tv_picture)
    TextView tvPicture;

    /**
     * 选择相机
     */
    private static final int RC_CHOOSE_CAMERA = 101;
    /**
     * ivUser
     */
    @BindView(R.id.iv_user)
    ImageView ivUser;
    /**
     * tvTestUserName
     */
    @BindView(R.id.tv_test_user_name)
    TextView tvTestUserName;
    /**
     * errorRecycleOut
     */
    @BindView(R.id.error_recycle_out)
    RecyclerView errorRecycleOut;
    /**
     * imgBack
     */
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
     * 相机权限
     */
    public static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;
    /**
     * test - 请求码
     */
    public static final int TEST_REQUESTCODE = 1;
    /**
     * test - 结果码
     */
    public static final int TEST_RESULTCODE = 2;
    /**
     * mRelayoutItem
     */
    private RelativeLayout mRelayoutItem;
    /**
     * mId
     */
    private int mId;
    /**
     * mNickName
     */
    private String mNickName;
    /**
     * flag
     */
    private int flag;
    /**
     * mOutClassId
     */
    private int mOutClassId;
    /**
     * mList
     */
    private List<UserOutListInfo.MessageBean> mList;
    /**
     * isChecked
     */
    private boolean isChecked;
    /**
     * mPosition
     */
    private int mPosition;
    /**
     * inputStream
     */
    private InputStream inputStream;

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
        mList = new ArrayList<>();
        subscribeClick(imgBack, o -> {
            finish();
        });
        if (flag == 1) {
            tvPrivateName.setText(getResources().getString(R.string.private_class_name));
            tvUserName.setText(getResources().getString(R.string.user_class_name));
        } else {
            tvPrivateName.setText(getResources().getString(R.string.private_out_class_name));
            tvUserName.setText(getResources().getString(R.string.user_out_class_name));
        }
        // 请求接口---私教人脸识别失败，获取私教列表
        impl.getPrivateEmployee(this, AppUtils.ORG_ID, AppUtils.STORE_ID, "1", "10000");
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
                intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri); // 更改系统默认存储路径
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
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_CHOOSE_CAMERA: // 相机回调
//                photoClip(tempUri);  // 调用裁剪方法
                if (data != null) {
                    setImageToView(data);
                } else {
//                    Bitmap bitmap = adjustImage(cameraSavePath.getAbsolutePath());
                    Bitmap bitmap = BitmapFactory.decodeFile(cameraSavePath.toString());
                    setImageNllToView(bitmap);
                }
                break;
            case CROP_SMALL_PICTURE: // 普通手机裁剪回调
                if (data != null) {
                    setImageToView(data);
                }
                break;
            case CROP_SMALL_PICTURE_MIUI:  // 小米适配----裁剪之后intent系统获取不到，只能显示裁剪之后的图片，而不是从intent中获取
                setImageMiuiToView();
                break;
            default:
                break;
        }
    }

    /**
     * 压缩图片
     *
     * @param absolutePath 是图片绝对路径
     * @return Bitmap
     */
    private Bitmap adjustImage(String absolutePath) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        // 这个isjustdecodebounds很重要
        opt.inJustDecodeBounds = true;

        // 获取到这个图片的原始宽度和高度
        int picWidth = opt.outWidth;
        int picHeight = opt.outHeight;

        // 获取屏的宽度和高度
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();

        // isSampleSize是表示对图片的缩放程度，比如值为2图片的宽度和高度都变为以前的1/2
        opt.inSampleSize = 1;
        // 根据屏的大小和图片大小计算出缩放比例
        if (picWidth > picHeight) {
            if (picWidth > screenWidth) {
                opt.inSampleSize = picWidth / screenWidth;
            }
        } else {
            if (picHeight > screenHeight) {

                opt.inSampleSize = picHeight / screenHeight;
            }
        }

        // 这次再真正地生成一个有像素的，经过缩放了的bitmap
        opt.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile(absolutePath, opt);
        return bm;
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri uri
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
            uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/test/"
                    + System.currentTimeMillis() + ".jpg");
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


    /**
     * 保存裁剪之后的图片数据
     *
     * @param data data
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            if (mNickName != null) {
                File file = AppUtils.drawTextPicture(this, photo, mNickName);
                impl.upLoadPicture(this, file.toString()); // 上传图片
            }

        }
    }

    /**
     * 相机回调图片为空
     *
     * @param mbitmap mbitmap
     */
    protected void setImageNllToView(Bitmap mbitmap) {
        if (mNickName != null) {
            File file = AppUtils.drawTextPicture(this, mbitmap, mNickName);
            impl.upLoadPicture(this, file.toString()); // 上传图片
        }
    }

    /**
     * 将bitmap转化为string
     *
     * @param bitmap bitmap
     * @return string
     */
    public String bitmapToString(Bitmap bitmap) {
        //将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    /**
     * 保存裁剪之后的图片数据----适配小米
     */
    protected void setImageMiuiToView() {
        //将Uri图片转换为Bitmap
        Bitmap bitmap = null;
        try {
            inputStream = getContentResolver().openInputStream(uritempFile);
            bitmap = BitmapFactory.decodeStream(inputStream);
//            img.setImageBitmap(mBitmap);
            File file = FileUtil.getFile(bitmap);
//            impl.updateImg(this, file.toString());
            Log.d("song", "保存剪裁之后小米file：" + file.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 写入内存
     *
     * @param photo photo
     */
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

    /**
     * activity跳转----签课跳转
     *
     * @param activity activity
     * @param code     code
     * @param flag     flag
     */
    public static void startActivity(Activity activity, int code, int flag) {
        Intent intent = new Intent(activity, TestErrorActivity.class);
        intent.putExtra("flag", flag);
        activity.startActivityForResult(intent, code);
    }

    /**
     * 私教失败列表请求成功
     *
     * @param info info
     */
    @Override
    public void setPrivateList(PrivateErrorListInfo info) {
        errorRecycleOut.setVisibility(View.GONE);
        errorRecycle.setVisibility(View.VISIBLE);

        List<PrivateErrorListInfo.MessageBean> message = info.getMessage();
        if (message.size() > 0) {
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
                            if (ContensUtils.checkAndApplyfPermissionActivity(TestErrorActivity.this,
                                    new String[]{Manifest.permission.CAMERA,
                                            Manifest.permission.READ_EXTERNAL_STORAGE,
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    RC_CHOOSE_CAMERA)) {
                                startCamera();
                            }
                        });
                    } else {
                        Log.d("song", "点击课程");
                        // 消课--检查是否有课程--根据课程id
                        impl.getPrivateCourse(TestErrorActivity.this, AppUtils.ORG_ID,
                                AppUtils.STORE_ID, String.valueOf(id));
                    }
                }
            });
        }

    }

    @Override
    public void setUpLoadPicture(PictureInfo pictureInfo) {
        Log.d("song", "testErrorActivity：" + ",:" + pictureInfo.getImage()
                + ",私教id：" + mId + ",:" + mPosition + ",:" + isChecked);
        // 拍照成功跳转到签课页面
        Intent intent = new Intent();
        intent.putExtra("primgUrl", pictureInfo.getImage());
        intent.putExtra("prId", mId);
        intent.putExtra("mListIntent", (Serializable) mList);
        intent.putExtra("check", isChecked);
        intent.putExtra("position", mPosition);
        setResult(TEST_RESULTCODE, intent);
        finish();
    }

    @Override
    public void getPrivateCourse(UserOutListInfo outListInfo) {
        errorRecycle.setVisibility(View.GONE);
        errorRecycleOut.setVisibility(View.VISIBLE);
        List<UserOutListInfo.MessageBean> message = outListInfo.getMessage();
        Log.d("song", "暂无可消课程:" + message.size());
        if (message.size() <= 0) {
            Toast.makeText(this, "暂无可消课程！", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("startend", 1);
            startActivity(intent);
            finish();
        } else {
            SignInAdapter signInAdapter = new SignInAdapter(this, message);
            errorRecycleOut.setLayoutManager(new LinearLayoutManager(this));
            errorRecycleOut.setAdapter(signInAdapter);
            mList = signInAdapter.getmList();
            signInAdapter.setOnItemListener(new SignInAdapter.onItemListener() {
                @Override
                public void onChilkListener(boolean isSelector, int id, int position) {
                    mOutClassId = id;
                    isChecked = isSelector;
                    mPosition = position;
                }
            });

            subscribeClick(tvPicture, o -> {
                if (ContensUtils.checkAndApplyfPermissionActivity(TestErrorActivity.this,
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        RC_CHOOSE_CAMERA)) {
                    startCamera();
                }
            });
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
