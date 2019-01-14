package com.ylean.cf_hospitalapp.hospital.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.hospital.bean.HospDepartListEntry;

import java.util.List;

/**
 * 医院科室
 * Created by linaidao on 2019/1/13.
 */

public class HospitalDepartmentAdapter extends RecyclerView.Adapter<HospitalDepartmentAdapter.MyViewHolder> {

    private Context context;
    private List<HospDepartListEntry.DataBean> departmentList;

    public HospitalDepartmentAdapter(Context context, List<HospDepartListEntry.DataBean> departmentList) {
        this.context = context;
        this.departmentList = departmentList;

    }

    @Override
    public HospitalDepartmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new HospitalDepartmentAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_choose, parent, false));
    }

    @Override
    public void onBindViewHolder(HospitalDepartmentAdapter.MyViewHolder holder, int position) {

        holder.tvContent.setText(departmentList.get(position).getName());

        holder.tvContent.setTextColor(context.getResources().getColor(R.color.color_ff));
        holder.tvContent.setBackgroundResource(R.drawable.shape_stroke_department);

//        holder.tvContent.setTextColor(commDisList.get(position).isSelect() ?
//                context.getResources().getColor(R.color.tab_colorf9) :
//                context.getResources().getColor(R.color.txt_color_light6));
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