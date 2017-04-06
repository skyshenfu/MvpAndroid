package com.elearningpath.wetestx.beans;

import com.elearningpath.wetestx.pojos.SingleArticle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/4/5
 * 版本：1.0.0
 * 描述：
 */

public class ArticleTypeBean extends DataInterface implements Serializable{
    private List<SingleArticle> dataList;

    public List<SingleArticle> getDatalist() {
        return dataList;
    }

    public void setDatalist(List<SingleArticle> datalist) {
        this.dataList = datalist;
    }
}
