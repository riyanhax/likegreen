package com.xbdl.xinushop.activity.mine.order;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.fragment.mine.messagedirect.MyDirectFragment;
import com.xbdl.xinushop.fragment.mine.messagedirect.MyMessageFragment;
import com.xbdl.xinushop.fragment.mine.order.AllorderFragment;
import com.xbdl.xinushop.fragment.mine.order.WaitPayFragment;
import com.xbdl.xinushop.fragment.mine.order.WaitSendFragment;
import com.xbdl.xinushop.fragment.mine.order.WaitTakeGoodsFragment;
import com.xbdl.xinushop.fragment.mine.order.WaiteValuateFragment;

import java.util.ArrayList;

/*
* 我的订单
* */
public class MyOrderActivity extends AppCompatActivity {
    private SlidingTabLayout mStAuditLive;
    private ViewPager mViewPager;
    private String[] times=new String[]{"全部","待付款","待发货","待收货","待评价"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        initView();
        initData();
    }

    private void initView() {
        findViewById(R.id.iv_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mStAuditLive=(SlidingTabLayout)findViewById(R.id.st_my);
        mViewPager=(ViewPager) findViewById(R.id.viewpager_home_my);
    }

    private void initData() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AllorderFragment());
        fragments.add(new WaitPayFragment());
        fragments.add(new WaitSendFragment());
        fragments.add(new WaitTakeGoodsFragment());
        fragments.add(new WaiteValuateFragment());

        mStAuditLive.setViewPager(mViewPager,times,this,fragments);
      //  mStAuditLive.setCurrentTab(number);

    }
}
