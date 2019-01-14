package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.activity.PicDetailAc;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

/**
 * 图片展示adapter
 * Created by linaidao on 2019/1/9.
 */

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.MyViewHolder> {

    private Context context;
    private List<String> images;

    public PicAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public PicAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PicAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_img, parent, false));
    }

    @Override
    public void onBindViewHolder(PicAdapter.MyViewHolder holder, int position) {

        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + images.get(position)));
        holder.ivDelete.setVisibility(View.GONE);
        holder.sdvImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(context, PicDetailAc.class);
                m.putExtra("picUrl", ApiService.WEB_ROOT + images.get(holder.getAdapterPosition()));
                context.startActivity(m);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        ImageView ivDelete;

        MyViewHolder(View view) {
            super(view);

            sdvImg = (SimpleDraweeView) view.findViewById(R.id.sdvImg);
            ivDelete = view.findViewById(R.id.ivDelete);


        }
    }
}