package com.ylean.cf_hospitalapp.inquiry.view;

import com.ylean.cf_hospitalapp.inquiry.bean.CommentDisBean;
import com.ylean.cf_hospitalapp.base.view.DataUploadView;

import java.util.List;

public interface IPayTWView extends DataUploadView {

    void setCommentDis(List<CommentDisBean.DataBean> data);
}
