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
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.scc.signeliminateclass.R;
import com.scc.signeliminateclass.adapter.SignInAdapter;
import com.scc.signeliminateclass.adapter.UserEClassErrorAdapter;
import com.scc.signeliminateclass.adapter.UserErrorAdapter;
import com.scc.signeliminateclass.base.BaseMvpActivity;
import com.scc.signeliminateclass.bean.PictureInfo;
import com.scc.signeliminateclass.bean.UserOutFaceErrorListInfo;
import com.scc.signeliminateclass.bean.UserOutListInfo;
import com.scc.signeliminateclass.bean.UserPhoneListInfo;
import com.scc.signeliminateclass.mvp.impl.UserErrorPresenterImpl;
import com.scc.signeliminateclass.mvp.uiinterface.UserUiInterface;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserErrorActivity extends BaseMvpActivity<UserErrorPresenterImpl> implements UserUiInterface {

    private static final String TAG = "UserErrorActivity";
    @Inject
    UserErrorPresenterImpl impl;
    @BindView(R.id.user_error_surface)
    CircleImageView userErrorSurface;
    @BindView(R.id.img_tips)
    ImageView imgTips;
    @BindView(R.id.tv_error_name)
    TextView tvTvErrorName;
    @BindView(R.id.liear_error_user)
    LinearLayout liearErrorUser;
    @BindView(R.id.ed_user_phone)
    EditText edUserPhone;
    @BindView(R.id.user_error_recycleview)
    RecyclerView userErrorRecycleview;
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

    /**
     * activity请求码
     */
    public static final int USER_REQUESTCODE = 5;

    /**
     * activity结果码
     */
    public static final int USER_RESULTCODE = 6;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.img_error_user)
    ImageView imgErrorUser;
    @BindView(R.id.relat_user_item)
    RelativeLayout relatUserItem;
    @BindView(R.id.tv_picture)
    TextView tvPicture;


    /**
     * 选择相机
     */
    private static final int RC_CHOOSE_CAMERA = 101;
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
    /**
     * 选择图片
     */
    private static final int RC_CHOOSE_PHOTO = 104;

    //权限
    public static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;
    public static final String NETWORK_PERMISSION = Manifest.permission.ACCESS_NETWORK_STATE;

    private List<UserOutFaceErrorListInfo.MessageBean> mList = new ArrayList();

    @Override
    protected UserErrorPresenterImpl initInjector() {
        component.inject(this);
        return impl;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_error;
    }

    @Override
    protected void findViews(Bundle savedInstanceState) {
        impl.setUiInterface(this);
    }

    @Override
    protected void init() {
        // 进入用户检测失败，私教的状态进行改变
        setPrivateUi();
        // 保存拍照路径
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/" + System.currentTimeMillis() + ".jpg");
        if (!cameraSavePath.getParentFile().exists()) {
            cameraSavePath.getParentFile().mkdirs();
        }


        List<UserOutListInfo.MessageBean> prurl = (List<UserOutListInfo.MessageBean>) getIntent().getSerializableExtra("prurl");

        int privated = (int) SPUtils.get(this, "orgId", 0);
        Log.d("song", "用户activity：" + privated);
        int outClassId = (int) SPUtils.get(this, "outUserId", 0);//已签课的课程id存入sp
//        impl.getMemberByCourse(this, AppUtils.ORG_ID, AppUtils.STORE_ID, String.valueOf(outClassId));
        String nickName = (String) SPUtils.get(this, "nickName", "");
        mName = nickName;
        Log.d("song", "用户activity----取值：" + privated+",:"+mName);

        // 来自那个一个activity
        int flag = getIntent().getIntExtra("flag", 0);
        if (flag == 1) {
            liearErrorUser.setVisibility(View.VISIBLE);
            edUserPhone.setVisibility(View.VISIBLE);
            // 输入手机号查询会员列表
            edUserPhone.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (!TextUtils.isEmpty(edUserPhone.getText().toString().trim())) {
                        impl.getUserPhoneList(UserErrorActivity.this, AppUtils.ORG_ID, AppUtils.STORE_ID, edUserPhone.getText().toString().trim());
                    }
                }
            });
        } else {
            if (privated != 0) {
                Log.d("song", "用户activity----不等于0：" + privated);
                liearErrorUser.setVisibility(View.GONE);
                edUserPhone.setVisibility(View.GONE);
                userErrorRecycleview.setVisibility(View.VISIBLE);
                tvPicture.setVisibility(View.VISIBLE);
                if (prurl != null && prurl.size() > 0) {
                    SignInAdapter signInAdapter = new SignInAdapter(this, prurl);
                    userErrorRecycleview.setLayoutManager(new LinearLayoutManager(this));
                    userErrorRecycleview.setAdapter(signInAdapter);
                }
                subscribeClick(tvPicture, o -> {
                    if (ContensUtils.checkAndApplyfPermissionActivity(UserErrorActivity.this, new String[]{Manifest.permission.CAMERA,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            RC_CHOOSE_CAMERA)) {
//                        mCameraSurfaceHolder.Stop();
                        startCamera();
                        Log.d("song", "点击用户条目监听");
                    }
                });
            }
        }
        subscribeClick(imgBack, o -> {
            finish();
        });
    }

    // 改变签课签到,
    private void setPrivateUi() {
        checkboxIn.setSelected(true);
        tvPrivateName.setSelected(true);
        tvPrivateTime.setVisibility(View.VISIBLE);
        String currentTime = (String) SPUtils.get(this, "currentTime", "");
        tvPrivateTime.setText(currentTime);
    }


    // activity跳转----签课跳转
    public static void startActivity(Activity activity, int code, int flag, List<UserOutListInfo.MessageBean> prurl) {
        Intent intent = new Intent(activity, UserErrorActivity.class);
        intent.putExtra("flag", flag);
        intent.putExtra("prurl", (Serializable) prurl);
        activity.startActivityForResult(intent, code);
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
            tempUri = Uri.fromFile(cameraSavePath); // 传递路径
            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);// 更改系统默认存储路径
        }
        try {
            startActivityForResult(intent, RC_CHOOSE_CAMERA);
        } catch (Exception e) {
            Log.d("song", "相机：" + e.toString());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RC_CHOOSE_CAMERA:
                if (permissions[0].equals(CAMERA_PERMISSION)) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Log.e(TAG, "===========权限回调---用户同意了");
//                        getRequest(flag);
//                        mCameraSurfaceHolder.Stop();
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
            if (mName != null) {
                Bitmap bitmap = ImageUtil.drawTextToCenter(this, photo, mName,
                        26, getResources().getColor(R.color.img_color));
                Bitmap bitmap1 = ImageUtil.drawTextToLeftBottom(this, bitmap, TimeUtil.getPictureCurrentTime(), 26,
                        getResources().getColor(R.color.img_color), 30, 80);
                File file = FileUtil.getFile(bitmap1);
                impl.upLoadPicture(this, file.toString());
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

    private int mUserId;
    private String mName;

    // 根据手机号查询，查询成功结果
    @Override
    public void setUserPhoneList(UserPhoneListInfo phoneList) {

        List<UserPhoneListInfo.MessageBean> message = phoneList.getMessage();
        // 网络请求成功，适配数据
        UserErrorAdapter userErrorAdapter = new UserErrorAdapter(this, message);
        userErrorRecycleview.setLayoutManager(new GridLayoutManager(this, 3));
        userErrorRecycleview.setAdapter(userErrorAdapter);

        // 点击条目跳转
        userErrorAdapter.setOnItemChilkListenre(new UserErrorAdapter.onItemChilkListenre() {
            @Override
            public void OnItemChilkListener(String name, String avatar, int id) {
                mUserId = id;
                mName = name;
                Log.d("song", "点击会员：" + mName + ",:" + id);
                userErrorRecycleview.setVisibility(View.GONE);
                edUserPhone.setVisibility(View.GONE);
                tvPicture.setVisibility(View.VISIBLE);
                relatUserItem.setVisibility(View.VISIBLE);

                // 点击显示某一个item
                Glide.with(UserErrorActivity.this).load(avatar).into(imgErrorUser);
                tvTvErrorName.setText(name);

                subscribeClick(tvPicture, o -> {
                    if (ContensUtils.checkAndApplyfPermissionActivity(UserErrorActivity.this, new String[]{Manifest.permission.CAMERA,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            RC_CHOOSE_CAMERA)) {
                        startCamera();
                        Log.d("song", "点击条目监听");
                    }
                });
            }
        });

    }

    @Override
    public void setUpLoadPicture(PictureInfo pictureInfo) {
        Log.d("song", "用户返回图片：" + ",:" + pictureInfo.getImage() + ",:" + mUserId);
        // 拍照成功跳转到签课页面
        Intent intent = new Intent();
        intent.putExtra("userImg", pictureInfo.getImage());
        intent.putExtra("userId", mUserId);
        setResult(USER_RESULTCODE, intent);
        finish();
    }
//
//    // 消课---查询用户列表
//    @Override
//    public void getMemberByCourse(UserOutFaceErrorListInfo userOutFaceErrorListInfo) {
//        UserOutFaceErrorListInfo.MessageBean message = userOutFaceErrorListInfo.getMessage();
//        if (message != null) {
//            mList.add(message);
//            UserEClassErrorAdapter userEClassErrorAdapter = new UserEClassErrorAdapter(this, mList);
//            userErrorRecycleview.setLayoutManager(new GridLayoutManager(this, 3));
//            userErrorRecycleview.setAdapter(userEClassErrorAdapter);
//            userEClassErrorAdapter.setOnItemChilkListenre(new UserEClassErrorAdapter.onItemChilkListenre() {
//                @Override
//                public void OnItemChilkListener(String name, String avatar) {
//                    mName = name;
//                    userErrorRecycleview.setVisibility(View.GONE);
//                    tvPicture.setVisibility(View.VISIBLE);
//                    relatUserItem.setVisibility(View.VISIBLE);
//
//                    // 点击显示某一个item
//                    Glide.with(UserErrorActivity.this).load(avatar).into(imgErrorUser);
//                    tvTvErrorName.setText(name);
//
//                    subscribeClick(tvPicture, o -> {
//                        if (ContensUtils.checkAndApplyfPermissionActivity(UserErrorActivity.this, new String[]{Manifest.permission.CAMERA,
//                                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                RC_CHOOSE_CAMERA)) {
////                        mCameraSurfaceHolder.Stop();
//                            startCamera();
//                            Log.d("song", "点击用户条目监听");
//                        }
//                    });
//                }
//            });
//        }
//
//    }

}
