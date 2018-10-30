package com.xbdl.xinushop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.xbdl.xinushop.R;

import uk.co.senab.photoview.PhotoView;

public class PhtotViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getIntent().getStringExtra("url");
        setContentView(R.layout.activity_phtot_view);
        findViewById(R.id.iv_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        PhotoView photoView =(PhotoView) findViewById(R.id.photoview);
        Log.v("nihaoma",url);
        Glide.with(this).load(url).into(photoView);
    }
}
