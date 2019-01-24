package com.ylean.cf_hospitalapp.mall.adapter;

import android.content.Context;
import android.content.DialogInterface;
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
import com.ylean.cf_hospitalapp.mall.acitity.GoodsCommandActivity;
import com.ylean.cf_hospitalapp.mall.acitity.GoodsOrderListActivity;
import com.ylean.cf_hospitalapp.mall.acitity.GoodsPayActivity;
import com.ylean.cf_hospitalapp.mall.acitity.LogisticActivity;
import com.ylean.cf_hospitalapp.mall.acitity.OrderDetailActivity;
import com.ylean.cf_hospitalapp.mall.acitity.RefundActivity;
import com.ylean.cf_hospitalapp.mall.acitity.ServiceOrderListActivity;
import com.ylean.cf_hospitalapp.mall.bean.GoodsInfoEntry;
import com.ylean.cf_hospitalapp.mall.bean.GoodsOrderInfoEntry;
import com.ylean.cf_hospitalapp.mall.bean.MallOrderEntry;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

/**
 * 订单列表adapter
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

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);

                holder.tv2.setText("取消订单");
                holder.tv3.setText("去支付");


                break;

            case GoodsStatus.WAIT_SEND://WAIT_SEND = "1";//待发货(
                holder.tvstatus.setText("待发货");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));

                holder.tv1.setVisibility(View.INVISIBLE);
                holder.tv2.setVisibility(View.INVISIBLE);
                holder.tv3.setVisibility(View.INVISIBLE);

                break;

            case GoodsStatus.WAIT_RECEIVE://WAIT_RECEIVE = "2";//待收货
                holder.tvstatus.setText("待收货");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);

                holder.tv2.setText("确认收货");
                holder.tv3.setText("查看物流");


                break;

            case GoodsStatus.RECEIVE://RECEIVE = "3";//已确认收货
                holder.tvstatus.setText("已确认收货");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.tab_colorf9));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);

                holder.tv2.setText("评价");
                holder.tv3.setText("申请售后");

                break;

            case GoodsStatus.CANCLE://CANCLE = "4";//已取消
                holder.tvstatus.setText("已取消");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.txt_color_light9));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv3.setText("删除订单");

                break;

            case GoodsStatus.REBACKING://REBACKING = "5";//退货中
                holder.tvstatus.setText("退货中");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));

                holder.tv1.setVisibility(View.INVISIBLE);
                holder.tv2.setVisibility(View.INVISIBLE);
                holder.tv3.setVisibility(View.INVISIBLE);

                break;

            case GoodsStatus.BACK_SUCCESS:// BACK_SUCCESS = "6";//已退款
                holder.tvstatus.setText("已退款");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.tab_colorf9));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.VISIBLE);

                holder.tv3.setText("删除订单");

                break;

            case GoodsStatus.REBACK_FAIL://REBACK_FAIL = "7";//退款不通过
                holder.tvstatus.setText("退款不通过");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv3.setText("删除订单");

                break;

            case GoodsStatus.DONE://DONE = "8";//已完成
                holder.tvstatus.setText("已完成");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.tab_colorf9));

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv1.setText("删除订单");
                holder.tv2.setText("申请售后");
                holder.tv3.setText("1".equals(orderList.get(position).getIscomment()) ? "已评价" : "评价");

                break;

            case GoodsStatus.WAIT_USE://WAIT_USE = "10";//待使用
                holder.tvstatus.setText("待使用");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));
                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv3.setText("使用");

                break;

            case GoodsStatus.EXCHANGEING:// EXCHANGEING = "11";//换货中
                holder.tvstatus.setText("换货中");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));
                holder.tv1.setVisibility(View.INVISIBLE);
                holder.tv2.setVisibility(View.INVISIBLE);
                holder.tv3.setVisibility(View.INVISIBLE);

                break;

            case GoodsStatus.ALREADY_EXCHANGING://ALREADY_EXCHANGING = "12";//已换货
                holder.tvstatus.setText("已换货");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.tab_colorf9));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv3.setText("删除订单");

                break;

            case GoodsStatus.EXCHANGE_RESUFED://EXCHANGE_RESUFED = "13";//换货不通过
                holder.tvstatus.setText("换货不通过");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_36));

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv3.setText("删除订单");

                break;

        }

        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + orderList.get(position).getSkuimg()));
        holder.tvTitle.setText(orderList.get(position).getSkuname());
        holder.tvPP.setText(orderList.get(position).getPoints() + "积分+" + orderList.get(position).getSkuprice() + "元");
        holder.tvdesc.setText("共1件商品  ¥" + orderList.get(position).getPrice());

        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (orderList.get(holder.getAdapterPosition()).getStatus()) {

                    case GoodsStatus.DONE://DONE = "8";//已完成
                        //删除订单
                        deleteOrder(holder);

                        break;

                }

            }
        });

        holder.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (orderList.get(holder.getAdapterPosition()).getStatus()) {

                    case GoodsStatus.WAIT_PAY://WAIT_PAY = "0";//待付款
                        //取消订单
                        cancleOrder(holder);

                        break;
                    case GoodsStatus.WAIT_RECEIVE://WAIT_RECEIVE = "2";//待收货

                        //确认收货
                        if (context instanceof GoodsOrderListActivity) {
                            GoodsOrderListActivity g = (GoodsOrderListActivity) context;
                            g.confirmGetGoods(orderList.get(holder.getAdapterPosition()).getOrderid());
                        }

                        break;
                    case GoodsStatus.RECEIVE://RECEIVE = "3";//已确认收货
                        //评价
                        recommand(holder);

                        break;

                    case GoodsStatus.DONE://DONE = "8";//已完成
                        //申请售后
                        refund(holder);

                        break;
                }
            }
        });


        holder.tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (orderList.get(holder.getAdapterPosition()).getStatus()) {

                    case GoodsStatus.WAIT_PAY://WAIT_PAY = "0";//待付款
                        //去支付
                        go2pay(holder);
                        break;

                    case GoodsStatus.WAIT_RECEIVE://WAIT_RECEIVE = "2";//待收货
                        //查看物流
                        lookLogistic(holder);

                        break;
                    case GoodsStatus.RECEIVE://RECEIVE = "3";//已确认收货
                        //申请售后

                        refund(holder);

                        break;
                    case GoodsStatus.CANCLE://CANCLE = "4";//已取消
                    case GoodsStatus.BACK_SUCCESS:// BACK_SUCCESS = "6";//已退款
                    case GoodsStatus.REBACK_FAIL://REBACK_FAIL = "7";//退款不通过
                    case GoodsStatus.ALREADY_EXCHANGING://ALREADY_EXCHANGING = "12";//已换货
                    case GoodsStatus.EXCHANGE_RESUFED://EXCHANGE_RESUFED = "13";//换货不通过

                        //删除订单
                        deleteOrder(holder);
                        break;

                    case GoodsStatus.DONE://DONE = "8";//已完成
                        recommand(holder);
                        break;

                    case GoodsStatus.WAIT_USE://WAIT_USE = "10";//待使用

                        //使用订单
                        userOrder(holder);

                        break;
                }
            }
        });

        holder.tvright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent m = new Intent(context, OrderDetailActivity.class);
                m.putExtra("orderId", orderList.get(holder.getAdapterPosition()).getOrderid());
                context.startActivity(m);

            }
        });

    }

    //使用订单
    private void userOrder(MyViewHolder holder) {

        if (context instanceof GoodsOrderListActivity) {
            GoodsOrderListActivity g = (GoodsOrderListActivity) context;
            g.go2use(orderList.get(holder.getAdapterPosition()).getOrderid());
        }

        if (context instanceof ServiceOrderListActivity) {
            ServiceOrderListActivity g = (ServiceOrderListActivity) context;
            g.go2use(orderList.get(holder.getAdapterPosition()).getOrderid());
        }

    }

    //评价
    private void recommand(MyViewHolder holder) {

        GoodsOrderInfoEntry.DataBean goodsInfo = new GoodsOrderInfoEntry.DataBean();
        goodsInfo.setIscomment(orderList.get(holder.getAdapterPosition()).getIscomment());
        goodsInfo.setCode(orderList.get(holder.getAdapterPosition()).getCode());
        goodsInfo.setOrdertype(orderList.get(holder.getAdapterPosition()).getOrdertype());
        goodsInfo.setSkuimg(orderList.get(holder.getAdapterPosition()).getSkuimg());
        goodsInfo.setSkuname(orderList.get(holder.getAdapterPosition()).getSkuname());
        goodsInfo.setPoints(orderList.get(holder.getAdapterPosition()).getPoints());
        goodsInfo.setSkuprice(orderList.get(holder.getAdapterPosition()).getSkuprice());
        goodsInfo.setOrderid(orderList.get(holder.getAdapterPosition()).getOrderid());

        if (context instanceof GoodsOrderListActivity) {
            GoodsOrderListActivity g = (GoodsOrderListActivity) context;
            g.command(goodsInfo);
        }

        if (context instanceof ServiceOrderListActivity) {
            ServiceOrderListActivity g = (ServiceOrderListActivity) context;
            g.command(goodsInfo);
        }

    }

    private void refund(MyViewHolder holder) {
        Intent m = new Intent(context, RefundActivity.class);
        MallOrderEntry.DataBean orderInfo = new MallOrderEntry.DataBean();
        orderInfo.setSkuimg(orderList.get(holder.getAdapterPosition()).getSkuimg());
        orderInfo.setSkuname(orderList.get(holder.getAdapterPosition()).getSkuname());
        orderInfo.setPoints(orderList.get(holder.getAdapterPosition()).getPoints());
        orderInfo.setPrice(orderList.get(holder.getAdapterPosition()).getPrice());
        orderInfo.setOrderid(orderList.get(holder.getAdapterPosition()).getOrderid());
        orderInfo.setOrdertype(orderList.get(holder.getAdapterPosition()).getOrdertype());
        m.putExtra("orderInfo", orderInfo);
        context.startActivity(m);
    }

    private void deleteOrder(MyViewHolder holder) {

        if (context instanceof GoodsOrderListActivity) {
            GoodsOrderListActivity g = (GoodsOrderListActivity) context;
            g.deleteOrder(orderList.get(holder.getAdapterPosition()).getOrderid());
        }

        if (context instanceof ServiceOrderListActivity) {
            ServiceOrderListActivity g = (ServiceOrderListActivity) context;
            g.deleteOrder(orderList.get(holder.getAdapterPosition()).getOrderid());
        }
    }

    private void lookLogistic(MyViewHolder holder) {
        Intent m = new Intent(context, LogisticActivity.class);
        m.putExtra("orderId", orderList.get(holder.getAdapterPosition()).getOrderid());
        context.startActivity(m);
    }

    //取消订单
    private void cancleOrder(MyViewHolder holder) {

        if (context instanceof GoodsOrderListActivity) {
            GoodsOrderListActivity goodsOrderListActivity = (GoodsOrderListActivity) context;
            goodsOrderListActivity.cancleAction(orderList.get(holder.getAdapterPosition()).getOrderid()
                    , orderList.get(holder.getAdapterPosition()).getStatus());
        }

        if (context instanceof ServiceOrderListActivity) {
            ServiceOrderListActivity goodsOrderListActivity = (ServiceOrderListActivity) context;
            goodsOrderListActivity.cancleAction(orderList.get(holder.getAdapterPosition()).getOrderid()
                    , orderList.get(holder.getAdapterPosition()).getStatus());
        }

    }

    //去支付
    private void go2pay(MyViewHolder holder) {
        GoodsInfoEntry.DataBean goodsInfo = new GoodsInfoEntry.DataBean();
        goodsInfo.setType(orderList.get(holder.getAdapterPosition()).getOrdertype());
        goodsInfo.setPrice(orderList.get(holder.getAdapterPosition()).getPrice());

        mIntent = new Intent(context, GoodsPayActivity.class);
        mIntent.putExtra("goodsInfo", goodsInfo);
        mIntent.putExtra("orderCode", orderList.get(holder.getAdapterPosition()).getCode());
        mIntent.putExtra("freightPrice", 0.00d);//运费
        context.startActivity(mIntent);
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
        TextView tv1;//
        TextView tv2;//
        TextView tv3;//

        MyViewHolder(View view) {
            super(view);
            tvordernum = view.findViewById(R.id.tvordernum);
            tvstatus = view.findViewById(R.id.tvstatus);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvPP = view.findViewById(R.id.tvPP);
            tvdesc = view.findViewById(R.id.tvdesc);
            tvright = view.findViewById(R.id.tvright);
            tv1 = view.findViewById(R.id.tv1);
            tv2 = view.findViewById(R.id.tv2);
            tv3 = view.findViewById(R.id.tv3);
        }
    }
}