package com.elearningpath.wetestx.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.elearningpath.wetestx.R;

import butterknife.Unbinder;

/**
 * Created by zhangty on 2017/2/7.
 * 一个抽象类，所有关于Mvp有关Activity的父类,主要封装resenter的销毁方法，防止内存泄漏
 */

public abstract class BaseActiviy<P extends BasePresenter> extends AppCompatActivity {

    protected int type=0;
    protected static final int TITLEBARONLYBACK=1;
    protected static final String TITLEDEFAULT="复用DEMO";
    //Presenter
    protected P presenter;
    //Butterknife的解绑对象
    protected Unbinder unbinder;
    protected TextView titleTextView;
    protected String title;
    protected LinearLayout rootLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_layout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            //先解绑Presenter和View的耦合关系
            presenter.detachMvpView();
            //再直接让Presenter为null
            presenter=null;
        }
        if (unbinder!=null){
            //解绑ButterKnife
            unbinder.unbind();
        }
    }
    private void initTop() {
        initTitle();
        initIcon();
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
                    BaseActiviy.this.finish();
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

    protected void setActivityType(int type,String title){
        this.type=type;
        this.title=title;
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
}
