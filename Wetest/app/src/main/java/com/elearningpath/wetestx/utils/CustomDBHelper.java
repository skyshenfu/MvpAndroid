package com.elearningpath.wetestx.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.elearningpath.wetestx.beans.ArticleTypeBean;
import com.elearningpath.wetestx.greendao.gen.ArticleTypeBeanDao;
import com.elearningpath.wetestx.greendao.gen.DaoMaster;
import com.elearningpath.wetestx.greendao.gen.SingleArticleDao;
import com.elearningpath.wetestx.pojos.SingleArticle;

import org.greenrobot.greendao.database.Database;

public class CustomDBHelper extends DaoMaster.OpenHelper {

    public CustomDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i("greenDAO",
            "Upgrading schema from version " + oldVersion + " to " + newVersion + " by migrating all tables data");
        // 第二个参数为要升级的Dao文件.
        MigrationHelper.getInstance().migrate(db, SingleArticleDao.class);
        MigrationHelper.getInstance().migrate(db, ArticleTypeBeanDao.class);

    }
}