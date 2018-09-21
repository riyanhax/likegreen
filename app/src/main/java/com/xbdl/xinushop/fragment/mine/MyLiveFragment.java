package com.xbdl.xinushop.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.LiveNowActivity;
import com.xbdl.xinushop.activity.mine.LivePlayBackActivity;
import com.xbdl.xinushop.activity.mine.LivePreViewActivity;

/**
 * Created by theWind on 2018/8/1.
 * 我的页面下面的直播
 */

public class MyLiveFragment extends Fragment implements View.OnClickListener {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_live, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.rl_my_trailer).setOnClickListener(this);//预告
        view.findViewById(R.id.rl_my_playback).setOnClickListener(this);//回放
        view.findViewById(R.id.rl_my_air_on).setOnClickListener(this);//即将开播
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_my_trailer:
                Intent intentLivePreViewActivity = new Intent(getContext(), LivePreViewActivity.class);
                startActivity(intentLivePreViewActivity);
                break;
            case R.id.rl_my_playback:
                Intent intentLivePlayBackActivity = new Intent(getContext(), LivePlayBackActivity.class);
                startActivity(intentLivePlayBackActivity);
                break;
            case R.id.rl_my_air_on:
                Intent intentLiveNowActivity = new Intent(getContext(), LiveNowActivity.class);
                startActivity(intentLiveNowActivity);
                break;
        }
    }
}
