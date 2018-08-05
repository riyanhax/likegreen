package com.pywl.likegreen.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.pywl.likegreen.R;
import com.pywl.likegreen.base.HomeBottomBarFragment;
import com.pywl.likegreen.fragment.FocuFragment;
import com.pywl.likegreen.fragment.RecommendedFragment;
import com.pywl.likegreen.fragment.TheNewFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by theWind on 2018/8/1.
 */

public class HomePageFragment extends HomeBottomBarFragment {

    private  SlidingTabLayout mStHomePage;//
    private ViewPager mViewpager;
    private String[] items=new String[]{"关注","推荐","最新"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View v){
         mStHomePage = (SlidingTabLayout)v.findViewById(R.id.st_home_page);
        mViewpager=(ViewPager)v.findViewById(R.id.viewpager_home_page);
    }
    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FocuFragment());
        fragments.add(new RecommendedFragment());
        fragments.add(new TheNewFragment());
        mStHomePage.setViewPager(mViewpager,items,(FragmentActivity) getActivity(),fragments);
    }
}
