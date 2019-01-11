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
import com.ylean.cf_hospitalapp.doctor.bean.VideoListEntry;
import com.ylean.cf_hospitalapp.net.ApiService;

import java.util.List;

/**
 * 讲堂adapter
 * Created by linaidao on 2019/1/11.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.MyViewHolder> {

    private Context context;
    private List<VideoListEntry.DataBean> videoList;

    public VideoListAdapter(Context context, List<VideoListEntry.DataBean> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @Override
    public VideoListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoListAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_home_video, parent, false));
    }

    @Override
    public void onBindViewHolder(VideoListAdapter.MyViewHolder holder, int i) {

        //标题
        holder.tvTitle.setText(videoList.get(i).getTitle());
        //医生头像
        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + videoList.get(i).getDocimg()));
        //医生姓名
        holder.tvName.setText(videoList.get(i).getDoctorname() + "-" + videoList.get(i).getHospitalname()
                + "-" + videoList.get(i).getDoctitle());
        //发布时间
        holder.tvTime.setText(videoList.get(i).getTimedesc());
        //浏览量
        holder.tvReadCount.setText(videoList.get(i).getBrowsecount() + "");
        //点赞数
        holder.tvGoodCount.setText(videoList.get(i).getFabulouscount() + "");
        //文章图片
        holder.sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + videoList.get(i).getImgurl()));
        //是否热门
        holder.tvHot.setVisibility("1".equals(videoList.get(i).getIshot()) ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return videoList == null ? 0 : videoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        SimpleDraweeView sdvPic;
        TextView tvTitle;
        TextView tvName;
        TextView tvContent;
        TextView tvTime;
        TextView tvReadCount;
        TextView tvGoodCount;
        TextView tvHot;

        MyViewHolder(View view) {
            super(view);
            sdvImg = (SimpleDraweeView) view.findViewById(R.id.sdvImg);
            sdvPic = (SimpleDraweeView) view.findViewById(R.id.sdvPic);

            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvTime = (TextView) view.findViewById(R.id.tvTime);
            tvReadCount = (TextView) view.findViewById(R.id.tvReadCount);
            tvGoodCount = (TextView) view.findViewById(R.id.tvGoodCount);
            tvHot = (TextView) view.findViewById(R.id.tvHot);
        }
    }
}