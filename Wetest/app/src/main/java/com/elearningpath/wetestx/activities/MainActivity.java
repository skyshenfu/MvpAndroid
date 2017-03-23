package com.elearningpath.wetestx.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elearningpath.wetestx.R;
import com.elearningpath.wetestx.base.BaseActiviy;
import com.elearningpath.wetestx.base.BaseModel;
import com.elearningpath.wetestx.base.BaseView;
import com.elearningpath.wetestx.configs.components.DaggerActivityComponent;
import com.elearningpath.wetestx.configs.modules.MainModule;
import com.elearningpath.wetestx.configs.modules.ProgressMoudle;
import com.elearningpath.wetestx.configs.modules.RxbusSubscriptionModule;
import com.elearningpath.wetestx.events.Event;
import com.elearningpath.wetestx.models.MainModel;
import com.elearningpath.wetestx.presenters.MainPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;
/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */
public class MainActivity extends BaseActiviy<MainPresenter> implements BaseView {
    @Inject
    Subscription rxSbscription;
    @BindView(R.id.title_textview)
    TextView titleTextView;
    @BindView(R.id.content_textview)
    TextView contentTextView;
    @BindView(R.id.change_button)
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityType(0,"标题",true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder=ButterKnife.bind(this);
        DaggerActivityComponent.builder()
                .progressMoudle(new ProgressMoudle(this))
                .rxbusSubscriptionModule(new RxbusSubscriptionModule(new Action1<Event>() {
                    @Override
                    public void call(Event event) {
                        if (event.getName().equals("ABC")){
                            Log.e("TAG", "call: ");
                        }
                    }
                }))
                .build()
                .inject(this);
        presenter.attachMvpView(this,this);
        super.rxSbscription=this.rxSbscription;
        Log.e("ssdad", "onCreate:");
    }


    @Override
    public void showProgress() {
        lazyProgressUtils.get().progressShow();
    }
    @OnClick({R.id.change_button,R.id.act_button,R.id.watch_button})
    void onClick(View view){
        switch (view.getId()){
            case R.id.change_button:
                presenter.loadData();
                break;
            case R.id.watch_button:
                presenter.startVideoPlay();
                break;
            case R.id.act_button:
                break;
        }

    }
    @Override
    public void hideProgress() {
        lazyProgressUtils.get().progressDismiss();
    }

    @Override
    public void showData(@Nullable BaseModel model) {
        MainModel mainModel= (MainModel) model;
        titleTextView.setText(mainModel.getTitle());
        contentTextView.setText(mainModel.getNumberStr());
    }

}
