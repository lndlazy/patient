package com.ylean.cf_hospitalapp.register.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.register.bean.NumListEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/4.
 */

public class NumRegisterAdapter extends RecyclerView.Adapter<NumRegisterAdapter.MyViewHolder> {

    private Context context;
    private List<NumListEntry.DataBean> numRegisterList;

    public NumRegisterAdapter(Context context, List<NumListEntry.DataBean> numRegisterList) {
        this.context = context;
        this.numRegisterList = numRegisterList;
    }

    @Override
    public NumRegisterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new NumRegisterAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_num_register, parent, false));
    }

    @Override
    public void onBindViewHolder(NumRegisterAdapter.MyViewHolder holder, int position) {

        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + numRegisterList.get(position).getDocimg()));
        holder.tvName.setText(numRegisterList.get(position).getDocname());
        holder.tvjobTitle.setText(numRegisterList.get(position).getDoctitle());
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

        MyViewHolder(View view) {
            super(view);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvName = view.findViewById(R.id.tvName);
            tvjobTitle = view.findViewById(R.id.tvjobTitle);
            tvContent = view.findViewById(R.id.tvContent);
            tvOrder = view.findViewById(R.id.tvOrder);
            tvMoney = view.findViewById(R.id.tvMoney);
        }
    }
}