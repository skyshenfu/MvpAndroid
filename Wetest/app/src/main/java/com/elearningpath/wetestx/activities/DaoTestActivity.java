package com.elearningpath.wetestx.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.elearningpath.wetestx.R;
import com.elearningpath.wetestx.base.BaseActivity;
import com.elearningpath.wetestx.base.BaseModel;
import com.elearningpath.wetestx.base.BaseView;
import com.elearningpath.wetestx.beans.ArticleTypeBean;
import com.elearningpath.wetestx.configs.components.DaggerActivityComponent;
import com.elearningpath.wetestx.configs.modules.ProgressMoudle;
import com.elearningpath.wetestx.configs.modules.RxbusSubscriptionModule;
import com.elearningpath.wetestx.events.Event;
import com.elearningpath.wetestx.presenters.DaoTestPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/4/7
 * 版本：1.0.0
 * 描述：
 */

public class DaoTestActivity extends BaseActivity<DaoTestPresenter> implements BaseView{
    @BindView(R.id.button_insert)
    Button insertButton;
    @BindView(R.id.button_delete)
    Button deleteButton;
    @BindView(R.id.button_update)
    Button updateButton;
    @BindView(R.id.button_find)
    Button findButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setActivityType(0,"数据库操作");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daotest);
        unbinder= ButterKnife.bind(this);
        DaggerActivityComponent.builder()
                .progressMoudle(new ProgressMoudle(this))
                .rxbusSubscriptionModule(new RxbusSubscriptionModule(new Consumer<Event>() {
                    @Override
                    public void accept(Event event) throws Exception {
                        if (event.getName().equals("ABC")){
                            Log.e("TAG", "call: ");
                        }
                    }
                }))
                .build()
                .inject(this);
        presenter.attachMvpView(this,this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showData(@Nullable BaseModel model) {

    }
    @OnClick({R.id.button_insert,R.id.button_delete,R.id.button_update,R.id.button_find})
    void onClick(View view){
        switch (view.getId()){
            case R.id.button_insert:
                ArticleTypeBean a=(ArticleTypeBean) getIntent().getSerializableExtra("DATA");
                presenter.insertTest(a);
                break;
            case R.id.button_delete:
                presenter.deleteTest(1);
                break;
            case R.id.button_update:
                presenter.updateTest((ArticleTypeBean) getIntent().getSerializableExtra("DATA"));
                break;
            case R.id.button_find:
                presenter.findTest();
                break;
        }

    }
}
