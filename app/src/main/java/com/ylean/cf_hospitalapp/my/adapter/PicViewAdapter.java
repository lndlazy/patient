package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.net.ApiService;

import java.util.List;

/**
 * Created by linaidao on 2019/1/9.
 */

public class PicViewAdapter extends RecyclerView.Adapter<PicViewAdapter.MyViewHolder> {

    private Context context;
    private List<String> images;

    public PicViewAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public PicViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PicViewAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_img, parent, false));
    }

    @Override
    public void onBindViewHolder(PicViewAdapter.MyViewHolder holder, final int position) {

        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + images.get(position)));
        holder.ivDelete.setVisibility(View.GONE);
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

            sdvImg = view.findViewById(R.id.sdvImg);
            ivDelete = view.findViewById(R.id.ivDelete);

        }
    }
}