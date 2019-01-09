package com.ylean.cf_hospitalapp.mall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.mall.acitity.ChooseAddressActivity;
import com.ylean.cf_hospitalapp.mall.bean.AddressListEntry;
import com.ylean.cf_hospitalapp.my.adapter.AreaAdapter;
import com.ylean.cf_hospitalapp.my.bean.AreaEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/7.
 */

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.MyViewHolder> {

    private Context context;
    private List<AddressListEntry.DataBean> addressList;

    public AddressListAdapter(Context context, List<AddressListEntry.DataBean> data) {
        this.context = context;
        this.addressList = data;
    }

    @Override
    public AddressListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressListAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_address, parent, false));
    }

    @Override
    public void onBindViewHolder(AddressListAdapter.MyViewHolder holder, final int position) {

        holder.tvName.setText(addressList.get(position).getName());
        holder.tvTel.setText(addressList.get(position).getMobile());
        holder.tvAddressDetail.setText(addressList.get(position).getProvincename()
                + addressList.get(position).getCityname()
                + addressList.get(position).getAreaname()
                + addressList.get(position).getAddress());
        holder.ivDefault.setSelected("1".equals(addressList.get(position).getIsdefault()));

        holder.rlAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof ChooseAddressActivity) {
                    ChooseAddressActivity activity = (ChooseAddressActivity) context;
                    activity.chooseCurrentAddress(addressList.get(position));
                }

            }
        });

        holder.ivDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof ChooseAddressActivity) {
                    ChooseAddressActivity activity = (ChooseAddressActivity) context;
                    activity.setCurrentDefault(addressList.get(position));
                }

            }
        });

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof ChooseAddressActivity) {
                    ChooseAddressActivity activity = (ChooseAddressActivity) context;
                    activity.editCurrent(addressList.get(position));
                }
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof ChooseAddressActivity) {
                    ChooseAddressActivity activity = (ChooseAddressActivity) context;
                    activity.deleteCurrent(addressList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return addressList == null ? 0 : addressList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rlAddress;
        TextView tvName;
        TextView tvTel;
        TextView tvAddressDetail;
        ImageView ivDefault;
        ImageView ivEdit;
        ImageView ivDelete;

        MyViewHolder(View view) {
            super(view);
            rlAddress = view.findViewById(R.id.rlAddress);
            tvName = view.findViewById(R.id.tvName);
            tvTel = view.findViewById(R.id.tvTel);
            tvAddressDetail = view.findViewById(R.id.tvAddressDetail);
            ivDefault = view.findViewById(R.id.ivDefault);
            ivEdit = view.findViewById(R.id.ivEdit);
            ivDelete = view.findViewById(R.id.ivDelete);
        }
    }
}