package com.ylean.cf_hospitalapp.mall.acitity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.comm.pres.IGoodsInfoPres;
import com.ylean.cf_hospitalapp.comm.view.IGoodsinfoView;
import com.ylean.cf_hospitalapp.mall.GoodsStatus;
import com.ylean.cf_hospitalapp.mall.bean.GoodsInfoEntry;
import com.ylean.cf_hospitalapp.mall.bean.GoodsOrderInfoEntry;
import com.ylean.cf_hospitalapp.mall.bean.MallOrderEntry;
import com.ylean.cf_hospitalapp.mall.pres.IOrderInfoPres;
import com.ylean.cf_hospitalapp.mall.view.IOrderInfoView;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.utils.CommonUtils;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 订单详情页面
 * Created by linaidao on 2019/1/23.
 */

public class OrderDetailActivity extends BaseActivity implements IOrderInfoView, View.OnClickListener, IGoodsinfoView {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.TextView tvStatus;
    private android.widget.TextView tvservicecode;
    private android.widget.LinearLayout llservicecode;
    private com.facebook.drawee.view.SimpleDraweeView sdvImg;
    private android.widget.TextView tvTitle;
    private android.widget.TextView tvPP;
    private android.widget.TextView tvcode;
    private android.widget.TextView tvpaystatus;
    private android.widget.TextView tvprice;
    private android.widget.LinearLayout llneedpay;
    private android.widget.TextView tvtime;
    private android.widget.LinearLayout llpaytime;
    private android.widget.TextView tvcancle;
    private android.widget.TextView tvNext;
    private android.widget.LinearLayout rlBottom;
    private ImageView i1;
//    private MallOrderEntry.DataBean orderInfo;

    private String orderId;

    private IOrderInfoPres iOrderInfoPres = new IOrderInfoPres(this);
    private IGoodsInfoPres iDeletePres = new IGoodsInfoPres(this);

    private RelativeLayout rlAddress;
    private TextView tvcopy;
    private TextView tvName;
    private TextView tvTel;
    private TextView tvAddressDetail;
    private RelativeLayout rlfreight;
    private TextView tvcompany;
    private TextView tvfreightcode;
    private TextView tvmiddle;

