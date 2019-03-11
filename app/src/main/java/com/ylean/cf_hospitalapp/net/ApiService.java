package com.ylean.cf_hospitalapp.net;


import com.ylean.cf_hospitalapp.base.bean.AddBean;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.comm.bean.ShareTitleBean;
import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.doctor.bean.InquiryListEntry;
import com.ylean.cf_hospitalapp.doctor.bean.VideoListEntry;
import com.ylean.cf_hospitalapp.home.bean.BannerBean;
import com.ylean.cf_hospitalapp.home.bean.LoginEntry;
import com.ylean.cf_hospitalapp.home.bean.SearchListEntry;
import com.ylean.cf_hospitalapp.home.bean.VideoSpeechDetailEntry;
import com.ylean.cf_hospitalapp.hospital.bean.HospDepartListEntry;
import com.ylean.cf_hospitalapp.hospital.bean.ServiceInfoEntry;
import com.ylean.cf_hospitalapp.hx.bean.GroupListEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.AlipayEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.ChatEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.CommentDisBean;
import com.ylean.cf_hospitalapp.inquiry.bean.DataUploadResultEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.DepartmentListEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.DisEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.DoctorLevelListBean;
import com.ylean.cf_hospitalapp.inquiry.bean.DoctorListEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.GradeLevelBean;
import com.ylean.cf_hospitalapp.inquiry.bean.HospitalEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.InquiryIntrBean;
import com.ylean.cf_hospitalapp.inquiry.bean.OrderEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.PeopleEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskDetailEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.PicAskResutEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.RecommendEntry;
import com.ylean.cf_hospitalapp.inquiry.bean.WxPayInfoEntry;
import com.ylean.cf_hospitalapp.login.bean.RegisterResultEntry;
import com.ylean.cf_hospitalapp.login.bean.SplishEntry;
import com.ylean.cf_hospitalapp.mall.bean.FreightPriceEntry;
import com.ylean.cf_hospitalapp.mall.bean.AddressListEntry;
import com.ylean.cf_hospitalapp.mall.bean.GoodsInfoEntry;
import com.ylean.cf_hospitalapp.mall.bean.GoodsOrderInfoEntry;
import com.ylean.cf_hospitalapp.mall.bean.LogisticEntry;
import com.ylean.cf_hospitalapp.mall.bean.MallOrderEntry;
import com.ylean.cf_hospitalapp.mall.bean.RefundAddressEntry;
import com.ylean.cf_hospitalapp.my.bean.AreaEntry;
import com.ylean.cf_hospitalapp.my.bean.BindEntry;
import com.ylean.cf_hospitalapp.my.bean.CityEntry;
import com.ylean.cf_hospitalapp.my.bean.DoctorDetailEntry;
import com.ylean.cf_hospitalapp.my.bean.EarnPointsBean;
import com.ylean.cf_hospitalapp.my.bean.EvalDetailEntry;
import com.ylean.cf_hospitalapp.my.bean.EvalListEntry;
import com.ylean.cf_hospitalapp.my.bean.FamilyDetailEntry;
import com.ylean.cf_hospitalapp.my.bean.GoodsListEntry;
import com.ylean.cf_hospitalapp.my.bean.HelpEntry;
import com.ylean.cf_hospitalapp.my.bean.InviteListEntry;
import com.ylean.cf_hospitalapp.my.bean.MCollectionListEntry;
import com.ylean.cf_hospitalapp.my.bean.MyAskReusltList;
import com.ylean.cf_hospitalapp.my.bean.MyDoctorListEntry;
import com.ylean.cf_hospitalapp.my.bean.MyInfoEntry;
import com.ylean.cf_hospitalapp.my.bean.MyReuqestListEntry;
import com.ylean.cf_hospitalapp.my.bean.NewsListEntry;
import com.ylean.cf_hospitalapp.my.bean.OrderInquiryDetailEntry;
import com.ylean.cf_hospitalapp.my.bean.PointsDetailEntry;
import com.ylean.cf_hospitalapp.my.bean.PointsEntry;
import com.ylean.cf_hospitalapp.my.bean.ProvinceEntry;
import com.ylean.cf_hospitalapp.my.bean.UnreadMsgEntry;
import com.ylean.cf_hospitalapp.my.bean.UpdateResultBean;
import com.ylean.cf_hospitalapp.popular.bean.DiseaseListEntry;
import com.ylean.cf_hospitalapp.popular.bean.ExpertEntry;
import com.ylean.cf_hospitalapp.register.bean.DoctorTypeEntry;
import com.ylean.cf_hospitalapp.register.bean.HospitalInfoEntry;
import com.ylean.cf_hospitalapp.register.bean.HospitalListEntry;
import com.ylean.cf_hospitalapp.register.bean.HospitalTypeEntry;
import com.ylean.cf_hospitalapp.register.bean.NumListEntry;
import com.ylean.cf_hospitalapp.register.bean.OrderInfoEntry;
import com.ylean.cf_hospitalapp.register.bean.RegisterDeartmentListEntry;
import com.ylean.cf_hospitalapp.register.bean.RegisterOrderEntry;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 请求的接口
 * Created by Aaron on 17/2/14.
 */
public interface ApiService {

    //    String WEB_ROOT = "http://cfyy.yl-mall.cn";
    String WEB_ROOT = "http://cfnew.yl-mall.cn";
    //        String WEB_ROOT = "http://yx.yl-mall.cn";
    String WEB_ROOT_ADDRESS = "http://api.map.baidu.com";

    String H5_BASE_WEB = "/api/app/art/getnewsbytype";

