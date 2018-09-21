package com.xbdl.xinushop.activity.mine;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.SlidingTabLayout;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.fragment.mine.AuditFragment;
import com.xbdl.xinushop.fragment.mine.LiveFragment;
import com.xbdl.xinushop.fragment.mine.messagedirect.MyDirectFragment;
import com.xbdl.xinushop.fragment.mine.messagedirect.MyMessageFragment;

import java.util.ArrayList;

import cn.jiguang.api.JCoreInterface;

/*
* 消息和私信页
* */
public class MyMessageAndDirectActivity extends AppCompatActivity {
    private SlidingTabLayout mStAuditLive;
    private ViewPager mViewPager;
    private String[] times=new String[]{"消息","私信"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message_and_direct);
        initView();
        initData();
    }
    private void initData() {
        Intent intent = getIntent();
        int number = intent.getIntExtra("HomeMyFragment", 0);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MyMessageFragment());
        fragments.add(new MyDirectFragment());
        mStAuditLive.setViewPager(mViewPager,times,this,fragments);
        mStAuditLive.setCurrentTab(number);
    }

    private void initView() {
        mStAuditLive=(SlidingTabLayout)findViewById(R.id.st_message_direct);
        mViewPager=(ViewPager) findViewById(R.id.viewpager_message_direct);
    }
    @Override
    protected void onPause() {
        JCoreInterface.onPause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        JCoreInterface.onResume(this);
        super.onResume();
    }
}