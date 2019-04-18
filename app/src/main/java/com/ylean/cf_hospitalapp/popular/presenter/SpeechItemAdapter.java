package com.ylean.cf_hospitalapp.popular.presenter;

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
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.popular.bean.ExpertEntry;

import java.util.List;

public class SpeechItemAdapter extends RecyclerView.Adapter<SpeechItemAdapter.MyViewHolder> {

    private Context context;
    private List<ExpertEntry.DataBean> expertSpeechList;
    private LayoutInflater mLayoutInflater;

    public SpeechItemAdapter(Context context, List<ExpertEntry.DataBean> expertSpeechList) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.expertSpeechList = expertSpeechList;
    }

    @Override
    public SpeechItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        try {
            return new SpeechItemAdapter.MyViewHolder(
                    mLayoutInflater.inflate(R.layout.item_home_video, parent, false));
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }

    }

    @Override
    public void onBindViewHolder(SpeechItemAdapter.MyViewHolder holder, int position) {

        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + expertSpeechList.get(position).getDocimg()));
        holder.sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + expertSpeechList.get(position).getImgurl()));

        holder.tvTitle.setText(expertSpeechList.get(position).getTitle());

        holder.tvName.setText(expertSpeechList.get(position).getDoctorname() + "-"
                + expertSpeechList.get(position).getHospitalname() + "-"
                + expertSpeechList.get(position).getDoctitle());

        holder.tvName.setVisibility(TextUtils.isEmpty(expertSpeechList.get(position).getDoctorname()) ? View.INVISIBLE : View.VISIBLE);
        holder.tvTime.setText(expertSpeechList.get(position).getTimedesc());

        holder.tvReadCount.setText(expertSpeechList.get(position).getBrowsecount() + "");
        holder.tvGoodCount.setText(expertSpeechList.get(position).getFabulouscount() + "");

        holder.tvHot.setVisibility(
                expertSpeechList.get(position).getIshot() == 0 ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {

        return expertSpeechList == null ? 0 : expertSpeechList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        SimpleDraweeView sdvPic;
        TextView tvTitle;

        TextView tvTime;
        TextView tvReadCount;
        TextView tvGoodCount;
        TextView tvHot;
        TextView tvName;


        MyViewHolder(View view) {
            super(view);

            sdvImg = (SimpleDraweeView) view.findViewById(R.id.sdvImg);
            sdvPic = (SimpleDraweeView) view.findViewById(R.id.sdvPic);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);

            tvTime = (TextView) view.findViewById(R.id.tvTime);
            tvReadCount = (TextView) view.findViewById(R.id.tvReadCount);
            tvGoodCount = (TextView) view.findViewById(R.id.tvGoodCount);
            tvHot = (TextView) view.findViewById(R.id.tvHot);
            tvName = (TextView) view.findViewById(R.id.tvName);

        }
    }
}