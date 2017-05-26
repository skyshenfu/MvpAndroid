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
import com.elearningpath.wetestx.base.BaseActivity;
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
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
/*
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */
public class MainActivity extends BaseActivity<MainPresenter> implements BaseView {
    @Inject
    Disposable disposable;
    @BindView(R.id.content_textview)
    TextView contentTextView;
    @BindView(R.id.change_button)
    Button button;
    @BindView(R.id.remote_button)
    Button remoteButton;
    @BindView(R.id.rxbus_button)
    Button rxButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityType(0,"标题",true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder=ButterKnife.bind(this);
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
        super.disposable=this.disposable;
        Log.e("ssdad", "onCreate:");
    }


    @Override
    public void showProgress() {
        lazyProgressUtils.get().progressShow();
    }
    @OnClick({R.id.change_button,R.id.act_button,R.id.watch_button,R.id.remote_button,R.id.rxbus_button})
    void onClick(View view){
        switch (view.getId()){
            case R.id.change_button:
                presenter.loadData();
                break;
            case R.id.watch_button:
                presenter.startVideoPlay();
                break;
            case R.id.act_button:
                presenter.startLiveShow("URL:rtmp://pili-publish.eupup.com/elearning/test?e=1490839623&token=_Z8oaTbALjft_zHJ7ebR-UromU0ephC3Ld874AfQ:_QeNsS_BWOHuf5B01nzxoUqEzRw=");
                break;
            case R.id.remote_button:
                presenter.loadRemoteData();
                break;
            case R.id.rxbus_button:
                presenter.testRx();
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
        contentTextView.setText(mainModel.getNumberStr());
    }

}
