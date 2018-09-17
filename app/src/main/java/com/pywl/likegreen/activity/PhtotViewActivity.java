package com.pywl.likegreen.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pywl.likegreen.R;

import uk.co.senab.photoview.PhotoView;

public class PhtotViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phtot_view);
        PhotoView photoView =(PhotoView) findViewById(R.id.photoview);

    }
}
