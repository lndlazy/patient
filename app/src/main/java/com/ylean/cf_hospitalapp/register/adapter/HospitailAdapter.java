package com.ylean.cf_hospitalapp.register.adapter;

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
import com.ylean.cf_hospitalapp.hospital.activity.HospitalDetailActivity;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.register.activity.DepartmentChooseActivity;
import com.ylean.cf_hospitalapp.register.bean.HospitalListEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/3.
 */

public class HospitailAdapter extends RecyclerView.Adapter<HospitailAdapter.MyViewHolder> {

    private Context context;
    private List<HospitalListEntry.DataBean> hospitailList;

    public HospitailAdapter(Context context, List<HospitalListEntry.DataBean> hospitailList) {
        this.context = context;
        this.hospitailList = hospitailList;
    }

    @Override
    public HospitailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HospitailAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_hospital, parent, false));
    }

    @Override
    public void onBindViewHolder(HospitailAdapter.MyViewHolder holder, final int position) {

        holder.sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + hospitailList.get(position).getImgurl()));
        holder.tvHospitalName.setText(hospitailList.get(position).getHospitalname());
        holder.tvAddress.setText(hospitailList.get(position).getAddress());
        holder.tel.setText(hospitailList.get(position).getSupportel());

        holder.rl_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent m = new Intent(context, HospitalDetailActivity.class);
                m.putExtra("hospitalInfo", hospitailList.get(position));
                context.startActivity(m);

//                Intent m = new Intent(context, WebviewActivity.class);
//                m.putExtra("title", "医院详细介绍");
//                String url = ApiService.WEB_ROOT + ApiService.HOSPITAL_DETAIL + "?id=" + hospitailList.get(position).getHospitalid();
//                m.putExtra("url", url);
//                context.startActivity(m);


            }
        });
        holder.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //选择科室,去挂号
                Intent m = new Intent(context, DepartmentChooseActivity.class);
                m.putExtra("hospitalInfo", hospitailList.get(position));
                context.startActivity(m);

            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitailList == null ? 0 : hospitailList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvPic;
        TextView tvHospitalName;
        TextView tvAddress;
        TextView tel;
        TextView tvRegister;
        RelativeLayout rl_content;

        MyViewHolder(View view) {
            super(view);
            rl_content = view.findViewById(R.id.rl_content);
            sdvPic = view.findViewById(R.id.sdvPic);
            tvHospitalName = view.findViewById(R.id.tvHospitalName);
            tvAddress = view.findViewById(R.id.tvAddress);
            tel = view.findViewById(R.id.tel);
            tvRegister = view.findViewById(R.id.tvRegister);
        }
    }
}