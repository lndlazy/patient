package com.ylean.cf_hospitalapp.hospital.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.IitemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linaidao on 2019/1/13.
 */

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.MyViewHolder> {

    private Context context;
    private List<IitemBean> itemBeanList = new ArrayList<>();

    public NearbyAdapter(Context ctx) {
        this.context = ctx;
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.free_transfer), R.mipmap.ic_free_transfer));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.route), R.mipmap.ic_route));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.navi), R.mipmap.ic_navi));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.park), R.mipmap.ic_park));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.hotel), R.mipmap.ic_hotel));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.attractions), R.mipmap.ic_attractions));
    }

    @Override
    public NearbyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NearbyAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_grideview_my, parent, false));
    }

    @Override
    public void onBindViewHolder(NearbyAdapter.MyViewHolder holder, int position) {
        holder.iv_head_pic.setImageResource(itemBeanList.get(position).getResId());
        holder.tv_nickname.setText(itemBeanList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return itemBeanList == null ? 0 : itemBeanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_head_pic;
        private TextView tv_nickname;

        MyViewHolder(View view) {
            super(view);
            iv_head_pic = view.findViewById(R.id.iv_head_pic);
            tv_nickname = view.findViewById(R.id.tv_nickname);
        }
    }
}