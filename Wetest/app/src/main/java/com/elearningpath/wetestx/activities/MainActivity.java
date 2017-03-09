package com.elearningpath.wetestx.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elearningpath.wetestx.MainApplication;
import com.elearningpath.wetestx.R;
import com.elearningpath.wetestx.base.BaseActiviy;
import com.elearningpath.wetestx.configs.components.DaggerActivityComponent;
import com.elearningpath.wetestx.configs.modules.MainModule;
import com.elearningpath.wetestx.models.MainModel;
import com.elearningpath.wetestx.presenters.MainPresenter;
import com.elearningpath.wetestx.utils.ProgressUtils;
import com.elearningpath.wetestx.utils.StatusBarUtil;
import com.elearningpath.wetestx.views.MainView;
import com.elearningpath.wetestx.widgets.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActiviy<MainPresenter> implements MainView {
    @BindView(R.id.title_textview)
    TextView titleTextView;
    @BindView(R.id.content_textview)
    TextView contentTextView;
    @BindView(R.id.change_button)
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityType(0,"标题",true);
        setProgressEnable();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder=ButterKnife.bind(this);
        DaggerActivityComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }


    @Override
    public void showProgress() {
        progressUtils.progressShow();
    }
    @OnClick(R.id.change_button)
    void onClick(View view){
        switch (view.getId()){
            case R.id.change_button:
                presenter.loadData();
                break;
        }

    }
    @Override
    public void hideProgress() {
        progressUtils.progressDismiss();
    }

    @Override
    public void showData(MainModel mainModel) {
        titleTextView.setText(mainModel.getTitle());
        contentTextView.setText(mainModel.getNumberStr());
    }

}
