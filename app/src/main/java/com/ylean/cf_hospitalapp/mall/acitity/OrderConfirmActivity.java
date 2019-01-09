package com.ylean.cf_hospitalapp.mall.acitity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.mall.bean.AddressListEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.List;

/**
 * 确定订单
 * Created by linaidao on 2019/1/7.
 */

public class OrderConfirmActivity extends BaseActivity implements View.OnClickListener {

    private static final int CHOOSE_ADDRESS = 0x1110;
    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.LinearLayout llAdd;
    private android.widget.TextView tvName;
    private android.widget.TextView tvTel;
    private android.widget.RelativeLayout rlAddress;
    private com.facebook.drawee.view.SimpleDraweeView sdvImg;
    private android.widget.TextView tvTitle;
    private android.widget.TextView tvPP;
    private android.widget.TextView tvFreight;
    private android.widget.TextView tvPoints;
    private android.widget.TextView rvPrice;
    private android.widget.TextView tvNext;
    private TextView tvAddressDetail;
    private AddressListEntry.DataBean currentAddress;
    private static final int CHOOSE_RESULT_CODE = 0x1212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_order_confirm);

        initView();
        getAllAddress();

    }

    //查询全部收货地址
    private void getAllAddress() {

        RetrofitHttpUtil.getInstance()
                .allAddress(new BaseNoTObserver<AddressListEntry>() {
                    @Override
                    public void onHandleSuccess(AddressListEntry addressListEntry) {

                        if (addressListEntry == null || addressListEntry.getData() == null
                                || addressListEntry.getData().size() < 1)
                            return;

                        List<AddressListEntry.DataBean> data = addressListEntry.getData();

                        boolean hasDefault = false;
                        for (int i = 0; i < data.size(); i++) {

                            if ("1".equals(data.get(i).getIsdefault())) {

                                hasDefault = true;

                                setAddressInfo(data.get(i));
                                break;

                            }

                        }

                        if (!hasDefault && data.size() > 0) {

                            AddressListEntry.DataBean dataBean = data.get(0);
                            setAddressInfo(dataBean);

                        }

                    }

                    @Override
                    public void onHandleError(String message) {
                        showErr(message);
                    }

                }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""));
    }

    private void setAddressInfo(AddressListEntry.DataBean dataBean) {

        rlAddress.setVisibility(View.VISIBLE);
        llAdd.setVisibility(View.GONE);

        currentAddress = dataBean;
        tvName.setText(dataBean.getName());
        tvTel.setText(dataBean.getMobile());
        tvAddressDetail.setText(
                dataBean.getProvincename()
                        + dataBean.getCityname()
                        + dataBean.getAreaname()
                        + dataBean.getAddress());
    }

    private void initView() {
        this.tvNext = (TextView) findViewById(R.id.tvNext);
        this.rvPrice = (TextView) findViewById(R.id.rvPrice);
        this.tvPoints = (TextView) findViewById(R.id.tvPoints);
        this.tvFreight = (TextView) findViewById(R.id.tvFreight);
        this.tvPP = (TextView) findViewById(R.id.tvPP);
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.sdvImg = (SimpleDraweeView) findViewById(R.id.sdvImg);
        this.rlAddress = (RelativeLayout) findViewById(R.id.rlAddress);
        this.tvTel = (TextView) findViewById(R.id.tvTel);
        this.tvName = (TextView) findViewById(R.id.tvName);
        this.llAdd = (LinearLayout) findViewById(R.id.llAdd);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tvAddressDetail = findViewById(R.id.tvAddressDetail);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rlAddress.setVisibility(View.GONE);

        llAdd.setOnClickListener(this);
        rlAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.llAdd://添加收货地址
            case R.id.rlAddress://添加收货地址

                Intent m = new Intent(this, ChooseAddressActivity.class);
                startActivityForResult(m, CHOOSE_ADDRESS);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case CHOOSE_ADDRESS:

                if (CHOOSE_RESULT_CODE == resultCode && data != null && data.getParcelableExtra("addressInfo") != null) {
                    AddressListEntry.DataBean addressInfo = data.getParcelableExtra("addressInfo");
                    setAddressInfo(addressInfo);
                }

                break;

        }
    }
}
