package com.pywl.likegreen.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.zhouwei.library.CustomPopWindow;
import com.flyco.tablayout.SlidingTabLayout;
import com.pywl.likegreen.MainActivity;
import com.pywl.likegreen.R;
import com.pywl.likegreen.activity.AuditAndLiveActivity;
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
    private View mMyMore, mSystemSetting, mMsgDriect;
    private String[] items = new String[]{"视频", "直播", "花园", "喜帖"};
    private PopupWindow popupWindow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_my, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View v) {
        mStMy = (SlidingTabLayout) v.findViewById(R.id.st_my);
        mViewpagerMy = (ViewPager) v.findViewById(R.id.viewpager_home_my);
        mMyMore = v.findViewById(R.id.my_more);//弹popuwindow
        mSystemSetting = v.findViewById(R.id.system_setting);//系统设置
        mMsgDriect = v.findViewById(R.id.msg_driect);//消息私信
        mMyMore.setOnClickListener(this);
        mSystemSetting.setOnClickListener(this);
        mMsgDriect.setOnClickListener(this);
    }

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MyVideoFragment());
        fragments.add(new MyLiveFragment());
        fragments.add(new MyGardenFragment());
        fragments.add(new MyWeddingCardFragment());
        mStMy.setViewPager(mViewpagerMy, items, (FragmentActivity) getActivity(), fragments);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_more://点击。。。
                showPopwindow();
                break;
            case R.id.system_setting:
                Intent intentSystemSetting = new Intent(getActivity(), SystemSettingsActivity.class);
                startActivity(intentSystemSetting);
                break;
            case R.id.msg_driect://点击铃铛
                Intent intentMyMessageAndDirectActivity = new Intent(getActivity(), MyMessageAndDirectActivity.class);
                intentMyMessageAndDirectActivity.putExtra("HomeMyFragment",0);
                startActivity(intentMyMessageAndDirectActivity);
                break;
            case R.id.pop_privateletter://私信
                Intent intentMyMessageAndDirectActivity2 = new Intent(getActivity(), MyMessageAndDirectActivity.class);
                intentMyMessageAndDirectActivity2.putExtra("HomeMyFragment",1);
                startActivity(intentMyMessageAndDirectActivity2);
                popupWindow.dismiss();
                break;
            case R.id.pop_audit://视频审核
                Intent intentAuditAndLiveActivity = new Intent(getActivity(), AuditAndLiveActivity.class);
                startActivity(intentAuditAndLiveActivity);
                break;
        }
    }

    private void showPopwindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_menu, null);
        //私信
        LinearLayout pop_privateletter = contentView.findViewById(R.id.pop_privateletter);
        pop_privateletter.setOnClickListener(this);
        //审核
        LinearLayout pop_audit = contentView.findViewById(R.id.pop_audit);
        pop_audit.setOnClickListener(this);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        //设置半透明
        WindowManager.LayoutParams lp = getActivity().getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
        //恢复正常
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(lp);
            }
        });
        popupWindow.showAsDropDown(mMyMore, -70, 5);
    }

}

