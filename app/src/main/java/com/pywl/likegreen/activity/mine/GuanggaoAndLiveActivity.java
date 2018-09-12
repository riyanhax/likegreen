package com.pywl.likegreen.activity.mine;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.SlidingTabLayout;
import com.pywl.likegreen.R;

import java.util.ArrayList;

public class GuanggaoAndLiveActivity extends AppCompatActivity {
   // private SlidingTabLayout mSt_guanggao_live;
  //  private ViewPager mViewpager_guanggao_live;
    private String[] times=new String[]{"",""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanggao_and_live);
        initView();
        initDate();
    }



    private void initView() {
    //    mSt_guanggao_live=(SlidingTabLayout)findViewById(R.id.st_guanggao_live);
     //  mViewpager_guanggao_live =(ViewPager) findViewById(R.id.viewpager_guanggao_live);
    }
    private void initDate() {
        ArrayList<Fragment> fragments = new ArrayList<>();
     //   mSt_guanggao_live.setViewPager(mViewpager_guanggao_live,times,this,fragments);
    }
}
