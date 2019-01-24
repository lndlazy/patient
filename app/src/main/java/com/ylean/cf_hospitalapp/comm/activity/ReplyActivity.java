package com.ylean.cf_hospitalapp.comm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.base.activity.BaseActivity;
import com.ylean.cf_hospitalapp.comm.pres.IReplyPres;
import com.ylean.cf_hospitalapp.comm.view.IReplyView;
import com.ylean.cf_hospitalapp.doctor.bean.CommComListEntry;
import com.ylean.cf_hospitalapp.utils.SaveUtils;
import com.ylean.cf_hospitalapp.utils.SpValue;
import com.ylean.cf_hospitalapp.widget.TitleBackBarView;

/**
 * 回复
 * Created by linaidao on 2019/1/13.
 */

public class ReplyActivity extends BaseActivity implements IReplyView {

    private android.widget.EditText etContent;
    private android.widget.TextView tvCommit;

    private IReplyPres iReplyPres = new IReplyPres(this);
    private String id;
    private CommComListEntry.DataBean commentBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_reply);

        id = getIntent().getStringExtra("id");
        commentBean = getIntent().getParcelableExtra("commentBean");
        init();

    }

    private void init() {
        this.tvCommit = (TextView) findViewById(R.id.tvCommit);
        this.etContent = (EditText) findViewById(R.id.etContent);
        TitleBackBarView tbv = (TitleBackBarView) findViewById(R.id.tbv);
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
                    showErr("请输入回复内容");
                    return;
                }

                commit();
            }
        });
    }

    private void commit() {

        iReplyPres.setToken((String) SaveUtils.get(this, SpValue.TOKEN, ""));
        iReplyPres.setId(id);
        iReplyPres.reply(etContent.getText().toString());

    }

    //回复成功
    @Override
    public void replySuccess() {

        Intent data = new Intent();
        data.putExtra("content", etContent.getText().toString());

//        if (commentBean != null) {
//            List<CommComListEntry.DataBean.ReplyBean> reply = commentBean.getReply();
//
//            if (reply == null)
//                reply = new ArrayList<>();
//
//            CommComListEntry.DataBean.ReplyBean replyBean = new CommComListEntry.DataBean.ReplyBean();
//            replyBean.setReplyname("我");
//            replyBean.setReplycontent(etContent.getText().toString());
//            reply.add(replyBean);
//
//        }
        CommComListEntry.DataBean.ReplyBean replyBean = new CommComListEntry.DataBean.ReplyBean();
        replyBean.setReplyname("我");
        replyBean.setReplycontent(etContent.getText().toString());

        data.putExtra("replyBean", replyBean);
        setResult(0x999, data);
        finish();

    }
}
