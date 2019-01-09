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

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.MyViewHolder> {

    private Context context;
    private List<AreaEntry.DataBean> areaList;

    public AreaAdapter(Context context, List<AreaEntry.DataBean> areaList) {
        this.context = context;
        this.areaList = areaList;
    }

    @Override
    public AreaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AreaAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_string, parent, false));
    }

    @Override
    public void onBindViewHolder(AreaAdapter.MyViewHolder holder, int position) {

        holder.tvContent.setText(areaList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return areaList == null ? 0 : areaList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        MyViewHolder(View view) {
            super(view);
            tvContent = view.findViewById(R.id.tvContent);
        }
    }
}