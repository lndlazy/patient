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

public class DisLeftAdapter extends RecyclerView.Adapter<DisLeftAdapter.MyViewHolder> {

    private Context context;
    private List<DisEntry.DataBean> departmentList;

    public DisLeftAdapter(Context context, List<DisEntry.DataBean> departmentList) {
        this.context = context;
        this.departmentList = departmentList;
    }

    @Override
    public DisLeftAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new DisLeftAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_string, parent, false));
    }

    @Override
    public void onBindViewHolder(DisLeftAdapter.MyViewHolder holder, int position) {

        holder.tvContent.setText(departmentList.get(position).getDepartname());
        holder.tvContent.setTextColor(departmentList.get(position).isSelect() ?
                context.getResources().getColor(R.color.tab_colorf9) :
                context.getResources().getColor(R.color.txt_color_light6));
    }

    @Override
    public int getItemCount() {
        return departmentList == null ? 0 : departmentList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        MyViewHolder(View view) {
            super(view);

            tvContent = view.findViewById(R.id.tvContent);

        }
    }
}