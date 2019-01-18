package com.ylean.cf_hospitalapp.base.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.Presenter.IHxPresenter;
import com.ylean.cf_hospitalapp.inquiry.FragmentThree;
import com.ylean.cf_hospitalapp.home.FragmentOne;
import com.ylean.cf_hospitalapp.my.FragmentFour;
import com.ylean.cf_hospitalapp.popular.FragmentTwo;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@SuppressLint("Registered")
public class HomeActivity extends BaseActivity implements View.OnClickListener, IHxView {

    private ImageView ivone;
    private TextView tvone;
    private RelativeLayout rlone;
    private ImageView ivtwo;
    private TextView tvtwo;
    private RelativeLayout rltwo;
    private ImageView ivthree;
    private TextView tvthree;
    private RelativeLayout rlthree;
    private ImageView ivfour;
    private TextView tvfour;
    private RelativeLayout rlfour;
    private TextView tv_msg_count;
    private FragmentManager fragmentManager;

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentFour fragmentFour;
    private FragmentThree fragmentThree;

    private static final int REQUEST_PERMISSION_LOCATION_CODE = 0x13;


    private IHxPresenter iHxPresenter = new IHxPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        initBottomView();
        initFragment();
        EventBus.getDefault().register(this);

        iHxPresenter.loginHx((String) SaveUtils.get(this, SpValue.HX_NAME, ""));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(String action) {

        if ("exit".equals(action)) {
            exit();
        }

    }

    private void initFragment() {

        fragmentManager = getSupportFragmentManager();
//        fragmentManager = getFragmentManager();

        ivone.setSelected(true);
        tvone.setTextColor(getResources().getColor(R.color.home_bottom_select_color));

        chooseFragment(0);

    }

    /**
     * 加载底部按钮
     */
    private void initBottomView() {

        tv_msg_count = (TextView) findViewById(R.id.tv_msg_count);

        this.rlone = (RelativeLayout) findViewById(R.id.rl_one);
        this.tvone = (TextView) findViewById(R.id.tv_one);
        this.ivone = (ImageView) findViewById(R.id.iv_one);

        this.rltwo = (RelativeLayout) findViewById(R.id.rl_two);
        this.tvtwo = (TextView) findViewById(R.id.tv_two);
        this.ivtwo = (ImageView) findViewById(R.id.iv_two);

        this.rlthree = (RelativeLayout) findViewById(R.id.rl_three);
        this.tvthree = (TextView) findViewById(R.id.tv_three);
        this.ivthree = (ImageView) findViewById(R.id.iv_three);

        this.rlfour = (RelativeLayout) findViewById(R.id.rl_four);
        this.tvfour = (TextView) findViewById(R.id.tv_four);
        this.ivfour = (ImageView) findViewById(R.id.iv_four);

        rlone.setOnClickListener(this);
        rltwo.setOnClickListener(this);
        rlthree.setOnClickListener(this);
        rlfour.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rl_one://第一个

                chooseFragment(0);

                //把所有的图标都变灰
                setSelectFalse();

                ivone.setSelected(true);
                tvone.setTextColor(getResources().getColor(R.color.home_bottom_select_color));

                break;

            case R.id.rl_two://第二个

                chooseFragment(1);

                //把所有的图标都变灰
                setSelectFalse();

                ivtwo.setSelected(true);
                tvtwo.setTextColor(getResources().getColor(R.color.home_bottom_select_color));

                break;

            case R.id.rl_three://第三个

                chooseFragment(2);

                //把所有的图标都变灰
                setSelectFalse();

                ivthree.setSelected(true);
                tvthree.setTextColor(getResources().getColor(R.color.home_bottom_select_color));

                break;

            case R.id.rl_four://第四个

                chooseFragment(3);

                //把所有的图标都变灰
                setSelectFalse();

                ivfour.setSelected(true);
                tvfour.setTextColor(getResources().getColor(R.color.home_bottom_select_color));

                break;

        }
    }

    /**
     * 把所有的图标都变灰
     * 字体还原
     */
    private void setSelectFalse() {

        ivone.setSelected(false);
        ivtwo.setSelected(false);
        ivthree.setSelected(false);
        ivfour.setSelected(false);

        tvone.setTextColor(getResources().getColor(R.color.text_color));
        tvtwo.setTextColor(getResources().getColor(R.color.text_color));
        tvthree.setTextColor(getResources().getColor(R.color.text_color));
        tvfour.setTextColor(getResources().getColor(R.color.text_color));

    }

    /**
     * 选择对应的fragment
     *
     * @param index 对应的fragment的角标
     */
    private void chooseFragment(int index) {

        FragmentTransaction transaction = null;
        if (fragmentManager != null)
            transaction = fragmentManager.beginTransaction();

        // 先移除掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
//        removeFragments(transaction);

        if (transaction == null)
            return;

        switch (index) {

            case 0: //

                if (fragmentOne == null)
                    fragmentOne = new FragmentOne();

                if (!fragmentOne.isAdded())
                    transaction.replace(R.id.fl_base, fragmentOne, "ZERO");
//                else
//                    fragmentOne.reBottonLocation();

                break;

            case 1: //

                if (fragmentTwo == null)
                    fragmentTwo = new FragmentTwo();

                if (!fragmentTwo.isAdded())
                    transaction.replace(R.id.fl_base, fragmentTwo, "ONE");

                break;

            case 2:

                if (fragmentThree == null)
                    fragmentThree = new FragmentThree();

                if (!fragmentThree.isAdded())
                    transaction.replace(R.id.fl_base, fragmentThree, "TWO");

                break;


            case 3://

                if (fragmentFour == null)
                    fragmentFour = new FragmentFour();

                if (!fragmentFour.isAdded())
                    transaction.replace(R.id.fl_base, fragmentFour, "THREE");

                break;
        }

        transaction.commit();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case REQUEST_PERMISSION_LOCATION_CODE://定位权限

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (fragmentOne != null)
                        fragmentOne.startLocation();

                } else {
                    showErr("没有定位权限");
                }
                break;


            case FragmentFour.REQUEST_PERMISSION_CALL_CODE:

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (fragmentFour != null)
                        fragmentFour.callPhoneNum();

                } else {
                    showErr("没有拨号权限");
                }
                break;

        }

    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        exitApp();
    }

    private long mPressedTime = 0;

    public void exitApp() {

        long mNowTime = System.currentTimeMillis();//获取第一次按键时间

        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差

            showErr("再按一次退出程序");

            mPressedTime = mNowTime;

        } else {//退出程序
            exit();
        }
    }


    private void exit() {
        finish();
        //退出环信登录
        EMClient.getInstance().logout(true);
        System.exit(0);
    }


}
