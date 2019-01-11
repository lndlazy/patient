package com.ylean.cf_hospitalapp.doctor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.doctor.bean.CommitListEntry;

import java.util.List;

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.MyViewHolder> {

    private Context context;
    private List<CommitListEntry.DataBean> commitList;

    public CommitAdapter(Context context, List<CommitListEntry.DataBean> commitList) {
        this.commitList = commitList;
        this.context = context;
    }

    @Override
    public CommitAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CommitAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_commit, parent, false));
    }

    @Override
    public void onBindViewHolder(CommitAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return commitList == null ? 0 : commitList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvName;
        TextView tvAttention;
        TextView tvcontent;
        TextView tvtime;



        MyViewHolder(View view) {
            super(view);

            sdvImg =   view.findViewById(R.id.sdvImg);
            tvName =  view.findViewById(R.id.tvName);
            tvAttention =  view.findViewById(R.id.tvAttention);
            tvcontent =  view.findViewById(R.id.tvcontent);
            tvtime =  view.findViewById(R.id.tvtime);

        }
    }
}