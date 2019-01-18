package com.ylean.cf_hospitalapp.my.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.IPicPresenter;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.DataUploadView;
import com.ylean.cf_hospitalapp.inquiry.bean.DataUploadResultEntry;
import com.ylean.cf_hospitalapp.my.bean.BindEntry;
import com.ylean.cf_hospitalapp.my.bean.MyInfoEntry;
import com.ylean.cf_hospitalapp.my.presenter.ISettingPres;
import com.ylean.cf_hospitalapp.my.view.ISettingView;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.Constant;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.ActionSheetDialog;
import com.ylean.cf_hospitalapp.widget.EnterItemView;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 个人设置
 * Created by linaidao on 2019/1/2.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener, ISettingView, DataUploadView {

    private android.widget.ImageView ivarr;
    private com.facebook.drawee.view.SimpleDraweeView ivPhoto;
    private android.widget.RelativeLayout rlHeadPic;
    private com.ylean.cf_hospitalapp.widget.EnterItemView eivSex;
    private com.ylean.cf_hospitalapp.widget.EnterItemView bindTel;
    private com.ylean.cf_hospitalapp.widget.EnterItemView wechat;
    private com.ylean.cf_hospitalapp.widget.EnterItemView modifyPwd;
    private android.widget.ImageView ivNotice;
    private android.widget.RelativeLayout rlNotice;
    private com.ylean.cf_hospitalapp.widget.EnterItemView update;
    private com.ylean.cf_hospitalapp.widget.EnterItemView aboutUs;
    private static final int REQUEST_PERMISSION_CAMERA_CODE = 0x20;
    private static final int REQUEST_PERMISSION_WRITE_CODE = 0x21;

    private String currentSex;

    private ISettingPres iSettingPres = new ISettingPres(this);
    private IPicPresenter iPicPresenter = new IPicPresenter(this);
    private MyInfoEntry.DataBean myInfoEntryData;
    private EnterItemView tvnickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_setting);

        myInfoEntryData = getIntent().getParcelableExtra("myInfoEntryData");
        initView();
        iSettingPres.bindInfo((String) SaveUtils.get(this, SpValue.TOKEN, ""));
    }

    private void initView() {

        this.aboutUs = (EnterItemView) findViewById(R.id.aboutUs);
        this.update = (EnterItemView) findViewById(R.id.update);
        tvnickname = (EnterItemView) findViewById(R.id.tvnickname);
        this.rlNotice = (RelativeLayout) findViewById(R.id.rlNotice);
        this.ivNotice = (ImageView) findViewById(R.id.ivNotice);
        this.modifyPwd = (EnterItemView) findViewById(R.id.modifyPwd);
        this.wechat = (EnterItemView) findViewById(R.id.wechat);
        this.bindTel = (EnterItemView) findViewById(R.id.bindTel);
        this.eivSex = (EnterItemView) findViewById(R.id.sex);
        this.rlHeadPic = (RelativeLayout) findViewById(R.id.rlHeadPic);
        this.ivPhoto = (SimpleDraweeView) findViewById(R.id.ivPhoto);
        this.ivarr = (ImageView) findViewById(R.id.iv_arr);
        TextView tv_submit = findViewById(R.id.tv_submit);

        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        aboutUs.setOnClickListener(this);
        update.setOnClickListener(this);
        rlNotice.setOnClickListener(this);
        modifyPwd.setOnClickListener(this);
        wechat.setOnClickListener(this);
        bindTel.setOnClickListener(this);
        eivSex.setOnClickListener(this);
        rlHeadPic.setOnClickListener(this);
        tvnickname.setOnClickListener(this);
        tv_submit.setOnClickListener(this);

        if (myInfoEntryData != null) {
            ivPhoto.setImageURI(Uri.parse(ApiService.WEB_ROOT + myInfoEntryData.getImgurl()));
            eivSex.setRightTxt(SpValue.MY_SEX_FEMALE.equals(myInfoEntryData.getSex()) ? "女" : "男");
            bindTel.setRightTxt(myInfoEntryData.getMobile());
            tvnickname.setRightTxt(myInfoEntryData.getRealname());
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvnickname://昵称

                Intent n = new Intent(this, ChangeNicknameActivity.class);
                if (myInfoEntryData != null)
                    n.putExtra("nickname", myInfoEntryData.getNickname());
                startActivityForResult(n, 0x91);

                break;

            case R.id.aboutUs:
                break;

            case R.id.update:
                break;

            case R.id.rlNotice:
                break;

            case R.id.modifyPwd://修改密码

                Intent i = new Intent(this, ChangePwdActivity.class);
                if (myInfoEntryData != null)
                    i.putExtra("tel", myInfoEntryData.getMobile());
                startActivity(i);

                break;

            case R.id.wechat://绑定第三方登录

                showThirdLogin();
                break;

            case R.id.bindTel://绑定手机号
                Intent m = new Intent(this, TelBindAct.class);
                if (myInfoEntryData != null)
                    m.putExtra("tel", myInfoEntryData.getMobile());
                startActivity(m);
                break;

            case R.id.sex://修改性别
                showSixChoose();
                break;

            case R.id.rlHeadPic://修改头像
                showPicChoose();
                break;

            case R.id.tv_submit://退出登录

                exitApp();

                break;

        }

    }

    //第三方登录
    private void showThirdLogin() {

        new ActionSheetDialog(this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("微信", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                UMShareAPI.get(SettingActivity.this).getPlatformInfo(SettingActivity.this, SHARE_MEDIA.WEIXIN, umAuthListener);//weix
                            }

                        })
                .addSheetItem("QQ", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                UMShareAPI.get(SettingActivity.this).getPlatformInfo(SettingActivity.this, SHARE_MEDIA.QQ, umAuthListener);//QQ登录
                            }
                        })
                .addSheetItem("微博", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                UMShareAPI.get(SettingActivity.this).getPlatformInfo(SettingActivity.this, SHARE_MEDIA.SINA, umAuthListener);//weibo
                            }
                        }).show();

    }

    //退出app
    private void exitApp() {

        String token = (String) SaveUtils.get(this, SpValue.TOKEN, "");
        SharedPreferences sp = getSharedPreferences(SaveUtils.CONFIG, MODE_PRIVATE);
        sp.edit().clear().apply();

        iSettingPres.exitApp(token);

    }

    private void showSixChoose() {

        new ActionSheetDialog(this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("男", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                // 男
                                iSettingPres.updateInfo((String) SaveUtils.get(SettingActivity.this, SpValue.TOKEN, "")
                                        , "", "", "", SpValue.MY_SEX_MALE, "", "");

                            }

                        })
                .addSheetItem("女", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                // 女
                                iSettingPres.updateInfo((String) SaveUtils.get(SettingActivity.this, SpValue.TOKEN, "")
                                        , "", "", "", SpValue.MY_SEX_FEMALE, "", "");
                            }
                        }).show();

    }

    /**
     * 弹出选择照片的对话框
     */
    @Deprecated
    protected void showPicChoose() {

        new ActionSheetDialog(this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                // 拍照
                                checkPicPer();

                            }

                        })
                .addSheetItem("去相册选择", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                // 从相册中选择
                                checkWritePermission();
                            }
                        }).show();

    }

    //获取拍照权限
    private void checkPicPer() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            iSettingPres.getTakePhoto(ISettingPres.TAKE_PHOTO_CODE, this);

        } else {
            //不具有权限，需要进行权限申请
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    REQUEST_PERMISSION_CAMERA_CODE);
        }
    }


    //获取读写权限
    private void checkWritePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //不具有权限，需要进行权限申请
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE_CODE);
        } else {
            iSettingPres.getFromPhotoAlbum(ISettingPres.PHOTO_ALBUM_CODE, SettingActivity.this);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case REQUEST_PERMISSION_CAMERA_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    iSettingPres.getTakePhoto(ISettingPres.TAKE_PHOTO_CODE, this);
                }
                break;

            case REQUEST_PERMISSION_WRITE_CODE:
                iSettingPres.getFromPhotoAlbum(ISettingPres.PHOTO_ALBUM_CODE, SettingActivity.this);

                break;
        }

    }

    private String cacheDir = Environment.getExternalStorageDirectory().getPath() + Constant.PHOTO_PATH;
    private File outFile;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 0x91:

                if (resultCode == 0x90) {
                    if (data != null) {
                        String nickname = data.getStringExtra("nickname");
                        iSettingPres.updateInfo((String) SaveUtils.get(SettingActivity.this, SpValue.TOKEN, "")
                                , "", nickname, "", "", "", "");
                    }
                }

                break;

            case ISettingPres.TAKE_PHOTO_CODE://拍照返回的code, 裁剪

                try {
                    if (iSettingPres.getCurrentFile() != null && iSettingPres.getCurrentFile().exists()) {

                        File photoFile = new File(cacheDir);
                        //文件夹不存在就创建文件夹
                        if (!photoFile.exists())
                            photoFile.mkdirs();

                        outFile = new File(photoFile.getPath(), UUID.randomUUID().toString() + ".jpg");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) //如果大于等于7.0使用FileProvider
                            iSettingPres.startPhotoZoom(
                                    FileProvider.getUriForFile(this, Constant.FILE_URL, iSettingPres.getCurrentFile())
                                    , outFile);//裁剪
                        else
                            iSettingPres.startPhotoZoom(Uri.fromFile(iSettingPres.getCurrentFile()), outFile);//裁剪

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case ISettingPres.PHOTO_RESOULT://裁剪后的图片

                if (outFile == null)
                    return;

                List<String> pic = new ArrayList<>();
                pic.add(outFile.getPath());
                iPicPresenter.uploadPic(pic);
//                upload_pic(this, outFile, get_user_img_upload_url());
                break;

            case ISettingPres.PHOTO_ALBUM_CODE://相册选择返回的code
                if (data == null)
                    return;
                File photoFile = new File(cacheDir);
                //文件夹不存在就创建文件夹
                if (!photoFile.exists())
                    photoFile.mkdirs();

                outFile = new File(photoFile, UUID.randomUUID().toString() + ".jpg");

                iSettingPres.startPhotoZoom(iSettingPres.checkSelectPhoto(this, data, this), outFile);//开始裁剪
//                iSettingPresenter.startPhotoZoom(FileProvider.getUriForFile(this, "com.kungfuhacking.wristbandpro.fileprovider", iSettingPresenter.getCurrentFile()));//开始裁剪

                break;

//            case MODIFY_MY_NAME_CODE://修改昵称
//
//                if (data == null)
//                    return;
//
//                iSettingPresenter.upDateUserInfo(data.getStringExtra("nickname"), sp.getString(Constant.BIRTHDAY, "")
//                        , sp.getString(Constant.SEX, ""), sp.getString(Constant.USER_IMG, ""));
//
//                break;
//
        }

    }

    @Override
    public void picUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {
        //图片上传成功
        //TODO 请求接口修改个人信息数据
        iSettingPres.updateInfo((String) SaveUtils.get(this, SpValue.TOKEN, ""),
                dataUploadResultEntry.getData().get(0), "", "", "", "", "");
//        ivPhoto.setImageURI(Uri.parse(ApiService.WEB_ROOT + dataUploadResultEntry.getData().get(0)));

    }

    @Override
    public void voiceUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {

    }

    @Override
    public void setImg(String imgUrl) {
        ivPhoto.setImageURI(imgUrl);
    }

    @Override
    public void setName(String name) {

        tvnickname.setRightTxt(name);
    }

    @Override
    public void setBirthday(String birthday) {

    }

    @Override
    public void setSex(String sex) {
        eivSex.setRightTxt(SpValue.MY_SEX_FEMALE.equals(sex) ? "女" : "男");
    }

    @Override
    public void setLocation(String location) {

    }

    @Override
    public void setAddress(String address) {

    }

    @Override
    public void bindInfo(BindEntry.DataBean data) {

        if (data.getQqstatus() == 1) {
            wechat.setRightTxt("已绑定");
            wechat.setTxt("QQ");
        } else if (data.getWbstatus() == 1) {
            wechat.setRightTxt("已绑定");
            wechat.setTxt("微博");
        } else if (data.getWxstatus() == 1) {
            wechat.setRightTxt("已绑定");
            wechat.setTxt("微信");
        } else {
            wechat.setRightTxt("未绑定");
            wechat.setTxt("微信");
        }

    }

    UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

            //   dtype  0-微信 1-扣扣 2-微博
            switch (share_media) {
                case QQ:

                    iSettingPres.bindThirdLogin((String) SaveUtils.get(SettingActivity.this, SpValue.TOKEN, "")
                            , map.get("openid"), "1", map.get("name"), map.get("gender"), map.get("iconurl"));
                    break;
                case WEIXIN:
                    iSettingPres.bindThirdLogin((String) SaveUtils.get(SettingActivity.this, SpValue.TOKEN, "")
                            , map.get("openid"), "0", map.get("name"), map.get("gender"), map.get("iconurl"));
                    break;
                case SINA:
                    iSettingPres.bindThirdLogin((String) SaveUtils.get(SettingActivity.this, SpValue.TOKEN, "")
                            , map.get("id"), "2", map.get("name"), map.get("gender"), map.get("iconurl"));
                    break;

            }

