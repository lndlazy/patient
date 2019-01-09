package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.bean.OrderEntry;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

/**
 * Created by linaidao on 2018/12/24.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {

    private Context context;
    private List<OrderEntry.DataBean> orderEntryList;
    private String type;

    public OrderListAdapter(Context context, List<OrderEntry.DataBean> orderEntryList, String type) {
        this.context = context;
        this.orderEntryList = orderEntryList;
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public OrderListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderListAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_order_info, parent, false));
    }

    @Override
    public void onBindViewHolder(OrderListAdapter.MyViewHolder holder, int position) {

        switch (type) {

            case SpValue.ASK_TYPE_PIC:

                holder.tvCommit.setVisibility(View.GONE);
                holder.ivLine.setVisibility(View.GONE);

                break;

            case SpValue.ASK_TYPE_TEL:

                holder.tvCommit.setVisibility(View.VISIBLE);
                holder.ivLine.setVisibility(View.VISIBLE);
                break;

            case SpValue.ASK_TYPE_VIDEO:

                holder.tvCommit.setVisibility(View.VISIBLE);
                holder.ivLine.setVisibility(View.VISIBLE);

                break;

        }

        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + orderEntryList.get(position).getDoctorimgurl()));
        holder.tvName.setText(orderEntryList.get(position).getDoctorname());
        holder.tvJob.setText(orderEntryList.get(position).getDepartname());
        holder.tvCompany.setText(orderEntryList.get(position).getHospitalname());
        holder.tvInfo.setText(orderEntryList.get(position).getAdeptdesc());
        holder.tvOrderTime.setText("预约时间：" + orderEntryList.get(position).getAsktime());
        holder.tvMoney.setText("¥ " + orderEntryList.get(position).getPrice());

        switch (orderEntryList.get(position).getIscomment()) {

            case 0:
                holder.tvCommit.setText("评价");
                holder.tvCommit.setBackgroundResource(R.drawable.shape_stroke_blue);
                break;

            case 1:
                holder.tvCommit.setText("已评价");
                holder.tvCommit.setBackgroundResource(R.color.white);
                break;

        }

        switch (orderEntryList.get(position).getStatus()) {

            case SpValue.ASK_STATUS_WAIT_PAY://待付款
                holder.tvStatus.setText("待付款");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_36));
                break;

            case SpValue.ASK_STATUS_OVER_TIME://已过期
                holder.tvStatus.setText("已过期");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.txt_color_light9));
                break;

            case SpValue.ASK_STATUS_WAIT_SURE://待确认
                holder.tvStatus.setText("待确认");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.point_selected));
                break;

            case SpValue.ASK_STATUS_WAIT_COMPLY://待完成
                holder.tvStatus.setText("待完成");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_36));
                break;

            case SpValue.ASK_STATUS_COMPLY://已完成
                holder.tvStatus.setText("已完成");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_70));
                break;

            case SpValue.ASK_STATUS_CANCLED://已取消
                holder.tvStatus.setText("已取消");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.txt_color_light9));
                break;

            case SpValue.ASK_STATUS_REFUND://申请退款中
                holder.tvStatus.setText("申请退款中");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.point_selected));
                break;

            case SpValue.ASK_STATUS_REFUND_OK://已退款
                holder.tvStatus.setText("已退款");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_70));
                break;

            case SpValue.ASK_STATUS_REFUND_FAIL://退款不通过
                holder.tvStatus.setText("退款不通过");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.txt_color_light9));
                break;

        }

    }

    @Override
    public int getItemCount() {
        return orderEntryList == null ? 0 : orderEntryList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;

        TextView tvName;
        TextView tvJob;
        TextView tvCompany;
        TextView tvInfo;
        TextView tvOrderTime;
        TextView tvStatus;
        TextView tvMoney;
        TextView tvDetail;
        TextView tvCommit;
        ImageView ivLine;

        MyViewHolder(View view) {
            super(view);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvName = view.findViewById(R.id.tvName);
            tvJob = view.findViewById(R.id.tvJob);
            tvCompany = view.findViewById(R.id.tvCompany);
            tvInfo = view.findViewById(R.id.tvInfo);
            tvOrderTime = view.findViewById(R.id.tvOrderTime);
            tvStatus = view.findViewById(R.id.tvStatus);
            tvMoney = view.findViewById(R.id.tvMoney);
            tvDetail = view.findViewById(R.id.tvDetail);
            tvCommit = view.findViewById(R.id.tvCommit);
            ivLine = view.findViewById(R.id.ivLine);
        }
    }
}