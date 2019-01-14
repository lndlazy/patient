package com.ylean.cf_hospitalapp.hospital.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.widget.ActionSheetDialog;
import com.ylean.cf_hospitalapp.widget.EnterItemView;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 免费接送
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_transfer);

        init();

    }

    private void init() {
        this.tv_submit = (TextView) findViewById(R.id.tv_submit);
        this.tvtime = (EnterItemView) findViewById(R.id.tvtime);
        this.tvaddress = (EnterItemView) findViewById(R.id.tvaddress);
        this.tvtel = (EnterItemView) findViewById(R.id.tvtel);
        this.tvcount = (EnterItemView) findViewById(R.id.tvcount);
        this.tvnickname = (EnterItemView) findViewById(R.id.tvnickname);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

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

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvnickname://修改姓名

                break;

            case R.id.tvcount://陪同人数

                showPeopleNum();
                break;

            case R.id.tvtel://手机号

                editTel();

                break;

            case R.id.tvaddress://详细地址

                break;

            case R.id.tvtime://时间

                break;




        }

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
                            }

                        })
                .addSheetItem("1", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                peoplenum = "1";

                            }
                        })
                .addSheetItem("2", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                peoplenum = "2";

                            }
                        })
                .addSheetItem("3", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                peoplenum = "3";
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
        etdialogcontent = editDialog.findViewById(R.id.etdialogcontent);
        etdialogcontent.setInputType(InputType.TYPE_CLASS_PHONE);
        TextView tvdialogcancle =   editDialog.findViewById(R.id.tvdialogcancle);
        TextView tvdialogcreate = (TextView) editDialog.findViewById(R.id.tvdialogcreate);
        tvdialogcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editDialog!=null && editDialog.isShowing())
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

                if (editDialog!=null && editDialog.isShowing())
                    editDialog.dismiss();
            }
        });
    }
}
