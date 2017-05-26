package com.elearningpath.wetestx.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.elearningpath.wetestx.R;
import com.elearningpath.wetestx.activities.MainActivity;
import com.elearningpath.wetestx.utils.ProgressUtils;
import com.elearningpath.wetestx.utils.StatusBarUtil;

import javax.inject.Inject;

import butterknife.Unbinder;
import dagger.Lazy;
import io.reactivex.disposables.Disposable;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */

public abstract class BaseActivity<P extends BasePresenterImpl> extends AppCompatActivity {

    protected int type=0;
    protected static final int TITLEBARONLYBACK=1;
    protected static final String TITLEDEFAULT="复用DEMO";
    //Presenter
    @Inject
    protected P presenter;
    @Inject
    protected Lazy<ProgressUtils> lazyProgressUtils;
    //Butterknife的解绑对象
    protected Unbinder unbinder;
   protected Disposable disposable=null;
    protected boolean isMainActivity=false;

    protected TextView titleTextView;
    protected String title;
    protected LinearLayout rootLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isMainActivity){
            if (savedInstanceState!=null){
                this.finish();
                startActivity(new Intent(this,MainActivity.class));
            }
        }else {
            if (savedInstanceState!=null){
                initRestore(savedInstanceState);
            }
        }
        super.setContentView(R.layout.base_layout);
    }

    protected  void initRestore(Bundle savedInstanceState){

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable!=null){
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }

        }
        if (presenter!=null){
            if (presenter.getCompositeDisposable()!=null){
                presenter.getCompositeDisposable().clear();
                Log.e("A", "onDestroy: ");
            }
            //先解绑Presenter和View的耦合关系
            presenter.detachMvpView();
            //再直接让Presenter为null
            presenter=null;
            Log.e("TAG", "onDestroy: 4");
        }else {
            Log.e("TAG", "onDestroy: 5");

        }
        if (unbinder!=null){
            //解绑ButterKnife
            unbinder.unbind();
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(this.getCurrentFocus()!=null){
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    protected void setActivityType(int type,String title){
        this.type=type;
        this.title=title;
    };
    protected void setActivityType(int type,String title,boolean isMain){
        this.type=type;
        this.title=title;
        this.isMainActivity=isMain;
    };
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(View.inflate(this,layoutResID,null));
    }

    @Override
    public void setContentView(View view) {
        rootLayout= (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout==null){
            return;
        }
        rootLayout.addView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initTop();
    }

    private void initTop() {
        initTitle();
        initIcon();
        initStatusBar();
    }

    private void initStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent),0);
    }


    private void initIcon() {
        ImageView backButton= (ImageView) rootLayout.findViewById(R.id.im_back);
        ImageView menuButton=(ImageView) rootLayout.findViewById(R.id.im_other);
        switch (type){
            case TITLEBARONLYBACK:
                menuButton.setVisibility(View.GONE);
                break;
            default:
                backButton.setVisibility(View.GONE);
                menuButton.setVisibility(View.GONE);
                break;
        }
        if (backButton.getVisibility()!=View.GONE){
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseActivity.this.finish();
                }
            });
        }

    }

    private void initTitle() {
        if (title==null){
            title=TITLEDEFAULT;
        }
        titleTextView= (TextView) rootLayout.findViewById(R.id.title);
        titleTextView.setText(title);
    }
}
