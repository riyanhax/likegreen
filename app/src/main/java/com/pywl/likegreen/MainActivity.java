package com.pywl.likegreen;



import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pywl.likegreen.activity.AddActivity;
import com.pywl.likegreen.adapter.BaseActivity;
import com.pywl.likegreen.bean.CallTab;
import com.pywl.likegreen.fragment.home.HomeFindFragment;
import com.pywl.likegreen.fragment.home.HomeMyFragment;
import com.pywl.likegreen.fragment.home.HomeNoteFragment;
import com.pywl.likegreen.fragment.home.HomePageFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends BaseActivity {
    final RxPermissions rxPermissions = new RxPermissions(this);
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";//测试url
//    private Class<Fragment>[] mFragments = new Class[]{HomePageFragment.class, HomeNoteFragment.class, HomeAddFragment.class,
//            HomeFindFragment.class, HomeMyFragment.class};
    Fragment main_home, main_note, main_find, main_mine,main_add;
    private RadioGroup main_radio;
    private RadioButton main_rbt_home,main_rbt_add,main_rbt_mine,main_rbt_find,main_rbt_note;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();
        initFragment();
        initPermissions();
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
        main_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                setTab(checkedId);
            }
        });
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

                main_rbt_home.setTextColor(getResources().getColor(R.color.green));
                main_rbt_home.setChecked(true);
                main_rbt_add.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_find.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_mine.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_note.setTextColor(getResources().getColor(R.color.maintextcolor));

                break;
            case R.id.main_rbt_note:
                if (main_note == null) {
                    main_note = new HomeNoteFragment();
                    transaction.add(R.id.fragment_container, main_note);
                } else {
                    transaction.show(main_note);
                }
                main_rbt_home.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_add.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_find.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_mine.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_note.setTextColor(getResources().getColor(R.color.green));
                main_rbt_note.setChecked(true);
                break;
            case R.id.main_rbt_add:
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent,1);
                /*if (main_add == null) {
                    main_add = new HomeAddFragment();
                    transaction.add(R.id.fragment_container, main_add);
                } else {
                    transaction.show(main_add);
                }*/
                main_rbt_home.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_add.setTextColor(getResources().getColor(R.color.green));
                main_rbt_add.setChecked(true);
                main_rbt_find.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_mine.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_note.setTextColor(getResources().getColor(R.color.maintextcolor));
                break;
            case R.id.main_rbt_find:
                if (main_find == null) {
                    main_find = new HomeFindFragment();
                    transaction.add(R.id.fragment_container, main_find);
                } else {
                    transaction.show(main_find);
                }
                main_rbt_home.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_add.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_find.setTextColor(getResources().getColor(R.color.green));
                main_rbt_find.setChecked(true);
                main_rbt_mine.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_note.setTextColor(getResources().getColor(R.color.maintextcolor));
                break;
            case R.id.main_rbt_mine:
                if (main_mine == null) {
                    main_mine = new HomeMyFragment();
                    transaction.add(R.id.fragment_container, main_mine);
                } else {
                    transaction.show(main_mine);
                }
                main_rbt_home.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_add.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_find.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_mine.setTextColor(getResources().getColor(R.color.green));
                main_rbt_mine.setChecked(true);
                main_rbt_note.setTextColor(getResources().getColor(R.color.maintextcolor));
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
            transaction.hide(main_add);
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
        switch (callTab){
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
        }
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
}
