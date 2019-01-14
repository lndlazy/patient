package com.ylean.cf_hospitalapp.doctor.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.home.activity.VideoSpeechActivity;
import com.ylean.cf_hospitalapp.hospital.activity.HospitalDetailActivity;
import com.ylean.cf_hospitalapp.inquiry.activity.PicDetailAc;
import com.ylean.cf_hospitalapp.inquiry.adapter.PicAdapter;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.List;


/**
 * 视频评论， 医院评论
 */
public class CommentCommAdapter extends RecyclerView.Adapter<CommentCommAdapter.MyViewHolder> {

    private Context context;
    private List<CommComListEntry.DataBean> commitList;

    public CommentCommAdapter(Context context, List<CommComListEntry.DataBean> commitList) {
        this.commitList = commitList;
        this.context = context;
    }

    @Override
    public CommentCommAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CommentCommAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_commit, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentCommAdapter.MyViewHolder holder, int i) {

        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + commitList.get(i).getPlimg()));
        holder.tvName.setText(commitList.get(i).getEvaluatename());
        holder.tvAttention.setText("1".equals(commitList.get(i).getIsfollow()) ? "已关注" : "未关注");
        holder.tvtime.setText(commitList.get(i).getTimedesc());
        holder.tvcontent.setText(commitList.get(i).getContent());
        //是否点赞
        holder.ivGood.setSelected("1".equals(commitList.get(i).getIsdz()));

        //先移除所有的views
        holder.llCommitReply.removeAllViews();
        if (commitList.get(i) != null && commitList.get(i).getReply() != null
                && commitList.get(i).getReply().size() > 0) {
            holder.llCommitReply.setVisibility(View.VISIBLE);

            for (int j = 0; j < commitList.get(holder.getAdapterPosition()).getReply().size(); j++) {

                CommComListEntry.DataBean.ReplyBean replyBean = commitList.get(holder.getAdapterPosition()).getReply().get(j);

                View view = View.inflate(context, R.layout.item_commit_reply, null);
                TextView tvreplyname = view.findViewById(R.id.tvreplyname);
                TextView tvreplycontent = view.findViewById(R.id.tvreplycontent);
                tvreplyname.setText(replyBean.getReplyname() + "：");
                tvreplycontent.setText(replyBean.getReplycontent());
                holder.llCommitReply.addView(view);

            }

        } else {
            holder.llCommitReply.setVisibility(View.GONE);
        }

        //关注
        holder.tvAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //关注
                if (context instanceof VideoSpeechActivity) {
                    VideoSpeechActivity videoSpeechActivity = (VideoSpeechActivity) context;
                    videoSpeechActivity.attentionAction(commitList.get(holder.getAdapterPosition()));
                }

                if (context instanceof HospitalDetailActivity) {
                    HospitalDetailActivity hospitalDetailActivity = (HospitalDetailActivity) context;
                    hospitalDetailActivity.attentionAction(commitList.get(holder.getAdapterPosition()));
                }

            }
        });

        //点赞
        holder.ivGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //点赞
                if (context instanceof VideoSpeechActivity) {
                    VideoSpeechActivity videoSpeechActivity = (VideoSpeechActivity) context;
                    videoSpeechActivity.goodAction(commitList.get(holder.getAdapterPosition()));
                }

                if (context instanceof HospitalDetailActivity) {
                    HospitalDetailActivity hospitalDetailActivity = (HospitalDetailActivity) context;
                    hospitalDetailActivity.goodAction(commitList.get(holder.getAdapterPosition()));
                }

            }
        });

        //回复
        holder.ivReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //回复 TODO
                if (context instanceof VideoSpeechActivity) {
                    VideoSpeechActivity videoSpeechActivity = (VideoSpeechActivity) context;
                    videoSpeechActivity.replyAction(commitList.get(holder.getAdapterPosition()));
                }

                if (context instanceof HospitalDetailActivity) {
                    HospitalDetailActivity hospitalDetailActivity = (HospitalDetailActivity) context;
                    hospitalDetailActivity.replyAction(commitList.get(holder.getAdapterPosition()));
                }
            }
        });

        if (commitList.get(holder.getAdapterPosition()) != null
                && commitList.get(holder.getAdapterPosition()).getImgs() != null
                && commitList.get(holder.getAdapterPosition()).getImgs().size() > 0) {

            holder.picRecyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.picRecyclerView.setLayoutManager(manager);
            holder.picRecyclerView.setItemAnimator(new DefaultItemAnimator());
            PicAdapter picAdapter = new PicAdapter(context, commitList.get(i).getImgs());
            holder.picRecyclerView.setAdapter(picAdapter);

            holder.picRecyclerView.addOnItemTouchListener(new OnItemClickListener(holder.picRecyclerView) {
                @Override
                public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                    Intent m = new Intent(context, PicDetailAc.class);
                    m.putExtra("picUrl", commitList.get(holder.getAdapterPosition()).getImgs().get(position));
                    context.startActivity(m);

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });
        } else {
            holder.picRecyclerView.setVisibility(View.GONE);
        }

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
        RecyclerView picRecyclerView;
        ImageView ivGood;
        ImageView ivReply;
        LinearLayout llCommitReply;

        MyViewHolder(View view) {
            super(view);

            sdvImg = view.findViewById(R.id.sdvImg);
            tvName = view.findViewById(R.id.name);
            tvAttention = view.findViewById(R.id.tvAttention);
            tvcontent = view.findViewById(R.id.tvcontent);
            tvtime = view.findViewById(R.id.tvtime);
            picRecyclerView = view.findViewById(R.id.picRecyclerView);
            ivGood = view.findViewById(R.id.ivGood);
            ivReply = view.findViewById(R.id.ivReply);
            llCommitReply = view.findViewById(R.id.llCommitReply);

        }
    }
}