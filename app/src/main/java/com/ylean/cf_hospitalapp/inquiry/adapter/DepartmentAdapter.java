package com.ylean.cf_hospitalapp.inquiry.adapter;

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

import java.util.List;

/**
 * 筛选科室分类
 */
public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.MyViewHolder> {

    private Context context;
    private List<DepartmentListEntry.DataBean> departmentList;

    public DepartmentAdapter(Context context, List<DepartmentListEntry.DataBean> departmentList) {
        this.context = context;
        this.departmentList = departmentList;
    }

    @Override
    public DepartmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new DepartmentAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.dialog_sort, parent, false));
    }

    @Override
    public void onBindViewHolder(DepartmentAdapter.MyViewHolder holder, int position) {

        holder.tvCount.setText(departmentList.get(position).getFdepartname());
        holder.tvCount.setTextColor(departmentList.get(position).isSelect() ?
                context.getResources().getColor(R.color.tab_colorf9) :
                context.getResources().getColor(R.color.txt_color_light6));
    }

    @Override
    public int getItemCount() {
        return departmentList == null ? 0 : departmentList.size();
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