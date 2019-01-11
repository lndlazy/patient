package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.doctor.activity.DoctorDetailActivity;
import com.ylean.cf_hospitalapp.my.bean.MyDoctorListEntry;
import com.ylean.cf_hospitalapp.my.presenter.IDoctorListPres;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

/**
 * Created by linaidao on 2019/1/6.
 */

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.MyViewHolder> {

    private Context context;
    private List<MyDoctorListEntry.DataBean> myDoctorList;
    private IDoctorListPres iDoctorListPres;

    public DoctorListAdapter(Context context, List<MyDoctorListEntry.DataBean> myDoctorList, IDoctorListPres iDoctorListPres) {
        this.context = context;
        this.myDoctorList = myDoctorList;
        this.iDoctorListPres = iDoctorListPres;
    }

    @Override
    public DoctorListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DoctorListAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_doctor, parent, false));
    }

    @Override
    public void onBindViewHolder(DoctorListAdapter.MyViewHolder holder, int position) {

        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + myDoctorList.get(position).getImgurl()));
        holder.tvName.setText(myDoctorList.get(position).getDocname());
        holder.tvDepartment.setText(myDoctorList.get(position).getDepartname()
                + "  " + myDoctorList.get(position).getDtitlename());
        holder.tvHospitalName.setText(myDoctorList.get(position).getHospital());
        holder.tvIntroduce.setText(myDoctorList.get(position).getAdeptdesc());
        holder.rldoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.getAdapterPosition();
                Intent m = new Intent(context, DoctorDetailActivity.class);
                m.putExtra("doctorId", myDoctorList.get(holder.getAdapterPosition()).getDoctorid());
                context.startActivity(m);

            }
        });
        //是否关注 0 否 1 是
        switch (myDoctorList.get(position).getIsconsult()) {

            case "0":

                holder.tvAttention.setText("关注");
                holder.tvAttention.setTextColor(context.getResources().getColor(R.color.tab_colorf9));
                holder.tvAttention.setBackgroundResource(R.drawable.shape_white_blue);

                break;

            case "1":

                holder.tvAttention.setText("已关注");
                holder.tvAttention.setTextColor(context.getResources().getColor(R.color.white));
                holder.tvAttention.setBackgroundResource(R.drawable.shape_bg_blue);

                break;

        }

        holder.tvAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ("0".equals(myDoctorList.get(holder.getAdapterPosition()).getIsconsult())) {
                    //关注医生
                    iDoctorListPres.attentionDoctor(myDoctorList.get(holder.getAdapterPosition()).getDoctorid()
                            , (String) SaveUtils.get(context, SpValue.TOKEN, ""), DoctorListAdapter.this, holder.getAdapterPosition());
                } else if ("1".equals(myDoctorList.get(holder.getAdapterPosition()).getIsconsult())) {
                    //取消关注
                    iDoctorListPres.noAttentionDoctor(myDoctorList.get(holder.getAdapterPosition()).getDoctorid()
                            , (String) SaveUtils.get(context, SpValue.TOKEN, ""), DoctorListAdapter.this, holder.getAdapterPosition());
                }

            }
        });
    }


    public void refush(int i, String attention) {
        myDoctorList.get(i).setIsconsult(attention);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return myDoctorList == null ? 0 : myDoctorList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvName;
        TextView tvDepartment;
        TextView tvHospitalName;
        TextView tvIntroduce;
        TextView tvAttention;
        RelativeLayout rldoctor;

        MyViewHolder(View view) {
            super(view);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvName = view.findViewById(R.id.tvName);
            tvDepartment = view.findViewById(R.id.tvDepartment);
            tvHospitalName = view.findViewById(R.id.tvHospitalName);
            tvIntroduce = view.findViewById(R.id.tvIntroduce);
            tvAttention = view.findViewById(R.id.tvAttention);
            rldoctor = view.findViewById(R.id.rldoctor);
        }
    }
}