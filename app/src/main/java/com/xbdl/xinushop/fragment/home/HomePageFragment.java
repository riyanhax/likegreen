package com.xbdl.xinushop.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mian.ApplyLiveActivity;
import com.xbdl.xinushop.activity.mine.LiveStreamingActivity;
import com.xbdl.xinushop.activity.mian.LivingActivity;
import com.xbdl.xinushop.base.BaseFragment;
import com.xbdl.xinushop.fragment.main.FocuFragment;
import com.xbdl.xinushop.fragment.main.RecommendedFragment;
import com.xbdl.xinushop.fragment.main.TheNewFragment;

import java.util.ArrayList;


/**
 * Created by theWind on 2018/8/1.
 */

public class HomePageFragment extends BaseFragment implements View.OnClickListener {
    private ImageView mIvHomeMoreBtn;
    private  SlidingTabLayout mStHomePage;//
    private ViewPager mViewpager;
    private String[] itemsRecommend=new String[]{"关注","推荐","最新"};


    private boolean isShow=true;
    private View mLlHomeLive,mRlMoreLive;
    private boolean isLive=true;
    private ArrayList<Fragment> fragmentsRecom;//推荐
    private ArrayList<Fragment> fragmentsLive;//直播

    private View mGotoLive;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View v){
         mStHomePage = v.findViewById(R.id.st_home_page);
        mViewpager=v.findViewById(R.id.viewpager_home_page);
        mIvHomeMoreBtn=v.findViewById(R.id.iv_home_more_btn);//打开直播的按钮
        mIvHomeMoreBtn.setOnClickListener(this);
        mLlHomeLive = v.findViewById(R.id.ll_home_live);//显示直播进入界面
        mRlMoreLive = v.findViewById(R.id.rl_morelive);//更多直播
        mRlMoreLive.setOnClickListener(this);
        mGotoLive=v.findViewById(R.id.rl_gotolive);
        mGotoLive.setOnClickListener(this);
        v.findViewById(R.id.rl_apply_live).setOnClickListener(this);
    }
    private void initData() {
        fragmentsRecom = new ArrayList<>();
        fragmentsRecom.add(new FocuFragment());
        fragmentsRecom.add(new RecommendedFragment());
        fragmentsRecom.add(new TheNewFragment());
        mStHomePage.setViewPager(mViewpager,itemsRecommend, getActivity(),fragmentsRecom);
        mStHomePage.setCurrentTab(1);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_home_more_btn:
                showLiveBtn(isShow);
                break;
            case R.id.rl_morelive://更多直播
                Intent intent = new Intent(getActivity(), LivingActivity.class);
                startActivity(intent);
                showLiveBtn(isShow);
                //isShow=!isShow;
                break;
            case R.id.rl_apply_live://申请直播
                Intent intentApplyLiveActivity = new Intent(getActivity(), ApplyLiveActivity.class);
                startActivity(intentApplyLiveActivity);
                showLiveBtn(isShow);
                break;
            case R.id.rl_gotolive://打开直播
                Intent intentSetAnchorActivity = new Intent(getActivity(), LiveStreamingActivity.class);
                startActivity(intentSetAnchorActivity);
                showLiveBtn(isShow);
                break;
        }
    }


    private void showLiveBtn(boolean on) {
        if (on){
            mIvHomeMoreBtn.setImageResource(R.drawable.dropdownmore);
            mLlHomeLive.setVisibility(View.VISIBLE);
            isShow=false;
            Log.v("nihaoma",isShow+"111111111111111111111111111111");
        }else {
            mIvHomeMoreBtn.setImageResource(R.drawable.more_home);
            mLlHomeLive.setVisibility(View.GONE);
            isShow=true;
            Log.v("nihaoma",isShow+"2222222222222222222222222222222222222");
        }
    }


}
