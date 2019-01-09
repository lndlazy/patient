package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.MyAskReusltList;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

/**
 * Created by linaidao on 2018/12/21.
 */

public class AskAdapter extends RecyclerView.Adapter<AskAdapter.MyViewHolder> {

    private Context context;
    private List<MyAskReusltList.DataBean> myAskResultList;
    private String type;

    public AskAdapter(Context context, List<MyAskReusltList.DataBean> myAskResultList, String type) {
        this.context = context;
        this.myAskResultList = myAskResultList;
        this.type = type;
    }

    @Override
    public AskAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AskAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_ask_info, parent, false));
    }

    @Override
    public void onBindViewHolder(AskAdapter.MyViewHolder holder, int position) {


        switch (type) {

            case SpValue.ASK_TYPE_PIC:
            case SpValue.ASK_TYPE_FREE:
                holder.tvInfo.setVisibility(View.VISIBLE);

                holder.tvInfo.setText(myAskResultList.get(position).getDescription());
                holder.tvTime.setText(myAskResultList.get(position).getReplaytime());

                break;

            case SpValue.ASK_TYPE_TEL:
                holder.tvInfo.setVisibility(View.GONE);

                break;

            case SpValue.ASK_TYPE_VIDEO:
                holder.tvInfo.setVisibility(View.GONE);

                break;

        }


//        holder.tvContent.setText(areaList.get(position).getName());
        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + myAskResultList.get(position).getDoctorimgurl()));
        holder.tvName.setText(myAskResultList.get(position).getDoctorname());
        holder.tvJob.setText(myAskResultList.get(position).getDoctitlename());
        holder.tvCompany.setText(myAskResultList.get(position).getHospitalname());

        holder.tvRead.setVisibility(myAskResultList.get(position).getCount() == 0 ? View.INVISIBLE : View.VISIBLE);
        holder.tvRead.setText(myAskResultList.get(position).getCount() + "");

        switch (myAskResultList.get(position).getStatus()) {

            case SpValue.ASK_STATUS_WAIT_PAY:
                holder.tvStatus.setText("待付款");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_bg_blue_smale);
                break;

            case SpValue.ASK_STATUS_OVER_TIME:
                holder.tvStatus.setText("已过期");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_round_corner_talk_bg);
                break;

            case SpValue.ASK_STATUS_WAIT_SURE:
                holder.tvStatus.setText("待确认");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_bg_blue_smale);
                break;

            case SpValue.ASK_STATUS_CANCLED:
                holder.tvStatus.setText("已取消");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_round_corner_talk_bg);
                break;
            case SpValue.ASK_STATUS_REFUND:
                holder.tvStatus.setText("申请退款中");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_bg_blue_smale);
                break;
            case SpValue.ASK_STATUS_REFUND_OK:
                holder.tvStatus.setText("已退款");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_round_corner_talk_bg);
                break;

            case SpValue.ASK_STATUS_WAIT_COMPLY:
                holder.tvStatus.setText("待完成");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_bg_blue_smale);
                break;

            case SpValue.ASK_STATUS_REFUND_FAIL:
                holder.tvStatus.setText("退款不通过");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_round_corner_talk_bg);
                break;

            case SpValue.ASK_STATUS_COMPLY:
                holder.tvStatus.setText("已完成");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_round_corner_talk_bg);

                break;
            default:
                holder.tvStatus.setText("");
                holder.tvStatus.setBackgroundResource(R.drawable.shape_round_corner_talk_bg);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return myAskResultList == null ? 0 : myAskResultList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvRead;
        TextView tvName;
        TextView tvJob;
        TextView tvCompany;
        TextView tvInfo;
        TextView tvTime;
        TextView tvStatus;

        MyViewHolder(View view) {
            super(view);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvName = view.findViewById(R.id.tvName);
            tvRead = view.findViewById(R.id.tvRead);
            tvJob = view.findViewById(R.id.tvJob);
            tvCompany = view.findViewById(R.id.tvCompany);
            tvInfo = view.findViewById(R.id.tvInfo);
            tvTime = view.findViewById(R.id.tvTime);
            tvStatus = view.findViewById(R.id.tvStatus);
        }
    }
}