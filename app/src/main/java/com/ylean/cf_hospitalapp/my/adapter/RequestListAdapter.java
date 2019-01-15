package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.MyReuqestListEntry;

import java.util.List;

/**
 * 我的申请列表adapter
 * Created by linaidao on 2019/1/14.
 */

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.MyViewHolder> {

    private Context context;
    private List<MyReuqestListEntry.DataBean> requestList;

    public RequestListAdapter(Context context, List<MyReuqestListEntry.DataBean> requestList) {
        this.context = context;
        this.requestList = requestList;
    }

    @Override
    public RequestListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RequestListAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_my_request, parent, false));
    }

    @Override
    public void onBindViewHolder(RequestListAdapter.MyViewHolder holder, int position) {

        holder.tvname.setText("患者：" + requestList.get(position).getPatientname());
        holder.tvnum.setText("预约号：" + requestList.get(position).getCode());
        holder.tvtime.setText(requestList.get(position).getApplytime());
        holder.tvhospital.setText("医院：" + requestList.get(position).getHospitalname());
        holder.tvtype.setText("服务项目：" + ("1".equals(requestList.get(position).getType()) ? "免费筛查" : "免费接送"));

        //0-未审核  1-未使用 2-已使用  3-审核不通过  4-已过期
        switch (requestList.get(position).getStatus()) {

            case "0":
                holder.tvstatus.setText("未审核");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_28));
                break;

            case "1":
                holder.tvstatus.setText("待使用");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.tab_colorf9));
                break;

            case "2":
                holder.tvstatus.setText("已使用");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.color_70));
                break;

            case "3":
                holder.tvstatus.setText("审核不通过");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.red));
                break;

            case "4":
                holder.tvstatus.setText("已过期");
                holder.tvstatus.setTextColor(context.getResources().getColor(R.color.txt_color_light9));
                break;

        }

    }

    @Override
    public int getItemCount() {
        return requestList == null ? 0 : requestList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvname;
        TextView tvstatus;
        TextView tvnum;
        TextView tvtime;
        TextView tvtype;
        TextView tvhospital;

        MyViewHolder(View view) {
            super(view);
            tvname = view.findViewById(R.id.tvname);
            tvstatus = view.findViewById(R.id.tvstatus);
            tvnum = view.findViewById(R.id.tvnum);
            tvtime = view.findViewById(R.id.tvtime);
            tvtype = view.findViewById(R.id.tvtype);
            tvhospital = view.findViewById(R.id.tvhospital);

        }
    }
}