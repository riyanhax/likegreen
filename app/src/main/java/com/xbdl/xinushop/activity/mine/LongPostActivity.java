package com.xbdl.xinushop.activity.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.xbdl.xinushop.R;
/*
* 长贴子
* */
public class LongPostActivity extends AppCompatActivity {
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
        title.setText(getResources().getString(R.string.longpost));
        search.setHint("搜索我的长帖子");
    }
}
