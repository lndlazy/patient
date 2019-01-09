package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.CommentListEntry;
import com.ylean.cf_hospitalapp.my.bean.EvalListEntry;
import com.ylean.cf_hospitalapp.utils.CommonUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

/**
 * Created by linaidao on 2019/1/9.
 */

public class MyEvalAdapter extends RecyclerView.Adapter<MyEvalAdapter.MyViewHolder> {

    private Context context;
    private List<EvalListEntry.DataBean> commandList;

    public MyEvalAdapter(Context context, List<EvalListEntry.DataBean> commandList) {
        this.context = context;
        this.commandList = commandList;

    }

    @Override
    public MyEvalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyEvalAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(MyEvalAdapter.MyViewHolder holder, int position) {

        switch (commandList.get(position).getCommenttype()) {

            case SpValue.COMMIT_TYPE_GOODS://"【商品订单】"
                holder.tvTitle.setText("【商品订单】" + commandList.get(position).getSpskuname());
                break;

            case SpValue.COMMIT_TYPE_SERVICE://"【服务订单】"
                holder.tvTitle.setText("【服务订单】" + commandList.get(position).getFwskuname());
                break;

            case SpValue.COMMIT_TYPE_DOCTOR://"【医生】"
                holder.tvTitle.setText("【医生】" + commandList.get(position).getDoctorname());
                break;
        }

        holder.tvContent.setText(commandList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return commandList == null ? 0 : commandList.size();
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