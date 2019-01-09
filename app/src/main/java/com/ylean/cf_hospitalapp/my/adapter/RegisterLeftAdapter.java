package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.bean.DepartmentListEntry;
import com.ylean.cf_hospitalapp.register.bean.RegisterDeartmentListEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/4.
 */

public class RegisterLeftAdapter extends RecyclerView.Adapter<RegisterLeftAdapter.MyViewHolder> {

    private Context context;
    private List<RegisterDeartmentListEntry.DataBean> leftData;

    public RegisterLeftAdapter(Context context, List<RegisterDeartmentListEntry.DataBean> leftData) {
        this.context = context;
        this.leftData = leftData;
    }

    @Override
    public RegisterLeftAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RegisterLeftAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.dialog_sort, parent, false));
    }

    @Override
    public void onBindViewHolder(RegisterLeftAdapter.MyViewHolder holder, int position) {

        holder.tvCount.setText(leftData.get(position).getDepartname());
        holder.tvCount.setTextColor(leftData.get(position).isSelect() ?
                context.getResources().getColor(R.color.tab_colorf9) :
                context.getResources().getColor(R.color.txt_color_light6));

        holder.rlCount.setBackgroundColor(leftData.get(position).isSelect() ?
                context.getResources().getColor(R.color.white)
                : context.getResources().getColor(R.color.gray_bg_eb));

    }

    @Override
    public int getItemCount() {
        return leftData == null ? 0 : leftData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvCount;
        ImageView ivCount;
        RelativeLayout rlCount;

        MyViewHolder(View view) {
            super(view);
            tvCount = view.findViewById(R.id.tvCount);
            rlCount = view.findViewById(R.id.rlCount);
            ivCount = view.findViewById(R.id.ivCount);
        }
    }
}