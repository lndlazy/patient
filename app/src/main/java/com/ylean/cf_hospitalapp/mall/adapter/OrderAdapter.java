package com.ylean.cf_hospitalapp.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.mall.GoodsStatus;
import com.ylean.cf_hospitalapp.mall.acitity.GoodsPayActivity;
import com.ylean.cf_hospitalapp.mall.acitity.RefundActivity;
import com.ylean.cf_hospitalapp.mall.bean.GoodsInfoEntry;
import com.ylean.cf_hospitalapp.mall.bean.MallOrderEntry;
import com.ylean.cf_hospitalapp.net.ApiService;

import java.util.List;

/**
 * Created by linaidao on 2019/1/21.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private Context context;
    private List<MallOrderEntry.DataBean> orderList;
    private Intent mIntent;

    public OrderAdapter(Context context, List<MallOrderEntry.DataBean> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_goods_order, parent, false));
    }

    @Override
    public void onBindViewHolder(OrderAdapter.MyViewHolder holder, int position) {

        holder.tvordernum.setText("订单号：" + orderList.get(position).getCode());

        switch (orderList.get(position).getStatus()) {

            case GoodsStatus.WAIT_PAY://WAIT_PAY = "0";//待付款
                holder.tvstatus.setText("待付款");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));

                holder.tvleft.setVisibility(View.VISIBLE);
                holder.tvleft.setText("去支付");

                break;

            case GoodsStatus.WAIT_SEND://WAIT_SEND = "1";//待发货(
                holder.tvstatus.setText("待发货");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));

                holder.tvleft.setVisibility(View.VISIBLE);
                holder.tvleft.setText("取消订单");

                break;

            case GoodsStatus.WAIT_RECEIVE://WAIT_RECEIVE = "2";//待收货
                holder.tvstatus.setText("待收货");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));


                holder.tvleft.setVisibility(View.VISIBLE);
                holder.tvleft.setText("取消订单");

                break;

            case GoodsStatus.RECEIVE://RECEIVE = "3";//已确认收货
                holder.tvstatus.setText("已确认收货");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.tab_colorf9));


                holder.tvleft.setVisibility(View.VISIBLE);
                holder.tvleft.setText("申请退货");

                break;

            case GoodsStatus.CANCLE://CANCLE = "4";//已取消
                holder.tvstatus.setText("已取消");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.txt_color_light9));

                holder.tvleft.setVisibility(View.INVISIBLE);


                break;

            case GoodsStatus.REBACKING://REBACKING = "5";//退货中
                holder.tvstatus.setText("退货中");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));

                holder.tvleft.setVisibility(View.INVISIBLE);


                break;

            case GoodsStatus.BACK_SUCCESS:// BACK_SUCCESS = "6";//已退款
                holder.tvstatus.setText("已退款");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.tab_colorf9));

                holder.tvleft.setVisibility(View.INVISIBLE);


                break;

            case GoodsStatus.REBACK_FAIL://REBACK_FAIL = "7";//退款不通过
                holder.tvstatus.setText("退款不通过");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));

                holder.tvleft.setVisibility(View.INVISIBLE);

                break;

            case GoodsStatus.DONE://DONE = "8";//已完成
                holder.tvstatus.setText("已完成");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.tab_colorf9));

                if ("1".equals(orderList.get(position).getIscomment())) {
                    holder.tvleft.setVisibility(View.VISIBLE);
                    holder.tvleft.setText("已评价");
                } else {
                    holder.tvleft.setVisibility(View.VISIBLE);
                    holder.tvleft.setText("评价");
                }

                break;

            case GoodsStatus.WAIT_USE://WAIT_USE = "10";//待使用
                holder.tvstatus.setText("待使用");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));

                holder.tvleft.setVisibility(View.VISIBLE);
                holder.tvleft.setText("申请退款");

                break;

            case GoodsStatus.EXCHANGEING:// EXCHANGEING = "11";//换货中
                holder.tvstatus.setText("换货中");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));

                holder.tvleft.setVisibility(View.INVISIBLE);


                break;

            case GoodsStatus.ALREADY_EXCHANGING://ALREADY_EXCHANGING = "12";//已换货
                holder.tvstatus.setText("已换货");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.tab_colorf9));

                holder.tvleft.setVisibility(View.INVISIBLE);

                break;

            case GoodsStatus.EXCHANGE_RESUFED://EXCHANGE_RESUFED = "13";//换货不通过
                holder.tvstatus.setText("换货不通过");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));
                holder.tvleft.setVisibility(View.INVISIBLE);

                break;

        }


        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + orderList.get(position).getSkuimg()));
        holder.tvTitle.setText(orderList.get(position).getSkuname());
        holder.tvPP.setText(orderList.get(position).getPoints() + "积分+" + orderList.get(position).getPrice() + "元");
        holder.tvdesc.setText("共1件商品  ¥" + orderList.get(position).getPrice());


        holder.tvleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (orderList.get(holder.getAdapterPosition()).getStatus()) {

                    case GoodsStatus.WAIT_PAY://WAIT_PAY = "0";//待付款
                        //去支付
                        GoodsInfoEntry.DataBean goodsInfo = new GoodsInfoEntry.DataBean();
                        goodsInfo.setType(orderList.get(position).getOrdertype());
                        goodsInfo.setPrice(orderList.get(position).getPrice());

                        mIntent = new Intent(context, GoodsPayActivity.class);
                        mIntent.putExtra("goodsInfo", goodsInfo);
                        mIntent.putExtra("orderCode", orderList.get(position).getCode());
                        mIntent.putExtra("freightPrice", orderList.get(position).getFreightmoney());//运费
                        context.startActivity(mIntent);

                        break;

                    case GoodsStatus.WAIT_SEND://WAIT_SEND = "1";//待发货(
                    case GoodsStatus.WAIT_RECEIVE://WAIT_RECEIVE = "2";//待收货
                    case GoodsStatus.RECEIVE://RECEIVE = "3";//已确认收货
                    case GoodsStatus.WAIT_USE://WAIT_USE = "10";//待使用
                        //申请退款
                        mIntent = new Intent(context, RefundActivity.class);
                        mIntent.putExtra("orderInfo", orderList.get(holder.getAdapterPosition()));
                        mIntent.putExtra("type", "2");
                        context.startActivity(mIntent);
                        break;

                    case GoodsStatus.CANCLE://CANCLE = "4";//已取消
                    case GoodsStatus.REBACKING://REBACKING = "5";//退货中
                    case GoodsStatus.BACK_SUCCESS:// BACK_SUCCESS = "6";//已退款
                    case GoodsStatus.REBACK_FAIL://REBACK_FAIL = "7";//退款不通过
                    case GoodsStatus.EXCHANGEING:// EXCHANGEING = "11";//换货中
                    case GoodsStatus.ALREADY_EXCHANGING://ALREADY_EXCHANGING = "12";//已换货
                    case GoodsStatus.EXCHANGE_RESUFED://EXCHANGE_RESUFED = "13";//换货不通过

                        break;

                    case GoodsStatus.DONE://DONE = "8";//已完成

                        if ("1".equals(orderList.get(position).getIscomment())) {
                            //已评价
                        } else {
                            //去评价

                        }

                        break;

                }

            }
        });

        holder.tvright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList == null ? 0 : orderList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvordernum;//订单号
        TextView tvstatus;//状态
        SimpleDraweeView sdvImg;
        TextView tvTitle;//产品说明
        TextView tvPP;//价格
        TextView tvdesc;//价格
        TextView tvright;//查看详情
        TextView tvleft;//评价

        MyViewHolder(View view) {
            super(view);
            tvordernum = view.findViewById(R.id.tvordernum);
            tvstatus = view.findViewById(R.id.tvstatus);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvPP = view.findViewById(R.id.tvPP);
            tvdesc = view.findViewById(R.id.tvdesc);
            tvright = view.findViewById(R.id.tvright);
            tvleft = view.findViewById(R.id.tvleft);
        }
    }
}