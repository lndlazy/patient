package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.bean.DoctorLevelListBean;

import java.util.List;

/**
 * 医生等级adapter
 */
public class DocterLevelAdapter extends RecyclerView.Adapter<DocterLevelAdapter.MyViewHolder> {

    private Context context;
    private List<DoctorLevelListBean.DataBean> doctorLevelList;

    public DocterLevelAdapter(Context context, List<DoctorLevelListBean.DataBean> doctorLevelList) {
        this.context = context;
        this.doctorLevelList = doctorLevelList;
    }

    @Override
    public DocterLevelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new DocterLevelAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_choose, parent, false));
    }

    @Override
    public void onBindViewHolder(DocterLevelAdapter.MyViewHolder holder, int position) {

        holder.tvContent.setText(doctorLevelList.get(position).getDoctitle());
        holder.tvContent.setTextColor(doctorLevelList.get(position).isSelect()?
                context.getResources().getColor(R.color.tab_colorf9) :
                context.getResources().getColor(R.color.txt_color_light6));
    }

    @Override
    public int getItemCount() {
        return doctorLevelList == null ? 0 : doctorLevelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        MyViewHolder(View view) {
            super(view);
            tvContent = view.findViewById(R.id.tvContent);
        }
    }
}