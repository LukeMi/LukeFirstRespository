package com.lukemi.android.tutorial.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class AbsBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
        getIntentData();
        initData(savedInstanceState);
        initView();
        bindPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initLayout() {
        if (bindLayout() != 0) {
            setContentView(bindLayout());
        } else {
            dateBind();
        }
        ButterKnife.bind(this);
    }

    /**
     * 数据绑定
     */
    protected void dateBind() {

    }

    /**
     * 绑定界面布局ID
     *
     * @return @LayoutRes
     */
    protected abstract int bindLayout();

    protected void getIntentData() {
    }

    protected void initData(Bundle savedInstanceState) {
    }

    protected void initView() {
    }

    /**
     * 绑定 presenter
     */
    protected void bindPresenter() {

    }

    /**
     * 解除绑定 presenter
     */
    protected void unBindPresenter() {

    }
}