    //资讯h5页面
    String H5_NEWS = "/api/app/art/newsinfo";
    //资讯h5分享页面
    String H5_NEWS_SHARE = "/api/app/art/sharenews";
    //文章详情
    String ARTICLE_NEWS = "/api/app/art/articleinfo";
    //文章分享页面
    String ARTICLE_NEWS_SHARE = "/api/app/art/sharearticle";
    //商品详情
    String GOODDS_DETAIL = "/api/app/art/proinfo";

    //疾病详情
    String DISEASE_DETAIL = "/api/app/art/diseaseinfo";

    //会员规则
    String H5_WEB_VIP_ROUE = H5_BASE_WEB + "?ch=1&ctype=888";

    //服务协议
    String SERVICE_XIEYI = H5_BASE_WEB + "?ch=1&ctype=99";

    //邀请好友
    String SERVICE_INVITE = H5_BASE_WEB + "?ch=1&ctype=777";

    String DEPARTMEN_INTRODUCE = "/api/app/hospital/departdetail";//科室介绍

    String HOSPITAL_DETAIL = "/api/app/hospital/hospitaldetail";//医院详情


    //根据城市名获取地理位置
    @GET("/geocoder")
    Observable<AddBean> recodeAddress(@Query("address") String address
            , @Query("output") String output, @Query("key") String key, @Query("city") String city);

    //登录
    @FormUrlEncoded
    @POST("/api/app/patient/login")
    Observable<LoginEntry> loginIn(@Field("ch") String ch, @Field("name") String name, @Field("pwd") String pwd);


//    //登录
//    @GET("/api/app/patient/login")
//    Observable<LoginEntry> loginIn(@Query("ch") String ch, @Query("name") String name, @Query("pwd") String pwd);

    //获取付费医生列表
    @FormUrlEncoded
    @POST("/api/app/consultation/getdoctorlist")
    Observable<DoctorListEntry> getPayDoctorList(@Field("ch") String ch, @Field("hosptialid") String hosptialid, @Field("searchname") String searchname
            , @Field("departid") String departid, @Field("doctitleid") String doctitleid, @Field("hosgradid") String hosgradid
            , @Field("asktype") String asktype, @Field("sorttype") String sorttype, @Field("page") int page, @Field("size") String size);


    //获取免费医生列表
    @FormUrlEncoded
    @POST("/api/app/consultation/getfreedoctorlist")
    Observable<DoctorListEntry> getFreeDoctorList(@Field("ch") String ch, @Field("hosptialid") String hosptialid
            , @Field("departid") String diseaseid, @Field("doctitleid") String doctitleid
            , @Field("hosgradid") String hosgradid, @Field("docname") String docname
            , @Field("page") int page, @Field("size") String size);


    //获取医院等级
    @FormUrlEncoded
    @POST("/api/app/hospital/hosgradelist")
    Observable<GradeLevelBean> getHosGradeLevelList(@Field("ch") String ch);

    //获取医生等级
    @FormUrlEncoded
    @POST("/api/app/doctor/doctitlelist")
    Observable<DoctorLevelListBean> getDoctorLevelList(@Field("ch") String ch);

    //查询全部科室
    @FormUrlEncoded
    @POST("/api/app/consultation/getdepartmenttree")
    Observable<DepartmentListEntry> getAllDepartment(@Field("ch") String ch);

    //查询常用科室
    @FormUrlEncoded
    @POST("/api/app/consultation/gethotdisease")
    Observable<CommentDisBean> getCommentDisList(@Field("ch") String ch);

    //获取banner图
    @FormUrlEncoded
    @POST("/api/app/adverting/queryadvert")
    Observable<BannerBean> getBanner(@Field("ch") String ch, @Field("pagemark") String pagemark, @Field("hospitalid") String hospitalid);
    //获取附近的医院

    @FormUrlEncoded
    @POST("/api/app/hospital/getnearhospital")
    Observable<HospitalEntry> getHospital(@Field("ch") String ch, @Field("longitude") String longitude, @Field("latitude") String latitude);
    //获取首页 综合推荐

    @FormUrlEncoded
    @POST("/api/app/art/getrecommedlist")
    Observable<RecommendEntry> getRecommand(@Field("ch") String ch, @Field("hospitalid") String hospitalid
            , @Field("page") int page, @Field("size") String page_size, @Field("token") String token);

    //我的家人列表
    @FormUrlEncoded
    @POST("/api/app/patient/getmyfamily")
    Observable<PeopleEntry> myFamilyList(@Field("token") String token, @Field("ch") String ch);

    //添加家庭成员
    @FormUrlEncoded
    @POST("/api/app/patient/addhzfolk")
    Observable<Basebean> addFamilyNum(@Field("token") String token, @Field("ch") String ch
            , @Field("name") String name, @Field("sex") String sex, @Field("birthdate") String birthdate, @Field("relationship") String relationship
            , @Field("pcode") String pcode, @Field("ccode") String ccode, @Field("acode") String acode, @Field("phone") String phone
            , @Field("IDcard") String IDcard, @Field("medicalcard") String medicalcard, @Field("diseasehistory") String diseasehistory
            , @Field("anaphylaxis") String anaphylaxis, @Field("operation") String operation, @Field("imgurl") String imgurl
            , @Field("province") String province, @Field("city") String city, @Field("area") String area);


    //修改家庭成员信息
    @FormUrlEncoded
    @POST("/api/app/patient/edithzfolk")
    Observable<Basebean> modifyFamilyNum(@Field("token") String token, @Field("ch") String ch, @Field("id") String id
            , @Field("name") String name, @Field("sex") String sex, @Field("birthdate") String birthdate, @Field("relationship") String relationship
            , @Field("pcode") String pcode, @Field("ccode") String ccode, @Field("acode") String acode, @Field("phone") String phone
            , @Field("IDcard") String IDcard, @Field("medicalcard") String medicalcard, @Field("diseasehistory") String diseasehistory
            , @Field("anaphylaxis") String anaphylaxis, @Field("operation") String operation, @Field("imgurl") String imgurl
            , @Field("province") String province, @Field("city") String city, @Field("area") String area);


