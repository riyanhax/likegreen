package com.pywl.likegreen.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.pywl.likegreen.R;
import com.pywl.likegreen.base.HomeBottomBarFragment;
import com.pywl.likegreen.fragment.mine.MyGardenFragment;
import com.pywl.likegreen.fragment.mine.MyLiveFragment;
import com.pywl.likegreen.fragment.mine.MyVideoFragment;
import com.pywl.likegreen.fragment.mine.MyWeddingCardFragment;

import java.util.ArrayList;

/**
 * Created by theWind on 2018/8/1.
 */

public class HomeMyFragment extends HomeBottomBarFragment {
    private SlidingTabLayout mStMy;
    private ViewPager mViewpagerMy;
    private String[] items=new String[]{"视频","直播","花园","喜帖"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_my, container, false);
        initView(view);
        initData();
        return view;
    }
   private void initView(View v){
        mStMy = (SlidingTabLayout)v.findViewById(R.id.st_my);
        mViewpagerMy = (ViewPager)v.findViewById(R.id.viewpager_my);
   }
    private void initData(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MyVideoFragment());
        fragments.add(new MyLiveFragment());
        fragments.add(new MyGardenFragment());
        fragments.add(new MyWeddingCardFragment());
        mStMy.setViewPager(mViewpagerMy,items,getActivity(),fragments);
    }
}
