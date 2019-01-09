package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.register.bean.RegisterDeartmentListEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/4.
 */

public class RegisterRightAdapter extends RecyclerView.Adapter<RegisterRightAdapter.MyViewHolder> {

    private Context context;
    private List<RegisterDeartmentListEntry.DataBean.MenzhenlistBean> menzhenlist;

    public RegisterRightAdapter(Context context, List<RegisterDeartmentListEntry.DataBean.MenzhenlistBean> menzhenlist) {
        this.context = context;
        this.menzhenlist = menzhenlist;
    }

    @Override
    public RegisterRightAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RegisterRightAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.dialog_sort, parent, false));
    }

    @Override
    public void onBindViewHolder(RegisterRightAdapter.MyViewHolder holder, int position) {

        holder.tvCount.setText(menzhenlist.get(position).getMenzhenname());
        holder.tvCount.setTextColor(menzhenlist.get(position).isSelect() ?
                context.getResources().getColor(R.color.tab_colorf9) :
                context.getResources().getColor(R.color.txt_color_light6));
    }

    @Override
    public int getItemCount() {
        return menzhenlist == null ? 0 : menzhenlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvCount;
        ImageView ivCount;
        RelativeLayout rlCount;

        MyViewHolder(View view) {
            super(view);
            tvCount = view.findViewById(R.id.tvCount);
            rlCount = view.findViewById(R.id.rlCount);
            ivCount = view.findViewById(R.id.ivCount);
        }
    }
}