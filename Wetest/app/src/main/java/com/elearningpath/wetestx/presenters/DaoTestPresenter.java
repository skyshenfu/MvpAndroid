package com.elearningpath.wetestx.presenters;

import com.elearningpath.wetestx.MainApplication;
import com.elearningpath.wetestx.base.BasePresenterImpl;
import com.elearningpath.wetestx.base.BaseView;
import com.elearningpath.wetestx.beans.ArticleTypeBean;
import com.elearningpath.wetestx.greendao.gen.SingleArticleDao;
import com.elearningpath.wetestx.pojos.SingleArticle;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/4/7
 * 版本：1.0.0
 * 描述：
 */

public class DaoTestPresenter extends BasePresenterImpl<BaseView> {
    @Inject
    public DaoTestPresenter() {
    }
    @Override
    public void initMvpView() {

    }
    public void insertTest(ArticleTypeBean articleTypeBean){
        long b=MainApplication.getMyApplication().getDaoSession().getArticleTypeBeanDao().insertOrReplace(articleTypeBean);
        for (SingleArticle singleArticle:articleTypeBean.getDataList()){
            singleArticle.setOwnerId(b);
        }
        MainApplication.getMyApplication().getDaoSession().getSingleArticleDao().insertInTx(articleTypeBean.getDataList());
        Logger.e(b+"");
    }
    public void deleteTest(long id){
        MainApplication.getMyApplication().getDaoSession().getArticleTypeBeanDao().deleteByKey(id);
    }
    public void updateTest(ArticleTypeBean articleTypeBean){
        MainApplication.getMyApplication().getDaoSession().getArticleTypeBeanDao().update(new ArticleTypeBean());
    }
    public List<ArticleTypeBean> findTest(){
        List<ArticleTypeBean> a= MainApplication.getMyApplication().getDaoSession().getArticleTypeBeanDao().loadAll();
        List<SingleArticle> b= MainApplication.getMyApplication().getDaoSession().getSingleArticleDao()
                .queryBuilder().where((SingleArticleDao.Properties.OwnerId.eq(a.get(0).getId()))).list();
        return a;
    }

}
