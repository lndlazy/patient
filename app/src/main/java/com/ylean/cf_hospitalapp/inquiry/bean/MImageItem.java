package com.ylean.cf_hospitalapp.inquiry.bean;

import com.lzy.imagepicker.bean.ImageItem;

/**
 * Created by linaidao on 2018/12/18.
 */

public class MImageItem extends ImageItem {

    private String urlPath;



//    public String name;       //图片的名字
//    public String path;       //图片的路径
//    public long size;         //图片的大小
//    public int width;         //图片的宽度
//    public int height;        //图片的高度
//    public String mimeType;   //图片的类型
//    public long addTime;      //图片的创建时间
    public MImageItem(String urlPath, String name, String path) {
        this.urlPath = urlPath;
        this.name = name;
        this.path = path;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