    //省份列表
    @FormUrlEncoded
    @POST("/api/app/addr/selectAllProvice")
    Observable<ProvinceEntry> provinceList(@Field("ch") String ch);

    //城市列表
    @FormUrlEncoded
    @POST("/api/app/addr/selectAllCity")
    Observable<CityEntry> cityList(@Field("ch") String ch, @Field("proviceCode") String proviceCode);

    //区列表
    @FormUrlEncoded
    @POST("/api/app/addr/selectAllArea")
    Observable<AreaEntry> areaList(@Field("ch") String ch, @Field("cityCode") String cityCode);

    //删除家庭成员
    @FormUrlEncoded
    @POST("/api/app/patient/delhzfolk")
    Observable<Basebean> deleteFamilyNum(@Field("token") String token, @Field("ch") String ch, @Field("id") String id);

    //第三方登录
    @FormUrlEncoded
    @POST("/api/app/patient/thirdLogin")
    Observable<LoginEntry> thirdLogin(@Field("openid") String openid, @Field("dtype") String dtype, @Field("ch") String ch);

    //绑定第三方登录
    @FormUrlEncoded
    @POST("/api/app/patient/bundling")
    Observable<Basebean> bindThirdLogin(@Field("openid") String openid, @Field("dtype") String dtype, @Field("token") String token);

    //退出登录
    @FormUrlEncoded
    @POST("/api/app/patient/signOut")
    Observable<Basebean> exitApp(@Field("token") String token, @Field("ch") String ch);

    //全部疾病
    @FormUrlEncoded
    @POST("/api/app/consultation/getdistree")
    Observable<DisEntry> allDisease(@Field("ch") String ch);

    //@Part MultipartBody.Part file
    //上传图片
    @Multipart
    @POST("/img/upload")
//    Observable<DataUploadResultEntry> uploadPic(@Part("ch") String ch,@Part("relationtype") Integer relationtype,@Part MultipartBody.Part parts);
    Observable<DataUploadResultEntry> uploadPic(@Part("ch") String ch, @Part("relationtype") int relationtype, @Part List<MultipartBody.Part> parts);


    //上传录音文件
    @Multipart
    @POST("/img/uploadvoice")
    Observable<DataUploadResultEntry> uploadVoice(@Part("ch") String ch, @Part("relationtype") int relationtype, @Part MultipartBody.Part parts);

    //创建付费问诊
    @FormUrlEncoded
    @POST("/api/app/consultation/add")
    Observable<PicAskResutEntry> createPayAsk(@Field("token") String token, @Field("ch") String ch, @Field("flokid") String flokid
            , @Field("diseaseid") String diseaseid
            , @Field("hospitalid") String hospitalid, @Field("problem") String problem, @Field("describe") String describe
            , @Field("imgs") String imgs, @Field("voiceurl") String voiceurl, @Field("doctorid") String doctorid
            , @Field("asktype") String asktype, @Field("asktime") String asktime, @Field("price") String price);

    //创建免费问诊
    @FormUrlEncoded
    @POST("/api/app/consultation/addfreeask")
    Observable<PicAskResutEntry> createFreeAsk(@Field("token") String token, @Field("ch") String ch, @Field("flokid") String flokid
            , @Field("diseaseid") String diseaseid
            , @Field("hospitalid") String hospitalid, @Field("problem") String problem, @Field("describe") String describe
            , @Field("imgs") String imgs, @Field("voiceurl") String voiceurl, @Field("doctorid") String doctorid);


    //问诊列表   1-图文问诊 2-电话问诊 3-视频问诊 4-免费义诊
    @FormUrlEncoded
    @POST("/api/app/consultation/getconsultalist")
    Observable<MyAskReusltList> askList(@Field("token") String token, @Field("ch") String ch, @Field("type") String type
            , @Field("page") int page, @Field("size") String size);


    //取消问诊订单
    @FormUrlEncoded
    @POST("/api/app/consultation/cancel")
    Observable<Basebean> cancleInquiryOrder(@Field("token") String token, @Field("ch") String ch, @Field("orderid") String orderid
            , @Field("status") String status, @Field("reason") String reason);


    //删除问诊订单
    @FormUrlEncoded
    @POST("/api/app/consultation/delorder")
    Observable<Basebean> deleteInquiryOrder(@Field("token") String token, @Field("ch") String ch, @Field("orderid") String orderid);

    //我的问诊列表
    @FormUrlEncoded
    @POST("/api/app/consultation/getorderlist")
    Observable<OrderEntry> myInquiry(@Field("token") String token, @Field("ch") String ch
            , @Field("type") String type
            , @Field("status") String status
            , @Field("page") int page, @Field("size") String size);

    //问诊微信支付数据获取
    @FormUrlEncoded
    @POST("/api/app/consultation/wechatpay")
    Observable<WxPayInfoEntry> wxPayInfo(@Field("token") String token, @Field("ch") String ch
            , @Field("groupnum") String groupnum);


    //积分商品 微信支付数据获取
    @FormUrlEncoded
    @POST("/api/app/pro/wechatpay")
    Observable<WxPayInfoEntry> wxGoodsPayInfo(@Field("token") String token, @Field("ch") String ch
            , @Field("groupnum") String groupnum);


