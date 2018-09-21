package com.xbdl.xinushop.activity.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.MyFocuseAdapter;

import java.util.List;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.callback.GetUserInfoListCallback;
import cn.jpush.im.android.api.model.UserInfo;
/*
* 我的关注
* */
public class MyFocuseActivity extends AppCompatActivity {
    private LRecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_focuse);
        initView();
        initData();

    }


    private void initView() {
       mList = (LRecyclerView)findViewById(R.id.focuse_list);
      findViewById(R.id.focuse_return).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              finish();
          }
      });

    }

    private void initData() {
        ContactManager.getFriendList(new GetUserInfoListCallback() {
            @Override
            public void gotResult(int i, String s, List<UserInfo> list) {
                if (0 == i) {
                    //获取好友列表成功
                } else {
                    //获取好友列表失败
                }
            }
        });
        MyFocuseAdapter myFocuseAdapter = new MyFocuseAdapter(this);
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(myFocuseAdapter);
        mList.setAdapter(lRecyclerViewAdapter);
    }
}
