package com.ylean.cf_hospitalapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;


/**
 * 我的里面的 条目item
 * Created by Aaron on 17/4/6.
 */

public class MyItemView extends RelativeLayout {

    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_content;

    public MyItemView(Context context) {
        this(context, null);
    }

    public MyItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        //加载自定义控件布局
        LayoutInflater.from(context).inflate(R.layout.custom_my_item_layout, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyItemView);

        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        tv_content = (TextView) findViewById(R.id.tv_content);
//        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);

        if (typedArray != null) {

            int leftImageId = typedArray.getResourceId(R.styleable.MyItemView_ileftImage, -1);
            int rightImageId = typedArray.getResourceId(R.styleable.MyItemView_irightImage, -1);
//            int backgroundRescId = typedArray.getResourceId(R.styleable.MyItemView_backgroundResc, -1);
            int textColorRescId = typedArray.getResourceId(R.styleable.MyItemView_itextColor, -1);
            float txtSize = typedArray.getDimension(R.styleable.MyItemView_itextSize, -1);
            String txt = typedArray.getString(R.styleable.MyItemView_itxt);

            if (leftImageId != -1)
                iv_left.setImageResource(leftImageId);

            if (rightImageId != -1)
                iv_right.setImageResource(rightImageId);

            if (!TextUtils.isEmpty(txt))
                tv_content.setText(txt);

            if (txtSize != -1)
                tv_content.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtSize);

            if (textColorRescId != -1)
                tv_content.setTextColor(getResources().getColor(textColorRescId));

//            if (backgroundRescId != -1)
//                rl.setBackgroundResource(backgroundRescId);

            typedArray.recycle();
        }


    }
}
