package com.ylean.cf_hospitalapp.home.presenter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.bean.PeopleEntry;
import com.ylean.cf_hospitalapp.utils.CommonUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;

public class FamilyNumsAdapter extends RecyclerView.Adapter<FamilyNumsAdapter.MyViewHolder> {

    private Context context;
    private List<PeopleEntry.DataBean> familyNumList;

    public FamilyNumsAdapter(Context context, List<PeopleEntry.DataBean> familyNumList) {
        this.context = context;
        this.familyNumList = familyNumList;
    }

    @Override
    public FamilyNumsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new FamilyNumsAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_family_num, parent, false));
    }

    @Override
    public void onBindViewHolder(FamilyNumsAdapter.MyViewHolder holder, int position) {

//        holder.ivSelect.setSelected(familyNumList.get(position).isSelect());
        holder.sdvPic.setImageURI(Uri.parse(familyNumList.get(position).getImgurl()));
        holder.tvId.setText("身份证    " + familyNumList.get(position).getIDcard());
        holder.tvInfo.setText(familyNumList.get(position).getAge() + "岁" +
                (SpValue.SEX_FEMALE.equals(familyNumList.get(position).getSex()) ? "    女" : "  男")
                + " 医保 " + familyNumList.get(position).getMedicalcard());
        holder.tvName.setText(familyNumList.get(position).getName());
        holder.tvRelat.setText(CommonUtils.getRelationship(familyNumList.get(position).getRelationship()));

    }

    @Override
    public int getItemCount() {
        return familyNumList == null ? 0 : familyNumList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //        ImageView ivSelect;
        SimpleDraweeView sdvPic;
        TextView tvName;
        TextView tvId;
        TextView tvInfo;
        TextView tvRelat;

        MyViewHolder(View view) {
            super(view);

//            ivSelect = view.findViewById(R.id.ivSelect);
            sdvPic = view.findViewById(R.id.sdvPic);

            tvName = view.findViewById(R.id.tvName);
            tvId = view.findViewById(R.id.tvId);
            tvInfo = view.findViewById(R.id.tvInfo);
            tvRelat = view.findViewById(R.id.tvRelat);

        }
    }
}