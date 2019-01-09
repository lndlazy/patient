package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.MyViewHolder> {

    private Context context;

    public CommitAdapter(Context context) {
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
//        if (msgs == null)
//            return 0;
//        return msgs.size();
        return 2;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvName;
        TextView tvTime;


        MyViewHolder(View view) {
            super(view);

            sdvImg = (SimpleDraweeView) view.findViewById(R.id.sdvImg);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvTime = (TextView) view.findViewById(R.id.tvTime);

        }
    }
}