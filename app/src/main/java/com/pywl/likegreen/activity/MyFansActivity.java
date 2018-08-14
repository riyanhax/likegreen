package com.pywl.likegreen.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.pywl.likegreen.R;
import com.pywl.likegreen.adapter.MyFocuseAdapter;

public class MyFansActivity extends AppCompatActivity {
    private LRecyclerView mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fans);
        initView();
        initData();
    }



    private void initView() {
        mList=(LRecyclerView)findViewById(R.id.fans_list);
    }
    private void initData() {
        MyFocuseAdapter myFocuseAdapter = new MyFocuseAdapter(this);
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(myFocuseAdapter);
        mList.setAdapter(lRecyclerViewAdapter);
    }
}
