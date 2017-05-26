package com.elearningpath.wetestx;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.elearningpath.wetestx.configs.components.DaggerAppComponent;
import com.elearningpath.wetestx.configs.modules.AppModule;
import com.elearningpath.wetestx.events.Event;
import com.elearningpath.wetestx.greendao.gen.DaoMaster;
import com.elearningpath.wetestx.greendao.gen.DaoSession;
import com.elearningpath.wetestx.utils.Constant;
import com.elearningpath.wetestx.utils.CustomDBHelper;
import com.elearningpath.wetestx.utils.RxBus;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.qiniu.pili.droid.streaming.StreamingEnv;
import com.squareup.leakcanary.LeakCanary;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */

public class MainApplication extends Application {
    private static MainApplication myApplication = null;
    private DaoMaster.OpenHelper mHelper;
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
        RxBus.getInstance().toObservable(Event.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Event>() {
                    @Override
                    public void accept(Event event) throws Exception {
                        if (event.getName().equals("hello")){
                            Toast.makeText(MainApplication.this, "你好", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        myApplication=this;
        initDataBase();
        initConstant();
        initThirdPartyFrameWork();
    }
/*
    *关于数据库的操作
    *Debug模式判断，如果目前是开发者模式，数据库一旦发生了升级行为先销毁表再创建表，数据丢失
    *如果目前不是开发者模式则采取先复制表为一个TMP_加原来表明的表，销毁数据库之后重建会将原来的表复制回去并且回复原来的名字
    *那些表需要复制回去要在utils包下面的的CustomDBHelper类下面的onUpgrade方法中加入对应的class
    */
    private void initDataBase() {
        // 通过DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的SQLiteOpenHelper 对象。
        if (Constant.ISDEBUGMODE){
            mHelper = new DaoMaster.DevOpenHelper(this,"wetestx-db", null);
        }else {
            mHelper=new CustomDBHelper(this,"wetestx-db", null);
        }
        db =mHelper.getWritableDatabase();
        // 注意：该数据库连接属于DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    private void initConstant() {
        Constant.CACAHEDIR=getApplicationContext().getExternalCacheDir().getAbsolutePath();
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
