package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.ProvinceEntry;

import java.util.List;

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.MyViewHolder> {

    private Context context;
    private List<ProvinceEntry.DataBean> provinceData;

    public ProvinceAdapter(Context context, List<ProvinceEntry.DataBean> provinceData) {
        this.context = context;
        this.provinceData = provinceData;
    }

    @Override
    public ProvinceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ProvinceAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_string, parent, false));
    }

    @Override
    public void onBindViewHolder(ProvinceAdapter.MyViewHolder holder, int position) {
        holder.tvContent.setText(provinceData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return provinceData == null ? 0 : provinceData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        MyViewHolder(View view) {
            super(view);
            tvContent = view.findViewById(R.id.tvContent);
        }
    }
}