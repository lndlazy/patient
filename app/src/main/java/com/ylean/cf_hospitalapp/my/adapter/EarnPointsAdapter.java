package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.AreaEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/3.
 */

public class EarnPointsAdapter extends RecyclerView.Adapter<EarnPointsAdapter.MyViewHolder> {

    private Context context;
    private List<AreaEntry.DataBean> areaList;

    public EarnPointsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public EarnPointsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EarnPointsAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_earn_points, parent, false));
    }

    @Override
    public void onBindViewHolder(EarnPointsAdapter.MyViewHolder holder, int position) {

//        holder.tvContent.setText(areaList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return areaList == null ? 0 : areaList.size();
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