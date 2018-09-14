package com.pywl.likegreen.activity.mian;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.pywl.likegreen.R;
import com.pywl.likegreen.fragment.live.HomeLiveFragment1;
import com.pywl.likegreen.fragment.live.PreviewFragment;
import com.pywl.likegreen.fragment.live.ReviewFragment;

import java.util.ArrayList;
/*
* 嵌套直播页面
* */
public class LivingActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] itemsLive=new String[]{"回顾","直播中","预告"};//123
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
        fragmentsLive.add(new HomeLiveFragment1());
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