    //splish页面图片获取
    @FormUrlEncoded
    @POST("/api/app/getImg")
    Observable<SplishEntry> getSplishPage(@Field("type") String type, @Field("ch") String ch);


    //医生详细详细
    @FormUrlEncoded
    @POST("/api/app/hospital/getdoctordetail")
    Observable<DoctorDetailEntry> doctorDetail(@Field("token") String token, @Field("ch") String ch
            , @Field("doctorid") String doctorid);

    //医生详情的评价列表
    @FormUrlEncoded
    @POST("/api/app/patient/docxqpjlist")
    Observable<CommComListEntry> doctorDetailCommentList(@Field("token") String token, @Field("ch") String ch
            , @Field("doctorid") String doctorid, @Field("page") int page
            , @Field("size") String size);

    //医生详情 问诊记录
    @FormUrlEncoded
    @POST("/api/app/doctor/getmyconsult")
    Observable<InquiryListEntry> inquiryHistory(@Field("ch") String ch, @Field("doctorid") String doctorid
            , @Field("page") int page, @Field("size") String size);

    //医生详情 医讲堂记录
    @FormUrlEncoded
    @POST("/api/app/doctor/getmyleature")
    Observable<VideoListEntry> videoHistory(@Field("ch") String ch, @Field("doctorid") String doctorid
            , @Field("page") int page, @Field("size") String size);

    //服务项目
    @FormUrlEncoded
    @POST("/api/app/doctor/getserverlist")
    Observable<ServiceInfoEntry> serviceInfo(@Field("ch") String ch, @Field("doctorid") String doctorid);

    //支付宝支付数据获取
    @FormUrlEncoded
    @POST("/api/app/consultation/getalipayconfig")
    Observable<AlipayEntry> aliPayInfo(@Field("token") String token, @Field("ch") String ch
            , @Field("groupnum") String groupnum);

    //支付宝商品支付数据获取
    @FormUrlEncoded
    @POST("/api/app/pro/getalipayconfig")
    Observable<AlipayEntry> aliPayGoodsInfo(@Field("token") String token, @Field("ch") String ch
            , @Field("groupnum") String groupnum);

    //获取运费信息
    @FormUrlEncoded
    @POST("/api/app/pro/getconfigset")
    Observable<FreightPriceEntry> freightInfo(@Field("token") String token, @Field("ch") String ch);

    //商品确认订单页 获取商品详情
    @FormUrlEncoded
    @POST("/api/app/pro/prodeatil")
    Observable<GoodsInfoEntry> goodsInfo(@Field("token") String token, @Field("ch") String ch, @Field("id") String id);

    //挂号 支付宝支付数据获取
    @FormUrlEncoded
    @POST("/api/app/appointment/getalipayconfig")
    Observable<AlipayEntry> aliPayRegisterInfo(@Field("token") String token, @Field("ch") String ch);

    //挂号 微信支付数据获取
    @FormUrlEncoded
    @POST("/api/app/appointment/wechatpay")
    Observable<WxPayInfoEntry> wxRegisterPayInfo(@Field("token") String token, @Field("ch") String ch
            , @Field("groupnum") String groupnum);

    //视频详情
    @FormUrlEncoded
    @POST("/api/app/live/getlivedetail")
    Observable<VideoSpeechDetailEntry> videoSpeechDetail(@Field("token") String token, @Field("ch") String ch
            , @Field("id") String id);


    //发送短信验证码
    @FormUrlEncoded
    @POST("/api/app/sms/send")
    Observable<Basebean> smsCode(@Field("ch") String ch, @Field("ph") String ph
            , @Field("smsType") String smsType);

    //发送短信验证码
    @FormUrlEncoded
    @POST("/api/app/chat/getchatuser")
    Observable<GroupListEntry> groupList(@Field("ch") String ch, @Field("roomid") String roomid);

    //注册
    @FormUrlEncoded
    @POST("/api/app/patient/register")
    Observable<RegisterResultEntry> registerInfo(@Field("ch") String ch, @Field("mobile") String mobile, @Field("sms") String sms
            , @Field("password") String password, @Field("nikename") String nikename, @Field("openid") String openid
            , @Field("dtype") String dtype, @Field("imgurl") String imgurl, @Field("pid") String pid);

    //找回密码
    @FormUrlEncoded
    @POST("/api/app/patient/findPwd")
    Observable<RegisterResultEntry> findPwd(@Field("ch") String ch, @Field("mobile") String mobile, @Field("sms") String sms
            , @Field("password") String password, @Field("renewPwd") String renewPwd);


    //专家讲堂，专家直播
    @FormUrlEncoded
    @POST("/api/app/art/getkepulist")
    Observable<ExpertEntry> expertTeach(@Field("ch") String ch, @Field("hospitalid") String hospitalid, @Field("name") String name
            , @Field("type") String type, @Field("page") int page, @Field("size") String size);


    //图文资讯
    @FormUrlEncoded
    @POST("/api/app/art/getnewslist")
    Observable<ExpertEntry> picNewsList(@Field("ch") String ch, @Field("hospitalid") String hospitalid, @Field("name") String name
            , @Field("type") String type, @Field("page") int page, @Field("size") String size);


    //疾病百科
    @FormUrlEncoded
    @POST("/api/app/art/getdiseaselist")
    Observable<DiseaseListEntry> diseaseList(@Field("ch") String ch, @Field("name") String name
            , @Field("page") int page, @Field("size") String size);


    //获取咨询 订单列表详情
    @FormUrlEncoded
    @POST("/api/app/consultation/getorderdetail")
    Observable<OrderInquiryDetailEntry> askOrderDetail(@Field("ch") String ch, @Field("token") String token, @Field("orderid") String orderid);


