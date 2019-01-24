package com.ylean.cf_hospitalapp.my.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
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

import com.facebook.drawee.view.SimpleDraweeView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.IPicPresenter;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.DataUploadView;
import com.ylean.cf_hospitalapp.inquiry.activity.PicDetailAc;
import com.ylean.cf_hospitalapp.inquiry.adapter.AskPicAdapter;
import com.ylean.cf_hospitalapp.inquiry.bean.DataUploadResultEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.MImageItem;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskDetailEntry;
import com.ylean.cf_hospitalapp.my.adapter.PicViewAdapter;
import com.ylean.cf_hospitalapp.my.bean.EvalDetailEntry;
import com.ylean.cf_hospitalapp.my.presenter.IEvaluatePres;
import com.ylean.cf_hospitalapp.my.view.IEvaluateView;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * 问诊 评价医生页面
 */
public class InquiryEvaulateDoctorActivity extends BaseActivity implements IEvaluateView, View.OnClickListener, DataUploadView {

    private IEvaluatePres iEvaluatePres = new IEvaluatePres(this);

    private PicAskDetailEntry.DataBean inquiryInfo;
    private String hospitalName;
    private String consultaid;
    private int askType;
    private MaterialRatingBar rb1;
    private MaterialRatingBar rb2;
    private MaterialRatingBar rb3;
    private EditText etConent;
    private RecyclerView picRecyclerView;
    private IPicPresenter iPicPresenter = new IPicPresenter(this);
    private static final int IMAGE_PICKER = 0x105;
    private List<MImageItem> images = new ArrayList<>();
    private AskPicAdapter picAdapter;
    private boolean noedit;
    private TextView tvCommit;
    private SimpleDraweeView sdvImg;
    private TextView tvName;
    private TextView tvJob;
    private TextView tvInfo;
    private TextView tvType;
    private TextView tvPirce;
    private TextView tvCompany;
    private ImageView iv_add_pic;

    //评价id
    private String evaluteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_argement);

        //用于获取评价详情的id
        consultaid = getIntent().getStringExtra("consultaid");

        hospitalName = getIntent().getStringExtra("hospitalName");

        //问诊过来的数据
        inquiryInfo = getIntent().getParcelableExtra("inquiryInfo");

