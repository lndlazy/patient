package com.ylean.cf_hospitalapp.mall.acitity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.mall.bean.AddressListEntry;
import com.ylean.cf_hospitalapp.mall.bean.GoodsInfoEntry;
import com.ylean.cf_hospitalapp.mall.pres.IOrderConfirmPer;
import com.ylean.cf_hospitalapp.mall.view.IOrderConfirmView;
import com.ylean.cf_hospitalapp.net.ApiService;
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

public class OrderConfirmActivity extends BaseActivity implements View.OnClickListener, IOrderConfirmView {

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

    private IOrderConfirmPer iOrderConfirmPer = new IOrderConfirmPer(this);
    private String id;

    private double freightPrice = -1;//运费
    private GoodsInfoEntry.DataBean goodsInfo;
    private RelativeLayout rlFreight;
    private String addressId = "";
//    private double goodsPrice = -1;//商品价格

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_order_confirm);

        id = getIntent().getStringExtra("id");
        freightPrice = -1;//运费价格
        initView();
        //商品详情
        iOrderConfirmPer.goodsInfo((String) SaveUtils.get(this, SpValue.TOKEN, ""), id);
        //获取运费
        iOrderConfirmPer.freightInfo((String) SaveUtils.get(this, SpValue.TOKEN, ""));
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

                        //没有默认的收货地址，就取第一个为默认地址
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

        addressId = dataBean.getId();

        rlAddress.setVisibility(View.VISIBLE);
        llAdd.setVisibility(View.GONE);

        currentAddress = dataBean;
        tvName.setText(dataBean.getName());
        tvTel.setText(dataBean.getMobile());
        tvAddressDetail.setText(dataBean.getProvincename() + dataBean.getCityname()
                + dataBean.getAreaname() + dataBean.getAddress());
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

        rlFreight = findViewById(R.id.rlFreight);

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
        tvNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.llAdd://添加收货地址
            case R.id.rlAddress://添加收货地址

                Intent m = new Intent(this, ChooseAddressActivity.class);
                startActivityForResult(m, CHOOSE_ADDRESS);
                break;

            case R.id.tvNext://确认兑换

                //如果是实物，需选择收货地址
                if (TextUtils.isEmpty(addressId) && "1".equals(goodsInfo.getType())) {
                    showErr("请选择收货地址");
                    return;
                }

                String fp = "";
                switch (goodsInfo.getType()) {

                    case "1"://1-实物商品
                        fp = freightPrice + "";
                        break;

                    case "2"://2-服务商品
                        fp = "0";
                        break;

                }

                iOrderConfirmPer.goodsOrder((String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , addressId, goodsInfo.getPrice() + "", fp, goodsInfo.getPoints()
                        , goodsInfo.getId(), goodsInfo.getPrice() + "", goodsInfo.getPoints()
                        , goodsInfo.getType(), "1", "无");

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

    //设置商品详情
    @Override
    public void setGoodsInfo(GoodsInfoEntry.DataBean data) {

//        title;//商品标题
//        price;//价格
//        oldprice; //原价
//        points; //积分
//        subtitle; //副标题
//        imgurl; //图片
//        stock;//库存
//        cid; //分类id
//        type; //商品类型 1-实物商品 2-服务商品
//        description; //商品描述
//        iscollect; //是否收藏 1-收藏 0-未收藏

        goodsInfo = data;

        switch (data.getType()) {

            case "1"://1-实物商品

                getAllAddress();

                break;

            case "2"://2-服务商品

                llAdd.setVisibility(View.GONE);
                rlAddress.setVisibility(View.GONE);
                rlFreight.setVisibility(View.GONE);

                break;

        }

        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + data.getImgurl()));
        tvTitle.setText(data.getTitle());
        tvPP.setText(data.getPoints() + "积分+" + data.getPrice() + "元");

        tvPoints.setText(data.getPoints());
        rvPrice.setText("¥" + data.getPrice());

    }

    /**
     * @param b          是否要运费 免运费false  要运费 true
     * @param freightPr  运费
     * @param goodsPrice 商品价格
     */
    @Override
    public void setFreightInfo(boolean b, double freightPr, double goodsPrice) {

//        this.goodsPrice = goodsPrice;
        tvFreight.setText(b ? (freightPr + "元") : "平台包邮");
        freightPrice = b ? freightPr : 0;

    }

    @Override
    public void orderSuccess(String data) {
        Intent mIntent = new Intent(this, GoodsPayActivity.class);
        mIntent.putExtra("goodsInfo", goodsInfo);
        mIntent.putExtra("freightPrice", freightPrice);
        mIntent.putExtra("orderCode", data);
        startActivity(mIntent);
    }


//    //设置运费详情
//    @Override
//    public void setFreightInfo(FreightPriceEntry.DataBean data) {
//
//        //basefreight; //基本运费
//        basefreight = data.getBasefreight();
//
//        // byfreight; //包邮价
//        byfreight = data.getByfreight();
//
//    }


}
