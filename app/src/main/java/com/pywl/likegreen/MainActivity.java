package com.pywl.likegreen;


import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pywl.likegreen.activity.EquipmentActivity;
import com.pywl.likegreen.activity.PlantDiaryActivity;
import com.pywl.likegreen.activity.ShortCameraActivity;
import com.pywl.likegreen.activity.WriteLongPostActivity;
import com.pywl.likegreen.activity.mian.ApplyLiveActivity;
import com.pywl.likegreen.activity.mine.ShareLiftActivity;
import com.pywl.likegreen.base.BaseActivity;
import com.pywl.likegreen.bean.CallTab;
import com.pywl.likegreen.fragment.home.HomeFindFragment;
import com.pywl.likegreen.fragment.home.HomeMyFragment;
import com.pywl.likegreen.fragment.home.HomeNoteFragment;
import com.pywl.likegreen.fragment.home.HomePageFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.UMShareAPI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    final RxPermissions rxPermissions = new RxPermissions(this);
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";//测试url

    Fragment main_home, main_note, main_find, main_mine,main_add;
    private RadioGroup main_radio;
    private RadioButton main_rbt_home,main_rbt_add,main_rbt_mine,main_rbt_find,main_rbt_note;
    private FragmentTransaction transaction;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        initView();
        initFragment();
        initPermissions();

        JMessageClient.login(getString(R.string.appidjiguang), "123456", new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                Log.i("asdf",""+i);
            }

        });



    }

    @Override
    protected void onResume() {

       /* HashMap<String, Boolean> stringBooleanHashMap = netWorkStateReceiver.wifiState();
        for (Map.Entry<String,Boolean> entry:stringBooleanHashMap.entrySet()){
            Log.v("nihaoma",entry.getKey()+entry.getValue());
        }*/
        super.onResume();

    }

    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (main_home == null) {
            main_home = new HomePageFragment();
            transaction.add(R.id.fragment_container, main_home);
            main_rbt_home.setTextColor(getResources().getColor(R.color.green));
        }
        transaction.commitAllowingStateLoss();
    }
    protected void initView() {
        main_radio = (RadioGroup) findViewById(R.id.main_radio);
        main_rbt_home = (RadioButton) findViewById(R.id.main_rbt_home);
        main_rbt_add = (RadioButton) findViewById(R.id.main_rbt_add);
        main_rbt_find = (RadioButton) findViewById(R.id.main_rbt_find);
        main_rbt_mine = (RadioButton) findViewById(R.id.main_rbt_mine);
        main_rbt_note = (RadioButton) findViewById(R.id.main_rbt_note);
        main_rbt_add.setOnClickListener(this);
        main_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                setTab(checkedId);
            }
        });
        findViewById(R.id.iv_mian_add).setOnClickListener(this);
    }

    private void setTab(@IdRes int checkedId) {
        transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (checkedId) {
            case R.id.main_rbt_home:
                if (main_home == null) {
                    main_home = new HomePageFragment();
                    transaction.add(R.id.fragment_container, main_home);
                } else {
                    transaction.show(main_home);
                }
                main_radio.setBackgroundColor(Color.TRANSPARENT);
                main_rbt_home.setTextColor(getResources().getColor(R.color.green));
                main_rbt_home.setChecked(true);
                main_rbt_add.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_find.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_mine.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_note.setTextColor(getResources().getColor(R.color.maintextcolor));
                EventBus.getDefault().post(CallTab.MAIN);
                break;
            case R.id.main_rbt_note:
                if (main_note == null) {
                    main_note = new HomeNoteFragment();
                    transaction.add(R.id.fragment_container, main_note);
                } else {
                    transaction.show(main_note);
                }
                main_radio.setBackgroundColor(Color.WHITE);
                main_rbt_home.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_add.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_find.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_mine.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_note.setTextColor(getResources().getColor(R.color.green));
                main_rbt_note.setChecked(true);
                EventBus.getDefault().post(CallTab.NOTE);
                break;
           // case R.id.main_rbt_add:
                /*Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent,1);*/
                /*if (main_add == null) {

                    transaction.add(R.id.fragment_container, main_add);
                } else {
                    transaction.show(main_add);
                }*/
               // showPopupWindow();
                /*main_rbt_home.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_add.setTextColor(getResources().getColor(R.color.green));
                main_rbt_add.setChecked(true);
                main_rbt_find.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_mine.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_note.setTextColor(getResources().getColor(R.color.maintextcolor));*/
               // break;
            case R.id.main_rbt_find:
                if (main_find == null) {
                    main_find = new HomeFindFragment();
                    transaction.add(R.id.fragment_container, main_find);
                } else {
                    transaction.show(main_find);
                }
                main_radio.setBackgroundColor(Color.WHITE);
                main_rbt_home.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_add.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_find.setTextColor(getResources().getColor(R.color.green));
                main_rbt_find.setChecked(true);
                main_rbt_mine.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_note.setTextColor(getResources().getColor(R.color.maintextcolor));
                EventBus.getDefault().post(CallTab.FIND);
                break;
            case R.id.main_rbt_mine:
                if (main_mine == null) {
                    main_mine = new HomeMyFragment();
                    transaction.add(R.id.fragment_container, main_mine);
                } else {
                    transaction.show(main_mine);
                }
                main_radio.setBackgroundColor(Color.WHITE);
                main_rbt_home.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_add.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_find.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_mine.setTextColor(getResources().getColor(R.color.green));
                main_rbt_mine.setChecked(true);
                main_rbt_note.setTextColor(getResources().getColor(R.color.maintextcolor));
                EventBus.getDefault().post(CallTab.MINE);
                break;
        }
        transaction.commitAllowingStateLoss();
    }



    public void hideAllFragment(FragmentTransaction transaction) {
        if (main_home != null) {
            transaction.hide(main_home);
        }
        if (main_find != null) {
            transaction.hide(main_find);
        }
        if (main_mine != null) {
            transaction.hide(main_mine);
        }
        if (main_add != null) {
           // transaction.hide(main_add);
        }
        if (main_note != null) {
            transaction.hide(main_note);
        }
        main_rbt_mine.setChecked(false);
        main_rbt_add.setChecked(false);
        main_rbt_find.setChecked(false);
        main_rbt_home.setChecked(false);
        main_rbt_note.setChecked(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //QQ分享
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
        //add页面结束后返回首页
        if (resultCode==2){
            if (requestCode==1){
                main_rbt_home.setChecked(true);
            }

        }
    }
    //接收eventbus事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setTab(CallTab callTab){
    /*    switch (callTab){
            case MAIN:
                main_rbt_home.setChecked(true);
                break;
            case NOTE:
                main_rbt_note.setChecked(true);
                break;
            case MINE:
                main_rbt_mine.setChecked(true);
                break;
            case FIND:
                main_rbt_find.setChecked(true);
                break;
        }*/
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initPermissions() {
        rxPermissions.request(
                Manifest.permission.INTERNET,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_PHONE_STATE)
                .subscribe();
    }
    private void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_add, null);
        contentView.findViewById(R.id.rl_add_close).setOnClickListener(this);//关闭
        contentView.findViewById(R.id.ll_add_shortvideo).setOnClickListener(this);//短视频
        contentView.findViewById(R.id.ll_add_plantdiary).setOnClickListener(this);//种植
        contentView.findViewById(R.id.ll_add_equipment).setOnClickListener(this);//应用设备
        contentView.findViewById(R.id.ll_add_longpost).setOnClickListener(this);//长贴子
        contentView.findViewById(R.id.ll_add_sharelife).setOnClickListener(this);//分享生活
        contentView.findViewById(R.id.ll_add_live).setOnClickListener(this);//直播

        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //设置为失去焦点 方便监听返回键的监听
        popupWindow.setFocusable(false);
        // 如果想要popupWindow 遮挡住状态栏可以加上这句代码
        popupWindow.setClippingEnabled(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);

        popupWindow.showAtLocation(main_rbt_add, Gravity.NO_GRAVITY, 0, 0);
    }

    @Override
    public void onBackPressed() {
        if (popupWindow!=null){
            popupWindow.dismiss();
        }
            super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_mian_add:
                showPopupWindow();
                break;
            case R.id.rl_add_close://关闭
                popupWindow.dismiss();

                break;
            case R.id.ll_add_shortvideo://短视频
                Intent intentShortCameraActivity = new Intent(MainActivity.this, ShortCameraActivity.class);
                startActivity(intentShortCameraActivity);
                popupWindow.dismiss();
                break;
            case R.id.ll_add_plantdiary://种植日记
                Intent intentPlantDiaryActivity = new Intent(MainActivity.this, PlantDiaryActivity.class);
                startActivity(intentPlantDiaryActivity);
                popupWindow.dismiss();
                break;
            case R.id.ll_add_equipment://应用设备
                Intent intentEquipmentActivity = new Intent(MainActivity.this, EquipmentActivity.class);
                startActivity(intentEquipmentActivity);
                popupWindow.dismiss();
                break;
            case R.id.ll_add_longpost://长贴子
                Intent intentLongPostActivity = new Intent(MainActivity.this, WriteLongPostActivity.class);
                startActivity(intentLongPostActivity);
                popupWindow.dismiss();
                break;
            case R.id.ll_add_sharelife://分享生活
                Intent intentShareLiftActivity = new Intent(MainActivity.this, ShareLiftActivity.class);
                startActivity(intentShareLiftActivity);
                popupWindow.dismiss();
                break;
            case R.id.ll_add_live://直播
                Intent intentApplyLiveActivity = new Intent(MainActivity.this, ApplyLiveActivity.class);
                startActivity(intentApplyLiveActivity);
                popupWindow.dismiss();
                break;
        }
    }
    private void popupWindowDissmiss(){

        popupWindow.dismiss();
    }
}
