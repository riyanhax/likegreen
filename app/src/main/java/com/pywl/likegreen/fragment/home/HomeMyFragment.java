package com.pywl.likegreen.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.zhouwei.library.CustomPopWindow;
import com.flyco.tablayout.SlidingTabLayout;
import com.pywl.likegreen.MainActivity;
import com.pywl.likegreen.R;
import com.pywl.likegreen.activity.MyMessageAndDirectActivity;
import com.pywl.likegreen.activity.SystemSettingsActivity;
import com.pywl.likegreen.base.HomeBottomBarFragment;
import com.pywl.likegreen.fragment.mine.MyGardenFragment;
import com.pywl.likegreen.fragment.mine.MyLiveFragment;
import com.pywl.likegreen.fragment.mine.MyVideoFragment;
import com.pywl.likegreen.fragment.mine.MyWeddingCardFragment;
import com.pywl.likegreen.utils.HomeGetFragmentUtil;

import java.util.ArrayList;

/**
 * Created by theWind on 2018/8/1.
 */

public class HomeMyFragment extends HomeBottomBarFragment implements View.OnClickListener {

    private SlidingTabLayout mStMy;
    private ViewPager mViewpagerMy;
    private View mMyMore,mSystemSetting,mMsgDriect;
    private CustomPopWindow mCustomPopWindow;
    private String[] items=new String[]{"视频","直播","花园","喜帖"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_my, container, false);
        initView(view);
        initData();
        return view;
    }
   private void initView(View v){
        mStMy = (SlidingTabLayout)v.findViewById(R.id.st_my);
        mViewpagerMy = (ViewPager)v.findViewById(R.id.viewpager_home_my);
        mMyMore = v.findViewById(R.id.my_more);//弹popuwindow
        mSystemSetting=v.findViewById(R.id.system_setting);//系统设置
        mMsgDriect=v.findViewById(R.id.msg_driect);//消息私信
        mMyMore.setOnClickListener(this);
        mSystemSetting.setOnClickListener(this);
        mMsgDriect.setOnClickListener(this);
   }
    private void initData(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MyVideoFragment());
        fragments.add(new MyLiveFragment());
        fragments.add(new MyGardenFragment());
        fragments.add(new MyWeddingCardFragment());
        mStMy.setViewPager(mViewpagerMy,items,getActivity(),fragments);
        //mStMy.setViewPager(mViewpagerMy);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_more:
                showPopwindow();
                break;
            case R.id.system_setting:
                Intent intentSystemSetting = new Intent(getActivity(), SystemSettingsActivity.class);
                startActivity(intentSystemSetting);
                break;
            case R.id.msg_driect:
                Intent intentMyMessageAndDirectActivity = new Intent(getActivity(), MyMessageAndDirectActivity.class);
                startActivity(intentMyMessageAndDirectActivity);
                break;
                default:
                    break;
        }
    }
    private void showPopwindow(){
        Log.v("nihaoma ","1111111111111111111111111111112222");
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_menu,null);
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(R.layout.pop_menu)
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.7f) // 控制亮度
                //.setFocusable(true)//是否获取焦点，默认为ture
               /* .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.e("TAG","onDismiss");
                    }
                })*/
                .create()
                .showAsDropDown(mMyMore,-70,5);


    }
    /**
     * 处理弹出显示内容、点击事件等逻辑
     * @param contentView
     */
    private void handleLogic(View contentView){

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCustomPopWindow!=null){
                    mCustomPopWindow.dissmiss();
                }
                String showContent = "";
                switch (v.getId()){
                    case R.id.pop_privateletter:
                        showContent = "点击 Item菜单1";
                        Log.v("nihaoma","11111111111111111111111111111");
                        break;
                    case R.id.pop_shoppingcar:
                        showContent = "点击 Item菜单2";
                        break;
                    case R.id.pop_order:
                        showContent = "点击 Item菜单3";
                        break;
                    case R.id.pop_coupon:
                        showContent = "点击 Item菜单4";
                        break;
                    case R.id.pop_audit:
                        showContent = "点击 Item菜单5" ;
                        break;
                    case R.id.pop_mycollection:
                        showContent = "点击 Item菜单6" ;
                        break;
                    case R.id.pop_mygoods:
                        showContent = "点击 Item菜单7" ;
                        break;
                }
                Toast.makeText(getActivity(),showContent,Toast.LENGTH_SHORT).show();
            }
        };
        contentView.findViewById(R.id.pop_privateletter).setOnClickListener(listener);
        contentView.findViewById(R.id.pop_shoppingcar).setOnClickListener(listener);
        contentView.findViewById(R.id.pop_order).setOnClickListener(listener);
        contentView.findViewById(R.id.pop_coupon).setOnClickListener(listener);
        contentView.findViewById(R.id.pop_audit).setOnClickListener(listener);
        contentView.findViewById(R.id.pop_mycollection).setOnClickListener(listener);
        contentView.findViewById(R.id.pop_mygoods).setOnClickListener(listener);
    }
/*    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }*/
}

