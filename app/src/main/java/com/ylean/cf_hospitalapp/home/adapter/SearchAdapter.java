package com.ylean.cf_hospitalapp.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.home.activity.ArtDetailAct;
import com.ylean.cf_hospitalapp.home.bean.SearchListEntry;
import com.ylean.cf_hospitalapp.inquiry.activity.InquiryDetailAct;
import com.ylean.cf_hospitalapp.inquiry.activity.PayTWActivity;
import com.ylean.cf_hospitalapp.my.activity.WebviewActivity;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.register.activity.DepartmentChooseActivity;
import com.ylean.cf_hospitalapp.register.bean.HospitalListEntry;
import com.ylean.cf_hospitalapp.utils.SpValue;

import java.util.List;


/**
 * 搜索adapter
 * Created by linaidao on 2019/1/10.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private static final int TYPE_HOSPITAL = 10;
    private static final int TYPE_DOCTOR = 11;
    private static final int TYPE_DEPARTMENT = 12;
    private static final int TYPE_ARTICLE = 13;
    private static final int TYPE_INQUIRY = 14;

    private Context context;
    private List<SearchListEntry.DataBean> searchList;

    public SearchAdapter(Context context, List<SearchListEntry.DataBean> searchList) {
        this.context = context;
        this.searchList = searchList;
    }

    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case TYPE_HOSPITAL:
                return new SearchAdapter.MyViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_hospital, parent, false), viewType);

            case TYPE_DOCTOR:
                return new SearchAdapter.MyViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_pay_ask_doctor, parent, false), viewType);

//            case TYPE_DEPARTMENT:
//                return new SearchAdapter.MyViewHolder(
//                        LayoutInflater.from(context).inflate(R.layout.item_string, parent, false), viewType);

            case TYPE_ARTICLE:
                return new SearchAdapter.MyViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_home_article, parent, false), viewType);

            case TYPE_INQUIRY:
                return new SearchAdapter.MyViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_home_ask, parent, false), viewType);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(SearchAdapter.MyViewHolder holder, int position) {

        switch (getItemViewType(position)) {

            case TYPE_HOSPITAL:

                setHospitalinfo(holder, position);

                break;

            case TYPE_DOCTOR:

                setDoctorInfo(holder, position);
                break;

//            case TYPE_DEPARTMENT:
//                break;

            case TYPE_ARTICLE:
                setArticleInfo(holder, position);
                break;

            case TYPE_INQUIRY://问诊
                setInquiryInfo(holder, position);
                break;

        }

    }

    private void setHospitalinfo(MyViewHolder holder, int position) {

        holder.sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + searchList.get(position).getImgurl()));
        holder.tvHospitalName.setText(searchList.get(position).getHospitalname());
        holder.tvAddress.setText(searchList.get(position).getAddress());
        holder.tel.setText(searchList.get(position).getSupportel());

        holder.rl_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent m = new Intent(context, WebviewActivity.class);
                m.putExtra("title", "医院详细介绍");
                String url = ApiService.WEB_ROOT + ApiService.HOSPITAL_DETAIL + "?id=" + searchList.get(position).getHospitalid();
                m.putExtra("url", url);
                context.startActivity(m);

            }
        });
        holder.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //选择科室
                Intent m = new Intent(context, DepartmentChooseActivity.class);
                SearchListEntry.DataBean hospitalSearchInfo = searchList.get(position);
                HospitalListEntry.DataBean hospitalInfo = new HospitalListEntry.DataBean();
                hospitalInfo.setAddress(hospitalSearchInfo.getAddress());
                hospitalInfo.setAreaname(hospitalSearchInfo.getAreaname());
                hospitalInfo.setCityname(hospitalSearchInfo.getCityname());
                hospitalInfo.setHospitalid(hospitalSearchInfo.getHospitalid());
                hospitalInfo.setHospitalname(hospitalSearchInfo.getHospitalname());
                hospitalInfo.setImgurl(hospitalSearchInfo.getImgurl());
                hospitalInfo.setLatitude(hospitalSearchInfo.getLatitude());
                hospitalInfo.setLongitude(hospitalSearchInfo.getLongitude());
                hospitalInfo.setProvincename(hospitalSearchInfo.getProvincename());
                hospitalInfo.setSupportel(hospitalSearchInfo.getSupportel());
                m.putExtra("hospitalInfo", hospitalInfo);

                context.startActivity(m);

            }
        });
    }

    private void setDoctorInfo(MyViewHolder holder, int position) {
        holder.sdvImg.setImageURI(ApiService.WEB_ROOT + Uri.parse(searchList.get(position).getImgurl()));
        holder.tvName.setText(searchList.get(position).getDoctorname());
        holder.tvJob.setText(searchList.get(position).getDoctortitle());
        holder.tvCompany.setText(searchList.get(position).getHospitalname());
        holder.tvInfo.setText(TextUtils.isEmpty(searchList.get(position).getAdeptdesc()) ? "暂无简介" :
                searchList.get(position).getAdeptdesc());
        holder.tvp1.setText(searchList.get(position).getTwprice());
        holder.tvp2.setText(searchList.get(position).getDhprice());
        holder.tvp3.setText(searchList.get(position).getSpprice());

        //图文问诊
        holder.llTuwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage(position, context, SpValue.ASK_TYPE_PIC);
            }
        });
        //电话问诊
        holder.llTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage(position, context, SpValue.ASK_TYPE_TEL);
            }
        });
        //视频问诊
        holder.llVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage(position, context, SpValue.ASK_TYPE_VIDEO);
            }
        });
    }

    private void setArticleInfo(MyViewHolder holder, int position) {
        //文章图片
        holder.sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + searchList.get(position).getImgurl()));
        //标题
        holder.tvTitle.setText(searchList.get(position).getTitle());
//        holder.tvContent.setText(recommendList.get(p).get);
        //医生头像
        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + searchList.get(position).getDocimg()));
        //医生姓名
        holder.tvName.setText(searchList.get(position).getDoctorname());
        //评论数
        holder.tvCommitCount.setText(searchList.get(position).getCommentcount());
        //点赞数
        holder.tvGoodCount.setText(searchList.get(position).getFabulouscount());
        //浏览量
        holder.tvReadCount.setText(searchList.get(position).getBrowsecount());
        //发布时间
        holder.tvTime.setText(searchList.get(position).getTimedesc());

        holder.llarticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent m = new Intent();
                m.setClass(context, ArtDetailAct.class);
                m.putExtra("id", searchList.get(position).getId());
                context.startActivity(m);

            }
        });
    }

    private void setInquiryInfo(MyViewHolder holder, int position) {
        //医生头像
        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + searchList.get(position).getUserimg()));
        //医生姓名
        holder.tvName.setText(searchList.get(position).getUsername());
        //发布时间
        holder.tvTime.setText(searchList.get(position).getCreatetime());
        //提问内容
        holder.tvAsk.setText(searchList.get(position).getProblem());
        //回答内容
        holder.tvAnswer.setText(searchList.get(position).getAnswer());
        holder.llask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent();
//                        m.setClass(getActivity(), AnswerDetailAct.class);
                m.setClass(context, InquiryDetailAct.class);
                m.putExtra("noedit", true);//不能回复，定时刷新
                m.putExtra("consultaid", searchList.get(position).getConsultaid());
                context.startActivity(m);
            }
        });
    }

    private void nextPage(int position, Context context, String askType) {
        Intent m = new Intent(context, PayTWActivity.class);
        m.putExtra("doctorId", searchList.get(position).getDoctorid());//医生id
        m.putExtra("doctorName", searchList.get(position).getDoctorname());//医生姓名
        m.putExtra("price", searchList.get(position).getTwprice());//问诊价格
        m.putExtra("type", askType);//图文，视频，电话 问诊
        m.putExtra("askType", SpValue.ASK_CHARGE);//付费问诊
        context.startActivity(m);
    }

    @Override
    public int getItemViewType(int position) {

        switch (searchList.get(position).getSearchType()) {
            case 0:
                return TYPE_HOSPITAL;

            case 1:
                return TYPE_DOCTOR;

//            case 2:
//                return TYPE_DEPARTMENT;

            case 3:
                return TYPE_ARTICLE;

            case 4:
                return TYPE_INQUIRY;

        }

        return super.getItemViewType(position);

    }


    @Override
    public int getItemCount() {
        return searchList == null ? 0 : searchList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvPic;
        TextView tvHospitalName;
        TextView tvAddress;
        TextView tel;
        TextView tvRegister;
        RelativeLayout rl_content;

        SimpleDraweeView sdvImg;
        TextView tvName;
        TextView tvJob;
        TextView tvCompany;
        TextView tvInfo;
        TextView tvp1;
        TextView tvp2;
        TextView tvp3;
        LinearLayout llTuwen;
        LinearLayout llTel;
        LinearLayout llVideo;
        LinearLayout llPrice;

        LinearLayout llarticle;
        TextView tvTitle;
        TextView tvContent;
        TextView tvTime;
        TextView tvReadCount;
        TextView tvGoodCount;
        TextView tvCommitCount;

        TextView tvAsk;
        TextView tvAnswer;
        LinearLayout llask;

        MyViewHolder(View view, int viewType) {
            super(view);

            switch (viewType) {

                case TYPE_HOSPITAL:

                    sdvPic = view.findViewById(R.id.sdvPic);
                    rl_content = view.findViewById(R.id.rl_content);
                    tvHospitalName = view.findViewById(R.id.tvHospitalName);
                    tvAddress = view.findViewById(R.id.tvAddress);
                    tel = view.findViewById(R.id.tel);
                    tvRegister = view.findViewById(R.id.tvRegister);//挂号

                    break;

                case TYPE_DOCTOR:

                    sdvImg = view.findViewById(R.id.sdvImg);
                    tvName = view.findViewById(R.id.tvName);
                    tvJob = view.findViewById(R.id.tvJob);
                    tvCompany = view.findViewById(R.id.tvCompany);
                    tvInfo = view.findViewById(R.id.tvInfo);
                    tvp1 = view.findViewById(R.id.tvp1);
                    tvp2 = view.findViewById(R.id.tvp2);
                    tvp3 = view.findViewById(R.id.tvp3);
                    llTuwen = view.findViewById(R.id.llTuwen);
                    llTel = view.findViewById(R.id.llTel);
                    llVideo = view.findViewById(R.id.llVideo);
                    llPrice = view.findViewById(R.id.llPrice);
                    break;

                case TYPE_DEPARTMENT:
                    break;

                case TYPE_ARTICLE:

                    sdvImg = view.findViewById(R.id.sdvImg);
                    sdvPic = view.findViewById(R.id.sdvPic);
                    tvTitle = view.findViewById(R.id.tvTitle);
                    tvContent = view.findViewById(R.id.tvContent);
                    tvName = view.findViewById(R.id.tvName);
                    tvTime = view.findViewById(R.id.tvTime);
                    tvReadCount = view.findViewById(R.id.tvReadCount);
                    tvGoodCount = view.findViewById(R.id.tvGoodCount);
                    tvCommitCount = view.findViewById(R.id.tvCommitCount);
                    llarticle = view.findViewById(R.id.llarticle);

                    break;

                case TYPE_INQUIRY:

                    sdvImg = (SimpleDraweeView) view.findViewById(R.id.sdvImg);
                    tvName = (TextView) view.findViewById(R.id.tvName);
                    tvTime = (TextView) view.findViewById(R.id.tvTime);
                    tvAsk = (TextView) view.findViewById(R.id.tvAsk);
                    tvAnswer = (TextView) view.findViewById(R.id.tvAnswer);
                    llask = view.findViewById(R.id.llask);

                    break;

            }

        }
    }
}