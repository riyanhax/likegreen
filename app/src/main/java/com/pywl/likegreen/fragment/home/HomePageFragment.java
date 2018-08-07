package com.pywl.likegreen.fragment.home;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

public class HomePageFragment extends HomeBottomBarFragment implements View.OnClickListener {
    private ImageView mIvHomeMoreBtn;
    private  SlidingTabLayout mStHomePage;//
    private ViewPager mViewpager;
    private String[] items=new String[]{"关注","推荐","最新"};

    private boolean isShow=false;
    private View mLlHomeLive;
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
        mIvHomeMoreBtn=(ImageView)v.findViewById(R.id.iv_home_more_btn);//打开直播的按钮
        mIvHomeMoreBtn.setOnClickListener(this);
        mLlHomeLive = v.findViewById(R.id.ll_home_live);//显示直播进入界面
    }
    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FocuFragment());
        fragments.add(new RecommendedFragment());
        fragments.add(new TheNewFragment());
        mStHomePage.setViewPager(mViewpager,items,(FragmentActivity) getActivity(),fragments);
        mStHomePage.setCurrentTab(1);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_home_more_btn:
                showLiveBtn(isShow);
                break;
        }
    }



    private void showLiveBtn(boolean on) {
        if (on){
            mIvHomeMoreBtn.setImageResource(R.drawable.dropdownmore);
            mLlHomeLive.setVisibility(View.GONE);
            isShow=false;
            Log.v("nihaoma",isShow+"111111111111111111111111111111");
        }else {
            mIvHomeMoreBtn.setImageResource(R.drawable.more_home);
            mLlHomeLive.setVisibility(View.VISIBLE);
            isShow=true;
            Log.v("nihaoma",isShow+"2222222222222222222222222222222222222");
        }
    }


}
