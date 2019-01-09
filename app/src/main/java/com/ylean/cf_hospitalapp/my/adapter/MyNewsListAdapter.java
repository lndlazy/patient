package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.AreaEntry;
import com.ylean.cf_hospitalapp.my.bean.NewsListEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/10.
 */

public class MyNewsListAdapter extends RecyclerView.Adapter<MyNewsListAdapter.MyViewHolder> {

    private Context context;
    private List<NewsListEntry.DataBean> newsList;

    public MyNewsListAdapter(Context context, List<NewsListEntry.DataBean> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public MyNewsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyNewsListAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(MyNewsListAdapter.MyViewHolder holder, int position) {

//        holder.tvContent.setText(areaList.get(position).getName());

        holder.tvtitle.setText(newsList.get(position).getTitle());
        holder.tvtime.setText(newsList.get(position).getSendtime());
        holder.tvcontent.setText(newsList.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvtitle;
        TextView tvtime;
        TextView tvcontent;

        MyViewHolder(View view) {
            super(view);
            tvtitle = view.findViewById(R.id.tvtitle);
            tvtime = view.findViewById(R.id.tvtime);
            tvcontent = view.findViewById(R.id.tvcontent);
        }
    }
}