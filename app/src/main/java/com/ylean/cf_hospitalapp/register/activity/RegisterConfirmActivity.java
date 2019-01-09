package com.ylean.cf_hospitalapp.register.activity;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.IPicPresenter;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.DataUploadView;
import com.ylean.cf_hospitalapp.inquiry.activity.AskPeopleActivity;
import com.ylean.cf_hospitalapp.inquiry.adapter.AskPicAdapter;
import com.ylean.cf_hospitalapp.inquiry.bean.DataUploadResultEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.MImageItem;
import com.ylean.cf_hospitalapp.inquiry.bean.PeopleEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskResutEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.register.bean.HospitalListEntry;
import com.ylean.cf_hospitalapp.register.bean.NumListEntry;
import com.ylean.cf_hospitalapp.register.bean.TimeEntry;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 确认挂号信息
 * Created by linaidao on 2019/1/5.
 */

public class RegisterConfirmActivity extends BaseActivity implements View.OnClickListener, DataUploadView {

    private android.widget.ImageView ivAddPeo;
    private android.widget.TextView tvPerson;
    private static final int CAMER_PERMISSION_CODE = 0x304;
    private static final int IMAGE_PICKER = 0x305;
    private android.widget.TextView tvHospitalName;
    private android.widget.TextView tvDepartmentName;
    private android.widget.TextView tvDoctorName;
    private android.widget.TextView tvMoney;
    private android.widget.TextView tvDate;
    private android.widget.EditText etContent;
    private android.support.v7.widget.RecyclerView picRecyclerView;
    private android.widget.ImageView tvUploadPic;
    //    private LinearLayout people;
    private RelativeLayout rlPatient;
    private TextView tvName;
    private TextView tvIdCard;
    private TextView tvAge;
    private NumListEntry.DataBean doctorRegisterInfo;
    private TimeEntry timeEntry;
    private String time;
    private static final int ASK_PEOPLE_CODE = 0x101;
    private static final int CHOOSE_ASK_PEOPEL_RESULE_CODE = 0X211;
    private PeopleEntry.DataBean peopleBean;
    private List<ImageItem> imageItems;//上传的图片集合
    private IPicPresenter iPicPresenter = new IPicPresenter(this);
    private List<MImageItem> images = new ArrayList<>();
    private AskPicAdapter askPicAdapter;
    private HospitalListEntry.DataBean hospitalInfo;
    private String departname;
    private String registerId;
    private String departid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_register_confirm);

        doctorRegisterInfo = getIntent().getParcelableExtra("doctorRegisterInfo");
        timeEntry = getIntent().getParcelableExtra("date");
        time = getIntent().getStringExtra("time");

        departname = getIntent().getStringExtra("departname");
        hospitalInfo = getIntent().getParcelableExtra("hospitalInfo");
        registerId = getIntent().getStringExtra("registerId");
        departid = getIntent().getStringExtra("departid");
        initView();

    }

    private void initView() {

        rlPatient = findViewById(R.id.rlPatient);

        tvName = findViewById(R.id.tvName);
        tvIdCard = findViewById(R.id.tvIdCard);
        tvAge = findViewById(R.id.tvAge);

        TextView tvNext = (TextView) findViewById(R.id.tvNext);
        this.tvUploadPic = (ImageView) findViewById(R.id.tvUploadPic);
        this.picRecyclerView = (RecyclerView) findViewById(R.id.picRecyclerView);
        this.etContent = (EditText) findViewById(R.id.etContent);
        this.tvDate = (TextView) findViewById(R.id.tvDate);
        this.tvMoney = (TextView) findViewById(R.id.tvMoney);
        this.tvDoctorName = (TextView) findViewById(R.id.tvDoctorName);
        this.tvDepartmentName = (TextView) findViewById(R.id.tvDepartmentName);
        this.tvHospitalName = (TextView) findViewById(R.id.tvHospitalName);
//        people = findViewById(R.id.people);
        this.tvPerson = (TextView) findViewById(R.id.tvPerson);
        this.ivAddPeo = (ImageView) findViewById(R.id.ivAddPeo);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        picRecycler();

        ivAddPeo.setOnClickListener(this);
        rlPatient.setOnClickListener(this);
        tvUploadPic.setOnClickListener(this);
        tvNext.setOnClickListener(this);

        if (hospitalInfo != null)
            tvHospitalName.setText(hospitalInfo.getHospitalname());

        if (doctorRegisterInfo != null) {
//            Logger.d("医院名称" + doctorRegisterInfo.getHospitalname() + "部门名称:" + doctorRegisterInfo.getDepartname());
            tvDepartmentName.setText(departname);
            tvDoctorName.setText(doctorRegisterInfo.getDocname() + "  " + doctorRegisterInfo.getDoctitle() + "    " + doctorRegisterInfo.getTeachname());
            tvMoney.setText(doctorRegisterInfo.getPrice() + "元");
        }

        if (timeEntry != null) {
            tvDate.setText(timeEntry.getDateDes() + "   " + timeEntry.getWeekDesc() + " " + time);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.rlPatient:
            case R.id.ivAddPeo://添加问诊人

                Intent m = new Intent(this, AskPeopleActivity.class);
                m.putExtra("title", "问诊人选择");
                startActivityForResult(m, ASK_PEOPLE_CODE);

                break;
            case R.id.tvUploadPic://上传照片
                choosePic();
                break;

            case R.id.tvNext://挂号

                register();

                break;
        }


    }

    private void register() {
        if (peopleBean == null) {
            showErr("请选择挂号人");
            return;
        }

        if (TextUtils.isEmpty(etContent.getText().toString())) {
            showErr("请输入帮助内容");
            return;
        }

        if (etContent.getText().toString().length() < 10) {
            showErr("帮助内容少于10个字");
            return;
        }

        if (doctorRegisterInfo == null)
            return;

        if (peopleBean == null)
            return;

        if (timeEntry == null)
            return;

        startRegister();
    }

    //开始挂号
    private void startRegister() {

        String img = "";
        if (images != null && images.size() > 0) {

            for (int i = 0; i < images.size(); i++) {
                img = img + images.get(i).getUrlPath() + ",";
            }
        }
        if (!TextUtils.isEmpty(img))
            img = img.substring(0, img.length() - 1);

//        String appointid = timeEntry.getDateDes();
        RetrofitHttpUtil
                .getInstance()
                .registerOrder(
                        new BaseNoTObserver<PicAskResutEntry>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                super.onSubscribe(d);
                                showLoading("提交中...");
                            }

                            @Override
                            public void onHandleSuccess(PicAskResutEntry resutEntry) {
                                hideLoading();

                                if (resutEntry == null || doctorRegisterInfo == null) {
                                    showErr("数据错误");
                                    return;
                                }

                                Intent m = new Intent(RegisterConfirmActivity.this, PayRegiserActivity.class);
                                m.putExtra("orderNum", resutEntry.getData());
                                Logger.d("医生id::" + doctorRegisterInfo.getDoctorid()
                                        + "\r\n名称:" + doctorRegisterInfo.getDocname()
                                        + "\r\n订单号:" + resutEntry.getData());
                                m.putExtra("doctorId", doctorRegisterInfo.getDoctorid());
                                m.putExtra("doctorName", doctorRegisterInfo.getDocname());
                                m.putExtra("price", doctorRegisterInfo.getPrice());
                                m.putExtra("type", "挂号订单");

                                startActivity(m);

//                                //TODO
                            }

                            @Override
                            public void onHandleError(String message) {
                                hideLoading();
                                showErr(message);
                            }

                        }, SpValue.CH
                        , (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , doctorRegisterInfo.getId()//时间id
                        , departid//科室id
                        , registerId//门诊id
                        , doctorRegisterInfo.getDoctorid()//医生id
                        , ""// 1-普通号  2-专家号
                        , peopleBean.getId()//就诊人id
                        , etContent.getText().toString()//就诊内容
                        , img);

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

            case CHOOSE_ASK_PEOPEL_RESULE_CODE://选中问诊人
                if (data != null) {
                    peopleBean = data.getParcelableExtra("selectPeople");
                    if (peopleBean != null)
                        setAskPeopleInfo();
                }
                break;

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

    private void setAskPeopleInfo() {
        ivAddPeo.setVisibility(View.GONE);
        tvPerson.setVisibility(View.GONE);

        rlPatient.setVisibility(View.VISIBLE);

        tvName.setText(peopleBean.getName());
        tvIdCard.setText("身份证   " + peopleBean.getIDcard());
        tvAge.setText(peopleBean.getAge() + "岁" +
                (SpValue.SEX_FEMALE.equals(peopleBean.getSex()) ? "    女" : "  男")
                + " 医保 " + peopleBean.getMedicalcard());

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
