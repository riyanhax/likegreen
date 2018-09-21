package com.xbdl.xinushop.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseFragment;
import com.xbdl.xinushop.fragment.find.ForumFragment;
import com.xbdl.xinushop.fragment.find.SelectedFragment;
import com.xbdl.xinushop.fragment.find.ShoppingMallFragment;

import java.util.ArrayList;

/**
 * Created by theWind on 2018/8/1.
 */

public class HomeFindFragment extends BaseFragment implements View.OnClickListener {
    private String[] items=new String[]{"精选","论坛","商城"};
    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;
    private int tab;
    private Fragment selectedFragment,forumFragment,shoppingMallFragment;
    private RadioButton selected,forum,shoppingmall;
    private ImageView equipment,shoppingCar;//应用设备  购物车
    private View downView;
    private boolean isShow=true;
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
        equipment = (ImageView)v.findViewById(R.id.iv_find_equipment);//应用设备
        equipment.setOnClickListener(this);
        shoppingCar = (ImageView)v.findViewById(R.id.iv_find_shopping);//购物
        downView = v.findViewById(R.id.find_downview);
        v.findViewById(R.id.view_transparent).setOnClickListener(this);
        }


    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new SelectedFragment());
        fragments.add(new ForumFragment());
        fragments.add(new ShoppingMallFragment());
        tabLayout.setViewPager(viewPager,items,getActivity(),fragments);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position){
                    case 0:
                    case 1:
                        equipment.setVisibility(View.VISIBLE);
                        shoppingCar.setVisibility(View.GONE);
                        break;
                    case 2:
                        equipment.setVisibility(View.GONE);
                        shoppingCar.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_find_equipment:
                showdownView(isShow);
                break;
            case R.id.view_transparent:
                showdownView(isShow);
                break;
        }
    }

    private void showdownView(boolean b) {
        if (b){
            downView.setVisibility(View.VISIBLE);
            isShow=false;
            Log.v("nihaoma",isShow+"1111111");
        }else {
            downView.setVisibility(View.GONE);
            isShow=true;
            Log.v("nihaoma",isShow+"2222222222222");
        }
    }


}


















/*    private void initView(View v) {
        tabLayout = (SlidingTabLayout)v.findViewById(R.id.st_home_find);
        viewPager = (ViewPager)v.findViewById(R.id.viewpager_home_find);
        equipment = (ImageView)v.findViewById(R.id.iv_find_equipment);
        RadioGroup radioGroup = (RadioGroup)v.findViewById(R.id.rg_find);
         selected =(RadioButton) v.findViewById(R.id.selected);
         forum =(RadioButton) v.findViewById(R.id.forum);
         shoppingmall =(RadioButton) v.findViewById(R.id.shoppingmall);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                setTab(checkedId);
            }
        });
    }*/





/*    private void initFragment() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (selectedFragment == null) {
            selectedFragment = new SelectedFragment();
            transaction.add(R.id.fragment_container, selectedFragment);
        }
        transaction.commitAllowingStateLoss();
    }*/



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

