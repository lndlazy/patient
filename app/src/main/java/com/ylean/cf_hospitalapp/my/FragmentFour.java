package com.ylean.cf_hospitalapp.my;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.inquiry.activity.AskPeopleActivity;
import com.ylean.cf_hospitalapp.base.view.BaseFragment;
import com.ylean.cf_hospitalapp.my.activity.DoctorListActivity;
import com.ylean.cf_hospitalapp.my.activity.EarnPointsActivity;
import com.ylean.cf_hospitalapp.my.activity.FeedbackActivity;
import com.ylean.cf_hospitalapp.my.activity.MyCollectionActivity;
import com.ylean.cf_hospitalapp.my.activity.MyCommentActivity;
import com.ylean.cf_hospitalapp.my.activity.MyEvaluationActivity;
import com.ylean.cf_hospitalapp.my.activity.MyFriendActivity;
import com.ylean.cf_hospitalapp.my.activity.MyNewsAct;
import com.ylean.cf_hospitalapp.my.activity.MyRequestActivity;
import com.ylean.cf_hospitalapp.my.activity.MyTopicActivity;
import com.ylean.cf_hospitalapp.my.activity.ServiceOrderListActivity;
import com.ylean.cf_hospitalapp.register.activity.MyRegisterOrderListActivity;
import com.ylean.cf_hospitalapp.my.activity.PointsDetailActivity;
import com.ylean.cf_hospitalapp.my.activity.PointsMallAct;
import com.ylean.cf_hospitalapp.my.activity.SettingAct;
import com.ylean.cf_hospitalapp.my.activity.VipRoueActivity;
import com.ylean.cf_hospitalapp.my.adapter.GroupMyAdapter;
import com.ylean.cf_hospitalapp.my.activity.MyFreeAskAct;
import com.ylean.cf_hospitalapp.my.activity.MyRegisteredAct;
import com.ylean.cf_hospitalapp.my.bean.MyInfoEntry;
import com.ylean.cf_hospitalapp.my.presenter.IMyFragmentPres;
import com.ylean.cf_hospitalapp.my.view.IMyFragmentView;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.Constant;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.GridViewForScrollView;
import com.ylean.cf_hospitalapp.widget.MyItemView;

public class FragmentFour extends BaseFragment implements View.OnClickListener, IMyFragmentView {

