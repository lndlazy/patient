package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.CityEntry;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {

    private Context context;
    private  List<CityEntry.DataBean> cityEntryData;

    public CityAdapter(Context context,  List<CityEntry.DataBean> cityEntryData) {
        this.context = context;
        this.cityEntryData = cityEntryData;
    }

    @Override
    public CityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new CityAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_string, parent, false));
    }

    @Override
    public void onBindViewHolder(CityAdapter.MyViewHolder holder, int position) {

        holder.tvContent.setText(cityEntryData.get(position).getName());
    }

    @Override
    public int getItemCount() {

        return cityEntryData == null ? 0 : cityEntryData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        MyViewHolder(View view) {
            super(view);
            tvContent = view.findViewById(R.id.tvContent);
        }
    }
}