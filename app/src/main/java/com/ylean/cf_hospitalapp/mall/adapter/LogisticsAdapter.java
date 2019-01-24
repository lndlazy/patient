package com.ylean.cf_hospitalapp.mall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.mall.bean.LogisticEntry;
import com.ylean.cf_hospitalapp.utils.DensityUtil;

import java.util.List;

/**
 * Created by linaidao on 2019/1/24.
 */

public class LogisticsAdapter extends RecyclerView.Adapter<LogisticsAdapter.LogisticsAdapterHolder> {

    private Context ctx;
    private LayoutInflater mLayoutInflater;
    private List<LogisticEntry.DataBean.TracesBean> lofisitcTraces;


    public LogisticsAdapter(Context ctx, List<LogisticEntry.DataBean.TracesBean> lofisitcTraces) {
        this.ctx = ctx;
        this.mLayoutInflater = LayoutInflater.from(ctx);
        this.lofisitcTraces = lofisitcTraces;
    }

    @Override
    public int getItemCount() {
        return lofisitcTraces == null ? 0 : lofisitcTraces.size();
    }

    @Override
    public LogisticsAdapter.LogisticsAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LogisticsAdapter.LogisticsAdapterHolder(mLayoutInflater.inflate(R.layout.item_logistics, parent, false));
    }

    @Override
    public void onBindViewHolder(LogisticsAdapter.LogisticsAdapterHolder holder, int position) {

        if (position == 0) {

            //绿色的圆点
            holder.iv_status.setImageResource(R.drawable.shape_circle_logistics_green);
            RelativeLayout.LayoutParams pointParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(ctx, 20), DensityUtil.dip2px(ctx, 20));
            pointParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            holder.iv_status.setLayoutParams(pointParams);

            holder.tv_time.setTextColor(ctx.getResources().getColor(R.color.color_25));
            holder.tv_status.setTextColor(ctx.getResources().getColor(R.color.color_25));

            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(ctx, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status);//让直线置于圆点下面
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            holder.iv_line.setLayoutParams(lineParams);

        } else {

//                holder.iv_status.setBackgroundResource(R.mipmap.ic_logistics_bottom);
            holder.iv_status.setImageResource(R.drawable.shape_circle_logistics_gray);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DensityUtil.dip2px(ctx, 10), DensityUtil.dip2px(ctx, 10));
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);

            holder.iv_status.setLayoutParams(lp);

            holder.tv_time.setTextColor(ctx.getResources().getColor(R.color.textColor_9b));
            holder.tv_status.setTextColor(ctx.getResources().getColor(R.color.textColor_9b));

            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(ctx, 1), ViewGroup.LayoutParams.MATCH_PARENT);
//                lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status);//让直线置于圆点下面
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            holder.iv_line.setLayoutParams(lineParams);

        }

        holder.tv_status.setText(lofisitcTraces.get(position).getAcceptstation());
        holder.tv_time.setText(lofisitcTraces.get(position).getAccepttime());

    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class LogisticsAdapterHolder extends RecyclerView.ViewHolder {

        ImageView iv_status;
        TextView tv_status;
        TextView tv_time;
        ImageView iv_line;

        LogisticsAdapterHolder(View view) {
            super(view);


            iv_line = (ImageView) view.findViewById(R.id.iv_line);

            iv_status = (ImageView) view.findViewById(R.id.iv_status);
            tv_status = (TextView) view.findViewById(R.id.tv_status);
            tv_time = (TextView) view.findViewById(R.id.tv_time);

        }
    }

}