//
///**
// *  String uid = map.get("uid");
// String openid = map.get("openid");//微博没有
// String unionid = map.get("unionid");//微博没有
// String access_token = map.get("access_token");
// String refresh_token = map.get("refresh_token");//微信,qq,微博都没有获取到
// String expires_in = map.get("expires_in");
// String name = map.get("name");
// String gender = map.get("gender");
// String iconurl = map.get("iconurl");
//
// */
//            String openId = map.get("uid");
//            String name = map.get("name");
//            String iconurl = map.get("iconurl");
//
//
//            //qq
//            String gender = map.get("gender");//性别  qq登录
//            String openid = map.get("openid");//qq登录  Key = openid, Value = E510C8632D72B7FD8846F3831869C975
//
//            //Key = profile_image_url, Value = http://thirdqq.qlogo.cn/qqapp/1106735139/E510C8632D72B7FD8846F3831869C975/100
//            //Key = iconurl, Value = http://thirdqq.qlogo.cn/qqapp/1106735139/E510C8632D72B7FD8846F3831869C975/100
//            String profile_image_url = map.get("profile_image_url");
////            String iconurl = map.get("iconurl");
//            //Key = accessToken, Value = 85DC4F34DD5A9C0D2FF2D4DEAAA57176
//            String accessToken = map.get("accessToken");
//            String access_token = map.get("access_token");//与accessToken同值
//            //Key = uid, Value = E510C8632D72B7FD8846F3831869C975
//            String uid = map.get("uid");
//            //Key = screen_name, Value = Aaron
//            // Key = name, Value = Aaron
//            String screen_name = map.get("screen_name");
//            // Key = expiration, Value = 1554869795077
//            String expiration = map.get("expiration");
//            // Key = expires_in, Value = 1554869795077
//            String expires_in = map.get("expires_in");
//
//
//            //微博
//            /*
//             *
//             *  Key = id, Value = 5928335579
//             *  Key = screen_name, Value = AutoMasterLee
//             *  Key = accessToken, Value = 2.00ntgMTG06PphWc9b7ceffffZ23OAE
//             *  Key = access_token, Value = 2.00ntgMTG06PphWc9b7ceffffZ23OAE
//             *  Key = name, Value = AutoMasterLee
//             *  Key = expiration, Value = 1549738799441
//             *  Key = gender, Value = 男
//             *
//             */

        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    @Override
    public void exitSuccess() {
        finish();
        EventBus.getDefault().post("exit");
    }

    @Override
    public void bindSuccess(String name, String gender, String iconurl) {
        if (gender.equals(""))
            iSettingPres.updateInfo((String) SaveUtils.get(SettingActivity.this, SpValue.TOKEN, "")
                    , iconurl, name, "", "男".equals(gender) ? "1" : "2", "", "");
    }

}
