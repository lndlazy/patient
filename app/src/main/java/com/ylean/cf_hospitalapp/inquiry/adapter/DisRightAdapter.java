package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.bean.DisEntry;

import java.util.List;

/**
 * Created by linaidao on 2018/12/18.
 */

public class DisRightAdapter extends RecyclerView.Adapter<DisRightAdapter.MyViewHolder> {

    private Context context;
    private List<DisEntry.DataBean.DiseaselistBean> diseaselist;

    public DisRightAdapter(Context context,List<DisEntry.DataBean.DiseaselistBean> diseaselist) {
        this.context = context;
        this.diseaselist = diseaselist;
    }

    @Override
    public DisRightAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new DisRightAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_string, parent, false));
    }

    @Override
    public void onBindViewHolder(DisRightAdapter.MyViewHolder holder, int position) {

        holder.tvContent.setText(diseaselist.get(position).getDiseasename());
        holder.tvContent.setTextColor(diseaselist.get(position).isSelect() ?
                context.getResources().getColor(R.color.tab_colorf9) :
                context.getResources().getColor(R.color.txt_color_light6));
    }

    @Override
    public int getItemCount() {
        return diseaselist == null ? 0 : diseaselist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        MyViewHolder(View view) {
            super(view);

            tvContent = view.findViewById(R.id.tvContent);

        }
    }
}