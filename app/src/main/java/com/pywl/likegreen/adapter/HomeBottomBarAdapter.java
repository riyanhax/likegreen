package com.pywl.likegreen.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pywl.likegreen.base.HomeBottomBarFragment;

import java.util.List;

/**
 * Created by theWind on 2018/8/1.
 */

public class HomeBottomBarAdapter extends FragmentPagerAdapter {
    List<HomeBottomBarFragment> mData;
    public HomeBottomBarAdapter(FragmentManager fm) {
        super(fm);
    }
    public HomeBottomBarAdapter(FragmentManager fm,List<HomeBottomBarFragment> data) {
        super(fm);
        mData=data;
    }

    @Override
    public Fragment getItem(int position) {

        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

}
