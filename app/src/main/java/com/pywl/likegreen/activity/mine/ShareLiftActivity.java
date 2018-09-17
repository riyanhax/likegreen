package com.pywl.likegreen.activity.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pywl.likegreen.R;

public class ShareLiftActivity extends AppCompatActivity {
    private TextView title;
    private EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_lift);
        initView();
        initData();
    }

    private void initView() {
         title =(TextView) findViewById(R.id.tv_title);
        search=(EditText) findViewById(R.id.et_search);
    }

    private void initData() {
        title.setText(getResources().getString(R.string.sharelift));
        search.setHint("搜索我的分享");
    }
}
