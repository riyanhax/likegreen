package com.xbdl.xinushop.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.MyMessageAndDirectActivity;
import com.xbdl.xinushop.base.BaseFragment;
import com.xbdl.xinushop.fragment.note.NoteHotFragment;
import com.xbdl.xinushop.fragment.note.NoteFocusFragment;

import java.util.ArrayList;

/**
 * Created by theWind on 2018/8/1.
 */
//日记
public class HomeNoteFragment extends BaseFragment implements View.OnClickListener {
    private String[] items=new String[]{"热门","关注"};
    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;
    private View search,bell;
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
         search = v.findViewById(R.id.iv_note_search);//搜索
         bell = v.findViewById(R.id.iv_note_bell);//消息
        search.setOnClickListener(this);
        bell.setOnClickListener(this);
    }

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new NoteHotFragment());
        fragments.add(new NoteFocusFragment());
        tabLayout.setViewPager(viewPager,items,getActivity(),fragments);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_note_search:
                break;
            case R.id.iv_note_bell:
                Intent intentMyMessageAndDirectActivity = new Intent(getActivity(), MyMessageAndDirectActivity.class);
                intentMyMessageAndDirectActivity.putExtra("HomeMyFragment",0);
                startActivity(intentMyMessageAndDirectActivity);
                break;

        }
    }
}
