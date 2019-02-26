package com.ylean.cf_hospitalapp.hospital.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.Presenter.ICollectionPres;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.base.view.ICollectionView;
import com.ylean.cf_hospitalapp.comm.activity.ReplyActivity;
import com.ylean.cf_hospitalapp.comm.pres.IGoodPres;
import com.ylean.cf_hospitalapp.comm.view.IGoodView;
import com.ylean.cf_hospitalapp.doctor.adapter.CommentCommAdapter;
import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.home.activity.CommentActivity;
import com.ylean.cf_hospitalapp.hospital.adapter.HospitalDepartmentAdapter;
import com.ylean.cf_hospitalapp.hospital.adapter.NearbyAdapter;
import com.ylean.cf_hospitalapp.hospital.bean.HospDepartListEntry;
import com.ylean.cf_hospitalapp.hospital.presenter.IHospitalDetailPres;
import com.ylean.cf_hospitalapp.hospital.view.IHospitalDetailView;
import com.ylean.cf_hospitalapp.my.activity.WebviewActivity;
import com.ylean.cf_hospitalapp.net.ApiService;
import com.ylean.cf_hospitalapp.register.activity.DepartmentChooseActivity;
import com.ylean.cf_hospitalapp.register.bean.HospitalInfoEntry;
import com.ylean.cf_hospitalapp.register.bean.HospitalListEntry;
import com.ylean.cf_hospitalapp.utils.CommonUtils;
import com.ylean.cf_hospitalapp.utils.LngLat;
import com.ylean.cf_hospitalapp.utils.OpenLocalMapUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.ActionSheetDialog;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;
import com.ylean.cf_hospitalapp.widget.swipe.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 医院详情
 * Created by linaidao on 2019/1/4.
 */
public class HospitalDetailActivity extends BaseActivity implements IHospitalDetailView, IGoodView, View.OnClickListener, ICollectionView {

    private android.widget.TextView tvContent;
    private HospitalListEntry.DataBean hospitalInfo;
    private TextView tel;
    private TextView tvAddress;
    private TextView tvHospitalName;
    private SimpleDraweeView sdvPic;

    private android.support.v7.widget.RecyclerView departmentRecyclerview;
    private android.support.v7.widget.RecyclerView arroundRecyclerview;
    private android.support.v7.widget.RecyclerView commentRecyclerview;

    private List<HospDepartListEntry.DataBean> departmentList = new ArrayList<>();
    private HospitalDepartmentAdapter hdAdapter;
    private CommentCommAdapter commAdapter;

    //医院详情p
    private IHospitalDetailPres iHospitalDetailPres = new IHospitalDetailPres(this);
    //关注p
    private ICollectionPres iCollectionPres = new ICollectionPres(this);

    //点赞
    private IGoodPres iGoodPres = new IGoodPres(this);
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hospitalInfo = getIntent().getParcelableExtra("hospitalInfo");
        setContentView(R.layout.act_hospital_detail);

        initView();
        if (hospitalInfo != null)
            iHospitalDetailPres.setHospitalid(hospitalInfo.getHospitalid());

