package com.lukemi.android.tutorial.viewtest;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.common.view.SingleLineLayout;
import com.lukemi.android.tutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleLineLinearlayoutActivity extends AppCompatActivity {

    @BindView(R.id.sll)
    SingleLineLayout sll;
    private String[] list = new String[]{
            "我的", "hello", "我的啊",
            "我的need", "我的OMG",
            "hello", "我的啊",
            "我的need", "我的OMG",
            "hello", "我的啊",
            "我的need", "我的OMG"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_line_linearlayout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        sll.setOnSinglineAddCompleteListener(new SingleLineLayout.OnSinglineAddCompleteListener() {
            @Override
            public void onSinglineComplite(int childCount) {
                Toast.makeText(SingleLineLinearlayoutActivity.this, "第 " + (childCount + 1) + " 个超出容器宽度", Toast.LENGTH_SHORT).show();
            }
        });
        for (int i = 0; i < list.length; i++) {
            TextView tv = new TextView(this);
            tv.setGravity(Gravity.CENTER);
            tv.setText(list[i]);
            tv.setTextColor(getResources().getColor(R.color.green));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp13));
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(10, 10, 10, 10);
            ViewGroup.LayoutParams lp = tv.getLayoutParams();
//            lp.s
            sll.addView(tv);
        }
    }
}
