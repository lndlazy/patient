package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.bean.GradeLevelBean;

import java.util.List;

public class HospitalLevelAdapter extends RecyclerView.Adapter<HospitalLevelAdapter.MyViewHolder> {

    private Context context;
    private List<GradeLevelBean.DataBean> gradeLevelList;

    public HospitalLevelAdapter(Context context, List<GradeLevelBean.DataBean> gradeLevelList) {
        this.context = context;
        this.gradeLevelList = gradeLevelList;
    }

    @Override
    public HospitalLevelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new HospitalLevelAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_choose, parent, false));
    }

    @Override
    public void onBindViewHolder(HospitalLevelAdapter.MyViewHolder holder, int position) {

        holder.tvContent.setText(gradeLevelList.get(position).getName());
        holder.tvContent.setTextColor(gradeLevelList.get(position).isSelect()?
                    context.getResources().getColor(R.color.tab_colorf9) :
                context.getResources().getColor(R.color.txt_color_light6));
    }

    @Override
    public int getItemCount() {
        return gradeLevelList == null ? 0 : gradeLevelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        MyViewHolder(View view) {
            super(view);
            tvContent = view.findViewById(R.id.tvContent);
        }
    }
}