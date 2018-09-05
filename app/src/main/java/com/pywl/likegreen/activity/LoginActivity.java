package com.pywl.likegreen.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.pywl.likegreen.R;

public class LoginActivity extends AppCompatActivity {
    private EditText mPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mPhoneNumber =(EditText) findViewById(R.id.et_phone_number);

    }
}