        //医院详情
        iHospitalDetailPres.hospitalDetail();
        //医院科室列表
        iHospitalDetailPres.hospitalDeartmentList();
        //医院评论列表
        iHospitalDetailPres.hospitalCommentList((String) SaveUtils.get(this, SpValue.TOKEN, ""));

    }

    @Override
    public void setHospitalInfo(HospitalInfoEntry hospitalInfoEntry) {

        HospitalInfoEntry.DataBean data = hospitalInfoEntry.getData();

        tvHospitalName.setText(data.getHospitalname());
        tvAddress.setText(data.getAddress());

        sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + data.getImgurl()));
        tel.setText(data.getSupportel());

        String content = "<font color=\"#767676\">" + data.getIntroduction() + "</font>"
                + "<font color=\"#33a9fa\">" + "[详细介绍]" + "</font>";
        tvContent.setText(Html.fromHtml(content));

    }

    //科室列表
    @Override
    public void setDepartmentInfo(List<HospDepartListEntry.DataBean> data) {

        departmentList.addAll(data);
        if (hdAdapter != null)
            hdAdapter.notifyDataSetChanged();

    }

    //评论信息
    @Override
    public void setCommentInfo(List<CommComListEntry.DataBean> data) {

        commAdapter = new CommentCommAdapter(this, data);
        commentRecyclerview.setAdapter(commAdapter);

    }

    private void initView() {

        tel = (TextView) findViewById(R.id.tel);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvHospitalName = (TextView) findViewById(R.id.tvHospitalName);
        sdvPic = (SimpleDraweeView) findViewById(R.id.sdvPic);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);

        LinearLayout llcomment = (LinearLayout) findViewById(R.id.llcomment);
        TextView viewRegister = (TextView) findViewById(R.id.viewRegister);
        this.commentRecyclerview = (RecyclerView) findViewById(R.id.commentRecyclerview);
        this.arroundRecyclerview = (RecyclerView) findViewById(R.id.arroundRecyclerview);
        this.departmentRecyclerview = (RecyclerView) findViewById(R.id.departmentRecyclerview);
        this.tvContent = (TextView) findViewById(R.id.tvContent);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (hospitalInfo == null)
            return;

        sdvPic.setImageURI(Uri.parse(ApiService.WEB_ROOT + hospitalInfo.getImgurl()));
        tvHospitalName.setText(hospitalInfo.getHospitalname());
        tvAddress.setText(hospitalInfo.getAddress());
        tel.setText(hospitalInfo.getSupportel());

        initArroundRecyclerview();
        sickRecycler();
        initCommentRecyclerView();

        viewRegister.setOnClickListener(this);
        llcomment.setOnClickListener(this);
        tvContent.setOnClickListener(this);

    }

    private void initCommentRecyclerView() {

//        commentRecyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        commentRecyclerview.setLayoutManager(linearLayoutManager);
        commentRecyclerview.setItemAnimator(new DefaultItemAnimator());
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_recyclerview_item_gray));
        commentRecyclerview.addItemDecoration(divider);

        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        commentRecyclerview.setHasFixedSize(true);
        commentRecyclerview.setNestedScrollingEnabled(false);
    }

    private void initArroundRecyclerview() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        arroundRecyclerview.setLayoutManager(gridLayoutManager);
        arroundRecyclerview.setItemAnimator(new DefaultItemAnimator());
