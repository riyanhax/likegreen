package com.pywl.likegreen.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
    private int tab;
    private Fragment selectedFragment,forumFragment,shoppingMallFragment;
    private RadioButton selected,forum,shoppingmall;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_find, container, false);
        initView(view);
        //initFragment();
        initData();
        return view;

    }
    private void initView(View v) {
        tabLayout = (SlidingTabLayout)v.findViewById(R.id.st_home_find);
        viewPager = (ViewPager)v.findViewById(R.id.viewpager_home_find);
/*        RadioGroup radioGroup = (RadioGroup)v.findViewById(R.id.rg_find);
         selected =(RadioButton) v.findViewById(R.id.selected);
         forum =(RadioButton) v.findViewById(R.id.forum);
         shoppingmall =(RadioButton) v.findViewById(R.id.shoppingmall);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                setTab(checkedId);
            }
        });*/
    }


/*    private void initFragment() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (selectedFragment == null) {
            selectedFragment = new SelectedFragment();
            transaction.add(R.id.fragment_container, selectedFragment);
        }
        transaction.commitAllowingStateLoss();
    }*/

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new SelectedFragment());
        fragments.add(new ForumFragment());
        fragments.add(new ShoppingMallFragment());
        tabLayout.setViewPager(viewPager,items,getActivity(),fragments);
    }

/*    public void setTab(int tab) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (tab){
            case R.id.selected:
                if (selectedFragment == null) {
                    selectedFragment = new SelectedFragment();
                    transaction.add(R.id.fragment_container, selectedFragment);
                } else {
                    transaction.show(selectedFragment);
                }
                break;
            case R.id.forum:
                if (forumFragment == null) {
                    forumFragment = new ForumFragment();
                    transaction.add(R.id.fragment_container, forumFragment);
                } else {
                    transaction.show(forumFragment);
                }
                break;
            case R.id.shoppingmall:
                if (shoppingMallFragment == null) {
                    shoppingMallFragment = new ShoppingMallFragment();
                    transaction.add(R.id.fragment_container, shoppingMallFragment);
                } else {
                    transaction.show(shoppingMallFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (selectedFragment != null) {
            transaction.hide(selectedFragment);
        }
        if (forumFragment != null) {
            transaction.hide(forumFragment);
        }
        if (shoppingMallFragment != null) {
            transaction.hide(shoppingMallFragment);
        }

        selected.setChecked(false);
        forum.setChecked(false);
        shoppingmall.setChecked(false);
    }*/
}