    //获取义诊/图文问诊详情
    @FormUrlEncoded
    @POST("/api/app/consultation/getdetail")
    Observable<PicAskDetailEntry> getFreePicAskDetail(@Field("ch") String ch, @Field("token") String token, @Field("consultaid") String consultaid);


    //获取对话列表
    @FormUrlEncoded
    @POST("/api/app/consultation/getreplaylist")
    Observable<ChatEntry> getChatInfo(@Field("ch") String ch, @Field("token") String token, @Field("consultaid") String consultaid);

    // 商品 跟  服务订单列表
    @FormUrlEncoded
    @POST("/api/app/pro/getorderlist")
    Observable<MallOrderEntry> serviceList(@Field("ch") String ch, @Field("token") String token, @Field("status") String status
            , @Field("type") String type, @Field("page") int page, @Field("size") String size);

    //回复对话
    @FormUrlEncoded
    @POST("/api/app/consultation/consultareply")
    Observable<Basebean> chatReply(@Field("ch") String ch, @Field("token") String token, @Field("consultaid") String consultaid
            , @Field("content") String content, @Field("type") String type, @Field("usertype") String usertype);

//    //文章详情h5页面
//    @FormUrlEncoded
//    @POST("/api/app/art/articleinfo")
//    Observable<Basebean> articleDetail(@Field("ch") String ch, @Field("token") String token, @Field("id") String id);
//
//
//    //文章详情h5页面
//    @FormUrlEncoded
//    @POST("/api/app/art/newsinfo")
//    Observable<Basebean> infoDetail(@Field("ch") String ch, @Field("token") String token, @Field("id") String id);


    //患者信息
    @FormUrlEncoded
    @POST("/api/app/patient/getpatientinfo")
    Observable<MyInfoEntry> patientInfo(@Field("ch") String ch, @Field("token") String token);

    //医患帮帮团
    @FormUrlEncoded
    @POST("/api/app/chat/getlist")
    Observable<HelpEntry> helpList(@Field("ch") String ch, @Field("token") String token);

    // 我的未读消息的数量
    @FormUrlEncoded
    @POST("/api/app/patient/mymessagesum")
    Observable<UnreadMsgEntry> unreadMsg(@Field("ch") String ch, @Field("token") String token);

    //我的收藏列表
    @FormUrlEncoded
    @POST("/api/app/art/getcollectlist")
    Observable<MCollectionListEntry> collectList(@Field("ch") String ch, @Field("token") String token
            , @Field("type") String type, @Field("page") int page
            , @Field("size") String size);

    //我的消息列表
    @FormUrlEncoded
    @POST("/api/app/patient/mymessagelist")
    Observable<NewsListEntry> myNewstList(@Field("ch") String ch, @Field("token") String token
            , @Field("type") String type, @Field("page") int page
            , @Field("size") String size);

    //更换手机号
    @FormUrlEncoded
    @POST("/api/app/patient/updphone")
    Observable<Basebean> changeTel(@Field("ch") String ch, @Field("token") String token
            , @Field("sms") String sms, @Field("pwd") String pwd
            , @Field("phone") String phone);

    //修改密码
    @FormUrlEncoded
    @POST("/api/app/patient/updatepwd")
    Observable<Basebean> changePwd(@Field("ch") String ch, @Field("token") String token
            , @Field("sms") String sms, @Field("newpwd") String newpwd
            , @Field("renewpwd") String renewpwd);

    //添加评价
    @FormUrlEncoded
    @POST("/api/app/patient/evaluate")
    Observable<Basebean> addEvaluation(@Field("ch") String ch, @Field("token") String token, @Field("relateid") String relateid
            , @Field("ordercode") String ordercode, @Field("content") String content, @Field("stardepict") String stardepict
            , @Field("starservice") String starservice, @Field("starperformance") String starperformance, @Field("type") String type
            , @Field("imgs") String imgs, @Field("ordertype") String ordertype);

    //商品分类
    @FormUrlEncoded
    @POST("/api/app/pro/getclalist")
    Observable<PointsEntry> goodsClassify(@Field("ch") String ch);

    //我的评价列表
    @FormUrlEncoded
    @POST("/api/app/patient/getpjlist")
    Observable<EvalListEntry> commandList(@Field("ch") String ch, @Field("token") String token, @Field("page") int page, @Field("size") String size);

    //我的评价详情
    @FormUrlEncoded
    @POST("/api/app/patient/commentdetail")
    Observable<EvalDetailEntry> commandInfo(@Field("ch") String ch, @Field("token") String token, @Field("id") String id);


    //商品 服务 评价详情
    @FormUrlEncoded
    @POST("/api/app/pro/commentdetail")
    Observable<EvalDetailEntry> goodsCommandInfo(@Field("ch") String ch, @Field("token") String token, @Field("code") String code);


    //删除订单
    @FormUrlEncoded
    @POST("/api/app/pro/delorder")
    Observable<EvalDetailEntry> deleteGoodsOrder(@Field("ch") String ch, @Field("token") String token, @Field("id") String id);

    //确认收货
    @FormUrlEncoded
    @POST("/api/app/pro/comfirmreceive")
    Observable<Basebean> confirmReceive(@Field("ch") String ch, @Field("token") String token, @Field("orderid") String orderid);

    //查看物流
    @FormUrlEncoded
    @POST("/api/app/pro/getlogistic")
    Observable<LogisticEntry> getLogistic(@Field("ch") String ch, @Field("token") String token, @Field("orderid") String orderid);

    //服务订单使用
    @FormUrlEncoded
    @POST("/api/app/pro/useservice")
    Observable<LogisticEntry> useServiceOrder(@Field("ch") String ch, @Field("token") String token, @Field("orderid") String orderid);

