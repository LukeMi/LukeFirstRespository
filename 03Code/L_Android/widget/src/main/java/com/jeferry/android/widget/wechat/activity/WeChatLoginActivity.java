package com.jeferry.android.widget.wechat.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jeferry.android.widget.R;

public class WeChatLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText accountET;
    private EditText passwordET;
    private View accountBottomLine;
    private View passwordBottomLine;
    private Button login;
    private Button accountDel;
    private Button passwordDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat_login);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        accountET = findViewById(R.id.account);
        passwordET = findViewById(R.id.password);
        accountBottomLine = findViewById(R.id.accountBottomLine);
        passwordBottomLine = findViewById(R.id.passwordBottomLine);
        login = findViewById(R.id.login);
        accountDel = findViewById(R.id.accountDel);
        passwordDel = findViewById(R.id.passwordDel);

        //初始化控件enable属性---->>解决xml enable="false" 属性失败
        accountBottomLine.setEnabled(false);
        passwordBottomLine.setEnabled(false);

        login.setOnClickListener(this);
        accountDel.setOnClickListener(this);
        passwordDel.setOnClickListener(this);

        accountET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    accountDel.setVisibility(View.VISIBLE);
                    if (passwordET.getText().length() > 0) {
                        login.setEnabled(true);
                    } else {
                        login.setEnabled(false);
                    }
                } else {
                    accountDel.setVisibility(View.INVISIBLE);
                    login.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        accountET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    accountBottomLine.setEnabled(true);
                    if (((EditText) v).getText().length() > 0) {
                        accountDel.setVisibility(View.VISIBLE);

                    }

                } else {
                    accountBottomLine.setEnabled(false);
                    accountDel.setVisibility(View.INVISIBLE);
                }
            }
        });

//passwordET
        passwordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    passwordDel.setVisibility(View.VISIBLE);
                    if (accountET.getText().length() > 0) {
                        login.setEnabled(true);
                    } else {
                        login.setEnabled(false);
                    }
                } else {
                    passwordDel.setVisibility(View.INVISIBLE);
                    login.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        passwordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    passwordBottomLine.setEnabled(true);
                    if (passwordET.getText().length() > 0) {
                        passwordDel.setVisibility(View.VISIBLE);
                    }

                } else {
                    passwordBottomLine.setEnabled(false);
                    passwordDel.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.login) {
            Toast.makeText(this, "login....", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.accountDel) {
            accountET.setText("");
        } else if (id == R.id.passwordDel) {
            passwordET.setText("");
        }
    }
}
