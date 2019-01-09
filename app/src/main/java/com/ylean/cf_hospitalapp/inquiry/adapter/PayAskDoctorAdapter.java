package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.bean.DoctorListEntry;
import com.ylean.cf_hospitalapp.inquiry.activity.PayTWActivity;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.CommonUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

public class PayAskDoctorAdapter extends RecyclerView.Adapter<PayAskDoctorAdapter.MyViewHolder> {

    private Context context;
    private List<DoctorListEntry.DataBean> doctorInfoList;

    public PayAskDoctorAdapter(Context context, List<DoctorListEntry.DataBean> doctorInfoList) {
        this.context = context;
        this.doctorInfoList = doctorInfoList;
    }

    @Override
    public PayAskDoctorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PayAskDoctorAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_pay_ask_doctor, parent, false));
    }

    @Override
    public void onBindViewHolder(PayAskDoctorAdapter.MyViewHolder holder, final int position) {

        holder.sdvImg.setImageURI(ApiService.WEB_ROOT + Uri.parse(doctorInfoList.get(position).getImgurl()));
        holder.tvName.setText(doctorInfoList.get(position).getDoctorname());
        holder.tvJob.setText(doctorInfoList.get(position).getDoctortitle());
        holder.tvCompany.setText(doctorInfoList.get(position).getHospitalname());
        holder.tvInfo.setText(TextUtils.isEmpty(doctorInfoList.get(position).getAdeptdesc()) ? "暂无简介" :
                doctorInfoList.get(position).getAdeptdesc());
        holder.tvp1.setText(CommonUtils.getNum2(doctorInfoList.get(position).getTwprice()));
        holder.tvp2.setText(CommonUtils.getNum2(doctorInfoList.get(position).getDhprice()));
        holder.tvp3.setText(CommonUtils.getNum2(doctorInfoList.get(position).getSpprice()));

        //图文问诊
        holder.llTuwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage(position, context, SpValue.ASK_TYPE_PIC);
            }
        });
        //电话问诊
        holder.llTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage(position, context, SpValue.ASK_TYPE_TEL);
            }
        });
        //视频问诊
        holder.llVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage(position, context, SpValue.ASK_TYPE_VIDEO);
            }
        });
    }

    private void nextPage(int position, Context context, String askType) {
        Intent m = new Intent(context, PayTWActivity.class);
        m.putExtra("doctorId", doctorInfoList.get(position).getDoctorid());//医生id
        m.putExtra("doctorName", doctorInfoList.get(position).getDoctorname());//医生姓名
        m.putExtra("price", doctorInfoList.get(position).getTwprice());//问诊价格
        m.putExtra("type", askType);//图文，视频，电话 问诊
        m.putExtra("askType", SpValue.ASK_CHARGE);//付费问诊
        context.startActivity(m);
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
        TextView tvp1;
        TextView tvp2;
        TextView tvp3;

        LinearLayout llTuwen;
        LinearLayout llTel;
        LinearLayout llVideo;
        LinearLayout llPrice;

        MyViewHolder(View view) {
            super(view);

            sdvImg = view.findViewById(R.id.sdvImg);
            tvName = view.findViewById(R.id.tvName);
            tvJob = view.findViewById(R.id.tvJob);
            tvCompany = view.findViewById(R.id.tvCompany);
            tvInfo = view.findViewById(R.id.tvInfo);
            tvp1 = view.findViewById(R.id.tvp1);
            tvp2 = view.findViewById(R.id.tvp2);
            tvp3 = view.findViewById(R.id.tvp3);
            llTuwen = view.findViewById(R.id.llTuwen);
            llTel = view.findViewById(R.id.llTel);
            llVideo = view.findViewById(R.id.llVideo);
            llPrice = view.findViewById(R.id.llPrice);
        }
    }
}