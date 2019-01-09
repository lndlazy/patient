package com.ylean.cf_hospitalapp.register.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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

import com.facebook.drawee.view.SimpleDraweeView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.IPicPresenter;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.DataUploadView;
import com.ylean.cf_hospitalapp.inquiry.adapter.AskPicAdapter;
import com.ylean.cf_hospitalapp.inquiry.bean.DataUploadResultEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.MImageItem;
import com.ylean.cf_hospitalapp.my.FragmentFour;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.register.PayStatus;
import com.ylean.cf_hospitalapp.register.bean.OrderInfoEntry;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 售后申请 申请退款
 * Created by linaidao on 2019/1/7.
 */

public class PayBackActivity extends BaseActivity implements View.OnClickListener, DataUploadView {

    private android.widget.TextView tvLeft;
    private com.facebook.drawee.view.SimpleDraweeView sdvImg;
    private android.widget.TextView tvName;
    private android.widget.TextView tvDepartment;
    private android.widget.TextView tvHospitalName;
    private android.widget.TextView tvIntroduce;
    private android.widget.TextView tvType;
    private android.widget.TextView tvPrice;
    private android.widget.EditText etDesc;
    private android.support.v7.widget.RecyclerView picRecyclerView;
    private android.widget.ImageView tvUploadPic;
    private android.widget.TextView tvNext;
    private OrderInfoEntry.DataBean mOrderInfo;
    private static final int CAMER_PERMISSION_CODE = 0x304;
    private static final int IMAGE_PICKER = 0x305;
    private List<ImageItem> imageItems;//上传的图片集合
    private IPicPresenter iPicPresenter = new IPicPresenter(this);
    private List<MImageItem> images = new ArrayList<>();

    private String type;
    private AskPicAdapter askPicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_pay_back);

//        n.putExtra("id", mOrderInfo.getId());
//        n.putExtra("type", "挂号订单");
//        n.putExtra("mOrderInfo", mOrderInfo);
        mOrderInfo = getIntent().getParcelableExtra("mOrderInfo");

        type = getIntent().getStringExtra("type");
        initView();

    }

    private void initView() {

        this.tvNext = (TextView) findViewById(R.id.tvNext);
        this.tvUploadPic = (ImageView) findViewById(R.id.tvUploadPic);
        this.picRecyclerView = (RecyclerView) findViewById(R.id.picRecyclerView);
        this.etDesc = (EditText) findViewById(R.id.etDesc);
        this.tvPrice = (TextView) findViewById(R.id.tvPrice);
        this.tvType = (TextView) findViewById(R.id.tvType);
        this.tvIntroduce = (TextView) findViewById(R.id.tvIntroduce);
        this.tvHospitalName = (TextView) findViewById(R.id.tvHospitalName);
        this.tvDepartment = (TextView) findViewById(R.id.tvDepartment);
        this.tvName = (TextView) findViewById(R.id.tvName);
        this.sdvImg = (SimpleDraweeView) findViewById(R.id.sdvImg);
        this.tvLeft = (TextView) findViewById(R.id.tvLeft);

        picRecycler();

        tvLeft.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        tvUploadPic.setOnClickListener(this);

        if (mOrderInfo == null)
            return;

        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + mOrderInfo.getDoctorimg()));
        tvName.setText(mOrderInfo.getDoctorname());
        tvDepartment.setText(mOrderInfo.getDepartname() + " " + mOrderInfo.getDocdocteachname());
        tvHospitalName.setText(mOrderInfo.getHospitalname());
//        tvIntroduce.setText(mOrderInfo.get);

        tvType.setText(type);
        tvPrice.setText("¥" + mOrderInfo.getPrice());
    }

    private void picRecycler() {
        //创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        picRecyclerView.setHasFixedSize(true);
        picRecyclerView.setNestedScrollingEnabled(false);
        picRecyclerView.setLayoutManager(manager);
        picRecyclerView.setItemAnimator(new DefaultItemAnimator());

//        //添加自定义分割线
//        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_home_divider));
//        recyclerView.addItemDecoration(divider);
        askPicAdapter = new AskPicAdapter(this, images);
        picRecyclerView.setAdapter(askPicAdapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvLeft:
                finish();
                break;

            case R.id.tvUploadPic://上传图片
                choosePic();
                break;

            case R.id.tvNext://提交退款申请

                if (TextUtils.isEmpty(etDesc.getText().toString())) {
                    showErr("请输入退款原因");
                    return;
                }

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

                builder.setTitle("提示").setMessage("您确定要取消挂号吗").setPositiveButton("取消挂号", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        payBack();
                    }
                }).show();

                break;
        }

    }

    //退款申请
    private void payBack() {

        String img = "";
        if (images != null && images.size() > 0) {

            for (int i = 0; i < images.size(); i++) {
                img = img + images.get(i).getUrlPath() + ",";
            }
        }
        if (!TextUtils.isEmpty(img))
            img = img.substring(0, img.length() - 1);

        RetrofitHttpUtil
                .getInstance()
                .payBack(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                showErr("申请成功");
                                nextActivity(MyRegisterOrderListActivity.class);
                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                            }

                        }
                        , SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , mOrderInfo.getId(), PayStatus.STATUS_WAIT_USE, etDesc.getText().toString(), img);

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
                    CAMER_PERMISSION_CODE);

            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case CAMER_PERMISSION_CODE://定位权限

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(this, ImageGridActivity.class);
                    startActivityForResult(intent, IMAGE_PICKER);
                } else {
                    showErr("没有权限");
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {

            case ImagePicker.RESULT_CODE_ITEMS:
                if (data != null && requestCode == IMAGE_PICKER)
                    uploadPics((ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS));
                break;
        }

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

    //删除图片
    public void removePic(int position) {

        if (images != null) {
            images.remove(position);
        }
        if (askPicAdapter != null)
            askPicAdapter.notifyDataSetChanged();

    }


    @Override
    public void picUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {
        for (int i = 0; i < imageItems.size(); i++) {
            MImageItem mImageItem = new MImageItem("", imageItems.get(i).name, imageItems.get(i).path);
            mImageItem.setUrlPath(dataUploadResultEntry.getData().get(i));
            images.add(mImageItem);
        }

        if (askPicAdapter != null)
            askPicAdapter.notifyDataSetChanged();

    }

    @Override
    public void voiceUploadSuccess(DataUploadResultEntry dataUploadResultEntry) {

    }
}
