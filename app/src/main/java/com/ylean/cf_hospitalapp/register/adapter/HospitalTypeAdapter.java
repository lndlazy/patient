package com.ylean.cf_hospitalapp.register.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.register.bean.HospitalTypeEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/3.
 */

public class HospitalTypeAdapter extends RecyclerView.Adapter<HospitalTypeAdapter.MyViewHolder> {

    private Context context;
    private List<HospitalTypeEntry.DataBean> hospitalTypeList;

    public HospitalTypeAdapter(Context context, List<HospitalTypeEntry.DataBean> hospitalTypeList) {
        this.context = context;
        this.hospitalTypeList = hospitalTypeList;
    }

    @Override
    public HospitalTypeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new HospitalTypeAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_choose, parent, false));
    }

    @Override
    public void onBindViewHolder(HospitalTypeAdapter.MyViewHolder holder, int position) {

        holder.tvContent.setText(hospitalTypeList.get(position).getName());
        holder.tvContent.setTextColor(hospitalTypeList.get(position).isSelect()?
                context.getResources().getColor(R.color.tab_colorf9) :
                context.getResources().getColor(R.color.txt_color_light6));
    }

    @Override
    public int getItemCount() {
        return hospitalTypeList == null ? 0 : hospitalTypeList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        MyViewHolder(View view) {
            super(view);
            tvContent = view.findViewById(R.id.tvContent);
        }
    }
}