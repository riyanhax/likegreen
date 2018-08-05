package com.pywl.likegreen;



import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pywl.likegreen.adapter.BaseActivity;
import com.pywl.likegreen.fragment.home.HomeAddFragment;
import com.pywl.likegreen.fragment.home.HomeFindFragment;
import com.pywl.likegreen.fragment.home.HomeMyFragment;
import com.pywl.likegreen.fragment.home.HomeNoteFragment;
import com.pywl.likegreen.fragment.home.HomePageFragment;


public class MainActivity extends BaseActivity {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";//测试url
//    private Class<Fragment>[] mFragments = new Class[]{HomePageFragment.class, HomeNoteFragment.class, HomeAddFragment.class,
//            HomeFindFragment.class, HomeMyFragment.class};
    Fragment main_home, main_note, main_find, main_mine,main_add;
    private RadioGroup main_radio;
    private RadioButton main_rbt_home,main_rbt_add,main_rbt_mine,main_rbt_find,main_rbt_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
    }
    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (main_home == null) {
            main_home = new HomePageFragment();
            transaction.add(R.id.fragment_container, main_home);
        }
        transaction.commitAllowingStateLoss();
    }
    protected void initView() {
        main_radio = findViewById(R.id.main_radio);
        main_rbt_home = findViewById(R.id.main_rbt_home);
        main_rbt_add = findViewById(R.id.main_rbt_add);
        main_rbt_find = findViewById(R.id.main_rbt_find);
        main_rbt_mine = findViewById(R.id.main_rbt_mine);
        main_rbt_note = findViewById(R.id.main_rbt_note);
        main_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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
                        break;
                    case R.id.main_rbt_add:
                        if (main_add == null) {
                            main_add = new HomeAddFragment();
                            transaction.add(R.id.fragment_container, main_add);
                        } else {
                            transaction.show(main_add);
                        }
                        main_rbt_home.setTextColor(getResources().getColor(R.color.maintextcolor));
                        main_rbt_add.setTextColor(getResources().getColor(R.color.green));
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
                        main_rbt_note.setTextColor(getResources().getColor(R.color.maintextcolor));
                        break;
                }
                transaction.commitAllowingStateLoss();
            }
        });
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
    }

}
