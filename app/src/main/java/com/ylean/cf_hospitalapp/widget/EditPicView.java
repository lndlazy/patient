package com.ylean.cf_hospitalapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;

public class EditPicView extends LinearLayout {


    private ImageView ivImg;
    private EditText etCommit;

    public EditPicView(Context context) {
        this(context, null);
    }

    public EditPicView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditPicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        //加载自定义控件布局
        LayoutInflater.from(context).inflate(R.layout.custom_my_item_edit, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditPicView);

        ivImg = findViewById(R.id.ivImg);
        etCommit = findViewById(R.id.etCommit);

        if (typedArray != null) {

            int leftImageId = typedArray.getResourceId(R.styleable.EditPicView_eLeftImage, -1);
            int textHintColor = typedArray.getResourceId(R.styleable.EditPicView_eTextHintColor, -1);
//            int backgroundRescId = typedArray.getResourceId(R.styleable.MyItemView_backgroundResc, -1);
            int textColorRescId = typedArray.getResourceId(R.styleable.EditPicView_eTextColor, -1);
            float txtSize = typedArray.getDimension(R.styleable.EditPicView_eTextSize, -1);

            String txt = typedArray.getString(R.styleable.EditPicView_eTxt);
            String txtHint = typedArray.getString(R.styleable.EditPicView_eTxtHint);

            if (leftImageId != -1)
                ivImg.setImageResource(leftImageId);

            if (textHintColor != -1)
                etCommit.setHintTextColor(getResources().getColor(textHintColor));

            if (!TextUtils.isEmpty(txt))
                etCommit.setText(txt);

            if (txtSize != -1)
                etCommit.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtSize);

            if (textColorRescId != -1)
                etCommit.setTextColor(getResources().getColor(textColorRescId));

            if (!TextUtils.isEmpty(txtHint))
                etCommit.setHint(txtHint);

//            if (backgroundRescId != -1)
//                rl.setBackgroundResource(backgroundRescId);

            etCommit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        if (onSearch != null)
                            onSearch.startSearch(etCommit.getText().toString());
                    }

                    return false;

                }
            });

            typedArray.recycle();
        }

    }

    public String getInputInfo() {
        return etCommit.getText().toString();
    }

    public OnSearch onSearch;


    public OnSearch getOnSearch() {
        return onSearch;
    }

    public void setOnSearch(OnSearch onSearch) {
        this.onSearch = onSearch;
    }

    public interface OnSearch {
        void startSearch(String s);
    }

}
