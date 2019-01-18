package com.ylean.cf_hospitalapp.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.home.FragmentOne;
import com.ylean.cf_hospitalapp.inquiry.activity.ChargeChooseDoctorActivity;
import com.ylean.cf_hospitalapp.inquiry.bean.RecommendEntry;
import com.ylean.cf_hospitalapp.inquiry.activity.PayTWActivity;
import com.ylean.cf_hospitalapp.home.bean.BannerBean;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.register.activity.OrderRegisterActivity;
import com.ylean.cf_hospitalapp.self.view.SelfTestActivity;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.ArrayList;
import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements View.OnClickListener {

    private Context context;
    private static final int TYPE_HEAD = 0x20;
    public static final int TYPE_ARTICLE = 0x21;
    public static final int TYPE_ASK = 0x22;
    public static final int TYPE_VIDEO = 0x23;
//    private static final int TYPE_NONE = -1;

    private List<BannerBean.DataBean> bannerList = new ArrayList<>();
    private List<RecommendEntry.DataBean> recommendList;
    private FragmentOne fragmentOne;

    public HomeAdapter(FragmentOne fragmentOne, Context context, List<RecommendEntry.DataBean> recommendList) {
        this.fragmentOne = fragmentOne;
        this.context = context;
        this.recommendList = recommendList;
    }

    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case TYPE_HEAD:
                return new HomeAdapter.MyViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.layout_home_headview, parent,
                                false), viewType);
            case TYPE_ARTICLE:
                return new HomeAdapter.MyViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_home_article, parent,
                                false), viewType);
            case TYPE_ASK:
                return new HomeAdapter.MyViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_home_ask, parent,
                                false), viewType);
            case TYPE_VIDEO:
                return new HomeAdapter.MyViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_home_video, parent,
                                false), viewType);
            default:
                return null;

        }

    }

    @Override
    public void onBindViewHolder(HomeAdapter.MyViewHolder holder, int position) {

        switch (getItemViewType(position)) {

            case TYPE_HEAD:

                initHeadInfo(holder);

                break;

            case TYPE_ARTICLE://文章

                setArticleInfo(holder, position);

                break;

            case TYPE_ASK://问答

                setAnswerInfo(holder, position);

                break;

            case TYPE_VIDEO://讲堂

                setVideoInfo(holder, position);

                break;

        }

    }

    //设置讲堂信息
    private void setVideoInfo(MyViewHolder holder, int i) {

        --i;
        //标题
        holder.tvTitle.setText(recommendList.get(i).getTitle());
        //医生头像
        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + recommendList.get(i).getDocimg()));
        //医生姓名
        holder.tvName.setText(recommendList.get(i).getDoctorname() + "-" + recommendList.get(i).getHospitalname()
                + "-" + recommendList.get(i).getDoctitle());
        //发布时间
        holder.tvTime.setText(recommendList.get(i).getTimedesc());
        //浏览量
        holder.tvReadCount.setText(recommendList.get(i).getBrowsecount() + "");
        //点赞数
        holder.tvGoodCount.setText(recommendList.get(i).getFabulouscount() + "");
        //文章图片
        holder.sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + recommendList.get(i).getImgurl()));
        //是否热门
        holder.tvHot.setVisibility(recommendList.get(i).getIshot() == 1 ? View.VISIBLE : View.INVISIBLE);

    }

    //设置问答内容
    private void setAnswerInfo(MyViewHolder holder, int i) {

        --i;
        //医生头像
        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + recommendList.get(i).getUserimg()));
        //医生姓名
        holder.tvName.setText(recommendList.get(i).getUsername());
        //发布时间
        holder.tvTime.setText(recommendList.get(i).getCreatetime());
        //提问内容
        holder.tvAsk.setText(recommendList.get(i).getProblem());
        //回答内容
        holder.tvAnswer.setText(recommendList.get(i).getAnswer());
    }

    //设置文章条目
    private void setArticleInfo(MyViewHolder holder, int i) {

        --i;
        //文章图片
        holder.sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + recommendList.get(i).getImgurl()));
        //标题
        holder.tvTitle.setText(recommendList.get(i).getTitle());
//        holder.tvContent.setText(recommendList.get(p).get);
        //医生头像
        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + recommendList.get(i).getDocimg()));
        //医生姓名
        holder.tvName.setText(recommendList.get(i).getDoctorname());
        //评论数
        holder.tvCommitCount.setText(recommendList.get(i).getCommentcount() + "");
        //点赞数
        holder.tvGoodCount.setText(recommendList.get(i).getFabulouscount() + "");
        //浏览量
        holder.tvReadCount.setText(recommendList.get(i).getBrowsecount() + "");
        //发布时间
        holder.tvTime.setText(recommendList.get(i).getTimedesc());
    }

    /**
     * 设置头信息
     *
     * @param holder
     */
    private void initHeadInfo(MyViewHolder holder) {

        if (bannerList == null)
            return;

        List<View> picList = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            picList.add(View.inflate(context, R.layout.item_viewpager, null));
        }

        if (bannerList.size() == 0)
            picList.add(View.inflate(context, R.layout.item_viewpager, null));

        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(context, picList, bannerList);
        holder.viewPager.setAdapter(homePagerAdapter);

        holder.llCharge.setOnClickListener(this);
        holder.llFree.setOnClickListener(this);
        holder.llTestSelf.setOnClickListener(this);
        holder.llOrder.setOnClickListener(this);

        holder.tablayout.addOnTabSelectedListener(listener);

