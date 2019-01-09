package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;

public class PicGridAdapter extends RecyclerView.Adapter<PicGridAdapter.MyViewHolder> {

    private Context context;

    public PicGridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PicGridAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PicGridAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_pic, parent, false));
    }

    @Override
    public void onBindViewHolder(PicGridAdapter.MyViewHolder holder, int position) {

        holder.ivDelete.setVisibility(View.GONE);
        holder.sdvPic.setImageURI("res://com.ylean.cf_hospitalapp/" + R.mipmap.ic_upload_pic);

    }

    @Override
    public int getItemCount() {
//        if (msgs == null)
//            return 0;
//        return msgs.size();
        return 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvPic;
        ImageView ivDelete;


        MyViewHolder(View view) {
            super(view);

            sdvPic = view.findViewById(R.id.sdvPic);
            ivDelete = view.findViewById(R.id.ivDelete);

        }
    }
}