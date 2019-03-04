package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.j256.ormlite.stmt.query.In;
import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.doctor.activity.DoctorDetailActivity;
import com.ylean.cf_hospitalapp.inquiry.activity.FreeAskDoctorListActivity;
import com.ylean.cf_hospitalapp.inquiry.bean.DoctorListEntry;
import com.ylean.cf_hospitalapp.net.ApiService;

import java.util.List;

/**
 * Created by linaidao on 2018/12/20.
 */

public class FreeAskDoctorAdapter extends RecyclerView.Adapter<FreeAskDoctorAdapter.MyViewHolder> {

    private Context context;
    private List<DoctorListEntry.DataBean> doctorInfoList;

    public FreeAskDoctorAdapter(Context context, List<DoctorListEntry.DataBean> doctorInfoList) {
        this.context = context;
        this.doctorInfoList = doctorInfoList;
    }

    @Override
    public FreeAskDoctorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new FreeAskDoctorAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_free_ask_doctor, parent, false));
    }

    @Override
    public void onBindViewHolder(FreeAskDoctorAdapter.MyViewHolder holder, final int position) {

        holder.sdvImg.setImageURI(ApiService.WEB_ROOT + Uri.parse(doctorInfoList.get(position).getImgurl()));
        holder.tvName.setText(doctorInfoList.get(position).getDoctorname());
        holder.tvJob.setText(doctorInfoList.get(position).getDoctortitle());
        holder.tvCompany.setText(doctorInfoList.get(position).getHospitalname());
        holder.tvInfo.setText(TextUtils.isEmpty(doctorInfoList.get(position).getAdeptdesc()) ? "暂无简介" :
                doctorInfoList.get(position).getAdeptdesc());
        holder.ivSelect.setSelected(doctorInfoList.get(position).isSelect());


        //选中问诊
        holder.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof FreeAskDoctorListActivity) {
                    FreeAskDoctorListActivity a = (FreeAskDoctorListActivity) context;
                    a.onItemClick(position);
                }

            }
        });

        //点击进入医生详情页面
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(context, DoctorDetailActivity.class);
                m.putExtra("doctorId", doctorInfoList.get(position).getDoctorid());
                context.startActivity(m);
            }
        });


    }

    @Override
    public int getItemCount() {
        return doctorInfoList == null ? 0 : doctorInfoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvName;
        TextView tvJob;
        TextView tvCompany;
        TextView tvInfo;
        ImageView ivSelect;
        LinearLayout ll;

        MyViewHolder(View view) {
            super(view);

            sdvImg = view.findViewById(R.id.sdvImg);
            tvName = view.findViewById(R.id.tvName);
            tvJob = view.findViewById(R.id.tvJob);
            tvCompany = view.findViewById(R.id.tvCompany);
            tvInfo = view.findViewById(R.id.tvInfo);
            ivSelect = view.findViewById(R.id.ivSelect);
            ll = view.findViewById(R.id.ll);


        }
    }
}