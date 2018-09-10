package com.pywl.likegreen.fragment.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
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

/*    private void initView(View view) {
        final SlidingTabLayout tabLayout =(SlidingTabLayout) view.findViewById(R.id.tab_forum);
        ViewPager viewPager =(ViewPager)view.findViewById(R.id.viewpager_forum);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ForumTopicFragment());
        fragments.add(new ForumTheNewsFragment());
        fragments.add(new ForumHotFragment());
        tabLayout.setViewPager(viewPager,items,getActivity(),fragments);

    }*/

    private void initView(View v) {
        final TabLayout tabLayout =(TabLayout) v.findViewById(R.id.tab_forum);
        ViewPager viewPager =(ViewPager)v.findViewById(R.id.viewpager_forum);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ForumTopicFragment());
        fragments.add(new ForumTheNewsFragment());
        fragments.add(new ForumHotFragment());
        viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager(),items));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView title = (TextView)(((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
                title.setTextSize(14);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView title = (TextView)(((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
                title.setTextSize(12);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //默认选中
        tabLayout.getTabAt(2).select();
    }
    public class MyPagerAdapter extends FragmentPagerAdapter{
        private String [] items;

        public MyPagerAdapter(FragmentManager fm,String[] items) {
            super(fm);
            this.items=items;
        }


        @Override
        public Fragment getItem(int position) {
            if (position==0){
                return  new ForumTopicFragment();
            }else if (position==1){
                return  new ForumTheNewsFragment();
            }else {
                return  new ForumHotFragment();
            }
        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return items[position];
        }
    }
}
