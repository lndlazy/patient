package com.ylean.cf_hospitalapp.mall.acitity;

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
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.DataUploadView;
import com.ylean.cf_hospitalapp.inquiry.activity.PicDetailAc;
import com.ylean.cf_hospitalapp.inquiry.adapter.AskPicAdapter;
import com.ylean.cf_hospitalapp.inquiry.bean.DataUploadResultEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.MImageItem;
import com.ylean.cf_hospitalapp.mall.bean.GoodsOrderInfoEntry;
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
 * 商品评价页面
 * Created by linaidao on 2019/1/23.
 */

public class GoodsCommandActivity extends BaseActivity implements View.OnClickListener, DataUploadView, IEvaluateView {

    private MaterialRatingBar rb1;
    private MaterialRatingBar rb2;
    private MaterialRatingBar rb3;
    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private com.facebook.drawee.view.SimpleDraweeView sdvImg;
    private android.widget.TextView tvTitle;
    private android.widget.TextView tvPP;
    private android.widget.EditText etConent;
    private android.support.v7.widget.RecyclerView picRecyclerView;
    private android.widget.ImageView ivaddpic;
    private android.widget.TextView tvCommit;
    private GoodsOrderInfoEntry.DataBean goodsInfo;

    private IEvaluatePres iEvaluatePres = new IEvaluatePres(this);

    private IPicPresenter iPicPresenter = new IPicPresenter(this);
    private static final int IMAGE_PICKER = 0x105;
    private List<MImageItem> images = new ArrayList<>();
    private AskPicAdapter picAdapter;
    private List<ImageItem> imageItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_goods_recommand);

        goodsInfo = getIntent().getParcelableExtra("goodsInfo");
        init();

        if (goodsInfo == null) {
            showErr("数据错误");
            finish();
        }

        if ("1".equals(goodsInfo.getIscomment())) {
            //获取评价详情
            commandInfo();
            //已经评价过，查看评价详情
            noEditable();
        }
    }

    //评价详情
    private void commandInfo() {

        RetrofitHttpUtil.getInstance()
                .goodsCommandInfo(new BaseNoTObserver<EvalDetailEntry>() {
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

                }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""), goodsInfo.getCode());

    }

    private void setLoadInfo(EvalDetailEntry.DataBean data) {

//        tvTitle.setText(data.getName());
//        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + data.getImgurl()));

        etConent.setText(data.getContent());

        rb1.setProgress(data.getStardepict());//治疗效果
        rb2.setProgress(data.getStarservice());//态度
        rb3.setProgress(data.getStarperformance());//性价比

        if (data.getImgs() != null && data.getImgs().size() > 0) {

            List<String> imgs = data.getImgs();
            PicViewAdapter picViewAdapter = new PicViewAdapter(this, imgs);
            picRecyclerView.setAdapter(picViewAdapter);

            picRecyclerView.addOnItemTouchListener(new OnItemClickListener(picRecyclerView) {
                @Override
                public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                    Intent m = new Intent(GoodsCommandActivity.this, PicDetailAc.class);
                    m.putExtra("picUrl", ApiService.WEB_ROOT + imgs.get(position));
                    startActivity(m);
                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });

        }

    }

    private void noEditable() {
        tvCommit.setVisibility(View.INVISIBLE);
        rb1.setIsIndicator(true);
        rb2.setIsIndicator(true);
        rb3.setIsIndicator(true);
        ivaddpic.setVisibility(View.INVISIBLE);

        //设置不可编辑
        etConent.setFocusable(false);
        etConent.setFocusableInTouchMode(false);
    }


    private void init() {

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        tv1.setText("商品描述");

        if (goodsInfo != null)
            tv2.setText("1".equals(goodsInfo.getOrdertype()) ? "物流" : "态度");

        this.tvCommit = (TextView) findViewById(R.id.tvCommit);
        this.ivaddpic = (ImageView) findViewById(R.id.iv_add_pic);
        this.picRecyclerView = (RecyclerView) findViewById(R.id.picRecyclerView);
        this.etConent = (EditText) findViewById(R.id.etConent);
        this.tvPP = (TextView) findViewById(R.id.tvPP);
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.sdvImg = (SimpleDraweeView) findViewById(R.id.sdvImg);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        picRecyclerView.setLayoutManager(manager);
        picRecyclerView.setItemAnimator(new DefaultItemAnimator());
        picAdapter = new AskPicAdapter(this, images);
        picRecyclerView.setAdapter(picAdapter);

        ivaddpic.setOnClickListener(this);
        tvCommit.setOnClickListener(this);

        if (goodsInfo != null) {
            sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + goodsInfo.getSkuimg()));
            tvTitle.setText(goodsInfo.getSkuname());
            tvPP.setText(goodsInfo.getPoints() + "积分+" + goodsInfo.getSkuprice() + "元");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_add_pic:
                choosePic();
                break;

            case R.id.tvCommit:

                commmit();

                break;
        }
    }

    private void commmit() {

        if (goodsInfo == null) {
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
                , goodsInfo.getSkuid(), goodsInfo.getCode(), etConent.getText().toString()
                , rating1 + "", rating2 + "", rating3 + ""
                , goodsInfo.getOrdertype(), img, "");
    }


    private void choosePic() {
        if (!checkPermisson()) {
            showErr("请允许权限");
            return;
        }

        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, IMAGE_PICKER);
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

    //删除图片
    public void removePic(int position) {

        if (images != null) {
            images.remove(position);
        }
        if (picAdapter != null)
            picAdapter.notifyDataSetChanged();

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

    @Override
    public void evaluateSuccess() {
        //评价成功
        setResult(0x003);
        finish();

    }
}
