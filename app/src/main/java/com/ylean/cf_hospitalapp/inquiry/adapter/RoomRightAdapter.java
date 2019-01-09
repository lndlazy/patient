package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.bean.DepartmentListEntry;

import java.util.List;

public class RoomRightAdapter extends RecyclerView.Adapter<RoomRightAdapter.MyViewHolder> {

    private Context context;
    private List<DepartmentListEntry.DataBean.ChildlistBean> roomRightList;

    public RoomRightAdapter(Context context, List<DepartmentListEntry.DataBean.ChildlistBean> roomRightList) {
        this.context = context;
        this.roomRightList = roomRightList;
    }

    @Override
    public RoomRightAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RoomRightAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.dialog_sort, parent, false));
    }

    @Override
    public void onBindViewHolder(RoomRightAdapter.MyViewHolder holder, int position) {

        holder.tvCount.setText(roomRightList.get(position).getDepartmentname());
        holder.tvCount.setTextColor(roomRightList.get(position).isSelect() ?
                context.getResources().getColor(R.color.tab_colorf9) :
                context.getResources().getColor(R.color.txt_color_light6));

    }

    @Override
    public int getItemCount() {
        return roomRightList == null ? 0 : roomRightList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCount;
        ImageView ivCount;
        RelativeLayout rlCount;

        MyViewHolder(View view) {
            super(view);
            tvCount = view.findViewById(R.id.tvCount);
            rlCount = view.findViewById(R.id.rlCount);
            ivCount = view.findViewById(R.id.ivCount);

        }
    }
}