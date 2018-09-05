package com.pywl.likegreen.fragment.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.pywl.likegreen.R;
import com.pywl.likegreen.fragment.find.forum.ForumHotFragment;
import com.pywl.likegreen.fragment.find.forum.ForumTheNewsFragment;
import com.pywl.likegreen.fragment.find.forum.ForumTopicFragment;

import java.util.ArrayList;

/**
 * Created by theWind on 2018/8/1.
 * 论坛
 */


public class ForumFragment extends Fragment {
    String[] items=new String[]{"话题","最新","热门"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_forum, container, false);
        initView(view);
        return view;
    }

    private void initView(View v) {
        SlidingTabLayout tabLayout =(SlidingTabLayout) v.findViewById(R.id.tab_forum);
        ViewPager viewPager =(ViewPager)v.findViewById(R.id.viewpager_forum);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ForumTopicFragment());
        fragments.add(new ForumTheNewsFragment());
        fragments.add(new ForumHotFragment());
        tabLayout.setViewPager(viewPager,items,getActivity(),fragments);
    }
}
