package com.elearningpath.wetestx.models;

import com.elearningpath.wetestx.base.BaseModel;
import com.elearningpath.wetestx.beans.ApiResponse;
import com.elearningpath.wetestx.beans.ArticleTypeBean;
import com.elearningpath.wetestx.pojos.SingleArticle;
import com.elearningpath.wetestx.utils.NetApi;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/4/5
 * 版本：1.0.0
 * 描述：
 */

public class ArticleTypeModel extends BaseModel {
    private ArticleTypeBean articleTypeBean;

    public ArticleTypeBean getArticleTypeBean() {
        return articleTypeBean;
    }

    public void setArticleTypeBean(ArticleTypeBean articleTypeBean) {
        this.articleTypeBean = articleTypeBean;
    }

    @Inject
    public ArticleTypeModel() {
    }

    public Observable<ApiResponse<ArticleTypeBean>> getArticleTypeRemote(){
        return NetApi.getInstance().getArticleResult();
    }
}
