package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.InviteListEntry;
import com.ylean.cf_hospitalapp.net.ApiService;

import java.util.List;

/**
 * Created by linaidao on 2019/1/22.
 */

public class InviteAdapter extends RecyclerView.Adapter<InviteAdapter.MyViewHolder> {

    private Context context;
    private List<InviteListEntry.DataBean> inviteList;

    public InviteAdapter(Context context, List<InviteListEntry.DataBean> inviteList) {
        this.context = context;
        this.inviteList = inviteList;
    }

    @Override
    public InviteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InviteAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_invite_list, parent, false));
    }

    @Override
    public void onBindViewHolder(InviteAdapter.MyViewHolder holder, int position) {

//        15311280989
        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + inviteList.get(position).getImgurl()));
        holder.tvname.setText(inviteList.get(position).getName());

        if (!TextUtils.isEmpty(inviteList.get(position).getPhone())
                && inviteList.get(position).getPhone().length() == 11) {
            String phone = inviteList.get(position).getPhone();
            CharSequence charSequence = phone.subSequence(0, 3);
            CharSequence charSequence1 = phone.subSequence(7, 11);
            holder.tvtel.setText(charSequence + "****" + charSequence1);

        } else
            holder.tvtel.setText("");

    }

    @Override
    public int getItemCount() {
        return inviteList == null ? 0 : inviteList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvname;
        TextView tvtel;

        MyViewHolder(View view) {
            super(view);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvname = view.findViewById(R.id.tvname);
            tvtel = view.findViewById(R.id.tvtel);
        }
    }
}