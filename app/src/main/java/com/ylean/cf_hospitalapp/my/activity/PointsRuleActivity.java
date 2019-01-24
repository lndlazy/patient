package com.ylean.cf_hospitalapp.my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 积分规则
 * Created by linaidao on 2019/1/3.
 */

public class PointsRuleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_points_rule);


        TextView tvContent = (TextView) findViewById(R.id.tvContent);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);


        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


//        String content = "<font  size='30' color=\"#009FF9\">" + "||什么是积分？\r\n" + "</font>"
//                + "<font  size='24' color=\"#2A2A2A\">" + "  好医无忧积分是针对好医无忧注册用户的奖励体系。当您注册登录好医无忧后，即可开启您的好医无忧积分之旅。\n" + "</font>"
//                + "<font  color=\"#009FF9\">" + "||如何获得积分？\n" + "</font>"
//                + "<font   color=\"#2A2A2A\">" + "    登录App、每日签到、分享内容及邀请好友等操作都可以获取积分。" +
//                "获得的积分可以在积分商城中兑换优惠券、礼品卡以及精选好物等礼品。具体的积分规则及获得方式如下：\n" +
//                " \n" +
//                "每日签到\n" +
//                "连续签到第1天 - 第5天：每日签到积分+3\n" +
//                "连续签到5天：额外获得15积分；\n" +
//                "连续签到第6天 - 第30天：每日签到积分+5，\n" +
//                "连续签到10天：额外获得20积分奖励；\n" +
//                "连续签到20天：额外获得50积分奖励；\n" +
//                "连续签到30天：额外获得100积分奖励；\n" +
//                "连续签到第31天及以上：每日签到积分+10；\n" +
//                "连续签到31天后每连续签到15天：额外100积分奖励；\n" +
//                "当连续签到中断后，则回到初始签到状态重新累计连续签到天数；\n" +
//                "\n" + "</font>"
//                + "<font  color=\"#009FF9\">" + "邀请好友下载好医无忧\n" + "</font>"
//                + "<font size=\"50px\" color=\"#2a2a2a\">" + "    每邀请一名好友下载好医无忧并成功输入你的邀请码，你可以获得积分+200，好友均可获得积分+300，当用户累计邀请5个好友的时候，可额外获得50积分的奖励；当累计邀请10个好友的时候，可再获得100积分的奖励；当邀请好友数超过10之后，每5个好友额外奖励50积分。\n" +
//                "\n" + "</font>"
//                + "<font size=\"50px\" color=\"#009FF9\">" + "分享\n" + "</font>"
//                + "<font size=\"50px\" color=\"#2a2a2a\">" + "每天成功将好医无忧内容分享到其他平台：每次获得积分+3，最多3次。\n" + "</font>"
//                + "<font size=\"50px\" color=\"#009FF9\">" + "其他操作\n" + "</font>"
//                + "<font size=\"50px\" color=\"#2a2a2a\">" + "新用户首次登录成功：赠送200积分\n" +
//                "首次开启每日签到提醒：赠送20积分\n" +
//                "首次在积分商城兑换优惠券/礼品：赠送20积分\n" +
//                "发表的评论获得10积分奖励，变成精华评论时，再获得15积分的奖励。\n" +
//                "\n" + "</font>"
//                + "<font size=\"50px\" color=\"#009FF9\">" + "||积分商城内的优惠券、商品怎么兑换？如何领取？\n" + "</font>"
//                + "<font size=\"50px\" color=\"#2a2a2a\">" + "    用户可在积分商城的选择相应积分商品，点击“立即兑换”按钮，确认兑换操作后系统将扣除相应的积分，即可获得积分商品。\n" +
//                "如果是实物商品，我们的工作人员会在核实无误后尽快将实物商品寄送给您。积分好礼及优惠券一经兑换后，均不可更改，所以各位好医无忧用户在兑换前要再次确认哟~~\n" +
//                "\n" + "</font>"
//                + "<font size=\"50px\" color=\"#009FF9\">" + "||其他问题\n" + "</font>"
//                + "<font size=\"50px\" color=\"#2a2a2a\">" + "   您可在积分商城->兑换记录中，查看到以往的兑换历史。如果兑换后没有立即使用，有效期过后将不能继续使用。\n" +
//                "    为了保证好医无忧用户的权益，部分优惠券对用户的IP、ID做了相应限制。如您处在公共区域上网，您所属的IP领取过优惠券后，对于限制领取次数的优惠券您将无法再领取。\n" +
//                "    如好医无忧用户在积分积累过程或商品兑换过程中遇到上述无法解答的情况，请联系好医无忧客服进行咨询。\n" +
//                "   好医无忧客服位于“我的”页面内、积分进度下方，我们将及时处理。客服的在线时间为每天8点至22点。\n" + "</font>"
////                + "<font size='50px' color=\"#009FF9\">" + "||什么是积分？" + "</font>"
////                + "<font size='50px' color=\"#009FF9\">" + "||什么是积分？" + "</font>"
////                + "<font size='50px' color=\"#009FF9\">" + "||什么是积分？" + "</font>"
//                ;
////        content =  "<font  color=\"#009FF9\">" + "||什么是积分？" + "</font>";
//
//        tvContent.setText(Html.fromHtml(content));

    }
}
