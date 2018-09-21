package com.xbdl.xinushop.fragment.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.xbdl.xinushop.R;

/**
 * Created by theWind on 2018/8/1.
 */
//热门

public class SelectedFragment extends Fragment {
    private String[] items=new String[]{"推荐","多肉达人","绿植养护","温室培养","","",""};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_selected, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        SlidingTabLayout viewById = (SlidingTabLayout)view.findViewById(R.id.st_find_selected);
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.vp_find_selected);

    }
}
