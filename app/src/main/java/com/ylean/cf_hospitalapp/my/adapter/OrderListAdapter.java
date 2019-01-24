package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.activity.BuyServiceAct;
import com.ylean.cf_hospitalapp.inquiry.activity.InquiryEvaulateActivity;
import com.ylean.cf_hospitalapp.inquiry.activity.InquiryOrderDetialActivity;
import com.ylean.cf_hospitalapp.inquiry.bean.OrderEntry;
import com.ylean.cf_hospitalapp.my.activity.MyInquiryListActivity;
import com.ylean.cf_hospitalapp.my.bean.OrderInquiryDetailEntry;
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

                holder.tvOrderTime.setVisibility(View.GONE);
                holder.ivLine.setVisibility(View.GONE);

                break;

            case SpValue.ASK_TYPE_TEL:

                holder.tvOrderTime.setVisibility(View.VISIBLE);
                holder.ivLine.setVisibility(View.VISIBLE);

                break;

            case SpValue.ASK_TYPE_VIDEO:

                holder.tvOrderTime.setVisibility(View.VISIBLE);
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

//        switch (orderEntryList.get(position).getIscomment()) {
//
//            case 0:
//                holder.tvCommit.setText("评价");
//                holder.tvCommit.setBackgroundResource(R.drawable.shape_stroke_blue);
//                break;
//
//            case 1:
//                holder.tvCommit.setText("已评价");
//                holder.tvCommit.setBackgroundResource(R.color.white);
//                break;
//
//        }

        switch (orderEntryList.get(position).getStatus()) {

            case SpValue.ASK_STATUS_WAIT_PAY://待付款
                holder.tvStatus.setText("待付款");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_36));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);

                holder.tv2.setText("取消订单");
                holder.tv3.setText("去支付");

                break;

            case SpValue.ASK_STATUS_OVER_TIME://已过期
                holder.tvStatus.setText("已过期");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.txt_color_light9));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.VISIBLE);

                holder.tv3.setText("删除订单");

                break;

            case SpValue.ASK_STATUS_WAIT_SURE://待确认
                holder.tvStatus.setText("待确认");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.point_selected));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                break;

            case SpValue.ASK_STATUS_WAIT_COMPLY://待完成
                holder.tvStatus.setText("待完成");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_36));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                break;

            case SpValue.ASK_STATUS_COMPLY://已完成
                holder.tvStatus.setText("已完成");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_70));

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);

                holder.tv1.setText("删除订单");
                holder.tv2.setText("申请售后");

                holder.tv3.setText(1 == orderEntryList.get(position).getIscomment() ? "已评价" : "评价");

                break;

            case SpValue.ASK_STATUS_CANCLED://已取消

                holder.tvStatus.setText("已取消");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.txt_color_light9));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.VISIBLE);

                holder.tv3.setText("删除订单");

                break;

            case SpValue.ASK_STATUS_REFUND://申请退款中
                holder.tvStatus.setText("申请退款中");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.point_selected));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                break;

            case SpValue.ASK_STATUS_REFUND_OK://已退款
                holder.tvStatus.setText("已退款");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_70));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.VISIBLE);

                holder.tv3.setText("删除订单");

                break;

            case SpValue.ASK_STATUS_REFUND_FAIL://退款不通过
                holder.tvStatus.setText("退款不通过");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.txt_color_light9));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.VISIBLE);

                holder.tv3.setText("删除订单");

                break;

        }

        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (orderEntryList.get(position).getStatus()) {

                    case SpValue.ASK_STATUS_COMPLY://已完成

                        //("删除订单");
                        deleteOrder(position);
                        break;

                }
            }
        });

        holder.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (orderEntryList.get(position).getStatus()) {

                    case SpValue.ASK_STATUS_WAIT_PAY://待付款

                        //("取消订单");
                        cancleAction(position);
                        break;

                    case SpValue.ASK_STATUS_COMPLY://已完成

                        //("申请售后");
                        refundback(position);
                        break;

                }
            }
        });

        holder.tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (orderEntryList.get(position).getStatus()) {

                    case SpValue.ASK_STATUS_WAIT_PAY://待付款

                        //("去支付"); TODO 类型更换
                        go2pay(position);
                        break;

                    case SpValue.ASK_STATUS_OVER_TIME://已过期
                    case SpValue.ASK_STATUS_CANCLED://已取消
                    case SpValue.ASK_STATUS_REFUND_OK://已退款
                    case SpValue.ASK_STATUS_REFUND_FAIL://退款不通过

                        //("删除订单");
                        deleteOrder(position);
                        break;

                    case SpValue.ASK_STATUS_COMPLY://已完成

                        // "已评价" : "评价");
                        Intent m = new Intent(context, InquiryEvaulateActivity.class);
                        OrderInquiryDetailEntry.DataBean orderInfo = new OrderInquiryDetailEntry.DataBean();

                        orderInfo.setIscomment(orderEntryList.get(position).getIscomment() + "");
                        orderInfo.setCode(orderEntryList.get(position).getCode());
                        orderInfo.setDoctorimgurl(orderEntryList.get(position).getDoctorimgurl());
                        orderInfo.setDoctorname(orderEntryList.get(position).getDoctorname());
                        orderInfo.setDepartname(orderEntryList.get(position).getDepartname());
                        orderInfo.setTitlename(orderEntryList.get(position).getTitlename());
                        orderInfo.setHospitalname(orderEntryList.get(position).getHospitalname());
                        orderInfo.setAdeptdesc(orderEntryList.get(position).getAdeptdesc());
                        orderInfo.setDoctorid(orderEntryList.get(position).getDoctorid());
                        orderInfo.setAsktype(orderEntryList.get(position).getType());
                        orderInfo.setPrice(orderEntryList.get(position).getPrice());

                        m.putExtra("orderInfo", orderInfo);
                        context.startActivity(m);

                        break;


                }
            }
        });

        holder.tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (orderEntryList.get(position).getType()) {

                    case SpValue.ASK_TYPE_PIC://图文详情

                        Intent m = new Intent(context, InquiryOrderDetialActivity.class);
                        m.putExtra("type", orderEntryList.get(position).getType());
                        m.putExtra("id", orderEntryList.get(position).getOrderid());
                        context.startActivity(m);

                        break;

                    case SpValue.ASK_TYPE_TEL:
                        break;

                    case SpValue.ASK_TYPE_VIDEO:
                        break;

                }
            }
        });

    }

    private void go2pay(int position) {
        Intent m = new Intent(context, BuyServiceAct.class);
        m.putExtra("orderNum", orderEntryList.get(position).getCode());
        m.putExtra("doctorId", orderEntryList.get(position).getDoctorid());
        m.putExtra("doctorName", orderEntryList.get(position).getDoctorname());
        m.putExtra("type", "图片问诊");
        m.putExtra("price", orderEntryList.get(position).getPrice());
        context.startActivity(m);
    }

    private void refundback(int position) {
        if (context instanceof MyInquiryListActivity) {
            MyInquiryListActivity m = (MyInquiryListActivity) context;
            m.refund(orderEntryList.get(position));
        }
    }

    private void cancleAction(int position) {
        if (context instanceof MyInquiryListActivity) {
            MyInquiryListActivity m = (MyInquiryListActivity) context;
            m.cancleAction(orderEntryList.get(position));
        }
    }

    private void deleteOrder(int position) {
        if (context instanceof MyInquiryListActivity) {
            MyInquiryListActivity m = (MyInquiryListActivity) context;
            m.deleteAction(orderEntryList.get(position));
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
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
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
            tv1 = view.findViewById(R.id.tv1);
            tv2 = view.findViewById(R.id.tv2);
            tv3 = view.findViewById(R.id.tv3);
            tv4 = view.findViewById(R.id.tv4);
            ivLine = view.findViewById(R.id.ivLine);
        }
    }
}