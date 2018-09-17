package com.pywl.likegreen.activity.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.pywl.likegreen.R;
/*
* 参与话题
* */
public class JoinThemeActivity extends AppCompatActivity {

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
        title.setText(getResources().getString(R.string.jiontheme));
        search.setHint("搜索我参与的话题");
    }
}