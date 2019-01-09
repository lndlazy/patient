package com.ylean.cf_hospitalapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.utils.DensityUtil;


/**
 * 自定义titlebarview
 * Created by Aaron on 17/2/13.
 */

public class TitleBackBarView extends RelativeLayout {

    private ImageView leftImage;
    private ImageView rightImage;
    private TextView tvText;

    public TitleBackBarView(Context context) {
        this(context, null);
    }

    public TitleBackBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBackBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        //加载自定义控件布局
        LayoutInflater.from(context).inflate(R.layout.custom_titlebarview, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBackBarView);

        leftImage = (ImageView) findViewById(R.id.iv_left);
        tvText = (TextView) findViewById(R.id.tv_title);
        rightImage = (ImageView) findViewById(R.id.iv_right);

        if (typedArray != null) {

            String titleText = typedArray.getString(R.styleable.TitleBackBarView_mTitleBarText);
            float titleSize = typedArray.getDimension(R.styleable.TitleBackBarView_mTitleBarSize, DensityUtil.dip2px(getContext(), 18));
            int titleColor = typedArray.getColor(R.styleable.TitleBackBarView_mTitleBarColor, Color.BLACK);
            int backgroundColor = typedArray.getColor(R.styleable.TitleBackBarView_mTitleBackgroundColor, getResources().getColor(R.color.white));
            int leftResource = typedArray.getResourceId(R.styleable.TitleBackBarView_mTitleLeft, -1);
            int rightResource = typedArray.getResourceId(R.styleable.TitleBackBarView_mTitleRight, -1);

            //设置title
            tvText.setText(titleText);
            tvText.setTextSize(titleSize / getResources().getDisplayMetrics().density);
            tvText.setTextColor(titleColor);
            setBackgroundColor(backgroundColor);//设置titlebar的背景颜色

            if (leftResource != -1)
                leftImage.setImageResource(leftResource);

            if (rightResource != -1)
                rightImage.setImageResource(rightResource);

            //回收typedArray
            typedArray.recycle();
        }

    }

    //左边图标点击事件
    public void setOnLeftClickListener(OnClickListener onLeftClickListener) {
        leftImage.setOnClickListener(onLeftClickListener);
    }

    //右边图标点击事件
    public void setOnRightClickListener(OnClickListener onRightClickListener) {
        rightImage.setOnClickListener(onRightClickListener);
    }

    public void setTitle(String title) {
        tvText.setText(title);
    }


    public void setLeftImage(Drawable drawable) {

//        if (resId != -1)
        leftImage.setImageDrawable(drawable);
//            leftImage.setImageResource(resId);
//        else
//            leftImage.setImageResource(-1);

    }

    public void setRightImage(Drawable drawable) {
        rightImage.setImageDrawable(drawable);
    }

    public void setRightVisible(boolean visible) {
        rightImage.setVisibility(visible ? VISIBLE : INVISIBLE);
    }

}
