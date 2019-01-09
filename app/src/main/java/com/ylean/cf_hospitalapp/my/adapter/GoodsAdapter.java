package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.AreaEntry;
import com.ylean.cf_hospitalapp.my.bean.GoodsListEntry;
import com.ylean.cf_hospitalapp.net.ApiService;

import java.util.List;

/**
 * Created by linaidao on 2019/1/3.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyViewHolder> {

    private Context context;

    private List<GoodsListEntry.DataBean> goodsList;

    public GoodsAdapter(Context context, List<GoodsListEntry.DataBean> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
    }

    @Override
    public GoodsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_goods, parent, false));
    }

    @Override
    public void onBindViewHolder(GoodsAdapter.MyViewHolder holder, int position) {

        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + goodsList.get(position).getImg()));
        holder.tvTitle.setText(goodsList.get(position).getTitle());

        if (goodsList.get(position).getPrice() == 0) {

            holder.tvMoney.setText(goodsList.get(position).getPoints() + "积分");

        } else {

            holder.tvMoney.setText(goodsList.get(position).getPoints() + "积分" +
                    "+" + goodsList.get(position).getPrice() + "元");

        }

    }

    @Override
    public int getItemCount() {
        return goodsList == null ? 0 : goodsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvTitle;
        TextView tvMoney;

        MyViewHolder(View view) {
            super(view);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvMoney = view.findViewById(R.id.tvMoney);
        }
    }
}