package com.ylean.cf_hospitalapp.home.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.bean.Basebean;
import com.ylean.cf_hospitalapp.base.view.BaseActivity;
import com.ylean.cf_hospitalapp.net.BaseNoTObserver;
import com.ylean.cf_hospitalapp.net.RetrofitHttpUtil;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 评论页面
 * Created by linaidao on 2019/1/7.
 */

public class CommentActivity extends BaseActivity {

    private com.ylean.cf_hospitalapp.widget.TitleBackBarView tbv;
    private android.widget.EditText etContent;
    private android.widget.TextView tvCommit;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_comment);
        id = getIntent().getStringExtra("id");
        initView();

    }

    private void initView() {
        this.tvCommit = (TextView) findViewById(R.id.tvCommit);
        this.etContent = (EditText) findViewById(R.id.etContent);
        this.tbv = (TitleBackBarView) findViewById(R.id.tbv);

        tbv.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etContent.getText().toString())) {
                    showErr("请输入评论内容");
                    return;
                }
                commit();
            }
        });
    }

    //提交评论
    private void commit() {

        RetrofitHttpUtil
                .getInstance()
                .evaluate(
                        new BaseNoTObserver<Basebean>() {
                            @Override
                            public void onHandleSuccess(Basebean basebean) {
                                showErr("评论成功");
                                finish();
                            }

                            @Override
                            public void onHandleError(String message) {
                                showErr(message);
                            }

                        }
                        , SpValue.CH, (String) SaveUtils.get(this, SpValue.TOKEN, "")
                        , id, "", etContent.getText().toString(), "", "", ""
                        , SpValue.COMMIT_TYPE_ARTICEL, "", "");

    }

}
