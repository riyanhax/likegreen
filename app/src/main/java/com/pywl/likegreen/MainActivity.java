package com.pywl.likegreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.View;

import com.pywl.likegreen.adapter.BaseActivity;
import com.pywl.likegreen.fragment.home.HomeAddFragment;
import com.pywl.likegreen.fragment.home.HomeFindFragment;
import com.pywl.likegreen.fragment.home.HomeMyFragment;
import com.pywl.likegreen.fragment.home.HomeNoteFragment;
import com.pywl.likegreen.fragment.home.HomePageFragment;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";//测试url
    private Class<Fragment>[] mFragments = new Class[]{HomePageFragment.class, HomeNoteFragment.class, HomeAddFragment.class,
            HomeFindFragment.class,HomeMyFragment.class};
    private final int TAB_HOME = 0, TAB_NOTE = 1, TAB_ADD = 2, TAB_FIND = 3,TAB_MY=4;
    private View[] mTabViews;
    private FragmentTabHost mTabHost;
    private int mSelectedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initTab();
    }

    protected void initData() {


    }


    public void initView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setVisibility(View.GONE);
        for (Class<Fragment> item : mFragments) {
            mTabHost.addTab(mTabHost.newTabSpec(item.getName()).setIndicator(item.getName()), item, null);
        }

        mTabViews = new View[]{findViewById(R.id.tab_homepage), findViewById(R.id.tab_note), findViewById(R.id.tab_add),
                findViewById(R.id.tab_find),findViewById(R.id.tab_mine)};
        for (View tab : mTabViews) {
            tab.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_homepage: //首页
                selectTab(TAB_HOME);
                break;
            case R.id.tab_note: //日记
                selectTab(TAB_NOTE);
                break;
            case R.id.tab_add: //添加
                selectTab(TAB_ADD);
                break;
            case R.id.tab_find: //发现
                selectTab(TAB_FIND);
                break;
            case R.id.tab_mine: //我的
                selectTab(TAB_MY);
                break;
            default:
                break;
        }
    }
    /**
     * 初始化Tab
     */
    private void initTab() {
        mTabViews[mSelectedIndex].setSelected(true);
        selectTab(mSelectedIndex);
    }
    private void selectTab(int index) {
        if (index >= 0 && mTabHost.getCurrentTab() != index) {
            for (int i = 0; i < mTabViews.length; i++) {
                mTabViews[i].setSelected(index == i);
            }

            mTabHost.setCurrentTab(index);
               /* if (MineScene.isLogined()) { //登录状态
                    mTabViews[i].setSelected(index == i);
                } else { //未登录状态*/
                   // if (index != TAB_MESSAGE) { // && index != TAB_PUBLISH
                     //   mTabViews[i].setSelected(index == i);
                  //  }
                }
            }

 //           StatusBarUtil.setTransparentForImageView(this, null);
 //           if (index == TAB_MESSAGE) { //消息 发布需要判断登录  || index == TAB_PUBLISH
//                if (MineScene.isLogined()) {
//                    if (index == TAB_MESSAGE) showNewMessageDot(false);
/*                    mTabHost.setCurrentTab(index);
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            } else {
                mTabHost.setCurrentTab(index);
            }
        }
    }*/
}
/*
    //定义底部标签图片大小
    Drawable drawableFirst = getResources().getDrawable(R.drawable.selector_ic_first);
    drawableFirst.setBounds(0, 0, 69, 69);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
            rbFirst.setCompoundDrawables(null, drawableFirst, null, null);//只放上面

            Drawable drawableSearch = getResources().getDrawable(R.drawable.selector_ic_search);
            drawableSearch.setBounds(0, 0, 69, 69);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
            rbSearch.setCompoundDrawables(null, drawableSearch, null, null);//只放上面

            Drawable drawableMe = getResources().getDrawable(R.drawable.selector_ic_people);
            drawableMe.setBounds(0, 0, 69, 69);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
            rbMe.setCompoundDrawables(null, drawableMe, null, null);//只放上面
            }*/
