package com.ylean.cf_hospitalapp.home.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.view.IRoomView;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.inquiry.presenter.IRoomPr;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

public class RoomChooseActivity extends BaseActivity implements IRoomView {

    private TitleBackBarView tbv;
    private SimpleDraweeView sdvPic;
    private android.widget.TextView tvHospitalName;
    private android.widget.TextView tvAddress;
    private android.widget.TextView tvTel;
    private android.support.v7.widget.RecyclerView leftRecyclerView;
    private android.support.v7.widget.RecyclerView rightRecyclerView;

    private IRoomPr iRoomPr = new IRoomPr(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_room_choose);

        initView();

        iRoomPr.allDepartment();
    }

    private void initView() {
        this.rightRecyclerView = (RecyclerView) findViewById(R.id.rightRecyclerView);
        this.leftRecyclerView = (RecyclerView) findViewById(R.id.leftRecyclerView);
        this.tvTel = (TextView) findViewById(R.id.tvTel);
        this.tvAddress = (TextView) findViewById(R.id.tvAddress);
        this.tvHospitalName = (TextView) findViewById(R.id.tvHospitalName);
        this.sdvPic = (SimpleDraweeView) findViewById(R.id.sdvPic);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
