package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.FromType;
import com.ylean.cf_hospitalapp.my.bean.AreaEntry;
import com.ylean.cf_hospitalapp.my.bean.PointsDetailEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/3.
 */

public class PointsDetailAdapter extends RecyclerView.Adapter<PointsDetailAdapter.MyViewHolder> {

    private Context context;
    //    private List<AreaEntry.DataBean> areaList;
    private List<PointsDetailEntry.DataBean> pointsList;

    public PointsDetailAdapter(Context context, List<PointsDetailEntry.DataBean> pointsList) {
        this.context = context;
        this.pointsList = pointsList;
//        this.areaList = areaList;
    }

    @Override
    public PointsDetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PointsDetailAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_points_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(PointsDetailAdapter.MyViewHolder holder, int position) {

        holder.tvTitle.setText(FromType.getType(pointsList.get(position).getFromtype()));
        holder.tvTime.setText(pointsList.get(position).getCreatetime());
        switch (pointsList.get(position).getType()) {

            case "0"://增加

                holder.tvDetail.setTextColor(context.getResources().getColor(R.color.color_70));
                holder.tvDetail.setText("+" + pointsList.get(position).getPoints());
                break;

            case "1"://消费
                holder.tvDetail.setTextColor(context.getResources().getColor(R.color.color_36));
                holder.tvDetail.setText("-" + pointsList.get(position).getPoints());

                break;

        }


    }

    @Override
    public int getItemCount() {
        return pointsList == null ? 0 : pointsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvTime;
        TextView tvDetail;

        MyViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvTime = view.findViewById(R.id.tvTime);
            tvDetail = view.findViewById(R.id.tvDetail);
        }
    }
}