    private GoodsOrderInfoEntry.DataBean goodsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_order_detail);
        orderId = getIntent().getStringExtra("orderId");
        init();

        iOrderInfoPres.orderInfo((String) SaveUtils.get(this, SpValue.TOKEN, ""), orderId);
    }

    private void init() {

        rlBottom = (LinearLayout) findViewById(R.id.rlBottom);
        tvNext = (TextView) findViewById(R.id.tvNext);
        tvcancle = (TextView) findViewById(R.id.tvcancle);
        llpaytime = (LinearLayout) findViewById(R.id.llpaytime);
        tvtime = (TextView) findViewById(R.id.tvtime);
        this.llneedpay = (LinearLayout) findViewById(R.id.llneedpay);
        this.tvprice = (TextView) findViewById(R.id.tvprice);
        this.tvpaystatus = (TextView) findViewById(R.id.tvpaystatus);
        this.tvcode = (TextView) findViewById(R.id.tvcode);
        this.tvPP = (TextView) findViewById(R.id.tvPP);
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.sdvImg = (SimpleDraweeView) findViewById(R.id.sdvImg);
        this.tvservicecode = (TextView) findViewById(R.id.tvservicecode);
        this.tvStatus = (TextView) findViewById(R.id.tvStatus);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);
        i1 = findViewById(R.id.i1);

        rlfreight = findViewById(R.id.rlfreight);

        //物流信息
        tvcompany = findViewById(R.id.tvcompany);
        tvfreightcode = findViewById(R.id.tvfreightcode);

        tvName = findViewById(R.id.tvName);
        tvTel = findViewById(R.id.tvTel);
        tvAddressDetail = findViewById(R.id.tvAddressDetail);

        tvmiddle = findViewById(R.id.tvmiddle);
        tvcopy = findViewById(R.id.tvcopy);
        rlAddress = findViewById(R.id.rlAddress);
        llservicecode = findViewById(R.id.llservicecode);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvcancle.setOnClickListener(this);
        tvmiddle.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        rlfreight.setOnClickListener(this);
    }

    @Override
    public void setOrderDetail(GoodsOrderInfoEntry.DataBean data) {

        goodsInfo = data;
        sdvImg.setImageURI(Uri.parse(ApiService.WEB_ROOT + data.getSkuimg()));
        tvTitle.setText(data.getSkuname());
        tvPP.setText(data.getPoints() + "积分+" + data.getSkuprice() + "元");
        tvprice.setText(data.getActualPay() + "元");
        tvtime.setText(data.getPaydate());
        tvcode.setText(data.getCode());

        switch (data.getOrdertype()) {
            case "1"://实物订单
                rlAddress.setVisibility(View.VISIBLE);
                llservicecode.setVisibility(View.GONE);

                tvName.setText(data.getConsignee());
                tvTel.setText(data.getTelphone());
                tvAddressDetail.setText(data.getAddress());

                //物流公司名称
                tvcompany.setText(data.getLogisticname());
                //物流单号
                tvfreightcode.setText(data.getLogisiticcode());

                break;
            case "2"://服务订单
                rlAddress.setVisibility(View.GONE);
                llservicecode.setVisibility(View.VISIBLE);

                tvservicecode.setText(data.getCode());//服务码
                tvcopy.setOnClickListener(this);
                break;

        }

        switch (data.getStatus()) {

            case GoodsStatus.WAIT_PAY://WAIT_PAY = "0";//待付款
                //去支付
                tvStatus.setText("待付款");
                tvpaystatus.setText("未支付");
                rlfreight.setVisibility(View.INVISIBLE);//运单信息
                i1.setImageResource(R.mipmap.ic_wait_pay);

                tvNext.setVisibility(View.VISIBLE);
                tvcancle.setVisibility(View.VISIBLE);
                tvmiddle.setVisibility(View.GONE);
                tvNext.setText("去支付");
                tvcancle.setText("取消订单");

                break;

            case GoodsStatus.WAIT_SEND://WAIT_SEND = "1";//待发货(
                tvStatus.setText("待发货");
                tvpaystatus.setText("已支付" + ("6".equals(data.getPaytype()) ? "  支付宝" : " 微信"));

                rlfreight.setVisibility(View.INVISIBLE);
                i1.setImageResource(R.mipmap.ic_wait_pay);

                rlBottom.setVisibility(View.INVISIBLE);

                break;

            case GoodsStatus.WAIT_RECEIVE://WAIT_RECEIVE = "2";//待收货
                tvStatus.setText("待收货");
                tvpaystatus.setText("已支付" + ("6".equals(data.getPaytype()) ? "  支付宝" : " 微信"));

                i1.setImageResource(R.mipmap.ic_wait_pay);

                tvmiddle.setVisibility(View.VISIBLE);
                tvNext.setVisibility(View.VISIBLE);
                tvcancle.setVisibility(View.GONE);

                tvNext.setText("确认收货");
                tvmiddle.setText("查看物流");

                rlfreight.setVisibility(View.VISIBLE);

                break;

            case GoodsStatus.RECEIVE://RECEIVE = "3";//已确认收货
                tvStatus.setText("已确认收货");
                tvpaystatus.setText("已支付" + ("6".equals(data.getPaytype()) ? "  支付宝" : " 微信"));

                i1.setImageResource(R.mipmap.ic_wait_pay);

                rlfreight.setVisibility(View.VISIBLE);

                tvcancle.setVisibility(View.VISIBLE);
                tvNext.setVisibility(View.VISIBLE);
                tvmiddle.setVisibility(View.GONE);
                tvcancle.setText("申请售后");
                tvNext.setText("评价");

                break;

            case GoodsStatus.WAIT_USE://WAIT_USE = "10";//待使用
                tvStatus.setText("待使用");
                tvpaystatus.setText("已支付" + ("6".equals(data.getPaytype()) ? "  支付宝" : " 微信"));

                rlfreight.setVisibility(View.INVISIBLE);
                i1.setImageResource(R.mipmap.ic_wait_pay);

                tvcancle.setVisibility(View.GONE);
                tvmiddle.setVisibility(View.GONE);
                tvNext.setText("使用");

                break;

            case GoodsStatus.CANCLE://CANCLE = "4";//已取消
                tvStatus.setText("已取消");
                tvpaystatus.setText("已取消");
                rlfreight.setVisibility(View.INVISIBLE);

                i1.setImageResource(R.mipmap.ic_order_cancled);

                tvNext.setVisibility(View.GONE);
                tvcancle.setVisibility(View.VISIBLE);
                tvmiddle.setVisibility(View.GONE);
                tvcancle.setText("删除订单");

                break;

            case GoodsStatus.REBACKING://REBACKING = "5";//退货中
                tvStatus.setText("退货中");
                tvpaystatus.setText("退货中");
                i1.setImageResource(R.mipmap.ic_order_cancled);

                rlBottom.setVisibility(View.INVISIBLE);

                break;

            case GoodsStatus.BACK_SUCCESS:// BACK_SUCCESS = "6";//已退款
                tvStatus.setText("已退款");
                tvpaystatus.setText("已退款");
                rlfreight.setVisibility(View.INVISIBLE);
                i1.setImageResource(R.mipmap.ic_refund);

                tvNext.setVisibility(View.GONE);
                tvcancle.setVisibility(View.VISIBLE);
                tvmiddle.setVisibility(View.GONE);
                tvcancle.setText("删除订单");

                break;

            case GoodsStatus.REBACK_FAIL://REBACK_FAIL = "7";//退款不通过
                tvStatus.setText("退款不通过");
                tvpaystatus.setText("已支付" + ("6".equals(data.getPaytype()) ? "  支付宝" : " 微信"));

                rlfreight.setVisibility(View.INVISIBLE);
                i1.setImageResource(R.mipmap.ic_order_cancled);

                tvNext.setVisibility(View.GONE);
                tvcancle.setVisibility(View.VISIBLE);
                tvmiddle.setVisibility(View.GONE);
                tvcancle.setText("删除订单");

                break;

            case GoodsStatus.EXCHANGEING:// EXCHANGEING = "11";//换货中
                tvStatus.setText("换货中");
                tvpaystatus.setText("已支付" + ("6".equals(data.getPaytype()) ? "  支付宝" : " 微信"));

                i1.setImageResource(R.mipmap.ic_refund);

                rlBottom.setVisibility(View.INVISIBLE);

                break;
            case GoodsStatus.ALREADY_EXCHANGING://ALREADY_EXCHANGING = "12";//已换货
                tvStatus.setText("已换货");
                tvpaystatus.setText("已支付" + ("6".equals(data.getPaytype()) ? "  支付宝" : " 微信"));

                i1.setImageResource(R.mipmap.ic_refund);

                tvNext.setVisibility(View.GONE);
                tvcancle.setVisibility(View.VISIBLE);
                tvmiddle.setVisibility(View.GONE);
                tvcancle.setText("删除订单");

                break;
            case GoodsStatus.EXCHANGE_RESUFED://EXCHANGE_RESUFED = "13";//换货不通过
                tvStatus.setText("换货不通过");
                tvpaystatus.setText("已支付" + ("6".equals(data.getPaytype()) ? "  支付宝" : " 微信"));

                i1.setImageResource(R.mipmap.ic_order_cancled);

                tvNext.setVisibility(View.GONE);
                tvcancle.setVisibility(View.VISIBLE);
                tvmiddle.setVisibility(View.GONE);
                tvcancle.setText("删除订单");
                break;

            case GoodsStatus.DONE://DONE = "8";//已完成
                tvStatus.setText("已完成");
                tvpaystatus.setText("已支付" + ("6".equals(data.getPaytype()) ? "  支付宝" : " 微信"));

                i1.setImageResource(R.mipmap.ic_order_finish);

                tvcancle.setText("删除订单");
                tvmiddle.setText("申请售后");
                tvNext.setText("1".equals(data.getIscomment()) ? "已评价" : "评价");

                break;

        }

    }

    @Override
    public void cancleOrderSuccess() {
        //取消订单成功
        finish();
    }

    @Override
    public void useServiceSuccess() {
        //使用服务订单成功
        finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvcopy://复制服务码

                CommonUtils.copy2clipboard(this, tvservicecode.getText().toString());
                showErr("复制成功");

                break;

            case R.id.tvmiddle://

                switch (goodsInfo.getStatus()) {

                    case GoodsStatus.DONE://DONE = "8";//已完成
                        //申请退货
                        refund();
                        break;

                    case GoodsStatus.WAIT_RECEIVE://WAIT_RECEIVE = "2";//待收货
                        //查看物流
                        lookFreight();

                        break;
                }

                break;

            case R.id.tvcancle://

                switch (goodsInfo.getStatus()) {

                    case GoodsStatus.WAIT_PAY://WAIT_PAY = "0";//待付款
                        //取消订单
                        cancleAction();
                        break;

                    case GoodsStatus.RECEIVE://RECEIVE = "3";//已确认收货
                        //申请售后
                        refund();
                        break;

                    case GoodsStatus.DONE://DONE = "8";//已完成
                    case GoodsStatus.BACK_SUCCESS:// BACK_SUCCESS = "6";//已退款
                    case GoodsStatus.REBACK_FAIL://REBACK_FAIL = "7";//退款不通过
                    case GoodsStatus.CANCLE://CANCLE = "4";//已取消
                    case GoodsStatus.ALREADY_EXCHANGING://ALREADY_EXCHANGING = "12";//已换货
                    case GoodsStatus.EXCHANGE_RESUFED://EXCHANGE_RESUFED = "13";//换货不通过
                        //删除订单
                        deleteOrder();
                        break;

                }

                break;

            case R.id.tvNext:

                switch (goodsInfo.getStatus()) {

                    case GoodsStatus.WAIT_PAY://WAIT_PAY = "0";//待付款
                        //去支付
                        go2pay();
                        break;

                    case GoodsStatus.WAIT_RECEIVE://WAIT_RECEIVE = "2";//待收货

                        //确认收货
                        confirmGetGoods();

                        break;
                    case GoodsStatus.DONE://DONE = "8";//已完成
                    case GoodsStatus.RECEIVE://RECEIVE = "3";//已确认收货
                        //评价
                        command();

                        break;

                    case GoodsStatus.WAIT_USE://WAIT_USE = "10";//待使用
                        //去使用
                        go2use();
                        break;
                }

                break;

            case R.id.rlfreight://查看物流
                lookFreight();
                break;

        }
    }

    //使用订单
    private void go2use() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

        builder.setTitle("提示").setMessage("确认使用该订单吗").setPositiveButton("确认使用", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //使用服务订单
                iOrderInfoPres.useServiceOrder((String) SaveUtils.get(OrderDetailActivity.this, SpValue.TOKEN, ""), orderId);

            }
        }).setNegativeButton("取消", null).show();

    }

    //查看物流
    private void lookFreight() {

        Intent m = new Intent(this, LogisticActivity.class);
        m.putExtra("orderId", orderId);
        startActivity(m);

    }

    //确认收货
    private void confirmGetGoods() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

        builder.setTitle("提示").setMessage("确认收货").setPositiveButton("已收货", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //确认收货
                iDeletePres.confirmReceive((String) SaveUtils.get(OrderDetailActivity.this, SpValue.TOKEN, ""), orderId);

            }
        }).setNegativeButton("取消", null).show();
    }

    //申请售后
    private void refund() {
        if (goodsInfo == null) {
            showErr("数据错误");
            return;
        }
        //申请售后
        Intent m = new Intent(this, RefundActivity.class);
        MallOrderEntry.DataBean orderInfo = new MallOrderEntry.DataBean();
        orderInfo.setSkuimg(goodsInfo.getSkuimg());
        orderInfo.setSkuname(goodsInfo.getSkuname());
        orderInfo.setPoints(goodsInfo.getPoints());
        orderInfo.setPrice(goodsInfo.getPrice());
        orderInfo.setOrderid(goodsInfo.getOrderid());
        orderInfo.setOrdertype(goodsInfo.getOrdertype());
        m.putExtra("orderInfo", orderInfo);
        startActivity(m);
    }

    //评价商品
    private void command() {
        Intent m = new Intent(this, GoodsCommandActivity.class);
        m.putExtra("goodsInfo", goodsInfo);
        startActivityForResult(m, 0x0041);
    }

    //去支付
    private void go2pay() {
        if (goodsInfo == null) {
            showErr("数据错误");
            return;
        }

        GoodsInfoEntry.DataBean info = new GoodsInfoEntry.DataBean();
        info.setType(goodsInfo.getOrdertype());
        info.setPrice(goodsInfo.getActualPay());

        Intent mIntent = new Intent(this, GoodsPayActivity.class);
        mIntent.putExtra("goodsInfo", info);
        mIntent.putExtra("orderCode", goodsInfo.getCode());
        mIntent.putExtra("freightPrice", 0.00d);//运费
        startActivity(mIntent);
    }

    //删除订单
    private void deleteOrder() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

        builder.setTitle("提示").setMessage("您确定要删除该订单吗").setPositiveButton("删除订单", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (goodsInfo == null) {
                    showErr("数据错误");
                    return;
                }

                //删除订单
                iDeletePres.deleteGoodsOrder((String) SaveUtils.get(OrderDetailActivity.this, SpValue.TOKEN, ""), orderId);
            }
        }).setNegativeButton("取消", null).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 0x0041:

                if (resultCode == 0x003) {
                    goodsInfo.setIscomment("1");
                    tvNext.setText("已评价");
                }

                break;

        }
    }

    //取消订单
    private void cancleAction() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom);

        builder.setTitle("提示").setMessage("您确定要取消订单吗").setPositiveButton("取消订单", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (goodsInfo == null) {
                    showErr("数据错误");
                    return;
                }

                iOrderInfoPres.cancleGoodsOrder((String) SaveUtils.get(OrderDetailActivity.this, SpValue.TOKEN, ""), orderId
                        , goodsInfo.getStatus());
            }
        }).setNegativeButton("保留", null).show();
    }

    @Override
    public void deleteSuccess() {
        //删除成功
        finish();
    }

    @Override
    public void confirmSuccess() {
        //确认收货成功
        finish();
    }
}
