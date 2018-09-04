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
import com.pywl.likegreen.fragment.find.ForumFragment;
import com.pywl.likegreen.fragment.find.SelectedFragment;
import com.pywl.likegreen.fragment.find.ShoppingMallFragment;
import com.pywl.likegreen.fragment.note.NoteFocusFragment;
import com.pywl.likegreen.fragment.note.NoteHotFragment;

import java.util.ArrayList;

/**
 * Created by theWind on 2018/8/1.
 */

public class HomeFindFragment extends HomeBottomBarFragment {
    private String[] items=new String[]{"精选","论坛","商城"};
    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_find, container, false);
        initView(view);
        initData();
        return view;

    }
    private void initView(View v) {
        tabLayout = (SlidingTabLayout)v.findViewById(R.id.st_home_find);
        viewPager = (ViewPager)v.findViewById(R.id.viewpager_home_find);
    }

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new SelectedFragment());
        fragments.add(new ForumFragment());
        fragments.add(new ShoppingMallFragment());
        tabLayout.setViewPager(viewPager,items,getActivity(),fragments);
    }
}
