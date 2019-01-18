package com.ylean.cf_hospitalapp.hx;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.hx.bean.GroupListEntry;
import com.ylean.cf_hospitalapp.net.ApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * 群组adapter
 * Created by linaidao on 2019/1/15.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyViewHolder> {

    private Context context;
    private List<GroupListEntry.DataBean> groupList = new ArrayList<>();

    public GroupAdapter(Context context, List<GroupListEntry.DataBean> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    @Override
    public GroupAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GroupAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_group, parent, false));
    }

    @Override
    public void onBindViewHolder(GroupAdapter.MyViewHolder holder, int position) {

        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + groupList.get(position).getImgurl()));
        holder.tvname.setText(groupList.get(position).getNickname());
        switch (groupList.get(position).getType()) {
            case 1:
                holder.tvtype.setText("群主");
                break;
            case 2:
                holder.tvtype.setText("群成员");
                break;

        }
//        holder.tvContent.setText(areaList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return groupList == null ? 0 : groupList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvname;
        TextView tvtype;

        MyViewHolder(View view) {
            super(view);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvname = view.findViewById(R.id.tvname);
            tvtype = view.findViewById(R.id.tvtype);
        }
    }
}