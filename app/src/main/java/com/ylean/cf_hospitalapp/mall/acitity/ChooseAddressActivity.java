package com.ylean.cf_hospitalapp.mall.acitity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.mall.adapter.AddressListAdapter;
import com.ylean.cf_hospitalapp.mall.bean.AddressListEntry;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择收货地址
 * Created by linaidao on 2019/1/7.
 */

public class ChooseAddressActivity extends BaseActivity implements View.OnClickListener {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.support.v7.widget.RecyclerView recyclerView;
    private android.widget.TextView tvNext;
    private List<AddressListEntry.DataBean> addressList = new ArrayList<>();
    private AddressListAdapter addressListAdapter;
    private static final int CHOOSE_RESULT_CODE = 0x1212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_choose_address);

        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();

        getAllAddress();

    }

    //查询全部收货地址
    private void getAllAddress() {

        RetrofitHttpUtil.getInstance()
                .allAddress(new BaseNoTObserver<AddressListEntry>() {
                    @Override
                    public void onHandleSuccess(AddressListEntry addressListEntry) {

                        if (addressListEntry == null || addressListEntry.getData() == null)
                            return;

                        addressList.clear();
                        addressList.addAll(addressListEntry.getData());

                        if (addressListAdapter != null)
                            addressListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onHandleError(String message) {
                        showErr(message);
                    }

                }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""));
    }

    private void initView() {
        this.tvNext = (TextView) findViewById(R.id.tvNext);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_home_divider));
        recyclerView.addItemDecoration(divider);

        addressListAdapter = new AddressListAdapter(this, addressList);
        recyclerView.setAdapter(addressListAdapter);

        tvNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tvNext:

                nextActivity(AddAddressActivity.class);

                break;
        }
    }

    //选择当前地址并返回
    public void chooseCurrentAddress(AddressListEntry.DataBean dataBean) {

        Intent data = new Intent();
        data.putExtra("addressInfo", dataBean);
        setResult(CHOOSE_RESULT_CODE, data);
        finish();

    }

    //吧当前的地址设置为默认地址
    public void setCurrentDefault(AddressListEntry.DataBean dataBean) {
        setDefaultAddress(dataBean);
    }


    //编辑当前地址
    public void editCurrent(AddressListEntry.DataBean dataBean) {
        Intent m = new Intent(this, AddAddressActivity.class);
        m.putExtra("addressInfo", dataBean);
        startActivity(m);
    }

    //删除当前的地址
    public void deleteCurrent(AddressListEntry.DataBean dataBean) {

        RetrofitHttpUtil.getInstance()
                .deleteAddress(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                showErr("删除成功");
                                getAllAddress();
                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                            }

                        }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""), dataBean.getId());

    }

    //设置为默认地址
    private void setDefaultAddress(final AddressListEntry.DataBean address) {

        RetrofitHttpUtil.getInstance()
                .setDefaultAddress(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {

                                for (int i = 0; i < addressList.size(); i++) {
                                    addressList.get(i).setIsdefault("0");
                                }

                                address.setIsdefault("1");
                                if (addressListAdapter != null)
                                    addressListAdapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                            }

                        }, SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, ""), address.getId());
    }

}
