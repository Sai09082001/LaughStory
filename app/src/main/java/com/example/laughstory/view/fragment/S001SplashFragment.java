package com.example.laughstory.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.laughstory.OnActionCallBack;
import com.example.laughstory.R;
import com.example.laughstory.viewmodel.SplashViewModel;

public class S001SplashFragment extends BaseFragment<SplashViewModel> {
    public static final String KEY_SHOW_MAIN_FRAGMENT = "KEY_SHOW_MAIN_FRAGMENT";
    private OnActionCallBack mCallBack;

    public void setmCallBack(OnActionCallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    @Override
    protected void initViews() {
        new Handler().postDelayed(this::gotoMainFragment, 2000);
    }

    @Override
    protected Class<SplashViewModel> getClassViewModel() {
        return SplashViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m001_splash_act;
    }

    private void gotoMainFragment() {
        mCallBack.onCallBack(KEY_SHOW_MAIN_FRAGMENT, null);
    }
}
