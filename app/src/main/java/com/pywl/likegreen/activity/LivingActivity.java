package com.pywl.likegreen.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.pywl.likegreen.R;
import com.pywl.likegreen.fragment.HomeLiveFragment;
import com.pywl.likegreen.fragment.PreviewFragment;
import com.pywl.likegreen.fragment.ReviewFragment;

import java.util.ArrayList;
/*
* 嵌套直播页面的fragment
* */
public class LivingActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] itemsLive=new String[]{"回顾","直播中","预告"};
    private SlidingTabLayout mHomeLive;
    private ViewPager mViewpager;
    private ArrayList<Fragment> fragmentsLive;
   private ImageView mReturnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living);
        initView();
        initData();
    }

    private void initView() {
        mHomeLive= (SlidingTabLayout)findViewById(R.id.st_home_live);
        mViewpager=(ViewPager) findViewById(R.id.viewpager_home_live);
        mReturnBack =(ImageView) findViewById(R.id.live_return_back);
        mReturnBack.setOnClickListener(this);
    }

    private void initData() {
        fragmentsLive = new ArrayList<>();
        fragmentsLive.add(new ReviewFragment());
        fragmentsLive.add(new HomeLiveFragment());
        fragmentsLive.add(new PreviewFragment());
        mHomeLive.setViewPager(mViewpager,itemsLive,this,fragmentsLive);
        mHomeLive.setCurrentTab(1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.live_return_back:
                finish();
                break;
        }
    }

}