    //添加收藏，关注
    @FormUrlEncoded
    @POST("/api/app/art/addcollect")
    Observable<Basebean> addCollection(@Field("ch") String ch, @Field("token") String token
            , @Field("relateid") String relateid, @Field("type") String type);

    //取消收藏 取消关注
    @FormUrlEncoded
    @POST("/api/app/art/delcollect")
    Observable<Basebean> removeCollection(@Field("ch") String ch, @Field("token") String token
            , @Field("relateid") String relateid, @Field("type") String type);

    //商品列表
    @FormUrlEncoded
    @POST("/api/app/pro/getprolist")
    Observable<GoodsListEntry> goodsList(@Field("ch") String ch, @Field("keys") String keys
            , @Field("classid") String classid, @Field("page") int page, @Field("size") String size);

    //积分记录
    @FormUrlEncoded
    @POST("/api/app/checkin/getchecklist")
    Observable<PointsDetailEntry> pointsHistory(@Field("ch") String ch, @Field("token") String token
            , @Field("fromtype") String fromtype, @Field("page") int page, @Field("size") String size);

    //搜索医生列表
    @FormUrlEncoded
    @POST("/api/app/doctor/searchdoctor")
    Observable<SearchListEntry> searchDoctor(@Field("ch") String ch, @Field("name") String name
            , @Field("hosptialid") String hosptialid, @Field("page") int page, @Field("size") String size);

    //搜索医院列表
    @FormUrlEncoded
    @POST("/api/app/hospital/searchhospital")
    Observable<SearchListEntry> searchHospital(@Field("ch") String ch, @Field("name") String name
            , @Field("page") int page, @Field("size") String size);

    //搜索文章列表
    @FormUrlEncoded
    @POST("/api/app/art/articlesearch")
    Observable<SearchListEntry> searchArticle(@Field("ch") String ch, @Field("name") String name
            , @Field("hosptialid") String hosptialid, @Field("page") int page, @Field("size") String size);

    //搜索问诊列表
    @FormUrlEncoded
    @POST("/api/app/consultation/searchconsult")
    Observable<SearchListEntry> searchInquiry(@Field("ch") String ch, @Field("name") String name
            , @Field("hosptialid") String hosptialid, @Field("page") int page, @Field("size") String size);

    //精彩问诊
    @FormUrlEncoded
    @POST("/api/app/consultation/gethotconsult")
    Observable<RecommendEntry> hotConsult(@Field("ch") String ch, @Field("hospitalid") String hospitalid
            , @Field("page") int page, @Field("size") String size);

    //专家讲堂
    @FormUrlEncoded
    @POST("/api/app/art/getlecturelist")
    Observable<RecommendEntry> speechList(@Field("ch") String ch, @Field("hospitalid") String hospitalid
            , @Field("page") int page, @Field("size") String size);

    //医院列表
    @FormUrlEncoded
    @POST("/api/app/hospital/gethospitallist")
    Observable<HospitalListEntry> hospitalList(@Field("ch") String ch, @Field("type") String type
            , @Field("hospitalname") String hospitalname, @Field("longitude") String longitude
            , @Field("latitude") String latitude, @Field("hostype") String hostype
            , @Field("hosgrade") String hosgrade);

    //医院类型列表
    @FormUrlEncoded
    @POST("/api/app/hospital/hostypelist")
    Observable<HospitalTypeEntry> hospitalTypeList(@Field("ch") String ch);

    //门诊列表
    @FormUrlEncoded
    @POST("/api/app/appointment/getmenztree")
    Observable<RegisterDeartmentListEntry> menzenList(@Field("ch") String ch, @Field("hospitalid") String hospitalid);


    //医院详情
    @FormUrlEncoded
    @POST("/api/app/hospital/gethosdetail")
    Observable<HospitalInfoEntry> hospitalInfo(@Field("ch") String ch, @Field("hospitalid") String hospitalid);


    //挂号医生类型
    @FormUrlEncoded
    @POST("/api/app/appointment/doctypelist")
    Observable<DoctorTypeEntry> doctorTypeList(@Field("ch") String ch);

    //查询号源
    @FormUrlEncoded
    @POST("/api/app/appointment/getappointlist")
    Observable<NumListEntry> orderNumList(@Field("ch") String ch, @Field("hospitalid") String hospitalid
            , @Field("departid") String departid, @Field("menzhenid") String menzhenid
            , @Field("timedata") String timedata, @Field("timetype") String timetype
            , @Field("doctypeid") String doctypeid, @Field("doctorid") String doctorid);

    //预约挂号
    @FormUrlEncoded
    @POST("/api/app/appointment/add")
    Observable<PicAskResutEntry> registerOrder(@Field("ch") String ch, @Field("token") String token
            , @Field("appointid") String appointid, @Field("departid") String departid
            , @Field("outdepartid") String outdepartid, @Field("doctorid") String doctorid
            , @Field("appointtype") String appointtype, @Field("checkpatienid") String checkpatienid
            , @Field("note") String note, @Field("imgs") String imgs);


    //挂号订单列表
    @FormUrlEncoded
    @POST("/api/app/appointment/getlist")
    Observable<RegisterOrderEntry> registerOrderList(@Field("ch") String ch, @Field("token") String token);

    //挂号订单详情
    @FormUrlEncoded
    @POST("/api/app/appointment/getdetail")
    Observable<OrderInfoEntry> orderInfo(@Field("ch") String ch, @Field("token") String token, @Field("id") String id);

    //挂号订单评价详情
    @FormUrlEncoded
    @POST("/api/app/appointment/commentdetail")
    Observable<EvalDetailEntry> registerCommentDetail(@Field("ch") String ch, @Field("token") String token, @Field("code") String code);

