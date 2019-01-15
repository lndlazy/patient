package com.ylean.cf_hospitalapp.hospital.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.IDateUtils;
import com.ylean.cf_hospitalapp.utils.NumFormatUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.ActionSheetDialog;

import java.util.Calendar;
import java.util.Date;

/**
 * 申请免费接送
 * Created by linaidao on 2019/1/14.
 */

public class TransferActivity extends BaseActivity implements View.OnClickListener {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private com.ylean.cf_hospitalapp.widget.EnterItemView tvnickname;
    private com.ylean.cf_hospitalapp.widget.EnterItemView tvcount;
    private com.ylean.cf_hospitalapp.widget.EnterItemView tvtel;
    private com.ylean.cf_hospitalapp.widget.EnterItemView tvaddress;
    private com.ylean.cf_hospitalapp.widget.EnterItemView tvtime;
    private android.widget.TextView tv_submit;

    private String peoplenum = "0";
    private Dialog editDialog;
    private EditText etdialogcontent;
    private String hospitalId;
    private String name;
    private String address;

    private String transferTime;
    private String phone;
    private Dialog nameDialog;
    private EditText etnamecontent;
    private Dialog addressDialog;
    private EditText etaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_transfer);

        hospitalId = getIntent().getStringExtra("hospitalId");
        init();
        tvcount.setRightTxt("0");
    }

    private void init() {
        this.tv_submit = findViewById(R.id.tv_submit);
        this.tvtime = findViewById(R.id.tvtime);
        this.tvaddress = findViewById(R.id.tvaddress);
        this.tvtel = findViewById(R.id.tvtel);
        this.tvcount = findViewById(R.id.tvcount);
        this.tvnickname = findViewById(R.id.tvnickname);
        this.tbv = findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvnickname.setOnClickListener(this);
        tvcount.setOnClickListener(this);
        tvtel.setOnClickListener(this);
        tvaddress.setOnClickListener(this);
        tvtime.setOnClickListener(this);
        tv_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvnickname://患者姓名

                editName();

                break;

            case R.id.tvcount://陪同人数

                showPeopleNum();

                break;

            case R.id.tvtel://手机号

                editTel();

                break;

            case R.id.tvaddress://详细地址

                editAddress();

                break;

            case R.id.tvtime://时间

                chooseTime();

                break;

            case R.id.tv_submit://提交申请

                freeTransfer();

                break;

        }

    }

    private void editAddress() {

        addressDialog = new Dialog(this, R.style.no_title);
        addressDialog.setCancelable(true);
        addressDialog.setCanceledOnTouchOutside(true);
        addressDialog.show();
        addressDialog.setContentView(R.layout.dialog_input);

        TextView tvdiaologaddress = addressDialog.findViewById(R.id.tvdiaologtitle);
        tvdiaologaddress.setText("请输入详细地址");
        etaddress = addressDialog.findViewById(R.id.etdialogcontent);

        TextView tvdialogcancle = addressDialog.findViewById(R.id.tvdialogcancle);
        TextView tvdialogcreate = addressDialog.findViewById(R.id.tvdialogcreate);
        tvdialogcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (addressDialog != null && addressDialog.isShowing())
                    addressDialog.dismiss();
            }
        });

        tvdialogcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etaddress.getText().toString())) {
                    showErr("请输入详细地址");
                    return;
                }

                if (addressDialog != null && addressDialog.isShowing())
                    addressDialog.dismiss();

                tvaddress.setRightTxt(etaddress.getText().toString());
                address = etaddress.getText().toString();
            }
        });

    }

    private void editName() {

        nameDialog = new Dialog(this, R.style.no_title);
        nameDialog.setCancelable(true);
        nameDialog.setCanceledOnTouchOutside(true);
        nameDialog.show();
        nameDialog.setContentView(R.layout.dialog_input);

        TextView tvdiaologtitle = nameDialog.findViewById(R.id.tvdiaologtitle);
        tvdiaologtitle.setText("请输入患者姓名");
        etnamecontent = nameDialog.findViewById(R.id.etdialogcontent);

        TextView tvdialogcancle = nameDialog.findViewById(R.id.tvdialogcancle);
        TextView tvdialogcreate = nameDialog.findViewById(R.id.tvdialogcreate);
        tvdialogcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nameDialog != null && nameDialog.isShowing())
                    nameDialog.dismiss();
            }
        });

        tvdialogcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etnamecontent.getText().toString())) {
                    showErr("请输入患者姓名");
                    return;
                }

                if (nameDialog != null && nameDialog.isShowing())
                    nameDialog.dismiss();

                tvnickname.setRightTxt(etnamecontent.getText().toString());
                name = etnamecontent.getText().toString();
            }
        });

    }


    //陪同人数
    private void showPeopleNum() {

        new ActionSheetDialog(this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("0", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                peoplenum = "0";
                                tvcount.setRightTxt("0");
                            }

                        })
                .addSheetItem("1", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                peoplenum = "1";
                                tvcount.setRightTxt("1");
                            }
                        })
                .addSheetItem("2", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                peoplenum = "2";
                                tvcount.setRightTxt("2");
                            }
                        })
                .addSheetItem("3", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                peoplenum = "3";
                                tvcount.setRightTxt("3");
                            }
                        }).show();

    }


    private void editTel() {

        editDialog = new Dialog(this, R.style.no_title);
        editDialog.setCancelable(true);
        editDialog.setCanceledOnTouchOutside(true);
        editDialog.show();
        editDialog.setContentView(R.layout.dialog_input);

        TextView tvdiaologtitle = editDialog.findViewById(R.id.tvdiaologtitle);
        tvdiaologtitle.setText("请输入手机号码");
        etdialogcontent = editDialog.findViewById(R.id.etdialogcontent);
        etdialogcontent.setInputType(InputType.TYPE_CLASS_PHONE);
        TextView tvdialogcancle = editDialog.findViewById(R.id.tvdialogcancle);
        TextView tvdialogcreate = editDialog.findViewById(R.id.tvdialogcreate);
        tvdialogcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editDialog != null && editDialog.isShowing())
                    editDialog.dismiss();
            }
        });

        tvdialogcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etdialogcontent.getText().toString())) {
                    showErr("请输入手机号");
                    return;
                }

                if (!NumFormatUtils.isMobileNum(etdialogcontent.getText().toString())) {
                    showErr("请输入正确的手机号");
                    return;
                }

                if (editDialog != null && editDialog.isShowing())
                    editDialog.dismiss();

                tvtel.setRightTxt(etdialogcontent.getText().toString());
                phone = etdialogcontent.getText().toString();
            }
        });
    }


    private void chooseTime() {

        Date date = new Date();
        final Calendar c = Calendar.getInstance();
        c.setTime(date);

        TimePickerView timePickerView = new TimePickerView(this, TimePickerView.Type.ALL);

        timePickerView.setTime(new Date());
        timePickerView.setCyclic(false);

        timePickerView.setTitle("选择预约时间");

        timePickerView.setCancelable(true);
        //时间选择后回调
        timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                transferTime = IDateUtils.formatDateTime(date, IDateUtils.DF_YYYY_MM_DD_HH_MM_SS);
                tvtime.setRightTxt(IDateUtils.formatDateTime(date, IDateUtils.DF_YYYY_MM_DD_HH_MM_SS));
            }
        });
        //弹出时间选择器
        timePickerView.show();

    }

    private void freeTransfer() {

        if (TextUtils.isEmpty(name)) {
            showErr("请输入患者姓名");
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            showErr("请输入手机号");
            return;
        }

        if (TextUtils.isEmpty(address)) {
            showErr("请输入详细地址");
            return;
        }

        if (TextUtils.isEmpty(transferTime)) {
            showErr("请选择接送时间");
            return;
        }

        RetrofitHttpUtil.getInstance()
                .freeTransfer(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                showErr("申请成功");
                                finish();
                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                            }

                        }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , name, peoplenum, phone, address, transferTime, hospitalId);

    }

}
