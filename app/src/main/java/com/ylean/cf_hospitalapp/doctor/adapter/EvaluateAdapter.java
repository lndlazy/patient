package com.ylean.cf_hospitalapp.doctor.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.doctor.activity.DoctorDetailActivity;
import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.net.ApiService;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * 评价列表
 * Created by linaidao on 2019/1/11.
 */

public class EvaluateAdapter extends RecyclerView.Adapter<EvaluateAdapter.MyViewHolder> {

    private Context context;
    private List<CommComListEntry.DataBean> commitList;

    public EvaluateAdapter(Context context, List<CommComListEntry.DataBean> commitList) {
        this.commitList = commitList;
        this.context = context;
    }

    @Override
    public EvaluateAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new EvaluateAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_evaluate, parent, false));
    }

    @Override
    public void onBindViewHolder(EvaluateAdapter.MyViewHolder holder, int i) {

        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + commitList.get(i).getPlimg()));
        holder.tvName.setText(commitList.get(i).getEvaluatename());
        holder.tvAttention.setText("1".equals(commitList.get(i).getIsfollow()) ? "已关注" : "未关注");
        try {
            holder.mrevaulate.setProgress(Integer.parseInt(commitList.get(i).getLivestars()));
        }catch (Exception e) {
            e.printStackTrace();
        }
        holder.tvstartcount.setText(commitList.get(i).getLivestars());
        holder.mrevaulate.setIsIndicator(true);//评价星星不可编辑
        holder.tvtime.setText(commitList.get(i).getTimedesc());
        holder.tvcontent.setText(commitList.get(i).getContent());

        holder.tvAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof DoctorDetailActivity) {
                    DoctorDetailActivity activity = (DoctorDetailActivity) context;
                    activity.personAttention(commitList.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return commitList == null ? 0 : commitList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvName;
        TextView tvAttention;
        TextView tvcontent;
        TextView tvtime;
        TextView tvstartcount;
        MaterialRatingBar mrevaulate;

        MyViewHolder(View view) {
            super(view);

            sdvImg = view.findViewById(R.id.sdvImg);
            tvName = view.findViewById(R.id.tvName);
            tvAttention = view.findViewById(R.id.tvAttention);
            tvcontent = view.findViewById(R.id.tvcontent);
            tvtime = view.findViewById(R.id.tvtime);
            tvstartcount = view.findViewById(R.id.tvstartcount);
            mrevaulate = view.findViewById(R.id.mrevaulate);

        }
    }
}