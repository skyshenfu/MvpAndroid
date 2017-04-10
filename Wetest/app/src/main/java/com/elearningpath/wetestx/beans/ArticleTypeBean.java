package com.elearningpath.wetestx.beans;

import com.elearningpath.wetestx.pojos.SingleArticle;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.io.Serializable;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.elearningpath.wetestx.greendao.gen.DaoSession;
import com.elearningpath.wetestx.greendao.gen.SingleArticleDao;
import com.elearningpath.wetestx.greendao.gen.ArticleTypeBeanDao;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/4/5
 * 版本：1.0.0
 * 描述：
 */
@Entity
public class ArticleTypeBean extends DataInterface implements Serializable{
    private static final long serialVersionUID = 2432359825875369797L;
    @Id(autoincrement = true)
    private Long id;
    private int sssss;
    @ToMany(referencedJoinProperty = "ownerId")
    private List<SingleArticle> dataList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1676281106)
    private transient ArticleTypeBeanDao myDao;
    @Generated(hash = 536046571)
    public ArticleTypeBean(Long id, int sssss) {
        this.id = id;
        this.sssss = sssss;
    }
    @Generated(hash = 1428609743)
    public ArticleTypeBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getSssss() {
        return this.sssss;
    }
    public void setSssss(int sssss) {
        this.sssss = sssss;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 198868074)
    public List<SingleArticle> getDataList() {
        if (dataList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SingleArticleDao targetDao = daoSession.getSingleArticleDao();
            List<SingleArticle> dataListNew = targetDao
                    ._queryArticleTypeBean_DataList(id);
            synchronized (this) {
                if (dataList == null) {
                    dataList = dataListNew;
                }
            }
        }
        return dataList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 774597464)
    public synchronized void resetDataList() {
        dataList = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 774794877)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getArticleTypeBeanDao() : null;
    }




}
