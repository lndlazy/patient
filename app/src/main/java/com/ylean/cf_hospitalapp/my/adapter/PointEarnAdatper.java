package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.EarnPointsBean;

import java.util.List;

/**
 * Created by linaidao on 2019/3/1.
 */

public class PointEarnAdatper extends RecyclerView.Adapter<PointEarnAdatper.MyViewHolder> {

    private Context context;
    private List<EarnPointsBean.DataBean> pointsList;

    public PointEarnAdatper(Context context, List<EarnPointsBean.DataBean> pointsList) {
        this.context = context;
        this.pointsList = pointsList;
    }

    @Override
    public PointEarnAdatper.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PointEarnAdatper.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_earn_points, parent, false));
    }

    @Override
    public void onBindViewHolder(PointEarnAdatper.MyViewHolder holder, int position) {

        holder.tvTitle.setText(pointsList.get(position).getName());
        holder.tvPoints.setText("+" + pointsList.get(position).getPoints());
        
        if ("签到".equals(pointsList.get(position).getName()))
            holder.tvType.setText("1".equals(pointsList.get(position).getIscomplete()) ? "已完成" : "签到");
        else
            holder.tvType.setText("1".equals(pointsList.get(position).getIscomplete()) ? "已完成" : "未完成");

        holder.tvType.setBackgroundResource("1".equals(pointsList.get(position).getIscomplete()) ?
                R.drawable.shape_bg_blue_big : R.drawable.shape_white_blue);
        holder.tvType.setTextColor("1".equals(pointsList.get(position).getIscomplete()) ? context.getResources().getColor(R.color.white)
                : context.getResources().getColor(R.color.tab_colorf9));

    }

    @Override
    public int getItemCount() {
        return pointsList == null ? 0 : pointsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvType;
        TextView tvPoints;

        MyViewHolder(View view) {
            super(view);

            tvTitle = view.findViewById(R.id.tvTitle);
            tvType = view.findViewById(R.id.tvType);
            tvPoints = view.findViewById(R.id.tvPoints);

        }
    }
}