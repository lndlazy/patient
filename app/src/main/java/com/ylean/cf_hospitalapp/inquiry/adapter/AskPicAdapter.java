package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.activity.InquiryEvaulateActivity;
import com.ylean.cf_hospitalapp.inquiry.activity.InquiryRefundActivity;
import com.ylean.cf_hospitalapp.inquiry.bean.MImageItem;
import com.ylean.cf_hospitalapp.inquiry.activity.PayTWActivity;
import com.ylean.cf_hospitalapp.mall.acitity.GoodsCommandActivity;
import com.ylean.cf_hospitalapp.mall.acitity.RefundActivity;
import com.ylean.cf_hospitalapp.my.activity.InquiryEvaulateDoctorActivity;
import com.ylean.cf_hospitalapp.register.activity.PayBackActivity;
import com.ylean.cf_hospitalapp.register.activity.RegisterConfirmActivity;
import com.ylean.cf_hospitalapp.register.activity.RegisterEvaulateActivity;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

/**
 * Created by linaidao on 2018/12/18.
 */

public class AskPicAdapter extends RecyclerView.Adapter<AskPicAdapter.MyViewHolder> {

    private Context context;
    private List<MImageItem> images;

    public AskPicAdapter(Context context, List<MImageItem> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public AskPicAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new AskPicAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_img, parent, false));
    }

    @Override
    public void onBindViewHolder(AskPicAdapter.MyViewHolder holder, final int position) {

        holder.sdvImg.setImageURI(Uri.parse(SpValue.FRESCO_LOCAL + images.get(position).path));

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof PayTWActivity) {
                    PayTWActivity payTWActivity = (PayTWActivity) context;
                    payTWActivity.removePic(position);
                }

                if (context instanceof RegisterConfirmActivity) {
                    RegisterConfirmActivity confirmActivity = (RegisterConfirmActivity) context;
                    confirmActivity.removePic(position);
                }

                if (context instanceof PayBackActivity) {
                    PayBackActivity payBackActivity = (PayBackActivity) context;
                    payBackActivity.removePic(position);
                }

                if (context instanceof InquiryEvaulateDoctorActivity) {
                    InquiryEvaulateDoctorActivity evaluateActivity = (InquiryEvaulateDoctorActivity) context;
                    evaluateActivity.removePic(position);
                }

                if (context instanceof RefundActivity) {
                    RefundActivity refundActivity = (RefundActivity) context;
                    refundActivity.removePic(position);
                }

                if (context instanceof GoodsCommandActivity) {
                    GoodsCommandActivity gcActivity = (GoodsCommandActivity) context;
                    gcActivity.removePic(position);
                }

                if (context instanceof RegisterEvaulateActivity) {
                    RegisterEvaulateActivity rActivity = (RegisterEvaulateActivity) context;
                    rActivity.removePic(position);
                }

                if (context instanceof InquiryRefundActivity) {
                    InquiryRefundActivity iActivity = (InquiryRefundActivity) context;
                    iActivity.removePic(position);
                }

                if (context instanceof InquiryEvaulateActivity) {
                    InquiryEvaulateActivity iActivity = (InquiryEvaulateActivity) context;
                    iActivity.removePic(position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        ImageView ivDelete;

        MyViewHolder(View view) {
            super(view);

            sdvImg = (SimpleDraweeView) view.findViewById(R.id.sdvImg);
            ivDelete = view.findViewById(R.id.ivDelete);

        }
    }
}