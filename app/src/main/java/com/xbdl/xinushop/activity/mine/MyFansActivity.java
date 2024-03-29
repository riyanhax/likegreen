package com.xbdl.xinushop.activity.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.mine.MyFocuseAdapter;
/*
* 我的粉丝
* */
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
        findViewById(R.id.iv_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initData() {
        MyFocuseAdapter myFocuseAdapter = new MyFocuseAdapter(this);
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(myFocuseAdapter);
        mList.setAdapter(lRecyclerViewAdapter);
    }
}
