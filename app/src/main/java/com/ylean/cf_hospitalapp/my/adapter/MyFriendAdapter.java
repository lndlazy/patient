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
import com.ylean.cf_hospitalapp.my.activity.MyFriendActivity;
import com.ylean.cf_hospitalapp.my.bean.AreaEntry;
import com.ylean.cf_hospitalapp.my.bean.MCollectionListEntry;
import com.ylean.cf_hospitalapp.net.ApiService;

import java.util.List;

/**
 * Created by linaidao on 2019/1/14.
 */

public class MyFriendAdapter extends RecyclerView.Adapter<MyFriendAdapter.MyViewHolder> {

    private Context context;
    private List<MCollectionListEntry.DataBean> collectionList;

    public MyFriendAdapter(Context context, List<MCollectionListEntry.DataBean> collectionList) {
        this.context = context;
        this.collectionList = collectionList;
    }

    @Override
    public MyFriendAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyFriendAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_my_friend, parent, false));
    }

    @Override
    public void onBindViewHolder(MyFriendAdapter.MyViewHolder holder, int position) {

        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + collectionList.get(position).getImgurl()));
        holder.tvname.setText(collectionList.get(position).getPatientname());
        holder.tvage.setText(collectionList.get(position).getAge() + "岁 " + ("1".equals(collectionList.get(position).getSex()) ? "男" : "女"));
//        holder.tvage.setText(collectionList.get(position).geta);

//        holder.tvattention.setText();

        holder.tvtime.setText(collectionList.get(position).getCreatetimetr());

        holder.tvattention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof MyFriendActivity) {
                    MyFriendActivity friendActivity = (MyFriendActivity) context;
                    friendActivity.attentionAction(collectionList.get(holder.getAdapterPosition()).getUserid());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return collectionList == null ? 0 : collectionList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvname;
        TextView tvage;
        TextView tvattention;
        TextView tvtime;

        MyViewHolder(View view) {
            super(view);
            sdvImg = view.findViewById(R.id.sdvImg);
            tvname = view.findViewById(R.id.tvname);
            tvage = view.findViewById(R.id.tvage);
            tvattention = view.findViewById(R.id.tvattention);
            tvtime = view.findViewById(R.id.tvtime);
        }
    }
}