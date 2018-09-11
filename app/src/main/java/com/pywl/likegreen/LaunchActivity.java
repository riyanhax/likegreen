package com.pywl.likegreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);


        new Handler().postDelayed(r, 2000); //设置2秒钟后切换到下个Activity
    }

    Runnable r = new Runnable() {
        public void run() {
            Intent intent = new Intent();
            intent.setClass(LaunchActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
