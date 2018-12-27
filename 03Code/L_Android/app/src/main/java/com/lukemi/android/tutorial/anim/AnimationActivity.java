package com.lukemi.android.tutorial.anim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.AbsBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AnimationActivity extends AbsBaseActivity {


    @BindView(R.id.tv_title_type1)
    TextView tvTitleType1;
    private AnimationActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
        protected int bindLayout() {
            return R.layout.activity_animation;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mContext = this;
    }

    @Override
    protected void initView() {
        tvTitleType1.setText("动画练习");
    }

    private void transition() {
  /*      TransitionDrawable drawable = (TransitionDrawable) getResources().getDrawable(R.drawable.transition_play_status);
        imgAnim.setImageDrawable(drawable);
        drawable.startTransition(200);*/
    }


    @OnClick({R.id.img_back, R.id.btn_tween, R.id.btn_fram, R.id.btn_property})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_tween:
                intent = new Intent(mContext, TweenAnimActivity.class);
                break;
            case R.id.btn_fram:
                intent = new Intent(mContext, FrameAnimActivity.class);
                break;
            case R.id.btn_property:
                intent = new Intent(mContext, PropertyAnimActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
