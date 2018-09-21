package com.xbdl.xinushop.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.AuditAndLiveActivity;
import com.xbdl.xinushop.activity.mine.MyFansActivity;
import com.xbdl.xinushop.activity.mine.MyFocuseActivity;
import com.xbdl.xinushop.activity.mine.MyMessageAndDirectActivity;
import com.xbdl.xinushop.activity.MyProductsReleaseActivity;
import com.xbdl.xinushop.activity.mine.PersonalDataActivity;
import com.xbdl.xinushop.activity.mine.SystemSettingsActivity;
import com.xbdl.xinushop.base.BaseFragment;

import com.xbdl.xinushop.fragment.mine.MyGardenFragment;
import com.xbdl.xinushop.fragment.mine.MyLiveFragment;
import com.xbdl.xinushop.fragment.mine.MyVideoFragment;
import com.xbdl.xinushop.fragment.mine.MyWeddingCardFragment;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by theWind on 2018/8/1.
 */

public class HomeMyFragment extends BaseFragment implements View.OnClickListener {

    private SlidingTabLayout mStMy;
    private ViewPager mViewpagerMy;
    private View mMyMore, mSystemSetting, mMsgDriect;
    private String[] items = new String[]{"视频", "直播", "花园", "喜帖"};
    private PopupWindow popupWindow;
    private TextView mMyfocuse,mMyfans;
    private CircleImageView headIcon;//头像
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
        mMyfocuse = (TextView)v.findViewById(R.id.my_home_focuse);//关注
        mMyfans = (TextView)v.findViewById(R.id.my_home_fans);//关注
        v.findViewById(R.id.tv_huozanshu).setOnClickListener(this);//获赞数
        headIcon =(CircleImageView) v.findViewById(R.id.iv_my_head);//头像
        mMyMore.setOnClickListener(this);
        mSystemSetting.setOnClickListener(this);
        mMsgDriect.setOnClickListener(this);
        mMyfocuse.setOnClickListener(this);
        mMyfans.setOnClickListener(this);
        headIcon.setOnClickListener(this);
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
                popupWindow.dismiss();
                break;
            case R.id.my_home_focuse://我的关注
                Intent intentMyFocuseActivity = new Intent(getActivity(), MyFocuseActivity.class);
                startActivity(intentMyFocuseActivity);
                break;
            case R.id.my_home_fans://我的粉丝
                Intent intentMyFansActivity = new Intent(getActivity(), MyFansActivity.class);
                startActivity(intentMyFansActivity);
                break;
            case R.id.tv_huozanshu://点赞数
                showDianZanShuPop();
                break;
            case R.id.pop_mygoods: //我发布的商品
                Intent intentMyProductsReleaseActivity = new Intent(getActivity(), MyProductsReleaseActivity.class);
                startActivity(intentMyProductsReleaseActivity);
                popupWindow.dismiss();
                break;
            case R.id.iv_my_head://头像
                Intent intentPersonalDataActivity = new Intent(getActivity(), PersonalDataActivity.class);
                startActivity(intentPersonalDataActivity);
                break;
            case R.id.pop_mywallet://钱包

                break;
        }
    }

    private void showDianZanShuPop() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_huo_zan, null);

        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
        popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);

    }

    private void showPopwindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_menu, null);
        //钱包
        LinearLayout pop_mywallet = contentView.findViewById(R.id.pop_mywallet);
        pop_mywallet.setOnClickListener(this);
        //私信
        LinearLayout pop_privateletter = contentView.findViewById(R.id.pop_privateletter);
        pop_privateletter.setOnClickListener(this);
        //审核
        LinearLayout pop_audit = contentView.findViewById(R.id.pop_audit);
        pop_audit.setOnClickListener(this);
        //我发布的商品
        LinearLayout pop_goods = contentView.findViewById(R.id.pop_mygoods);
        pop_goods.setOnClickListener(this);
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
        popupWindow.showAsDropDown(mMyMore, -80, 5);
    }

}
