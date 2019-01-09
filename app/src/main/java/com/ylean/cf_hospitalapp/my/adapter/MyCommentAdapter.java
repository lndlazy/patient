package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.CommentListEntry;
import com.ylean.cf_hospitalapp.utils.CommonUtils;

import java.util.List;

/**
 * Created by linaidao on 2019/1/7.
 */

public class MyCommentAdapter extends RecyclerView.Adapter<MyCommentAdapter.MyViewHolder> {

    private Context context;
    private List<CommentListEntry.DataBean> commentList;

    public MyCommentAdapter(Context context, List<CommentListEntry.DataBean> commentList) {
        this.context = context;
        this.commentList = commentList;

    }

    @Override
    public MyCommentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyCommentAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(MyCommentAdapter.MyViewHolder holder, int position) {

        holder.tvTitle.setText(CommonUtils.getCommentType(commentList.get(position).getCommenttype()));
        holder.tvContent.setText(commentList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return commentList == null ? 0 : commentList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvContent;

        MyViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvContent = view.findViewById(R.id.tvContent);
        }
    }
}