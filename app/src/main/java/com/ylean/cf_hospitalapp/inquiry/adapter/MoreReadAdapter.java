package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;

public class MoreReadAdapter extends RecyclerView.Adapter<MoreReadAdapter.MyViewHolder> {

    private Context context;

    public MoreReadAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MoreReadAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MoreReadAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_some_read, parent, false));
    }

    @Override
    public void onBindViewHolder(MoreReadAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
//        if (msgs == null)
//            return 0;
//        return msgs.size();
        return 3;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvTitle;
        TextView tvConent;


        MyViewHolder(View view) {
            super(view);

            sdvImg = (SimpleDraweeView) view.findViewById(R.id.sdvImg);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvConent = (TextView) view.findViewById(R.id.tvConent);

        }
    }
}