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
import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.doctor.activity.DoctorDetailActivity;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.register.activity.ChooseNumActivity;
import com.ylean.cf_hospitalapp.register.bean.NumListEntry;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

/**
 * Created by linaidao on 2019/1/4.
 */

public class NumRegisterAdapter extends RecyclerView.Adapter<NumRegisterAdapter.MyViewHolder> {

    private Context context;
    private List<NumListEntry.DataBean> numRegisterList;
    private int amOrPm;

    //i 上午还是下午 1是上午， 2是下午
    public NumRegisterAdapter(Context context, List<NumListEntry.DataBean> numRegisterList, int i) {
        this.context = context;
        this.numRegisterList = numRegisterList;
        amOrPm = i;
    }

    @Override
    public NumRegisterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new NumRegisterAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_num_register, parent, false));
    }

    @Override
    public void onBindViewHolder(NumRegisterAdapter.MyViewHolder holder, int position) {

        String reserveType = numRegisterList.get(position).getReserveType();

        //1-普通门诊 2-专家门诊 3-针对医生的门诊
        switch (reserveType) {

            case "1"://普通门诊
                holder.tvName.setText(numRegisterList.get(position).getDepartname());
                holder.tvjobTitle.setText("普通号");
                holder.sdvImg.setImageURI(Uri.parse(SpValue.FRESCO_RES + context.getPackageName() + "/" + R.mipmap.ic_hospital));

                break;
            case "2"://专家门诊
                holder.tvName.setText(numRegisterList.get(position).getDepartname());
                holder.tvjobTitle.setText("专家号");
                holder.sdvImg.setImageURI(Uri.parse(SpValue.FRESCO_RES + context.getPackageName() + "/" + R.mipmap.ic_hospital));

                break;
            case "3"://针对医生的门诊
                holder.tvName.setText(numRegisterList.get(position).getDocname());
                holder.tvjobTitle.setText(numRegisterList.get(position).getDoctitle());
                holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + numRegisterList.get(position).getDocimg()));

                break;
        }


        holder.tvContent.setText(numRegisterList.get(position).getDescription());

        //未约满-0 已约满-1
        switch (numRegisterList.get(position).getStatus()) {

            case 0:
                holder.tvOrder.setText("挂号");
                holder.tvOrder.setTextColor(context.getResources().getColor(R.color.tab_colorf9));
                holder.tvOrder.setBackgroundResource(R.drawable.shape_white_blue);
                break;

            case 1:
                holder.tvOrder.setText("已约满");
                holder.tvOrder.setTextColor(context.getResources().getColor(R.color.white));
                holder.tvOrder.setBackgroundResource(R.drawable.shape_yellow_big);
                break;

        }

        holder.tvMoney.setText("¥" + numRegisterList.get(position).getPrice());

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 进入医生详情
//                Logger.d("进入医生详情");
                Intent m = new Intent(context, DoctorDetailActivity.class);
                m.putExtra("doctorId", numRegisterList.get(position).getDoctorid());
                context.startActivity(m);

            }
        });

        holder.tvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //挂号

                if (context instanceof ChooseNumActivity) {
                    ChooseNumActivity c = (ChooseNumActivity) context;
                    c.registerOrder(position, numRegisterList, amOrPm);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return numRegisterList == null ? 0 : numRegisterList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvjobTitle;
        TextView tvName;
        TextView tvContent;
        TextView tvOrder;
        TextView tvMoney;
        RelativeLayout rl;

        MyViewHolder(View view) {
            super(view);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvName = view.findViewById(R.id.tvName);
            tvjobTitle = view.findViewById(R.id.tvjobTitle);
            tvContent = view.findViewById(R.id.tvContent);
            tvOrder = view.findViewById(R.id.tvOrder);
            tvMoney = view.findViewById(R.id.tvMoney);
            rl = view.findViewById(R.id.rl);
        }
    }
}