//        !!!!!!!!!!!问诊订单和挂号订单评论的是 医生，所以传医生id  商品订单和服务订单 relateid 传 商品id
        if (inquiryInfo != null)
            evaluteId = inquiryInfo.getDoctorid();//评论的id

        //是否 是无法编辑模式，只是查看
        noedit = getIntent().getBooleanExtra("noedit", false);

        //问诊类型
        askType = getIntent().getIntExtra("askType", -1);//1图文问诊，2电话，3视频
        initWidget();
        checkPermisson();

        setInfo();

        //无法编辑模式
        if (noedit)
            noEditable();

        if (noedit)
            //获取评价详情
            commandInfo();

    }

    private void noEditable() {
        tvCommit.setVisibility(View.GONE);
        rb1.setIsIndicator(true);
        rb2.setIsIndicator(true);
        rb3.setIsIndicator(true);
        iv_add_pic.setVisibility(View.GONE);

        //设置不可编辑
        etConent.setFocusable(false);
        etConent.setFocusableInTouchMode(false);
    }

    private void commandInfo() {

        RetrofitHttpUtil.getInstance()
                .commandInfo(new BaseNoTObserver<EvalDetailEntry>() {
                    @Override
                    public void onHandleSuccess(EvalDetailEntry evalDetailEntry) {

                        if (evalDetailEntry == null || evalDetailEntry.getData() == null)
                            return;

                        setLoadInfo(evalDetailEntry.getData());
                    }

                    @Override
                    public void onHandleError(String message) {
                        showErr(message);
                    }

                }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""), consultaid);

    }

    private void setLoadInfo(EvalDetailEntry.DataBean data) {

        tvName.setText(data.getName());
        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + data.getImgurl()));
        tvJob.setText(data.getDepartname() + "   " + data.getDtitlename());
        tvInfo.setText(data.getAdeptdesc());
        tvPirce.setText("¥" + data.getPrice());
        tvCompany.setText(data.getHospital());//医院名称
        etConent.setText(data.getContent());

        switch (data.getOrdertype()) {

            case "1"://图文问诊
                tvType.setText("图文问诊");
                break;
            case "2"://电话问诊
                tvType.setText("电话问诊");
                break;
            case "3"://视频问诊
                tvType.setText("视频问诊");
                break;
        }

        rb1.setProgress(data.getStarservice());//态度
        rb2.setProgress(data.getStardepict());//治疗效果
        rb3.setProgress(data.getStarperformance());//性价比

        if (data.getImgs() != null && data.getImgs().size() > 0) {

            List<String> imgs = data.getImgs();
            PicViewAdapter picViewAdapter = new PicViewAdapter(this, imgs);
            picRecyclerView.setAdapter(picViewAdapter);

            picRecyclerView.addOnItemTouchListener(new OnItemClickListener(picRecyclerView) {
                @Override
                public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                    Intent m = new Intent(InquiryEvaulateDoctorActivity.this, PicDetailAc.class);
                    m.putExtra("picUrl", ApiService.WEB_ROOT + imgs.get(position));
                    startActivity(m);
                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });

        }

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
                    0x12);

            return false;
        }
        return true;
    }

    private void initWidget() {
        TitleBackBarView tbv = findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        picRecyclerView = findViewById(R.id.picRecyclerView);
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        picRecyclerView.setLayoutManager(manager);
        picRecyclerView.setItemAnimator(new DefaultItemAnimator());
        picAdapter = new AskPicAdapter(this, images);
        picRecyclerView.setAdapter(picAdapter);

        sdvImg = findViewById(R.id.sdvImg);
        tvName = findViewById(R.id.tvName);
        tvJob = findViewById(R.id.tvJob);
        tvCompany = findViewById(R.id.tvCompany);
        tvInfo = findViewById(R.id.tvInfo);
        tvType = findViewById(R.id.tvType);
        tvPirce = findViewById(R.id.tvPirce);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        etConent = findViewById(R.id.etConent);
        tvCommit = findViewById(R.id.tvCommit);
        iv_add_pic = findViewById(R.id.iv_add_pic);

        tvCommit.setOnClickListener(this);
        iv_add_pic.setOnClickListener(this);

    }

    private void setInfo() {

        if (inquiryInfo == null)
            return;

        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + inquiryInfo.getDocimg()));
        tvName.setText(inquiryInfo.getDoctorname());
        tvJob.setText(inquiryInfo.getDepartname() + "   " + inquiryInfo.getDoctitlename());
        tvInfo.setText(inquiryInfo.getAdeptdesc());
        tvPirce.setText("¥" + inquiryInfo.getPrice());
        tvCompany.setText(hospitalName);//医院名称

        switch (askType) {

            case 1://图文问诊
                tvType.setText("图文问诊");
                break;
            case 2://电话问诊
                tvType.setText("电话问诊");
                break;
            case 3://视频问诊
                tvType.setText("视频问诊");

            case 4:
                tvType.setText("免费义诊");

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {

            case ImagePicker.RESULT_CODE_ITEMS:

                if (data != null && requestCode == IMAGE_PICKER) {

                    List<ImageItem> imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    if (imageItems.size() > 3) {
                        showErr("图片最多三张");
                        return;
                    }

                    uploadPics((ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS));
                }

                break;

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tvCommit:

                if (inquiryInfo == null) {
                    showErr("数据错误");
                    return;
                }

                //提交评价
                if (TextUtils.isEmpty(etConent.getText().toString())) {
                    showErr("请输入评价内容");
                    return;
                }

                int rating1 = (int) rb1.getRating();
                int rating2 = (int) rb2.getRating();
                int rating3 = (int) rb3.getRating();

                if (rating1 == 0 || rating2 == 0 || rating3 == 0) {
                    showErr("请评价订单信息");
                    return;
                }

                String img = "";
                if (images != null && images.size() > 0) {

                    for (int i = 0; i < images.size(); i++) {
                        img = img + images.get(i).getUrlPath() + ",";
                    }
                }
                if (!TextUtils.isEmpty(img))
                    img = img.substring(0, img.length() - 1);

                iEvaluatePres.addEvaluate(
                        (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , evaluteId, inquiryInfo.getCode(), etConent.getText().toString()
                        , rating2 + "", rating1 + "", rating3 + ""
                        , "3", img, askType + "");

                break;

            case R.id.iv_add_pic://上传图片

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

    //删除图片
    public void removePic(int position) {

        if (images != null) {
            images.remove(position);
        }
        if (picAdapter != null)
            picAdapter.notifyDataSetChanged();

    }

    @Override
    public void evaluateSuccess() {

        //评价成功
        setResult(0x003);
        finish();

    }
}
