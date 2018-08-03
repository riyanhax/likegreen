package com.pywl.likegreen.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.SlidingTabLayout;
import com.pywl.likegreen.R;
import com.pywl.likegreen.fragment.mine.AuditFragment;
import com.pywl.likegreen.fragment.mine.LiveFragment;

import java.util.ArrayList;

public class AuditAndLiveActivity extends AppCompatActivity {
    private SlidingTabLayout mStAuditLive;
    private ViewPager mViewPager;
    private String[] times=new String[]{"广告视频","直播"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_and_live);
        initView();
        initData();
    }

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AuditFragment());
        fragments.add(new LiveFragment());
        mStAuditLive.setViewPager(mViewPager,times,this,fragments);
    }

    private void initView() {
        mStAuditLive=(SlidingTabLayout)findViewById(R.id.st_audit_live);
        mViewPager=(ViewPager) findViewById(R.id.st_audit_live);
    }
}
