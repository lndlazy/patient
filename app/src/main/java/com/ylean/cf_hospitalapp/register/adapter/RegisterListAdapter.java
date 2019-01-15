package com.ylean.cf_hospitalapp.register.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.register.PayStatus;
import com.ylean.cf_hospitalapp.register.bean.RegisterOrderEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/6.
 */

public class RegisterListAdapter extends RecyclerView.Adapter<RegisterListAdapter.MyViewHolder> {

    private Context context;
    private List<RegisterOrderEntry.DataBean> registerList;

    public RegisterListAdapter(Context context, List<RegisterOrderEntry.DataBean> registerList) {
        this.context = context;
        this.registerList = registerList;
    }

    @Override
    public RegisterListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RegisterListAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_register_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RegisterListAdapter.MyViewHolder holder, int position) {

        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + registerList.get(position).getImgurl()));
        holder.tvHospitalName.setText(registerList.get(position).getHospitalname());
        holder.tvDepartmentName.setText(registerList.get(position).getDepartname());

        switch (registerList.get(position).getAppointtype()) {

            case "0"://普通医生门诊
                holder.tvDoctorName.setText(registerList.get(position).getDoctorname() + "  "
                        + registerList.get(position).getDoctitle() + "  " + registerList.get(position).getDocdocteachname());
                break;

            case "1":
                holder.tvDoctorName.setText("普通门诊 " + registerList.get(position).getDoctorname() + "  "
                        + registerList.get(position).getDoctitle() + "  " + registerList.get(position).getDocdocteachname());
                break;

            case "2":
                holder.tvDoctorName.setText("专家门诊 " + registerList.get(position).getDoctorname() + "  "
                        + registerList.get(position).getDoctitle() + "  " + registerList.get(position).getDocdocteachname());
                break;

        }

        holder.tvDate.setText(registerList.get(position).getAppointtime());
        holder.tvPatient.setText(registerList.get(position).getFolkname());
        switch (registerList.get(position).getStatus()) {

            case PayStatus.STATUS_WAIT_PAY://待支付
                holder.tvStatus.setText("待支付");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_36));
                break;

            case PayStatus.STATUS_WAIT_USE://待使用
                holder.tvStatus.setText("待使用");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.tab_colorf9));

                break;

            case PayStatus.STATUS_USED://已使用
                holder.tvStatus.setText("已使用");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_70));

                break;

            case PayStatus.STATUS_REFUND://申请退款中
                holder.tvStatus.setText("退款申请");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_28));

                break;

            case PayStatus.STATUS_REFUNDED://已退款
                holder.tvStatus.setText("已退款");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.color_020));

                break;

            case PayStatus.STATUS_REFUND_REFUSED://退款不通过
                holder.tvStatus.setText("退款不通过");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.txt_color_light9));

                break;

            case PayStatus.STATUS_CANCLED://已取消
                holder.tvStatus.setText("已取消");
                holder.tvStatus.setTextColor(context.getResources().getColor(R.color.txt_color_light9));

                break;

        }

    }

    @Override
    public int getItemCount() {
        return registerList == null ? 0 : registerList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvHospitalName;
        TextView tvStatus;
        TextView tvDepartmentName;
        TextView tvDoctorName;
        TextView tvDate;
        TextView tvPatient;

        MyViewHolder(View view) {
            super(view);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvHospitalName = view.findViewById(R.id.tvHospitalName);
            tvStatus = view.findViewById(R.id.tvStatus);
            tvDepartmentName = view.findViewById(R.id.tvDepartmentName);
            tvDate = view.findViewById(R.id.tvDate);
            tvPatient = view.findViewById(R.id.tvPatient);
            tvDoctorName = view.findViewById(R.id.tvDoctorName);
        }
    }
}