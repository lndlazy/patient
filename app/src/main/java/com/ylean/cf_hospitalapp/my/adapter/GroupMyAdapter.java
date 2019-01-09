package com.ylean.cf_hospitalapp.my.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.bean.IitemBean;

import java.util.ArrayList;
import java.util.List;

public class GroupMyAdapter extends BaseAdapter {

    private Context ctx;
    private List<IitemBean> itemBeanList = new ArrayList<>();

    public GroupMyAdapter(Context ctx) {
        this.ctx = ctx;

        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.my_item_1), R.mipmap.ic_item_1));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.my_item_2), R.mipmap.ic_item_2));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.my_item_3), R.mipmap.ic_item_3));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.my_item_4), R.mipmap.ic_item_4));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.my_item_5), R.mipmap.ic_item_5));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.my_item_6), R.mipmap.ic_item_6));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.my_item_7), R.mipmap.ic_item_7));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.my_item_8), R.mipmap.ic_item_8));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.my_item_9), R.mipmap.ic_item_9));
        itemBeanList.add(new IitemBean(ctx.getResources().getString(R.string.my_item_10), R.mipmap.ic_item_10));

    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (convertView == null) {

            convertView = View.inflate(ctx, R.layout.item_grideview_my, null);
            viewHolder = new ViewHolder();

            viewHolder.iv_head_pic = (ImageView) convertView.findViewById(R.id.iv_head_pic);
            viewHolder.tv_nickname = (TextView) convertView.findViewById(R.id.tv_nickname);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_nickname.setText(itemBeanList.get(i).getName());
        viewHolder.iv_head_pic.setImageResource(itemBeanList.get(i).getResId());

        return convertView;
    }

    private class ViewHolder {

        private ImageView iv_head_pic;
        private TextView tv_nickname;

    }

}