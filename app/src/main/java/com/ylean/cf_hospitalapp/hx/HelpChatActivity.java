package com.ylean.cf_hospitalapp.hx;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hyphenate.EMGroupChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMucSharedFile;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EasyUtils;
import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.hx.fragement.ChatFragment;

import java.util.List;

/**
 * 帮帮团聊天页面
 * Created by linaidao on 2019/1/15.
 */

public class HelpChatActivity extends FragmentActivity {

//    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
//    private android.support.v7.widget.RecyclerView recyclerView;
//    private android.widget.EditText etInput;
//    private android.widget.ImageView choosePic;
//    private HelpEntry.DataBean helpInfo;

    private EaseChatFragment chatFragment;
    String toChatUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.act_help_chat);
        setContentView(R.layout.act_help_chat_frag);

//        helpInfo = getIntent().getParcelableExtra("helpInfo");

        toChatUsername = getIntent().getExtras().getString("userId");

        Logger.d("toChatUsername::" + toChatUsername);
        chatFragment = new ChatFragment();
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();

//        init();

//        //加入聊天室
//        joinRoom();
    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
        if (EasyUtils.isSingleActivity(this)) {
            finish();
        }

//        EMClient.getInstance().chatroomManager().leaveChatRoom(toChatUsername);
    }


    private void joinRoom() {



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().groupManager().joinGroup(toChatUsername);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }


                EMClient.getInstance().groupManager().addGroupChangeListener(new EMGroupChangeListener() {
                    @Override
                    public void onInvitationReceived(String groupId, String groupName, String inviter, String reason) {
                        //接收到群组加入邀请
                    }

                    @Override
                    public void onRequestToJoinReceived(String groupId, String groupName, String applyer, String reason) {
                        //用户申请加入群
                        Logger.d("用户申请加入群");
                    }

                    @Override
                    public void onRequestToJoinAccepted(String groupId, String groupName, String accepter) {
                        //加群申请被同意
                        Logger.d("加群申请被同意");

                    }

                    @Override
                    public void onRequestToJoinDeclined(String groupId, String groupName, String decliner, String reason) {
                        //加群申请被拒绝
                        Logger.d("加群申请被拒绝");

                    }

                    @Override
                    public void onInvitationAccepted(String groupId, String inviter, String reason) {
                        //群组邀请被同意
                        Logger.d("群组邀请被同意");

                    }

                    @Override
                    public void onInvitationDeclined(String groupId, String invitee, String reason) {
                        //群组邀请被拒绝
                        Logger.d("群组邀请被拒绝");

                    }

                    @Override
                    public void onUserRemoved(String groupId, String groupName) {

                    }

                    @Override
                    public void onGroupDestroyed(String groupId, String groupName) {

                    }

                    @Override
                    public void onAutoAcceptInvitationFromGroup(String groupId, String inviter, String inviteMessage) {
                        //接收邀请时自动加入到群组的通知
                    }

                    @Override
                    public void onMuteListAdded(String groupId, final List<String> mutes, final long muteExpire) {
                        //成员禁言的通知
                    }

                    @Override
                    public void onMuteListRemoved(String groupId, final List<String> mutes) {
                        //成员从禁言列表里移除通知
                    }

                    @Override
                    public void onAdminAdded(String groupId, String administrator) {
                        //增加管理员的通知
                        Logger.d("增加管理员的通知");

                    }

                    @Override
                    public void onAdminRemoved(String groupId, String administrator) {
                        //管理员移除的通知
                    }

                    @Override
                    public void onOwnerChanged(String groupId, String newOwner, String oldOwner) {
                        //群所有者变动通知
                    }
                    @Override
                    public void onMemberJoined(final String groupId,  final String member){
                        //群组加入新成员通知
                        Logger.d("群组加入新成员通知");

                    }
                    @Override
                    public void onMemberExited(final String groupId, final String member) {
                        //群成员退出通知
                    }

                    @Override
                    public void onAnnouncementChanged(String groupId, String announcement) {
                        //群公告变动通知
                    }

                    @Override
                    public void onSharedFileAdded(String groupId, EMMucSharedFile sharedFile) {
                        //增加共享文件的通知
                    }

                    @Override
                    public void onSharedFileDeleted(String groupId, String fileId) {
                        //群共享文件删除通知
                    }
                });
            }
        }).start();



//        EMClient.getInstance().chatroomManager().joinChatRoom(toChatUsername, new EMValueCallBack<EMChatRoom>() {
//
//            @Override
//            public void onSuccess(EMChatRoom value) {
//                //加入聊天室成功
//
//                Logger.d("加入聊天室成功");
//            }
//
//            @Override
//            public void onError(final int error, String errorMsg) {
//                //加入聊天室失败
////                showErr("加入聊天室失败");
//                Logger.d("加入聊天室失败" + errorMsg);
//
//            }
//        });


    }

    public String getToChatUsername(){
        return toChatUsername;
    }

//    private void init() {
//        this.choosePic = (ImageView) findViewById(R.id.choosePic);
//        this.etInput = (EditText) findViewById(R.id.etInput);
//        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);
//        tbv.setOnLeftClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
}
