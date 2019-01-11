package com.ylean.cf_hospitalapp.doctor.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.doctor.bean.InquiryListEntry;
import com.ylean.cf_hospitalapp.my.adapter.AreaAdapter;
import com.ylean.cf_hospitalapp.my.bean.AreaEntry;
import com.ylean.cf_hospitalapp.net.ApiService;

import java.util.List;

/**
 * Created by linaidao on 2019/1/11.
 */

public class InquiryItemAdapter extends RecyclerView.Adapter<InquiryItemAdapter.MyViewHolder> {

    private Context context;
    private List<InquiryListEntry.DataBean> inquiryList;

    public InquiryItemAdapter(Context context, List<InquiryListEntry.DataBean> inquiryList) {
        this.context = context;
        this.inquiryList = inquiryList;
    }

    @Override
    public InquiryItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InquiryItemAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_home_ask, parent, false));
    }

    @Override
    public void onBindViewHolder(InquiryItemAdapter.MyViewHolder holder, int i) {

        //医生头像
        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + inquiryList.get(i).getUserimg()));
        //医生姓名
        holder.tvName.setText(inquiryList.get(i).getUsername());
        //发布时间
        holder.tvTime.setText(inquiryList.get(i).getCreatetime());
        //提问内容
        holder.tvAsk.setText(inquiryList.get(i).getProblem());
        //回答内容
        holder.tvAnswer.setText(inquiryList.get(i).getAnswer());
    }

    @Override
    public int getItemCount() {
        return inquiryList == null ? 0 : inquiryList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvName;
        TextView tvContent;
        TextView tvTime;
        TextView tvAsk;
        TextView tvAnswer;

        MyViewHolder(View view) {
            super(view);
            sdvImg = (SimpleDraweeView) view.findViewById(R.id.sdvImg);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvTime = (TextView) view.findViewById(R.id.tvTime);
            tvAsk = (TextView) view.findViewById(R.id.tvAsk);
            tvAnswer = (TextView) view.findViewById(R.id.tvAnswer);
        }
    }
}