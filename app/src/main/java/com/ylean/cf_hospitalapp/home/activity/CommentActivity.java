package com.ylean.cf_hospitalapp.home.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.IPicPresenter;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.DataUploadView;
import com.ylean.cf_hospitalapp.inquiry.adapter.AskPicAdapter;
import com.ylean.cf_hospitalapp.inquiry.bean.DataUploadResultEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.MImageItem;
import com.ylean.cf_hospitalapp.my.presenter.IEvaluatePres;
import com.ylean.cf_hospitalapp.my.view.IEvaluateView;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论页面
 * Created by linaidao on 2019/1/7.
 */

public class CommentActivity extends BaseActivity implements IEvaluateView, DataUploadView, View.OnClickListener {

    private static final int CAMER_PERMISSION_CODE = 0x123;
    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.EditText etContent;
    private android.widget.TextView tvCommit;
    private String id;

    private IEvaluatePres iEvaluatePres = new IEvaluatePres(this);
    private RecyclerView picRecyclerView;
    private ImageView iv_add_pic;
    private boolean with_pic;

    private String ordertype = "";
    private String ordercode = "";
    private String type = "";
    private static final int IMAGE_PICKER = 0x105;

    private IPicPresenter iPicPresenter = new IPicPresenter(this);
    private AskPicAdapter picAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_comment);

        with_pic = getIntent().getBooleanExtra("with_pic", false);

        /**
         * token
         * relateid        问诊订单和挂号订单评论的是 医生，所以传医生id  商品订单和服务订单 relateid 传 商品id
         * ordercode       评论 挂号订单，问诊订单，商品订单，服务订单时需要传订单编号
         * content         评论内容
         * stardepict      商品描述， 治疗效果
         * starservice     服务态度， 物流
         * starperformance 性价比
         * type            评论类型（1-商品订单 2-服务订单 3-医生 4-医院 5-文章 6-视频 7-帖子） 论 挂号订单和问诊订单时，type传 3
         * imgs            评论图片
         * ordertype       订单类型1图文问诊2电话问诊3视频问诊4挂号订单[评论挂号订单和问诊订单是需要传 ordertype]
         */
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        ordertype = getIntent().getStringExtra("ordertype");
        ordercode = getIntent().getStringExtra("ordercode");

        initView();

        checkPermisson();
    }

    private void initView() {
        this.tvCommit = (TextView) findViewById(R.id.tvCommit);
        this.etContent = (EditText) findViewById(R.id.etContent);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        picRecyclerView = findViewById(R.id.picRecyclerView);
        iv_add_pic = findViewById(R.id.iv_add_pic);

        if (with_pic) {
            picRecyclerView.setVisibility(View.VISIBLE);
            iv_add_pic.setVisibility(View.VISIBLE);
        }

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etContent.getText().toString())) {
                    showErr("请输入评论内容");
                    return;
                }
                commit();
            }
        });

        iv_add_pic.setOnClickListener(this);

        picRecycler();
    }

    private void picRecycler() {
        //创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        picRecyclerView.setLayoutManager(manager);
        picRecyclerView.setItemAnimator(new DefaultItemAnimator());

        picAdapter = new AskPicAdapter(this, images);
        picRecyclerView.setAdapter(picAdapter);
    }


    private boolean checkPermisson() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    CAMER_PERMISSION_CODE);

            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMER_PERMISSION_CODE: {//调用系统相机申请拍照权限回调
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                }
                break;

            }

        }

    }

    /**
     * token
     * relateid        问诊订单和挂号订单评论的是 医生，所以传医生id  商品订单和服务订单 relateid 传 商品id
     * ordercode       评论 挂号订单，问诊订单，商品订单，服务订单时需要传订单编号
     * content         评论内容
     * stardepict      商品描述， 治疗效果
     * starservice     服务态度， 物流
     * starperformance 性价比
     * type            评论类型（1-商品订单 2-服务订单 3-医生 4-医院 5-文章 6-视频 7-帖子） 论 挂号订单和问诊订单时，type传 3
     * imgs            评论图片
     * ordertype       订单类型1图文问诊2电话问诊3视频问诊4挂号订单[评论挂号订单和问诊订单是需要传 ordertype]
     */
    //提交评论
    private void commit() {

        String img = "";
        if (images != null && images.size() > 0) {

            for (int i = 0; i < images.size(); i++) {
                img = img + images.get(i).getUrlPath() + ",";
            }
        }
        if (!TextUtils.isEmpty(img))
            img = img.substring(0, img.length() - 1);


        iEvaluatePres.addEvaluate((String) SaveUtils.get(this, SpValue.TOKEN, "")
                , id, ordercode, etContent.getText().toString(), "0", "0", "0"
                , type, img, ordertype);
    }

    @Override
    public void evaluateSuccess() {

        showErr("评论成功");
        finish();

    }

    private List<MImageItem> images = new ArrayList<>();

    @Override
    public void picUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {
        for (int i = 0; i < imageItems.size(); i++) {
            MImageItem mImageItem = new MImageItem("", imageItems.get(i).name, imageItems.get(i).path);
            mImageItem.setUrlPath(dataUploadResultEntry.getData().get(i));
            images.add(mImageItem);
        }

        if (picAdapter != null)
            picAdapter.notifyDataSetChanged();
    }

    @Override
    public void voiceUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_add_pic:
                choosePic();
                break;

        }

    }

    private void choosePic() {
        if (!checkPermisson()) {
            showErr("请允许权限");
            return;
        }

        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, IMAGE_PICKER);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {

            case ImagePicker.RESULT_CODE_ITEMS:

                if (data != null && requestCode == IMAGE_PICKER) {

                    List<ImageItem> imageItems = (List<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);

                    if (imageItems.size() > 3) {
                        showErr("图片不能多于三张");
                        return;
                    }

                    uploadPics((ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS));

                }

                break;

        }

    }


    private List<ImageItem> imageItems;

    //上传图片
    private void uploadPics(final List<ImageItem> imageItems) {

        if (imageItems == null || imageItems.size() < 1)
            return;

        this.imageItems = imageItems;

        List<String> picPathLists = new ArrayList<>();

        for (int i = 0; i < imageItems.size(); i++) {
            picPathLists.add(imageItems.get(i).path);
        }

        //上传图片
        iPicPresenter.uploadPic(picPathLists);

    }


}
