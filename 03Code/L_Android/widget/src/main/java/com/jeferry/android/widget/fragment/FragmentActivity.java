package com.jeferry.android.widget.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.jeferry.android.widget.R;

public class FragmentActivity extends AppCompatActivity {

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        init();
    }

    private void init() {
        findViewById(R.id.tv_add_fragment).setOnClickListener(this::onCLick);
        findViewById(R.id.tv_back_fragment).setOnClickListener(this::onCLick);
        findViewById(R.id.iv_back).setOnClickListener(this::onCLick);
    }

    private void onCLick(View view) {
        int id = view.getId();
        if (id == R.id.tv_add_fragment) {
            ParamsFragment fragment = ParamsFragment.newInstance(++i);
            addFragment(fragment);
        } else if (id == R.id.tv_back_fragment || id == R.id.iv_back) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStack();
            } else {
                finish();
            }
        }
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 添加到回退站
        transaction.addToBackStack("")
                .setCustomAnimations(R.anim.anim_dim_in, R.anim.anim_dim_out,
                        R.anim.anim_right_in, R.anim.anim_left_out)
                .add(R.id.fragment, fragment)
                .commitAllowingStateLoss();
    }
}
