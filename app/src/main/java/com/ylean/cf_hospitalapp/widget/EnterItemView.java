package com.ylean.cf_hospitalapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;


/**
 * 带箭头的条目view
 * Created by Aaron on 17/2/23.
 */

public class EnterItemView extends RelativeLayout {

    private ImageView leftImage;
    private ImageView rightImage;
    private TextView mRightText;
    private TextView tvText;

    public EnterItemView(Context context) {
        this(context, null);
    }

    public EnterItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EnterItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {

        //加载自定义控件布局
        LayoutInflater.from(context).inflate(R.layout.custom_enter_view, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EnterItemView);

        leftImage = (ImageView) findViewById(R.id.iv_left);
        tvText = (TextView) findViewById(R.id.tv_text);
        mRightText = (TextView) findViewById(R.id.tv_right);
        rightImage = (ImageView) findViewById(R.id.iv_right);

        if (typedArray != null) {

            String test = typedArray.getString(R.styleable.EnterItemView_mText);
            float titleSize = typedArray.getDimension(R.styleable.EnterItemView_mTextSize, 16);
            int titleColor = typedArray.getColor(R.styleable.EnterItemView_mTextColor, Color.BLACK);
            int mRightTxtColor = typedArray.getColor(R.styleable.EnterItemView_mRightTxtColor, Color.BLACK);
            int backgroundColor = typedArray.getColor(R.styleable.EnterItemView_mBackgroundColor, Color.WHITE);
            int leftResource = typedArray.getResourceId(R.styleable.EnterItemView_mLeft, -1);
            int rightResource = typedArray.getResourceId(R.styleable.EnterItemView_mRight, -1);

            boolean mShowEnter = typedArray.getBoolean(R.styleable.EnterItemView_mShowEnter, true);
//            boolean mShowRightTxt = typedArray.getBoolean(R.styleable.EnterItemView_mShowRightTxt, false);
            String mRightTxt = typedArray.getString(R.styleable.EnterItemView_mRightTxt);

            if (!mShowEnter) {

                rightImage.setVisibility(GONE);

                LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.setMargins(0, 0, (int) (20 * (getResources().getDisplayMetrics().density)), 0);
                mRightText.setLayoutParams(params);
            }

//            if (TextUtils.isEmpty(mRightTxt))
//                mRightText.setVisibility(GONE);
//            else
            mRightText.setText(mRightTxt);
            mRightText.setTextColor(mRightTxtColor);

            //设置txt
            tvText.setText(test);
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


    //右边图标点击事件
    public void setOnRightClickListener(OnClickListener onRightClickListener) {
        rightImage.setOnClickListener(onRightClickListener);
    }

    //
    public void setRightTxt(String string) {
        mRightText.setText(string);
    }

    public String getRightTxt() {
        return mRightText.getText().toString();
    }


    public void setTxt(String txt) {
        tvText.setText(txt);
    }

}
