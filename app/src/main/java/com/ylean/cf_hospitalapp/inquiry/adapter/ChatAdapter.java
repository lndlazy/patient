package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.activity.InquiryIntroAct;
import com.ylean.cf_hospitalapp.inquiry.bean.ChatEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskDetailEntry;
import com.ylean.cf_hospitalapp.inquiry.utils.ChatType;
import com.ylean.cf_hospitalapp.inquiry.activity.PicDetailAc;
import com.ylean.cf_hospitalapp.audio.UPlayer;
import com.ylean.cf_hospitalapp.net.ApiService;

import java.util.List;

/**
 * 聊天adapter
 * Created by linaidao on 2018/12/24.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private Context context;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int NONE = -1;
    private List<ChatEntry.DataBean> chatInfoList;
    private boolean isPlaying;
    private UPlayer uPlayer;
    private String consultaid;

    public ChatAdapter(Context context, List<ChatEntry.DataBean> chatInfoList) {
        this.context = context;
        this.chatInfoList = chatInfoList;
    }

    public void setConsultaid(String consultaid) {
        this.consultaid = consultaid;
    }

    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case LEFT:
                return new ChatAdapter.MyViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_chat_left, parent, false), viewType);

            case RIGHT:
                return new ChatAdapter.MyViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_chat_right, parent, false), viewType);
        }

        return null;

    }

    @Override
    public int getItemViewType(int position) {

        try {
            return (ChatType.CHAT_USER_TYPE_PATIENT.equals(chatInfoList.get(position).getUsertype())) ? RIGHT : LEFT;

        } catch (Exception e) {
            e.printStackTrace();
            return NONE;
        }

    }

    @Override
    public void onBindViewHolder(ChatAdapter.MyViewHolder holder, final int position) {

        try {

            setChatinfo(holder, position);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private Handler handler = new Handler();

    private void setChatinfo(MyViewHolder holder, final int i) {
        //头像
        holder.sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + chatInfoList.get(i).getImgurl()));
        holder.tvConent.setText("");

        switch (chatInfoList.get(i).getType()) {

            case ChatType.CHAT_CONTENT_TYPE_TXT:

                holder.tvConent.setVisibility(View.VISIBLE);
                holder.sdvPic.setVisibility(View.GONE);
                holder.tvVoice.setVisibility(View.GONE);
                holder.tvConent.setText(chatInfoList.get(i).getContent());
                holder.tvConent.setOnClickListener(null);
                break;
            case ChatType.CHAT_CONTENT_TYPE_VIDEO:

                holder.tvVoice.setVisibility(View.VISIBLE);
                holder.tvConent.setVisibility(View.GONE);
                holder.sdvPic.setVisibility(View.GONE);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //录音时长
                        try {
                            MediaPlayer mediaPlayer = new MediaPlayer();
                            mediaPlayer.setDataSource(chatInfoList.get(i).getUrl());
                            mediaPlayer.prepare();
                            mediaPlayer.getDuration();

                            int round = Math.round(mediaPlayer.getDuration() / 1000);

                            Logger.d("录音时长:::" + round + ",url地址::" + chatInfoList.get(i).getUrl());

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    holder.tvVoice.setText(round + "''");
                                }
                            });
                            mediaPlayer.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                holder.tvVoice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //播放语音
                        if (isPlaying) {
                            //播放中,判断当前是否正在播放当前的录音文件, 如果是当前的录音文件 则停止播放,
                            // 如果不是当前的录音文件,则停止播放,并且播放点击的录音文件
                            if (uPlayer != null)
                                uPlayer.stop();

                            //播放点击的录音文件
                            startPlay(chatInfoList.get(i).getUrl());

                        } else
                            //未播放, 开始播放
                            startPlay(chatInfoList.get(i).getUrl());

                    }
                });

                break;
            case ChatType.CHAT_CONTENT_TYPE_PIC:

                holder.tvVoice.setVisibility(View.GONE);
                holder.tvConent.setVisibility(View.GONE);
                holder.sdvPic.setVisibility(View.VISIBLE);

                //加载图片
                holder.sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + chatInfoList.get(i).getUrl()));

                holder.sdvPic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent m = new Intent(context, PicDetailAc.class);
                        m.putExtra("picUrl", ApiService.WEB_ROOT + chatInfoList.get(i).getUrl());
                        context.startActivity(m);
                    }
                });

                break;

            case ChatType.CHAT_CONTENT_TYPE_DETAIL://问诊小结

                holder.tvConent.setVisibility(View.VISIBLE);
                holder.sdvPic.setVisibility(View.GONE);
                holder.tvVoice.setVisibility(View.GONE);
                holder.tvConent.setText("问诊小结");
                holder.tvConent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //查看问诊小结 TODO


//                        PicAskDetailEntry.DataBean detailInfo = new PicAskDetailEntry.DataBean();
//                        ChatEntry.DataBean dataBean = chatInfoList.get(i);
//                        detailInfo.setConsultaid(dataBean.getConsultationid());
//                        detailInfo.setFlokname(dataBean.getf);
//                        detailInfo.setIdcard(dataBean.get);
//                        detailInfo.setAge();
//                        detailInfo.setSex();
//                        detailInfo.setMedicalcard();
//                        detailInfo.setDepartname();

                        Intent m = new Intent(context, InquiryIntroAct.class);
                        m.putExtra("consultaid", consultaid);
                        context.startActivity(m);

                    }
                });
                break;

        }
    }

    /**
     * 开始播放录音文件
     *
     * @param
     * @param url
     */
    private void startPlay(String url) {
        uPlayer = new UPlayer(url);
        uPlayer.start();

        isPlaying = true;
    }

    @Override
    public int getItemCount() {
        return chatInfoList == null ? 0 : chatInfoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sdvImg;
        TextView tvVoice;
        TextView tvConent;
        SimpleDraweeView sdvPic;

        MyViewHolder(View view, int viewType) {
            super(view);

            sdvImg = (SimpleDraweeView) view.findViewById(R.id.sdvImg);

            tvVoice = (TextView) view.findViewById(R.id.tvVoice);
            tvConent = (TextView) view.findViewById(R.id.tvConent);
            sdvPic = (SimpleDraweeView) view.findViewById(R.id.sdvPic);

        }
    }
}