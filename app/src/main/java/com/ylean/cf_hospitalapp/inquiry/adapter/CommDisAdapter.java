package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.bean.CommentDisBean;

import java.util.List;

/**
 * 医生等级adapter
 */
public class CommDisAdapter extends RecyclerView.Adapter<CommDisAdapter.MyViewHolder> {

    private Context context;
    private List<CommentDisBean.DataBean> commDisList;

    public CommDisAdapter(Context context, List<CommentDisBean.DataBean> commDisList) {
        this.context = context;
        this.commDisList = commDisList;
    }

    @Override
    public CommDisAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CommDisAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_choose, parent, false));
    }

    @Override
    public void onBindViewHolder(CommDisAdapter.MyViewHolder holder, int position) {

        holder.tvContent.setText(commDisList.get(position).getDiseasename());
        holder.tvContent.setTextColor(commDisList.get(position).isSelect() ?
                context.getResources().getColor(R.color.tab_colorf9) :
                context.getResources().getColor(R.color.txt_color_light6));
    }

    @Override
    public int getItemCount() {
        return commDisList == null ? 0 : commDisList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        MyViewHolder(View view) {
            super(view);
            tvContent = view.findViewById(R.id.tvContent);
        }
    }
}