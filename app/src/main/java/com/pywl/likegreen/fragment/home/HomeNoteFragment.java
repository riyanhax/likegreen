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
import com.pywl.likegreen.fragment.note.NoteHotFragment;
import com.pywl.likegreen.fragment.note.NoteFocusFragment;

import java.util.ArrayList;

/**
 * Created by theWind on 2018/8/1.
 */
//日记
public class HomeNoteFragment extends HomeBottomBarFragment {
    private String[] items=new String[]{"热门","关注"};
    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_note, container, false);
        initView(view);
        initData();
        return view;

    }

    private void initView(View v) {
         tabLayout = (SlidingTabLayout)v.findViewById(R.id.st_home_riji);
         viewPager = (ViewPager)v.findViewById(R.id.viewpager_home_riji);
    }

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new NoteHotFragment());
        fragments.add(new NoteFocusFragment());
        tabLayout.setViewPager(viewPager,items,getActivity(),fragments);
    }
}
