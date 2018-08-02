package com.pywl.likegreen.activity;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.pywl.likegreen.R;
import com.pywl.likegreen.customtabentitys.DirMsgEntity;
import com.pywl.likegreen.fragment.mine.MyDirectFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

//消息、私信页
public class DirectMessagesActivity extends AppCompatActivity {
    @BindView(R.id.tab_dir_msg)
    CommonTabLayout tab_dir_msg;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_messages);
        ButterKnife.bind(this);

        ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
        DirMsgEntity dirMsgEntity = new DirMsgEntity();
        customTabEntities.add(dirMsgEntity);
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new MyDirectFragment());
        fragmentArrayList.add(new MyDirectFragment());

        setTabData(customTabEntities,getFragmentManager(),1,fragmentArrayList);

    }
    /** 关联数据支持同时切换fragments */
    public void setTabData(ArrayList<CustomTabEntity> tabEntitys, FragmentManager fm, int containerViewId, ArrayList<Fragment> fragments){

    }
}
