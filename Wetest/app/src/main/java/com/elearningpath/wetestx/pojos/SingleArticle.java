package com.elearningpath.wetestx.pojos;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/4/5
 * 版本：1.0.0
 * 描述：
 */
@Entity
public class SingleArticle implements Serializable{
    private static final long serialVersionUID = -8316690181978817165L;
    @Id(autoincrement = true)
    private Long id;
    private Long ownerId;
    private long classid;
    private int displayorder;
    private String name;

    public SingleArticle() {
    }

    @Generated(hash = 2127266108)
    public SingleArticle(Long id, Long ownerId, long classid, int displayorder,
            String name) {
        this.id = id;
        this.ownerId = ownerId;
        this.classid = classid;
        this.displayorder = displayorder;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public long getClassid() {
        return this.classid;
    }

    public void setClassid(long classid) {
        this.classid = classid;
    }

    public int getDisplayorder() {
        return this.displayorder;
    }

    public void setDisplayorder(int displayorder) {
        this.displayorder = displayorder;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
