package com.lukemi.android.tutorial.sessionlifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lukemi.android.tutorial.R;

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
    }
}
