package com.pywl.likegreen.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.SlidingTabLayout;
import com.pywl.likegreen.R;
import com.pywl.likegreen.fragment.mine.AuditFragment;
import com.pywl.likegreen.fragment.mine.LiveFragment;
import com.pywl.likegreen.fragment.mine.messagedirect.MyDirectFragment;
import com.pywl.likegreen.fragment.mine.messagedirect.MyMessageFragment;

import java.util.ArrayList;

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
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MyMessageFragment());
        fragments.add(new MyDirectFragment());
        mStAuditLive.setViewPager(mViewPager,times,this,fragments);
    }

    private void initView() {
        mStAuditLive=(SlidingTabLayout)findViewById(R.id.st_message_direct);
        mViewPager=(ViewPager) findViewById(R.id.viewpager_message_direct);
    }
}