//        //添加自定义分割线
//        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_home_divider));
//        arroundRecyclerview.addItemDecoration(divider);

        gridLayoutManager.setSmoothScrollbarEnabled(true);
        gridLayoutManager.setAutoMeasureEnabled(true);
        arroundRecyclerview.setHasFixedSize(true);
        arroundRecyclerview.setNestedScrollingEnabled(false);

        NearbyAdapter nearbyAdapter = new NearbyAdapter(this);
        arroundRecyclerview.setAdapter(nearbyAdapter);

        arroundRecyclerview.addOnItemTouchListener(new OnItemClickListener(arroundRecyclerview) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                switch (position) {

                    case 0://免费接送

                        mIntent = new Intent(HospitalDetailActivity.this, TransferActivity.class);
                        if (hospitalInfo != null)
                            mIntent.putExtra("hospitalId", hospitalInfo.getHospitalid());
                        startActivity(mIntent);

                        break;
                    case 1://来院路线
                        roteNavi("导航");
                        break;
                    case 2://院内导航 TODO


                        break;
                    case 3://周边停车
                        roteNavi("停车场");
                        break;
                    case 4://附近酒店
                        roteNavi("酒店");
                        break;
                    case 5://周边景点
                        roteNavi("景点");
                        break;
                }

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    //来院路线
    private void roteNavi(String type) {

        if (hospitalInfo == null) {
            showErr("数据错误");
            return;
        }

        new ActionSheetDialog(this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("高德地图", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                                if (!CommonUtils.isAvilible(HospitalDetailActivity.this, "com.autonavi.minimap")) {
                                    showErr("请先安装高德地图");
                                    return;
                                }

                                LngLat lngLat = new LngLat(Double.parseDouble(hospitalInfo.getLongitude()), Double.parseDouble(hospitalInfo.getLatitude()));
                                LngLat bdDecrypt = CommonUtils.bd_decrypt(lngLat);

                                switch (type) {

                                    case "导航":
                                        openGaoDeMap(bdDecrypt.getLantitude(), bdDecrypt.getLongitude(), hospitalInfo.getHospitalname());
                                        break;

                                    default:
                                        Intent intent = new Intent();
                                        intent.setAction(Intent.ACTION_VIEW);
                                        intent.addCategory(Intent.CATEGORY_DEFAULT);

                                        //将功能Scheme以URI的方式传入data
                                        Uri uri = Uri.parse("androidamap://arroundpoi?sourceApplication=" + getResources().getString(R.string.app_name)
                                                + "&keywords=" + type + "&lat=" + bdDecrypt.getLantitude() + "&lon=" + bdDecrypt.getLongitude() + "&dev=0");
                                        intent.setData(uri);

                                        //启动该页面即可
                                        startActivity(intent);
                                        break;
                                }


                            }

                        })
                .addSheetItem("百度地图", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                                if (!CommonUtils.isAvilible(HospitalDetailActivity.this, "com.baidu.BaiduMap")) {
                                    showErr("请先安装百度地图");
                                    return;
                                }

                                switch (type) {
                                    case "导航":

                                        openBaiduMap(
                                                Double.parseDouble((String) SaveUtils.get(HospitalDetailActivity.this, SpValue.LAT, ""))
                                                , Double.parseDouble((String) SaveUtils.get(HospitalDetailActivity.this, SpValue.LON, ""))
                                                , "", Double.parseDouble(hospitalInfo.getLatitude()), Double.parseDouble(hospitalInfo.getLongitude())
                                                , hospitalInfo.getHospitalname(), (String) SaveUtils.get(HospitalDetailActivity.this, SpValue.CITY, ""));

                                        break;

                                    default:
                                        Intent i1 = new Intent();
                                        i1.setData(Uri.parse("baidumap://map/place/search?query=" + type
                                                + "&region=&location=" + hospitalInfo.getLatitude()
                                                + "," + hospitalInfo.getLongitude() + "&radius=5000&bounds=&src=andr.baidu.openAPIdemo"));
                                        startActivity(i1);

                                        break;
                                }


                            }
                        }).show();

    }

    /**
     * 打开百度地图
     *
     * @param slat  开始地点 维度
     * @param slon  开始地点 经度
     * @param sname 开始地点 名字
     * @param dlat  终点地点 维度
     * @param dlon  终点地点 经度
     * @param dname 终点名字
     * @param city  所在城市- 动态获取 （例如：北京市）
     * @author jack
     * created at 2017/8/2 15:01
     */
    private void openBaiduMap(double slat, double slon, String sname,
                              double dlat, double dlon, String dname, String city) {
        try {
            String uri = OpenLocalMapUtil.getBaiduMapUri(String.valueOf(slat), String.valueOf(slon), sname,
                    String.valueOf(dlat), String.valueOf(dlon), dname, city, "");
            Intent intent = Intent.parseUri(uri, 0);
            startActivity(intent); //启动调用
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 打开高德地图
     *
     * @author jack
     * created at 2017/8/2 15:01
     */
    private void openGaoDeMap(double dlat, double dlon, String dname) {
        try {
            // APP_NAME  自己应用的名字
            String uri = OpenLocalMapUtil.getGdMapUri(getResources().getString(R.string.app_name), "", "", ""
                    , String.valueOf(dlat), String.valueOf(dlon), dname);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.autonavi.minimap");
            intent.setData(Uri.parse(uri));
            startActivity(intent); //启动调用
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void sickRecycler() {

        GridLayoutManager doctorLayoutManager = new GridLayoutManager(this, 3);
        departmentRecyclerview.setLayoutManager(doctorLayoutManager);
        doctorLayoutManager.setSmoothScrollbarEnabled(true);
        doctorLayoutManager.setAutoMeasureEnabled(true);

        departmentRecyclerview.setHasFixedSize(true);
        departmentRecyclerview.setNestedScrollingEnabled(false);
        hdAdapter = new HospitalDepartmentAdapter(this, departmentList);
        departmentRecyclerview.setAdapter(hdAdapter);

        departmentRecyclerview.addOnItemTouchListener(new OnItemClickListener(departmentRecyclerview) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {

                if (hospitalInfo == null) {
                    showErr("数据错误");
                    return;
                }

                Intent m = new Intent(HospitalDetailActivity.this, WebviewActivity.class);
                String url = ApiService.WEB_ROOT + ApiService.DEPARTMEN_INTRODUCE + "?id=" + departmentList.get(position).getDepartmentid()
                        + "&hospitalid=" + hospitalInfo.getHospitalid();
                Logger.d("url:::" + url);
                m.putExtra("url", url);
                m.putExtra("title", departmentList.get(position).getName());
                startActivity(m);

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    //当前点击病友信息
    private CommComListEntry.DataBean commentInfo;


    //点击关注
    public void attentionAction(CommComListEntry.DataBean dataBean) {

        commentInfo = dataBean;
        iCollectionPres.setId(dataBean.getEvaluateid());
        if ("1".equals(dataBean.getIsfollow())) {
            //已关注，点击取消关注
            iCollectionPres.removeCollection((String) SaveUtils.get(this, SpValue.TOKEN, ""), "6");

        } else {
            //未关注， 点击关注
            iCollectionPres.addCollection((String) SaveUtils.get(this, SpValue.TOKEN, ""), "6");

        }

    }

    //点赞
    public void goodAction(CommComListEntry.DataBean dataBean) {

        commentInfo = dataBean;

        iGoodPres.setToken((String) SaveUtils.get(this, SpValue.TOKEN, ""));

        //type 资讯(1),文章(2),直播(3),讲堂(4),帖子(5),评论(6);
        iGoodPres.setType("6");//点赞类型

        iGoodPres.setRelateid(dataBean.getEvaluateid());
        if ("1".equals(dataBean.getIsdz())) {
            //已经点赞， 取消点赞
            iGoodPres.removeGood();
        } else {
            //未点赞， 点击点赞
            iGoodPres.good();
        }

    }

    @Override
    public void goodSuccess(String type) {

        if (commentInfo != null)
            commentInfo.setIsdz("1");

        if (commAdapter != null)
            commAdapter.notifyDataSetChanged();

    }

    @Override
    public void removeSuccess(String type) {

        if (commentInfo != null)
            commentInfo.setIsdz("0");

        if (commAdapter != null)
            commAdapter.notifyDataSetChanged();
    }


    private CommComListEntry.DataBean replyBean;

    //回复 评论信息
    public void replyAction(CommComListEntry.DataBean dataBean) {
        replyBean = dataBean;
        Intent m = new Intent(this, ReplyActivity.class);
        m.putExtra("id", dataBean.getId());
        m.putExtra("commentBean", dataBean);
        startActivityForResult(m, 0x998);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0x998 && resultCode == 0x999) {
            //回复
            if (data != null) {

                CommComListEntry.DataBean.ReplyBean resultBean = data.getParcelableExtra("replyBean");

                if (resultBean != null && replyBean != null) {
                    List<CommComListEntry.DataBean.ReplyBean> reply = replyBean.getReply();

                    if (reply == null)
                        reply = new ArrayList<>();

                    reply.add(resultBean);

                }

                if (commAdapter != null)
                    commAdapter.notifyDataSetChanged();

            }

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.llcomment://评价

                mIntent = new Intent(this, CommentActivity.class);
                mIntent.putExtra("with_pic", true);

                if (hospitalInfo != null)
                    mIntent.putExtra("id", hospitalInfo.getHospitalid());
                mIntent.putExtra("type", "4");
                mIntent.putExtra("", "");
                startActivity(mIntent);

                break;

            case R.id.viewRegister://挂号

                mIntent = new Intent(this, DepartmentChooseActivity.class);
                mIntent.putExtra("hospitalInfo", hospitalInfo);
                startActivity(mIntent);

                break;

            case R.id.tvContent://医院详情

                mIntent = new Intent(this, WebviewActivity.class);
                mIntent.putExtra("title", "医院详细介绍");
                if (hospitalInfo != null) {
                    String url = ApiService.WEB_ROOT + ApiService.HOSPITAL_DETAIL + "?id=" + hospitalInfo.getHospitalid();
                    mIntent.putExtra("url", url);
                }
                startActivity(mIntent);
                break;
        }
    }

    @Override
    public void collectionSuccess(String type) {
        if (commentInfo != null)
            commentInfo.setIsfollow("1");

        if (commAdapter != null)
            commAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeCollectionSuccess(String type) {
        if (commentInfo != null)
            commentInfo.setIsfollow("0");

        if (commAdapter != null)
            commAdapter.notifyDataSetChanged();
    }
}
