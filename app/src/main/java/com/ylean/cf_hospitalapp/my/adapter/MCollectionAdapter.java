package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.MCollectionListEntry;
import com.ylean.cf_hospitalapp.net.ApiService;

import java.util.List;

/**
 * Created by linaidao on 2019/1/9.
 */

public class MCollectionAdapter extends RecyclerView.Adapter<MCollectionAdapter.MyViewHolder> {

    private Context context;
    private List<MCollectionListEntry.DataBean> collectionList;

    public MCollectionAdapter(Context context, List<MCollectionListEntry.DataBean> collectionList) {
        this.context = context;
        this.collectionList = collectionList;
    }

    @Override
    public MCollectionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MCollectionAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_home_article, parent, false));
    }

    @Override
    public void onBindViewHolder(MCollectionAdapter.MyViewHolder holder, int i) {

        holder.sdvImg.setVisibility(TextUtils.isEmpty(collectionList.get(i).getDocimg()) ?
                View.GONE : View.VISIBLE);
        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + collectionList.get(i).getDocimg()));
        holder.sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + collectionList.get(i).getImgurl()));

        holder.tvTitle.setText(collectionList.get(i).getTitle());

//        holder.tvContent.setText(collectionList.get(i).get);
        holder.tvName.setText(collectionList.get(i).getDoctorname());
//        holder.tvTime.setText();
        holder.tvReadCount.setText(collectionList.get(i).getPageview());
        holder.tvGoodCount.setText(collectionList.get(i).getPraisenum());
//        holder.tvCommitCount.setText(collectionList.get(i).getc);

//        TextUtils.isEmpty(ivCommit)

    }

    @Override
    public int getItemCount() {
        return collectionList == null ? 0 : collectionList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvPic;
        SimpleDraweeView sdvImg;
        TextView tvTitle;
        TextView tvContent;
        TextView tvName;
        TextView tvCommitCount;
        TextView tvGoodCount;
        TextView tvReadCount;
        TextView tvTime;

        MyViewHolder(View view) {
            super(view);
            sdvPic = view.findViewById(R.id.sdvPic);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvContent = view.findViewById(R.id.tvContent);
            tvName = view.findViewById(R.id.tvName);
            tvCommitCount = view.findViewById(R.id.tvCommitCount);
            tvGoodCount = view.findViewById(R.id.tvGoodCount);
            tvReadCount = view.findViewById(R.id.tvReadCount);
            tvTime = view.findViewById(R.id.tvTime);
        }
    }
}