//        holder.viewPager.
    }

//    @Override
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0)
            return TYPE_HEAD;

        --position;

        //1 视频 2 文章  3 问答
        switch (recommendList.get(position).getType()) {
            case "1":
                return TYPE_VIDEO;

            case "2":
                return TYPE_ARTICLE;

            case "3":
                return TYPE_ASK;

        }

        return super.getItemViewType(position);

    }

    @Override
    public int getItemCount() {

//        Logger.d("item个数::" + (recommendList == null ? 0 : recommendList.size() + 1));
        return recommendList == null ? 0 : recommendList.size() + 1;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.llTestSelf:

                Intent t = new Intent(context, SelfTestActivity.class);
                context.startActivity(t);

                break;

            case R.id.llCharge://付费问诊
                Intent m = new Intent(context, ChargeChooseDoctorActivity.class);
                m.putExtra("askType", SpValue.ASK_CHARGE);
                context.startActivity(m);
                break;

            case R.id.llFree://免费义诊
                Intent free = new Intent(context, PayTWActivity.class);
                free.putExtra("askType", SpValue.ASK_FREE);//免费义诊
                free.putExtra("type", SpValue.ASK_TYPE_PIC);//免费义诊只有图文问诊
                context.startActivity(free);
                break;

            case R.id.llOrder://预约挂号
                Intent order = new Intent(context, OrderRegisterActivity.class);
//                order.putExtra("askType", SpValue.ASK_FREE);//免费义诊
//                order.putExtra("type", SpValue.ASK_TYPE_PIC);//免费义诊只有图文问诊
                context.startActivity(order);
                break;

        }

    }

    private TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            //选择的tab
            Logger.d("onTabSelected,选择的tab:" + tab.getText().toString());
            int position = tab.getPosition();
            fragmentOne.setCurrentPosition(position);

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
//            Logger.d("onTabUnselected,离开的那个tab::" + tab.getText().toString());
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
//            Logger.d("onTabReselected,再次选择tab::" + tab.getText().toString());
        }
    };


    public void setBannerInfo(List<BannerBean.DataBean> bannerList) {
        this.bannerList.clear();
        this.bannerList.addAll(bannerList);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        SimpleDraweeView sdvPic;
        TextView tvTitle;
        TextView tvName;
        TextView tvContent;
        TextView tvTime;
        TextView tvReadCount;
        TextView tvGoodCount;
        TextView tvCommitCount;

        TextView tvHot;

        TextView tvAsk;
        TextView tvAnswer;

        ViewPager viewPager;
//        LinearLayout llPoint;
        LinearLayout llTestSelf;
        LinearLayout llFree;
        LinearLayout llCharge;
        LinearLayout llOrder;
        TabLayout tablayout;

        MyViewHolder(View view, int viewType) {
            super(view);

            switch (viewType) {

                case TYPE_HEAD:

                    viewPager = view.findViewById(R.id.viewPager);
//                    llPoint = view.findViewById(R.id.llPoint);
                    llTestSelf = view.findViewById(R.id.llTestSelf);
                    llFree = view.findViewById(R.id.llFree);
                    llCharge = view.findViewById(R.id.llCharge);
                    llOrder = view.findViewById(R.id.llOrder);
                    tablayout = view.findViewById(R.id.tablayout);

                    break;

                case TYPE_ARTICLE:

                    sdvImg = (SimpleDraweeView) view.findViewById(R.id.sdvImg);
                    sdvPic = (SimpleDraweeView) view.findViewById(R.id.sdvPic);
                    tvTitle = (TextView) view.findViewById(R.id.tvTitle);
                    tvContent = (TextView) view.findViewById(R.id.tvContent);
                    tvName = (TextView) view.findViewById(R.id.tvName);
                    tvTime = (TextView) view.findViewById(R.id.tvTime);
                    tvReadCount = (TextView) view.findViewById(R.id.tvReadCount);
                    tvGoodCount = (TextView) view.findViewById(R.id.tvGoodCount);
                    tvCommitCount = (TextView) view.findViewById(R.id.tvCommitCount);

                    return;

                case TYPE_ASK:

                    sdvImg = (SimpleDraweeView) view.findViewById(R.id.sdvImg);
                    tvName = (TextView) view.findViewById(R.id.tvName);
                    tvTime = (TextView) view.findViewById(R.id.tvTime);
                    tvAsk = (TextView) view.findViewById(R.id.tvAsk);
                    tvAnswer = (TextView) view.findViewById(R.id.tvAnswer);

                    break;

                case TYPE_VIDEO:
                    sdvImg = (SimpleDraweeView) view.findViewById(R.id.sdvImg);
                    sdvPic = (SimpleDraweeView) view.findViewById(R.id.sdvPic);

                    tvTitle = (TextView) view.findViewById(R.id.tvTitle);
                    tvName = (TextView) view.findViewById(R.id.tvName);
                    tvTime = (TextView) view.findViewById(R.id.tvTime);
                    tvReadCount = (TextView) view.findViewById(R.id.tvReadCount);
                    tvGoodCount = (TextView) view.findViewById(R.id.tvGoodCount);
                    tvHot = (TextView) view.findViewById(R.id.tvHot);

                    break;

            }

        }
    }
}