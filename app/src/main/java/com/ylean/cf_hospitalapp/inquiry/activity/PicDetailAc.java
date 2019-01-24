package com.ylean.cf_hospitalapp.inquiry.activity;

import android.os.Bundle;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

import me.panpf.sketch.SketchImageView;

/**
 * Created by linaidao on 2018/12/31.
 */

public class PicDetailAc extends BaseActivity {

//    private PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_pic_detail);

//        SimpleDraweeView zoomView = findViewById(R.id.zoomView);

        String picUrl = getIntent().getStringExtra("picUrl");
        SketchImageView image_main = findViewById(R.id.image_main);
        image_main.displayImage(picUrl);
        Logger.d("图片地址::" + picUrl);
//        zoomView.setImageURI(Uri.parse(picUrl));

        //开启手势缩放
        image_main.setZoomEnabled(true);


        TitleBackBarView tbv = findViewById(R.id.tbv);
        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        picUrl = BitmapFactory.decodeFile(photoUrl);
//        pv_pre.setImageBitmap(detailBitmap);
//        rl_pre.setVisibility(View.VISIBLE);
//        isShowingPicDetail = true;
//        mAttacher = new PhotoViewAttacher(zoomView);
    }

    /**
     * 隐藏详情图片
     */
    public void hintPicDetail() {
//        isShowingPicDetail = false;
//        rl_pre.setVisibility(View.GONE);
//        mAttacher = null;
    }

}