    private IMyFragmentPres iMyFragmentPres = new IMyFragmentPres(this);
    private SimpleDraweeView sdvImg;
    private TextView tvName;
    private TextView tvUsbableIntegral;
    private TextView tvTotalIntegral;
    private MyInfoEntry.DataBean myInfoEntryData;
    public static final int REQUEST_PERMISSION_CALL_CODE = 0x14;
    private Intent m;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_four, null);

        initView(view);
        iMyFragmentPres.myInfo((String) SaveUtils.get(getActivity(), SpValue.TOKEN, ""));
        return view;
    }

    private void initView(View view) {

        GridViewForScrollView gridView = view.findViewById(R.id.gridView);

        sdvImg = view.findViewById(R.id.sdvImg);
        ImageView ivSet = view.findViewById(R.id.ivSet);
        ImageView ivMsg = view.findViewById(R.id.ivMsg);
        ImageView ivNewMsg = view.findViewById(R.id.ivNewMsg);

        tvName = view.findViewById(R.id.tvName);
        TextView tvLevel = view.findViewById(R.id.tvLevel);
        TextView tvVipRule = view.findViewById(R.id.tvVipRule);//会员规则
        tvUsbableIntegral = view.findViewById(R.id.tvUsbableIntegral);
        tvTotalIntegral = view.findViewById(R.id.tvTotalIntegral);

        LinearLayout llDetail = view.findViewById(R.id.llDetail);
        LinearLayout llEarn = view.findViewById(R.id.llEarn);
        LinearLayout llMyRegister = view.findViewById(R.id.llMyRegister);
        LinearLayout llMyRegistered = view.findViewById(R.id.llMyRegistered);
        LinearLayout llGoodsOrder = view.findViewById(R.id.llGoodsOrder);
        LinearLayout llServiceOrder = view.findViewById(R.id.llServiceOrder);
        RelativeLayout rlIntegralMall = view.findViewById(R.id.rlIntegralMall);
        MyItemView mivHelp = view.findViewById(R.id.mivHelp);
        MyItemView mivRecommadFriend = view.findViewById(R.id.mivRecommadFriend);
        MyItemView mivCustomer = view.findViewById(R.id.mivCustomer);
        MyItemView mivCustomerTel = view.findViewById(R.id.mivCustomerTel);

        ivMsg.setOnClickListener(this);
        tvVipRule.setOnClickListener(this);
        llMyRegister.setOnClickListener(this);
        llMyRegistered.setOnClickListener(this);
        llGoodsOrder.setOnClickListener(this);
        llServiceOrder.setOnClickListener(this);

        llDetail.setOnClickListener(this);
        llEarn.setOnClickListener(this);
        rlIntegralMall.setOnClickListener(this);
        mivHelp.setOnClickListener(this);
        mivRecommadFriend.setOnClickListener(this);
        mivCustomer.setOnClickListener(this);
        mivCustomerTel.setOnClickListener(this);
        ivSet.setOnClickListener(this);

        GroupMyAdapter groupMyAdapter = new GroupMyAdapter(getActivity());
        gridView.setAdapter(groupMyAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0://我的义诊
                        nextActivity(MyFreeAskAct.class);
                        break;

                    case 1://我的申请
                        nextActivity(MyRequestActivity.class);
                        break;

                    case 2://我的医生
                        nextActivity(DoctorListActivity.class);
                        break;
                    case 3://我的家人

                        m = new Intent(getActivity(), AskPeopleActivity.class);
                        m.putExtra("title", "我的家人");
                        m.putExtra("type", "my");
                        startActivity(m);

                        break;

                    case 5://我的评论

                        m = new Intent(getActivity(), MyCommentActivity.class);
                        startActivity(m);

                        break;

                    case 6://我的收藏

                        m = new Intent(getActivity(), MyCollectionActivity.class);
                        startActivity(m);

                        break;

                    case 7://我的评价
                        m = new Intent(getActivity(), MyEvaluationActivity.class);
                        startActivity(m);
                        break;
                    case 8://我的病友
                        m = new Intent(getActivity(), MyFriendActivity.class);
                        startActivity(m);
                        break;
                    case 9://我的话题
                        m = new Intent(getActivity(), MyTopicActivity.class);
                        startActivity(m);
                        break;
                }

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivMsg://我的消息

                nextActivity(MyNewsAct.class);

                break;

            case R.id.tvVipRule://会员规则

                nextActivity(VipRoueActivity.class);

                break;

            case R.id.ivSet://个人设置

                Intent m = new Intent(getActivity(), SettingAct.class);
                if (myInfoEntryData != null)
                    m.putExtra("myInfoEntryData", myInfoEntryData);
                startActivity(m);

                break;

            case R.id.llMyRegister://我的挂号

                nextActivity(MyRegisterOrderListActivity.class);

                break;

            case R.id.llMyRegistered://我的问诊

                nextActivity(MyRegisteredAct.class);

                break;

            case R.id.llGoodsOrder://商品订单
                break;

            case R.id.llServiceOrder://服务订单

                nextActivity(ServiceOrderListActivity.class);

                break;

//            case R.id.llUseable://可用积分
//                break;
//
//            case R.id.llTotal://累计积分
//                break;

            case R.id.llDetail://积分明细

                nextActivity(PointsDetailActivity.class);
                break;

            case R.id.llEarn://赚钱积分
                nextActivity(EarnPointsActivity.class);
                break;

            case R.id.rlIntegralMall://积分商城

                nextActivity(PointsMallAct.class);

                break;

            case R.id.mivHelp://帮助与反馈

                nextActivity(FeedbackActivity.class);
                break;

            case R.id.mivRecommadFriend://推荐给朋友

                break;

            case R.id.mivCustomer://联系客服

                break;

            case R.id.mivCustomerTel:// 客服电话
                getCallPer();
                break;

        }

    }

    //获取拨号权限
    private void getCallPer() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            callPhoneNum();
        } else {
            //需要进行权限申请
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION_CALL_CODE);
        }

    }

    public void callPhoneNum() {

        AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity(), R.style.AlertDialogCustom);
        builder.setMessage(Constant.CUSTOM_PHONE_NUM).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                //拨打电话
                Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + Constant.CUSTOM_PHONE_NUM));
                nextActivityWithIntent(intent);

            }
        }).show();

    }


    @Override
    public void setInfo(MyInfoEntry myInfoEntry) {

        if (myInfoEntry == null || myInfoEntry.getData() == null)
            return;

        myInfoEntryData = myInfoEntry.getData();
        tvName.setText(TextUtils.isEmpty(myInfoEntry.getData().getNickname()) ? "未设置" : myInfoEntry.getData().getNickname());
        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + myInfoEntry.getData().getImgurl()));

        tvUsbableIntegral.setText(myInfoEntry.getData().getPoints());
        tvTotalIntegral.setText(myInfoEntry.getData().getTotalPoints());

    }
}
