package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.HelpEntry;

import java.util.List;

/**
 * 帮帮团adapter
 * Created by linaidao on 2019/1/15.
 */

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.MyViewHolder> {

    private Context context;
    private List<HelpEntry.DataBean> helpList;

    public HelpAdapter(Context context, List<HelpEntry.DataBean> helpList) {
        this.context = context;
        this.helpList = helpList;
    }

    @Override
    public HelpAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HelpAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_help, parent, false));
    }

    @Override
    public void onBindViewHolder(HelpAdapter.MyViewHolder holder, int position) {

        holder.tvName.setText(helpList.get(position).getOwner());
        holder.tvHospitalName.setText(helpList.get(position).getRoomname());
        holder.tvIntroduce.setText(helpList.get(position).getDescription());
        holder.tvtime.setText(helpList.get(position).getCreatetime());
    }

    @Override
    public int getItemCount() {
        return helpList == null ? 0 : helpList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvName;
        TextView tvHospitalName;
        TextView tvIntroduce;
        TextView tvtime;

        MyViewHolder(View view) {
            super(view);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvName = view.findViewById(R.id.tvName);
            tvHospitalName = view.findViewById(R.id.tvHospitalName);
            tvIntroduce = view.findViewById(R.id.tvIntroduce);
            tvtime = view.findViewById(R.id.tvtime);
        }
    }
}