    //问诊订单评价详情
    @FormUrlEncoded
    @POST("/api/app/consultation/commentdetail")
    Observable<EvalDetailEntry> inquiryCommentDetail(@Field("ch") String ch, @Field("token") String token, @Field("code") String code);

    //删除挂号订单
    @FormUrlEncoded
    @POST("/api/app/appointment/delorder")
    Observable<Basebean> deleteRegisterOrder(@Field("ch") String ch, @Field("token") String token, @Field("orderid") String orderid);

    //查询绑定信息
    @FormUrlEncoded
    @POST("/api/app/patient/bundinfo")
    Observable<BindEntry> bindInfo(@Field("token") String token);

    //修改个人信息
    @FormUrlEncoded
    @POST("/api/app/patient/updpatientinfo")
    Observable<Basebean> updateInfo(@Field("ch") String ch, @Field("token") String token, @Field("imgurl") String imgurl
            , @Field("name") String name, @Field("birthday") String birthday, @Field("sex") String sex
            , @Field("location") String location, @Field("address") String address);

    //添加信息反馈
    @FormUrlEncoded
    @POST("/api/app/patient/addFeedBack")
    Observable<Basebean> feedback(@Field("ch") String ch, @Field("token") String token, @Field("content") String content
            , @Field("type") String type, @Field("phone") String phone);

    //我咨询的医生列表
    @FormUrlEncoded
    @POST("/api/app/patient/getconsultlist")
    Observable<MyDoctorListEntry> askDoctorList(@Field("ch") String ch, @Field("token") String token
            , @Field("page") int page, @Field("size") String size);

    //我关注的医生列表
    @FormUrlEncoded
    @POST("/api/app/patient/getcolldoclist")
    Observable<MyDoctorListEntry> attentionDoctorList(@Field("ch") String ch, @Field("token") String token
            , @Field("page") int page, @Field("size") String size);

    //视频详情的评价列表
    @FormUrlEncoded
    @POST("/api/app/live/commentlist")
    Observable<CommComListEntry> videoCommentList(@Field("ch") String ch, @Field("token") String token, @Field("id") String id
            , @Field("page") int page, @Field("size") String size);

//    //关注医生
//    @FormUrlEncoded
//    @POST("/api/app/patient/addconsult")
//    Observable<MyDoctorListEntry> attentionDoctor(@Field("ch") String ch, @Field("token") String token
//            , @Field("doctorid") String doctorid);

    //点赞
    @FormUrlEncoded
    @POST("/api/app/art/fabulousNews")
    Observable<Basebean> good(@Field("token") String token, @Field("type") String type
            , @Field("relateid") String relateid);

    //取消点赞
    @FormUrlEncoded
    @POST("/api/app/art/delfabulous")
    Observable<Basebean> removeGood(@Field("token") String token, @Field("type") String type
            , @Field("relateid") String relateid);

    //添加回复
    @FormUrlEncoded
    @POST("/api/app/creply/commentreply")
    Observable<Basebean> reply(@Field("ch") String ch, @Field("token") String token
            , @Field("id") String id, @Field("content") String content);

    //添加回复
    @FormUrlEncoded
    @POST("/api/app/patient/getpllist")
    Observable<com.ylean.cf_hospitalapp.my.bean.CommentListEntry> commentList(@Field("ch") String ch, @Field("token") String token
            , @Field("page") int page, @Field("size") String size);

    //取消挂号订单
    @FormUrlEncoded
    @POST("/api/app/appointment/cancel")
    Observable<Basebean> cancleOrder(@Field("ch") String ch, @Field("token") String token
            , @Field("id") String id, @Field("status") String status, @Field("reason") String reason);

    //取消商品订单
    @FormUrlEncoded
    @POST("/api/app/pro/cancel")
    Observable<Basebean> cancleGoodsOrder(@Field("ch") String ch, @Field("token") String token
            , @Field("orderid") String orderid, @Field("status") String status, @Field("reason") String reason);


    //获取售后寄回地址
    @GET("/api/app/pro/getreturnadd")
    Observable<RefundAddressEntry> refundAddress();

    //挂号订单申请退款
    @FormUrlEncoded
    @POST("/api/app/appointment/afterreturn")
    Observable<Basebean> payBack(@Field("ch") String ch, @Field("token") String token
            , @Field("id") String id, @Field("status") String status, @Field("reason") String reason, @Field("imgs") String imgs);


    //问诊订单 申请退款
    @FormUrlEncoded
    @POST("/api/app/consultation/afterreturn")
    Observable<Basebean> inquiryRefund(@Field("ch") String ch, @Field("token") String token
            , @Field("orderid") String orderid, @Field("status") String status, @Field("reason") String reason, @Field("imgs") String imgs);


    //商品申请退款
    @FormUrlEncoded
    @POST("/api/app/pro/afterreturn")
    Observable<Basebean> goodsRefund(@Field("ch") String ch, @Field("token") String token
            , @Field("orderid") String orderid, @Field("reason") String reason, @Field("imgs") String imgs
            , @Field("type") String type);


    //查询全部收货地址
    @FormUrlEncoded
    @POST("/api/app/receiver/selectAddrByUserId")
    Observable<AddressListEntry> allAddress(@Field("ch") String ch, @Field("token") String token);


    //设置为默认地址
    @FormUrlEncoded
    @POST("/api/app/receiver/updateAddrDefault")
    Observable<AddressListEntry> setDefaultAddress(@Field("ch") String ch, @Field("token") String token, @Field("id") String id);

