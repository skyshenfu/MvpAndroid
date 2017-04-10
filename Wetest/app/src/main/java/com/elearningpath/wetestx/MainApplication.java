package com.elearningpath.wetestx;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.elearningpath.wetestx.configs.components.AppComponent;
import com.elearningpath.wetestx.configs.components.DaggerAppComponent;
import com.elearningpath.wetestx.configs.modules.AppModule;
import com.elearningpath.wetestx.greendao.gen.DaoMaster;
import com.elearningpath.wetestx.greendao.gen.DaoSession;
import com.elearningpath.wetestx.utils.Constant;
import com.elearningpath.wetestx.utils.NetApi;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.qiniu.pili.droid.streaming.StreamingEnv;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;


/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */

public class MainApplication extends Application {
    private static MainApplication myApplication = null;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static MainApplication getApplication() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build().inject(this);
        myApplication=this;
        initDataBase();
        initConstant();
        initThirdPartyFrameWork();
    }

    private void initDataBase() {
        // 通过DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为greenDAO 已经帮你做了。
        // 注意：默认的DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this,"wetestx-db", null);
        db =mHelper.getWritableDatabase();
        // 注意：该数据库连接属于DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    private void initConstant() {
        Constant.CACAHEDIR=getApplicationContext().getExternalCacheDir().getAbsolutePath();
        Logger.e(Constant.CACAHEDIR);
    }

    /**
     * 第三方框架的的初始化方法
     */
    private void initThirdPartyFrameWork() {
        //LeakCanary一个脱了低级趣味的若引用框架
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        //Logger一个简练帅气的Logger组件
        if (Constant.ISDEBUGMODE) {
            Logger.init(Constant.LOGGERTAG);
        } else {
            Logger.init().logLevel(LogLevel.NONE);
        }
        //七牛云视频&没有更坏的地，只有累死的牛
        StreamingEnv.init(getApplicationContext());

    }

    /**
     * 判断网络状态的方法
     * @return
     */

    public static boolean  getNetStatus(){
        ConnectivityManager connectivity = (ConnectivityManager) MainApplication.getApplication().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity!=null){
            NetworkInfo info=connectivity.getActiveNetworkInfo();
            if (info != null && info.getState()== NetworkInfo.State.CONNECTED) {
                return true;
            }
        }

        return false;
    }
    public static MainApplication getMyApplication(){
        return myApplication;
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }


    public SQLiteDatabase getDb() {
        return db;
    }
}
