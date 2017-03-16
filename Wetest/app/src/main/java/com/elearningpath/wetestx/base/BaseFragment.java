package com.elearningpath.wetestx.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Unbinder;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */
public abstract class BaseFragment<P extends BasePresenterImpl> extends Fragment {
    protected Context context;
    protected Unbinder unbinder;
    protected   P fragmentPresenter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        contextInit();
    }
    protected void contextInit(){
        this.context=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (fragmentPresenter!=null){
            //先解绑Presenter和View的耦合关系
            fragmentPresenter.detachMvpView();
            //再直接让Presenter为null
            fragmentPresenter=null;
        }
        if (unbinder!=null){
            unbinder.unbind();
        }
    }
}