    //删除地址
    @FormUrlEncoded
    @POST("/api/app/receiver/delReceiverAddr")
    Observable<AddressListEntry> deleteAddress(@Field("ch") String ch, @Field("token") String token, @Field("id") String id);

    //我的家人详情
    @FormUrlEncoded
    @POST("/api/app/patient/getjrbyuid")
    Observable<FamilyDetailEntry> familyDetail(@Field("ch") String ch, @Field("token") String token, @Field("id") String id);

    //我的申请列表
    @FormUrlEncoded
    @POST("/api/app/patient/getapplylist")
    Observable<MyReuqestListEntry> requestList(@Field("ch") String ch, @Field("token") String token, @Field("status") String status
            , @Field("page") int page, @Field("size") String size);

    //添加收货地址
    @FormUrlEncoded
    @POST("/api/app/receiver/addReceiverAddr")
    Observable<Basebean> addAddress(@Field("ch") String ch, @Field("token") String token
            , @Field("name") String name, @Field("mobile") String mobile, @Field("provinceCode") String provinceCode
            , @Field("cityCode") String cityCode, @Field("areaCode") String areaCode, @Field("provinceName") String provinceName
            , @Field("cityName") String cityName, @Field("areaName") String areaName, @Field("address") String address
            , @Field("isDefault") String isDefault);

    //修改收货地址
    @FormUrlEncoded
    @POST("/api/app/receiver/updateAddr")
    Observable<Basebean> modifyAddress(@Field("ch") String ch, @Field("token") String token, @Field("id") String id
            , @Field("name") String name, @Field("mobile") String mobile, @Field("provinceCode") String provinceCode
            , @Field("cityCode") String cityCode, @Field("areaCode") String areaCode, @Field("provinceName") String provinceName
            , @Field("cityName") String cityName, @Field("areaName") String areaName, @Field("address") String address
            , @Field("isDefault") String isDefault);


    //结束问诊
    @FormUrlEncoded
    @POST("/api/app/consultation/overconsulta")
    Observable<Basebean> endInquiry(@Field("ch") String ch, @Field("token") String token, @Field("consultaid") String consultaid);


    //查看问诊小结
    @FormUrlEncoded
    @POST("/api/app/consultation/summarydetail")
    Observable<InquiryIntrBean> inquirySummaryReview(@Field("ch") String ch, @Field("token") String token, @Field("consultaid") String consultaid);


    //医院科室列表
    @FormUrlEncoded
    @POST("/api/app/hospital/gethosdepart")
    Observable<HospDepartListEntry> hospitalDeartmentList(@Field("ch") String ch, @Field("hospitalid") String hospitalid);


    //医院详情的评论列表
    @FormUrlEncoded
    @POST("/api/app/patient/hosxqpjlist")
    Observable<CommComListEntry> hospitalCommentList(@Field("ch") String ch, @Field("token") String token
            , @Field("hospitalid") String hospitalid, @Field("page") int page, @Field("size") String size);


    //我邀请的用户列表
    @FormUrlEncoded
    @POST("/api/app/patient/myinvitationlist")
    Observable<InviteListEntry> inviteList(@Field("ch") String ch, @Field("token") String token
            , @Field("page") int page, @Field("size") String size);


    //我邀请的用户列表
    @FormUrlEncoded
    @POST("/api/app/pro/orderdetail")
    Observable<GoodsOrderInfoEntry> goodsOrderInfo(@Field("ch") String ch, @Field("token") String token
            , @Field("orderid") String orderid);


    //申请免费接送
    @FormUrlEncoded
    @POST("/api/app/patient/addfreeshuttle")
    Observable<Basebean> freeTransfer(@Field("ch") String ch, @Field("token") String token
            , @Field("name") String name, @Field("peoplecount") String peoplecount, @Field("phone") String phone
            , @Field("address") String address, @Field("shuttletime") String shuttletime, @Field("hospitalid") String hospitalid);

    //添加商品订单
    @FormUrlEncoded
    @POST("/api/app/pro/addorder")
    Observable<PicAskResutEntry> goodsOrder(@Field("ch") String ch, @Field("token") String token
            , @Field("addrid") String addrid, @Field("totalmoney") String totalmoney, @Field("freightmoney") String freightmoney
            , @Field("usepoints") String usepoints, @Field("skuid") String skuid, @Field("skuprice") String skuprice
            , @Field("points") String points, @Field("skutype") String skutype, @Field("skucount") String skucount
            , @Field("remark") String remark);


    //分享成功后调用 用来增加积分
    @FormUrlEncoded
    @POST("/api/app/checkin/getsharepoint")
    Observable<Basebean> shareSuccessIntegralGress(@Field("token") String token);


    //根据id查询文章资讯标题
    @FormUrlEncoded
    @POST("/api/app/art/getnewtitle")
    Observable<ShareTitleBean> getShareTileByid(@Field("id") String id);


//    //删除地址
//    @FormUrlEncoded
//    @POST("/api/app/pro/prodeatil")
//    Observable<AddressListEntry> deleteAddress(@Field("ch") String ch, @Field("token") String token, @Field("id") String id);


    //如何赚取积分
    @FormUrlEncoded
    @POST("/api/app/checkin/pointsrule")
    Observable<EarnPointsBean> howGetPoints(@Field("ch") String ch, @Field("token") String token);

    //签到
    @FormUrlEncoded
    @POST("/api/app/checkin/addpointrecord")
    Observable<Basebean> checkIn(@Field("ch") String ch, @Field("token") String token);

    //新版本检测
    @FormUrlEncoded
    @POST("/api/app/versionupd/checkVersion")
    Observable<UpdateResultBean> checkUpdate(@Field("ch") String ch, @Field("type") String type);


}
